package com.erp.distribution.desgreenrestkt.jpa_repository

import com.erp.distribution.desgreenrestkt.data.source.entity.FWarehouse
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FWarehouseJPARepository : JpaRepository<FWarehouse, Int> {
//    override fun findById(id: Int): FWarehouse
    fun findByKode1(kode1: String): List<FWarehouse>

    @Query("SELECT u FROM FWarehouse u WHERE u.kode1 LIKE :kode1 and u.description LIKE :description")
    fun findAll(kode1: String, description: String): List<FWarehouse>

    @Query("SELECT u FROM FWarehouse u WHERE u.fdivisionBean = :fdivisionBean")
    fun findAllByDivision(fdivisionBean: Int): List<FWarehouse>

    @Query("SELECT u FROM FWarehouse u " +
            " left outer join FDivision f on u.fdivisionBean = f.id " +
            " where f.fcompanyBean = :fcompanyBean ORDER BY u.kode1")
    fun findAllByCompany(fcompanyBean: Int): List<FWarehouse>

    @Query("SELECT u FROM FWarehouse u " +
            " left outer join FDivision f on u.fdivisionBean = f.id " +
            " where f.id = :fdivisionBean OR " +
            "      (f.shareMaterialToCompany = true and f.fcompanyBean = :fcompanyBean ) ORDER BY u.kode1")
    fun findAllByDivisionAndShareToCompany(fdivisionBean: Int, fcompanyBean: Int): List<FWarehouse>
}