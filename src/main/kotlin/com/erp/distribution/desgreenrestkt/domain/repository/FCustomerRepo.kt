package com.erp.distribution.desgreenrestkt.domain.repository

import com.erp.distribution.desgreenrestkt.data.source.entity.FCustomerEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface FCustomerRepo {
    fun findById(id: Int): FCustomerEntity
    fun findAll(): List<FCustomerEntity>
    fun findByKode1(kode1: String): List<FCustomerEntity>
    fun findAll(kode1: String, description: String): List<FCustomerEntity>
    fun findAllByDivision(fdivisionBean: Int): List<FCustomerEntity>
    fun findAllByDivisionAndShareToCompany(fdivisionBean: Int, fcompanyBean: Int): List<FCustomerEntity>

    fun save(fCustomerEntity: FCustomerEntity): FCustomerEntity
    fun saveAll(listFCustomer: List<FCustomerEntity>): List<FCustomerEntity>
    fun delete(fCustomerEntity: FCustomerEntity)
    fun deleteInBatch(listFCustomer: List<FCustomerEntity>)

}