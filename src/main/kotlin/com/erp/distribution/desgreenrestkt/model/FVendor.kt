package com.erp.distribution.desgreenrestkt.model

import com.erp.distribution.desgreenrestkt.domain.model.modelenum.EnumCurrency
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import java.util.*
import javax.persistence.*

@JacksonXmlRootElement
@Entity
@Table(name = "fvendor")
class FVendor {
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

    //	@ManyToOne
    //	@JoinColumn(name="fdivisionBean", referencedColumnName="ID")
    //	private FDivision fdivisionBean;
    @Column(name = "fdivisionBean", nullable = false)
    var fdivisionBean = 0

    @Column(name = "VCODE", length = 15)
    var vcode = ""

    @Column(name = "VNAME", length = 75)
    var vname = ""

    @Column(name = "ADDRESS1", length = 75)
    var address1 = ""

    @Column(name = "ADDRESS2", length = 75)
    var address2 = ""

    @Column(name = "CITY1", length = 35)
    var city1 = ""

    @Column(name = "STATE1", length = 35)
    var state1 = ""

    @Column(name = "PHONE", length = 50)
    var phone = ""

    @Column(name = "EMAIL", length = 100)
    var email = ""

    @Column(name = "JOIN_DATE")
    @Temporal(TemporalType.DATE)
    var joinDate = Date()

    @Column(name = "LAST_TRANS")
    @Temporal(TemporalType.DATE)
    var lastTrans = Date()

    @Column(name = "NO_REKENING", length = 150)
    var noRekening = ""

    @Column(name = "CURRENCY", length = 5)
    @Enumerated(EnumType.STRING)
    var currency: EnumCurrency? = null

    /*
	 * Diskon Margin Barang: disc2 & Disc2Plus
	 */
    @Column(name = "DISC2_MARGIN")
    var disc2Margin = 0.0

    @Column(name = "DISC1PLUS_MARGIN")
    var disc1PlusMargin = 0.0

    /*
	 * PERPAJAKAN
	 */
    @Column(name = "PKP")
    var isPkp = true

    @Column(name = "NAMA_PRSH_FAKTURPAJAK", length = 150)
    var namaPrshFakturPajak = ""

    @Column(name = "NAMA_PENGUSAHA_KENA_PAJAK", length = 120)
    var namaPengusahaKenaPajak = ""

    @Column(name = "NPWP", length = 120)
    var npwp = ""

    @Column(name = "TANGGAL_PENGUKUHAN_PKP")
    @Temporal(TemporalType.DATE)
    var tanggalPengukuhanPkp = Date()

    @Column(name = "STATUS_ACTIVE")
    var isStatusActive = true

    @Column(name = "TOP", length = 4)
    var top = 0

    //PORT WS:: UNTUK TRANSAKSI PEMBALIAN DAN PENJUALAN
    @Column(name = "WSPORT", length = 10)
    var wsport: String? = null

    @Column(name = "DISC1REGMANUAL")
    var isDisc1RegManual = false

    @Column(name = "DISCPLUSREGMANUAL")
    var isDiscPlusRegManual = false

    /*
	 * Tidak Wajib Disi: karena untuk aturan yang bersifat opsional
	 * - Jika Salesman Exclusive maka hanya boleh menjual barang dari Satu vendor tertentu atau Beberapa Vendor
	 */
    //    @ManyToOne
    //	@JoinColumn(name="fsalesmanBean", referencedColumnName="ID", nullable= true)
    //	private FSalesman fsalesmanBean;
//    @Column(name = "fsalesmanBean")
//    var fsalesmanBean = 0

    @Column(name = "CREATED")
    @Temporal(TemporalType.TIMESTAMP)
    var created = Date()

    @Column(name = "MODIFIED")
    @Temporal(TemporalType.TIMESTAMP)
    var modified = Date()

    @Column(name = "MODIFIED_BY", length = 20)
    var modifiedBy = "" //User ID

    //TAMBAHAN
    @Transient
    var tempString1 = ""

    @Transient
    var tempString2 = ""

    @Transient
    var tempInt1 = 0

    @Transient
    var tempInt2 = 0

    @Transient
    var tempDouble1 = 0.0

    @Transient
    var tempDouble2 = 0.0

    @Transient
    var tempDouble31 = 0.0

    @Transient
    var tempDouble32 = 0.0

}