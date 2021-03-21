package com.erp.distribution.desgreenrestkt.jpa_repository

import com.erp.distribution.desgreenrestkt.data.source.entity.FParamDiskonNota
import org.springframework.data.jpa.repository.JpaRepository

interface FParamDiskonNotaJPARepository : JpaRepository<FParamDiskonNota, Int> { //    FParamDiskonNota findById(int id);
    //    List<FParamDiskonNota> findByKode1(String kode1);
    //    @Query("SELECT u FROM FParamDiskonNota u WHERE u.kode1 LIKE :kode1 and u.description LIKE :description")
    //    List<FParamDiskonNota>  findAll(String kode1, String description);
    //
    //
    //    @Query("SELECT u FROM FParamDiskonNota u WHERE u.fdivisionBean = :fdivisionBean" )
    //    List<FParamDiskonNota>  findAllByDivision(int fdivisionBean);
}