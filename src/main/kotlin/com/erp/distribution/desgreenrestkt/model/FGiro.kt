package com.erp.distribution.desgreenrestkt.model

import com.erp.distribution.desgreenrestkt.domain.model.modelenum.EnumAccTransactionType
import com.erp.distribution.desgreenrestkt.domain.model.modelenum.EnumStatusGiro
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
@Table(name = "fgiro")
class FGiro : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    var id = 0

    /*
	 * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
	 * keperluan diantaranya:
	 * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
	 * 2. 
	 */
    //	@Column(name="SOURCE_ID")
    //	private long sourceID =0;
    @Column(name = "GIRO_NUMBER", length = 50)
    var giroNumber = ""

    @Column(name = "BANK_NAME", length = 55)
    var bankName = ""

    @Column(name = "PEMILIK_REK", length = 75)
    var pemilikRek = ""

    /*
	 * Ikut Cash Bank: Deposit or Payment
	 */
    @Enumerated(EnumType.STRING)
    @Column(name = "ACC_TRANSACTION_TYPE", length = 10)
    var accTransactionType: EnumAccTransactionType? = null

    @Temporal(TemporalType.DATE)
    @Column(name = "GIRO_DATE")
    var giroDate = Date()

    @Temporal(TemporalType.DATE)
    @Column(name = "GIRO_DUE_DATE")
    var giroDueDate = Date()

    @Column(name = "AMOUNT_RP")
    var amountRp = 0.0

    @Column(name = "AMOUNT_USED")
    var amountUsed = 0.0

    /*
	 * Pencairan Giro
	 * Tanggal Cair or Tolak Adalah Tanggal Transaksi Jurnal Akuntansi
	 */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "STATUS_GIRO")
    var statusGiro: EnumStatusGiro? = null

    @Temporal(TemporalType.DATE)
    @Column(name = "CAIR_TOLAK_DATE")
    var cairOrTolakDate = Date()

    @Column(name = "SHARED_TO_COMPANY")
    var isSharedToCompany = false //setting Shared to company khusus ditaruh disini, bukan di divisi

    /*
	 * Account Mapping: Bank Account
	 * Accaount untuk Pencairan Pada Piutang Giro Misalnya
	 */
    //	@ManyToOne
    //	@JoinColumn(name="accAccountBean", referencedColumnName="ID", nullable=true)
    //	private AccAccount accAccountBean;
    @Column(name = "accAccountBean")
    var accAccountBean = 0

    //	@ManyToOne
    //	@JoinColumn(name="accAccountCairOrTolak", referencedColumnName="ID", nullable=true)
    //	AccAccount accAccountCairOrTolak;
    @Column(name = "accAccountCairOrTolak")
    var accAccountCairOrTolak = 0

    /*
	 * true	= Giro
	 * false =  Transfer
	 */
    @Column(name = "GIRO_OR_TRANSFER")
    var isGiroOrTransfer = true

    //	@ManyToOne
    //	@JoinColumn(name="fdivisionBean", referencedColumnName="ID")
    //	private FDivision fdivisionBean;
    @Column(name = "fdivisionBean", nullable = false)
    var fdivisionBean = 0

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