package com.erp.distribution.desgreenrestkt.data.source.local.dao_security

import com.erp.distribution.desgreenrestkt.data.source.entity_security.FUser
import org.springframework.data.jpa.repository.JpaRepository

interface FUsersJPARepository : JpaRepository<FUser?, Int?> {
    // Optional<FUser> findByEmail(String email);
//    override fun findById(id: Int): FUser

    fun findByEmail(email: String?): FUser?

    // Optional<FUser> findByUsername(String username);
    fun findByUsername(username: String?): FUser?
}