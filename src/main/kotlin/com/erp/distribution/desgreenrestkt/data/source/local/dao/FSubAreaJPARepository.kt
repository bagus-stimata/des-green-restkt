package com.erp.distribution.desgreenrestkt.data.source.local.dao

import com.erp.distribution.desgreenrestkt.data.source.entity.FSubArea
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FSubAreaJPARepository : JpaRepository<FSubArea, Int> {
//    override fun findById(id: Int): FSubArea
    fun findByKode1(kode1: String): List<FSubArea>

    @Query("SELECT u FROM FSubArea u WHERE u.kode1 LIKE :kode1 and u.description LIKE :description")
    fun findAll(kode1: String, description: String): List<FSubArea>

    //    @Query("SELECT u FROM FSubArea u WHERE u.fdivisionBean = :fdivisionBean" )
    //    List<FSubArea>  findAllByDivision(int fdivisionBean);
    @Query("SELECT u FROM FSubArea u WHERE u.fareaBean = :fareaBean ")
    fun findAllByParent(fareaBean: Int): List<FSubArea>

    @Query("SELECT u FROM FSubArea u WHERE u.fareaBean IN :listFareaBean ")
    fun findAllByListParent(listFareaBean: List<Int>): List<FSubArea>
}