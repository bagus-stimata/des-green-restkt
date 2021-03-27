package com.erp.distribution.desgreenrestkt.domain.repository

import com.erp.distribution.desgreenrestkt.data.source.entity.FStockEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface FStockRepo {
    fun findById(id: Int): FStockEntity
    fun findAll(): List<FStockEntity>
    fun findByKode1(kode1: String): List<FStockEntity>
    fun findAll(kode1: String, description: String): List<FStockEntity>
    fun findAllByDivision(fdivisionBean: Int): List<FStockEntity>
    fun findAllByDivisionAndShareToCompany(fdivisionBean: Int, fcompanyBean: Int): List<FStockEntity>

    fun save(fStockEntity: FStockEntity): FStockEntity
    fun saveAll(listFStock: List<FStockEntity>): List<FStockEntity>
    fun delete(fStockEntity: FStockEntity)
    fun deleteInBatch(listFStock: List<FStockEntity>)

}