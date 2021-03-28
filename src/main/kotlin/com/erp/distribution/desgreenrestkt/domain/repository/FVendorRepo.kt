package com.erp.distribution.desgreenrestkt.domain.repository

import com.erp.distribution.desgreenrestkt.data.source.entity.FVendorEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface FVendorRepo {
    fun findById(id: Int): FVendorEntity
    fun findAll(): List<FVendorEntity>
    fun findByVcode(vcode: String): List<FVendorEntity>
    fun findAll(kode1: String, description: String): List<FVendorEntity>
    fun findByDivision(fdivisionBean: Int): List<FVendorEntity>
    fun findByCompany(fcompanyBean: Int): List<FVendorEntity>
    fun findByDivisionAndShareToCompany(fdivisionBean: Int, fcompanyBean: Int): List<FVendorEntity>

    fun save(fVendorEntity: FVendorEntity): FVendorEntity
    fun saveAll(listFVendor: List<FVendorEntity>): List<FVendorEntity>
    fun delete(fVendorEntity: FVendorEntity)
    fun deleteInBatch(listFVendor: List<FVendorEntity>)

}