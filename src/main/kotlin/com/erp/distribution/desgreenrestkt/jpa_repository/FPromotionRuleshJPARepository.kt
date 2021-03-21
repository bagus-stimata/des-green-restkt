package com.erp.distribution.desgreenrestkt.jpa_repository

import com.erp.distribution.desgreenrestkt.data.source.entity.FPromotionRulesh
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FPromotionRuleshJPARepository : JpaRepository<FPromotionRulesh, Int> {
//    override fun findById(id: Int): FPromotionRulesh
    fun findByKode1(kode1: String): List<FPromotionRulesh>

    @Query("SELECT u FROM FPromotionRulesh u WHERE u.kode1 LIKE :kode1 and u.description LIKE :description")
    fun findAll(kode1: String, description: String): List<FPromotionRulesh>

    @Query("SELECT u FROM FPromotionRulesh u WHERE u.fdivisionBean = :fdivisionBean")
    fun findAllByDivision(fdivisionBean: Int): List<FPromotionRulesh> //    @Query("SELECT u FROM FPromotionRulesh u WHERE "
    //            + " ( a.fdivisionBean IN :listFdivisionBean OR a.sharedToCompany = true)"
    //            + " AND a.validPeriodDateFrom <= :posisiDate AND a.validPeriodDateTo >= :posisiDate "
    //            + " AND (a.statusActive >= :isActiveOnly AND a.statusActive <= true "
    //            + " " )
    //    List<FPromotionRulesh>  findAllBy_ValidByDate(List<FDivision> listFdivisionBean, Date posisiDate, boolean isActiveOnly);
}