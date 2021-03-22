package com.erp.distribution.desgreenrestkt.data.source.local.dao

import com.erp.distribution.desgreenrestkt.data.source.entity.FRegion
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FRegionJPARepository : JpaRepository<FRegion, Int> {
//    override fun findById(id: Int): FRegion
    fun findByKode1(kode1: String): List<FRegion>

    @Query("SELECT u FROM FRegion u WHERE u.kode1 LIKE :kode1 and u.description LIKE :description")
    fun findAll(kode1: String, description: String): List<FRegion>

    @Query("SELECT u FROM FRegion u WHERE u.fdivisionBean = :fdivisionBean")
    fun findAllByDivision(fdivisionBean: Int): List<FRegion>
}