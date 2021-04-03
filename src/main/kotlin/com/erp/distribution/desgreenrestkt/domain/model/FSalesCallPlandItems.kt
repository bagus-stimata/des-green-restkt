package com.erp.distribution.desgreenrestkt.domain.model

import com.erp.distribution.desgreenrestkt.data.source.entity.FCustomerEntity
import com.erp.distribution.desgreenrestkt.data.source.entity.FSalesCallPlandItemsEntity
import com.erp.distribution.desgreenrestkt.data.source.entity.FSalesCallPlanhEntity
import com.erp.distribution.desgreenrestkt.presentation.model.FSalesCallPlandItemsRes
import java.io.Serializable
import javax.persistence.*

data class FSalesCallPlandItems (

    var id :Long = 0L,

    @Column(name = "NOURUT", length = 4)
    var noUrut :Int = 0,

    @ManyToOne
    @JoinColumn(name = "fsalesCallPlanhBean", referencedColumnName = "ID")
    var fsalesCallPlanhBean: FSalesCallPlanh = FSalesCallPlanh(),

    @ManyToOne
    @JoinColumn(name = "fcustomerBean", referencedColumnName = "ID")
    var fcustomerBean: FCustomer = FCustomer(),

    /**
     * 0. Daily: value -> Minggu, Senin, Selasa...
     * 1. Weekly: value -> Minggu, Senin, Selasa...
     * 2. BiWeekly: Genap, Ganjil
     * 3. Monthly: Tanggal berapa saja
     */
    var value1 :Boolean =false,
    var value2 :Boolean =false,
    var value3 :Boolean =false,
    var value4 :Boolean =false,
    var value5 :Boolean =false,
    var value6 :Boolean =false,
    var value7 :Boolean =false,

    @Transient
    var tempInt1 :Int = 0,

    @Transient
    var tempString1 :String = ""

    //	@Column(name="CREATED")
    //	@Temporal(TemporalType.TIMESTAMP)
    //	private Date created = new Date();
    //	@Column(name="MODIFIED")
    //	@Temporal(TemporalType.TIMESTAMP)
    //	private Date modified = new Date();
    //	@Column(name="MODIFIED_BY", length=20)
    //	private String modifiedBy =""; //User ID

):Serializable

internal fun FSalesCallPlandItems.toEntity(): FSalesCallPlandItemsEntity {
    return FSalesCallPlandItemsEntity(
        id = id,

        noUrut = noUrut,

        fsalesCallPlanhBean = FSalesCallPlanhEntity(fsalesCallPlanhBean.id),
        fcustomerBean = FCustomerEntity(fcustomerBean.id),

        value1 = value1,
        value2 = value2,
        value3 = value3,
        value4 = value4,
        value5 = value5,
        value6 = value6,
        value7 = value7,

        tempInt1 = tempInt1,
        tempString1 = tempString1

    )
}

internal fun FSalesCallPlandItems.toResponse(): FSalesCallPlandItemsRes {
    return FSalesCallPlandItemsRes(
        id = id,

        noUrut = noUrut,

        fsalesCallPlanhBean = fsalesCallPlanhBean.id,
        fcustomerBean = fcustomerBean.id,

        value1 = value1,
        value2 = value2,
        value3 = value3,
        value4 = value4,
        value5 = value5,
        value6 = value6,
        value7 = value7,

        tempInt1 = tempInt1,
        tempString1 = tempString1

    )
}