package com.erp.distribution.desgreenrestkt.data.source.local.dao

import com.erp.distribution.desgreenrestkt.data.source.entity.FtSalesdPromoTpruDiscEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FtSalesdPromoTpruDiscJPARepository : JpaRepository<FtSalesdPromoTpruDiscEntity, Long> {
//    override fun findById(id: Long): FtSalesdPromoTpruDiscEntity
    fun findByNoUrut(noUrut: String): List<FtSalesdPromoTpruDiscEntity>

    @Query("SELECT u FROM FtSalesdPromoTpruDiscEntity u WHERE u.noUrut LIKE :noUrut")
    fun findAll(noUrut: String): List<FtSalesdPromoTpruDiscEntity>

    @Query("SELECT u FROM FtSalesdPromoTpruDiscEntity u WHERE u.ftSalesdBean = :ftSalesdBean")
    fun findAllByParentId(ftSalesdBean: Long): List<FtSalesdPromoTpruDiscEntity>
}