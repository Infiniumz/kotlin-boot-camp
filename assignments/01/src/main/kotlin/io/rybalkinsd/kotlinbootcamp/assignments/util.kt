package io.rybalkinsd.kotlinbootcamp.assignments

/**
 * Returns the greatest of `int` values.
 *
 * @param values an argument. !! Assume values.length > 0. !!
 * @return the largest of values.
 */
fun max(a: Int, b: Int) = if (a > b) a else b

fun max(values: List<Int>): Int {
    var max: Int = values[0]
    values.forEach { max = max(max, it) }
    return max
}

/**
 * Returns the sum of all `int` values.
 *
 * @param values an argument. Assume values.length > 0.
 * @return the sum of all values.
 */
fun sum(values: List<Int>): Long {
    var sum: Long = 0L
    values.forEach { sum += it }
    return sum
}