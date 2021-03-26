package com.erp.distribution.desgreenrestkt.data.source.local.dao

import com.erp.distribution.desgreenrestkt.data.source.entity.FtArPaymenthEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FtArPaymenthJPARepository : JpaRepository<FtArPaymenthEntity, Long> {
//    override fun findById(id: Long): FtArPaymenthEntity
    fun findByNoRek(noRek: String): List<FtArPaymenthEntity>

    @Query("SELECT u FROM FtArPaymenthEntity u WHERE u.noRek LIKE :noRek")
    fun findAll(noRek: String): List<FtArPaymenthEntity>

    @Query("SELECT u FROM FtArPaymenthEntity u WHERE u.fdivisionBean = :fdivisionBean")
    fun findAllByDivision(fdivisionBean: Int): List<FtArPaymenthEntity>
}