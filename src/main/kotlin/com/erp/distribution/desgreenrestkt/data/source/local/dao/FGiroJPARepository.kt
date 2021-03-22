package com.erp.distribution.desgreenrestkt.data.source.local.dao

import com.erp.distribution.desgreenrestkt.data.source.entity.FGiro
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FGiroJPARepository : JpaRepository<FGiro, Long> {
//    override fun findById(id: Long): FGiro?
    fun findByGiroNumber(giroNumber: String?): List<FGiro>

    @Query("SELECT u FROM FGiro u WHERE u.giroNumber LIKE :giroNumber ")
    fun findAll(giroNumber: String?): List<FGiro>

    @Query("SELECT u FROM FGiro u WHERE u.fdivisionBean = :fdivisionBean")
    fun findAllByDivision(fdivisionBean: Int): List<FGiro>
}