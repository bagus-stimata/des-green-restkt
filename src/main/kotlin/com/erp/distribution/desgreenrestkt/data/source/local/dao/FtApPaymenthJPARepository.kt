package com.erp.distribution.desgreenrestkt.data.source.local.dao

import com.erp.distribution.desgreenrestkt.data.source.entity.FtApPaymenthEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FtApPaymenthJPARepository : JpaRepository<FtApPaymenthEntity, Long> {
//    override fun findById(id: Long): FtApPaymenthEntity
    fun findByNoRek(noRek: String): List<FtApPaymenthEntity>

    @Query("SELECT u FROM FtApPaymenthEntity u WHERE u.noRek LIKE :noRek")
    fun findAll(noRek: String): List<FtApPaymenthEntity>

    @Query("SELECT u FROM FtApPaymenthEntity u WHERE u.fdivisionBean = :fdivisionBean")
    fun findAllByDivision(fdivisionBean: Int): List<FtApPaymenthEntity>
}