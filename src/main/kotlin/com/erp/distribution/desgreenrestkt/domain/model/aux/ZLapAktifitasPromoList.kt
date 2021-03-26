package com.erp.distribution.desgreenrestkt.domain.model.aux

import java.util.*
import javax.persistence.*

class ZLapAktifitasPromoList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    @Column(name = "GRUP1")
    var grup1: String? = null

    @Column(name = "GRUP2")
    var grup2: String? = null

    @Column(name = "GRUP3")
    var grup3: String? = null

    @Column(name = "PROMOID")
    var promoid: String? = null

    @Column(name = "PROMODESC")
    var promodesc: String? = null

    @Column(name = "INVOICENO")
    var invoiceno: String? = null

    @Column(name = "INVOICEDATE")
    @Temporal(TemporalType.DATE)
    var invoicedate: Date? = null

    @Column(name = "CUSTAREA")
    var custarea: String? = null

    @Column(name = "CUSTSUBAREA")
    var custsubarea: String? = null

    @Column(name = "CUSTGROUP")
    var custgroup: String? = null

    @Column(name = "CUSTSUBGROUP")
    var custsubgroup: String? = null

    @Column(name = "CUSTNO")
    var custno: String? = null

    @Column(name = "CUSTNAME")
    var custname: String? = null

    @Column(name = "ADDRESS")
    var address: String? = null

    @Column(name = "CITY")
    var city: String? = null

    //NILAI NOTA
    @Column(name = "SUBTOTALAFTERDISCAFTERPPN")
    var subtotalafterdiscafterppn: Double? = null

    //PROMO BARANG
    @Column(name = "fREEBONUSPCODE")
    var freebonuspcode: String? = null

    @Column(name = "fREEBONUSPNAME")
    var freebonuspname: String? = null

    @Column(name = "fREEBONUSQTYPCS")
    var freebonusqtypcs: Int? = null

    @Column(name = "fREEBONUSQTYBES")
    var freebonusqtybes: Int? = null

    @Column(name = "fREEBONUSQTYSED")
    var freebonusqtysed: Int? = null

    @Column(name = "fREEBONUSQTYKEC")
    var freebonusqtykec: Int? = null

    @Column(name = "fREEBONUSAFTERPPN")
    var freebonusafterppn: Double? = null

    //PROMO DISC
    @Column(name = "DISC1")
    var disc1: Double? = null

    @Column(name = "DISC2")
    var disc2: Double? = null

    @Column(name = "DISC1AFTERPPN")
    var disc1afterppn: Double? = null

    @Column(name = "DISC2AFTERPPN")
    var disc2afterppn: Double? = null

    //PROMO CASHBACK
    @Column(name = "CASHBACKAFTERPPN")
    var cashbackafterppn: Double? = null


    override fun toString(): String {
        return "" + id + ""
    }
}