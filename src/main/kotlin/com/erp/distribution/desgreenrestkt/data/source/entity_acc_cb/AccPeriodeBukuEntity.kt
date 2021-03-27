package com.erp.distribution.desgreenrestkt.data.source.entity_acc_cb

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import java.io.Serializable
import java.util.*
import javax.persistence.*

@JacksonXmlRootElement
@Entity
@Table(name = "acc_periodebuku")
class AccPeriodeBukuEntity : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", length = 9)
    private val id = 0

    //	@ManyToOne
    //	@JoinColumn(name="fdivisionBean", referencedColumnName="ID")
    //	private FDivision fdivisionBean;
    @Column(name = "fdivisionBean", nullable = false)
    private val fdivisionBean = 0

    /*
	 * Komposite Key: Tidak boleh ada yang sama
	 */
    @Column(name = "PERIODE_YEAR", length = 5)
    private val periodeYear = 0

    @Column(name = "PERIODE_MONTH", length = 3)
    private val periodeMonth = 0

    /*
	 * Komposite Key: End
	 */
    @Column(name = "PERIODE_DATE_FROM")
    @Temporal(TemporalType.DATE)
    private val periodeDateFrom: Date? = null

    @Column(name = "PERIODE_DATE_TO")
    @Temporal(TemporalType.DATE)
    private val periodeDateTo: Date? = null

    @Column(name = "POSTING")
    private val posting = false

    @Column(name = "STATUS_ACTIVE")
    private val statusActive = true

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