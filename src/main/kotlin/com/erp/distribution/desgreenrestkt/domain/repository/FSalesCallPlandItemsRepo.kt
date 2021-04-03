package com.erp.distribution.desgreenrestkt.domain.repository

import com.erp.distribution.desgreenrestkt.data.source.entity.FSalesCallPlandItemsEntity
import com.erp.distribution.desgreenrestkt.domain.model.FArea
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface FSalesCallPlandItemsRepo {
    fun findById(id: Long): FSalesCallPlandItemsEntity
    fun findAll(): List<FSalesCallPlandItemsEntity>
//    fun findByKode1(kode1: String): List<FSalesCallPlandItemsEntity>
//    fun findAll(kode1: String, description: String): List<FSalesCallPlandItemsEntity>
    fun findByParent(fparentBean: Long): List<FSalesCallPlandItemsEntity>
    fun findByListOfParent(listFSalesCallPlanh: List<Long>): List<FSalesCallPlandItemsEntity>

//    fun save(fSalesCallPlandItemsEntity: FSalesCallPlandItemsEntity): FSalesCallPlandItemsEntity
//    fun saveAll(listFSalesCallPlandItems: List<FSalesCallPlandItemsEntity>): List<FSalesCallPlandItemsEntity>
//    fun delete(fSalesCallPlandItemsEntity: FSalesCallPlandItemsEntity)
//    fun deleteInBatch(listFSalesCallPlandItems: List<FSalesCallPlandItemsEntity>)

}