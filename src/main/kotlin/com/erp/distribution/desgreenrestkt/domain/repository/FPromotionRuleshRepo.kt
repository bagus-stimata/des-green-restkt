package com.erp.distribution.desgreenrestkt.domain.repository

import com.erp.distribution.desgreenrestkt.data.source.entity.FPromotionRuleshEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface FPromotionRuleshRepo {
    fun findById(id: Int): FPromotionRuleshEntity
    fun findAll(): List<FPromotionRuleshEntity>
    fun findByKode1(kode1: String): List<FPromotionRuleshEntity>
    fun findAll(kode1: String, description: String): List<FPromotionRuleshEntity>
    fun findByDivision(fdivisionBean: Int): List<FPromotionRuleshEntity>

    fun save(fPromotionRuleshEntity: FPromotionRuleshEntity): FPromotionRuleshEntity
    fun saveAll(listFPromotionRulesh: List<FPromotionRuleshEntity>): List<FPromotionRuleshEntity>
    fun delete(fPromotionRuleshEntity: FPromotionRuleshEntity)
    fun deleteInBatch(listFPromotionRulesh: List<FPromotionRuleshEntity>)

}