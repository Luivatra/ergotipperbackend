package com.ergolui.ergotipperbackend

import org.springframework.data.jdbc.repository.query.Modifying
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param

interface AccountRepository : CrudRepository<ETBAccount, String> {

    @Query("select * from account")
    fun findAccounts(): List<ETBAccount>

    @Query("select id, platform, username, '' as password, wallet from account where platform=:platform and username=:username")
    fun findAccount(@Param("platform") platform: String,@Param("username") username: String) : ETBAccount?

    @Query("select * from account where platform=:platform and username=:username and password = crypt(:password, password)")
    fun findAccountSecure(@Param("platform") platform: String,@Param("username") username: String, @Param("password") password: String) : ETBAccount?

    @Modifying
    @Query(value = "insert into account (platform,username,password,wallet) values (:platform,:username,crypt(:password, gen_salt('bf', 8)),:wallet)")
    fun newAccount(@Param("platform") platform: String,
                   @Param("username") username: String,
                   @Param("password") password: String,
                   @Param("wallet") wallet: String): String

    @Modifying
    @Query(value = "update account set password = crypt(:newPassword, gen_salt('bf', 8)) where platform = :platform and username = :username and password = crypt(:password, password)")
    fun updatePassword(
        @Param("platform") platform: String,
        @Param("username") username: String,
        @Param("password") password: String,
        @Param("newPassword") newPassword: String
    ): String

    @Modifying
    @Query(value = "update account set wallet = :wallet where platform = :platform and username = :username and password = crypt(:password, password)")
    fun updateWallet(
        @Param("platform") platform: String,
        @Param("username") username: String,
        @Param("password") password: String,
        @Param("wallet") wallet: String
    ): String

}