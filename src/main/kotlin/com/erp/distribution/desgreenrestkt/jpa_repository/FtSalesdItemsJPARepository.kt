package com.erp.distribution.desgreenrestkt.jpa_repository

import com.erp.distribution.desgreenrestkt.model.FtSalesdItems
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface FtSalesdItemsJPARepository : JpaRepository<FtSalesdItems, Long> {
//    override fun findById(id: Long): FtSalesdItems
    fun findByNoUrut(noUrut: String): List<FtSalesdItems>

    @Query("SELECT u FROM FtSalesdItems u WHERE u.noUrut LIKE :noUrut")
    fun findAll(noUrut: String): List<FtSalesdItems>

    @Query("SELECT u FROM FtSalesdItems u WHERE u.ftSaleshBean = :ftSaleshBean")
    fun findAllByFtSaleshBean(ftSaleshBean: Long): List<FtSalesdItems>

    @Query("SELECT u FROM FtSalesdItems u WHERE u.ftSaleshBean IN :listFtSaleshBean")
    fun findAllByListFtSalesh(listFtSaleshBean: List<Long>): List<FtSalesdItems>

    @Modifying
    @Query("delete from FtSalesdItems u where u.ftSaleshBean = :ftSaleshBean")
    fun deleteByFtSalesh(@Param("ftSaleshBean") ftSaleshBean: Long): Int
    override fun deleteInBatch(iterable: Iterable<FtSalesdItems>)
}