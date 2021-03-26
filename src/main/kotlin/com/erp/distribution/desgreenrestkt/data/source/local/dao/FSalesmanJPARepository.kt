package com.erp.distribution.desgreenrestkt.data.source.local.dao

import com.erp.distribution.desgreenrestkt.data.source.entity.FSalesmanEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FSalesmanJPARepository : JpaRepository<FSalesmanEntity, Int> {
//    override fun findById(id: Int): FSalesmanEntity
    fun findBySpcode(spcode: String): List<FSalesmanEntity>

    @Query("SELECT u FROM FSalesmanEntity u WHERE u.spcode LIKE :spcode and u.spname LIKE :spname")
    fun findAll(spcode: String, spname: String): List<FSalesmanEntity>

    @Query("SELECT u FROM FSalesmanEntity u WHERE u.fdivisionBean = :fdivisionBean")
    fun findAllByDivision(fdivisionBean: Int): List<FSalesmanEntity>

    @Query("SELECT u FROM FSalesmanEntity u " +
            " left outer join FDivisionEntity f on u.fdivisionBean = f.id " +
            " where f.fcompanyBean = :fcompanyBean ORDER BY u.spcode ")
    fun findAllByCompany(fcompanyBean: Int): List<FSalesmanEntity>

    @Query("SELECT u FROM FSalesmanEntity u " +
            " left outer join FDivisionEntity f on u.fdivisionBean = f.id " +
            " where f.id = :fdivisionBean OR " +
            "      (f.shareMaterialToCompany = true and f.fcompanyBean = :fcompanyBean ) ORDER BY u.spcode")
    fun findAllByDivisionAndShareToCompany(fdivisionBean: Int, fcompanyBean: Int): List<FSalesmanEntity>
}