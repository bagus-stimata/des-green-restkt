package com.erp.distribution.desgreenrestkt.data.source.local.dao

import com.erp.distribution.desgreenrestkt.data.source.entity.FPromotionRulesdValidProductsEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FPromotionRulesdValidProductsJPARepository : JpaRepository<FPromotionRulesdValidProductsEntity, Int> {
//    override fun findById(id: Int): FPromotionRulesdValidProductsEntity
    fun findByNoUrut(noUrut: String): List<FPromotionRulesdValidProductsEntity>

    @Query("SELECT u FROM FPromotionRulesdValidProductsEntity u WHERE u.noUrut LIKE :noUrut ")
    fun findAll(noUrut: String): List<FPromotionRulesdValidProductsEntity>

//    @Query("SELECT u FROM FPromotionRulesdValidProductsEntity u WHERE u.fpromotionRuleshBean = :fpromotionRuleshBean")
//    fun findAllByParentId(fpromotionRuleshBean: Int): List<FPromotionRulesdValidProductsEntity>
//
//    @Query("SELECT u FROM FPromotionRulesdValidProductsEntity u WHERE u.fpromotionRuleshBean IN :listFpromotionRuleshBean")
//    fun findAllByListParentId(listFpromotionRuleshBean: List<Int>): List<FPromotionRulesdValidProductsEntity>

}