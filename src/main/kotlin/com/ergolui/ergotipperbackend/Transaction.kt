package com.ergolui.ergotipperbackend

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("transaction")
data class Transaction(@Id val id: String?=null,
                       val from_display: String?="",
                       val from_account: String,
                       val from_wallet: String,
                       val to_display: String?="",
                       val to_account: String,
                       val to_wallet: String,
                       val amount: Long,
                       var succeeded: String="0",
                       val token: String,
                       val token_amount: Double,
val comment: String?="")