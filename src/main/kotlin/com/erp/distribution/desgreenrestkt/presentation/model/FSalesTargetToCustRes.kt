package com.erp.distribution.desgreenrestkt.presentation.model

import com.erp.distribution.desgreenrestkt.data.source.entity.FCustomerEntity
import com.erp.distribution.desgreenrestkt.data.source.entity.FDivisionEntity
import com.erp.distribution.desgreenrestkt.data.source.entity.FSalesTargetToCustEntity
import com.erp.distribution.desgreenrestkt.domain.model.FCustomer
import com.erp.distribution.desgreenrestkt.domain.model.FDivision
import com.erp.distribution.desgreenrestkt.domain.model.FSalesTargetToCust
import com.erp.distribution.desgreenrestkt.domain.model.FSalesman
import com.erp.distribution.desgreenrestkt.presentation.model.FAreaRes
import java.io.Serializable
import java.util.*
import javax.persistence.*

data class FSalesTargetToCustRes (

    var id :Long = 0L,

    /**
     * Target and Target Plan
     */
    @Column(name = "TARGET_SALES_AMOUNT")
    var targetSalesAmount :Double = 0.0,

//    @ManyToOne
//    @JoinColumn(name = "fdivisionBean", referencedColumnName = "ID")
//    var fdivisionBean: FDivision = FDivision(),
    var fdivisionBean: Int = 0,

    var fsalesmanBean: Int = 0,

//    @ManyToOne
//    @JoinColumn(name = "fcustomerBean", referencedColumnName = "ID")
//    var fcustomerBean: FCustomer = FCustomer(),
    var fcustomerBean: Int = 0,

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

internal fun FSalesTargetToCustRes.toEntity(): FSalesTargetToCust {
    return FSalesTargetToCust(
        id = id,
        targetSalesAmount = targetSalesAmount,

        fdivisionBean = FDivision(fdivisionBean) ,
        fsalesmanBean = FSalesman(fsalesmanBean),
        fcustomerBean = FCustomer(fcustomerBean),

        statusActive = statusActive,

//        fsubareaSet = fsubareaSet,

        created = created,
        modified = modified,
        modifiedBy = modifiedBy
    )
}