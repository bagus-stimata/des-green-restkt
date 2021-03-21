package com.erp.distribution.desgreenrestkt.jpa_repository

import com.erp.distribution.desgreenrestkt.model.FMaterialGroup1
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FMaterialGroup1JPARepository : JpaRepository<FMaterialGroup1, Int> {
//    override fun findById(id: Int): FMaterialGroup1?
    fun findByKode1(kode1: String?): List<FMaterialGroup1>

    @Query("SELECT u FROM FMaterialGroup1 u WHERE u.kode1 LIKE :kode1 and u.description LIKE :description")
    fun findAll(kode1: String?, description: String?): List<FMaterialGroup1>

    @Query("SELECT u FROM FMaterialGroup1 u WHERE u.fdivisionBean = :fdivisionBean")
    fun findAllByDivision(fdivisionBean: Int): List<FMaterialGroup1>

    @Query("SELECT u FROM FMaterialGroup1 u " +
            " left outer join FDivision f on u.fdivisionBean = f.id " +
            " where f.id = :fdivisionBean OR " +
            "      (f.shareMaterialToCompany = true and f.fcompanyBean = :fcompanyBean ) ")
    fun findAllByDivisionAndShareToCompany(fdivisionBean: Int, fcompanyBean: Int): List<FMaterialGroup1>

    @Query("SELECT u FROM FMaterialGroup1 u " +
            " left outer join FDivision f on u.fdivisionBean = f.id " +
            " where f.fcompanyBean = :fcompanyBean  ")
    fun findAllByCompany(fcompanyBean: Int): List<FMaterialGroup1>
}