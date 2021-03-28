package com.erp.distribution.desgreenrestkt.domain.repository

import com.erp.distribution.desgreenrestkt.data.source.entity.FCustomerEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface FCustomerRepo {
    fun findById(id: Int): FCustomerEntity
    fun findAll(): List<FCustomerEntity>
    fun findByCustno(custno: String): List<FCustomerEntity>
    fun findAll(custno: String, custname: String): List<FCustomerEntity>
    fun findByDivision(fdivisionBean: Int): List<FCustomerEntity>
    fun findByDivisionAndShareToCompany(fdivisionBean: Int, fcompanyBean: Int): List<FCustomerEntity>

    fun save(fCustomerEntity: FCustomerEntity): FCustomerEntity
    fun saveAll(listFCustomer: List<FCustomerEntity>): List<FCustomerEntity>
    fun delete(fCustomerEntity: FCustomerEntity)
    fun deleteInBatch(listFCustomer: List<FCustomerEntity>)

}