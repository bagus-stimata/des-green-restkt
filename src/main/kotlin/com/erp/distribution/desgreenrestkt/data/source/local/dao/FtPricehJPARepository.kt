package com.erp.distribution.desgreenrestkt.data.source.local.dao

import com.erp.distribution.desgreenrestkt.data.source.entity.FtPricehEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FtPricehJPARepository : JpaRepository<FtPricehEntity, Int> {
    fun findByRefno(refno: Int): FtPricehEntity
    fun findByNoRek(noRek: String): List<FtPricehEntity>

    @Query("SELECT u FROM FtPricehEntity u WHERE u.noRek LIKE :noRek")
    fun findAll(noRek: String): List<FtPricehEntity>

    @Query("SELECT u FROM FtPricehEntity u WHERE u.fdivisionBean = :fdivisionBean")
    fun findAllByDivision(fdivisionBean: Int): List<FtPricehEntity>
}