package com.erp.distribution.desgreenrestkt.domain.repository

import com.erp.distribution.desgreenrestkt.data.source.entity.FtSalesdItemsEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface FtSalesdItemsRepo {
    fun findById(id: Int): FtSalesdItemsEntity
    fun findAll(): List<FtSalesdItemsEntity>
    fun findByKode1(kode1: String): List<FtSalesdItemsEntity>
    fun findAll(kode1: String, description: String): List<FtSalesdItemsEntity>
    fun findByDivision(fdivisionBean: Int): List<FtSalesdItemsEntity>
    fun findByDivisionAndShareToCompany(fdivisionBean: Int, fcompanyBean: Int): List<FtSalesdItemsEntity>

    fun save(ftSalesdItemsEntity: FtSalesdItemsEntity): FtSalesdItemsEntity
    fun saveAll(listFtSalesdItems: List<FtSalesdItemsEntity>): List<FtSalesdItemsEntity>
    fun delete(ftSalesdItemsEntity: FtSalesdItemsEntity)
    fun deleteInBatch(listFtSalesdItems: List<FtSalesdItemsEntity>)

}