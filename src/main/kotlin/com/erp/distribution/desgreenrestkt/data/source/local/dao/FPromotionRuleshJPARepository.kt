package com.erp.distribution.desgreenrestkt.data.source.local.dao

import com.erp.distribution.desgreenrestkt.data.source.entity.FPromotionRuleshEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FPromotionRuleshJPARepository : JpaRepository<FPromotionRuleshEntity, Int> {
//    override fun findById(id: Int): FPromotionRuleshEntity
    fun findByKode1(kode1: String): List<FPromotionRuleshEntity>

    @Query("SELECT u FROM FPromotionRuleshEntity u WHERE u.kode1 LIKE :kode1 and u.description LIKE :description")
    fun findAll(kode1: String, description: String): List<FPromotionRuleshEntity>

    @Query("SELECT u FROM FPromotionRuleshEntity u WHERE u.fdivisionBean.id = :fdivisionBean")
    fun findAllByDivision(fdivisionBean: Int): List<FPromotionRuleshEntity> //    @Query("SELECT u FROM FPromotionRuleshEntity u WHERE "
    //            + " ( a.fdivisionBean IN :listFdivisionBean OR a.sharedToCompany = true)"
    //            + " AND a.validPeriodDateFrom <= :posisiDate AND a.validPeriodDateTo >= :posisiDate "
    //            + " AND (a.statusActive >= :isActiveOnly AND a.statusActive <= true "
    //            + " " )
    //    List<FPromotionRuleshEntity>  findAllBy_ValidByDate(List<FDivisionEntity> listFdivisionBean, Date posisiDate, boolean isActiveOnly);

}