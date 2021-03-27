package com.erp.distribution.desgreenrestkt.domain.repository

import com.erp.distribution.desgreenrestkt.data.source.entity.FPromotionRulesdValidProductsEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface FPromotionRulesdValidProductsRepo {
    fun findById(id: Int): FPromotionRulesdValidProductsEntity
    fun findAll(): List<FPromotionRulesdValidProductsEntity>
    fun findByKode1(kode1: String): List<FPromotionRulesdValidProductsEntity>
    fun findAll(kode1: String, description: String): List<FPromotionRulesdValidProductsEntity>
    fun findAllByDivision(fdivisionBean: Int): List<FPromotionRulesdValidProductsEntity>
    fun findAllByDivisionAndShareToCompany(fdivisionBean: Int, fcompanyBean: Int): List<FPromotionRulesdValidProductsEntity>

    fun save(fPromotionRulesdValidProductsEntity: FPromotionRulesdValidProductsEntity): FPromotionRulesdValidProductsEntity
    fun saveAll(listFPromotionRulesdValidProducts: List<FPromotionRulesdValidProductsEntity>): List<FPromotionRulesdValidProductsEntity>
    fun delete(fPromotionRulesdValidProductsEntity: FPromotionRulesdValidProductsEntity)
    fun deleteInBatch(listFPromotionRulesdValidProducts: List<FPromotionRulesdValidProductsEntity>)

}