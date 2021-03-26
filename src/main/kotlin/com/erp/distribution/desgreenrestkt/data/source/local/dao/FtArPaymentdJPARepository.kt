package com.erp.distribution.desgreenrestkt.data.source.local.dao

import com.erp.distribution.desgreenrestkt.data.source.entity.FtArPaymentdEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FtArPaymentdJPARepository : JpaRepository<FtArPaymentdEntity, Long> {
//    override fun findById(id: Long): FtArPaymentdEntity

    @Query("SELECT u FROM FtArPaymentdEntity u WHERE u.ftArPaymenthBean = :ftArPaymenthBean")
    fun findAllByParentId(ftArPaymenthBean: Long): List<FtArPaymentdEntity>

    @Query("SELECT u FROM FtArPaymentdEntity u WHERE u.ftArPaymenthBean IN :listFtArPaymenthBean")
    fun findAllByListParentId(listFtArPaymenthBean: List<Long>): List<FtArPaymentdEntity>

    @Query("SELECT u FROM FtArPaymentdEntity u WHERE u.ftArPaymenthBean = :ftArPaymenthBean and u.ftSaleshBean = :ftSaleshBean ")
    fun findAllByParentId(ftArPaymenthBean: Long, ftSaleshBean: Long): List<FtArPaymentdEntity>
}