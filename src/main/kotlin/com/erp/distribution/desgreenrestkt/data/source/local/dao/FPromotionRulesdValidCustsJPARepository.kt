package com.erp.distribution.desgreenrestkt.data.source.local.dao

import com.erp.distribution.desgreenrestkt.data.source.entity.FPromotionRulesdValidCusts
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FPromotionRulesdValidCustsJPARepository : JpaRepository<FPromotionRulesdValidCusts, Int> {
//    override fun findById(id: Int): FPromotionRulesdValidCusts
    fun findByNoUrut(noUrut: String): List<FPromotionRulesdValidCusts>

    @Query("SELECT u FROM FPromotionRulesdValidCusts u WHERE u.noUrut LIKE :noUrut")
    fun findAll(noUrut: String): List<FPromotionRulesdValidCusts>

    @Query("SELECT u FROM FPromotionRulesdValidCusts u WHERE u.fpromotionRuleshBean = :fpromotionRuleshBean")
    fun findAllByParentId(fpromotionRuleshBean: Int): List<FPromotionRulesdValidCusts>

    @Query("SELECT u FROM FPromotionRulesdValidCusts u WHERE u.fpromotionRuleshBean IN :listFpromotionRuleshBean")
    fun findAllByListParentId(listFpromotionRuleshBean: List<Int>): List<FPromotionRulesdValidCusts>
}