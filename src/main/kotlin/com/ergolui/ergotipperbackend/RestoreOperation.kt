package com.ergolui.ergotipperbackend

data class RestoreOperation(val platform: String, val username: String, val password: String, val oldSeed: String?, val newSeed: String, val status: String?, val statusMessage: String?)
