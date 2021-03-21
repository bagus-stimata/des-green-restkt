package com.erp.distribution.desgreenrestkt.jpa_repository

import com.erp.distribution.desgreenrestkt.data.source.entity.FtSalesh
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Transactional
interface FtSaleshJPARepository : JpaRepository<FtSalesh, Long> {
    fun findByRefno(id: Long): FtSalesh
    fun findByOrderno(orderno: String): List<FtSalesh>
    fun findByInvoiceno(invoiceno: String): List<FtSalesh>

    @Query("SELECT u FROM FtSalesh u WHERE u.orderno LIKE :orderno and u.invoiceno LIKE :invoiceno")
    fun findAll(orderno: String, invoiceno: String): List<FtSalesh>

    @Query("SELECT u FROM FtSalesh u WHERE u.fdivisionBean = :fdivisionBean")
    fun findAllByDivision(fdivisionBean: Int): List<FtSalesh>

    @Query("SELECT u FROM FtSalesh u WHERE u.sourceId = :sourceId and u.created = :created ")
    fun findBySourceIdAndCreated(sourceId: Long, created: Date): Optional<FtSalesh>

    @Query("SELECT u FROM FtSalesh u WHERE u.sourceId = :sourceId")
    fun findBySourceId(sourceId: Long): Optional<FtSalesh>

    //    @Query("SELECT u FROM FtSalesh u WHERE u.sourceId = :sourceID")
    //    List<FtSalesh> findAllBySourceId(@Param("sourceId") long sourceID);
    @Query(value = "SELECT * FROM FtSalesh  WHERE SOURCE_ID = :sourceId ", nativeQuery = true)
    fun findAllBySourceIdNative(sourceId: Long): Collection<FtSalesh>
}