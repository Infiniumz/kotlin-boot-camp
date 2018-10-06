package io.rybalkinsd.kotlinbootcamp.practice

/**
 * "8".toBinary() == "1000"
 *
 * @throws NumberFormatException if could not be parsed as Number
 * @throws IllegalArgumentException if target string is null
 */
fun String?.toBinary(): String {
    var x: Long = 0L
    var str: String = ""
    if (this == null) {
        throw IllegalArgumentException()
    }
    try {
        x = this.toLong()
    } catch (e: Exception) {
        throw NumberFormatException()
    }
    while (x > 0) {
        str += (x % 2).toString()
        x /= 2
    }
    return str.reversed()
}
