package com.erp.distribution.desgreenrestkt.domain.repository

import com.erp.distribution.desgreenrestkt.data.source.entity.FStockEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.*

interface FStockRepo {
    fun findById(id: Int): FStockEntity
    fun findAll(): List<FStockEntity>
    fun findByFMaterial(fmaterialBean: Int): List<FStockEntity>
    fun findByFMaterial(fmaterialBean: Int, stockDateFrom: Date, stockDateTo: Date): List<FStockEntity>
    fun findByFWarehouse(fwarehouseBean: Int, stockDateFrom: Date, stockDateTo: Date): List<FStockEntity>
    fun findAll(fwarehouseBean: Int, fmaterialBean: Int, stockDateFrom: Date, stockDateTo: Date): List<FStockEntity>

    fun save(fStockEntity: FStockEntity): FStockEntity
    fun saveAll(listFStock: List<FStockEntity>): List<FStockEntity>
    fun delete(fStockEntity: FStockEntity)
    fun deleteInBatch(listFStock: List<FStockEntity>)

}