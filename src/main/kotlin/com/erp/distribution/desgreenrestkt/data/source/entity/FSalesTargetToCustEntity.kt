package com.erp.distribution.desgreenrestkt.data.source.entity

import com.erp.distribution.desgreenrestkt.domain.model.FCustomer
import com.erp.distribution.desgreenrestkt.domain.model.FDivision
import com.erp.distribution.desgreenrestkt.domain.model.FSalesTargetToCust
import com.erp.distribution.desgreenrestkt.domain.model.FSalesman
import java.io.Serializable
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "fsales_targettocust")
data class FSalesTargetToCustEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(
        name = "hibernate_sequence",
        sequenceName = "hibernate_sequence_master",
        allocationSize = 20,
        initialValue = 912345668
    )
    @Column(name = "ID")
    var id :Long = 0L,

    /**
     * Target and Target Plan
     */
    @Column(name = "TARGET_SALES_AMOUNT")
    var targetSalesAmount :Double = 0.0,

//    @ManyToOne
//    @JoinColumn(name = "fdivisionBean", referencedColumnName = "ID")
    var fdivisionBean: Int = 0,

    @ManyToOne
    @JoinColumn(name = "fsalesmanBean", referencedColumnName = "ID")
    var fsalesmanBean: FSalesmanEntity = FSalesmanEntity(),

    @ManyToOne
    @JoinColumn(name = "fcustomerBean", referencedColumnName = "ID")
    var fcustomerBean: FCustomerEntity = FCustomerEntity(),

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

internal fun FSalesTargetToCustEntity.toDomain(): FSalesTargetToCust {
    return FSalesTargetToCust(
        id = id,
        targetSalesAmount = targetSalesAmount,

        fdivisionBean = FDivision(fdivisionBean) ,
        fsalesmanBean = FSalesman(fsalesmanBean.id),
        fcustomerBean = FCustomer(fcustomerBean.id),

        statusActive = statusActive,

//        fsubareaSet = fsubareaSet,

        created = created,
        modified = modified,
        modifiedBy = modifiedBy
    )
}