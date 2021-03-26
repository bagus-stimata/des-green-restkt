package com.erp.distribution.desgreenrestkt.domain.model.aux

import java.util.*
import javax.persistence.*

class ZLapStockOpname {
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

    @Column(name = "STOCKOPNAMEDATE")
    @Temporal(TemporalType.DATE)
    var stockopnamedate: Date? = null

    //**SALDO AWAL
    @Column(name = "SALDOAWALPCS")
    var saldoAwalPcs: Int? = null

    @Column(name = "SALDOAWALBES")
    var saldoAwalBes: Int? = null

    @Column(name = "SALDOAWALSED")
    var saldoAwalSed: Int? = null

    @Column(name = "SALDOAWALKEC")
    var saldoAwalKec: Int? = null

    @Column(name = "SALDOAWALNILAIBELI")
    var saldoAwalNilaiBeli: Double? = null

    @Column(name = "SALDOAWALNILAIJUAL")
    var saldoAwalNilaiJual: Double? = null

    //**PENJUALAN(-)
    @Column(name = "PENJUALANPCS")
    var penjualanPcs: Int? = null

    @Column(name = "PENJUALANBES")
    var penjualanBes: Int? = null

    @Column(name = "PENJUALANSED")
    var penjualanSed: Int? = null

    @Column(name = "PENJUALANKEC")
    var penjualanKec: Int? = null

    @Column(name = "PENJUALANNILAIBELI")
    var penjualanNilaiBeli: Double? = null

    @Column(name = "PENJUALANNILAIJUAL")
    var penjualanNilaiJual: Double? = null

    //**RETUR PENJUALAN(+)
    @Column(name = "RETURPENJUALANPCS")
    var returPenjualanPcs: Int? = null

    @Column(name = "RETURPENJUALANBES")
    var returPenjualanBes: Int? = null

    @Column(name = "RETURPENJUALANSED")
    var returPenjualanSed: Int? = null

    @Column(name = "RETURPENJUALANKEC")
    var returPenjualanKec: Int? = null

    @Column(name = "RETURPENJUALANNILAIBELI")
    var returPenjualanNilaiBeli: Double? = null

    @Column(name = "RETURPENJUALANNILAIJUAL")
    var returPenjualanNilaiJual: Double? = null

    //**PEMBELIAN (+)
    @Column(name = "PEMBELIANPCS")
    var pembelianPcs: Int? = null

    @Column(name = "PEMBELIANBES")
    var pembelianBes: Int? = null

    @Column(name = "PEMBELIANSED")
    var pembelianSed: Int? = null

    @Column(name = "PEMBELIANKEC")
    var pembelianKec: Int? = null

    @Column(name = "PEMBELIANNILAIBELI")
    var pembelianNilaiBeli: Double? = null

    @Column(name = "PEMBELIANNILAIJUAL")
    var pembelianNilaiJual: Double? = null

    //**RETUR PEMBELIAN (-)
    @Column(name = "RETURPEMBELIANPCS")
    var returPembelianPcs: Int? = null

    @Column(name = "RETURPEMBELIANBES")
    var returPembelianBes: Int? = null

    @Column(name = "RETURPEMBELIANSED")
    var returPembelianSed: Int? = null

    @Column(name = "RETURPEMBELIANKEC")
    var returPembelianKec: Int? = null

    @Column(name = "RETURPEMBELIANNILAIBELI")
    var returPembelianNilaiBeli: Double? = null

    @Column(name = "RETURPEMBELIANNILAIJUAL")
    var returPembelianNilaiJual: Double? = null

    //**TRANSFER IN (+)
    @Column(name = "TRANSFERINPCS")
    var transferInPcs: Int? = null

    @Column(name = "TRANSFERINBES")
    var transferInBes: Int? = null

    @Column(name = "TRANSFERINSED")
    var transferInSed: Int? = null

    @Column(name = "TRANSFERINKEC")
    var transferInKec: Int? = null

    @Column(name = "TRANSFERINNILAIBELI")
    var transferInNilaiBeli: Double? = null

    @Column(name = "TRANSFERINNILAIJUAL")
    var transferInNilaiJual: Double? = null

