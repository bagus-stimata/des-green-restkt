package com.erp.distribution.desgreenrestkt.model

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import java.io.Serializable
import java.util.*
import javax.persistence.*

@JacksonXmlRootElement
@Entity
@Table(name = "ftax")
class FTax : Serializable {
    @Id
    @Column(name = "ID", length = 9)
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id = 0

    /*
	 * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
	 * keperluan diantaranya:
	 * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
	 * 2. 
	 */
    @Column(name = "SOURCE_ID", length = 9)
    var sourceId = 0

    @Column(name = "KODE1", length = 10)
    var kode1 = ""

    @Column(name = "KODE2", length = 20)
    var kode2 = ""

    @Column(name = "DESCRIPTION", length = 100)
    var description = ""

    @Column(name = "TAX_PERCENT")
    var taxPercent = 0.0

    //	@ManyToOne
    //	@JoinColumn(name="accAccountTaxPurchaseBean", referencedColumnName="ID")
    //	private AccAccount accAccountTaxPurchaseBean;
    @Column(name = "accAccountTaxPurchaseBean", nullable = false)
    var accAccountTaxPurchaseBean = 0

    //	@ManyToOne
    //	@JoinColumn(name="accAccountTaxSalesBean", referencedColumnName="ID")
    //	private AccAccount accAccountTaxSalesBean;
    @Column(name = "accAccountTaxSalesBean", nullable = false)
    var accAccountTaxSalesBean = 0

    //	@ManyToOne
    //	@JoinColumn(name="fdivisionBean", referencedColumnName="ID")
    //	private FDivision fdivisionBean;
    @Column(name = "fdivisionBean", nullable = false)
    var fdivisionBean = 0

    @Column(name = "STATUS_ACTIVE")
    var isStatusActive = true

    @Column(name = "CREATED")
    @Temporal(TemporalType.TIMESTAMP)
    var created = Date()

    @Column(name = "MODIFIED")
    @Temporal(TemporalType.TIMESTAMP)
    var modified = Date()

    @Column(name = "MODIFIED_BY", length = 20)
    var modifiedBy = "" //User ID

}