package com.erp.distribution.desgreenrestkt.data.source.local.dao

import com.erp.distribution.desgreenrestkt.data.source.entity.FMaterialEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FMaterialJPARepository : JpaRepository<FMaterialEntity, Int> {

    fun findByPcode(pcode: String?): List<FMaterialEntity>

    @Query("SELECT u FROM FMaterialEntity u WHERE u.pcode LIKE :pcode and u.pname LIKE :pname")
    fun findAll(pcode: String, pname: String): List<FMaterialEntity>

    @Query("SELECT u FROM FMaterialEntity u WHERE u.fdivisionBean = :fdivisionBean")
    fun findAllByDivision(fdivisionBean: Int): List<FMaterialEntity>

    @Query("SELECT u FROM FMaterialEntity u WHERE u.fdivisionBean = :fdivisionBean AND u.fvendorBean IN :listVendor")
    fun findAllByDivisionAndListVendor(fdivisionBean: Int, listVendor: List<Int>): List<FMaterialEntity>

    @Query("select u from FMaterialEntity u " +
            " left outer join FDivision f on u.fdivisionBean = f.id " +
            " where f.id = :fdivisionBean OR " +
            "      (f.shareMaterialToCompany = true and f.fcompanyBean = :fcompanyBean ) ")
    fun findAllByDivisionAndShareToCompany(fdivisionBean: Int, fcompanyBean: Int): List<FMaterialEntity>

    @Query("select u from FMaterialEntity u " +
            " left outer join FDivision f on u.fdivisionBean = f.id " +
            " where (f.id = :fdivisionBean OR " +
            "      (f.shareMaterialToCompany = true and f.fcompanyBean = :fcompanyBean )) AND  u.fvendorBean IN :listVendor")
    fun findAllByDivisionAndShareToCompanyAndListVendor(fdivisionBean: Int, fcompanyBean: Int, listVendor: List<Int>): List<FMaterialEntity>

    @Query("SELECT u FROM FMaterialEntity u WHERE u.fdivisionBean = :fdivisionBean")
    fun findAllByDivision(fdivisionBean: Int, pageable: Pageable?): Page<FMaterialEntity>

}