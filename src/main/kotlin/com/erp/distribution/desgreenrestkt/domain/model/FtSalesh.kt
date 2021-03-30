package com.erp.distribution.desgreenrestkt.domain.model

import com.erp.distribution.desgreenrestkt.data.source.entity.FDistributionChannelEntity
import com.erp.distribution.desgreenrestkt.data.source.entity.FtSaleshEntity
import com.erp.distribution.desgreenrestkt.domain.model.enum.*
import com.erp.distribution.desgreenrestkt.presentation.model.FtSaleshRes
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import java.io.Serializable
import java.util.*
import javax.persistence.*

@JacksonXmlRootElement
data class FtSalesh(
    var refno: Long =0L,

    /*
	 * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
	 * keperluan diantaranya:
	 * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
	 * 2. 
	 */
    @Column(name = "SOURCE_ID") //	private Long sourceID = Long.valueOf(0);
    var sourceId: Long =0L, //JPQL tidak tahu nama sourceID INGAT !!!!

    //ORDERNO=SO
    @Column(name = "ORDERNO", length = 20)
    var orderno: String ="",

    @Column(name = "VALID_ORDER")
    var validOrder : Boolean =false,

    //INVOICENO
    @Column(name = "INVOICENO", length = 20)
    var invoiceno: String ="",

    @Column(name = "PRIORITY", length = 3)
    var priority :Int =0,

    /*
	 * ignore/reject promotion rules setting
	 */
    @Column(name = "NO_PROMOTION_RULES")
    var noPromotionRules : Boolean =false,

    @Column(name = "TAX_NUMBER", length = 30)
    var taxNumber: String ="",

    @Temporal(TemporalType.DATE)
    @Column(name = "TAX_DATE")
    var taxDate :Date =Date(),

    /*
	 * SO: FROM GOOD RECEIVE
	 */
    //	@ManyToOne
    //	@JoinColumn(name="fromGoodReceiptBean", referencedColumnName="refno", nullable=true)
    //	private  FtPurchaseh fromGoodReceiptBean;
    //	@Column(name="fromGoodReceiptBean", nullable =true)
    //	private long fromGoodReceiptBean :Int =0,;
    /*
	 * FAKTUR FROM SO
	 */
    //	@ManyToOne
    //	@JoinColumn(name="fakturSOBean", referencedColumnName="refno")
    //	private  FtSalesh fakturSOBean; //me as Sales Invoice
    @Column(name = "fakturSOBean", nullable =true)
    var fakturSOBean: Long? =0,

    //	@ManyToOne
    //	@JoinColumn(name="returAtasFakturBean", referencedColumnName="refno", nullable=true)
    //	private  FtSalesh returAtasFakturBean;
    //	@Column(name="returAtasFakturBean", nullable =true)
    //	private long returAtasFakturBean :Int =0,;
    /*
	 * Status Nota: (1)O-Open Sedang dikirim, (2)T-Terkirim,
	 * 		(3)P-Pending Pengiriman, (4)B-Batal Nota Batal Seluruhnya
	 */
    @Column(name = "STATUS_PENGIRIMAN")
    @Enumerated(EnumType.ORDINAL)
    var statusPengiriman: EnumStatusPengiriman = EnumStatusPengiriman.NOTA_OPEN,

    //SURAT JALAN PENGIRIMAIN = DO
    @Column(name = "SJ_PENGIRIMAN_NO", length = 15)
    var sjPengirimanNo: String ="",

    @Column(name = "SJ_PENGIRIMAN_DATE")
    @Temporal(TemporalType.DATE)
    var sjPengirimanDate :Date =Date(), //Jika tidak ada nomor SJ maka tidak berlaku

    //Driver
    //	@Column(name="DRIVER_NAME", length=40)
    //	private String driverName="";
    //	@ManyToOne
    //	@JoinColumn(name="driverBean", referencedColumnName="ID", nullable=true)
    //	private FSalesman driverBean;
    @Column(name = "driverBean")
    var driverBean: Int? =0,

    @Column(name = "NOPOL", length = 30)
    var nopol: String ="",

    /*
	 * HARUS DIGANTI MENGGUNAKAN LIST
	 */
    @Column(name = "SJ_PENAGIHAN_NO", length = 15)
    var sjPenagihanNo: String ="",

    @Column(name = "SJ_PENAGIHAN_DATE")
    @Temporal(TemporalType.DATE)
    var sjPenagihanDate :Date =Date(),

    //	@ManyToOne
    //	@JoinColumn(name="collectorBean", referencedColumnName="ID", nullable=true)
    //	private FSalesman collectorBean;
    @Column(name = "collectorBean", nullable =true)
    var collectorBean: Int? =0,

    @Temporal(TemporalType.DATE)
    @Column(name = "INVOICE_DATE")
    var invoiceDate :Date =Date(),

    @Temporal(TemporalType.DATE)
    @Column(name = "ORDER_DATE")
    var orderDate :Date =Date(),

    @Column(name = "TOP")
    var top :Int =0,

    @Temporal(TemporalType.DATE)
    @Column(name = "DUE_DATE")
    var dueDate :Date =Date(),

    /*
	 * SEPERTINYA KITA TIDAK PAKAI INI
	 * SALDO AWAL ITU ada pada tipe transaksi
	 */
    //	@Column(name="SALDO")
    //	private boolean saldo=false;
    /*
	 * AMOUNT: Amount Setelah Disc1, Disc2, Disc3, +Disc1, +Disc2 pada DETIL (Amount Detil Setelah dipotong Diskon)
	 */
    @Column(name = "AMOUNT")
    var amountRp :Double =0.0,

    @Column(name = "AMOUNT_PPN_RP")
    var amountPpnRp :Double =0.0,

    @Transient
    var amountRpAfterPpn :Double =0.0,

    /*
	 * berhubungan dengan Account -> menjadi apa
	 * 	Kas (kas Masuk) pada
	 * 		Uang Muka Penjulaan
	 * Dengan Giro/Transfer, dan jika kosong maka Artinya Cash
	 * Asumsi: Uang muka adalah sekali
	 */
    //	@Column(name="UANG_MUKA_RP")
    //	private Double uangMukaRp=0.0;
    //	@ManyToOne
    //	@JoinColumn(name="fgiroBean", referencedColumnName="ID", nullable=true)
    //	private FGiro fgiroBean;
    /*
	 * ATAS TIDAK DIPAKAI LAGI
	 * Untuk SO saja
	 */
    //	@ManyToOne
    //	@JoinColumn(name="fuangMuka_SOBean", referencedColumnName="ID", nullable=true)
    //	private FUangMuka fuangMuka_SOBean;
    @Column(name = "fuangMuka_SOBean", nullable = true)
    var fuangMuka_SOBean: Int? =0,

    @Column(name = "DISC1")
    var disc1 :Double =0.0,

    @Transient
    var disc1Rp :Double =0.0,

    @Transient
    var disc1PpnRp :Double =0.0,

    @Transient
    var disc1RpAfterPpn :Double =0.0,

    //###TAMBAHAN
    @Transient
    var amountAfterDisc1Rp :Double =0.0,

    @Transient
    var amountAfterDisc1PpnRp :Double =0.0,

    @Transient
    var amountAfterDisc1RpAfterPpn :Double =0.0,

    @Column(name = "DISC2")
    var disc2 :Double =0.0,

    @Transient
    var disc2Rp :Double =0.0,

    @Transient
    var disc2PpnRp :Double =0.0,

    @Transient
    var disc2RpAfterPpn :Double =0.0,

    //AMOUNT AFTER DISC1 dan DISC2 dan DiscPlus
    @Transient
    var amountAfterDisc2Rp :Double =0.0,

    @Transient
    var amountAfterDisc2PpnRp :Double =0.0,

    @Transient
    var amountAfterDisc2RpAfterPpn :Double =0.0,

    @Column(name = "DISC")
    var discPlus_FG :Double =0.0,

    @Transient
    var discPlusRp_FG :Double =0.0,

    @Transient
    var discPlusPpnRp_FG :Double =0.0,

    @Transient
    var discPlusRpAfterPpn_FG :Double =0.0,

    ///Jika yes maka setiap FG yang harganya nol maka akan di hitung akumulasinya, lalu nilainya ditaruh di CashBack
    @Column(name = "CAL_CASHBACK_FG")
    var calcCashBackFg : Boolean =false,

    /*
	 * Sama dengan bawah: Jangan Lupa
	 */
    //DPP
    @Column(name = "AMOUNT_AFTER_DISC_PLUS") //MEMANG BERBEDA DENGAN NAMA TABEL
    var amountAfterDiscPlusRp_FG :Double =0.0,

    @Column(name = "PPN_RP")
    var ppnRp :Double =0.0,

    //DPP+PPN
    @Column(name = "AMOUNT_AFTER_DISC_PLUS_AFTERPPN") //MEMANG BERBEDA DENGAN NAMA TABEL
    var amountAfterDiscPlusRpAfterPpn_FG :Double =0.0,

    //AMOUNT PAY
    @Column(name = "AMOUNT_PAY_RP")
    var amountPayRp :Double =0.0,

    @Column(name = "END_OF_DAY")
    var endOfDay : Boolean =false,

    @Column(name = "OPEN_LOCK_PRICE_DISKON")
    var openLockInputPriceAndDiscount : Boolean =false,

    /*
	 * REQUEST PLAFON
	 */
    @Column(name = "STATUS_REQUEST_DISCOUNT")
    @Enumerated(EnumType.ORDINAL)
    var statusRequestDiscount: EnumRequestStatus = EnumRequestStatus.OPEN,

    @Column(name = "STATUS_REQUEST_PLAFON")
    @Enumerated(EnumType.ORDINAL)
    var statusRequestPlafon: EnumRequestStatus = EnumRequestStatus.OPEN,

    @Column(name = "NOTES", length = 160)
    var notes: String ="",

    @Column(length = 5, name = "TUNAI_KREDIT")
    @Enumerated(EnumType.STRING)
    var tunaiKredit: EnumTunaiKredit = EnumTunaiKredit.T,

    /* TIPE FAKTUR
	 * F=FAKTUR PENJUALAN STANDART, R= RETURN PENJULAN, FI=PENJUALAN INTERN,
	 * FDN= DEBIT NOTE MANUAL, RCN=RETUR CREDIT NOTE MANUAL
	 * */
    @Column(length = 5, name = "TIPE_FAKTUR")
    @Enumerated(EnumType.STRING)
    var tipeFaktur: EnumTipeFakturJual? = EnumTipeFakturJual.F,

    /* TIPE JUAL
	 * SHOP=SHOPSALE, TO=TAKING ORDER, C=CANVAS, TF=TASK FORCE, D=DENTED, BS=BAD STOCK
	 * */
    @Column(length = 5, name = "SALES_TYPE")
    var salesType: EnumSalesType? = EnumSalesType.TO,

    @Column(name = "PRINT_COUNTER", length = 4)
    var printCounter :Int =0,

    @Column(name = "PROSES")
    var proses : Boolean =false,

    @Column(name = "USED_SO")
    var usedSO : Boolean =false,

    //1.Cash 2.Debit 3.Kartu Kredit 4.Cek 5.E-Wallet 6.Lain-lain
    var tipeBayarPos :Int =0,

    @Column(name = "AMOUNT_KASIR_BAYAR")
    var amountKasirBayar :Double =0.0,

    //	@ManyToOne
    //	@JoinColumn(name="fdivisionBean", referencedColumnName="ID", nullable=false)
    //	private FDivision fdivisionBean;
    @Column(name = "fdivisionBean", nullable = false)
    var fdivisionBean :Int =0,

    //	@ManyToOne
    //	@JoinColumn(name="fsalesmanBean", referencedColumnName="ID", nullable=false)
    //	private FSalesman fsalesmanBean;
    @Column(name = "fsalesmanBean", nullable = false)
    var fsalesmanBean :Int =0,

    /*
	 *	fcustomerBean = Bill To adalah yang melakuan Order Pertama kali
	 *	fcustomerShipToBean = adalah tempat pengiriman barang. jika null maka Shipto adalah BillTo
	 *	fcustomerPromoToBean = adalah melakukan Switch Pengalihan Promo
	 */
    //	@ManyToOne
    //	@JoinColumn(name="fcustomerBean", referencedColumnName="ID", nullable=false)
    //	private FCustomer fcustomerBean;
    @Column(name = "fcustomerBean", nullable = false)
    var fcustomerBean :Int =0,

    //Allow Null
    //	@ManyToOne
    //	@JoinColumn(name="fcustomerShipToBean", referencedColumnName="ID", nullable=true)
    //	private FCustomer fcustomerShipToBean;
    @Column(name = "fcustomerShipToBean")
    var fcustomerShipToBean: Int? =0,

    //Allow Null
    //	@ManyToOne
    //	@JoinColumn(name="fcustomerPromoToBean", referencedColumnName="ID", nullable=true)
    //	private FCustomer fcustomerPromoToBean;
    //	@Column(name="fcustomerPromoToBean")
    //	private Integer fcustomerPromoToBean :Int =0,;
    //	@ManyToOne
    //	@JoinColumn(name="fwarehouseBean", referencedColumnName="ID", nullable=false)
    //	private FWarehouse fwarehouseBean;
    @Column(name = "fwarehouseBean", nullable = false)
    var fwarehouseBean :Int =0,

    /*
	 * Account Mapping
	 */
    //	@ManyToOne
    //	@JoinColumn(name="accAccountArKbBean", referencedColumnName="ID", nullable =true)
    //	private AccAccount accAccountArKbBean;
    @Column(name = "accAccountArKbBean", nullable =true)
    var accAccountArKbBean: Int? =0,

    //	@ManyToOne
    //	@JoinColumn(name="accAccountFtSaleshCredit", referencedColumnName="ID", nullable =true)
    //	private AccAccount accAccountFtSaleshCredit;
    @Column(name = "accAccountFtSaleshCredit", nullable =true)
    var accAccountFtSaleshCredit: Int? =0,

    /**
     * Seharusnya menggunakan interface Class Sendiri
     */
    @Transient
    var listFtsalesdItems: List<FtSalesdItems> = ArrayList(),

    //PEGIRIMAN:
    //	@ManyToOne
    //	@JoinColumn(name="fexpedisiBean", referencedColumnName="ID", nullable=true)
    //	private FExpedisi fexpedisiBean;
    @Column(name = "fexpedisiBean", nullable =true)
    var fexpedisiBean: Int? =null,

    /*
	 * MAPPING ACCOUNT
	 * Tidak Berubah Ubah
	 * 1. Tax Account: ikut Mappint Sistem Divisi
	 * 2. Persediaan: Tidak berubah ubah karena defaultnya per-divisi perbarang
	 * 3. COGS/HPP: tidak berubah-ubah karena defaultnya per-divisi perbarang
	 * Bisa di ubah-ubah
	 * 1. Kas Besar
	 * 2. Piutang
	 */
    @Column(name = "CREATED")
    @Temporal(TemporalType.TIMESTAMP)
    var created :Date =Date(),

    @Column(name = "MODIFIED")
    @Temporal(TemporalType.TIMESTAMP)
    var modified :Date =Date(),

    @Column(name = "MODIFIED_BY", length = 20)
    var modifiedBy: String ="" //User ID

): Serializable

