package com.erp.distribution.desgreenrestkt.data.source.local.dao

import com.erp.distribution.desgreenrestkt.data.source.entity.FtApPaymenth
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FtApPaymenthJPARepository : JpaRepository<FtApPaymenth, Long> {
//    override fun findById(id: Long): FtApPaymenth
    fun findByNoRek(noRek: String): List<FtApPaymenth>

    @Query("SELECT u FROM FtApPaymenth u WHERE u.noRek LIKE :noRek")
    fun findAll(noRek: String): List<FtApPaymenth>

    @Query("SELECT u FROM FtApPaymenth u WHERE u.fdivisionBean = :fdivisionBean")
    fun findAllByDivision(fdivisionBean: Int): List<FtApPaymenth>
}