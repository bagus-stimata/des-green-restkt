package com.erp.distribution.desgreenrestkt.data.source.local.dao

import com.erp.distribution.desgreenrestkt.data.source.entity.FtSalesdPromoTpruDisc
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FtSalesdPromoTpruDiscJPARepository : JpaRepository<FtSalesdPromoTpruDisc, Long> {
//    override fun findById(id: Long): FtSalesdPromoTpruDisc
    fun findByNoUrut(noUrut: String): List<FtSalesdPromoTpruDisc>

    @Query("SELECT u FROM FtSalesdPromoTpruDisc u WHERE u.noUrut LIKE :noUrut")
    fun findAll(noUrut: String): List<FtSalesdPromoTpruDisc>

    @Query("SELECT u FROM FtSalesdPromoTpruDisc u WHERE u.ftSalesdBean = :ftSalesdBean")
    fun findAllByParentId(ftSalesdBean: Long): List<FtSalesdPromoTpruDisc>
}