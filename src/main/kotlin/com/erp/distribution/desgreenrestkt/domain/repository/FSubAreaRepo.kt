package com.erp.distribution.desgreenrestkt.domain.repository

import com.erp.distribution.desgreenrestkt.data.source.entity.FSubAreaEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface FSubAreaRepo {
    fun findById(id: Int): FSubAreaEntity
    fun findAll(): List<FSubAreaEntity>
    fun findByKode1(kode1: String): List<FSubAreaEntity>
    fun findAll(kode1: String, description: String): List<FSubAreaEntity>
    fun findAllByDivision(fdivisionBean: Int): List<FSubAreaEntity>
    fun findAllByDivisionAndShareToCompany(fdivisionBean: Int, fcompanyBean: Int): List<FSubAreaEntity>

    fun save(fSubAreaEntity: FSubAreaEntity): FSubAreaEntity
    fun saveAll(listFSubArea: List<FSubAreaEntity>): List<FSubAreaEntity>
    fun delete(fSubAreaEntity: FSubAreaEntity)
    fun deleteInBatch(listFSubArea: List<FSubAreaEntity>)

}