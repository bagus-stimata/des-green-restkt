package com.erp.distribution.desgreenrestkt.data.source.local.dao

import com.erp.distribution.desgreenrestkt.data.source.entity.FDivisionEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FDivisionJPARepository : JpaRepository<FDivisionEntity, Int> {
//    override fun findById(id: Int): FDivisionEntity?
    fun findByKode1(kode1: String): List<FDivisionEntity>

    @Query("SELECT u FROM FDivisionEntity u WHERE u.kode1 LIKE :kode1 and u.description LIKE :description")
    fun findAll(kode1: String, description: String): List<FDivisionEntity>

    @Query("SELECT u FROM FDivisionEntity u WHERE u.fcompanyBean = :fcompanyBean")
    fun findAllByCompanyId(fcompanyBean: Int): List<FDivisionEntity>
}