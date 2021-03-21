package com.erp.distribution.desgreenrestkt.data.source.entity

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import java.util.*
import javax.persistence.*

@JacksonXmlRootElement //@Entity
//@Table(name="fexpedisi")
class FExpedisi {
    @Id
    @Column(name = "ID", length = 9)
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id = 0

    //	@ManyToOne
    //	@JoinColumn(name="fdivisionBean", referencedColumnName="ID")
    //	private FDivision fdivisionBean;
    @Column(name = "fdivisionBean", nullable = false)
    var fdivisionBean = 0

    /*
	 * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
	 * keperluan diantaranya:
	 * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
	 * 2. 
	 */
    @Column(name = "SOURCE_ID", length = 9)
    var sourceID = 0

    @Column(name = "KODE1", length = 10)
    var kode1 = ""

    @Column(name = "KODE2", length = 20)
    var kode2 = ""

    @Column(name = "NAME", length = 75)
    var name = ""

    @Column(name = "TYPE", length = 5)
    var type = ""

    @Column(name = "ADDREESS1", length = 100)
    var address1 = ""

    @Column(name = "CITY1", length = 30)
    var city1 = ""

    @Column(name = "STATE1", length = 30)
    var state1 = ""

    @Column(name = "PHONE", length = 30)
    var phone = ""

    @Column(name = "MOBILE", length = 30)
    var mobile = ""

    @Column(name = "EMAIL", length = 100)
    var email = ""

    @Temporal(TemporalType.DATE)
    var joindate = Date()

    @Temporal(TemporalType.DATE)
    var lasttrans = Date()

    @Column(name = "STATUSACTIVE")
    var isStatusActive = false

    @Column(name = "CREATED")
    @Temporal(TemporalType.TIMESTAMP)
    var created = Date()

    @Column(name = "MODIFIED")
    @Temporal(TemporalType.TIMESTAMP)
    var modified = Date()

    @Column(name = "MODIFIED_BY", length = 20)
    var modifiedBy = "" //User ID

}