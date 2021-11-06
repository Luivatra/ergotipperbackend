package com.ergolui.ergotipperbackend

import org.springframework.stereotype.Service
import org.ergoplatform.appkit.Address
import org.ergoplatform.appkit.NetworkType
import org.ergoplatform.appkit.SecretString
import org.springframework.beans.factory.annotation.Value
import org.http4k.client.ApacheClient
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.format.Jackson.asJsonObject
import kotlin.math.pow

@Service
class WalletService(val db : WalletRepository,
                    val tokenDb: TokenRepository,
                    @Value("\${ergolui.ergotipperbackend.seedsalt}")
                    private val key: String) {

    fun findWallets(): List<Wallet> = db.findWallets()

    fun getSeed(wallet_id: String) : SecretString {
        return SecretString.create(db.getSeed(wallet_id,this.key))
    }

    fun getBalance(wallet_id: String) : BalanceTotal {
        val address = this.getAddress(wallet_id)
        val client = ApacheClient()
        val url = "https://api.ergoplatform.com/api/v1/addresses/"+address+"/balance/total"
        val request = Request(Method.GET, url)
        val response = client(request)
        val jsonResponse = response.bodyString().asJsonObject()

        val confirmedTokenAmounts = mutableListOf<TokenBalance>()
        for (token in jsonResponse["confirmed"]["tokens"])
        {
            val tipToken = tokenDb.getTokenById(token["tokenId"].textValue())
            if (tipToken != null)
            {
                confirmedTokenAmounts.add(TokenBalance(tipToken,token["amount"].asDouble()/10.0.pow(token["decimals"].asDouble())))
            }
        }
        val confirmedETBBalance = ETBBalance(jsonResponse["confirmed"]["nanoErgs"].bigIntegerValue(),confirmedTokenAmounts.toList())

        val unconfirmedTokenAmounts = mutableListOf<TokenBalance>()
        for (token in jsonResponse["unconfirmed"]["tokens"])
        {
            val tipToken = tokenDb.getTokenById(token["tokenId"].textValue())
            if (tipToken != null)
            {
                unconfirmedTokenAmounts.add(TokenBalance(tipToken,token["amount"].asDouble()/10.0.pow(token["decimals"].asDouble())))
            }
        }
        val unconfirmedETBBalance = ETBBalance(jsonResponse["unconfirmed"]["nanoErgs"].bigIntegerValue(),unconfirmedTokenAmounts.toList())
        return BalanceTotal(confirmedETBBalance,unconfirmedETBBalance)
    }

    fun getAddress(wallet_id: String) : String = Address.createEip3Address(0,NetworkType.MAINNET,this.getSeed(wallet_id),SecretString.create("")).toString()

    fun post(wallet: Wallet){
        db.newWallet(wallet.seed,this.key)
    }
}