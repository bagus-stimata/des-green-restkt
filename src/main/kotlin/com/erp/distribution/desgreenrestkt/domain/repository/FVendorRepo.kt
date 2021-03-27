package com.erp.distribution.desgreenrestkt.domain.repository

import com.erp.distribution.desgreenrestkt.data.source.entity.FVendorEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface FVendorRepo {
    fun findById(id: Int): FVendorEntity
    fun findAll(): List<FVendorEntity>
    fun findByKode1(kode1: String): List<FVendorEntity>
    fun findAll(kode1: String, description: String): List<FVendorEntity>
    fun findAllByDivision(fdivisionBean: Int): List<FVendorEntity>
    fun findAllByDivisionAndShareToCompany(fdivisionBean: Int, fcompanyBean: Int): List<FVendorEntity>

    fun save(fVendorEntity: FVendorEntity): FVendorEntity
    fun saveAll(listFVendor: List<FVendorEntity>): List<FVendorEntity>
    fun delete(fVendorEntity: FVendorEntity)
    fun deleteInBatch(listFVendor: List<FVendorEntity>)

}