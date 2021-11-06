package com.ergolui.ergotipperbackend

import java.math.BigInteger

data class ETBBalance(val nanoErgBalance: BigInteger, val tokenBalances: List<TokenBalance>)
