package com.erp.distribution.desgreenrestkt.domain.repository

import com.erp.distribution.desgreenrestkt.data.source.entity.FTaxEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface FTaxRepo {
    fun findById(id: Int): FTaxEntity
    fun findAll(): List<FTaxEntity>
    fun findByKode1(kode1: String): List<FTaxEntity>
    fun findAll(kode1: String, description: String): List<FTaxEntity>
    fun findAllByDivision(fdivisionBean: Int): List<FTaxEntity>
    fun findAllByDivisionAndShareToCompany(fdivisionBean: Int, fcompanyBean: Int): List<FTaxEntity>

    fun save(fTaxEntity: FTaxEntity): FTaxEntity
    fun saveAll(listFTax: List<FTaxEntity>): List<FTaxEntity>
    fun delete(fTaxEntity: FTaxEntity)
    fun deleteInBatch(listFTax: List<FTaxEntity>)

}