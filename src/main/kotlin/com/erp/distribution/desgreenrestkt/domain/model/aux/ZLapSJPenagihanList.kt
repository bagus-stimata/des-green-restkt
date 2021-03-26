package com.erp.distribution.desgreenrestkt.domain.model.aux

import java.util.*
import javax.persistence.*

class ZLapSJPenagihanList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    @Column(name = "GRUP1")
    var grup1 = ""

    @Column(name = "GRUP2")
    var grup2 = ""

    @Column(name = "GRUP3")
    var grup3 = ""

    @Column(name = "SJPENGIRIMANNO")
    var sjpengirimanno = ""

    @Column(name = "SJPENGIRIMANDATE")
    @Temporal(TemporalType.DATE)
    var sjpengirimandate: Date? = null

    @Column(name = "SJPENAGIHANNO")
    var sjpenagihanno = ""

    @Column(name = "SJPENAGIHANDATE")
    @Temporal(TemporalType.DATE)
    var sjpenagihandate: Date? = null

    @Column(name = "TAGIHAN_KE")
    var tagihanKe = 0

    @Column(name = "SPCODE")
    var spcode = ""

    @Column(name = "SPNAME")
    var spname = ""

    @Column(name = "CUSTNO")
    var custno = ""

    @Column(name = "CUSTNAME")
    var custname = ""

    @Column(name = "INVOICENO")
    var invoiceno = ""

    @Column(name = "INVOICEDATE")
    @Temporal(TemporalType.DATE)
    var invoicedate: Date? = null

    @Column(name = "DUEDATE")
    @Temporal(TemporalType.DATE)
    var duedate: Date? = null

    @Column(name = "TUNAIKREDIT")
    var tunaikredit = ""

    @Column(name = "PRICE1")
    var price1 = 0.0

    @Column(name = "PRICE2")
    var price2 = 0.0

    @Column(name = "PRICE3")
    var price3 = 0.0

    @Column(name = "PRICE4")
    var price4 = 0.0

    override fun toString(): String {
        return "" + id + ""
    }


}