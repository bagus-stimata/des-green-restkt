package com.erp.distribution.desgreenrestkt.jpa_repository

import com.erp.distribution.desgreenrestkt.data.source.entity.FtPurchasedItems
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FtPurchasedItemsJPARepository : JpaRepository<FtPurchasedItems, Long> {
//    override fun findById(id: Long): FtPurchasedItems
    fun findByNoUrut(noUrut: String): List<FtPurchasedItems>

    @Query("SELECT u FROM FtPurchasedItems u WHERE u.noUrut LIKE :noUrut")
    fun findAll(noUrut: String): List<FtPurchasedItems>

    @Query("SELECT u FROM FtPurchasedItems u WHERE u.ftPurchasehBean = :ftPurchasehBean")
    fun findAllByParentId(ftPurchasehBean: Long): List<FtPurchasedItems>

    @Query("SELECT u FROM FtPurchasedItems u WHERE u.ftPurchasehBean IN :listFtPurchasehBean")
    fun findAllByListParentId(listFtPurchasehBean: List<Long>): List<FtPurchasedItems>
}