package com.erp.distribution.desgreenrestkt.domain.model

import com.erp.distribution.desgreenrestkt.data.source.entity.FDistributionChannelEntity
import com.erp.distribution.desgreenrestkt.data.source.entity.FVendorEntity
import com.erp.distribution.desgreenrestkt.domain.model.enum.EnumCurrency
import com.erp.distribution.desgreenrestkt.presentation.model.FVendorRes
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import java.io.Serializable
import java.util.*
import javax.persistence.*

@JacksonXmlRootElement
data class FVendor (
    var id  :Int =0,

    /*
	 * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
	 * keperluan diantaranya:
	 * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
	 * 2. 
	 */
    @Column(name = "SOURCE_ID", length = 9)
    var sourceId  :Int =0,

    //	@ManyToOne
    //	@JoinColumn(name="fdivisionBean", referencedColumnName="ID")
    //	private FDivision fdivisionBean;
    @Column(name = "fdivisionBean", nullable = false)
    var fdivisionBean  :Int =0,

    @Column(name = "VCODE", length = 15)
    var vcode :String ="",

    @Column(name = "VNAME", length = 75)
    var vname :String ="",

    @Column(name = "ADDRESS1", length = 75)
    var address1 :String ="",

    @Column(name = "ADDRESS2", length = 75)
    var address2 :String ="",

    @Column(name = "CITY1", length = 35)
    var city1 :String ="",

    @Column(name = "STATE1", length = 35)
    var state1 :String ="",

    @Column(name = "PHONE", length = 50)
    var phone :String ="",

    @Column(name = "EMAIL", length = 100)
    var email :String ="",

    @Column(name = "JOIN_DATE")
    @Temporal(TemporalType.DATE)
    var joinDate :Date =Date(),

    @Column(name = "LAST_TRANS")
    @Temporal(TemporalType.DATE)
    var lastTrans :Date =Date(),

    @Column(name = "NO_REKENING", length = 150)
    var noRekening :String ="",

    @Column(name = "CURRENCY", length = 5)
    @Enumerated(EnumType.STRING)
    var currency: EnumCurrency? = EnumCurrency.IDR,

    /*
	 * Diskon Margin Barang: disc2 & Disc2Plus
	 */
    @Column(name = "DISC2_MARGIN")
    var disc2Margin  :Double =0.0,

    @Column(name = "DISC1PLUS_MARGIN")
    var disc1PlusMargin  :Double =0.0,

    /*
	 * PERPAJAKAN
	 */
    @Column(name = "PKP")
    var pkp  :Boolean =true,

    @Column(name = "NAMA_PRSH_FAKTURPAJAK", length = 150)
    var namaPrshFakturPajak :String ="",

    @Column(name = "NAMA_PENGUSAHA_KENA_PAJAK", length = 120)
    var namaPengusahaKenaPajak :String ="",

    @Column(name = "NPWP", length = 120)
    var npwp :String ="",

    @Column(name = "TANGGAL_PENGUKUHAN_PKP")
    @Temporal(TemporalType.DATE)
    var tanggalPengukuhanPkp :Date =Date(),

    @Column(name = "STATUS_ACTIVE")
    var statusActive  :Boolean =true,

    @Column(name = "TOP", length = 4)
    var top  :Int =0,

    //PORT WS:: UNTUK TRANSAKSI PEMBALIAN DAN PENJUALAN
    @Column(name = "WSPORT", length = 10)
    var wsport: String? = "",

    @Column(name = "DISC1REGMANUAL")
    var disc1RegManual  :Boolean =false,

    @Column(name = "DISCPLUSREGMANUAL")
    var isDiscPlusRegManual  :Boolean =false,

    /*
	 * Tidak Wajib Disi: karena untuk aturan yang bersifat opsional
	 * - Jika Salesman Exclusive maka hanya boleh menjual barang dari Satu vendor tertentu atau Beberapa Vendor
	 */
    //    @ManyToOne
    //	@JoinColumn(name="fsalesmanBean", referencedColumnName="ID", nullable :Boolean =true,)
    //	private FSalesman fsalesmanBean;
//    @Column(name = "fsalesmanBean")
//    var fsalesmanBean  :Int =0,

    @Column(name = "CREATED")
    @Temporal(TemporalType.TIMESTAMP)
    var created :Date =Date(),

    @Column(name = "MODIFIED")
    @Temporal(TemporalType.TIMESTAMP)
    var modified :Date =Date(),

    @Column(name = "MODIFIED_BY", length = 20)
    var modifiedBy :String ="", //User ID

    //TAMBAHAN
    @Transient
    var tempString1 :String ="",

    @Transient
    var tempString2 :String ="",

    @Transient
    var tempInt1  :Int =0,

    @Transient
    var tempInt2  :Int =0,

    @Transient
    var tempDouble1  :Double =0.0,

    @Transient
    var tempDouble2  :Double =0.0,

    @Transient
    var tempDouble31  :Double =0.0,

    @Transient
    var tempDouble32  :Double =0.0


): Serializable

