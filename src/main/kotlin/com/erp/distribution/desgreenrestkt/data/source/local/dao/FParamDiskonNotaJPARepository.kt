package com.erp.distribution.desgreenrestkt.data.source.local.dao

import com.erp.distribution.desgreenrestkt.data.source.entity.FParamDiskonNotaEntity
import org.springframework.data.jpa.repository.JpaRepository

interface FParamDiskonNotaJPARepository : JpaRepository<FParamDiskonNotaEntity, Int> { //    FParamDiskonNotaEntity findById(int id);

    //    List<FParamDiskonNotaEntity> findByKode1(String kode1);
    //    @Query("SELECT u FROM FParamDiskonNotaEntity u WHERE u.kode1 LIKE :kode1 and u.description LIKE :description")
    //    List<FParamDiskonNotaEntity>  findAll(String kode1, String description);
    //
    //
    //    @Query("SELECT u FROM FParamDiskonNotaEntity u WHERE u.fdivisionBean = :fdivisionBean" )
    //    List<FParamDiskonNotaEntity>  findAllByDivision(int fdivisionBean);

}