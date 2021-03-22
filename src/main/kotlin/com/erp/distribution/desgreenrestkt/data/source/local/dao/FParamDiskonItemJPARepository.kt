package com.erp.distribution.desgreenrestkt.data.source.local.dao

import com.erp.distribution.desgreenrestkt.data.source.entity.FParamDiskonItem
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FParamDiskonItemJPARepository : JpaRepository<FParamDiskonItem, Int> {
//    override fun findById(id: Int): FParamDiskonItem
    fun findByNoRek(noRek: String): List<FParamDiskonItem>

    @Query("SELECT u FROM FParamDiskonItem u WHERE u.noRek LIKE :noRek and u.description LIKE :description")
    fun findAll(noRek: String, description: String): List<FParamDiskonItem> //    @Query("SELECT u FROM FParamDiskonItem u WHERE u.fdivisionBean = :fdivisionBean" )
    //    List<FParamDiskonItem>  findAllByDivision(int fdivisionBean);
}