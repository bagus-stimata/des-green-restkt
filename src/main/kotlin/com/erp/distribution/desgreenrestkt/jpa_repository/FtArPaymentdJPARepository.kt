package com.erp.distribution.desgreenrestkt.jpa_repository

import com.erp.distribution.desgreenrestkt.data.source.entity.FtArPaymentd
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FtArPaymentdJPARepository : JpaRepository<FtArPaymentd, Long> {
//    override fun findById(id: Long): FtArPaymentd

    @Query("SELECT u FROM FtArPaymentd u WHERE u.ftArPaymenthBean = :ftArPaymenthBean")
    fun findAllByParentId(ftArPaymenthBean: Long): List<FtArPaymentd>

    @Query("SELECT u FROM FtArPaymentd u WHERE u.ftArPaymenthBean IN :listFtArPaymenthBean")
    fun findAllByListParentId(listFtArPaymenthBean: List<Long>): List<FtArPaymentd>

    @Query("SELECT u FROM FtArPaymentd u WHERE u.ftArPaymenthBean = :ftArPaymenthBean and u.ftSaleshBean = :ftSaleshBean ")
    fun findAllByParentId(ftArPaymenthBean: Long, ftSaleshBean: Long): List<FtArPaymentd>
}