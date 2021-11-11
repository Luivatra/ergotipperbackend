package com.ergolui.ergotipperbackend

import org.springframework.stereotype.Service
import org.ergoplatform.appkit.Mnemonic
import org.springframework.beans.factory.annotation.Value

@Service
class AccountService(val db : AccountRepository,
                     val walletDb : WalletRepository,
                     @Value("\${ergolui.ergotipperbackend.seedsalt}")
                     private val key: String) {
    fun findAccounts(): List<ETBAccount> = db.findAccounts()

    fun findAccount(platform: String, username: String): ETBAccount? = db.findAccount(platform,username)

    fun post(ETBAccount: ETBAccount){
        val newWallet = Wallet (seed = Mnemonic.generateEnglishMnemonic(),id=null)
        newWallet.seed = walletDb.encryptSeed(newWallet.seed,this.key)
        val walletId = walletDb.save(newWallet).id
        ETBAccount.wallet = walletId.toString()
        db.newAccount(ETBAccount.platform,ETBAccount.username,ETBAccount.password,ETBAccount.wallet.toString())
    }

    fun changePassword(platform: String, username: String, password: String, newPassword: String): String{
        val account = db.findAccountSecure(platform,username,password)
        return if (account != null) {
            db.updatePassword(platform, username, password, newPassword)
            ""
        }
        else
            "Could not change to new password, maybe incorrect current password"
    }

    fun getSeed(platform: String, username: String, password: String): String
    {
        val account = db.findAccountSecure(platform,username,password)
        return if (account != null)
            walletDb.getSeed(account.wallet.toString(),this.key)
        else
            "Could not retrieve seed, maybe incorrect password"
    }

    fun restoreSeed(restore: RestoreOperation): RestoreOperation
    {
        val account = db.findAccountSecure(restore.platform,restore.username,restore.password)
        return if (account != null) {
            if (ErgoUtil.validateSeed(restore.newSeed)) {
                val newWallet = Wallet (seed = restore.newSeed,id=null)
                newWallet.seed = walletDb.encryptSeed(newWallet.seed,this.key)
                val walletId = walletDb.save(newWallet).id
                account.wallet = walletId.toString()
                db.updateWallet(account.platform,account.username,restore.password,account.wallet.toString())
                RestoreOperation(restore.platform,restore.username,"","","","Success","")
            } else
                RestoreOperation(restore.platform,restore.username,"","",restore.newSeed,"error","Invalid seed phrase, make sure it is spelled correctly.")
        } else
            RestoreOperation(restore.platform,restore.username,"","","","error","Could not restore seed, maybe incorrect password")
    }

}