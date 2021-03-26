package com.erp.distribution.desgreenrestkt.domain.model

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import java.io.Serializable
import javax.persistence.*

@JacksonXmlRootElement
data class FtPurchasedExpenses (
    val id: Long =0L,

    @Column(name = "NO_URUT", length = 4)
    val noUrut  :Int =0,

    @Column(name = "AMOUNT")
    val amount  :Double =0.0,

    @Column(name = "NOTES", length = 120)
    val notes :String ="",

    /*
	 * HPP Pasti Apply To Item untuk DES
	 * Jika ditaruh sebagai biaya maka pasti akan dihitungkan sebagai HPP
	 *  
	 */
    //	@Column(name="APPLY_TO_ITEM")
    //	boolean applyToItem=false;
    /*
	 * Jika pay to other vendor=true, maka:
	 * - Akan menjadi Journal tersendiri:
	 * - Hutang vendor lain -> pada Biaya
	 * 
	 * Jika false
	 *  - akan ikut total pada nota tersebut
	 * 
	 */
    @Column(name = "PAY_TO_OTHER_VENDOR")
    val payToOtherVendor  :Boolean =false,

    //	@ManyToOne
    //	@JoinColumn(name="ftPurchasehBean", referencedColumnName="refno")
    //	FtPurchaseh ftPurchasehBean;
    @Column(name = "ftPurchasehBean", nullable = false)
    val ftPurchasehBean: Long =0L,

    //	@ManyToOne
    //	@JoinColumn(name="accAccountBean", referencedColumnName="ID")
    //	AccAccount accAccountBean;
    @Column(name = "accAccountBean", nullable = false)
    val accAccountBean  :Int =0

): Serializable