package com.erp.distribution.desgreenrestkt.data.source.local.dao

import com.erp.distribution.desgreenrestkt.data.source.entity.FtSaleshEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Transactional
interface FtSaleshJPARepository : JpaRepository<FtSaleshEntity, Long> {
    fun findByRefno(id: Long): FtSaleshEntity
    fun findByOrderno(orderno: String): List<FtSaleshEntity>
    fun findByInvoiceno(invoiceno: String): List<FtSaleshEntity>

    @Query("SELECT u FROM FtSaleshEntity u WHERE u.orderno LIKE :orderno and u.invoiceno LIKE :invoiceno")
    fun findAll(orderno: String, invoiceno: String): List<FtSaleshEntity>

    @Query("SELECT u FROM FtSaleshEntity u WHERE u.fdivisionBean = :fdivisionBean")
    fun findAllByDivision(fdivisionBean: Int): List<FtSaleshEntity>

    @Query("SELECT u FROM FtSaleshEntity u WHERE u.sourceId = :sourceId and u.created = :created ")
    fun findBySourceIdAndCreated(sourceId: Long, created: Date): Optional<FtSaleshEntity>

    @Query("SELECT u FROM FtSaleshEntity u WHERE u.sourceId = :sourceId")
    fun findBySourceId(sourceId: Long): Optional<FtSaleshEntity>

    //    @Query("SELECT u FROM FtSaleshEntity u WHERE u.sourceId = :sourceID")
    //    List<FtSaleshEntity> findAllBySourceId(@Param("sourceId") long sourceID);
    @Query(value = "SELECT * FROM FtSaleshEntity  WHERE SOURCE_ID = :sourceId ", nativeQuery = true)
    fun findAllBySourceIdNative(sourceId: Long): Collection<FtSaleshEntity>
}