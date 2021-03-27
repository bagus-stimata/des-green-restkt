package com.erp.distribution.desgreenrestkt.data.source.entity_acc_cb

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import java.io.Serializable
import java.util.*
import javax.persistence.*

@JacksonXmlRootElement
@Entity
@Table(name = "acc_balancehpp")
class AccBalanceHppEntity : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID")
    private val id: Long = 0

    /*
	 * KESALAHAN DESIGN AWAL, KARENA PENGGUNAAN DIVISI
	 * DIVISI ADALAH DUMMY, JADI ABAIKAN
	 * DI ISI DUMMY
	 * 
	 * KEPUTUSAN UNTUK MEMAKAI DIVISI
	 * dengan Syarat
	 * Jika Divisi Adalah
	 * 
	 * diffCompanyAccount = true;
	 * 
	 */
    //	@ManyToOne
    //	@JoinColumn(name="fdivisionBean", referencedColumnName="ID")
    //	private FDivision fdivisionBean;
    @Column(name = "fdivisionBean", nullable = false)
    private val fdivisionBean = 0

    /*
	 * KALAU INI HITUGANNYA PER Akun(cuma Cogs) per Periode, setiap hari: ???
	 * Hitung balance dari COGS pakai jurnal, jadi tidak bisa pakai ini
	 */
    //	@ManyToOne
    //	@JoinColumn(name="accBalanceBean", referencedColumnName="ID")
    //	private AccBalance accBalanceBean;
    //##karena HPP maka mengambil dari PRODUCT
    //	@ManyToOne
    //	@JoinColumn(name="fmaterialBean", referencedColumnName="ID")
    //	private FMaterial fmaterialBean;
    @Column(name = "fmaterialBean", nullable = false)
    private val fmaterialBean = 0

    /*
	 * HARI YANG DIPAKAI JUGA PADA HPP
	 * HPP/COGS adalah harga barang Net SEBELUM PPN
	 */
    @Column(name = "HPP_DATE")
    @Temporal(TemporalType.DATE)
    private val hppDate: Date? = null

    /*
	 * Qty Cuma Pembantu:
	 * Qty Diambil dari Qty pada Saldo Stok Real pada Perushaan Tersebut
	 */
    @Column(name = "OPENINGBALANCE_QTY")
    private val openingBalanceQty = 0

    @Column(name = "OPENINGBALANCE_TOTAMOUNT")
    private val openingBalanceTotAmount = 0.0

    //	@Column(name="OPENINGBALANCE_HPP")
    //	private Double openingBalanceHPP=0.0;
    @Transient
    private val purchaseQty = 0

    @Transient
    private val purchasePrice = 0.0

    @Transient
    private val purchaseTotAmount = 0.0

    @Transient
    private val soldQty = 0

    @Transient
    private val soldPrice = 0.0

    @Transient
    private val soldTotAmount = 0.0

    /*
	 * Qty Cuma Pembantu:
	 * Qty Diambil dari Qty pada Saldo Stok Real pada Perushaan Tersebut
	 */
    @Column(name = "CLOSINGBALANCE_QTY")
    private val closingBalanceQty = 0

    @Column(name = "CLOSINGBALANCE_TOTAMOUNT")
    private val closingBalanceTotAmount = 0.0

    //	@Column(name="CLOSINGBALANCE_HPP")
    //	private Double closingBalanceHPP=0.0;
    /*
	 * SALDO AKAN MENGGUNAKAN INI
	 */
    @Column(name = "OPENING_BALANCE_HPP_AVERAGE")
    private val openingBalanceHppAverage = 0.0

    @Column(name = "OPENING_BALANCE_HPP_FIFO")
    private val openingBalanceHppFifo = 0.0

    @Column(name = "OPENING_BALANCE_HPP_LIFO")
    private val openingBalanceHppLifo = 0.0

    @Column(name = "CLOSING_BALANCE_HPP_AVERAGE")
    private val closingBalanceHppAverage = 0.0

    @Column(name = "CLOSING_BALANCE_HPP_FIFO")
    private val closingBalanceHppFifo = 0.0

    @Column(name = "CLOSING_BALANCE_HPP_LIFO")
    private val closingBalanceHppLifo = 0.0
}