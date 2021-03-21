package com.erp.distribution.desgreenrestkt.model

import com.erp.distribution.desgreenrestkt.domain.model.modelenum.EnumAccTransactionType
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import java.io.Serializable
import java.util.*
import javax.persistence.*

/**
 * The persistent class for the BUKUGIRO database table.
 *
 */
@JacksonXmlRootElement
@Entity
@Table(name = "fuang_muka")
class FUangMuka : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    var id = 0

    /*
	 * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
	 * keperluan diantaranya:
	 * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
	 * 2. xxxx
	 */
    @Column(name = "SOURCE_ID")
    var sourceId = 0

    @Column(name = "NO_REK", length = 50)
    var noRek = ""

    @Temporal(TemporalType.DATE)
    @Column(name = "TR_DATE")
    var trDate = Date()

    /*
	 * Ikut Cash Bank: Deposit or Payment
	 */
    @Enumerated(EnumType.STRING)
    @Column(name = "ACC_TRANSACTION_TYPE", length = 10)
    var accTransactionType: EnumAccTransactionType? = null

    @Column(name = "AMOUNT_RP")
    var amountRp = 0.0

    @Column(name = "AMOUNT_USED")
    var amountUsed = 0.0

    /*
	 * Pencairan Giro
	 * Tanggal Cair or Tolak Adalah Tanggal Transaksi Jurnal Akuntansi
	 */
    //	@Enumerated(EnumType.ORDINAL)
    //	@Column(name="STATUS_GIRO")
    //	private EnumStatusGiro statusGiro;
    //	@Column(name="SHARED_TO_COMPANY")
    //	private boolean sharedToCompany=false; //setting Shared to company khusus ditaruh disini, bukan di divisi
    @Column(name = "CASH_AMOUNTPAY")
    var cashAmountPay = 0.0

    @Column(name = "GIRO_AMOUNTPAY")
    var giroAmountPay = 0.0

    @Column(name = "TRANSFER_AMOUNTPAY")
    var transferAmountPay = 0.0

    //	@ManyToOne
    //	@JoinColumn(name="ftransferBean", referencedColumnName="ID", nullable=true)
    //	private FGiro ftransferBean;
    @Column(name = "ftransferBean")
    var ftransferBean = 0

    //	@ManyToOne
    //	@JoinColumn(name="fgiroBean", referencedColumnName="ID", nullable=true)
    //	private FGiro fgiroBean;
    @Column(name = "fgiroBean")
    var fgiroBean = 0

    //	@ManyToOne
    //	@JoinColumn(name="fdivisionBean", referencedColumnName="ID")
    //	private FDivision fdivisionBean;
    @Column(name = "fdivisionBean", nullable = false)
    var fdivisionBean = 0

    @Column(name = "SHARED_TO_COMPANY")
    var isSharedToCompany = false //setting Shared to company khusus ditaruh disini, bukan di divisi

    //Jika uang muka penjualan
    //	@ManyToOne
    //	@JoinColumn(name="fcustomerBean", referencedColumnName="ID")
    //	private FCustomer fcustomerBean;
    @Column(name = "fcustomerBean")
    var fcustomerBean = 0

    //Jika uang muka pembelian
    //	@ManyToOne
    //	@JoinColumn(name="fvendorBean", referencedColumnName="ID")
    //	private FVendor fvendorBean;
    @Column(name = "fvendorBean")
    var fvendorBean = 0

    /*
	 * Account Mapping: Bank Account
	 * Accaount untuk Pencairan Pada Piutang Giro Misalnya
	 */
    //	@ManyToOne
    //	@JoinColumn(name="accAccountDebitBean", referencedColumnName="ID", nullable=true)
    //	private AccAccount accAccountDebitBean;
    @Column(name = "accAccountDebitBean", nullable = false)
    var accAccountDebitBean = 0

    //	@ManyToOne
    //	@JoinColumn(name="accAccountCreditBean", referencedColumnName="ID", nullable=true)
    //	AccAccount accAccountCreditBean;
    @Column(name = "accAccountCreditBean", nullable = false)
    var accAccountCreditBean = 0

    @Column(name = "NOTES", length = 160)
    var notes = ""

    @Column(name = "STATUS_ACTIVE")
    var isStatusActive = true

    @Transient
    var tempString = ""

    //LOG
    @Column(name = "CREATED")
    @Temporal(TemporalType.TIMESTAMP)
    var created = Date()

    @Column(name = "MODIFIED")
    @Temporal(TemporalType.TIMESTAMP)
    var modified = Date()

    @Column(name = "MODIFIED_BY", length = 20)
    var modifiedBy = "" //User ID

}