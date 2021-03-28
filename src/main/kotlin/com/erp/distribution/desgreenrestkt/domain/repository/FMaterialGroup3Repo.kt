package com.erp.distribution.desgreenrestkt.domain.repository

import com.erp.distribution.desgreenrestkt.data.source.entity.FMaterialGroup3Entity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface FMaterialGroup3Repo {
    fun findById(id: Int): FMaterialGroup3Entity
    fun findAll(): List<FMaterialGroup3Entity>
    fun findByKode1(kode1: String): List<FMaterialGroup3Entity>
    fun findAll(kode1: String, description: String): List<FMaterialGroup3Entity>
    fun findByParent(fparentBean: Int): List<FMaterialGroup3Entity>
    fun findByListOfParent(listFMaterialGroup3: List<Int>): List<FMaterialGroup3Entity>

    fun save(fMaterialGroup3Entity: FMaterialGroup3Entity): FMaterialGroup3Entity
    fun saveAll(listFMaterialGroup3: List<FMaterialGroup3Entity>): List<FMaterialGroup3Entity>
    fun delete(fMaterialGroup3Entity: FMaterialGroup3Entity)
    fun deleteInBatch(listFMaterialGroup3: List<FMaterialGroup3Entity>)

}