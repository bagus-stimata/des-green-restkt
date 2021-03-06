package com.erp.distribution.desgreenrestkt.domain.utils

import com.erp.distribution.desgreenrestkt.data.source.entity.FMaterialEntity
import java.text.NumberFormat

class KonversiProductAndStockHelperImpl : KonversiProductAndStockHelper {
    private val serialVersionUID = -3353307136697624454L
    var fMaterialEntity: FMaterialEntity? = FMaterialEntity()
    var pcsOrUom4 = 0.0

    constructor() {}
    constructor(pcsOrUom4: Double, fMaterialEntity: FMaterialEntity?) {
        this.pcsOrUom4 = pcsOrUom4
        this.fMaterialEntity = fMaterialEntity
    }

    constructor(pcsOrUom4: Double, convfact1: Int, convfact2: Int, convfact3: Int) {
        this.pcsOrUom4 = pcsOrUom4
        fMaterialEntity = FMaterialEntity()
        fMaterialEntity!!.convfact1 = convfact1
        fMaterialEntity!!.convfact2 = convfact2
        fMaterialEntity!!.convfact3 = convfact3
    }

    override fun setValue(pcsOrUom4: Double, fMaterialEntity: FMaterialEntity?) {
        this.pcsOrUom4 = pcsOrUom4
        this.fMaterialEntity = fMaterialEntity
    }

    //maka double
    override val uom1FromSmallest: Double
        get() {
            var valueBiggest = 0.0
            if (fMaterialEntity!!.convfact1 == 1) {
                try {
                    valueBiggest = pcsOrUom4 / fMaterialEntity!!.convfact1 //maka double
                } catch (ex: Exception) {
                }
            } else {
                try {
                    valueBiggest = pcsOrUom4.toInt() / fMaterialEntity!!.convfact1.toDouble()
                } catch (ex: Exception) {
                }
            }
            return valueBiggest
        }//dan seterusnya pasti satu

    //Asumsi Convfact2,3 pasti 1
    override val uom2FromSmallest: Double
        get() {
            var valueUom2 = 0.0
            if (fMaterialEntity!!.convfact1 == 1) { //Asumsi Convfact2,3 pasti 1
                valueUom2 = 0.0
            } else if (fMaterialEntity!!.convfact2 == 1) { //dan seterusnya pasti satu
                try {
                    val sisaQtyUom1 = pcsOrUom4 % fMaterialEntity!!.convfact1
                    valueUom2 = sisaQtyUom1 / fMaterialEntity!!.convfact2
                } catch (ex: Exception) {
                }
            } else {
                try {
                    val sisaQtyUom1 = pcsOrUom4.toInt() % fMaterialEntity!!.convfact1
                    valueUom2 = sisaQtyUom1 / fMaterialEntity!!.convfact2.toDouble()
                } catch (ex: Exception) {
                }
            }
            return valueUom2
        }//				int sisaQtyUom1 = (int) pcsOrUom4 % fMaterial.getConvfact1();		//				double sisaQtyUom1 = pcsOrUom4 % fMaterial.getConvfact1();		//dan seterusnya pasti satu

    //Asumsi Convfact2,3 pasti 1
    val uom2_234FromSmallest: Double
        get() {
            var valueUom2 = 0.0
            if (fMaterialEntity!!.convfact1 == 1) { //Asumsi Convfact2,3 pasti 1
                valueUom2 = 0.0
            } else if (fMaterialEntity!!.convfact2 == 1) { //dan seterusnya pasti satu
                try {
//				double sisaQtyUom1 = pcsOrUom4 % fMaterial.getConvfact1();		
                    valueUom2 = pcsOrUom4 / fMaterialEntity!!.convfact2
                } catch (ex: Exception) {
                }
            } else {
                try {
//				int sisaQtyUom1 = (int) pcsOrUom4 % fMaterial.getConvfact1();		
                    valueUom2 = pcsOrUom4.toInt() / fMaterialEntity!!.convfact2.toDouble()
                } catch (ex: Exception) {
                }
            }
            return valueUom2
        }//Asumsi Convfact 3 pasti 1

