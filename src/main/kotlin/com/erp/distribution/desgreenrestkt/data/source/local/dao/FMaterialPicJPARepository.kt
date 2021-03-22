package com.erp.distribution.desgreenrestkt.data.source.local.dao

import com.erp.distribution.desgreenrestkt.data.source.entity.FMaterialPic
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FMaterialPicJPARepository : JpaRepository<FMaterialPic, Int> {
//    override fun findById(id: Int): FMaterialPic

    @Query("SELECT u FROM FMaterialPic u WHERE u.uploadFrom LIKE :uploadFrom ")
    fun findAll(uploadFrom: String): List<FMaterialPic>

    @Query("SELECT u FROM FMaterialPic u WHERE u.fmaterialBean = :fmaterialBean")
    fun findAllByParentId(fmaterialBean: Int): List<FMaterialPic>

    @Query("SELECT u FROM FMaterialPic u WHERE u.fmaterialBean IN :listFMaterialBean")
    fun findAllByListParentId(listFMaterialBean: List<Int>): List<FMaterialPic>
}