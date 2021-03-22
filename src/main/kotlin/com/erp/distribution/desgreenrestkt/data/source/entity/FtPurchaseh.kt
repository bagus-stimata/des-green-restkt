package com.erp.distribution.desgreenrestkt.data.source.entity

import com.erp.distribution.desgreenrestkt.domain.model.enum.EnumRequestStatus
import com.erp.distribution.desgreenrestkt.domain.model.enum.EnumStatusUpload
import com.erp.distribution.desgreenrestkt.domain.model.enum.EnumTipeFakturBeli
import com.erp.distribution.desgreenrestkt.domain.model.enum.EnumTunaiKredit
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import java.io.Serializable
import java.util.*
import javax.persistence.*

@JacksonXmlRootElement
@Entity
@Table(name = "ftpurchaseh")
class FtPurchaseh : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "REFNO")
    var refno: Long = 0

    /*
	 * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
	 * keperluan diantaranya:
	 * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
	 * 2. 
	 */
    @Column(name = "SOURCE_ID")
    var sourceId: Long = 0

    /*
	 * TRANSAKSI BISA DIMULAI DARI PURCHASE INVOICE ATAU PO
	 */
    @Column(name = "NOPO", length = 20)
    var nopo = ""

    @Column(name = "INVOICENO", length = 30)
    var invoiceno = ""

    @Temporal(TemporalType.DATE)
    @Column(name = "PO_DATE")
    var poDate = Date()

    @Column(name = "LAMA_CREDIT", length = 4)
    var lamaCredit = 0

    @Column(name = "REQUEST_STATUS")
    @Enumerated(EnumType.ORDINAL)
    var requestStatus = EnumRequestStatus.OPEN

    /*
	 * TANGGAL BARANG DITERIMA: yang akan menjadi STOK dan HPP(Accounting)
	 */
    @Column(name = "DELIVERY_NO", length = 30)
    var goodReceiptNo = ""

    @Temporal(TemporalType.DATE)
    @Column(name = "GOODRECEIPT_DATE")
    var goodReceiptDate = Date()

    /*
	 * Tanggal Invoice yang akan menjadi 
	 * 1. Account Payable
	 * 2. dan Menjadi Tanggal pada Faktur Pajak
	 */
    @Temporal(TemporalType.DATE)
    @Column(name = "INVOICE_DATE")
    var invoiceDate = Date()

    @Temporal(TemporalType.DATE)
    @Column(name = "DUE_DATE")
    var dueDate = Date()

    /*
	 * Posting Date atau Journal Date dipakai untuk alternatif tanggal posting
	 * Sesuai dengan referensi SAP
	 * 
	 */
    @Temporal(TemporalType.DATE)
    @Column(name = "POSTING_DATE")
    var postingDate = Date()

    @Column(name = "TAX_NUMBER", length = 30)
    var taxNumber = ""

    /*
	 * Tax Date adalah tanggal Faktur Pajak Masuk Jurnal
	 */
    @Temporal(TemporalType.DATE)
    @Column(name = "TAX_DATE")
    var taxDate = Date()

    /*
	 * Defaultnya adalah mengikuti nomor invoice
	 * Tapi bisa diubah-ubah sesuai bulan upload faktur pajak
	 */
    @Column(name = "MASA_PAJAK")
    var masaPajak = 0

    @Column(name = "STATUS_UPLOAD")
    @Enumerated(EnumType.ORDINAL)
    var statusUpload = EnumStatusUpload.OPEN

    //	@Column(name = "RETUR_ATAS_FAKTUR", length = 30)
    //	private String returAtasFaktur ="";
    /*
	 * DIBAYAR TUNAI ATAU SESUAI DENGAN AKUNNYA
	 * PADA LAPORAN HUTANG: Akan dianggal Sudah Lunas dan dilewati
	 */
    @Column(name = "RTR_DIBAYAR_TUNAI")
    var isRtrDibayarTunai = false

    /*
	 * FAKTUR FROM PO
	 */
    //	@ManyToOne
    //	@JoinColumn(name="fakturPOBean", referencedColumnName="refno", nullable = true)
    //	private  FtPurchaseh fakturPOBean; //me as good receipt
    @Column(name = "fakturPOBean")
    var fakturPOBean: Long = 0

    /*
	 * FAKTUR FROM GR
	 */
    //	@ManyToOne
    //	@JoinColumn(name="fakturGRBean", referencedColumnName="refno", nullable = true)
    //	private  FtPurchaseh fakturGRBean; //me as good receipt
    @Column(name = "fakturGRBean")
    var fakturGRBean: Long = 0

    //	@ManyToOne
    //	@JoinColumn(name="returAtasFakturBean", referencedColumnName="refno", nullable = true)
    //	private  FtPurchaseh returAtasFakturBean; //me as Retur
    @Column(name = "returAtasFakturBean")
    var returAtasFakturBean: Long = 0

    //RETUR ATAS FAKTUR	
    @Column(length = 5, name = "TUNAI_KREDIT")
    @Enumerated(EnumType.STRING)
    var tunaiKredit = EnumTunaiKredit.T

    /* TIPE FAKTUR
	 * F=FAKTUR PEMBELIAN STANDART, R= RETURN PEMBELIAN DARI PABRIK, 
	 * FI=PEMBELIAN INTERN, INFG=BARANG DATANG FREE GOOD
	 * PO=PEMBELIAN to Factory
	 * */
    @Column(length = 5, name = "TIPE_FAKTUR", nullable = false)
    @Enumerated(EnumType.STRING)
    var tipeFaktur: EnumTipeFakturBeli? = null

    /*
	 * AMOUNT: Amount Setelah Disc1, Disc2, Disc3, +Disc1, +Disc2 pada DETIL (Amount Detil Setelah dipotong Diskon) 
	 */
    @Column(name = "AMOUNT")
    var amountRp = 0.0

    @Column(name = "AMOUNT_PPN_RP")
    var amountPpnRp = 0.0

    @Transient
    var amountRpAfterPpn = 0.0

    /*
	 * berhubungan dengan Account -> menjadi apa
	 * 	Uang Muka Pembelian pada
	 * 		Kas (kas keluar)
	 * Dengan Giro/Transfer, dan jika kosong maka Artinya Cash
	 * Asumsi: Uang muka adalah sekali
	 */
    @Column(name = "UANG_MUKA_RP")
    var uangMukaRp = 0.0

    //	@ManyToOne
    //	@JoinColumn(name="fgiroBean", referencedColumnName="ID", nullable=true)
    //	private FGiro fgiroBean;
    @Column(name = "fgiroBean")
    var fgiroBean = 0

    /*
	 * ATAS TIDAK DIPAKAI LAGI
	 * Untuk PO saja
	 */
    //	@ManyToOne
    //	@JoinColumn(name="fuangMuka_POBean", referencedColumnName="ID", nullable=true)
    //	private FUangMuka fuangMuka_POBean;
    @Column(name = "fuangMuka_POBean")
    var fuangMuka_POBean = 0

    @Column(name = "DISC1")
    var disc1 = 0.0

    @Transient
    var disc1Rp = 0.0

    @Transient
    var disc1PpnRp = 0.0

    @Transient
    var disc1RpAfterPpn = 0.0

    //###TAMBAHAN
    @Transient
    var amountAfterDisc1Rp = 0.0

    @Transient
    var amountAfterDisc1PpnRp = 0.0

    @Transient
    var amountAfterDisc1RpAfterPpn = 0.0

    @Column(name = "DISC2")
    var disc2 = 0.0

    @Transient
    var disc2Rp = 0.0

    @Transient
    var disc2PpnRp = 0.0

    @Transient
    var disc2RpAfterPpn = 0.0

    //AMOUNT AFTER DISC1 dan DISC2 dan DiscPlus
    @Transient
    var amountAfterDisc2Rp = 0.0

    @Transient
    var amountAfterDisc2PpnRp = 0.0

    @Transient
    var amountAfterDisc2RpAfterPpn = 0.0

    @Column(name = "DISC")
    var discPlus_FG = 0.0

    @Transient
    var discPlusRp_FG = 0.0

    @Transient
    var discPlusPpnRp_FG = 0.0

    @Transient
    var discPlusRpAfterPpn_FG = 0.0

    /*
	 * Sama dengan bawah: Jangan Lupa
	 */
    //DPP: Pindah Bawah
    //	@Column(name="AMOUNT_AFTER_DISC_PLUS")
    //	private Double amountAfterDiscPlusRp_FG=0.0;
    /*
	 * TIDAK ADA YANG PAKAI SEMENTARA KITA NON AKTIFKAN
	 */
    @Transient
    var amountAfterDiscPlusFgRp = 0.0

    @Transient
    var amountAfterDiscPlusFgPpnRp = 0.0

    @Transient
    var amountAfterDiscPlusFgRpAfterPpn = 0.0

    //======== TAMBAHAN ===========
    @Column(name = "DISC_EXCL_COGS")
    var discExclCogs = 0.0

    @Transient
    var discExclCogsRp = 0.0

    @Transient
    var discExclCogsPpnRp = 0.0

    @Transient
    var discExclCogsRpAfterPpn = 0.0

    /*
	 * PINDAHAN DARI ATAS: DAN TERPAKASA NAMANYA SEPERTI INI
	 * Sama dengan bawah: Jangan Lupa
	 */
    //DPP
    @Column(name = "AMOUNT_AFTER_DISC_PLUS")
    var amountAfterDiscPlusRp_FG = 0.0

    //	@Column(name="PPN_PERCENT")
    //	private Double ppnPercent=0.0;
    //	@Transient
    @Column(name = "PPN_RP")
    var ppnRp = 0.0

    //DPP+PPN
    @Column(name = "AMOUNT_AFTER_DISC_PLUS_AFTERPPN")
    var amountAfterDiscPlusRpAfterPpn_FG = 0.0 //Sama dengan amountAfterDisc spt dibawah

    //	//AMOUNT AFTER DISC
    //	@Column(name="AMOUNT_AFTERDISC_ALL")
    //	private Double amountAfterDiscAll=0.0;
    //	@Column(name="AMOUNT_AFTERDISC_ALL_AFTERPPN")
    //	private Double amountAfterDiscAllAfterPpn=0.0;
    @Column(name = "SALDO")
    var saldo = false

    //AMOUNT PAY
    @Column(name = "AMOUNT_PAY_RP")
    var amountPayRp = 0.0

    @Column(name = "END_OF_DAY")
    var isEndOfDay = false

    @Column(name = "USED_GRINV")
    var isUsedGrInv = false

    @Column(name = "USED_SO")
    var isUsedSO = false

    @Column(name = "PRINT_COUNTER")
    var printCounter = 0

    @Column(name = "lunas")
    var isLunas = false

    //ATURAN: update stok dan sumber apakah manual atau tidak
    @Column(name = "SOURCE", length = 5)
    var source = ""

    @Column(name = "PROSES")
    var isProses = false

    @Column(name = "NOTES", length = 160)
    var notes = ""

    @Column(name = "SHIP_TO", length = 200)
    var shipTo = ""

    @Column(name = "BILL_TO", length = 200)
    var billTo = ""

    //	@ManyToOne
    //	@JoinColumn(name="fdivisionBean", referencedColumnName="ID", nullable=false)
    //	private FDivision fdivisionBean;
    @Column(name = "fdivisionBean", nullable = false)
    var fdivisionBean = 0

    //	@ManyToOne
    //	@JoinColumn(name="fvendorBean", referencedColumnName="ID", nullable=false)
    //	private FVendor fvendorBean;
    @Column(name = "fvendorBean", nullable = false)
    var fvendorBean = 0

    //	@ManyToOne
    //	@JoinColumn(name="fwarehouseBean", referencedColumnName="ID", nullable=false)
    //	private FWarehouse fwarehouseBean;
    @Column(name = "fwarehouseBean", nullable = false)
    var fwarehouseBean = 0

    /*
	 * Account Mapping
	 */
    //	@ManyToOne
    //	@JoinColumn(name="accAccountApKbBean", referencedColumnName="ID", nullable = false)
    //	private AccAccount accAccountApKbBean;
    @Column(name = "accAccountApKbBean")
    var accAccountApKbBean = 0

    //	@ManyToOne
    //	@JoinColumn(name="accAccountApKbBean_SecondStep", referencedColumnName="ID", nullable = false)
    //	private AccAccount accAccountApKbBean_SecondStep;
    @Column(name = "accAccountApKbBean_SecondStep")
    var accAccountApKbBean_SecondStep = 0

    //Akun Potongan Khusus
    //	@ManyToOne
    //	@JoinColumn(name="accAccNonCogsFgBean", referencedColumnName="ID", nullable = true)
    //	private AccAccount accAccNonCogsFgBean;
    @Column(name = "accAccNonCogsFgBean")
    var accAccNonCogsFgBean = 0

    //	@ManyToOne
    //	@JoinColumn(name="accAccNonCogsDiscBean", referencedColumnName="ID", nullable = true)
    //	private AccAccount accAcNonCogsDiscBean;
    @Column(name = "accAccNonCogsDiscBean")
    var accAccNonCogsDiscBean = 0

    @Column(name = "CREATED")
    @Temporal(TemporalType.TIMESTAMP)
    var created = Date()

    @Column(name = "MODIFIED")
    @Temporal(TemporalType.TIMESTAMP)
    var modified = Date()

    @Column(name = "MODIFIED_BY", length = 20)
    var modifiedBy = "" //User ID

}