package com.erp.distribution.desgreenrestkt.domain.repository

import com.erp.distribution.desgreenrestkt.data.source.entity.FPromotionRulesdValidCustsEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface FPromotionRulesdValidCustsRepo {
    fun findById(id: Int): FPromotionRulesdValidCustsEntity
    fun findAll(): List<FPromotionRulesdValidCustsEntity>
    fun findByKode1(kode1: String): List<FPromotionRulesdValidCustsEntity>
    fun findAll(kode1: String, description: String): List<FPromotionRulesdValidCustsEntity>
    fun findByDivision(fdivisionBean: Int): List<FPromotionRulesdValidCustsEntity>
    fun findByDivisionAndShareToCompany(fdivisionBean: Int, fcompanyBean: Int): List<FPromotionRulesdValidCustsEntity>

    fun save(fPromotionRulesdValidCustsEntity: FPromotionRulesdValidCustsEntity): FPromotionRulesdValidCustsEntity
    fun saveAll(listFPromotionRulesdValidCusts: List<FPromotionRulesdValidCustsEntity>): List<FPromotionRulesdValidCustsEntity>
    fun delete(fPromotionRulesdValidCustsEntity: FPromotionRulesdValidCustsEntity)
    fun deleteInBatch(listFPromotionRulesdValidCusts: List<FPromotionRulesdValidCustsEntity>)

}