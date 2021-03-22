package com.erp.distribution.desgreenrestkt.data.source.local.dao

import com.erp.distribution.desgreenrestkt.data.source.entity.FMaterialGroup2
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FMaterialGroup2JPARepository : JpaRepository<FMaterialGroup2, Int> {
//    override fun findById(id: Int): FMaterialGroup2?
    fun findByKode1(kode1: String?): List<FMaterialGroup2>

    @Query("SELECT u FROM FMaterialGroup2 u WHERE u.kode1 LIKE :kode1 and u.description LIKE :description")
    fun findAll(kode1: String?, description: String?): List<FMaterialGroup2>

    @Query("SELECT u FROM FMaterialGroup2 u WHERE u.fmaterialGroup1Bean = :fmaterialGroup1Bean")
    fun findAllByParentId(fmaterialGroup1Bean: Int): List<FMaterialGroup2>

    @Query("SELECT u FROM FMaterialGroup2 u WHERE u.fmaterialGroup1Bean IN :listFMaterialGroup1Bean")
    fun findAllByListParentId(listFMaterialGroup1Bean: List<Int>): List<FMaterialGroup2>
}