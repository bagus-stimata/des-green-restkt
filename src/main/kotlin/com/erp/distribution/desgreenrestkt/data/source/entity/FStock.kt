package com.erp.distribution.desgreenrestkt.data.source.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import java.util.*
import javax.persistence.*

@JacksonXmlRootElement
@Entity
@Table(name = "fstock")
class FStock {
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
    @JsonIgnore
    @Column(name = "SOURCE_ID")
    var sourceId: Long = 0

    /*
	 * HARI YANG DIPAKAI JUGA PADA HPP
	 */
    @Column(name = "STOCK_DATE")
    @Temporal(TemporalType.DATE)
    var stockDate: Date? = null

    //	@Column(name="SALDO_AWAL")
    //	private Integer saldoAwal =0.0;
    @Column(name = "SALDO_AWAL")
    var saldoAwal = 0.0

    /*
	 * NEXT AKAN DIBIKIN TRANSIEN
	 */
    //	@Column(name="QTY_IN")
    //	private Integer qtyIn =0.0;
    //	@Column(name="QTY_OUT")
    //	private Integer qtyOut =0.0;
    @JsonIgnore
    @Column(name = "QTY_IN")
    var qtyIn = 0.0

    @JsonIgnore
    @Column(name = "QTY_OUT")
    var qtyOut = 0.0

    //	@Column(name="QTY_ADJUST", length=10)	
    //	private Integer qtyAdjust =0.0;
    //	@Column(name="SALDO_AKHIR")
    //	private Integer saldoAkhir =0.0;
    @JsonIgnore
    @Column(name = "QTY_ADJUST", length = 10)
    var qtyAdjust = 0.0

    @Column(name = "SALDO_AKHIR")
    var saldoAkhir = 0.0

    /*
	 * ORDERED STOCK
	 */
    //	@Column(name="QTY_HOLD", length=9)	
    //	private Integer qtyHold =0.0;
    @JsonIgnore
    @Column(name = "QTY_HOLD", length = 9)
    var qtyHold = 0.0

    /*
	 * HARGA BELI NET TERAKHIR
	 * TAPI HARGA BELI TERAKHIR INI MENGGUNAKAN PPN (after PPN)
	 */
    @JsonIgnore
    @Column(name = "CLOSING_PPRICE2")
    var closingPprice2_AfterPpn = 0.0

    //	@ManyToOne
    //	@JoinColumn(name="fwarehouseBean", referencedColumnName="ID")
    //	private FWarehouse fwarehouseBean;
    @Column(name = "fwarehouseBean", nullable = false)
    var fwarehouseBean = 0

    //	@ManyToOne
    //	@JoinColumn(name="fmaterialBean", referencedColumnName="ID")
    //	private FMaterial fmaterialBean;
    @Column(name = "fmaterialBean", nullable = false)
    var fmaterialBean = 0

}