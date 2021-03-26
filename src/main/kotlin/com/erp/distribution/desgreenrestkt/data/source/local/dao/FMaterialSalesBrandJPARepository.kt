package com.erp.distribution.desgreenrestkt.data.source.local.dao

import com.erp.distribution.desgreenrestkt.data.source.entity.FMaterialSalesBrandEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FMaterialSalesBrandJPARepository : JpaRepository<FMaterialSalesBrandEntity, Int> {
//    override fun findById(id: Int): FMaterialSalesBrandEntity
    fun findByKode1(kode1: String): List<FMaterialSalesBrandEntity>

    @Query("SELECT u FROM FMaterialSalesBrandEntity u WHERE u.kode1 LIKE :kode1 and u.description LIKE :description")
    fun findAll(kode1: String, description: String): List<FMaterialSalesBrandEntity>

    @Query("SELECT u FROM FMaterialSalesBrandEntity u WHERE u.fdivisionBean = :fdivisionBean")
    fun findAllByDivision(fdivisionBean: Int): List<FMaterialSalesBrandEntity>

    @Query("SELECT u FROM FMaterialSalesBrandEntity u " +
            " left outer join FDivisionEntity f on u.fdivisionBean = f.id " +
            " where f.id = :fdivisionBean OR " +
            "      (f.shareMaterialToCompany = true and f.fcompanyBean = :fcompanyBean ) ")
    fun findAllByDivisionAndShareToCompany(fdivisionBean: Int, fcompanyBean: Int): List<FMaterialSalesBrandEntity>
}