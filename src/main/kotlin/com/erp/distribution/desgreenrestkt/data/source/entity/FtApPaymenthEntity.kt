package com.erp.distribution.desgreenrestkt.data.source.entity

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import java.io.Serializable
import java.util.*
import javax.persistence.*

@JacksonXmlRootElement
@Entity
@Table(name =  "ftappaymenth" )
data class FtApPaymenthEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="hibernate_sequence")
    @SequenceGenerator(
        name = "hibernate_sequence",
        sequenceName = "hibernate_sequence_header",
        allocationSize = 20,
        initialValue = 912345668
    )
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

    @Column(name =  "NO_REK" , length = 15)
    var noRek :String ="",

    @Column(name =  "TR_DATE" )
    @Temporal(TemporalType.DATE)
    var trDate: Date = Date(),

    @Column(name =  "ENTRY_DATE" )
    @Temporal(TemporalType.DATE)
    var entryDate: Date = Date(),

    @Column(name =  "NOTES" , length = 150)
    var notes :String ="",

    @Column(name =  "PRINT_COUNTER" , length = 4)
    var printCounter :Int =0,

    //PENGGUNAAN UTAMA PADA END_OF_DAY
    @Column(name =  "END_OF_DAY" )
    var endOfDay  :Boolean =false,

    @Column(name =  "CLOSING" )
    var closing  :Boolean =false,

    //	@ManyToOne
    //	@JoinColumn(name= fdivisionBean , referencedColumnName= ID )
    //	private FDivision fdivisionBean;
    @Column(name =  "fdivisionBean" , nullable = false)
    var fdivisionBean :Int =0,

    @Column(name =  "CREATED" )
    @Temporal(TemporalType.TIMESTAMP)
    var created :Date =Date(),

    @Column(name =  "MODIFIED" )
    @Temporal(TemporalType.TIMESTAMP)
    var modified :Date =Date(),

    @Column(name =  "MODIFIED_BY" , length = 20)
    var modifiedBy :String =""    //User ID

): Serializable