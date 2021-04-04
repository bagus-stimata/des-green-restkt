package com.erp.distribution.desgreenrestkt.data.source.local.dao_security

import com.erp.distribution.desgreenrestkt.data.source.entity_security.FUserVendorsEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FUserVendorsJPARepository : JpaRepository<FUserVendorsEntity, Int> {
    @Query("SELECT u FROM FUserVendorsEntity u WHERE u.fuserBean.id = :fuserBean")
    fun findAllByParentId(fuserBean: Int): List<FUserVendorsEntity>
}