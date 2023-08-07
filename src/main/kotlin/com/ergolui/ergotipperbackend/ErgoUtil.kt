package com.ergolui.ergotipperbackend

import org.ergoplatform.appkit.*
import org.ergoplatform.appkit.impl.ErgoTreeContract
import org.ergoplatform.sdk.ErgoToken
import org.ergoplatform.sdk.SecretString
import org.springframework.core.env.Environment
import org.springframework.stereotype.Component


@Component
class ErgoUtil(private val environment: Environment) {

    init {
        env = environment
    }

    companion object {

        lateinit var env: Environment


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
                val feeAmount = env.getProperty("ergolui.ergotipperbackend.feeamount").toString()
                val feeAddress = env.getProperty("ergolui.ergotipperbackend.feeaddress").toString()
                val maxInputBoxes = env.getProperty("ergolui.ergotipperbackend.maxinputboxes").toString()
                val nodeUrl = env.getProperty("ergolui.ergotipperbackend.nodeurl").toString()

                val ergoClient = RestApiErgoClient.create(
                    nodeUrl,
                    NetworkType.MAINNET,
                    "",
                    RestApiErgoClient.defaultMainnetExplorerUrl
                )
                return ergoClient.execute { ctx: BlockchainContext ->
                    val proverBuilder = ctx.newProverBuilder()
                        .withMnemonic(
                            mnemonic,
                            SecretString.create(""),
                            false
                        ).withEip3Secret(0)
                    val prover = proverBuilder.build()
                    val contract: ErgoContract = ErgoTreeContract(Address.create(recipient).ergoAddress.script(), NetworkType.MAINNET)
                    val feeContract = ErgoTreeContract(Address.create(feeAddress).ergoAddress.script(), NetworkType.MAINNET)

                    var boxOperations = BoxOperations.createForSender(prover.eip3Addresses[0], ctx).withAmountToSpend(amountToSend+feeAmount.toLong()).withFeeAmount(1000000)
                    if (tokensToSend.isNotEmpty())
                        boxOperations = boxOperations.withTokensToSpend(tokensToSend)

                    val txB: UnsignedTransactionBuilder = boxOperations.blockchainContext.newTxBuilder()

                    val newBoxBuilder = txB.outBoxBuilder().contract(contract).value(amountToSend)
                    val newBox =
                        if (tokensToSend.isNotEmpty())
                            newBoxBuilder.tokens(*tokensToSend.toTypedArray()).build()
                        else
                            newBoxBuilder.build()
                    val tipFeeBox = txB.outBoxBuilder().contract(feeContract).value(feeAmount.toLong()).build()

                    txB.addOutputs(newBox, tipFeeBox)

                    val boxesLoader = RecordingBoxesLoader().apply { withAllowChainedTx(true) }

                    boxOperations.withInputBoxesLoader(boxesLoader)
                        .withMaxInputBoxesToSelect(maxInputBoxes.toInt())

                    val boxesToSpend: List<InputBox> = boxOperations.loadTop(0)

                    txB.addInputs(*boxesToSpend.toTypedArray())
                        .fee(boxOperations.feeAmount)
                        .sendChangeTo(boxOperations.senders[0])

                    val addedInputs = txB.inputBoxes
                    if (addedInputs.size < maxInputBoxes.toInt()) {
                        val firstSenderErgoTree =
                            boxOperations.senders.first().toErgoContract().ergoTree
                        val extraInputBoxes = boxesLoader.allBoxesLoaded.filter {
                            it.ergoTree == firstSenderErgoTree && !addedInputs.contains(it)
                        }.take(maxInputBoxes.toInt() - addedInputs.size)
                        if (extraInputBoxes.isNotEmpty())
                            txB.addInputs(*extraInputBoxes.toTypedArray())
                    }

                    val signed = prover.sign(txB.build())
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

private class RecordingBoxesLoader : ExplorerAndPoolUnspentBoxesLoader() {
    val allBoxesLoaded = ArrayList<InputBox>()

    override fun loadBoxesPage(
        ctx: BlockchainContext,
        sender: Address,
        page: Int
    ): MutableList<InputBox> {
        val boxesLoaded = super.loadBoxesPage(ctx, sender, page)
        allBoxesLoaded.addAll(boxesLoaded)
        return boxesLoaded
    }
}