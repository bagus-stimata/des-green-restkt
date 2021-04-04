package com.erp.distribution.desgreenrestkt.data.source.local.dao_security

import com.erp.distribution.desgreenrestkt.data.source.entity_security.FUserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface FUsersJPARepository : JpaRepository<FUserEntity, Int> {

    fun findByEmail(email: String): FUserEntity

    // Optional<FUser> findByUsername(String username);
    fun findByUsername(username: String): FUserEntity
}