package com.ergolui.ergotipperbackend

import org.ergoplatform.appkit.InputBox

data class SendTransactionResult(val success: Boolean, val id: String?="", val errorMsg: String?="")
