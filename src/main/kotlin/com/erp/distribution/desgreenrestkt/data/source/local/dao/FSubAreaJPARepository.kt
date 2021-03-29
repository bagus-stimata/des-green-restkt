package com.erp.distribution.desgreenrestkt.data.source.local.dao

import com.erp.distribution.desgreenrestkt.data.source.entity.FSubAreaEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface FSubAreaJPARepository : JpaRepository<FSubAreaEntity, Int> {
//    override fun findById(id: Int): FSubAreaEntity
    fun findByKode1(kode1: String): List<FSubAreaEntity>

    @Query("SELECT u FROM FSubAreaEntity u WHERE u.kode1 LIKE :kode1 and u.description LIKE :description")
    fun findAll(kode1: String, description: String): List<FSubAreaEntity>

    //    @Query("SELECT u FROM FSubAreaEntity u WHERE u.fdivisionBean = :fdivisionBean" )
    //    List<FSubAreaEntity>  findAllByDivision(int fdivisionBean);
    @Query("SELECT u FROM FSubAreaEntity u WHERE u.fareaBean.id = :fareaBean ")
    fun findAllByParent(fareaBean: Int): List<FSubAreaEntity>

    @Query("SELECT u FROM FSubAreaEntity u WHERE u.fareaBean IN :listFareaBean ")
    fun findAllByListParent(listFareaBean: List<Int>): List<FSubAreaEntity>
}