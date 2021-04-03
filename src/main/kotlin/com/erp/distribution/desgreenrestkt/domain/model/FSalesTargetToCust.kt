package com.erp.distribution.desgreenrestkt.domain.model

import com.erp.distribution.desgreenrestkt.data.source.entity.FCustomerEntity
import com.erp.distribution.desgreenrestkt.data.source.entity.FDivisionEntity
import com.erp.distribution.desgreenrestkt.data.source.entity.FSalesTargetToCustEntity
import com.erp.distribution.desgreenrestkt.data.source.entity.FSalesmanEntity
import com.erp.distribution.desgreenrestkt.presentation.model.FAreaRes
import com.erp.distribution.desgreenrestkt.presentation.model.FSalesTargetToCustRes
import java.io.Serializable
import java.util.*
import javax.persistence.*

data class FSalesTargetToCust (

    var id :Long = 0L,

    /**
     * Target and Target Plan
     */
    @Column(name = "TARGET_SALES_AMOUNT")
    var targetSalesAmount :Double = 0.0,

//    @ManyToOne
//    @JoinColumn(name = "fdivisionBean", referencedColumnName = "ID")
    var fdivisionBean: FDivision = FDivision(),

    var fsalesmanBean: FSalesman = FSalesman(),

//    @ManyToOne
//    @JoinColumn(name = "fcustomerBean", referencedColumnName = "ID")
    var fcustomerBean: FCustomer = FCustomer(),

    @Column(name = "STATUS_ACTIVE")
    var statusActive :Boolean = true,

    //	 
    //	@OneToMany(mappedBy="fareaBean", fetch=FetchType.LAZY)
    //	@Fetch(FetchMode.JOIN)
    //	private Set<FSubArea> fsubareaSet;
    //	 
    //	@OneToMany(mappedBy="validAreaBean", fetch=FetchType.LAZY)
    //	@Fetch(FetchMode.JOIN)
    //	private Set<FPromotionRulesdValidCusts> fpromotionRulesdValidCustsSet;
    @Column(name = "CREATED")
    @Temporal(TemporalType.TIMESTAMP)
    var created :Date = Date(),

    @Column(name = "MODIFIED")
    @Temporal(TemporalType.TIMESTAMP)
    var modified :Date = Date(),

    @Column(name = "MODIFIED_BY", length = 20)
    var modifiedBy  :String= "" //User ID

): Serializable

internal fun FSalesTargetToCust.toEntity(): FSalesTargetToCustEntity {
    return FSalesTargetToCustEntity(
        id = id,
        targetSalesAmount = targetSalesAmount,

        fdivisionBean = fdivisionBean.id,
        fsalesmanBean = FSalesmanEntity(fsalesmanBean.id),
        fcustomerBean = FCustomerEntity(fcustomerBean.id),

        statusActive = statusActive,

//        fsubareaSet = fsubareaSet,

        created = created,
        modified = modified,
        modifiedBy = modifiedBy
    )
}

internal fun FSalesTargetToCust.toResponse(): FSalesTargetToCustRes {
    return FSalesTargetToCustRes(
        id = id,
        targetSalesAmount = targetSalesAmount,

        fdivisionBean = fdivisionBean.id ,
        fsalesmanBean = fsalesmanBean.id,
        fcustomerBean = fcustomerBean.id,

        statusActive = statusActive,

//        fsubareaSet = fsubareaSet,

        created = created,
        modified = modified,
        modifiedBy = modifiedBy
    )
}