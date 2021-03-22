package com.erp.distribution.desgreenrestkt.data.source.local.dao

import com.erp.distribution.desgreenrestkt.data.source.entity.FtStockTransferdItems
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FtStockTransferdItemsJPARepository : JpaRepository<FtStockTransferdItems, Long> {
//    override fun findById(id: Long): FtStockTransferdItems
    fun findByNoUrut(noUrut: String): List<FtStockTransferdItems>

    @Query("SELECT u FROM FtStockTransferdItems u WHERE u.noUrut LIKE :noUrut")
    fun findAll(noUrut: String): List<FtStockTransferdItems>

    @Query("SELECT u FROM FtStockTransferdItems u WHERE u.ftStockTransferhBean = :ftStockTransferhBean")
    fun findAllByParentId(ftStockTransferhBean: Long): List<FtStockTransferdItems>

    @Query("SELECT u FROM FtStockTransferdItems u WHERE u.ftStockTransferhBean IN :listFtStockTransferhBean")
    fun findAllByListParentId(listFtStockTransferhBean: List<Long>): List<FtStockTransferdItems>
}