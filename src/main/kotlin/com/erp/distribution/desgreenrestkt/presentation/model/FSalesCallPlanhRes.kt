package com.erp.distribution.desgreenrestkt.presentation.model

import com.erp.distribution.desgreenrestkt.domain.model.FDivision
import com.erp.distribution.desgreenrestkt.domain.model.FSalesCallPlanh
import com.erp.distribution.desgreenrestkt.domain.model.FSalesman
import com.erp.distribution.desgreenrestkt.domain.model.enum.EnumTipeCallPlan
import java.io.Serializable
import java.util.*
import javax.persistence.*

data class FSalesCallPlanhRes (

    var id :Long = 0L,

    @Column(name = "KODE1", length = 15)
    var kode1 :String = "",

    @Column(name = "DESCRIPTION", length = 100)
    var description :String = "",

    @Transient
    var tempString1 :String = "",
    /**
     * Dikunjungi pada minggu ke berapa saja
     */
    /**
     * 0. Daily:
     * 1. Weekly:
     * 2. BiWeekly:
     * 3. Monthly:
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "TIPE_CALLPLAN")
    var tipeCallPlan: EnumTipeCallPlan = EnumTipeCallPlan.WEEKLY,

    /**
     * 0. Daily:
     * 1. Weekly:
     * 2. BiWeekly: Genap, Ganjil
     * 3. Monthly:
     */
    var param1 :Int = 0,

//    @ManyToOne
//    @JoinColumn(name = "fdivisionBean", referencedColumnName = "ID")
//    var fdivisionBean: FDivision = FDivision(),
    var fdivisionBean: Int = 0,

//    @ManyToOne
//    @JoinColumn(name = "fsalesmanBean", referencedColumnName = "ID")
//    var fsalesmanBean: FSalesman = FSalesman(),
    var fsalesmanBean: Int = 0,

//    @OneToMany(mappedBy = "fsalesCallPlanhBean", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
//    @Fetch(FetchMode.JOIN)
//    var fSalesCallPlandItemsSet: Set<FSalesCallPlandItems> = HashSet<FSalesCallPlandItems>(),

    @Column(name = "STATUS_ACTIVE")
    var statusActive :Boolean = true,

    @Column(name = "CREATED")
    @Temporal(TemporalType.TIMESTAMP)
    var created :Date = Date(),

    @Column(name = "MODIFIED")
    @Temporal(TemporalType.TIMESTAMP)
    var modified :Date = Date(),

    @Column(name = "MODIFIED_BY", length = 20)
    var modifiedBy :String = "" //User ID

): Serializable

internal fun FSalesCallPlanhRes.toDomain(): FSalesCallPlanh {
    return FSalesCallPlanh(
        id = id,

        kode1 = kode1,
        description = description,
        tempString1 = tempString1,
        tipeCallPlan = tipeCallPlan,
        param1 = param1,

        fdivisionBean = FDivision(fdivisionBean) ,
        fsalesmanBean = FSalesman(fsalesmanBean),

        statusActive = statusActive,

//        fsubareaSet = fsubareaSet,

        created = created,
        modified = modified,
        modifiedBy = modifiedBy
    )
}