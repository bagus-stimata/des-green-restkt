package com.erp.distribution.desgreenrestkt.data.source.local.dao

import com.erp.distribution.desgreenrestkt.data.source.entity.FGiroEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FGiroJPARepository : JpaRepository<FGiroEntity, Long> {
//    override fun findById(id: Long): FGiroEntity?
    fun findByGiroNumber(giroNumber: String): List<FGiroEntity>

    @Query("SELECT u FROM FGiroEntity u WHERE u.giroNumber LIKE :giroNumber ")
    fun findAll(giroNumber: String): List<FGiroEntity>

    @Query("SELECT u FROM FGiroEntity u WHERE u.fdivisionBean = :fdivisionBean")
    fun findAllByDivision(fdivisionBean: Int): List<FGiroEntity>
}