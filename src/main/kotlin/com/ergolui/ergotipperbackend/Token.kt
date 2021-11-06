package com.ergolui.ergotipperbackend

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("token")
data class Token(@Id val token_id: String?,
                   val token_name: String,
                 val decimals: Int)