    //Asumsi Convfact2,3 pasti 1
    override val uom3FromSmallest: Double
        get() {
            var valueUom3 = 0.0
            if (fMaterialEntity!!.convfact1 == 1) { //Asumsi Convfact2,3 pasti 1
                valueUom3 = 0.0
            } else if (fMaterialEntity!!.convfact2 == 1) { //Asumsi Convfact 3 pasti 1
                valueUom3 = 0.0
            } else if (fMaterialEntity!!.convfact3 == 1) {
                try {
                    val sisaQtyUom1 = pcsOrUom4.toInt() % fMaterialEntity!!.convfact1
                    val sisaQtyUom2 = sisaQtyUom1 % fMaterialEntity!!.convfact2
                    valueUom3 = sisaQtyUom2 / fMaterialEntity!!.convfact3.toDouble()
                } catch (ex: Exception) {
                }
            } else {
                try {
                    val sisaQtyUom1 = pcsOrUom4.toInt() % fMaterialEntity!!.convfact1
                    val sisaQtyUom2 = sisaQtyUom1 % fMaterialEntity!!.convfact2
                    valueUom3 = sisaQtyUom2 / fMaterialEntity!!.convfact3.toDouble()
                } catch (ex: Exception) {
                }
            }
            return valueUom3
        }//			System.out.println("Uom4 masuk rule4");//Asumsi Convfact 3 pasti 1

    //Asumsi Convfact2,3 pasti 1
    override val uom4FromSmallest: Double
        get() {
            var valueUom4 = 0.0
            if (fMaterialEntity!!.convfact1 == 1) { //Asumsi Convfact2,3 pasti 1
                valueUom4 = 0.0
            } else if (fMaterialEntity!!.convfact2 == 1) { //Asumsi Convfact 3 pasti 1
                valueUom4 = 0.0
            } else if (fMaterialEntity!!.convfact3 == 1) {
                valueUom4 = 0.0
            } else {
//			System.out.println("Uom4 masuk rule4");
                try {
                    val sisaQtyUom1 = pcsOrUom4 % fMaterialEntity!!.convfact1
                    val sisaQtyUom2 = sisaQtyUom1 % fMaterialEntity!!.convfact2
                    val sisaQtyUom3 = sisaQtyUom2 % fMaterialEntity!!.convfact3
                    valueUom4 = sisaQtyUom3
                } catch (ex: Exception) {
                }
            }
            return valueUom4
        }

    override val pPriceBeforePpnFromFMaterialBeforePpn: Double
        get() = pcsOrUom4 * fMaterialEntity!!.pprice2

    override val sPriceBeforePpnFromFMaterialBeforePpn: Double
        get() = pcsOrUom4 * fMaterialEntity!!.sprice2

    override val pPriceBeforePpnFromFMaterialAfterPpn: Double
        get() = pcsOrUom4 * fMaterialEntity!!.pprice2AfterPpn

    override val sPriceBeforePpnFromFMaterialAfterPpn: Double
        get() = pcsOrUom4 * fMaterialEntity!!.sprice2AfterPpn

    override fun getUom1234String(jumlahDigit: Double): String {
        val nf_3 = NumberFormat.getInstance()
        nf_3.maximumFractionDigits = 3

//		String strUom1 = String.valueOf(getUom1FromSmallest());
//		String strUom2 = String.valueOf(getUom2FromSmallest());
//		String strUom3 = String.valueOf(getUom3FromSmallest());		
//		String strUom4 = String.valueOf(getUom4FromSmallest());		
        val strUom1 = nf_3.format(uom1FromSmallest)
        var strUom2 = nf_3.format(uom2FromSmallest)
        var strUom3 = nf_3.format(uom3FromSmallest)
        var strUom4 = nf_3.format(uom4FromSmallest)
        val lenUom2 = uom2FromSmallest.toString().length.toDouble()
        var jumlahTambahanNol = jumlahDigit - lenUom2
        var tambahanNol = ""
        run {
            var i = 0.0
            while (i < jumlahTambahanNol) {
                tambahanNol += "0"
                i++
            }
        }
        strUom2 = tambahanNol + strUom2
        val lenUom3 = uom3FromSmallest.toString().length.toDouble()
        jumlahTambahanNol = jumlahDigit - lenUom3
        tambahanNol = ""
        run {
            var i = 0.0
            while (i < jumlahTambahanNol) {
                tambahanNol += "0"
                i++
            }
        }
        strUom3 = tambahanNol + strUom3
        val lenUom4 = uom4FromSmallest.toString().length.toDouble()
        jumlahTambahanNol = jumlahDigit - lenUom4
        tambahanNol = ""
        var i = 0.0
        while (i < jumlahTambahanNol) {
            tambahanNol += "0"
            i++
        }
        strUom4 = tambahanNol + strUom4
        return "$strUom1.$strUom2.$strUom3.$strUom4"
    }

