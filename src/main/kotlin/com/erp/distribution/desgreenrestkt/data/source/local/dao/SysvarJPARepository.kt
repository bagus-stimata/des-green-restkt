package com.erp.distribution.desgreenrestkt.data.source.local.dao

import com.erp.distribution.desgreenrestkt.data.source.entity.SysvarEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface SysvarJPARepository : JpaRepository<SysvarEntity?, String?> {
    //    SysvarEntity findById(String id);
    //    List<SysvarEntity> findBySyvarId(String sysvar);
    @Query("SELECT u FROM SysvarEntity u WHERE  u.id LIKE :sysvarId")
    fun findBySysvarId(sysvarId: String?): SysvarEntity?

    @Query("SELECT u FROM SysvarEntity u WHERE  u.id LIKE :sysvarId")
    fun findAllSysvarId(sysvarId: String?): List<SysvarEntity?>?

    @Query("SELECT u FROM SysvarEntity u WHERE  u.deskripsi LIKE :deskripsi")
    fun findAll(deskripsi: String?): List<SysvarEntity?>?

    @Query("SELECT u FROM SysvarEntity u WHERE u.fdivisionBean = :fdivisionBean")
    fun findAllByDivision(fdivisionBean: Int): List<SysvarEntity?>?
}