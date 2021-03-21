package com.erp.distribution.desgreenrestkt.jpa_repository

import com.erp.distribution.desgreenrestkt.model.FDistributionChannel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FDistributionChannelJPARepository : JpaRepository<FDistributionChannel, Int> {
//    override fun findById(id: Int): FDistributionChannel?
    fun findByKode1(kode1: String?): List<FDistributionChannel>

    @Query("SELECT u FROM FDistributionChannel u WHERE u.kode1 LIKE :kode1 and u.description LIKE :description")
    fun findAll(kode1: String?, description: String?): List<FDistributionChannel>

    @Query("SELECT u FROM FDistributionChannel u WHERE u.fdivisionBean = :fdivisionBean")
    fun findAllByDivision(fdivisionBean: Int): List<FDistributionChannel>

    @Query("SELECT u FROM FDistributionChannel u " +
            " left outer join FDivision f on u.fdivisionBean = f.id " +
            " where f.id = :fdivisionBean OR " +
            "      (f.shareMaterialToCompany = true and f.fcompanyBean = :fcompanyBean ) ")
    fun findAllByDivisionAndShareToCompany(fdivisionBean: Int, fcompanyBean: Int): List<FDistributionChannel>
}