package com.erp.distribution.desgreenrestkt.data.source.local.dao

import com.erp.distribution.desgreenrestkt.data.source.entity.FtOpnamehEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FtOpnamehJPARepository : JpaRepository<FtOpnamehEntity, Long> {
    fun findByRefno(refno: Long): FtOpnamehEntity
    fun findByNoRek(noRek: String): List<FtOpnamehEntity>

    @Query("SELECT u FROM FtOpnamehEntity u WHERE u.noRek LIKE :noRek ")
    fun findAll(noRek: String): List<FtOpnamehEntity>

    @Query("SELECT u FROM FtOpnamehEntity u WHERE u.fdivisionBean = :fdivisionBean")
    fun findAllByDivision(fdivisionBean: Int): List<FtOpnamehEntity>
}