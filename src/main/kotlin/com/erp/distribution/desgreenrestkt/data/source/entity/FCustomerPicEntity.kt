package com.erp.distribution.desgreenrestkt.data.source.entity

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import java.io.Serializable
import java.util.*
import javax.persistence.*

@JacksonXmlRootElement
@Entity
@Table(name = "fcustomer_pic")
data class FCustomerPicEntity (
    //	@ManyToOne
    //	@JoinColumn(name="fcustomerBean")
    //	private FCustomer fcustomerBean;
    @Column(name = "fcustomerBean", nullable = false)
    var fcustomerBean :Int =0,

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    var id: Long =0L,

    /*
	 * Nomor Urut:
	 * 1 = adalah gambar produk depan (Utama)
	 * 2 = adalah gambar produk sisi lain
	 * 3 = adalah gambar produk sisi lain
	 * 4 = aalah gambar produk sisi lain
	 */
    @Column(name = "NOMOR_URUT", length = 4)
    var nomorUrut :Int =0,

    //DOC, PIC, PDF
    @Column(name = "TIPE_FILE", length = 20)
    var tipeFile :String ="",

    @Column(name = "image_name")
    var imageName :String ="",

    @Column(name = "TITLE", length = 155)
    var title :String ="",

    @Column(name = "NOTES", length = 255)
    var description :String ="",

    /*
     * MOBILE = diupload dari mobile
     * APP_WEB = dari aplikasi Web
     */
    @Column(name = "UPLOAD_FROM", length = 50)
    var uploadFrom :String ="",

    //	@Column(name = "image_capture_by", length=30)
    //	private String imageCapturedBy :String ="",;
    @Column(name = "CREATED")
    @Temporal(TemporalType.TIMESTAMP)
    var created :Date =Date(),

    @Column(name = "MODIFIED")
    @Temporal(TemporalType.TIMESTAMP)
    var modified :Date =Date(),

    @Column(name = "MODIFIED_BY", length = 20)
    var modifiedBy :String ="" //User ID

): Serializable