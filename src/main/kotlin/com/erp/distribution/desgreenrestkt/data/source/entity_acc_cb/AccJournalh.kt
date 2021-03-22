package com.erp.distribution.desgreenrestkt.data.source.entity_acc_cb

import com.erp.distribution.desgreenrestkt.domain.model.enum.EnumAccTransactionType
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import java.io.Serializable
import java.util.*
import javax.persistence.*

@JacksonXmlRootElement
@Entity
@Table(name = "accjournalh")
class AccJournalh : Serializable {
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

    @Column(name = "NO_REK", length = 30)
    private val noRek = ""

    @Column(name = "TR_DATE")
    @Temporal(TemporalType.DATE)
    private val trDate = Date()

    @Column(name = "NOTES", length = 210)
    private val notes = ""

    @Transient
    private val tempNotes = ""

    @Column(name = "AMOUNT_DEBIT")
    private val amountDebit = 0.0

    @Column(name = "AMOUNT_CREDIT")
    private val amountCredit = 0.0

    @Column(name = "POSTING")
    private val posting = false

    @Column(name = "POSTINGTEMP")
    private val postingTemp = false

    @Column(name = "END_OF_DAY")
    private val endOfDay = false

    //SUMBER J=JOURNAL, BP=Sales Order and Retur, AR=Account Receivable, PO=Purchase, AP=Account Payable
    //	@Column(name="SOURCE", length=5)
    //	private String source;
    @Enumerated(EnumType.STRING)
    @Column(name = "ACC_TRANSACTION_TYPE", length = 10)
    private val accTransactionType: EnumAccTransactionType? = null

    /*
	 * Nomor refenrensi dari sumber. pasti berbeda kalau menggunakan refno ini
	 */
    @Column(name = "SOURCE_REFNO")
    private val sourceRefno: Long = 0

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