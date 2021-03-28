package com.erp.distribution.desgreenrestkt.domain.repository

import com.erp.distribution.desgreenrestkt.data.source.entity.FMaterialSalesBrandEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface FMaterialSalesBrandRepo {
    fun findById(id: Int): FMaterialSalesBrandEntity
    fun findAll(): List<FMaterialSalesBrandEntity>
    fun findByKode1(kode1: String): List<FMaterialSalesBrandEntity>
    fun findAll(kode1: String, description: String): List<FMaterialSalesBrandEntity>
    fun findByDivision(fdivisionBean: Int): List<FMaterialSalesBrandEntity>
    fun findByDivisionAndShareToCompany(fdivisionBean: Int, fcompanyBean: Int): List<FMaterialSalesBrandEntity>

    fun save(fMaterialSalesBrandEntity: FMaterialSalesBrandEntity): FMaterialSalesBrandEntity
    fun saveAll(listFMaterialSalesBrand: List<FMaterialSalesBrandEntity>): List<FMaterialSalesBrandEntity>
    fun delete(fMaterialSalesBrandEntity: FMaterialSalesBrandEntity)
    fun deleteInBatch(listFMaterialSalesBrand: List<FMaterialSalesBrandEntity>)

}