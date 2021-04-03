package com.erp.distribution.desgreenrestkt.domain.repository

import com.erp.distribution.desgreenrestkt.data.source.entity.FSalesTargetToCustEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface FSalesTargetToCustRepo {
    fun findById(id: Long): FSalesTargetToCustEntity
    fun findAll(): List<FSalesTargetToCustEntity>
//    fun findByKode1(kode1: String): List<FSalesTargetToCustEntity>
//    fun findAll(kode1: String, description: String): List<FSalesTargetToCustEntity>
    fun findBySalesman(fsalesmanBean: Int): List<FSalesTargetToCustEntity>
    fun findByDivision(fdivisionBean: Int): List<FSalesTargetToCustEntity>
    fun findByDivisionAndShareToCompany(fdivisionBean: Int, fcompanyBean: Int): List<FSalesTargetToCustEntity>

//    fun save(fSalesTargetToCustEntity: FSalesTargetToCustEntity): FSalesTargetToCustEntity
//    fun saveAll(listFSalesTargetToCust: List<FSalesTargetToCustEntity>): List<FSalesTargetToCustEntity>
//    fun delete(fSalesTargetToCustEntity: FSalesTargetToCustEntity)
//    fun deleteInBatch(listFSalesTargetToCust: List<FSalesTargetToCustEntity>)

}