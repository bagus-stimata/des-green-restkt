package com.erp.distribution.desgreenrestkt.data.source.local.dao

import com.erp.distribution.desgreenrestkt.data.source.entity.FtPriceAlthEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FtPriceAlthJPARepository : JpaRepository<FtPriceAlthEntity, Int> {
//    override fun findById(id: Int): FtPriceAlthEntity
//    fun findByNoRek(noRek: String): List<FtPriceAlthEntity>

    @Query("SELECT u FROM FtPriceAlthEntity u WHERE u.noRek LIKE :noRek ")
    fun findAllByNoRek(noRek: String): List<FtPriceAlthEntity>

    @Query("SELECT u FROM FtPriceAlthEntity u WHERE u.fdivisionBean = :fdivisionBean")
    fun findAllByDivision(fdivisionBean: Int): List<FtPriceAlthEntity>

    @Query("SELECT u FROM FtPriceAlthEntity u " +
            " left outer join FDivisionEntity f on u.fdivisionBean = f.id " +
            " where f.id = :fdivisionBean OR " +
            "      (f.shareMaterialToCompany = true and f.fcompanyBean = :fcompanyBean ) ")
    fun findAllByDivisionAndShareToCompany(fdivisionBean: Int, fcompanyBean: Int): List<FtPriceAlthEntity>
}