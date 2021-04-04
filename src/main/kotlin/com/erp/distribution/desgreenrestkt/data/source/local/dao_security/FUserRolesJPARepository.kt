package com.erp.distribution.desgreenrestkt.data.source.local.dao_security

import com.erp.distribution.desgreenrestkt.data.source.entity_security.FUserRolesEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FUserRolesJPARepository : JpaRepository<FUserRolesEntity, Int> {
    @Query("SELECT u FROM FUserRolesEntity u WHERE u.fuserBean.id = :fuserBean")
    fun findAllByParentId(fuserBean: Int): List<FUserRolesEntity>
}