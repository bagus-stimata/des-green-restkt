package com.erp.distribution.desgreenrestkt.data.source.entity

import com.erp.distribution.desgreenrestkt.domain.model.enum.EnumAccTransactionType
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
data class FUangMukaEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="hibernate_sequence")
    @SequenceGenerator(
        name = "hibernate_sequence",
        sequenceName = "hibernate_sequence_master",
        allocationSize = 20,
        initialValue = 912345668
    )
    @Column(name = "ID")
    var id  :Int =0,

    /*
	 * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
	 * keperluan diantaranya:
	 * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
	 * 2. xxxx
	 */
    @Column(name = "SOURCE_ID")
    var sourceId  :Int =0,

    @Column(name = "NO_REK", length = 50)
    var noRek :String ="",

    @Temporal(TemporalType.DATE)
    @Column(name = "TR_DATE")
    var trDate :Date =Date(),

    /*
	 * Ikut Cash Bank: Deposit or Payment
	 */
    @Enumerated(EnumType.STRING)
    @Column(name = "ACC_TRANSACTION_TYPE", length = 10)
    var accTransactionType: EnumAccTransactionType = EnumAccTransactionType.JURNAL_MEM,

    @Column(name = "AMOUNT_RP")
    var amountRp  :Double =0.0,

    @Column(name = "AMOUNT_USED")
    var amountUsed  :Double =0.0,

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
    var cashAmountPay  :Double =0.0,

    @Column(name = "GIRO_AMOUNTPAY")
    var giroAmountPay  :Double =0.0,

    @Column(name = "TRANSFER_AMOUNTPAY")
    var transferAmountPay  :Double =0.0,

    //	@ManyToOne
    //	@JoinColumn(name="ftransferBean", referencedColumnName="ID", nullable=true)
    //	private FGiro ftransferBean;
    @Column(name = "ftransferBean")
    var ftransferBean  :Int =0,

    //	@ManyToOne
    //	@JoinColumn(name="fgiroBean", referencedColumnName="ID", nullable=true)
    //	private FGiro fgiroBean;
    @Column(name = "fgiroBean")
    var fgiroBean  :Int =0,

    //	@ManyToOne
    //	@JoinColumn(name="fdivisionBean", referencedColumnName="ID")
    //	private FDivision fdivisionBean;
    @Column(name = "fdivisionBean", nullable = false)
    var fdivisionBean  :Int =0,

    @Column(name = "SHARED_TO_COMPANY")
    var isSharedToCompany  :Boolean =false, //setting Shared to company khusus ditaruh disini, bukan di divisi

    //Jika uang muka penjualan
    //	@ManyToOne
    //	@JoinColumn(name="fcustomerBean", referencedColumnName="ID")
    //	private FCustomer fcustomerBean;
    @Column(name = "fcustomerBean")
    var fcustomerBean  :Int =0,

    //Jika uang muka pembelian
    //	@ManyToOne
    //	@JoinColumn(name="fvendorBean", referencedColumnName="ID")
    //	private FVendor fvendorBean;
    @Column(name = "fvendorBean")
    var fvendorBean  :Int =0,

    /*
	 * Account Mapping: Bank Account
	 * Accaount untuk Pencairan Pada Piutang Giro Misalnya
	 */
    //	@ManyToOne
    //	@JoinColumn(name="accAccountDebitBean", referencedColumnName="ID", nullable=true)
    //	private AccAccount accAccountDebitBean;
    @Column(name = "accAccountDebitBean", nullable = false)
    var accAccountDebitBean  :Int =0,

    //	@ManyToOne
    //	@JoinColumn(name="accAccountCreditBean", referencedColumnName="ID", nullable=true)
    //	AccAccount accAccountCreditBean;
    @Column(name = "accAccountCreditBean", nullable = false)
    var accAccountCreditBean  :Int =0,

    @Column(name = "NOTES", length = 160)
    var notes :String ="",

    @Column(name = "STATUS_ACTIVE")
    var isStatusActive  :Boolean =true,

    @Transient
    var tempString :String ="",

    //LOG
    @Column(name = "CREATED")
    @Temporal(TemporalType.TIMESTAMP)
    var created :Date =Date(),

    @Column(name = "MODIFIED")
    @Temporal(TemporalType.TIMESTAMP)
    var modified :Date =Date(),

    @Column(name = "MODIFIED_BY", length = 20)
    var modifiedBy :String ="" //User ID

): Serializable