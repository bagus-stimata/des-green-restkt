package com.erp.distribution.desgreenrestkt.model

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import java.io.Serializable
import javax.persistence.*

/**
 * The persistent class for the BANK database table.
 *
 */
@JacksonXmlRootElement
@Entity
@Table(name = "fparamdiskonitemvendor")
class FParamDiskonItemVendor : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", length = 9)
    private val id: Long = 0

    /*
	 * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
	 * keperluan diantaranya:
	 * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
	 * 2. 
	 */
    @Column(name = "SOURCE_ID", length = 9)
    private val sourceId: Long = 0

    //	@ManyToOne
    //	@JoinColumn(name="fdivisionBean", referencedColumnName="ID")
    //	private FDivision fdivisionBean;
    //	@Column(name="fdivisionBean", nullable = false)
    //	private Integer fdivisionBean = 0;
    @Column(name = "DESCRIPTION", length = 150)
    private val description = ""

    //QTY DIMANFAATKAN UNTUK RUPIAH
    @Column(name = "QTYORRUPIAH")
    private val qtyOrRupiah = false //true = Qty
    private val nominal1 = 0.0 //Nominal or Qty
    private val diskon1 = 0.0
    private val diskon1plus = 0.0
    private val nominal2 = 0.0
    private val diskon2 = 0.0
    private val diskon2plus = 0.0
    private val nominal3 = 0.0
    private val diskon3 = 0.0
    private val diskon3plus = 0.0
    private val nominal4 = 0.0
    private val diskon4 = 0.0
    private val diskon4plus = 0.0
    private val nominal5 = 0.0
    private val diskon5 = 0.0
    private val diskon5plus = 0.0
    private val allvendor = true

    //	@ManyToOne
    //	@JoinColumn(name="fvendorBean", referencedColumnName="ID")
    //	private FVendor fvendorBean;
    @Column(name = "fvendorBean")
    private val fvendorBean = 0
    private val allProductGroup = true

    //	@ManyToOne
    //	@JoinColumn(name="fmaterialGroup3Bean", referencedColumnName="ID")
    //	private FMaterialGroup3 fmaterialGroup3Bean;
    @Column(name = "fmaterialGroup3Bean")
    private val fmaterialGroup3Bean = 0

    @Column(name = "STATUS_ACTIVE")
    private val statusActive = true

    companion object {
        private const val serialVersionUID = 1L
    }
}