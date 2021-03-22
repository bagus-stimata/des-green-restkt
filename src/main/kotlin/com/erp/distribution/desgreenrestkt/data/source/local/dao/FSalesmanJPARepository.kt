package com.erp.distribution.desgreenrestkt.data.source.local.dao

import com.erp.distribution.desgreenrestkt.data.source.entity.FSalesman
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FSalesmanJPARepository : JpaRepository<FSalesman, Int> {
//    override fun findById(id: Int): FSalesman
    fun findBySpcode(spcode: String): List<FSalesman>

    @Query("SELECT u FROM FSalesman u WHERE u.spcode LIKE :spcode and u.spname LIKE :spname")
    fun findAll(spcode: String, spname: String): List<FSalesman>

    @Query("SELECT u FROM FSalesman u WHERE u.fdivisionBean = :fdivisionBean")
    fun findAllByDivision(fdivisionBean: Int): List<FSalesman>

    @Query("SELECT u FROM FSalesman u " +
            " left outer join FDivision f on u.fdivisionBean = f.id " +
            " where f.fcompanyBean = :fcompanyBean ORDER BY u.spcode ")
    fun findAllByCompany(fcompanyBean: Int): List<FSalesman>

    @Query("SELECT u FROM FSalesman u " +
            " left outer join FDivision f on u.fdivisionBean = f.id " +
            " where f.id = :fdivisionBean OR " +
            "      (f.shareMaterialToCompany = true and f.fcompanyBean = :fcompanyBean ) ORDER BY u.spcode")
    fun findAllByDivisionAndShareToCompany(fdivisionBean: Int, fcompanyBean: Int): List<FSalesman>
}