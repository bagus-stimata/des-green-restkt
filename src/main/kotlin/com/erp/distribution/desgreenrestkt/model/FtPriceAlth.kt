package com.erp.distribution.desgreenrestkt.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import java.util.*
import javax.persistence.*

@JacksonXmlRootElement
@Entity
@Table(name = "ftpricealth")
class FtPriceAlth {
    //** Tools: Jangan dihapus
    //** End Tools
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", length = 9)
    var id = 0

    /*
	 * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
	 * keperluan diantaranya:
	 * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
	 * 2. 
	 */
    @JsonIgnore
    @Column(name = "SOURCE_ID", length = 9)
    var sourceId = 0

    @Column(name = "NO_REK", length = 15)
    var noRek = ""

    @Column(name = "DESCRIPTION", length = 50)
    var description = ""

    //	@ManyToOne
    //	@JoinColumn(name="fdivisionBean", referencedColumnName="ID", nullable=false)
    //	private FDivision fdivisionBean;
    @Column(name = "fdivisionBean", nullable = false)
    var fdivisionBean = 0

    @Temporal(TemporalType.DATE)
    @Column(name = "TR_DATE")
    var trDate = Date()

    /**
     * Pastikan yang ditarik pasti aktif
     */
    @JsonIgnore
    @Column(name = "STATUS_ACTIVE")
    var isStatusActive = true

    @JsonIgnore
    @Column(name = "CREATED")
    @Temporal(TemporalType.TIMESTAMP)
    var created = Date()

    @Column(name = "MODIFIED")
    @Temporal(TemporalType.TIMESTAMP)
    var modified = Date()

    @JsonIgnore
    @Column(name = "MODIFIED_BY", length = 20)
    var modifiedBy = "" //User ID

}