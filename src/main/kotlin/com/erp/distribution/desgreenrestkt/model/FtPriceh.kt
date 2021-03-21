package com.erp.distribution.desgreenrestkt.model

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import java.util.*
import javax.persistence.*

@JacksonXmlRootElement
@Entity
@Table(name = "ftpriceh")
class FtPriceh {
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
    private val sourceId: Long = 0

    @Column(name = "NO_REK", length = 100)
    private val noRek = ""

    @Column(name = "DESCRIPTION", length = 50)
    private val description = ""

    @Column(name = "NOTES", length = 255)
    private val notes = ""

    @Column(name = "TR_DATE")
    @Temporal(TemporalType.DATE)
    private val trDate = Date()

    @Column(name = "POSTING")
    private val posting = false

    @Column(name = "END_OF_DAY")
    private val endOfDay = false

    @Column(name = "PRINT_COUNTER", length = 4)
    private val printCounter = 0

    //	@ManyToOne
    //	@JoinColumn(name="fdivisionBean", referencedColumnName="ID", nullable=false)
    //	private FDivision fdivisionBean;
    @Column(name = "fdivisionBean", nullable = false)
    private val fdivisionBean = 0

    @Column(name = "CREATED")
    @Temporal(TemporalType.TIMESTAMP)
    private val created = Date()

    @Column(name = "MODIFIED")
    @Temporal(TemporalType.TIMESTAMP)
    private val modified = Date()

    @Column(name = "MODIFIED_BY", length = 20)
    private val modifiedBy = "" //User ID
}