    //		String strUom1 = String.valueOf(getUom1FromSmallest());
//		String strUom2 = String.valueOf(getUom2FromSmallest());
//		String strUom3 = String.valueOf(getUom3FromSmallest());		
//		String strUom4 = String.valueOf(getUom4FromSmallest());		
    override val uom1234StringUom: String
        get() {
            val nf_3 = NumberFormat.getInstance()
            nf_3.maximumFractionDigits = 3

//		String strUom1 = String.valueOf(getUom1FromSmallest());
//		String strUom2 = String.valueOf(getUom2FromSmallest());
//		String strUom3 = String.valueOf(getUom3FromSmallest());		
//		String strUom4 = String.valueOf(getUom4FromSmallest());		
            var strUom1 = nf_3.format(uom1FromSmallest)
            var strUom2 = nf_3.format(uom2FromSmallest)
            var strUom3 = nf_3.format(uom3FromSmallest)
            var strUom4 = nf_3.format(uom4FromSmallest)
            if (uom1FromSmallest == 0.0) {
                strUom1 = ""
            } else {
                strUom1 += " " + fMaterialEntity!!.uom1
            }
            if (uom2FromSmallest == 0.0) {
                strUom2 = ""
            } else {
                strUom2 += " " + fMaterialEntity!!.uom2
            }
            if (uom3FromSmallest == 0.0) {
                strUom3 = ""
            } else {
                strUom3 += " " + fMaterialEntity!!.uom3
            }
            if (uom4FromSmallest == 0.0) {
                strUom4 = ""
            } else {
                strUom4 += " " + fMaterialEntity!!.uom4
            }
            return "$strUom1 $strUom2 $strUom3 $strUom4".trim { it <= ' ' }
        }

    //		String strUom1 = String.valueOf(getUom1FromSmallest());
//		String strUom2 = String.valueOf(getUom2FromSmallest());
//		String strUom3 = String.valueOf(getUom3FromSmallest());		
//		String strUom4 = String.valueOf(getUom4FromSmallest());		
    override val uom1234StringUomHurufBesar: String
        get() {
            val nf_3 = NumberFormat.getInstance()
            nf_3.maximumFractionDigits = 3

//		String strUom1 = String.valueOf(getUom1FromSmallest());
//		String strUom2 = String.valueOf(getUom2FromSmallest());
//		String strUom3 = String.valueOf(getUom3FromSmallest());		
//		String strUom4 = String.valueOf(getUom4FromSmallest());		
            var strUom1 = nf_3.format(uom1FromSmallest)
            var strUom2 = nf_3.format(uom2FromSmallest)
            var strUom3 = nf_3.format(uom3FromSmallest)
            var strUom4 = nf_3.format(uom4FromSmallest)
            if (uom1FromSmallest == 0.0) {
                strUom1 = ""
            } else {
                strUom1 += " " + fMaterialEntity!!.uom1.toUpperCase()
            }
            if (uom2FromSmallest == 0.0) {
                strUom2 = ""
            } else {
                strUom2 += " " + fMaterialEntity!!.uom2.toUpperCase()
            }
            if (uom3FromSmallest == 0.0) {
                strUom3 = ""
            } else {
                strUom3 += " " + fMaterialEntity!!.uom3.toUpperCase()
            }
            if (uom4FromSmallest == 0.0) {
                strUom4 = ""
            } else {
                strUom4 += " " + fMaterialEntity!!.uom4.toUpperCase()
            }
            return "$strUom1 $strUom2 $strUom3 $strUom4".trim { it <= ' ' }
        }

    override fun getUom1234StringUom(fmaterialBean: FMaterialEntity?, qtyStock_Smallest: Double): String {
        fMaterialEntity = fmaterialBean
        pcsOrUom4 = qtyStock_Smallest
        val nf_3 = NumberFormat.getInstance()
        nf_3.maximumFractionDigits = 3

//		String strUom1 = String.valueOf(getUom1FromSmallest());
//		String strUom2 = String.valueOf(getUom2FromSmallest());
//		String strUom3 = String.valueOf(getUom3FromSmallest());		
//		String strUom4 = String.valueOf(getUom4FromSmallest());		
        var strUom1 = nf_3.format(uom1FromSmallest)
        var strUom2 = nf_3.format(uom2FromSmallest)
        var strUom3 = nf_3.format(uom3FromSmallest)
        var strUom4 = nf_3.format(uom4FromSmallest)
        if (uom1FromSmallest == 0.0) {
            strUom1 = ""
        } else {
            strUom1 += " " + fMaterialEntity!!.uom1
        }
        if (uom2FromSmallest == 0.0) {
            strUom2 = ""
        } else {
            strUom2 += " " + fMaterialEntity!!.uom2
        }
        if (uom3FromSmallest == 0.0) {
            strUom3 = ""
        } else {
            strUom3 += " " + fMaterialEntity!!.uom3
        }
        if (uom4FromSmallest == 0.0) {
            strUom4 = ""
        } else {
            strUom4 += " " + fMaterialEntity!!.uom4
        }
        return "$strUom1 $strUom2 $strUom3 $strUom4".trim { it <= ' ' }
    }

