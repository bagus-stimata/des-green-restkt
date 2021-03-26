package com.erp.distribution.desgreenrestkt.data.source.local.dao

import com.erp.distribution.desgreenrestkt.data.source.entity.FtSalesdPromoTpruCbEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FtSalesdPromoTpruCbJPARepository : JpaRepository<FtSalesdPromoTpruCbEntity, Long> {
//    override fun findById(id: Long): FtSalesdPromoTpruCbEntity
    fun findByNoUrut(noUrut: String): List<FtSalesdPromoTpruCbEntity>

    @Query("SELECT u FROM FtSalesdPromoTpruCbEntity u WHERE u.noUrut LIKE :noUrut")
    fun findAll(noUrut: String): List<FtSalesdPromoTpruCbEntity>

    @Query("SELECT u FROM FtSalesdPromoTpruCbEntity u WHERE u.ftSalesdFreegood = :ftSalesdFreegood")
    fun findAllByParentId(ftSalesdFreegood: Long): List<FtSalesdPromoTpruCbEntity>
}