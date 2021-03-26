package com.erp.distribution.desgreenrestkt.data.source.local.dao

import com.erp.distribution.desgreenrestkt.data.source.entity.FVendorEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FVendorJPARepository : JpaRepository<FVendorEntity, Int> {
//    override fun findById(id: Int): FVendorEntity
    fun findByVcode(vcode: String): List<FVendorEntity>

    @Query("SELECT u FROM FVendorEntity u WHERE u.vcode LIKE :vcode and u.vname LIKE :vname")
    fun findAll(vcode: String, vname: String): List<FVendorEntity>

    @Query("SELECT u FROM FVendorEntity u WHERE u.fdivisionBean = :fdivisionBean")
    fun findAllByDivision(fdivisionBean: Int): List<FVendorEntity>

    @Query("SELECT u FROM FVendorEntity u " +
            " left outer join FDivisionEntity f on u.fdivisionBean = f.id " +
            " where  f.fcompanyBean = :fcompanyBean   ORDER BY vcode")
    fun findAllByCompany(fcompanyBean: Int): List<FVendorEntity>

    @Query("SELECT u FROM FVendorEntity u " +
            " left outer join FDivisionEntity f on u.fdivisionBean = f.id " +
            " where f.id = :fdivisionBean OR " +
            "      (f.shareMaterialToCompany = true and f.fcompanyBean = :fcompanyBean ) ORDER BY vcode")
    fun findAllByDivisionAndShareToCompany(fdivisionBean: Int, fcompanyBean: Int): List<FVendorEntity>
}