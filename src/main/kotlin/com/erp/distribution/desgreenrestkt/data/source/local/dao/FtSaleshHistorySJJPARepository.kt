package com.erp.distribution.desgreenrestkt.data.source.local.dao

import com.erp.distribution.desgreenrestkt.data.source.entity.FtSaleshHistorySJEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FtSaleshHistorySJJPARepository : JpaRepository<FtSaleshHistorySJEntity, Long> {
//    override fun findById(id: Long): FtSaleshHistorySJEntity
    fun findBySjNumber(sjNumber: String): List<FtSaleshHistorySJEntity>

    @Query("SELECT u FROM FtSaleshHistorySJEntity u WHERE u.sjNumber LIKE :sjNumber ")
    fun findAll(sjNumber: String): List<FtSaleshHistorySJEntity>

    @Query("SELECT u FROM FtSaleshHistorySJEntity u WHERE u.ftSaleshBean = :ftSaleshBean")
    fun findAllByParentId(ftSaleshBean: Long): List<FtSaleshHistorySJEntity>

    @Query("SELECT u FROM FtSaleshHistorySJEntity u WHERE u.ftSaleshBean IN :ftSaleshBean")
    fun findAllByListParentId(ftSaleshBean: List<Long>): List<FtSaleshHistorySJEntity>
}