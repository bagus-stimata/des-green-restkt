package com.erp.distribution.desgreenrestkt.data.source.local.dao

import com.erp.distribution.desgreenrestkt.data.source.entity.FtPurchasedExpensesEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FtPurchasedExpensesJPARepository : JpaRepository<FtPurchasedExpensesEntity, Long> {
//    override fun findById(id: Long): FtPurchasedExpensesEntity
    fun findByNoUrut(noUrut: String): List<FtPurchasedExpensesEntity>

    @Query("SELECT u FROM FtPurchasedExpensesEntity u WHERE u.noUrut LIKE :noUrut")
    fun findAll(noUrut: String): List<FtPurchasedExpensesEntity>

    @Query("SELECT u FROM FtPurchasedExpensesEntity u WHERE u.ftPurchasehBean = :ftPurchasehBean")
    fun findAllByParentId(ftPurchasehBean: Long): List<FtPurchasedExpensesEntity>
}