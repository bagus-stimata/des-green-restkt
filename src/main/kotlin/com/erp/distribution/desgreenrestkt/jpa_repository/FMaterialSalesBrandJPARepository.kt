package com.erp.distribution.desgreenrestkt.jpa_repository

import com.erp.distribution.desgreenrestkt.data.source.entity.FMaterialSalesBrand
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FMaterialSalesBrandJPARepository : JpaRepository<FMaterialSalesBrand, Int> {
//    override fun findById(id: Int): FMaterialSalesBrand
    fun findByKode1(kode1: String): List<FMaterialSalesBrand>

    @Query("SELECT u FROM FMaterialSalesBrand u WHERE u.kode1 LIKE :kode1 and u.description LIKE :description")
    fun findAll(kode1: String, description: String): List<FMaterialSalesBrand>

    @Query("SELECT u FROM FMaterialSalesBrand u WHERE u.fdivisionBean = :fdivisionBean")
    fun findAllByDivision(fdivisionBean: Int): List<FMaterialSalesBrand>

    @Query("SELECT u FROM FMaterialSalesBrand u " +
            " left outer join FDivision f on u.fdivisionBean = f.id " +
            " where f.id = :fdivisionBean OR " +
            "      (f.shareMaterialToCompany = true and f.fcompanyBean = :fcompanyBean ) ")
    fun findAllByDivisionAndShareToCompany(fdivisionBean: Int, fcompanyBean: Int): List<FMaterialSalesBrand>
}