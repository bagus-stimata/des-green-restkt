package com.erp.distribution.desgreenrestkt.data.source.local.dao

import com.erp.distribution.desgreenrestkt.data.source.entity.FCustomerEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FCustomerJPARepository : JpaRepository<FCustomerEntity, Int> {
//    override fun findById(id: Int): FCustomerEntity?
    fun findByCustno(custno: String?): List<FCustomerEntity>

    @Query("SELECT u FROM FCustomerEntity u WHERE u.custno LIKE :custno and u.custname LIKE :custname ")
    fun findAll(custno: String?, custname: String?): List<FCustomerEntity>

    @Query("SELECT u FROM FCustomerEntity u WHERE u.fdivisionBean = :fdivisionBean")
    fun findAllByDivision(fdivisionBean: Int): List<FCustomerEntity>

    @Query("SELECT u FROM FCustomerEntity u " +
            " left outer join FDivision f on u.fdivisionBean = f.id " +
            " where f.id = :fdivisionBean OR " +
            "      (f.shareMaterialToCompany = true and f.fcompanyBean = :fcompanyBean ) ")
    fun findAllByDivisionAndShareToCompany(fdivisionBean: Int, fcompanyBean: Int): List<FCustomerEntity>

    @Query("SELECT u FROM FCustomerEntity u WHERE u.fdivisionBean = :fdivisionBean")
    fun findAllByDivision(fdivisionBean: Int, pageable: Pageable?): Page<FCustomerEntity>
}