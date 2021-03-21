package com.erp.distribution.desgreenrestkt.jpa_repository

import com.erp.distribution.desgreenrestkt.data.source.entity.FtPriceh
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FtPricehJPARepository : JpaRepository<FtPriceh, Int> {
    fun findByRefno(refno: Int): FtPriceh
    fun findByNoRek(noRek: String): List<FtPriceh>

    @Query("SELECT u FROM FtPriceh u WHERE u.noRek LIKE :noRek")
    fun findAll(noRek: String): List<FtPriceh>

    @Query("SELECT u FROM FtPriceh u WHERE u.fdivisionBean = :fdivisionBean")
    fun findAllByDivision(fdivisionBean: Int): List<FtPriceh>
}