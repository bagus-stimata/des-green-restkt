package com.erp.distribution.desgreenrestkt.data.source.local.dao

import com.erp.distribution.desgreenrestkt.data.source.entity.FSalesTargetToCustEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FSalesTargetToCustJPARepository : JpaRepository<FSalesTargetToCustEntity, Long> {

    @Query("SELECT u FROM FSalesTargetToCustEntity u WHERE u.fsalesmanBean.id = :fsalesmanBean")
    fun findAllBySalesman(fsalesmanBean: Int): List<FSalesTargetToCustEntity>

    @Query("SELECT u FROM FSalesTargetToCustEntity u WHERE u.fdivisionBean = :fdivisionBean")
    fun findAllByDivision(fdivisionBean: Int): List<FSalesTargetToCustEntity>

    @Query("SELECT u FROM FSalesTargetToCustEntity u " +
            " left outer join FDivisionEntity f on u.fdivisionBean = f.id " +
            " where f.id = :fdivisionBean OR " +
            "      (f.shareMaterialToCompany = true and f.fcompanyBean = :fcompanyBean ) ")
    fun findAllByDivisionAndShareToCompany(fdivisionBean: Int, fcompanyBean: Int): List<FSalesTargetToCustEntity>
}