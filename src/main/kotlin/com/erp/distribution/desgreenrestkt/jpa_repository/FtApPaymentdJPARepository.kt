package com.erp.distribution.desgreenrestkt.jpa_repository

import com.erp.distribution.desgreenrestkt.model.FtApPaymentd
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FtApPaymentdJPARepository : JpaRepository<FtApPaymentd, Long> {
//    override fun findById(id: Long): FtApPaymentd

    @Query("SELECT u FROM FtApPaymentd u WHERE u.ftApPaymenthBean = :ftApPaymenthBean")
    fun findAllByParentId(ftApPaymenthBean: Long): List<FtApPaymentd>

    @Query("SELECT u FROM FtApPaymentd u WHERE u.ftApPaymenthBean IN :listFtApPaymenthBean")
    fun findAllByListParentId(listFtApPaymenthBean: List<Long>): List<FtApPaymentd>

    @Query("SELECT u FROM FtApPaymentd u WHERE u.ftApPaymenthBean = :ftApPaymenthBean and u.ftPurchasehBean = :ftPurchasehBean ")
    fun findAllByParentId(ftApPaymenthBean: Long, ftPurchasehBean: Long): List<FtApPaymentd>
}