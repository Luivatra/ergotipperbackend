package com.ergolui.ergotipperbackend

import org.springframework.data.jdbc.repository.query.Modifying
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param

interface TransactionRepository : CrudRepository<Transaction, String> {

    @Query("select * from transaction where from_account = :from_account OR from_wallet = :from_wallet order by insert_time")
    fun findTransactions(@Param("from_wallet") fromWallet: String, @Param("from_account") fromAccount: String): List<Transaction>

}