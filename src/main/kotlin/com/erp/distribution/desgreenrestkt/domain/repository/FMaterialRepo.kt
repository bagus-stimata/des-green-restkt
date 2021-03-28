package com.erp.distribution.desgreenrestkt.domain.repository

import com.erp.distribution.desgreenrestkt.data.source.entity.FMaterialEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface FMaterialRepo {

    fun findById(id: Int): FMaterialEntity
    fun findAll(): List<FMaterialEntity>
    fun findByPcode(pcode: String): List<FMaterialEntity>
    fun findAll(pcode: String, pname: String): List<FMaterialEntity>
    fun findByDivision(fdivisionBean: Int): List<FMaterialEntity>
    fun findByDivisionAndListVendor(fdivisionBean: Int, listVendor: List<Int>): List<FMaterialEntity>
    fun findByDivisionAndShareToCompany(fdivisionBean: Int, fcompanyBean: Int): List<FMaterialEntity>
    fun findByDivisionAndShareToCompanyAndListVendor(fdivisionBean: Int, fcompanyBean: Int, listVendor: List<Int>): List<FMaterialEntity>
    fun findByDivision(fdivisionBean: Int, pageable: Pageable?): Page<FMaterialEntity>

}
