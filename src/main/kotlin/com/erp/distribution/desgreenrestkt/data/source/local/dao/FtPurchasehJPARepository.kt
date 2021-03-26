package com.erp.distribution.desgreenrestkt.data.source.local.dao

import com.erp.distribution.desgreenrestkt.data.source.entity.FtPurchasehEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FtPurchasehJPARepository : JpaRepository<FtPurchasehEntity, Long> {
    fun findByRefno(refno: Long): FtPurchasehEntity
    fun findByNopo(nopo: String): List<FtPurchasehEntity>
    fun findByGoodReceiptNo(goodReceiptNo: String): List<FtPurchasehEntity>
    fun findByInvoiceno(invoiceno: String): List<FtPurchasehEntity>

    @Query("SELECT u FROM FtPurchasehEntity u WHERE u.nopo LIKE :nopo and u.goodReceiptNo LIKE :goodReceiptNo and u.invoiceno LIKE :invoiceno")
    fun findAll(nopo: String, goodReceiptNo: String, invoiceno: String): List<FtPurchasehEntity>

    @Query("SELECT u FROM FtPurchasehEntity u WHERE u.fdivisionBean = :fdivisionBean")
    fun findAllByDivision(fdivisionBean: Int): List<FtPurchasehEntity>
}