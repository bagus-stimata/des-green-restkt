package com.erp.distribution.desgreenrestkt.jpa_repository

import com.erp.distribution.desgreenrestkt.data.source.entity.FtPurchasedExpenses
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FtPurchasedExpensesJPARepository : JpaRepository<FtPurchasedExpenses, Long> {
//    override fun findById(id: Long): FtPurchasedExpenses
    fun findByNoUrut(noUrut: String): List<FtPurchasedExpenses>

    @Query("SELECT u FROM FtPurchasedExpenses u WHERE u.noUrut LIKE :noUrut")
    fun findAll(noUrut: String): List<FtPurchasedExpenses>

    @Query("SELECT u FROM FtPurchasedExpenses u WHERE u.ftPurchasehBean = :ftPurchasehBean")
    fun findAllByParentId(ftPurchasehBean: Long): List<FtPurchasedExpenses>
}