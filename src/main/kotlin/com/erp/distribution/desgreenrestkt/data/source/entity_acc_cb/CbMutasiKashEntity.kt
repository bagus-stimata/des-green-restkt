package com.erp.distribution.desgreenrestkt.data.source.entity_acc_cb

import com.erp.distribution.desgreenrestkt.domain.model.enum.EnumAccTransactionType
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import java.io.Serializable
import java.util.*
import javax.persistence.*

@JacksonXmlRootElement
@Entity
@Table(name = "cb_mutasikash")
class CbMutasiKashEntity : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "REFNO")
    private val refno: Long = 0

    /*
	 * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
	 * keperluan diantaranya:
	 * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
	 * 2. 
	 */
    @Column(name = "SOURCE_ID")
    private val sourceID: Long = 0

    //	@ManyToOne
    //	@JoinColumn(name="fdivisionBean", referencedColumnName="ID", nullable=false)
    //	private FDivision fdivisionBean;
    @Column(name = "fdivisionBean", nullable = false)
    private val fdivisionBean = 0

    @Column(name = "NOREK", length = 15)
    private val noRek = ""

    @Column(name = "CEK_NUMBER", length = 30)
    private val cekNumber = ""

    @Column(name = "TR_DATE")
    @Temporal(TemporalType.DATE)
    private val trDate = Date()

    @Column(name = "PAYEE", length = 65) //Orang yang membayar
    private val payee = ""

    @Column(name = "NOTES", length = 200) //Membayar untuk
    private val notes = ""

    /*
	 * JIKA: true maka jurnalnya berbeda
	 * 
	 */
    @Column(name = "MUTASI_KAS")
    private val mutasiKas = false

    @Column(name = "MUTASI_ANTAR_PT")
    private val mutasiAntarPT = false

    @Column(name = "MUTASI_KONFIRM")
    private val mutasiKonfirm = false

    /*
	 * Dipakai untuk Settlemen dengan AR vs Kasir
	 */
    //	@ManyToOne
    //	@JoinColumn(name="payeeBean", referencedColumnName="ID", nullable=true)
    //	private FSalesman payeeBean;
    @Column(name = "payeeBean")
    private val payeeBean = 0

    @Column(name = "AMOUNT")
    private val amount = 0.0
    private val endOfDay = false

    //SUMBER J=JOURNAL, BP=Sales Order and Retur, AR=Account Receivable, PO=Purchase, AP=Account Payable
    //	@Column(name="SOURCE", length=5)
    //	private String source= 0;
    @Enumerated(EnumType.STRING)
    @Column(name = "ACC_TRANSACTION_TYPE", length = 10)
    private val accTransactionType: EnumAccTransactionType? = null

    /*
	 * Account Bank Only: Jadi harus difilter
	 */
    //	@ManyToOne
    //	@JoinColumn(name="accAccountBean", referencedColumnName="ID", nullable=false)
    //    private AccAccount accAccountBean;
    @Column(name = "accAccountBean", nullable = false)
    private val accAccountBean = 0

    @Column(name = "CREATED")
    @Temporal(TemporalType.TIMESTAMP)
    private val created = Date()

    @Column(name = "MODIFIED")
    @Temporal(TemporalType.TIMESTAMP)
    private val modified = Date()

    @Column(name = "MODIFIED_BY", length = 20)
    private val modifiedBy = "" //User ID

    companion object {
        /**
         *
         */
        private const val serialVersionUID = 1L
    }
}