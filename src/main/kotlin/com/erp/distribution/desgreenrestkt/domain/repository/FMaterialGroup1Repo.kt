package com.erp.distribution.desgreenrestkt.domain.repository

import com.erp.distribution.desgreenrestkt.data.source.entity.FMaterialGroup1Entity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface FMaterialGroup1Repo {
    fun findById(id: Int): FMaterialGroup1Entity
    fun findAll(): List<FMaterialGroup1Entity>
    fun findByKode1(kode1: String): List<FMaterialGroup1Entity>
    fun findAll(kode1: String, description: String): List<FMaterialGroup1Entity>
    fun findByDivision(fdivisionBean: Int): List<FMaterialGroup1Entity>
    fun findByCompany(fcompanyBean: Int): List<FMaterialGroup1Entity>
    fun findByDivisionAndShareToCompany(fdivisionBean: Int, fcompanyBean: Int): List<FMaterialGroup1Entity>

    fun save(fMaterialGroup1Entity: FMaterialGroup1Entity): FMaterialGroup1Entity
    fun saveAll(listFMaterialGroup1: List<FMaterialGroup1Entity>): List<FMaterialGroup1Entity>
    fun delete(fMaterialGroup1Entity: FMaterialGroup1Entity)
    fun deleteInBatch(listFMaterialGroup1: List<FMaterialGroup1Entity>)

}