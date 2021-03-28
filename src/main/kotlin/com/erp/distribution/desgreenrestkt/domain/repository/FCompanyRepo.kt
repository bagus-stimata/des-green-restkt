package com.erp.distribution.desgreenrestkt.domain.repository

import com.erp.distribution.desgreenrestkt.data.source.entity.FCompanyEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface FCompanyRepo {
    fun findById(id: Int): FCompanyEntity
    fun findAll(): List<FCompanyEntity>
    fun findByKode1(kode1: String): List<FCompanyEntity>
    fun findAll(kode1: String, description: String): List<FCompanyEntity>

    fun save(fCompanyEntity: FCompanyEntity): FCompanyEntity
    fun saveAll(listFCompany: List<FCompanyEntity>): List<FCompanyEntity>
    fun delete(fCompanyEntity: FCompanyEntity)
    fun deleteInBatch(listFCompany: List<FCompanyEntity>)

}