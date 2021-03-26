package com.erp.distribution.desgreenrestkt.data.source.local.dao

import com.erp.distribution.desgreenrestkt.data.source.entity.FUangMukaEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FUangMukaJPARepository : JpaRepository<FUangMukaEntity, Long> {
//    override fun findById(id: Long): FUangMukaEntity
    fun findByNoRek(noRek: String): List<FUangMukaEntity>

    @Query("SELECT u FROM FUangMukaEntity u WHERE u.noRek LIKE :noRek")
    fun findAll(noRek: String): List<FUangMukaEntity>

    @Query("SELECT u FROM FUangMukaEntity u WHERE u.fdivisionBean = :fdivisionBean")
    fun findAllByDivision(fdivisionBean: Int): List<FUangMukaEntity>
}