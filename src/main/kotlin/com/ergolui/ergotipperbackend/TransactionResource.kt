package com.ergolui.ergotipperbackend

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/transaction")
class TransactionResource(val service : TransactionService) {

    @PostMapping
    fun post(@RequestBody transactionLike: TransactionLike): SendTransactionResult {
        return service.tip(transactionLike)
    }
}