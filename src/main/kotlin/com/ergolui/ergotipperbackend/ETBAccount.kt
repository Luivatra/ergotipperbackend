package com.ergolui.ergotipperbackend

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("account")
data class ETBAccount(@Id val id: String?,
                      val platform: String,
                      val username: String,
                      val password: String,
                      var wallet: String?)

