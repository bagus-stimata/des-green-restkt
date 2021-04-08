package com.erp.distribution.desgreenrestkt.domain.repository

import com.erp.distribution.desgreenrestkt.data.source.entity.FtSaleshEntity
import com.erp.distribution.desgreenrestkt.domain.model.enum.EnumStatusPengiriman
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.*

interface FtSaleshRepo {
    fun findByRefno(refno: Long): FtSaleshEntity
    fun findAll(): List<FtSaleshEntity>
    fun findByOrderno(orderno: String): List<FtSaleshEntity>
    fun findByInvoiceno(invoiceno: String): List<FtSaleshEntity>
    fun findAll(orderno: String, invoiceno: String): List<FtSaleshEntity>
    fun findByDivision(fdivisionBean: Int): List<FtSaleshEntity>
    fun findByDivision(fdivisionBean: Int, orderDateFrom: Date, orderDateTo: Date, invoiceDateFrom: Date, invoiceDateTo: Date): List<FtSaleshEntity>
    fun findByDivisionAndOrderDate(fdivisionBean: Int, orderDateFrom: Date, orderDateTo: Date): List<FtSaleshEntity>
    fun findByDivisionAndInvoiceDate(fdivisionBean: Int, invoiceDateFrom: Date, invoiceDateTo: Date): List<FtSaleshEntity>

    fun findBySourceIdAndCreated(sourceId: Long, created: Date): FtSaleshEntity
    fun findBySourceId(sourceId: Long): List<FtSaleshEntity>
    fun findBySourceIdAndDivisionAndSalesmanAndCustomerAndWarehouse(sourceId: Long, fdivisionBean: Int, fsalesmanBean: Int, fcustomerBean: Int, fwarehouseBean: Int): FtSaleshEntity


    fun save(ftSaleshEntity: FtSaleshEntity): FtSaleshEntity
    fun saveAll(listFtSalesh: List<FtSaleshEntity>): List<FtSaleshEntity>
    fun delete(ftSaleshEntity: FtSaleshEntity)
    fun deleteInBatch(listFtSalesh: List<FtSaleshEntity>)

    fun findAllTotalSales(dateFrom :Date, dateTo :Date, listFsalesmanBean: List<Int>, listStatusPengiriman: List<EnumStatusPengiriman>): Double


}