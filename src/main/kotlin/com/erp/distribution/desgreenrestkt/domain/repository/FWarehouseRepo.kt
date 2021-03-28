package com.erp.distribution.desgreenrestkt.domain.repository

import com.erp.distribution.desgreenrestkt.data.source.entity.FWarehouseEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface FWarehouseRepo {
    fun findById(id: Int): FWarehouseEntity
    fun findAll(): List<FWarehouseEntity>
    fun findByKode1(kode1: String): List<FWarehouseEntity>
    fun findAll(kode1: String, description: String): List<FWarehouseEntity>
    fun findByDivision(fdivisionBean: Int): List<FWarehouseEntity>
    fun findByCompany(fcompanyBean: Int): List<FWarehouseEntity>
    fun findByDivisionAndShareToCompany(fdivisionBean: Int, fcompanyBean: Int): List<FWarehouseEntity>

    fun save(fWarehouseEntity: FWarehouseEntity): FWarehouseEntity
    fun saveAll(listFWarehouse: List<FWarehouseEntity>): List<FWarehouseEntity>
    fun delete(fWarehouseEntity: FWarehouseEntity)
    fun deleteInBatch(listFWarehouse: List<FWarehouseEntity>)

}