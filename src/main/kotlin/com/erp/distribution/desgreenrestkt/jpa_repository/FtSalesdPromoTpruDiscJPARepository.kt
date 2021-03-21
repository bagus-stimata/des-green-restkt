package com.erp.distribution.desgreenrestkt.jpa_repository

import com.erp.distribution.desgreenrestkt.model.FtSalesdPromoTpruDisc
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