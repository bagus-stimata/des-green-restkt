package com.erp.distribution.desgreenrestkt.model

import com.erp.distribution.desgreenrestkt.domain.model.modelenum.EnumTipeSuratJalan
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import java.io.Serializable
import java.util.*
import javax.persistence.*

@JacksonXmlRootElement
@Entity
@Table(name = "ftsalesh_history_sj")
class FtSaleshHistorySJ : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    var id: Long = 0

    @Column(name = "TIPE_SURAT_JALAN")
    @Enumerated(EnumType.ORDINAL)
    var tipeSuratJalan: EnumTipeSuratJalan? = null

    @Column(name = "SJ_NUMBER", length = 20)
    var sjNumber = ""

    @Temporal(TemporalType.DATE)
    @Column(name = "SJ_DATE")
    var sjDate = Date()

    @Column(name = "DRIVER_NAME", length = 40)
    var driverName = ""

    /*
	 * Di pakai untuk klarifikasi di kemudian hari jika ada yang berusahan untuk menghapus atau melakukan upaya-upaya kecurangan
	 * pada Surat Jalan Pengiriman
	 */
    @Column(name = "TOTAL_PCS_KIRIM")
    var totalPcsKirimTagih = 0

    //Total saat kirim
    @Column(name = "AMOUNT_AFTER_DISC_PLUS_AFTERPPN")
    var amountAfterDiscPlusRpAfterPpn = 0.0

    //	@ManyToOne
    //	@JoinColumn(name="ftSaleshBean", referencedColumnName="refno")
    //	private FtSalesh ftSaleshBean;
    @Column(name = "ftSaleshBean", nullable = false)
    var ftSaleshBean: Long = 0

}