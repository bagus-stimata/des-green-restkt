package com.erp.distribution.desgreenrestkt.domain.repository

import com.erp.distribution.desgreenrestkt.data.source.entity.FSalesmanEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface FSalesmanRepo {
    fun findById(id: Int): FSalesmanEntity
    fun findAll(): List<FSalesmanEntity>
    fun findBySpcode(kode1: String): List<FSalesmanEntity>
    fun findAll(kode1: String, description: String): List<FSalesmanEntity>
    fun findByDivision(fdivisionBean: Int): List<FSalesmanEntity>
    fun findByCompany(fcompanyBean: Int): List<FSalesmanEntity>
    fun findByDivisionAndShareToCompany(fdivisionBean: Int, fcompanyBean: Int): List<FSalesmanEntity>

    fun save(fSalesmanEntity: FSalesmanEntity): FSalesmanEntity
    fun saveAll(listFSalesman: List<FSalesmanEntity>): List<FSalesmanEntity>
    fun delete(fSalesmanEntity: FSalesmanEntity)
    fun deleteInBatch(listFSalesman: List<FSalesmanEntity>)

}