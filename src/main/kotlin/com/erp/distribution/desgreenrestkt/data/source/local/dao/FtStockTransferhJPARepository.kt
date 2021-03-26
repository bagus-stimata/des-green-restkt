package com.erp.distribution.desgreenrestkt.data.source.local.dao

import com.erp.distribution.desgreenrestkt.data.source.entity.FtStockTransferhEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FtStockTransferhJPARepository : JpaRepository<FtStockTransferhEntity, Long> {
    fun findByRefno(refno: Long): FtStockTransferhEntity
    fun findByNoRek(noRek: String): List<FtStockTransferhEntity>

    @Query("SELECT u FROM FtStockTransferhEntity u WHERE u.noRek LIKE :noRek")
    fun findAll(noRek: String): List<FtStockTransferhEntity>

    @Query("SELECT u FROM FtStockTransferhEntity u WHERE u.fdivisionBean = :fdivisionBean")
    fun findAllByDivision(fdivisionBean: Int): List<FtStockTransferhEntity>
}