package com.erp.distribution.desgreenrestkt.jpa_repository

import com.erp.distribution.desgreenrestkt.model.FtPriceAlth
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FtPriceAlthJPARepository : JpaRepository<FtPriceAlth, Int> {
//    override fun findById(id: Int): FtPriceAlth
    fun findByNoRek(noRek: String): List<FtPriceAlth>

    @Query("SELECT u FROM FtPriceAlth u WHERE u.noRek LIKE :noRek ")
    fun findAll(noRek: String): List<FtPriceAlth>

    @Query("SELECT u FROM FtPriceAlth u WHERE u.fdivisionBean = :fdivisionBean")
    fun findAllByDivision(fdivisionBean: Int): List<FtPriceAlth>

    @Query("SELECT u FROM FtPriceAlth u " +
            " left outer join FDivision f on u.fdivisionBean = f.id " +
            " where f.id = :fdivisionBean OR " +
            "      (f.shareMaterialToCompany = true and f.fcompanyBean = :fcompanyBean ) ")
    fun findAllByDivisionAndShareToCompany(fdivisionBean: Int, fcompanyBean: Int): List<FtPriceAlth>
}