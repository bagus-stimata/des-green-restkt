package com.erp.distribution.desgreenrestkt.jpa_repository

import com.erp.distribution.desgreenrestkt.model.FCustomer
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FCustomerJPARepository : JpaRepository<FCustomer, Int> {
//    override fun findById(id: Int): FCustomer?
    fun findByCustno(custno: String?): List<FCustomer>

    @Query("SELECT u FROM FCustomer u WHERE u.custno LIKE :custno and u.custname LIKE :custname ")
    fun findAll(custno: String?, custname: String?): List<FCustomer>

    @Query("SELECT u FROM FCustomer u WHERE u.fdivisionBean = :fdivisionBean")
    fun findAllByDivision(fdivisionBean: Int): List<FCustomer>

    @Query("SELECT u FROM FCustomer u " +
            " left outer join FDivision f on u.fdivisionBean = f.id " +
            " where f.id = :fdivisionBean OR " +
            "      (f.shareMaterialToCompany = true and f.fcompanyBean = :fcompanyBean ) ")
    fun findAllByDivisionAndShareToCompany(fdivisionBean: Int, fcompanyBean: Int): List<FCustomer>

    @Query("SELECT u FROM FCustomer u WHERE u.fdivisionBean = :fdivisionBean")
    fun findAllByDivision(fdivisionBean: Int, pageable: Pageable?): Page<FCustomer>
}