internal fun FtSalesh.toEntity(): FtSaleshEntity {
    return FtSaleshEntity(
        refno = refno,
        sourceId = sourceId,

        orderno = orderno,
        validOrder = validOrder,
        invoiceno = invoiceno,
        priority = priority,
        noPromotionRules = noPromotionRules,
        taxNumber = taxNumber,
        taxDate = taxDate,
        fakturSOBean = fakturSOBean,
        statusPengiriman = statusPengiriman,
        sjPengirimanNo = sjPengirimanNo,
        sjPengirimanDate = sjPengirimanDate,
        driverBean = driverBean,
        nopol = nopol,
        sjPenagihanNo = sjPenagihanNo,
        sjPenagihanDate = sjPenagihanDate,
        collectorBean = collectorBean,
        invoiceDate = invoiceDate,
        orderDate = orderDate,
        top = top,
        dueDate = dueDate,
        amountRp = amountRp,
        amountPpnRp = amountPpnRp,
        amountRpAfterPpn = amountRpAfterPpn,
        fuangMuka_SOBean = fuangMuka_SOBean,
        disc1 = disc1,
        disc1Rp = disc1Rp,
        disc1PpnRp = disc1PpnRp,
        disc1RpAfterPpn = disc1RpAfterPpn,
        amountAfterDisc1Rp = amountAfterDisc1Rp,
        amountAfterDisc1PpnRp = amountAfterDisc1PpnRp,
        amountAfterDisc1RpAfterPpn = amountAfterDisc1RpAfterPpn,
        disc2 = disc2,
        disc2Rp = disc2Rp,
        disc2PpnRp = disc2PpnRp,
        disc2RpAfterPpn = disc2RpAfterPpn,
        amountAfterDisc2Rp = amountAfterDisc2Rp,
        amountAfterDisc2PpnRp = amountAfterDisc2PpnRp,
        amountAfterDisc2RpAfterPpn = amountAfterDisc2RpAfterPpn,
        discPlus_FG = discPlus_FG,
        discPlusRp_FG = discPlusRp_FG,
        discPlusPpnRp_FG = discPlusPpnRp_FG,
        discPlusRpAfterPpn_FG = discPlusRpAfterPpn_FG,
        calcCashBackFg = calcCashBackFg,

        amountAfterDiscPlusRp_FG = amountAfterDiscPlusRp_FG,
        ppnRp = ppnRp,
        amountAfterDiscPlusRpAfterPpn_FG = amountAfterDiscPlusRpAfterPpn_FG,
        amountPayRp = amountPayRp,
        endOfDay = endOfDay,
        openLockInputPriceAndDiscount = openLockInputPriceAndDiscount,
        statusRequestDiscount = statusRequestDiscount,
        statusRequestPlafon = statusRequestPlafon,
        notes = notes,
        tunaiKredit = tunaiKredit,
        tipeFaktur = tipeFaktur,
        salesType = salesType,
        printCounter = printCounter,
        proses = proses,
        usedSO = usedSO,
        tipeBayarPos = tipeBayarPos,
        amountKasirBayar = amountKasirBayar,

        fdivisionBean = fdivisionBean,
        fsalesmanBean = fsalesmanBean,
        fcustomerBean = fcustomerBean,
        fcustomerShipToBean = fcustomerShipToBean,
        fwarehouseBean = fwarehouseBean,
        accAccountArKbBean = accAccountArKbBean,
        accAccountFtSaleshCredit = accAccountFtSaleshCredit,
//        listFtsalesdItems = listFtsalesdItems,
        fexpedisiBean = fexpedisiBean,

        created = created,
        modified = modified,
        modifiedBy = modifiedBy
    )
}


