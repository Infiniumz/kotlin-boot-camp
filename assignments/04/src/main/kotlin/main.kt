import java.io.File
// import java.nio.file.Paths
import java.util.Random

fun IntRange.random() =
        Random().nextInt((endInclusive + 1) - start) + start

fun game(mapOfWords: MutableMap<Int, MutableMap<Char, List<String>>>) {
    val numb = (4..5).random()
    val chr = ('a'.toInt()..'z'.toInt()).random().toChar()
    val sz = mapOfWords[numb]!![chr]?.size
    var targetStr = mapOfWords[numb]!![chr]!![(0..sz!!).random()]
    println("Welcome to Bulls and Cows game!")
    println("I offered you a $numb-letter word, try to guess it")
    for (i in (0..10)) {
        val str = readLine().toString()
        var bulls = 0
        var cows = 0
        when {
            str == targetStr -> {
                println("GG WP!")
                return
            }
            str.length != numb -> {
                println("I offered you a $numb-letter word, not a ${str.length}-letter word!")
            }
            else -> {
                var tmp = str.toMutableList()
                var targetTmp = targetStr.toMutableList()
                for (e in 0 until numb) {
                    if (e >= tmp.size) {
                        break
                    }
                    if (tmp[e] == targetTmp[e]) {
                        bulls++
                        tmp.removeAt(e)
                        targetTmp.removeAt(e)
                    }
                }
                for (e in 0 until tmp.size) {
                    if (e >= tmp.size) {
                        break
                    }
                    for (j in 0 until targetTmp.size) {
                        if (tmp[e] == targetTmp[j]) {
                            cows++
                            tmp.removeAt(e)
                            targetTmp.removeAt(j)
                            break
                        }
                    }
                }
                println("Bulls: $bulls \n Cows: $cows")
            }
        }
    }
    println("You've lost, sorry :(")
    return
}

fun main(args: Array<String>) {
    /*val path = Paths.get("").toAbsolutePath().toString()
    val setOfWords = File(path + "/task/dictionary.txt").readLines().toSet()*/
    println("Enter a path to a dictionary.txt file:")
    val path = readLine().toString()
    val setOfWords = File(path).readLines().toSet()
    var mapOfWords = mutableMapOf<Int, MutableMap<Char, List<String>>>()
    mapOfWords.put(4, mutableMapOf())
    mapOfWords.put(5, mutableMapOf())
    for (i in 'a'..'z') {
        var a = setOfWords.filter { it.length == 4 && it[0] == i }
        mapOfWords[4]?.put(i, a)
        a = setOfWords.filter { it.length == 5 && it[0] == i }
        mapOfWords[5]?.put(i, a)
    }
    game(mapOfWords)
}