package com.erp.distribution.desgreenrestkt.security_repository

import com.erp.distribution.desgreenrestkt.security_model.FUser
import org.springframework.data.jpa.repository.JpaRepository

interface FUsersJPARepository : JpaRepository<FUser?, Int?> {
    // Optional<FUser> findByEmail(String email);
//    override fun findById(id: Int): FUser

    fun findByEmail(email: String?): FUser?

    // Optional<FUser> findByUsername(String username);
    fun findByUsername(username: String?): FUser?
}