package com.erp.distribution.desgreenrestkt.jpa_repository

import com.erp.distribution.desgreenrestkt.data.source.entity.FMaterial
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FMaterialJPARepository : JpaRepository<FMaterial, Int> {
//    override fun findById(id: Int): FMaterial?
    fun findByPcode(pcode: String?): List<FMaterial>

    @Query("SELECT u FROM FMaterial u WHERE u.pcode LIKE :pcode and u.pname LIKE :pname")
    fun findAll(pcode: String?, pname: String?): List<FMaterial>

    @Query("SELECT u FROM FMaterial u WHERE u.fdivisionBean = :fdivisionBean")
    fun findAllByDivision(fdivisionBean: Int): List<FMaterial>

    @Query("SELECT u FROM FMaterial u WHERE u.fdivisionBean = :fdivisionBean AND u.fvendorBean IN :listVendor")
    fun findAllByDivisionAndListVendor(fdivisionBean: Int, listVendor: List<Int>): List<FMaterial>

    @Query("select u from FMaterial u " +
            " left outer join FDivision f on u.fdivisionBean = f.id " +
            " where f.id = :fdivisionBean OR " +
            "      (f.shareMaterialToCompany = true and f.fcompanyBean = :fcompanyBean ) ")
    fun findAllByDivisionAndShareToCompany(fdivisionBean: Int, fcompanyBean: Int): List<FMaterial>

    @Query("select u from FMaterial u " +
            " left outer join FDivision f on u.fdivisionBean = f.id " +
            " where (f.id = :fdivisionBean OR " +
            "      (f.shareMaterialToCompany = true and f.fcompanyBean = :fcompanyBean )) AND  u.fvendorBean IN :listVendor")
    fun findAllByDivisionAndShareToCompanyAndListVendor(fdivisionBean: Int, fcompanyBean: Int, listVendor: List<Int>): List<FMaterial>

    @Query("SELECT u FROM FMaterial u WHERE u.fdivisionBean = :fdivisionBean")
    fun findAllByDivision(fdivisionBean: Int, pageable: Pageable?): Page<FMaterial>
}