package com.ergolui.ergotipperbackend

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table


@Table("wallet")
data class Wallet(@Id val id: String?,
                   var seed: String)
