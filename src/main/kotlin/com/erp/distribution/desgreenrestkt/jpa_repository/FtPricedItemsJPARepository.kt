package com.erp.distribution.desgreenrestkt.jpa_repository

import com.erp.distribution.desgreenrestkt.model.FtPricedItems
import org.springframework.data.jpa.repository.JpaRepository

interface FtPricedItemsJPARepository : JpaRepository<FtPricedItems, Int> {
//    override fun findById(id: Int): FtPricedItems //    List<FtPricedItems> findByNoRek(String noRek);
    //
    //    @Query("SELECT u FROM FtPriceh u WHERE u.noRek LIKE :noRek")
    //    List<FtPricedItems>  findAll(String noRek);
    //    @Query("SELECT u FROM FtPricedItems u WHERE u.ftPricehBean = :ftPricehBean" )
    //    List<FtPricedItems>  findAllByParentId(Long ftPricehBean);
}