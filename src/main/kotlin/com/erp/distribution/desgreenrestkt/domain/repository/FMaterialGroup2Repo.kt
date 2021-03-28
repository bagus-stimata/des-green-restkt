package com.erp.distribution.desgreenrestkt.domain.repository

import com.erp.distribution.desgreenrestkt.data.source.entity.FMaterialGroup2Entity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface FMaterialGroup2Repo {
    fun findById(id: Int): FMaterialGroup2Entity
    fun findAll(): List<FMaterialGroup2Entity>
    fun findByKode1(kode1: String): List<FMaterialGroup2Entity>
    fun findAll(kode1: String, description: String): List<FMaterialGroup2Entity>
    fun findByParent(fdivisionBean: Int): List<FMaterialGroup2Entity>

    fun save(fMaterialGroup2Entity: FMaterialGroup2Entity): FMaterialGroup2Entity
    fun saveAll(listFMaterialGroup2: List<FMaterialGroup2Entity>): List<FMaterialGroup2Entity>
    fun delete(fMaterialGroup2Entity: FMaterialGroup2Entity)
    fun deleteInBatch(listFMaterialGroup2: List<FMaterialGroup2Entity>)

}