package com.erp.distribution.desgreenrestkt.jpa_repository

import com.erp.distribution.desgreenrestkt.model.FtSaleshHistorySJ
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FtSaleshHistorySJJPARepository : JpaRepository<FtSaleshHistorySJ, Long> {
//    override fun findById(id: Long): FtSaleshHistorySJ
    fun findBySjNumber(sjNumber: String): List<FtSaleshHistorySJ>

    @Query("SELECT u FROM FtSaleshHistorySJ u WHERE u.sjNumber LIKE :sjNumber ")
    fun findAll(sjNumber: String): List<FtSaleshHistorySJ>

    @Query("SELECT u FROM FtSaleshHistorySJ u WHERE u.ftSaleshBean = :ftSaleshBean")
    fun findAllByParentId(ftSaleshBean: Long): List<FtSaleshHistorySJ>

    @Query("SELECT u FROM FtSaleshHistorySJ u WHERE u.ftSaleshBean IN :ftSaleshBean")
    fun findAllByListParentId(ftSaleshBean: List<Long>): List<FtSaleshHistorySJ>
}