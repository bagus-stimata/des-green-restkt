package com.erp.distribution.desgreenrestkt.data.source.entity

import com.erp.distribution.desgreenrestkt.domain.model.enum.EnumReligion
import com.erp.distribution.desgreenrestkt.domain.model.enum.EnumSalesType
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import java.io.Serializable
import java.util.*
import javax.persistence.*

@JacksonXmlRootElement
@Entity
@Table(name =  "fsalesman" )
data class FSalesman (
    @Id
    @Column(name =  "ID" , length = 9)
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id :Int =0,

    /*
	 * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
	 * keperluan diantaranya:
	 * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
	 * 2. 
	 */
    @Column(name =  "SOURCE_ID" , length = 9)
    var sourceId :Int =0,

    @Column(name =  "SPCODE" , length = 15)
    var spcode :String ="",

    @Column(name =  "SPNAME" , length = 50)
    var spname :String ="",

    /*
	 * TIPE JUAL SALESMAN
	 * TO = Taking Order
	 * C = Canvas
	 * TF = Task Force
	 * SHOP = Shop Sales
	 */
    //	@Column(name= SALES_TYPE , length=5)
    //	private String salesType=  ;
    @Column(name =  "SALES_TYPE" , length = 5)
    @Enumerated(EnumType.STRING)
    var salesType: EnumSalesType? = EnumSalesType.TO,

    //	@ManyToOne
    //	@JoinColumn(name= fdivisionBean , referencedColumnName= ID )
    //	private FDivision fdivisionBean;
    @Column(name =  "fdivisionBean" , nullable = false)
    var fdivisionBean :Int =0,

    @Column(name =  "ADDREESS1" , length = 100)
    var address1 :String ="",

    @Column(name =  "CITY1" , length = 30)
    var city1 :String ="",

    @Column(name =  "STATE1" , length = 30)
    var state1 :String ="",

    @Column(name =  "PHONE" , length = 30)
    var phone :String ="",

    @Column(name =  "MOBILE" , length = 30)
    var mobile :String ="",

    @Column(name =  "WHATSAPP" , length = 50)
    var whatsApp :String ="",

    @Column(name =  "EMAIL" , length = 100)
    var email :String ="",

    @Column(name =  "JOIN_DATE" )
    @Temporal(TemporalType.DATE)
    var joinDate :Date =Date(),

    @Column(name =  "LAST_TRANS" )
    @Temporal(TemporalType.DATE)
    var lastTrans :Date =Date(),

    @Column(name =  "BORN_PLACE" , length = 25)
    var bornPlace :String ="",

    @Column(name =  "BORN_DATE" )
    @Temporal(TemporalType.DATE)
    var bornDate :Date =Date(),

    @Column(name =  "RELIGION" )
    @Enumerated(EnumType.ORDINAL)
    var religion: EnumReligion? = EnumReligion.OTH1,

    @Column(name =  "STATUS_ACTIVE" )
    var statusActive  :Boolean =false,

    @Column(name =  "WEBSERVICE_ACTIVE" )
    var webServiceActive  :Boolean =false,

    //FOR KASSA
    @Column(name =  "KASSA_STATUS_OPEN" )
    var kassaStatusOpen  :Boolean =false,

    @Column(name =  "KASSA_IP" , length = 15)
    var kassaIp :String ="",

    //	@OneToMany(mappedBy= fsalesmanBean , fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    //	@ManyToOne
    //	@JoinColumn(name= ftPriceAlthBean , referencedColumnName= ID , nullable=true)
    //	private FtPriceAlth ftPriceAlthBean;
    @Column(name =  "ftPriceAlthBean" )
    var ftPriceAlthBean: Int? =0,

    /*
	 * ignore/reject promotion rules setting
	 */
    @Column(name =  "NO_PROMOTION_RULES" )
    var noPromotionRules  :Boolean =false,

    @Column(name =  "VENDORCOVERED" )
    var vendorcovered  :Boolean =false,

    @Column(name =  "CREATED" )
    @Temporal(TemporalType.TIMESTAMP)
    var created :Date =Date(),

    @Column(name =  "MODIFIED" )
    @Temporal(TemporalType.TIMESTAMP)
    var modified :Date =Date(),

    @Column(name =  "MODIFIED_BY" , length = 20)
    var modifiedBy :String =""   //User ID

): Serializable