package com.erp.distribution.desgreenrestkt.domain.repository

import com.erp.distribution.desgreenrestkt.data.source.entity.FDistributionChannelEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface FDistributionChannelRepo {
    fun findById(id: Int): FDistributionChannelEntity
    fun findAll(): List<FDistributionChannelEntity>
    fun findByKode1(kode1: String): List<FDistributionChannelEntity>
    fun findAll(kode1: String, description: String): List<FDistributionChannelEntity>
    fun findByDivision(fdivisionBean: Int): List<FDistributionChannelEntity>
    fun findByDivisionAndShareToCompany(fdivisionBean: Int, fcompanyBean: Int): List<FDistributionChannelEntity>

    fun save(fDistributionChannelEntity: FDistributionChannelEntity): FDistributionChannelEntity
    fun saveAll(listFDistributionChannel: List<FDistributionChannelEntity>): List<FDistributionChannelEntity>
    fun delete(fDistributionChannelEntity: FDistributionChannelEntity)
    fun deleteInBatch(listFDistributionChannel: List<FDistributionChannelEntity>)

}