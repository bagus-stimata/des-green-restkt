package com.erp.distribution.desgreenrestkt.data.source.local.dao

import com.erp.distribution.desgreenrestkt.data.source.entity.FtApPaymentdEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FtApPaymentdJPARepository : JpaRepository<FtApPaymentdEntity, Long> {
//    override fun findById(id: Long): FtApPaymentdEntity

    @Query("SELECT u FROM FtApPaymentdEntity u WHERE u.ftApPaymenthBean = :ftApPaymenthBean")
    fun findAllByParentId(ftApPaymenthBean: Long): List<FtApPaymentdEntity>

    @Query("SELECT u FROM FtApPaymentdEntity u WHERE u.ftApPaymenthBean IN :listFtApPaymenthBean")
    fun findAllByListParentId(listFtApPaymenthBean: List<Long>): List<FtApPaymentdEntity>

    @Query("SELECT u FROM FtApPaymentdEntity u WHERE u.ftApPaymenthBean = :ftApPaymenthBean and u.ftPurchasehBean = :ftPurchasehBean ")
    fun findAllByParentId(ftApPaymenthBean: Long, ftPurchasehBean: Long): List<FtApPaymentdEntity>
}