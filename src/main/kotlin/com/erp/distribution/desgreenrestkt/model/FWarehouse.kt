package com.erp.distribution.desgreenrestkt.model

import com.erp.distribution.desgreenrestkt.domain.model.modelenum.EnumTipeWarehouse
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import java.util.*
import javax.persistence.*

@JacksonXmlRootElement
@Entity
@Table(name = "fwarehouse")
class FWarehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", length = 9)
    var id = 0

    /*
	 * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
	 * keperluan diantaranya:
	 * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
	 * 2. 
	 */
    @Column(name = "SOURCE_ID", length = 9)
    var sourceId = 0

    @Column(name = "KODE1", length = 10)
    var kode1 = ""

    @Column(name = "KODE2", length = 20)
    var kode2 = ""

    //	@ManyToOne
    //	@JoinColumn(name="fdivisionBean", referencedColumnName="ID")
    //	private FDivision fdivisionBean;
    @Column(name = "fdivisionBean", nullable = false)
    var fdivisionBean = 0

    @Column(name = "PRODUCT_HPP_SAVED")
    var isProductHppSaved = false

    @Column(name = "NUMBER_PRIORITY")
    var numberPriority: Int? = null

    @Column(name = "DESCRIPTION", length = 100)
    var description = ""

    @Column(name = "GUDANG_UTAMA")
    var isGudangUtama = false

    @Column(name = "ADDRESS1", length = 100)
    var address1 = ""

    @Column(name = "CITY1", length = 30)
    var city1: String? = null

    @Column(name = "STATE1", length = 30)
    var state1 = ""

    @Column(name = "PHONE", length = 50)
    var phone = ""

    @Column(name = "STATUS_ACTIVE")
    var isStatusActive = false

    @Column(name = "GDG_PO")
    var isGudangPo = true

    @Column(name = "GDG_SO")
    var isGudangSo = true

    @Column(name = "GDG_TRANSFER")
    var isGudangTransfer = true

    @Column(name = "GDG_RETAIL")
    var isGudangRetail = true

    @Column(name = "GDG_PUSAT_COMPANY")
    var isGudangPusatCompany = true

    @Column(name = "GDG_TRANSIT_DIV")
    var isGudangTransitDiv = true

    @Column(length = 5, name = "TIPE_WAREHOUSE")
    @Enumerated(EnumType.STRING)
    var tipeWarehouse: EnumTipeWarehouse? = null

    //PORT WS:: UNTUK TRANSAKSI PEMBALIAN DAN PENJUALAN
    @Column(name = "WSPORT", length = 10)
    var wsport = ""

    @Column(name = "CREATED")
    @Temporal(TemporalType.TIMESTAMP)
    var created = Date()

    @Column(name = "MODIFIED")
    @Temporal(TemporalType.TIMESTAMP)
    var modified = Date()

    @Column(name = "MODIFIED_BY", length = 20)
    var modifiedBy = "" //User ID

}