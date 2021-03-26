package com.erp.distribution.desgreenrestkt.data.source.local.dao

import com.erp.distribution.desgreenrestkt.data.source.entity.FtStockTransferdItemsEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FtStockTransferdItemsJPARepository : JpaRepository<FtStockTransferdItemsEntity, Long> {
//    override fun findById(id: Long): FtStockTransferdItemsEntity
    fun findByNoUrut(noUrut: String): List<FtStockTransferdItemsEntity>

    @Query("SELECT u FROM FtStockTransferdItemsEntity u WHERE u.noUrut LIKE :noUrut")
    fun findAll(noUrut: String): List<FtStockTransferdItemsEntity>

    @Query("SELECT u FROM FtStockTransferdItemsEntity u WHERE u.ftStockTransferhBean = :ftStockTransferhBean")
    fun findAllByParentId(ftStockTransferhBean: Long): List<FtStockTransferdItemsEntity>

    @Query("SELECT u FROM FtStockTransferdItemsEntity u WHERE u.ftStockTransferhBean IN :listFtStockTransferhBean")
    fun findAllByListParentId(listFtStockTransferhBean: List<Long>): List<FtStockTransferdItemsEntity>
}