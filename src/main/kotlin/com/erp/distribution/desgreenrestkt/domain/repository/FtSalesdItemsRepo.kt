package com.erp.distribution.desgreenrestkt.domain.repository

import com.erp.distribution.desgreenrestkt.data.source.entity.FtSalesdItemsEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface FtSalesdItemsRepo {
    fun findById(id: Long): FtSalesdItemsEntity
    fun findAll(): List<FtSalesdItemsEntity>
    fun findByParent(fparentBean: Long): List<FtSalesdItemsEntity>
    fun findByListOfParent(listFParent: List<Long>): List<FtSalesdItemsEntity>

    fun save(ftSalesdItemsEntity: FtSalesdItemsEntity): FtSalesdItemsEntity
    fun saveAll(listFtSalesdItems: List<FtSalesdItemsEntity>): List<FtSalesdItemsEntity>
    fun delete(ftSalesdItemsEntity: FtSalesdItemsEntity)
    fun deleteInBatch(listFtSalesdItems: List<FtSalesdItemsEntity>)

}