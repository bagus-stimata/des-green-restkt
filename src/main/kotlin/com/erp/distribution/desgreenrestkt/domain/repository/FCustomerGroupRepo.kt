package com.erp.distribution.desgreenrestkt.domain.repository

import com.erp.distribution.desgreenrestkt.data.source.entity.FCustomerGroupEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface FCustomerGroupRepo {
    fun findById(id: Int): FCustomerGroupEntity
    fun findAll(): List<FCustomerGroupEntity>
    fun findByKode1(kode1: String): List<FCustomerGroupEntity>
    fun findAll(kode1: String, description: String): List<FCustomerGroupEntity>
    fun findByDivision(fdivisionBean: Int): List<FCustomerGroupEntity>
    fun findByDivisionAndShareToCompany(fdivisionBean: Int, fcompanyBean: Int): List<FCustomerGroupEntity>

    fun save(fCustomerGroupEntity: FCustomerGroupEntity): FCustomerGroupEntity
    fun saveAll(listFCustomerGroup: List<FCustomerGroupEntity>): List<FCustomerGroupEntity>
    fun delete(fCustomerGroupEntity: FCustomerGroupEntity)
    fun deleteInBatch(listFCustomerGroup: List<FCustomerGroupEntity>)

}