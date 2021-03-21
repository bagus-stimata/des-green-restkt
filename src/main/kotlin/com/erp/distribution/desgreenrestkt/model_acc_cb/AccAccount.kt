package com.erp.distribution.desgreenrestkt.model_acc_cb

import com.erp.distribution.desgreenrestkt.domain.model.modelenum.EnumAccCoaType
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import java.io.Serializable
import java.util.*
import javax.persistence.*

@JacksonXmlRootElement
@Entity
@Table(name = "acc_account")
class AccAccount : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", length = 9)
    private val id = 0

    /*
	 * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
	 * keperluan diantaranya:
	 * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
	 * 2. 
	 */
    @Column(name = "SOURCE_ID", length = 9)
    private val sourceID = 0

    //	@ManyToOne
    //	@JoinColumn(name="fdivisionBean", referencedColumnName="ID")
    //	private FDivision fdivisionBean;
    @Column(name = "fdivisionBean", nullable = false)
    private val fdivisionBean = 0

    @Column(name = "KODE1", length = 20)
    private val kode1 = ""

    @Column(name = "KODE2", length = 20)
    private val kode2 = ""

    @Enumerated(EnumType.STRING)
    @Column(name = "COA_TYPE", length = 10)
    private val coaType: EnumAccCoaType? = null

    @Column(name = "TITLE_GROUP1", length = 65) //Group Selain Group COA
    private val titleGroup1 = ""

    @Column(name = "TITLE_GROUP2", length = 65) //Group Selain Group COA
    private val titleGroup2 = ""

    @Column(name = "NAME", length = 100)
    private val description = ""

    //Boleh Dimasukin Saldo
    @Column(name = "POST_EDIT")
    private val postEdit = true

    /*
	 * Biasanya dipakai untuk ayat silang yang digunakan untuk semua divisi dalam company
	 */
    @Column(name = "USED_FOR_ALLDIV")
    private val usedForAllDiv = false

    @Column(name = "STATUS_ACTIVE")
    private val statusActive = true

    /*
	 * Jika Level =1 
	 * maka adalah Top Level Account
	 */
    @Column(name = "ACC_LEVEL", length = 3)
    private val accLevel = 2

    /*
	 * Cicular Relation Ship
	 */
    //	@Column(name="parentAccount", length=30)
    //	@ManyToOne
    //	@JoinColumn(name = "parentAccount", nullable=true)
    //	private AccAccount accParent;
    @Column(name = "parentAccount")
    private val parentAccount = 0

    @Column(name = "CREATED")
    @Temporal(TemporalType.TIMESTAMP)
    private val created = Date()

    @Column(name = "MODIFIED")
    @Temporal(TemporalType.TIMESTAMP)
    private val modified = Date()

    @Column(name = "MODIFIED_BY", length = 20)
    private val modifiedBy = "" //User ID

    companion object {
        private const val serialVersionUID = 1L
    }
}