package com.erp.distribution.desgreenrestkt.data.source.entity

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import java.io.Serializable
import java.util.*
import javax.persistence.*

@JacksonXmlRootElement
@Entity
@Table(name = "fcustomer_pic")
class FCustomerPic : Serializable {
    //	@ManyToOne
    //	@JoinColumn(name="fcustomerBean")
    //	private FCustomer fcustomerBean;
    @Column(name = "fcustomerBean", nullable = false)
    var fcustomerBean = 0

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    var id: Long = 0

    /*
	 * Nomor Urut:
	 * 1 = adalah gambar produk depan (Utama)
	 * 2 = adalah gambar produk sisi lain
	 * 3 = adalah gambar produk sisi lain
	 * 4 = aalah gambar produk sisi lain
	 */
    @Column(name = "NOMOR_URUT", length = 4)
    var nomorUrut = 0

    //DOC, PIC, PDF
    @Column(name = "TIPE_FILE", length = 20)
    var tipeFile = ""

    @Column(name = "image_name")
    var imageName = ""

    @Column(name = "TITLE", length = 155)
    var title = ""

    @Column(name = "NOTES", length = 255)
    var description = ""

    /*
     * MOBILE = diupload dari mobile
     * APP_WEB = dari aplikasi Web
     */
    @Column(name = "UPLOAD_FROM", length = 50)
    var uploadFrom = ""

    //	@Column(name = "image_capture_by", length=30)
    //	private String imageCapturedBy = "";
    @Column(name = "CREATED")
    @Temporal(TemporalType.TIMESTAMP)
    var created = Date()

    @Column(name = "MODIFIED")
    @Temporal(TemporalType.TIMESTAMP)
    var modified = Date()

    @Column(name = "MODIFIED_BY", length = 20)
    var modifiedBy = "" //User ID

    companion object {
        /**
         *
         */
        const val serialVersionUID = 1L
    }
}