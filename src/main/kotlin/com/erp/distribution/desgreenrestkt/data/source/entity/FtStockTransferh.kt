package com.erp.distribution.desgreenrestkt.data.source.entity

import com.erp.distribution.desgreenrestkt.domain.model.modelenum.EnumTipeStockTransfer
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import java.util.*
import javax.persistence.*

@JacksonXmlRootElement
@Entity
@Table(name = "ftstocktransferh")
class FtStockTransferh {
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
    @Column(name = "SOURCE_ID")
    var sourceId: Long = 0

    @Column(name = "NO_REK", length = 30)
    var noRek = ""

    @Column(name = "TR_DATE")
    @Temporal(TemporalType.DATE)
    var trDate = Date()

    /*
	 * PENGGUNAANYA MENGGUNAKAN PARAMETER SISTEM
	 * JIKA TIPE 0 = MAKA DEFAULT DAN TIDAK MENGGUNAKAN JURNAL DAN TIDAK MODE DITERIMA ATAU TIDAK
	 * JIKA TIPE 1= Maka terdapat jurnal
	 * 		a. Saat Kirim (yaitu saat nomor gudang diterbitkan)
	 * 		b. Saat Diterima 
	 * 		User penerima hanya bisa memberi tanda diterima dan memasukkan Qty Kembali (Kekurangan jika barang tidak ada atau rusak)
	 */
    //	@Column(name="RECEIVED")
    //	private boolean received =true; //Default adalah tidak memakai jurnal sehingga true
    @Column(name = "RECEIPT_BYDEST")
    var isReceiptByDest = false

    @Temporal(TemporalType.DATE)
    @Column(name = "GOODRECEIPT_DATE")
    var goodReceiptDate = Date()

    @Column(name = "TIPE_STOCK_TRANSFER")
    @Enumerated(EnumType.ORDINAL)
    var tipeStockTransfer: EnumTipeStockTransfer? = null

    @Column(name = "NOTES", length = 170)
    var notes = ""

    /*
	 * Jumlah keseluruhan
	 */
    @Column(name = "AMOUNT")
    var amountRp = 0.0

    @Column(name = "END_OF_DAY")
    var isEndOfDay = false

    @Column(name = "PRINT_COUNTER", length = 4)
    var printCounter = 0

    @Transient
    var tempInt = 0

    //	@ManyToOne
    //	@JoinColumn(name="fdivisionBean", referencedColumnName="ID", nullable=false)
    //	private FDivision fdivisionBean;
    @Column(name = "fdivisionBean", nullable = false)
    var fdivisionBean = 0

    //	@ManyToOne
    //	@JoinColumn(name="fwarehouseBeanFrom", referencedColumnName="ID", nullable=false)
    //	private FWarehouse fwarehouseBeanFrom;
    @Column(name = "fwarehouseBeanFrom", nullable = false)
    var fwarehouseBeanFrom = 0

    //	@ManyToOne
    //	@JoinColumn(name="fwarehouseBeanTo", referencedColumnName="ID", nullable=false)
    //	private FWarehouse fwarehouseBeanTo;
    @Column(name = "fwarehouseBeanTo", nullable = false)
    var fwarehouseBeanTo = 0

    //	@ManyToOne
    //	@JoinColumn(name="ftPurchaseh_ReqBean", referencedColumnName="refno", nullable=true)
    //	private FtPurchaseh ftPurchaseh_ReqBean;
    @Column(name = "ftPurchaseh_ReqBean")
    var ftPurchaseh_ReqBean: Long = 0

    @Column(name = "CREATED")
    @Temporal(TemporalType.TIMESTAMP)
    var created = Date()

    @Column(name = "MODIFIED")
    @Temporal(TemporalType.TIMESTAMP)
    var modified = Date()

    @Column(name = "MODIFIED_BY", length = 20)
    var modifiedBy = "" //User ID

}