package com.erp.distribution.desgreenrestkt.domain.repository

import com.erp.distribution.desgreenrestkt.data.source.entity.FArea
import com.erp.distribution.desgreenrestkt.data.source.entity.FMaterialEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface FMaterialRepo {
    fun findAll(): List<FMaterialEntity>

    fun findByPcode(pcode: String?): List<FMaterialEntity>
    fun findAll(pcode: String, pname: String): List<FMaterialEntity>
    fun findAllByDivision(fdivisionBean: Int): List<FMaterialEntity>
    fun findAllByDivisionAndListVendor(fdivisionBean: Int, listVendor: List<Int>): List<FMaterialEntity>
    fun findAllByDivisionAndShareToCompany(fdivisionBean: Int, fcompanyBean: Int): List<FMaterialEntity>
    fun findAllByDivisionAndShareToCompanyAndListVendor(fdivisionBean: Int, fcompanyBean: Int, listVendor: List<Int>): List<FMaterialEntity>
    fun findAllByDivision(fdivisionBean: Int, pageable: Pageable?): Page<FMaterialEntity>

}
