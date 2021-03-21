package com.erp.distribution.desgreenrestkt.data.source.entity

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import java.io.Serializable
import javax.persistence.*

@JacksonXmlRootElement
@Entity
@Table(name = "ftsalesdpromotprudisc")
class FtSalesdPromoTpruDisc : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "REFNO_PROMO")
    var id: Long = 0

    @Column(name = "NO_URUT", length = 4)
    var noUrut = 0

    //	@ManyToOne
    //	@JoinColumn(name="ftSalesdBean", referencedColumnName="ID")
    //	private FtSalesdItems ftSalesdBean;
    @Column(name = "ftSalesdBean", nullable = false)
    var ftSalesdBean: Long = 0

    //	@ManyToOne
    //	@JoinColumn(name="fPromoBean", referencedColumnName="ID")
    //	private FPromotionRulesh fPromoBean;
    @Column(name = "fPromoBean", nullable = false)
    var fPromoBean = 0

    /*
	 * PRICE in PCS: akan dipakai sebagai dasar perhitungan pada laporan aktifitas promosi
	 * dan Dipakai untuk Perhitungan Piutang pada Principle pada Journal:
	 */
    @Column(name = "PRICE_PCS_RPT")
    var pricePcsRpt = 0.0

    @Column(name = "DISC1")
    var disc1: Double? = null

    /*
	 * KHUSUS PEMBERIAN BERUPA VALUE: 
	 */
    @Column(name = "DISC1_VALUE_UOM1")
    var disc1Value_Uom1 = 0.0

    //==============
    @Column(name = "DISC2")
    var disc2 = 0.0

    @Column(name = "DISC3")
    var disc3 = 0.0

    @Column(name = "DISC1_PLUS")
    var disc1Plus = 0.0

    @Column(name = "DISC2_PLUS")
    var disc2Plus = 0.0


}