internal fun FtSalesh.toResponse(): FtSaleshRes {
    return FtSaleshRes(
        refno = refno,
        sourceId = sourceId,

        orderno = orderno,
        validOrder = validOrder,
        invoiceno = invoiceno,
        priority = priority,
        noPromotionRules = noPromotionRules,
        taxNumber = taxNumber,
        taxDate = taxDate,
        fakturSOBean = fakturSOBean,
        statusPengiriman = statusPengiriman,
        sjPengirimanNo = sjPengirimanNo,
        sjPengirimanDate = sjPengirimanDate,
        driverBean = driverBean,
        nopol = nopol,
        sjPenagihanNo = sjPenagihanNo,
        sjPenagihanDate = sjPenagihanDate,
        collectorBean = collectorBean,
        invoiceDate = invoiceDate,
        orderDate = orderDate,
        top = top,
        dueDate = dueDate,
        amountRp = amountRp,
        amountPpnRp = amountPpnRp,
        amountRpAfterPpn = amountRpAfterPpn,
        fuangMuka_SOBean = fuangMuka_SOBean,
        disc1 = disc1,
        disc1Rp = disc1Rp,
        disc1PpnRp = disc1PpnRp,
        disc1RpAfterPpn = disc1RpAfterPpn,
        amountAfterDisc1Rp = amountAfterDisc1Rp,
        amountAfterDisc1PpnRp = amountAfterDisc1PpnRp,
        amountAfterDisc1RpAfterPpn = amountAfterDisc1RpAfterPpn,
        disc2 = disc2,
        disc2Rp = disc2Rp,
        disc2PpnRp = disc2PpnRp,
        disc2RpAfterPpn = disc2RpAfterPpn,
        amountAfterDisc2Rp = amountAfterDisc2Rp,
        amountAfterDisc2PpnRp = amountAfterDisc2PpnRp,
        amountAfterDisc2RpAfterPpn = amountAfterDisc2RpAfterPpn,
        discPlus_FG = discPlus_FG,
        discPlusRp_FG = discPlusRp_FG,
        discPlusPpnRp_FG = discPlusPpnRp_FG,
        discPlusRpAfterPpn_FG = discPlusRpAfterPpn_FG,
        calcCashBackFg = calcCashBackFg,

        amountAfterDiscPlusRp_FG = amountAfterDiscPlusRp_FG,
        ppnRp = ppnRp,
        amountAfterDiscPlusRpAfterPpn_FG = amountAfterDiscPlusRpAfterPpn_FG,
        amountPayRp = amountPayRp,
        endOfDay = endOfDay,
        openLockInputPriceAndDiscount = openLockInputPriceAndDiscount,
        statusRequestDiscount = statusRequestDiscount,
        statusRequestPlafon = statusRequestPlafon,
        notes = notes,
        tunaiKredit = tunaiKredit,
        tipeFaktur = tipeFaktur,
        salesType = salesType,
        printCounter = printCounter,
        proses = proses,
        usedSO = usedSO,
        tipeBayarPos = tipeBayarPos,
        amountKasirBayar = amountKasirBayar,

        fdivisionBean = fdivisionBean,
        fsalesmanBean = fsalesmanBean,
        fcustomerBean = fcustomerBean,
        fcustomerShipToBean = fcustomerShipToBean,
        fwarehouseBean = fwarehouseBean,
        accAccountArKbBean = accAccountArKbBean,
        accAccountFtSaleshCredit = accAccountFtSaleshCredit,
//        listFtsalesdItems = listFtsalesdItems,
        fexpedisiBean = fexpedisiBean,

        created = created,
        modified = modified,
        modifiedBy = modifiedBy
    )
}

