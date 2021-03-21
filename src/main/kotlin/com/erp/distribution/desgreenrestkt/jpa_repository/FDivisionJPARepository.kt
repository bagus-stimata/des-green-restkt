package com.erp.distribution.desgreenrestkt.jpa_repository

import com.erp.distribution.desgreenrestkt.model.FDivision
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FDivisionJPARepository : JpaRepository<FDivision, Int> {
//    override fun findById(id: Int): FDivision?
    fun findByKode1(kode1: String?): List<FDivision>

    @Query("SELECT u FROM FDivision u WHERE u.kode1 LIKE :kode1 and u.description LIKE :description")
    fun findAll(kode1: String?, description: String?): List<FDivision>

    @Query("SELECT u FROM FDivision u WHERE u.fcompanyBean = :fcompanyBean")
    fun findAllByParentId(fcompanyBean: Int): List<FDivision>
}