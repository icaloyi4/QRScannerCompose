package co.id.mii.qrscanner.core.utils

import java.text.DecimalFormat
import java.text.NumberFormat

fun numberFormat(number: Double, formatString : String?="#,###") : String{
    var formatter: NumberFormat = DecimalFormat(formatString)
    return formatter.format(number)
}
