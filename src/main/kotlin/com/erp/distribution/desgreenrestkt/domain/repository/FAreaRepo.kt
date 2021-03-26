package com.erp.distribution.desgreenrestkt.domain.repository

import com.erp.distribution.desgreenrestkt.data.source.entity.FAreaEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface FAreaRepo {
    fun findById(id: Int): FAreaEntity
    fun findAll(): List<FAreaEntity>
    fun findByKode1(kode1: String): List<FAreaEntity>
    fun findAll(kode1: String, description: String): List<FAreaEntity>
    fun findAllByDivision(fdivisionBean: Int): List<FAreaEntity>
    fun findAllByDivisionAndShareToCompany(fdivisionBean: Int, fcompanyBean: Int): List<FAreaEntity>

    fun save(fAreaEntity: FAreaEntity): FAreaEntity?

}