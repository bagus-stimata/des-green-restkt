package com.erp.distribution.desgreenrestkt.jpa_repository

import com.erp.distribution.desgreenrestkt.data.source.entity.FtPurchaseh
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FtPurchasehJPARepository : JpaRepository<FtPurchaseh, Long> {
    fun findByRefno(refno: Long): FtPurchaseh
    fun findByNopo(nopo: String): List<FtPurchaseh>
    fun findByGoodReceiptNo(goodReceiptNo: String): List<FtPurchaseh>
    fun findByInvoiceno(invoiceno: String): List<FtPurchaseh>

    @Query("SELECT u FROM FtPurchaseh u WHERE u.nopo LIKE :nopo and u.goodReceiptNo LIKE :goodReceiptNo and u.invoiceno LIKE :invoiceno")
    fun findAll(nopo: String, goodReceiptNo: String, invoiceno: String): List<FtPurchaseh>

    @Query("SELECT u FROM FtPurchaseh u WHERE u.fdivisionBean = :fdivisionBean")
    fun findAllByDivision(fdivisionBean: Int): List<FtPurchaseh>
}