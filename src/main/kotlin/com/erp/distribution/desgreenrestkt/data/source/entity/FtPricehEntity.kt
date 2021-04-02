package com.erp.distribution.desgreenrestkt.data.source.entity

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import java.io.Serializable
import java.util.*
import javax.persistence.*

@JacksonXmlRootElement
@Entity
@Table(name = "ftpriceh")
data class FtPricehEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="hibernate_sequence")
    @SequenceGenerator(
        name = "hibernate_sequence",
        sequenceName = "hibernate_sequence_header",
        allocationSize = 20,
        initialValue = 912345668
    )
    @Column(name = "REFNO")
    val refno: Long =0L,

    /*
	 * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
	 * keperluan diantaranya:
	 * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
	 * 2. 
	 */
    @Column(name = "SOURCE_ID")
    val sourceId: Long =0L,

    @Column(name = "NO_REK", length = 100)
    val noRek :String ="",

    @Column(name = "DESCRIPTION", length = 50)
    val description :String ="",

    @Column(name = "NOTES", length = 255)
    val notes :String ="",

    @Column(name = "TR_DATE")
    @Temporal(TemporalType.DATE)
    val trDate :Date =Date(),

    @Column(name = "POSTING")
    val posting  :Boolean =false,

    @Column(name = "END_OF_DAY")
    val endOfDay  :Boolean =false,

    @Column(name = "PRINT_COUNTER", length = 4)
    val printCounter :Int =0,

    //	@ManyToOne
    //	@JoinColumn(name="fdivisionBean", referencedColumnName="ID", nullable=false)
    //	FDivision fdivisionBean;
    @Column(name = "fdivisionBean", nullable = false)
    val fdivisionBean :Int =0,

    @Column(name = "CREATED")
    @Temporal(TemporalType.TIMESTAMP)
    val created :Date =Date(),

    @Column(name = "MODIFIED")
    @Temporal(TemporalType.TIMESTAMP)
    val modified :Date =Date(),

    @Column(name = "MODIFIED_BY", length = 20)
    val modifiedBy :String ="" //User ID
): Serializable