    override fun getUom1234StringUomHtml(fmaterialBean: FMaterialEntity?, qtyStock_Smallest: Double): String {
        fMaterialEntity = fmaterialBean
        pcsOrUom4 = qtyStock_Smallest
        val nf_3 = NumberFormat.getInstance()
        nf_3.maximumFractionDigits = 3

//		String strUom1 = "<font color=\"blue\">" + nf_3.format(getUom1FromSmallest()) + "</font>";
        var strUom1 = nf_3.format(uom1FromSmallest)
        var strUom2 = nf_3.format(uom2FromSmallest)
        var strUom3 = nf_3.format(uom3FromSmallest)
        var strUom4 = nf_3.format(uom4FromSmallest)
        if (uom1FromSmallest == 0.0) {
            strUom1 = ""
        } else {
            strUom1 += " <small>" + fMaterialEntity!!.uom1 + "</small> "
        }
        if (uom2FromSmallest == 0.0) {
            strUom2 = ""
        } else {
            strUom2 += " <small>" + fMaterialEntity!!.uom2 + "</small> "
        }
        if (uom3FromSmallest == 0.0) {
            strUom3 = ""
        } else {
            strUom3 += " <small>" + fMaterialEntity!!.uom3 + "</small> "
        }
        if (uom4FromSmallest == 0.0) {
            strUom4 = ""
        } else {
            strUom4 += " <small>" + fMaterialEntity!!.uom4 + "</small> "
        }
        return "$strUom1$strUom2 $strUom3 $strUom4".trim { it <= ' ' }
    }

    override fun getUom_234StringUomHtml(fmaterialBean: FMaterialEntity?, qtyStock_Smallest: Double): String {
        fMaterialEntity = fmaterialBean
        pcsOrUom4 = qtyStock_Smallest
        val nf_3 = NumberFormat.getInstance()
        nf_3.maximumFractionDigits = 3

//		String strUom1 = "<font color=\"blue\">" + String.valueOf(getUom1FromSmallest()) + "</font>";

//		double qtyUom2 = pcsOrUom4 / fMaterial.getConvfact2();		
//		String strUom2 = nf_3.format(qtyUom2);
        var strUom2 = nf_3.format(uom2_234FromSmallest)
        var strUom3 = nf_3.format(uom3FromSmallest)
        var strUom4 = nf_3.format(uom4FromSmallest)

//		if (getUom1FromSmallest()==0) {
//			strUom1="";
//		}else {
//			strUom1 += " <small>" + fMaterial.getUom1() + "</small> ";
//		}
        if (uom2_234FromSmallest == 0.0) {
            strUom2 = ""
        } else {
            strUom2 += " <small>" + fMaterialEntity!!.uom2 + "</small> "
        }
        //		if (getUom2FromSmallest()==0){
//			strUom2="";
//		}else {
//			strUom2 += " <small>" + fMaterial.getUom2() + "</small> ";			
//		}
        if (uom3FromSmallest == 0.0) {
            strUom3 = ""
        } else {
            strUom3 += " <small>" + fMaterialEntity!!.uom3 + "</small> "
        }
        if (uom4FromSmallest == 0.0) {
            strUom4 = ""
        } else {
            strUom4 += " <small>" + fMaterialEntity!!.uom4 + "</small> "
        }
        return "$strUom2 $strUom3 $strUom4".trim { it <= ' ' }
    }

    override fun getSmallestFromUom1234(fMaterialEntityBean: FMaterialEntity, uom1: Double, uom2: Double,
                                        uom3: Double, uom4: Double): Double {
        var qtyPcs = uom1 * fMaterialEntityBean.convfact1 + uom2 * fMaterialEntityBean.convfact2 + uom3 * fMaterialEntityBean.convfact3 + uom4
        return qtyPcs
    }

