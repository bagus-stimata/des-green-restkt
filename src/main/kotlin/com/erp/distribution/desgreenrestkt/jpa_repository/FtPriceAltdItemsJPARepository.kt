package com.erp.distribution.desgreenrestkt.jpa_repository

import com.erp.distribution.desgreenrestkt.data.source.entity.FtPriceAltdItems
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FtPriceAltdItemsJPARepository : JpaRepository<FtPriceAltdItems, Int> {
//    override fun findById(id: Int): FtPriceAltdItems
    fun findByNoUrut(noUrut: String): List<FtPriceAltdItems>

    @Query("SELECT u FROM FtPriceAltdItems u WHERE u.noUrut LIKE :noUrut")
    fun findAll(noUrut: String): List<FtPriceAltdItems>

    @Query("SELECT u FROM FtPriceAltdItems u WHERE u.ftPriceAlthBean = :ftPriceAlthBean")
    fun findAllByParent(ftPriceAlthBean: Int): List<FtPriceAltdItems>

    @Query("SELECT u FROM FtPriceAltdItems u WHERE u.ftPriceAlthBean IN :listFtPriceAlthBean")
    fun findAllByListParent(listFtPriceAlthBean: List<Int>): List<FtPriceAltdItems>
}