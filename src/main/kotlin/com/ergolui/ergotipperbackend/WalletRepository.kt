package com.ergolui.ergotipperbackend

import org.springframework.data.jdbc.repository.query.Modifying
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param

interface WalletRepository : CrudRepository<Wallet, String> {

    @Query("select * from wallet")
    fun findWallets(): List<Wallet>

    @Query("select convert_from(decrypt(seed::bytea, :key::bytea, 'aes'), 'utf-8') from wallet where id = :wallet_id")
    fun getSeed(@Param("wallet_id") wallet_id: String, @Param("key") key: String): String

    @Modifying
    @Query(value = "insert into wallet (seed) values (encrypt(:seed::bytea, :key::bytea, 'aes'))")
    fun newWallet(@Param("seed") seed: String, @Param("key") key: String): String

    @Query("select encrypt(:seed::bytea, :key::bytea, 'aes')")
    fun encryptSeed(@Param("seed") seed: String, @Param("key") key: String): String

}