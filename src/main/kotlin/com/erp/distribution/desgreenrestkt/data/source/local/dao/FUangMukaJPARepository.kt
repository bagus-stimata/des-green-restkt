package com.erp.distribution.desgreenrestkt.data.source.local.dao

import com.erp.distribution.desgreenrestkt.data.source.entity.FUangMuka
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FUangMukaJPARepository : JpaRepository<FUangMuka, Long> {
//    override fun findById(id: Long): FUangMuka
    fun findByNoRek(noRek: String): List<FUangMuka>

    @Query("SELECT u FROM FUangMuka u WHERE u.noRek LIKE :noRek")
    fun findAll(noRek: String): List<FUangMuka>

    @Query("SELECT u FROM FUangMuka u WHERE u.fdivisionBean = :fdivisionBean")
    fun findAllByDivision(fdivisionBean: Int): List<FUangMuka>
}