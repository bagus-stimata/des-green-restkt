package com.erp.distribution.desgreenrestkt.data.source.local.dao

import com.erp.distribution.desgreenrestkt.data.source.entity.Sysvar
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface SysvarJPARepository : JpaRepository<Sysvar?, String?> {
    //    Sysvar findById(String id);
    //    List<Sysvar> findBySyvarId(String sysvar);
    @Query("SELECT u FROM Sysvar u WHERE  u.id LIKE :sysvarId")
    fun findBySysvarId(sysvarId: String?): Sysvar?

    @Query("SELECT u FROM Sysvar u WHERE  u.id LIKE :sysvarId")
    fun findAllSysvarId(sysvarId: String?): List<Sysvar?>?

    @Query("SELECT u FROM Sysvar u WHERE  u.deskripsi LIKE :deskripsi")
    fun findAll(deskripsi: String?): List<Sysvar?>?

    @Query("SELECT u FROM Sysvar u WHERE u.fdivisionBean = :fdivisionBean")
    fun findAllByDivision(fdivisionBean: Int): List<Sysvar?>?
}