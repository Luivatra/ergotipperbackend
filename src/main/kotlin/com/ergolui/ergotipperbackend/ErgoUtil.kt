package com.ergolui.ergotipperbackend

import org.ergoplatform.appkit.*
import org.ergoplatform.appkit.impl.ErgoTreeContract


class ErgoUtil {

    companion object {
        fun validateSeed(seed: String): Boolean
        {
            try {
                Mnemonic.checkEnglishMnemonic(seed.split(" "))
            }
            catch (e: MnemonicValidationException)
            {
                return false
            }
            return true
        }

        fun sendTx(
            mnemonic: SecretString,
            recipient: String,
            amountToSend: Long,
            tokensToSend: List<ErgoToken>
        ): SendTransactionResult {
            try {
                val ergoClient = RestApiErgoClientWithMemory.create(
                    "http://192.168.1.81:9053/",
                    NetworkType.MAINNET,
                    "",
                    RestApiErgoClient.defaultMainnetExplorerUrl
                )
                return ergoClient.execute { ctx: BlockchainContext ->
                    val proverBuilder = ctx.newProverBuilder()
                        .withMnemonic(
                            mnemonic,
                            SecretString.create("")
                        )
                    proverBuilder.withEip3Secret(0)
                    val prover = proverBuilder.build()
                    val contract: ErgoContract = ErgoTreeContract(Address.create(recipient).ergoAddress.script())
                    val signed = BoxOperationsWithMemory.putToContractTx(
                        ctx, prover, true,
                        contract, amountToSend, tokensToSend
                    )
                    ctx.sendTransaction(signed)

                    val txId = signed.id

                    return@execute SendTransactionResult(txId.isNotEmpty(), txId)
                }
            } catch (t: Throwable) {
                return SendTransactionResult(false, errorMsg = t.stackTraceToString())
            }
        }
    }
}