package com.erp.distribution.desgreenrestkt.domain.model.aux

import java.util.*
import javax.persistence.*

class ZLapPackingList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    @Column(name = "GRUP1")
    var grup1: String? = null

    @Column(name = "GRUP2")
    var grup2: String? = null

    @Column(name = "GRUP3")
    var grup3: String? = null

    @Column(name = "PCODE")
    var pcode: String? = null

    @Column(name = "PNAME")
    var pname: String? = null

    @Column(name = "CONVFACT1")
    var convfact1: Int? = null

    @Column(name = "CONVFACT2")
    var convfact2: Int? = null

    @Column(name = "UOM1")
    var uom1: String? = null

    @Column(name = "UOM2")
    var uom2: String? = null

    @Column(name = "UOM3")
    var uom3: String? = null

    @Column(name = "SJDATE")
    @Temporal(TemporalType.DATE)
    var sjdate: Date? = null

    //**SALDO AWAL
    //**SALDO AKHIR
    @Column(name = "QTY_PCS")
    var qtyPcs: Int? = null

    @Column(name = "QTY_BES")
    var qtyBes: Int? = null

    @Column(name = "QTY_SED")
    var qtySed: Int? = null

    @Column(name = "QTY_KEC")
    var qtyKec: Int? = null

    override fun toString(): String {
        return "" + id + ""
    }


}