package com.erp.distribution.desgreenrestkt.data.source.entity

import com.erp.distribution.desgreenrestkt.domain.model.enum.EnumTipeStockOpname
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import java.io.Serializable
import java.util.*
import javax.persistence.*

@JacksonXmlRootElement
@Entity
@Table(name =  "ftopnameh" )
data class FtOpnameh (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name =  "REFNO" )
    var refno: Long =0L,

    /*
	 * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
	 * keperluan diantaranya:
	 * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
	 * 2. 
	 */
    @Column(name =  "SOURCE_ID" )
    var sourceId: Long =0L,

    @Column(name =  "NO_REK" , length = 30)
    var noRek :String ="",

    @Column(name =  "TR_DATE" )
    @Temporal(TemporalType.DATE)
    var trDate :Date =Date(),

    @Column(name =  "TIPE_STOCK_OPNAME" , length = 5)
    @Enumerated(EnumType.STRING)
    var tipeStockOpname: EnumTipeStockOpname? = EnumTipeStockOpname.PAR,

    /*
	 * Account 
	 */
    //	@ManyToOne
    //	@JoinColumn(name= accAccountBean , referencedColumnName= ID )
    //    private AccAccount accAccountBean;
    @Column(name =  "accAccountBean" )
    var accAccountBean :Int =0,

    @Column(name =  "notes" , length = 200)
    var notes :String ="",

    @Column(name =  "POSTING" )
    var isPosting  :Boolean =false,

    @Column(name =  "END_OF_DAY" )
    var isEndOfDay  :Boolean =false,

    @Column(name =  "PRINT_COUNTER" , length = 4)
    var printCounter :Int =0,

    //	@ManyToOne
    //	@JoinColumn(name= fdivisionBean , referencedColumnName= ID , nullable=false)
    //	private FDivision fdivisionBean;
    @Column(name =  "fdivisionBean" , nullable = false)
    var fdivisionBean :Int =0,

    //	@ManyToOne
    //	@JoinColumn(name= fwarehouseBean , referencedColumnName= ID , nullable=false)
    //	private FWarehouse fwarehouseBean;
    @Column(name =  "fwarehouseBean" , nullable = false)
    var fwarehouseBean :Int =0,

    @Column(name =  "CREATED" )
    @Temporal(TemporalType.TIMESTAMP)
    var created :Date =Date(),

    @Column(name =  "MODIFIED" )
    @Temporal(TemporalType.TIMESTAMP)
    var modified :Date =Date(),

    @Column(name =  "MODIFIED_BY" , length = 20)
    var modifiedBy :String =""    //User ID

): Serializable