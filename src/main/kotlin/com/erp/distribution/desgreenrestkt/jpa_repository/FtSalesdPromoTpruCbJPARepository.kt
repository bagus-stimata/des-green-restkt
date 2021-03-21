package com.erp.distribution.desgreenrestkt.jpa_repository

import com.erp.distribution.desgreenrestkt.model.FtSalesdPromoTpruCb
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FtSalesdPromoTpruCbJPARepository : JpaRepository<FtSalesdPromoTpruCb, Long> {
//    override fun findById(id: Long): FtSalesdPromoTpruCb
    fun findByNoUrut(noUrut: String): List<FtSalesdPromoTpruCb>

    @Query("SELECT u FROM FtSalesdPromoTpruCb u WHERE u.noUrut LIKE :noUrut")
    fun findAll(noUrut: String): List<FtSalesdPromoTpruCb>

    @Query("SELECT u FROM FtSalesdPromoTpruCb u WHERE u.ftSalesdFreegood = :ftSalesdFreegood")
    fun findAllByParentId(ftSalesdFreegood: Long): List<FtSalesdPromoTpruCb>
}