package com.erp.distribution.desgreenrestkt.data.source.local.dao

import com.erp.distribution.desgreenrestkt.data.source.entity.FPromotionRulesdValidCustsEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FPromotionRulesdValidCustsJPARepository : JpaRepository<FPromotionRulesdValidCustsEntity, Int> {

    //    override fun findById(id: Int): FPromotionRulesdValidCustsEntity
    fun findByNoUrut(noUrut: String): List<FPromotionRulesdValidCustsEntity>

    @Query("SELECT u FROM FPromotionRulesdValidCustsEntity u WHERE u.noUrut LIKE :noUrut")
    fun findAll(noUrut: String): List<FPromotionRulesdValidCustsEntity>

//    @Query("SELECT u FROM FPromotionRulesdValidCustsEntity u WHERE u.fpromotionRuleshBean = :fpromotionRuleshBean")
//    fun findAllByParentId(fpromotionRuleshBean: Int): List<FPromotionRulesdValidCustsEntity>
//
//    @Query("SELECT u FROM FPromotionRulesdValidCustsEntity u WHERE u.fpromotionRuleshBean IN :listFpromotionRuleshBean")
//    fun findAllByListParentId(listFpromotionRuleshBean: List<Int>): List<FPromotionRulesdValidCustsEntity>

}