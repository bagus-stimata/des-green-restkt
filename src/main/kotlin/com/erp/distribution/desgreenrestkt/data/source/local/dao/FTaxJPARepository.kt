package com.erp.distribution.desgreenrestkt.data.source.local.dao

import com.erp.distribution.desgreenrestkt.data.source.entity.FTaxEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FTaxJPARepository : JpaRepository<FTaxEntity, Int> {
//    override fun findById(id: Int): FTaxEntity
    fun findByKode1(kode1: String): List<FTaxEntity>

    @Query("SELECT u FROM FTaxEntity u WHERE u.kode1 LIKE :kode1 and u.description LIKE :description")
    fun findAll(kode1: String, description: String): List<FTaxEntity>

    @Query("SELECT u FROM FTaxEntity u WHERE u.fdivisionBean = :fdivisionBean")
    fun findAllByDivision(fdivisionBean: Int): List<FTaxEntity>

    @Query("SELECT u FROM FTaxEntity u  " +
            " left outer join FDivisionEntity f on u.fdivisionBean = f.id " +
            " where f.id = :fdivisionBean OR " +
            "      (f.shareMaterialToCompany = true and f.fcompanyBean = :fcompanyBean ) ")
    fun findAllByDivisionAndShareToCompany(fdivisionBean: Int, fcompanyBean: Int): List<FTaxEntity>
}