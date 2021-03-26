package com.erp.distribution.desgreenrestkt.data.source.local.dao

import com.erp.distribution.desgreenrestkt.data.source.entity.FtOpnamedItemsEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FtOpnamedItemsJPARepository : JpaRepository<FtOpnamedItemsEntity, Long> {
//    override fun findById(id: Long): FtOpnamedItemsEntity
    fun findByNoUrut(noUrut: String): List<FtOpnamedItemsEntity>

    @Query("SELECT u FROM FtOpnamedItemsEntity u WHERE u.noUrut LIKE :noUrut")
    fun findAll(noUrut: String): List<FtOpnamedItemsEntity>

    @Query("SELECT u FROM FtOpnamedItemsEntity u WHERE u.ftOpnamehBean = :ftOpnamehBean")
    fun findAllByParentId(ftOpnamehBean: Long): List<FtOpnamedItemsEntity>

    @Query("SELECT u FROM FtOpnamedItemsEntity u WHERE u.ftOpnamehBean IN :listFtOpnamehBean")
    fun findAllByListParentId(listFtOpnamehBean: List<Long>): List<FtOpnamedItemsEntity>
}