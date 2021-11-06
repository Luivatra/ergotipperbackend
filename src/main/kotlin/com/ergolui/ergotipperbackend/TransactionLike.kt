package com.ergolui.ergotipperbackend

data class TransactionLike(val fromDisplay: String,
                            val fromPlatform: String,
                           val fromUsername: String,
                           val toDisplay: String,
                           val toPlatform: String,
                           val toUsername: String,
                           val amount: Long,
                           val tokenName: String,
                           val tokenAmount: Double,
                            val comment: String)
