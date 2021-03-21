package com.erp.distribution.desgreenrestkt.jpa_repository

import com.erp.distribution.desgreenrestkt.data.source.entity.FCustomerGroup
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FCustomerGroupJPARepository : JpaRepository<FCustomerGroup, Int> {
//    override fun findById(id: Int): FCustomerGroup?
    fun findByKode1(kode1: String?): List<FCustomerGroup>

    @Query("SELECT u FROM FCustomerGroup u WHERE u.kode1 LIKE :kode1 and u.description LIKE :description ")
    fun findAll(kode1: String?, description: String?): List<FCustomerGroup>

    @Query("SELECT u FROM FCustomerGroup u WHERE u.fdivisionBean = :fdivisionBean")
    fun findAllByDivision(fdivisionBean: Int): List<FCustomerGroup>

    @Query("SELECT u FROM FCustomerGroup u " +
            " left outer join FDivision f on u.fdivisionBean = f.id " +
            " where f.id = :fdivisionBean OR " +
            "      (f.shareMaterialToCompany = true and f.fcompanyBean = :fcompanyBean ) ")
    fun findAllByDivisionAndShareToCompany(fdivisionBean: Int, fcompanyBean: Int): List<FCustomerGroup>
}