package com.erp.distribution.desgreenrestkt.data.source.local.dao

import com.erp.distribution.desgreenrestkt.data.source.entity.FtPriceAltdItemsEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FtPriceAltdItemsJPARepository : JpaRepository<FtPriceAltdItemsEntity, Int> {
//    override fun findById(id: Int): FtPriceAltdItemsEntity
    fun findByNoUrut(noUrut: String): List<FtPriceAltdItemsEntity>

    @Query("SELECT u FROM FtPriceAltdItemsEntity u WHERE u.noUrut LIKE :noUrut")
    fun findAll(noUrut: String): List<FtPriceAltdItemsEntity>

    @Query("SELECT u FROM FtPriceAltdItemsEntity u WHERE u.ftPriceAlthBean = :ftPriceAlthBean")
    fun findAllByParent(ftPriceAlthBean: Int): List<FtPriceAltdItemsEntity>

    @Query("SELECT u FROM FtPriceAltdItemsEntity u WHERE u.ftPriceAlthBean IN :listFtPriceAlthBean")
    fun findAllByListParent(listFtPriceAlthBean: List<Int>): List<FtPriceAltdItemsEntity>
}