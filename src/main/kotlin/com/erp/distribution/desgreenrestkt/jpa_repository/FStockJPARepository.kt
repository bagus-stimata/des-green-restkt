package com.erp.distribution.desgreenrestkt.jpa_repository

import com.erp.distribution.desgreenrestkt.model.FStock
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface FStockJPARepository : JpaRepository<FStock, Int> {
//    override fun findById(id: Int): FStock

    @Query("SELECT u FROM FStock u WHERE u.fmaterialBean = :fmaterialBean")
    fun findAllBy(fmaterialBean: Int): List<FStock>

    @Query("SELECT u FROM FStock u WHERE u.fmaterialBean = :fmaterialBean and u.stockDate >= :stockDateFrom and u.stockDate <= :stockDateTo")
    fun findAllByFMaterial(fmaterialBean: Int, stockDateFrom: Date, stockDateTo: Date): List<FStock>

    @Query("SELECT u FROM FStock u WHERE u.fwarehouseBean = :fwarehouseBean and u.stockDate >= :stockDateFrom and u.stockDate <= :stockDateTo")
    fun findAllByFWarehouse(fwarehouseBean: Int, stockDateFrom: Date, stockDateTo: Date): List<FStock>

    //    @Query("SELECT u FROM FStock u WHERE u.fmaterialBean = :fmaterialBean and u.fwarehouseBean = :fwarehouseBean and u.stockDate >= :stockDateFrom and u.stockDate <= :stockDateTo" )
    //    List<FStock>  findAll(int fmaterialBean, int fwarehouseBean, Date stockDateFrom, Date stockDateTo);
    @Query("SELECT u FROM FStock u WHERE u.fmaterialBean = :fmaterialBean and u.fwarehouseBean = :fwarehouseBean and u.stockDate >= :stockDateFrom and u.stockDate <= :stockDateTo")
    fun findAll(fwarehouseBean: Int, fmaterialBean: Int, stockDateFrom: Date, stockDateTo: Date): List<FStock>
}