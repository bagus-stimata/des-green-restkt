package com.erp.distribution.desgreenrestkt.domain.repository

import com.erp.distribution.desgreenrestkt.data.source.entity.FtPriceAltdItemsEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface FtPriceAltdItemsRepo {
    fun findById(id: Int): FtPriceAltdItemsEntity
    fun findAll(): List<FtPriceAltdItemsEntity>
    fun findByParent(fparentBean: Int): List<FtPriceAltdItemsEntity>

    fun save(ftPriceAltdItemsEntity: FtPriceAltdItemsEntity): FtPriceAltdItemsEntity
    fun saveAll(listFtPriceAltdItems: List<FtPriceAltdItemsEntity>): List<FtPriceAltdItemsEntity>
    fun delete(ftPriceAltdItemsEntity: FtPriceAltdItemsEntity)
    fun deleteInBatch(listFtPriceAltdItems: List<FtPriceAltdItemsEntity>)

}