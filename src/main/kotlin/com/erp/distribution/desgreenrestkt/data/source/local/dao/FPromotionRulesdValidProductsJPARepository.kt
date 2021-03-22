package com.erp.distribution.desgreenrestkt.data.source.local.dao

import com.erp.distribution.desgreenrestkt.data.source.entity.FPromotionRulesdValidProducts
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FPromotionRulesdValidProductsJPARepository : JpaRepository<FPromotionRulesdValidProducts, Int> {
//    override fun findById(id: Int): FPromotionRulesdValidProducts
    fun findByNoUrut(noUrut: String): List<FPromotionRulesdValidProducts>

    @Query("SELECT u FROM FPromotionRulesdValidProducts u WHERE u.noUrut LIKE :noUrut ")
    fun findAll(noUrut: String): List<FPromotionRulesdValidProducts>

    @Query("SELECT u FROM FPromotionRulesdValidProducts u WHERE u.fpromotionRuleshBean = :fpromotionRuleshBean")
    fun findAllByParentId(fpromotionRuleshBean: Int): List<FPromotionRulesdValidProducts>

    @Query("SELECT u FROM FPromotionRulesdValidProducts u WHERE u.fpromotionRuleshBean IN :listFpromotionRuleshBean")
    fun findAllByListParentId(listFpromotionRuleshBean: List<Int>): List<FPromotionRulesdValidProducts>
}