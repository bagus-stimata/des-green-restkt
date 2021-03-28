package com.erp.distribution.desgreenrestkt.domain.repository

import com.erp.distribution.desgreenrestkt.data.source.entity.FDivisionEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface FDivisionRepo {
    fun findById(id: Int): FDivisionEntity
    fun findAll(): List<FDivisionEntity>
    fun findByKode1(kode1: String): List<FDivisionEntity>
    fun findAll(kode1: String, description: String): List<FDivisionEntity>
    fun findByCompany(fcompanyBean: Int): List<FDivisionEntity>

    fun save(fDivisionEntity: FDivisionEntity): FDivisionEntity
    fun saveAll(listFDivision: List<FDivisionEntity>): List<FDivisionEntity>
    fun delete(fDivisionEntity: FDivisionEntity)
    fun deleteInBatch(listFDivision: List<FDivisionEntity>)

}