    //**TRANSFER OUT (+)
    @Column(name = "TRANSFEROUTPCS")
    var transferOutPcs: Int? = null

    @Column(name = "TRANSFEROUTBES")
    var transferOutBes: Int? = null

    @Column(name = "TRANSFEROUTSED")
    var transferOutSed: Int? = null

    @Column(name = "TRANSFEROUTKEC")
    var transferOutKec: Int? = null

    @Column(name = "TRANSFEROUTNILAIBELI")
    var transferOutNilaiBeli: Double? = null

    @Column(name = "TRANSFEROUTNILAIJUAL")
    var transferOutNilaiJual: Double? = null

    //**PENYESUAIAN/STOCK OPNAME (+/-)
    @Column(name = "PEYESUAIANPCS")
    var penyesuaianPcs: Int? = null

    @Column(name = "PEYESUAIANBES")
    var penyesuaianBes: Int? = null

    @Column(name = "PEYESUAIANSED")
    var penyesuaianSed: Int? = null

    @Column(name = "PEYESUAIANKEC")
    var penyesuaianKec: Int? = null

    @Column(name = "PEYESUAIANNILAIBELI")
    var penyesuaianNilaiBeli: Double? = null

    @Column(name = "PEYESUAIANNILAIJUAL")
    var penyesuaianNilaiJual: Double? = null

    //**SALDO AKHIR
    @Column(name = "SALDOAKHIRPCS")
    var saldoAkhirPcs: Int? = null

    @Column(name = "SALDOAKHIRBES")
    var saldoAkhirBes: Int? = null

    @Column(name = "SALDOAKHIRSED")
    var saldoAkhirSed: Int? = null

    @Column(name = "SALDOAKHIRKEC")
    var saldoAkhirKec: Int? = null

    @Column(name = "SALDOAKHIRNILAIBELI")
    var saldoAkhirNilaiBeli: Double? = null

    @Column(name = "SALDOAKHIRNILAIJUAL")
    var saldoAkhirNilaiJual: Double? = null

    //**PENAMBAHAN**
    @Column(name = "PENAMBAHANPCS")
    var penambahanPcs: Int? = null

    @Column(name = "PENAMBAHANBES")
    var penambahanBes: Int? = null

    @Column(name = "PENAMBAHANSED")
    var penambahanSed: Int? = null

    @Column(name = "PENAMBAHANKEC")
    var penambahanKec: Int? = null

    @Column(name = "PENAMBAHANNILAIBELI")
    var penambahanNilaiBeli: Double? = null

    @Column(name = "PENAMBAHANNILAIJUAL")
    var penambahanNilaiJual: Double? = null

    //**PENGURANGAN(-)
    @Column(name = "PENGURANGANPCS")
    var penguranganPcs: Int? = null

    @Column(name = "PENGURANGANBES")
    var penguranganBes: Int? = null

    @Column(name = "PENGURANGANSED")
    var penguranganSed: Int? = null

    @Column(name = "PENGURANGANKEC")
    var penguranganKec: Int? = null

    @Column(name = "PENGURANGANNILAIBELI")
    var penguranganNilaiBeli: Double? = null

    @Column(name = "PENGURANGANNILAIJUAL")
    var penguranganNilaiJual: Double? = null

    //**QTY FISIK
    @Column(name = "FISIKPCS")
    var fisikPcs: Int? = null

    @Column(name = "FISIKBES")
    var fisikBes: Int? = null

    @Column(name = "FISIKSED")
    var fisikSed: Int? = null

    @Column(name = "FISIKKEC")
    var fisikKec: Int? = null

    //**QTY TEORI
    @Column(name = "TEORIPCS")
    var teoriPcs: Int? = null

    @Column(name = "TEORIBES")
    var teoriBes: Int? = null

    @Column(name = "TEORISED")
    var teoriSed: Int? = null

    @Column(name = "TEORIKEC")
    var teoriKec: Int? = null

    @Column(name = "AKUMULASINILAIBELI")
    var akumulasiNilaiBeli: Double? = null

    @Column(name = "AKUMULASINILAIJUAL")
    var akumulasiNilaiJual: Double? = null

    override fun toString(): String {
        return "=$id"
    }


}