package com.erp.distribution.desgreenrestkt.domain.repository

import com.erp.distribution.desgreenrestkt.data.source.entity.FtSaleshEntity
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

    fun findBySourceIdAndCreated(sourceId: Long, created: Date): Optional<FtSaleshEntity>
    fun findBySourceId(sourceId: Long): Optional<FtSaleshEntity>


    fun save(ftSaleshEntity: FtSaleshEntity): FtSaleshEntity
    fun saveAll(listFtSalesh: List<FtSaleshEntity>): List<FtSaleshEntity>
    fun delete(ftSaleshEntity: FtSaleshEntity)
    fun deleteInBatch(listFtSalesh: List<FtSaleshEntity>)

}