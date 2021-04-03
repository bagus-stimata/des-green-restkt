package com.erp.distribution.desgreenrestkt.data.source.entity

import com.erp.distribution.desgreenrestkt.domain.model.FCustomer
import com.erp.distribution.desgreenrestkt.domain.model.FSalesCallPlandItems
import com.erp.distribution.desgreenrestkt.domain.model.FSalesCallPlanh
import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "fsales_callpland_items")
data class FSalesCallPlandItemsEntity (

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(
        name = "hibernate_sequence",
        sequenceName = "hibernate_sequence_header",
        allocationSize = 60,
        initialValue = 912345668
    )
    @Column(name = "ID")
    var id :Long = 0L,

    @Column(name = "NOURUT", length = 4)
    var noUrut :Int = 0,

    @ManyToOne
    @JoinColumn(name = "fsalesCallPlanhBean", referencedColumnName = "ID")
    var fsalesCallPlanhBean: FSalesCallPlanhEntity = FSalesCallPlanhEntity(),

    @ManyToOne
    @JoinColumn(name = "fcustomerBean", referencedColumnName = "ID")
    var fcustomerBean: FCustomerEntity = FCustomerEntity(),

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

internal fun FSalesCallPlandItemsEntity.toDomain(): FSalesCallPlandItems {
    return FSalesCallPlandItems(
        id = id,

        noUrut = noUrut,

        fsalesCallPlanhBean = FSalesCallPlanh(fsalesCallPlanhBean.id),
        fcustomerBean = FCustomer(fcustomerBean.id),

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