package com.erp.distribution.desgreenrestkt.presentation.model

import com.erp.distribution.desgreenrestkt.data.source.entity.FGiroEntity
import com.erp.distribution.desgreenrestkt.domain.model.FDistributionChannel
import com.erp.distribution.desgreenrestkt.domain.model.FGiro
import com.erp.distribution.desgreenrestkt.domain.model.enum.EnumAccTransactionType
import com.erp.distribution.desgreenrestkt.domain.model.enum.EnumStatusGiro
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import java.io.Serializable
import java.util.*
import javax.persistence.*

/**
 * The persistent class for the BUKUGIRO database table.
 *
 */
@JacksonXmlRootElement
data class FGiroRes(
    var id  :Int =0,

    @Column(name = "SOURCE_ID")
    var sourceId :Int =0,

    /*
	 * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
	 * keperluan diantaranya:
	 * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
	 * 2. 
	 */
    //	@Column(name="SOURCE_ID")
    //	private long sourceID =0;
    @Column(name = "GIRO_NUMBER", length = 50)
    var giroNumber :String ="",

    @Column(name = "BANK_NAME", length = 55)
    var bankName :String ="",

    @Column(name = "PEMILIK_REK", length = 75)
    var pemilikRek :String ="",

    /*
	 * Ikut Cash Bank: Deposit or Payment
	 */
    @Enumerated(EnumType.STRING)
    @Column(name = "ACC_TRANSACTION_TYPE", length = 10)
    var accTransactionType: EnumAccTransactionType? = EnumAccTransactionType.JURNAL_MEM,

    @Temporal(TemporalType.DATE)
    @Column(name = "GIRO_DATE")
    var giroDate :Date =Date(),

    @Temporal(TemporalType.DATE)
    @Column(name = "GIRO_DUE_DATE")
    var giroDueDate :Date =Date(),

    @Column(name = "AMOUNT_RP")
    var amountRp  :Double =0.0,

    @Column(name = "AMOUNT_USED")
    var amountUsed  :Double =0.0,

    /*
	 * Pencairan Giro
	 * Tanggal Cair or Tolak Adalah Tanggal Transaksi Jurnal Akuntansi
	 */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "STATUS_GIRO")
    var statusGiro: EnumStatusGiro? = EnumStatusGiro.GIRO_OPEN,

    @Temporal(TemporalType.DATE)
    @Column(name = "CAIR_TOLAK_DATE")
    var cairOrTolakDate :Date =Date(),

    @Column(name = "SHARED_TO_COMPANY")
    var sharedToCompany  :Boolean =false, //setting Shared to company khusus ditaruh disini, bukan di divisi

    /*
	 * Account Mapping: Bank Account
	 * Accaount untuk Pencairan Pada Piutang Giro Misalnya
	 */
    //	@ManyToOne
    //	@JoinColumn(name="accAccountBean", referencedColumnName="ID", nullable=true)
    //	private AccAccount accAccountBean;
    @Column(name = "accAccountBean")
    var accAccountBean  :Int =0,

    //	@ManyToOne
    //	@JoinColumn(name="accAccountCairOrTolak", referencedColumnName="ID", nullable=true)
    //	AccAccount accAccountCairOrTolak;
    @Column(name = "accAccountCairOrTolak")
    var accAccountCairOrTolak  :Int =0,

    /*
	 * true	= Giro
	 * false =  Transfer
	 */
    @Column(name = "GIRO_OR_TRANSFER")
    var giroOrTransfer  :Boolean =true,

    //	@ManyToOne
    //	@JoinColumn(name="fdivisionBean", referencedColumnName="ID")
    //	private FDivision fdivisionBean;
    @Column(name = "fdivisionBean", nullable = false)
    var fdivisionBean  :Int =0,

    @Column(name = "STATUS_ACTIVE")
    var statusActive  :Boolean =true,

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


internal fun FGiroRes.toDomain(): FGiro{
    return FGiro(
        id = id,
        sourceId = sourceId,

        giroNumber = giroNumber,
        bankName = bankName,
        pemilikRek = pemilikRek,

        accTransactionType = accTransactionType,
        giroDate = giroDate,
        giroDueDate = giroDueDate,
        amountRp = amountRp,
        amountUsed = amountUsed,
        statusGiro = statusGiro,
        cairOrTolakDate = cairOrTolakDate,
        sharedToCompany = sharedToCompany,
        accAccountBean = accAccountBean,
        accAccountCairOrTolak = accAccountCairOrTolak,
        giroOrTransfer = giroOrTransfer,

        fdivisionBean = fdivisionBean,
        statusActive = statusActive,

        created = created,
        modified = modified,
        modifiedBy = modifiedBy
    )
}


