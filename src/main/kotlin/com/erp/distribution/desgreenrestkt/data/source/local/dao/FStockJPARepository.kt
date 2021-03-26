package com.erp.distribution.desgreenrestkt.data.source.local.dao

import com.erp.distribution.desgreenrestkt.data.source.entity.FStockEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface FStockJPARepository : JpaRepository<FStockEntity, Int> {
//    override fun findById(id: Int): FStockEntity

    @Query("SELECT u FROM FStockEntity u WHERE u.fmaterialBean = :fmaterialBean")
    fun findAllBy(fmaterialBean: Int): List<FStockEntity>

    @Query("SELECT u FROM FStockEntity u WHERE u.fmaterialBean = :fmaterialBean and u.stockDate >= :stockDateFrom and u.stockDate <= :stockDateTo")
    fun findAllByFMaterial(fmaterialBean: Int, stockDateFrom: Date, stockDateTo: Date): List<FStockEntity>

    @Query("SELECT u FROM FStockEntity u WHERE u.fwarehouseBean = :fwarehouseBean and u.stockDate >= :stockDateFrom and u.stockDate <= :stockDateTo")
    fun findAllByFWarehouse(fwarehouseBean: Int, stockDateFrom: Date, stockDateTo: Date): List<FStockEntity>

    //    @Query("SELECT u FROM FStockEntity u WHERE u.fmaterialBean = :fmaterialBean and u.fwarehouseBean = :fwarehouseBean and u.stockDate >= :stockDateFrom and u.stockDate <= :stockDateTo" )
    //    List<FStockEntity>  findAll(int fmaterialBean, int fwarehouseBean, Date stockDateFrom, Date stockDateTo);
    @Query("SELECT u FROM FStockEntity u WHERE u.fmaterialBean = :fmaterialBean and u.fwarehouseBean = :fwarehouseBean and u.stockDate >= :stockDateFrom and u.stockDate <= :stockDateTo")
    fun findAll(fwarehouseBean: Int, fmaterialBean: Int, stockDateFrom: Date, stockDateTo: Date): List<FStockEntity>
}