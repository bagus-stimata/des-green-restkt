package com.erp.distribution.desgreenrestkt.data.source.local.dao

import com.erp.distribution.desgreenrestkt.data.source.entity.FMaterialGroup3Entity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FMaterialGroup3JPARepository : JpaRepository<FMaterialGroup3Entity, Int> {
//    override fun findById(id: Int): FMaterialGroup3Entity?
    fun findByKode1(kode1: String): List<FMaterialGroup3Entity>

    @Query("SELECT u FROM FMaterialGroup3Entity u WHERE u.kode1 LIKE :kode1 and u.description LIKE :description")
    fun findAll(kode1: String, description: String): List<FMaterialGroup3Entity>

    @Query("SELECT u FROM FMaterialGroup3Entity u WHERE u.fmaterialGroup2Bean = :fmaterialGroup2Bean")
    fun findAllByParentId(fmaterialGroup2Bean: Int): List<FMaterialGroup3Entity>

    @Query("SELECT u FROM FMaterialGroup3Entity u WHERE u.fmaterialGroup2Bean IN :listFMaterialGroup2Bean")
    fun findAllByListOfParent(listFMaterialGroup2Bean: List<Int>): List<FMaterialGroup3Entity>
}