    override fun getUom1234StringUom_FromConvfact(qtyStock_Smallest: Double, convfact1: Int, convfact2: Int, convfact3: Int,
                                                  uom1: String?, uom2: String?, uom3: String?, uom4: String?): String {
        fMaterialEntity = FMaterialEntity()
        fMaterialEntity!!.convfact1 = convfact1
        fMaterialEntity!!.convfact2 = convfact2
        fMaterialEntity!!.convfact3 = convfact3
        fMaterialEntity!!.uom1 = uom1!!
        fMaterialEntity!!.uom2 = uom2!!
        fMaterialEntity!!.uom3 = uom3!!
        fMaterialEntity!!.uom4 = uom4!!
        pcsOrUom4 = qtyStock_Smallest
        val nf_3 = NumberFormat.getInstance()
        nf_3.maximumFractionDigits = 3
        var strUom1 = nf_3.format(uom1FromSmallest)
        var strUom2 = nf_3.format(uom2FromSmallest)
        var strUom3 = nf_3.format(uom3FromSmallest)
        var strUom4 = nf_3.format(uom4FromSmallest)
        if (uom1FromSmallest == 0.0) {
            strUom1 = ""
        } else {
            strUom1 += " " + fMaterialEntity!!.uom1
        }
        if (uom2FromSmallest == 0.0) {
            strUom2 = ""
        } else {
            strUom2 += " " + fMaterialEntity!!.uom2
        }
        if (uom3FromSmallest == 0.0) {
            strUom3 = ""
        } else {
            strUom3 += " " + fMaterialEntity!!.uom3
        }
        if (uom4FromSmallest == 0.0) {
            strUom4 = ""
        } else {
            strUom4 += " " + fMaterialEntity!!.uom4
        }
        return "$strUom1 $strUom2 $strUom3 $strUom4".trim { it <= ' ' }
    }

    //		double qtyUom2 = pcsOrUom4 / fMaterial.getConvfact2();	
//		String strUom2 = nf_3.format(qtyUom2);

    //		if (getUom1FromSmallest()==0) {
//			strUom1="";
//		}else {
//			strUom1 += " " + fMaterial.getUom1().toUpperCase();
//		}
    override val uom_234StringUom: String
        get() {
            val nf_3 = NumberFormat.getInstance()
            nf_3.maximumFractionDigits = 3

//		double qtyUom2 = pcsOrUom4 / fMaterial.getConvfact2();	
//		String strUom2 = nf_3.format(qtyUom2);
            var strUom2 = nf_3.format(uom2_234FromSmallest)
            var strUom3 = nf_3.format(uom3FromSmallest)
            var strUom4 = nf_3.format(uom4FromSmallest)

//		if (getUom1FromSmallest()==0) {
//			strUom1="";
//		}else {
//			strUom1 += " " + fMaterial.getUom1().toUpperCase();
//		}
            if (uom2_234FromSmallest == 0.0) {
                strUom2 = ""
            } else {
                strUom2 += " " + fMaterialEntity!!.uom2
            }
            if (uom3FromSmallest == 0.0) {
                strUom3 = ""
            } else {
                strUom3 += " " + fMaterialEntity!!.uom3
            }
            if (uom4FromSmallest == 0.0) {
                strUom4 = ""
            } else {
                strUom4 += " " + fMaterialEntity!!.uom4
            }
            return "$strUom2 $strUom3 $strUom4".trim { it <= ' ' }
        }

    //		double qtyUom2 = pcsOrUom4 / fMaterial.getConvfact2();		
//		String strUom2 = nf_3.format(qtyUom2);

    //		if (getUom1FromSmallest()==0) {
//			strUom1="";
//		}else {
//			strUom1 += " " + fMaterial.getUom1().toUpperCase();
//		}
    override val uom_234StringUomHurufBesar: String
        get() {
            val nf_3 = NumberFormat.getInstance()
            nf_3.maximumFractionDigits = 3

//		double qtyUom2 = pcsOrUom4 / fMaterial.getConvfact2();		
//		String strUom2 = nf_3.format(qtyUom2);
            var strUom2 = nf_3.format(uom2_234FromSmallest)
            var strUom3 = nf_3.format(uom3FromSmallest)
            var strUom4 = nf_3.format(uom4FromSmallest)

//		if (getUom1FromSmallest()==0) {
//			strUom1="";
//		}else {
//			strUom1 += " " + fMaterial.getUom1().toUpperCase();
//		}
            if (uom2_234FromSmallest == 0.0) {
                strUom2 = ""
            } else {
                strUom2 += " " + fMaterialEntity!!.uom2.toUpperCase()
            }
            if (uom3FromSmallest == 0.0) {
                strUom3 = ""
            } else {
                strUom3 += " " + fMaterialEntity!!.uom3.toUpperCase()
            }
            if (uom4FromSmallest == 0.0) {
                strUom4 = ""
            } else {
                strUom4 += " " + fMaterialEntity!!.uom4.toUpperCase()
            }
            return "$strUom2 $strUom3 $strUom4".trim { it <= ' ' }
        }




}