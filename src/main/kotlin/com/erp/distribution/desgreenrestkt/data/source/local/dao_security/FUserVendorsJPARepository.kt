package com.erp.distribution.desgreenrestkt.data.source.local.dao_security

import com.erp.distribution.desgreenrestkt.data.source.entity_security.FUserVendors
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FUserVendorsJPARepository : JpaRepository<FUserVendors?, Int?> {
    // Optional<FUser> findByEmail(String email);
    // Optional<FUser> findByUsername(String username);
    @Query("SELECT u FROM FUserVendors u WHERE u.fuserBean.id = :fuserBean")
    fun findAllByParentId(fuserBean: Int): List<FUserVendors>
}