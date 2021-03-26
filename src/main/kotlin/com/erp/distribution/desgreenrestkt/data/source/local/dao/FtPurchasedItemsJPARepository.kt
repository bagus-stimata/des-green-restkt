package com.erp.distribution.desgreenrestkt.data.source.local.dao

import com.erp.distribution.desgreenrestkt.data.source.entity.FtPurchasedItemsEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FtPurchasedItemsJPARepository : JpaRepository<FtPurchasedItemsEntity, Long> {
//    override fun findById(id: Long): FtPurchasedItemsEntity
    fun findByNoUrut(noUrut: String): List<FtPurchasedItemsEntity>

    @Query("SELECT u FROM FtPurchasedItemsEntity u WHERE u.noUrut LIKE :noUrut")
    fun findAll(noUrut: String): List<FtPurchasedItemsEntity>

    @Query("SELECT u FROM FtPurchasedItemsEntity u WHERE u.ftPurchasehBean = :ftPurchasehBean")
    fun findAllByParentId(ftPurchasehBean: Long): List<FtPurchasedItemsEntity>

    @Query("SELECT u FROM FtPurchasedItemsEntity u WHERE u.ftPurchasehBean IN :listFtPurchasehBean")
    fun findAllByListParentId(listFtPurchasehBean: List<Long>): List<FtPurchasedItemsEntity>
}