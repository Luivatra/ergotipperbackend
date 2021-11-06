package com.ergolui.ergotipperbackend

import org.springframework.data.jdbc.repository.query.Modifying
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param

interface TokenRepository : CrudRepository<Token, String> {

    @Query("select * from token where LOWER(token_name) = LOWER(:token_name)")
    fun getTokenByName(@Param("token_name") tokenName: String): Token?

    @Query("select * from token where token_id = :token_id")
    fun getTokenById(@Param("token_id") tokenId: String): Token?

}