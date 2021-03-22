package com.erp.distribution.desgreenrestkt.data.source.entity

import com.erp.distribution.desgreenrestkt.domain.model.enum.EnumTipeStockOpname
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import java.util.*
import javax.persistence.*

@JacksonXmlRootElement
@Entity
@Table(name = "ftopnameh")
class FtOpnameh {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "REFNO")
    var refno: Long = 0

    /*
	 * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
	 * keperluan diantaranya:
	 * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
	 * 2. 
	 */
    @Column(name = "SOURCE_ID")
    var sourceId: Long = 0

    @Column(name = "NO_REK", length = 30)
    var noRek = ""

    @Column(name = "TR_DATE")
    @Temporal(TemporalType.DATE)
    var trDate = Date()

    @Column(name = "TIPE_STOCK_OPNAME", length = 5)
    @Enumerated(EnumType.STRING)
    var tipeStockOpname: EnumTipeStockOpname? = null

    /*
	 * Account 
	 */
    //	@ManyToOne
    //	@JoinColumn(name="accAccountBean", referencedColumnName="ID")
    //    private AccAccount accAccountBean;
    @Column(name = "accAccountBean")
    var accAccountBean = 0

    @Column(name = "notes", length = 200)
    var notes = ""

    @Column(name = "POSTING")
    var isPosting = false

    @Column(name = "END_OF_DAY")
    var isEndOfDay = false

    @Column(name = "PRINT_COUNTER", length = 4)
    var printCounter = 0

    //	@ManyToOne
    //	@JoinColumn(name="fdivisionBean", referencedColumnName="ID", nullable=false)
    //	private FDivision fdivisionBean;
    @Column(name = "fdivisionBean", nullable = false)
    var fdivisionBean = 0

    //	@ManyToOne
    //	@JoinColumn(name="fwarehouseBean", referencedColumnName="ID", nullable=false)
    //	private FWarehouse fwarehouseBean;
    @Column(name = "fwarehouseBean", nullable = false)
    var fwarehouseBean = 0

    @Column(name = "CREATED")
    @Temporal(TemporalType.TIMESTAMP)
    var created = Date()

    @Column(name = "MODIFIED")
    @Temporal(TemporalType.TIMESTAMP)
    var modified = Date()

    @Column(name = "MODIFIED_BY", length = 20)
    var modifiedBy = "" //User ID

}