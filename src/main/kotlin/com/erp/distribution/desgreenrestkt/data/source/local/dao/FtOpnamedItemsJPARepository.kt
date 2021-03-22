package com.erp.distribution.desgreenrestkt.data.source.local.dao

import com.erp.distribution.desgreenrestkt.data.source.entity.FtOpnamedItems
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FtOpnamedItemsJPARepository : JpaRepository<FtOpnamedItems, Long> {
//    override fun findById(id: Long): FtOpnamedItems
    fun findByNoUrut(noUrut: String): List<FtOpnamedItems>

    @Query("SELECT u FROM FtOpnamedItems u WHERE u.noUrut LIKE :noUrut")
    fun findAll(noUrut: String): List<FtOpnamedItems>

    @Query("SELECT u FROM FtOpnamedItems u WHERE u.ftOpnamehBean = :ftOpnamehBean")
    fun findAllByParentId(ftOpnamehBean: Long): List<FtOpnamedItems>

    @Query("SELECT u FROM FtOpnamedItems u WHERE u.ftOpnamehBean IN :listFtOpnamehBean")
    fun findAllByListParentId(listFtOpnamehBean: List<Long>): List<FtOpnamedItems>
}