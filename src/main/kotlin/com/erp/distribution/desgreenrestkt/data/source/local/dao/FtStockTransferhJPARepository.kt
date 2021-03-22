package com.erp.distribution.desgreenrestkt.data.source.local.dao

import com.erp.distribution.desgreenrestkt.data.source.entity.FtStockTransferh
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FtStockTransferhJPARepository : JpaRepository<FtStockTransferh, Long> {
    fun findByRefno(refno: Long): FtStockTransferh
    fun findByNoRek(noRek: String): List<FtStockTransferh>

    @Query("SELECT u FROM FtStockTransferh u WHERE u.noRek LIKE :noRek")
    fun findAll(noRek: String): List<FtStockTransferh>

    @Query("SELECT u FROM FtStockTransferh u WHERE u.fdivisionBean = :fdivisionBean")
    fun findAllByDivision(fdivisionBean: Int): List<FtStockTransferh>
}