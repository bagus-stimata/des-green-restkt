package com.erp.distribution.desgreenrestkt.domain.model.enum

enum class EnumMonth(var intId: Int, var descIndonesia: String, var descEnglish: String) {
    JAN(0, "Januari", "January"), FEB(1, "Februari", "February"), MAR(2, "Maret", "March"), APRL(3, "April", "April"), MEI(
        4,
        "Mei",
        "May"
    ), JUN(5, "Juni", "June"), JUL(6, "Juli", "July"), AGT(7, "Agustus", "August"), SEP(8, "September", "September"), OKT(
        9,
        "Oktober",
        "October"
    ), NOP(10, "Nopember", "November"), DES(11, "Desember", "December");

//    var strCode: String? = null

    companion object {
        fun getMonth(month_Int: Int): EnumMonth? {
            var enumMonthSelected: EnumMonth? = null
            when (month_Int) {
                0 -> enumMonthSelected = JAN
                1 -> enumMonthSelected = FEB
                2 -> enumMonthSelected = MAR
                3 -> enumMonthSelected = APRL
                4 -> enumMonthSelected = MEI
                5 -> enumMonthSelected = JUN
                6 -> enumMonthSelected = JUL
                7 -> enumMonthSelected = AGT
                8 -> enumMonthSelected = SEP
                9 -> enumMonthSelected = OKT
                10 -> enumMonthSelected = NOP
                11 -> enumMonthSelected = DES
                else -> {
                }
            }
            return enumMonthSelected
        }
    }

}