internal fun FVendor.toEntity(): FVendorEntity {
    return FVendorEntity(
        id = id,
        sourceId = sourceId,

        vcode = vcode,
        vname = vname,
        fdivisionBean = fdivisionBean,
        statusActive = statusActive,

        address1 = address1,
        address2 = address2,
        city1 = city1,
        state1 = state1,
        phone = phone,
        email = email,
        joinDate = joinDate,
        lastTrans = lastTrans,
        noRekening = noRekening,
        currency = currency,

        disc2Margin = disc2Margin,
        disc1PlusMargin = disc1PlusMargin,
        pkp = pkp,
        namaPrshFakturPajak = namaPrshFakturPajak,
        namaPengusahaKenaPajak = namaPengusahaKenaPajak,
        tanggalPengukuhanPkp = tanggalPengukuhanPkp,

        top = top,
        wsport = wsport,
        disc1RegManual = disc1RegManual,
        isDiscPlusRegManual = isDiscPlusRegManual,

        tempString1 = tempString1,
        tempString2 = tempString2,
        tempInt1 = tempInt1,
        tempInt2 = tempInt2,
        tempDouble1 = tempDouble1,
        tempDouble2 = tempDouble2,
        tempDouble31 = tempDouble31,
        tempDouble32 = tempDouble32,

        created = created,
        modified = modified,
        modifiedBy = modifiedBy
    )
}


internal fun FVendor.toReponse(): FVendorRes {
    return FVendorRes(
        id = id,
        sourceId = sourceId,

        vcode = vcode,
        vname = vname,
        fdivisionBean = fdivisionBean,
        statusActive = statusActive,

        address1 = address1,
        address2 = address2,
        city1 = city1,
        state1 = state1,
        phone = phone,
        email = email,
        joinDate = joinDate,
        lastTrans = lastTrans,
        noRekening = noRekening,
        currency = currency,

        disc2Margin = disc2Margin,
        disc1PlusMargin = disc1PlusMargin,
        pkp = pkp,
        namaPrshFakturPajak = namaPrshFakturPajak,
        namaPengusahaKenaPajak = namaPengusahaKenaPajak,
        tanggalPengukuhanPkp = tanggalPengukuhanPkp,

        top = top,
        wsport = wsport,
        disc1RegManual = disc1RegManual,
        isDiscPlusRegManual = isDiscPlusRegManual,

        tempString1 = tempString1,
        tempString2 = tempString2,
        tempInt1 = tempInt1,
        tempInt2 = tempInt2,
        tempDouble1 = tempDouble1,
        tempDouble2 = tempDouble2,
        tempDouble31 = tempDouble31,
        tempDouble32 = tempDouble32,

        created = created,
        modified = modified,
        modifiedBy = modifiedBy
    )
}
