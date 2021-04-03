package com.erp.distribution.desgreenrestkt.domain.repository

import com.erp.distribution.desgreenrestkt.data.source.entity.FSalesCallPlanhEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface FSalesCallPlanhRepo {
    fun findById(id: Long): FSalesCallPlanhEntity
    fun findAll(): List<FSalesCallPlanhEntity>
    fun findByKode1(kode1: String): List<FSalesCallPlanhEntity>
    fun findAll(kode1: String, description: String): List<FSalesCallPlanhEntity>
    fun findBySalesman(fsalesmanBean: Int): List<FSalesCallPlanhEntity>
    fun findByDivision(fdivisionBean: Int): List<FSalesCallPlanhEntity>
    fun findByDivisionAndShareToCompany(fdivisionBean: Int, fcompanyBean: Int): List<FSalesCallPlanhEntity>

//    fun save(fSalesCallPlanhEntity: FSalesCallPlanhEntity): FSalesCallPlanhEntity
//    fun saveAll(listFSalesCallPlanh: List<FSalesCallPlanhEntity>): List<FSalesCallPlanhEntity>
//    fun delete(fSalesCallPlanhEntity: FSalesCallPlanhEntity)
//    fun deleteInBatch(listFSalesCallPlanh: List<FSalesCallPlanhEntity>)

}