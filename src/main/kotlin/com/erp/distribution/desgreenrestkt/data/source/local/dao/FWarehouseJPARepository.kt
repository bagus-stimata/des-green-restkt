package com.erp.distribution.desgreenrestkt.data.source.local.dao

import com.erp.distribution.desgreenrestkt.data.source.entity.FWarehouseEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FWarehouseJPARepository : JpaRepository<FWarehouseEntity, Int> {
//    override fun findById(id: Int): FWarehouseEntity
    fun findByKode1(kode1: String): List<FWarehouseEntity>

    @Query("SELECT u FROM FWarehouseEntity u WHERE u.kode1 LIKE :kode1 and u.description LIKE :description")
    fun findAll(kode1: String, description: String): List<FWarehouseEntity>

    @Query("SELECT u FROM FWarehouseEntity u WHERE u.fdivisionBean = :fdivisionBean")
    fun findAllByDivision(fdivisionBean: Int): List<FWarehouseEntity>

    @Query("SELECT u FROM FWarehouseEntity u " +
            " left outer join FDivisionEntity f on u.fdivisionBean = f.id " +
            " where f.fcompanyBean = :fcompanyBean ORDER BY u.kode1")
    fun findAllByCompany(fcompanyBean: Int): List<FWarehouseEntity>

    @Query("SELECT u FROM FWarehouseEntity u " +
            " left outer join FDivisionEntity f on u.fdivisionBean = f.id " +
            " where f.id = :fdivisionBean OR " +
            "      (f.shareMaterialToCompany = true and f.fcompanyBean = :fcompanyBean ) ORDER BY u.kode1")
    fun findAllByDivisionAndShareToCompany(fdivisionBean: Int, fcompanyBean: Int): List<FWarehouseEntity>
}