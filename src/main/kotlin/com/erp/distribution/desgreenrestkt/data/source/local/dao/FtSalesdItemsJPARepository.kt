package com.erp.distribution.desgreenrestkt.data.source.local.dao

import com.erp.distribution.desgreenrestkt.data.source.entity.FtSalesdItemsEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface FtSalesdItemsJPARepository : JpaRepository<FtSalesdItemsEntity, Long> {
//    override fun findById(id: Long): FtSalesdItemsEntity
    fun findByNoUrut(noUrut: String): List<FtSalesdItemsEntity>

    @Query("SELECT u FROM FtSalesdItemsEntity u WHERE u.noUrut LIKE :noUrut")
    fun findAll(noUrut: String): List<FtSalesdItemsEntity>

    @Query("SELECT u FROM FtSalesdItemsEntity u WHERE u.ftSaleshBean = :ftSaleshBean")
    fun findAllByFtSaleshBean(ftSaleshBean: Long): List<FtSalesdItemsEntity>

    @Query("SELECT u FROM FtSalesdItemsEntity u WHERE u.ftSaleshBean IN :listFtSaleshBean")
    fun findAllByListFtSalesh(listFtSaleshBean: List<Long>): List<FtSalesdItemsEntity>

    @Modifying
    @Query("delete from FtSalesdItemsEntity u where u.ftSaleshBean = :ftSaleshBean")
    fun deleteByFtSalesh(@Param("ftSaleshBean") ftSaleshBean: Long): Int
    override fun deleteInBatch(iterable: Iterable<FtSalesdItemsEntity>)
}