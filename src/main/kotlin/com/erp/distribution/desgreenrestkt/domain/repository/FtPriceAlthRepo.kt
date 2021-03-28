package com.erp.distribution.desgreenrestkt.domain.repository

import com.erp.distribution.desgreenrestkt.data.source.entity.FtPriceAlthEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface FtPriceAlthRepo {
    fun findById(id: Int): FtPriceAlthEntity
    fun findAll(): List<FtPriceAlthEntity>
    fun findByNoRek(kode1: String): List<FtPriceAlthEntity>
    fun findByDivision(fdivisionBean: Int): List<FtPriceAlthEntity>
    fun findByDivisionAndShareToCompany(fdivisionBean: Int, fcompanyBean: Int): List<FtPriceAlthEntity>

    fun save(ftPriceAlthEntity: FtPriceAlthEntity): FtPriceAlthEntity
    fun saveAll(listFtPriceAlth: List<FtPriceAlthEntity>): List<FtPriceAlthEntity>
    fun delete(ftPriceAlthEntity: FtPriceAlthEntity)
    fun deleteInBatch(listFtPriceAlth: List<FtPriceAlthEntity>)

}