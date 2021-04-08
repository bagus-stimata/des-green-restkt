package com.erp.distribution.desgreenrestkt.data.source.local.dao

import com.erp.distribution.desgreenrestkt.data.source.entity.FtSaleshEntity
import com.erp.distribution.desgreenrestkt.domain.model.enum.EnumStatusPengiriman
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Transactional
interface FtSaleshJPARepository : JpaRepository<FtSaleshEntity, Long> {
    fun findByRefno(refno: Long): FtSaleshEntity
    fun findByOrderno(orderno: String): List<FtSaleshEntity>
    fun findByInvoiceno(invoiceno: String): List<FtSaleshEntity>

    @Query("SELECT u FROM FtSaleshEntity u WHERE u.orderno LIKE :orderno and u.invoiceno LIKE :invoiceno")
    fun findAll(orderno: String, invoiceno: String): List<FtSaleshEntity>

    @Query("SELECT u FROM FtSaleshEntity u WHERE u.fdivisionBean = :fdivisionBean")
    fun findAllByDivision(fdivisionBean: Int): List<FtSaleshEntity>

    @Query("SELECT u FROM FtSaleshEntity u WHERE u.fdivisionBean = :fdivisionBean " +
            " AND u.orderDate BETWEEN :orderDateFrom AND :orderDateTo " +
            " AND u.invoiceDate BETWEEN :invoiceDateFrom AND :invoiceDateTo "
    )
    fun findAllByDivision(fdivisionBean: Int, orderDateFrom: Date, orderDateTo: Date, invoiceDateFrom: Date, invoiceDateTo: Date): List<FtSaleshEntity>

    @Query("SELECT u FROM FtSaleshEntity u WHERE u.fdivisionBean = :fdivisionBean " +
            " AND u.orderDate BETWEEN :orderDateFrom AND :orderDateTo "
    )
    fun findAllByDivisionAndOrderDate(fdivisionBean: Int, orderDateFrom: Date, orderDateTo: Date): List<FtSaleshEntity>

    @Query("SELECT u FROM FtSaleshEntity u WHERE u.fdivisionBean = :fdivisionBean " +
            " AND u.invoiceDate BETWEEN :invoiceDateFrom AND :invoiceDateTo "
    )
    fun findAllByDivisionAndInvoiceDate(fdivisionBean: Int, invoiceDateFrom: Date, invoiceDateTo: Date): List<FtSaleshEntity>

    @Query("SELECT u FROM FtSaleshEntity u WHERE u.sourceId = :sourceId and u.created = :created ")
    fun findBySourceIdAndCreated(sourceId: Long, created: Date): Optional<FtSaleshEntity>

    @Query("SELECT u FROM FtSaleshEntity u WHERE u.sourceId = :sourceId")
    fun findBySourceId(sourceId: Long): List<FtSaleshEntity>

    @Query("SELECT u FROM FtSaleshEntity u WHERE" +
            " u.sourceId = :sourceId " +
            " AND u.fdivisionBean = :fdivisionBean" +
            " AND u.fsalesmanBean = :fsalesmanBean" +
            " AND u.fcustomerBean = :fcustomerBean" +
            " AND u.fwarehouseBean = :fwarehouseBean" +
            " ")
    fun findBySourceIdAndDivisionAndSalesmanAndCustomerAndWarehouse(sourceId: Long, fdivisionBean: Int, fsalesmanBean: Int, fcustomerBean: Int, fwarehouseBean: Int): Optional<FtSaleshEntity>

    //    @Query("SELECT u FROM FtSaleshEntity u WHERE u.sourceId = :sourceID")
    //    List<FtSaleshEntity> findAllBySourceId(@Param("sourceId") long sourceID);
    @Query(value = "SELECT * FROM FtSaleshEntity  WHERE SOURCE_ID = :sourceId ", nativeQuery = true)
    fun findAllBySourceIdNative(sourceId: Long): Collection<FtSaleshEntity>


    fun findAllTotalSales(dateFrom :Date, dateTo :Date, listFsalesmanBean: List<Int>, listStatusPengiriman: List<EnumStatusPengiriman>): Double{
        return findAllTotalSalesX(listFsalesmanBean)
    }
    /**
     * QUERY
     */
    @Query("SELECT SUM(u.amountAfterDiscPlusRpAfterPpn_FG) FROM FtSaleshEntity u " +
            " WHERE" +
            " u.invoiceno != '' " +
            " AND u.invoiceno IS NOT NULL " +
            " AND u.fsalesmanBean IN :listFsalesmanBean " +
//            " AND u.invoiceDate BETWEEN  :dateFrom AND :dateTo " +
//            " AND u.statusPengiriman IN :listStatusPengiriman " +
            "")
    fun findAllTotalSalesX(listFsalesmanBean: List<Int>): Double

}