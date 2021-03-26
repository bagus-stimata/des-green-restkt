package com.erp.distribution.desgreenrestkt.data.source.local.dao

import com.erp.distribution.desgreenrestkt.data.source.entity.FMaterialPicEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FMaterialPicJPARepository : JpaRepository<FMaterialPicEntity, Int> {
//    override fun findById(id: Int): FMaterialPicEntity

    @Query("SELECT u FROM FMaterialPicEntity u WHERE u.uploadFrom LIKE :uploadFrom ")
    fun findAll(uploadFrom: String): List<FMaterialPicEntity>

    @Query("SELECT u FROM FMaterialPicEntity u WHERE u.fmaterialBean = :fmaterialBean")
    fun findAllByParentId(fmaterialBean: Int): List<FMaterialPicEntity>

    @Query("SELECT u FROM FMaterialPicEntity u WHERE u.fmaterialBean IN :listFMaterialBean")
    fun findAllByListParentId(listFMaterialBean: List<Int>): List<FMaterialPicEntity>
}