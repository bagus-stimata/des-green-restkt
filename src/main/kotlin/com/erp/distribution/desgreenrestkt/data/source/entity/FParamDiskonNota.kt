package com.erp.distribution.desgreenrestkt.data.source.entity

import com.erp.distribution.desgreenrestkt.domain.model.enum.EnumTunaiKredit
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import java.io.Serializable
import java.util.*
import javax.persistence.*

/**
 * The persistent class for the BANK database table.
 *
 */
@JacksonXmlRootElement
@Entity
@Table(name ="fparam_diskon_nota")
data class FParamDiskonNota(
    @Id
    @Column(name ="ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id : Int= 0,

    //	@ManyToOne
    //	@JoinColumn(name= :Int =0,fdivisionBean :Int =0,, referencedColumnName= :Int =0,ID)
    //	private FDivision fdivisionBean;
    @Column(name ="fdivisionBean", nullable = false)
    var fdivisionBean :Int =0,

    /*
	 * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
	 * keperluan diantaranya:
	 * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
	 * 2. 
	 */
    @Column(name ="SOURCE_ID")
    var sourceId :Int =0,

    @Column(name ="KODE1", length = 20)
    var kode1 :String = "",

    @Column(name ="KODE2", length = 20)
    var kode2 :String = "",

    @Column(name ="DESCRIPTION", length = 70)
    var description :String ="",

    //	@ManyToOne
    //	@JoinColumn(name= :Int =0,forFcustomerGroupBean :Int =0,, referencedColumnName= :Int =0,ID)
    //	private FCustomerGroup forFcustomerGroupBean;
    @Column(name ="forFcustomerGroupBean", nullable = false)
    var forFcustomerGroupBean :Int = 0,

    @Column(length = 5)
    @Enumerated
    var forTunaiKredit: EnumTunaiKredit? = EnumTunaiKredit.T,

    @Column(name ="STATUS_ACTIVE")
    var isStatusActive  :Boolean =true,

    /*
	 * dimulai daribyNominal TERKECIL sampai terbesar: Programnya menaik donk
	 */
    @Column(name ="BUY_NOMINAL_1")
    var buyNominal_1  :Double =0.0,

    @Column(name ="DISC1_GET_1")
    var disc1Get_1  :Double =0.0,

    @Column(name ="DISC2_GET_1")
    var disc2Get_1  :Double =0.0,

    @Column(name ="DISC_PLUS_GET_1")
    var discPlusGet_1  :Double =0.0,

    @Column(name ="BUY_NOMINAL_2")
    var buyNominal_2  :Double =0.0,

    @Column(name ="DISC1_GET_2")
    var disc1Get_2  :Double =0.0,

    @Column(name ="DISC2_GET_2")
    var disc2Get_2  :Double =0.0,

    @Column(name ="DISC_PLUS_GET_2")
    var discPlusGet_2  :Double =0.0,

    @Column(name ="BUY_NOMINAL_3")
    var buyNominal_3  :Double =0.0,

    @Column(name ="DISC1_GET_3")
    var disc1Get_3  :Double =0.0,

    @Column(name ="DISC2_GET_3")
    var disc2Get_3  :Double =0.0,

    @Column(name ="DISC_PLUS_GET_3")
    var discPlusGet_3  :Double =0.0,

    @Column(name ="BUY_NOMINAL_4")
    var buyNominal_4  :Double =0.0,

    @Column(name ="DISC1_GET_4")
    var disc1Get_4  :Double =0.0,

    @Column(name ="DISC2_GET_4")
    var disc2Get_4  :Double =0.0,

    @Column(name ="DISC_PLUS_GET_4")
    var discPlusGet_4  :Double =0.0,

    @Column(name ="BUY_NOMINAL_5")
    var buyNominal_5  :Double =0.0,

    @Column(name ="DISC1_GET_5")
    var disc1Get_5  :Double =0.0,

    @Column(name ="DISC2_GET_5")
    var disc2Get_5  :Double =0.0,

    @Column(name ="DISC_PLUS_GET_5")
    var discPlusGet_5  :Double =0.0,

    //LOG
    @Column(name ="CREATED")
    @Temporal(TemporalType.TIMESTAMP)
    var created :Date =Date(),

    @Column(name ="MODIFIED")
    @Temporal(TemporalType.TIMESTAMP)
    var modified :Date =Date(),

    @Column(name ="MODIFIED_BY", length = 20)
    var modifiedBy :String = "" //User ID

): Serializable