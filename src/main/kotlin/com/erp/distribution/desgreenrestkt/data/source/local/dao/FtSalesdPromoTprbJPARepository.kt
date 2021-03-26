package com.erp.distribution.desgreenrestkt.data.source.local.dao

import com.erp.distribution.desgreenrestkt.data.source.entity.FtSalesdPromoTprbEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FtSalesdPromoTprbJPARepository : JpaRepository<FtSalesdPromoTprbEntity, Long> {
//    override fun findById(id: Long): FtSalesdPromoTprbEntity
    fun findByNoUrut(noUrut: String): List<FtSalesdPromoTprbEntity>

    @Query("SELECT u FROM FtSalesdPromoTprbEntity u WHERE u.noUrut LIKE :noUrut")
    fun findAll(noUrut: String): List<FtSalesdPromoTprbEntity>

    @Query("SELECT u FROM FtSalesdPromoTprbEntity u WHERE u.ftSalesdFreegood = :ftSalesdFreegood")
    fun findAllByParentId(ftSalesdFreegood: Long): List<FtSalesdPromoTprbEntity>
}