package com.erp.distribution.desgreenrestkt.data.source.local.dao

import com.erp.distribution.desgreenrestkt.data.source.entity.FDistributionChannelEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FDistributionChannelJPARepository : JpaRepository<FDistributionChannelEntity, Int> {
//    override fun findById(id: Int): FDistributionChannelEntity?
    fun findByKode1(kode1: String?): List<FDistributionChannelEntity>

    @Query("SELECT u FROM FDistributionChannelEntity u WHERE u.kode1 LIKE :kode1 and u.description LIKE :description")
    fun findAll(kode1: String, description: String): List<FDistributionChannelEntity>

    @Query("SELECT u FROM FDistributionChannelEntity u WHERE u.fdivisionBean = :fdivisionBean")
    fun findAllByDivision(fdivisionBean: Int): List<FDistributionChannelEntity>

    @Query("SELECT u FROM FDistributionChannelEntity u " +
            " left outer join FDivisionEntity f on u.fdivisionBean = f.id " +
            " where f.id = :fdivisionBean OR " +
            "      (f.shareMaterialToCompany = true and f.fcompanyBean = :fcompanyBean ) ")
    fun findAllByDivisionAndShareToCompany(fdivisionBean: Int, fcompanyBean: Int): List<FDistributionChannelEntity>
}