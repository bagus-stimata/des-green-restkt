package com.erp.distribution.desgreenrestkt.data.source.entity_acc_cb

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import java.io.Serializable
import java.util.*
import javax.persistence.*

/**
 * The persistent class for the ACCOUNTGROUP database table.
 *
 */
@JacksonXmlRootElement
@Entity
@Table(name = "acc_costcenter")
class AccCostCenter : Serializable {
    @Id
    @Column(name = "ID", length = 9)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private val id = 0

    /*
	 * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
	 * keperluan diantaranya:
	 * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
	 * 2. 
	 */
    @Column(name = "SOURCE_ID", length = 9)
    private val sourceID = 0

    @Column(name = "KODE1", length = 10)
    private val kode1 = ""

    @Column(name = "KODE2", length = 20)
    private val kode2 = ""

    @Column(name = "DESCRIPTION", length = 100)
    private val description = ""

    //	@ManyToOne
    //	@JoinColumn(name="fdivisionBean", referencedColumnName="ID")
    //	private FDivision fdivisionBean;
    @Column(name = "fdivisionBean", nullable = false)
    private val fdivisionBean = 0

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