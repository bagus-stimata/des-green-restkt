package com.erp.distribution.desgreenrestkt.data.source.entity

import com.erp.distribution.desgreenrestkt.domain.model.enum.EnumTipeSuratJalan
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import java.io.Serializable
import java.util.*
import javax.persistence.*

@JacksonXmlRootElement
@Entity
@Table(name = "ftsalesh_history_sj")
data class FtSaleshHistorySJ (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    var id: Long =0L,

    @Column(name = "TIPE_SURAT_JALAN")
    @Enumerated(EnumType.ORDINAL)
    var tipeSuratJalan: EnumTipeSuratJalan = EnumTipeSuratJalan.OTH1,

    @Column(name = "SJ_NUMBER", length = 20)
    var sjNumber :String ="",

    @Temporal(TemporalType.DATE)
    @Column(name = "SJ_DATE")
    var sjDate :Date =Date(),

    @Column(name = "DRIVER_NAME", length = 40)
    var driverName :String ="",

    /*
	 * Di pakai untuk klarifikasi di kemudian hari jika ada yang berusahan untuk menghapus atau melakukan upaya-upaya kecurangan
	 * pada Surat Jalan Pengiriman
	 */
    @Column(name = "TOTAL_PCS_KIRIM")
    var totalPcsKirimTagih  :Int =0,

    //Total saat kirim
    @Column(name = "AMOUNT_AFTER_DISC_PLUS_AFTERPPN")
    var amountAfterDiscPlusRpAfterPpn  :Double =0.0,

    //	@ManyToOne
    //	@JoinColumn(name="ftSaleshBean", referencedColumnName="refno")
    //	private FtSalesh ftSaleshBean;
    @Column(name = "ftSaleshBean", nullable = false)
    var ftSaleshBean: Long =0L

): Serializable