package com.erp.distribution.desgreenrestkt.model

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import java.io.Serializable
import javax.persistence.*

@JacksonXmlRootElement
@Entity
@Table(name = "ftpurchased_expenses")
class FtPurchasedExpenses : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private val id: Long = 0

    @Column(name = "NO_URUT", length = 4)
    private val noUrut = 0

    @Column(name = "AMOUNT")
    private val amount = 0.0

    @Column(name = "NOTES", length = 120)
    private val notes = ""

    /*
	 * HPP Pasti Apply To Item untuk DES
	 * Jika ditaruh sebagai biaya maka pasti akan dihitungkan sebagai HPP
	 *  
	 */
    //	@Column(name="APPLY_TO_ITEM")
    //	private boolean applyToItem=false;
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
    private val payToOtherVendor = false

    //	@ManyToOne
    //	@JoinColumn(name="ftPurchasehBean", referencedColumnName="refno")
    //	private FtPurchaseh ftPurchasehBean;
    @Column(name = "ftPurchasehBean", nullable = false)
    private val ftPurchasehBean: Long = 0

    //	@ManyToOne
    //	@JoinColumn(name="accAccountBean", referencedColumnName="ID")
    //	private AccAccount accAccountBean;
    @Column(name = "accAccountBean", nullable = false)
    private val accAccountBean = 0
}