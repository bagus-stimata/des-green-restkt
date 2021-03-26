package com.erp.distribution.desgreenrestkt.data.source.local.dao

import com.erp.distribution.desgreenrestkt.data.source.entity.FRegionEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FRegionJPARepository : JpaRepository<FRegionEntity, Int> {
//    override fun findById(id: Int): FRegionEntity
    fun findByKode1(kode1: String): List<FRegionEntity>

    @Query("SELECT u FROM FRegionEntity u WHERE u.kode1 LIKE :kode1 and u.description LIKE :description")
    fun findAll(kode1: String, description: String): List<FRegionEntity>

    @Query("SELECT u FROM FRegionEntity u WHERE u.fdivisionBean = :fdivisionBean")
    fun findAllByDivision(fdivisionBean: Int): List<FRegionEntity>
}