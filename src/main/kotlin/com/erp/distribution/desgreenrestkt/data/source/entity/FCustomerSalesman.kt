package com.erp.distribution.desgreenrestkt.data.source.entity

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import java.io.Serializable
import javax.persistence.*

@JacksonXmlRootElement
@Entity
@Table(name = "fcustomer_salesman")
class FCustomerSalesman : Serializable {
    //	@ManyToOne
    //	@JoinColumn(name="fcustomerBean")
    //	private FCustomer fcustomerBean;
    @Column(name = "fcustomerBean", nullable = false)
    private val fcustomerBean = 0

    //	@ManyToOne
    //	@JoinColumn(name="fsalesmanBean")
    //	private FSalesman fsalesmanBean;
    @Column(name = "fsalesmanBean", nullable = false)
    private val fsalesmanBean = 0

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="hibernate_sequence")
    @SequenceGenerator(
        name = "hibernate_sequence",
        sequenceName = "hibernate_sequence_master",
        allocationSize = 20,
        initialValue = 912345668
    )
    @Column(name = "ID", length = 9)
    private val id = 0

    /*
	 * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
	 * keperluan diantaranya:
	 * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
	 * 2. 
	 */
    @Column(name = "SOURCE_ID", length = 9)
    private val sourceID = 0

    @Column(name = "NO_URUT", length = 4)
    private val noUrut = 0

    @Column(name = "HARIKUNJUNGAN", length = 3)
    private val harikunjungan = 0

    @Column(name = "PEKANKUNJUNGAN", length = 3)
    private val pekankunjungan = 0

    companion object {
        /**
         *
         */
        private const val serialVersionUID = 1L
    }
}