package com.erp.distribution.desgreenrestkt.domain.repository

import com.erp.distribution.desgreenrestkt.data.source.entity.FtSaleshEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface FtSaleshRepo {
    fun findById(id: Int): FtSaleshEntity
    fun findAll(): List<FtSaleshEntity>
    fun findByKode1(kode1: String): List<FtSaleshEntity>
    fun findAll(kode1: String, description: String): List<FtSaleshEntity>
    fun findAllByDivision(fdivisionBean: Int): List<FtSaleshEntity>
    fun findAllByDivisionAndShareToCompany(fdivisionBean: Int, fcompanyBean: Int): List<FtSaleshEntity>

    fun save(ftSaleshEntity: FtSaleshEntity): FtSaleshEntity
    fun saveAll(listFtSalesh: List<FtSaleshEntity>): List<FtSaleshEntity>
    fun delete(ftSaleshEntity: FtSaleshEntity)
    fun deleteInBatch(listFtSalesh: List<FtSaleshEntity>)

}