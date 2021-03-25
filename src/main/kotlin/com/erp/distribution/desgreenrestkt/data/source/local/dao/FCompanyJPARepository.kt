package com.erp.distribution.desgreenrestkt.data.source.local.dao

import com.erp.distribution.desgreenrestkt.data.source.entity.FCompanyEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FCompanyJPARepository : JpaRepository<FCompanyEntity, Int> {
//    override fun findById(id: Int): FCompanyEntity?
    fun findByKode1(kode1: String?): List<FCompanyEntity>

    @Query("SELECT u FROM FCompanyEntity u WHERE u.kode1 LIKE :kode1 and u.description LIKE :description")
    fun findAll(kode1: String?, description: String?): List<FCompanyEntity>
}