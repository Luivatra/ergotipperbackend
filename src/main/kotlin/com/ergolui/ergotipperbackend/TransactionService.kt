package com.ergolui.ergotipperbackend

import kotlin.math.floor
import kotlin.math.pow
import org.ergoplatform.sdk.ErgoToken
import org.springframework.stereotype.Service

@Service
class TransactionService(
        val db: TransactionRepository,
        val walletService: WalletService,
        val tokenDB: TokenRepository,
        val accountService: AccountService
) {
    fun findTransactions(fromWallet: String, fromAccount: String): List<Transaction> =
            db.findTransactions(fromWallet, fromAccount)

    fun post(transaction: Transaction): SendTransactionResult {
        var tokensToSend: List<ErgoToken> = emptyList()
        if (transaction.token.isNotEmpty()) {
            val token = tokenDB.getTokenByName(transaction.token)
            if (token != null) {
                tokensToSend =
                        listOf<ErgoToken>(
                                ErgoToken(
                                        token.token_id,
                                        floor(
                                                        transaction.token_amount *
                                                                10.0.pow(token.decimals.toDouble())
                                                )
                                                .toLong()
                                )
                        )
            } else {
                return SendTransactionResult(
                        false,
                        errorMsg = "Unknown token name: " + transaction.token
                )
            }
        }
        val tx =
                ErgoUtil.sendTx(
                        walletService.getSeed(transaction.from_wallet),
                        walletService.getAddress(transaction.to_wallet),
                        transaction.amount,
                        tokensToSend,
                        transaction.comment
                )
        if (tx.success) transaction.succeeded = "1" else println(tx.errorMsg)
        db.save(transaction)
        return tx
    }

    fun tip(transactionLike: TransactionLike): SendTransactionResult {
        val fromAccount =
                accountService.findAccount(
                        transactionLike.fromPlatform,
                        transactionLike.fromUsername
                )
        if (fromAccount != null) {
            val toAccount =
                    accountService.findAccount(
                            transactionLike.toPlatform,
                            transactionLike.toUsername
                    )
            if (toAccount != null) {
                return post(
                        Transaction(
                                from_display = transactionLike.fromDisplay,
                                from_account = fromAccount.id.toString(),
                                from_wallet = fromAccount.wallet.toString(),
                                to_display = transactionLike.toDisplay,
                                to_account = toAccount.id.toString(),
                                to_wallet = toAccount.wallet.toString(),
                                amount = transactionLike.amount,
                                token = transactionLike.tokenName,
                                token_amount = transactionLike.tokenAmount,
                                comment = transactionLike.comment
                        )
                )
            } else {
                return SendTransactionResult(false, errorMsg = "To account unknown")
            }
        } else {
            return SendTransactionResult(false, errorMsg = "From account unknown")
        }
    }
}
