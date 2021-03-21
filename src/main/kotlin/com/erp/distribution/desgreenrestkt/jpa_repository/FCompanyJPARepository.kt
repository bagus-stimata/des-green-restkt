package com.erp.distribution.desgreenrestkt.jpa_repository

import com.erp.distribution.desgreenrestkt.model.FCompany
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FCompanyJPARepository : JpaRepository<FCompany, Int> {
//    override fun findById(id: Int): FCompany?
    fun findByKode1(kode1: String?): List<FCompany>

    @Query("SELECT u FROM FCompany u WHERE u.kode1 LIKE :kode1 and u.description LIKE :description")
    fun findAll(kode1: String?, description: String?): List<FCompany>
}