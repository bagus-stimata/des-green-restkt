package com.erp.distribution.desgreenrestkt.data.source.local.dao

import com.erp.distribution.desgreenrestkt.data.source.entity.FVendor
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FVendorJPARepository : JpaRepository<FVendor, Int> {
//    override fun findById(id: Int): FVendor
    fun findByVcode(vcode: String): List<FVendor>

    @Query("SELECT u FROM FVendor u WHERE u.vcode LIKE :vcode and u.vname LIKE :vname")
    fun findAll(vcode: String, vname: String): List<FVendor>

    @Query("SELECT u FROM FVendor u WHERE u.fdivisionBean = :fdivisionBean")
    fun findAllByDivision(fdivisionBean: Int): List<FVendor>

    @Query("SELECT u FROM FVendor u " +
            " left outer join FDivision f on u.fdivisionBean = f.id " +
            " where  f.fcompanyBean = :fcompanyBean   ORDER BY vcode")
    fun findAllByCompany(fcompanyBean: Int): List<FVendor>

    @Query("SELECT u FROM FVendor u " +
            " left outer join FDivision f on u.fdivisionBean = f.id " +
            " where f.id = :fdivisionBean OR " +
            "      (f.shareMaterialToCompany = true and f.fcompanyBean = :fcompanyBean ) ORDER BY vcode")
    fun findAllByDivisionAndShareToCompany(fdivisionBean: Int, fcompanyBean: Int): List<FVendor>
}