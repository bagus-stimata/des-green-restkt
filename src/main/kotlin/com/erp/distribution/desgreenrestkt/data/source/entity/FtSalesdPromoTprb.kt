package com.erp.distribution.desgreenrestkt.data.source.entity

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import java.io.Serializable
import javax.persistence.*

@JacksonXmlRootElement
@Entity
@Table(name = "ftsalesdpromotprb")
class FtSalesdPromoTprb : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "REFNO_PROMO")
    var id: Long = 0

    @Column(name = "NO_URUT", length = 4)
    var noUrut: Int? = null

    //	@ManyToOne
    //	@JoinColumn(name="ftSalesdFreegood", referencedColumnName="ID")
    //	private FtSalesdItems ftSalesdFreegood;	//Free Goodnya, bukan itemDetil yang mendapat
    @Column(name = "ftSalesdFreegood", nullable = false)
    var ftSalesdFreegood: Long = 0

    //	@ManyToOne
    //	@JoinColumn(name="fpromoBean", referencedColumnName="ID")
    //	private FPromotionRulesh fpromoBean;
    @Column(name = "fpromoBean", nullable = false)
    var fpromoBean = 0

    //	@ManyToOne
    //	@JoinColumn(name="fmaterialBean", referencedColumnName="ID")
    //	private FMaterial fmaterialBean;
    @Column(name = "fmaterialBean", nullable = false)
    var fmaterialBean = 0

    //	@Column(name="PPRICE")
    //	private Double pprice =0;
    //	
    //	@Column(name="SPRICE")
    //	private Double sprice =0;
    @Column(name = "PRICE_PCS_DTLITM")
    var pricePcs_DetilItem = 0.0 //Sebelum PPN

    /*
	 * PRICE in PCS: akan dipakai sebagai dasar perhitungan pada laporan aktifitas promosi
	 * dan Dipakai untuk Perhitungan Piutang pada Principle pada Journal:
	 */
    @Column(name = "PRICE_PCS_RPT")
    var pricePcsRpt = 0.0 //Sebelum PPN

    //	@Column(name="TPRB_QTY", length=8)
    //	private Integer tprbQty;
    @Column(name = "TPRB_QTY", length = 8)
    var tprbQty = 0.0

    fun setFtSalesdFreegood(ftSalesdFreegood: Int) {
        this.ftSalesdFreegood = ftSalesdFreegood.toLong()
    }

}