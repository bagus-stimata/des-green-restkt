package com.erp.distribution.desgreenrestkt.data.source.local.dao

import com.erp.distribution.desgreenrestkt.data.source.entity.FMaterialGroup1Entity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FMaterialGroup1JPARepository : JpaRepository<FMaterialGroup1Entity, Int> {
//    override fun findById(id: Int): FMaterialGroup1Entity?
    fun findByKode1(kode1: String): List<FMaterialGroup1Entity>

    @Query("SELECT u FROM FMaterialGroup1Entity u WHERE u.kode1 LIKE :kode1 and u.description LIKE :description")
    fun findAll(kode1: String, description: String): List<FMaterialGroup1Entity>

    @Query("SELECT u FROM FMaterialGroup1Entity u WHERE u.fdivisionBean = :fdivisionBean")
    fun findAllByDivision(fdivisionBean: Int): List<FMaterialGroup1Entity>

    @Query("SELECT u FROM FMaterialGroup1Entity u " +
            " left outer join FDivisionEntity f on u.fdivisionBean = f.id " +
            " where f.id = :fdivisionBean OR " +
            "      (f.shareMaterialToCompany = true and f.fcompanyBean = :fcompanyBean ) ")
    fun findAllByDivisionAndShareToCompany(fdivisionBean: Int, fcompanyBean: Int): List<FMaterialGroup1Entity>

    @Query("SELECT u FROM FMaterialGroup1Entity u " +
            " left outer join FDivisionEntity f on u.fdivisionBean = f.id " +
            " where f.fcompanyBean = :fcompanyBean  ")
    fun findAllByCompany(fcompanyBean: Int): List<FMaterialGroup1Entity>
}