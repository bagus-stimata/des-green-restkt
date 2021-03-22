package com.erp.distribution.desgreenrestkt.data.source.local.dao

import com.erp.distribution.desgreenrestkt.data.source.entity.FtArPaymenth
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FtArPaymenthJPARepository : JpaRepository<FtArPaymenth, Long> {
//    override fun findById(id: Long): FtArPaymenth
    fun findByNoRek(noRek: String): List<FtArPaymenth>

    @Query("SELECT u FROM FtArPaymenth u WHERE u.noRek LIKE :noRek")
    fun findAll(noRek: String): List<FtArPaymenth>

    @Query("SELECT u FROM FtArPaymenth u WHERE u.fdivisionBean = :fdivisionBean")
    fun findAllByDivision(fdivisionBean: Int): List<FtArPaymenth>
}