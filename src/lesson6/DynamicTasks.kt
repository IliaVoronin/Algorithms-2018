@file:Suppress("UNUSED_PARAMETER")

package lesson6

/**
 * Наибольшая общая подпоследовательность.
 * Средняя
 *
 * Дано две строки, например "nematode knowledge" и "empty bottle".
 * Найти их самую длинную общую подпоследовательность -- в примере это "emt ole".
 * Подпоследовательность отличается от подстроки тем, что её символы не обязаны идти подряд
 * (но по-прежнему должны быть расположены в исходной строке в том же порядке).
 * Если общей подпоследовательности нет, вернуть пустую строку.
 * При сравнении подстрок, регистр символов *имеет* значение.
 * РЕСУРСОЕМКОСТЬ - o(N * M)
 * ТРУДОЕМКОСТЬ - o(N * M)
 * где N - длина первой строки, M - длина второй строки
 */
fun longestCommonSubSequence(first: String, second: String): String {
    var output = ""
    val max = Array(first.length + 1) { IntArray(second.length + 1) }
    for (i in 1 until first.length + 1) {
        for (j in 1 until second.length + 1) {
            if (first[i - 1] == second[j - 1]) {
                max[i][j] = 1 + max[i - 1][j - 1]
            } else
                max[i][j] = Math.max(max[i - 1][j], max[i][j - 1])
        }
    }
    var i = first.length
    var j = second.length
    while (i > 0 && j > 0) {
        when {
            first[i - 1] == second[j - 1] -> {
                output = first[i - 1] + output
                i--
                j--
            }
            max[i][j] == max[i - 1][j] -> i--
            else -> j--
        }
    }
    return output
}

/**
 * Наибольшая возрастающая подпоследовательность
 * Средняя
 *
 * Дан список целых чисел, например, [2 8 5 9 12 6].
 * Найти в нём самую длинную возрастающую подпоследовательность.
 * Элементы подпоследовательности не обязаны идти подряд,
 * но должны быть расположены в исходном списке в том же порядке.
 * Если самых длинных возрастающих подпоследовательностей несколько (как в примере),
 * то вернуть ту, в которой числа расположены раньше (приоритет имеют первые числа).
 * В примере ответами являются 2, 8, 9, 12 или 2, 5, 9, 12 -- выбираем первую из них.
 * РЕСУРСОЕМКОСТЬ - o(n)
 * ТРУДОЕМКОСТЬ - o(n^2)
 * ВСПОМОГАТЕЛЬНЫЙ МАТЕРИАЛ - http://e-maxx.ru/algo/longest_increasing_subseq_log
 */
fun longestIncreasingSubSequence(list: List<Int>): List<Int> {
    val output = ArrayList<Int>()
    if (list.isEmpty()) return output
    val p = IntArray(list.size)
    val d = IntArray(list.size)
    for (i in 0 until list.size) {
        d[i] = 1
        p[i] = -1
        for (j in 0 until i) {
            if (list[j] < list[i] && d[j] + 1 > d[i]) {
                d[i] = d[j] + 1
                p[i] = j
            }
        }
    }
    var length = 0
    var position = 0
    for (i in d.indices) {
        if (d[i] > length) {
            position = i
            length = d[i]
        }
    }
    while (position != -1) {
        output.add(0, list[position])
        position = p[position]
    }
    return output
}

/**
 * Самый короткий маршрут на прямоугольном поле.
 * Сложная
 *
 * В файле с именем inputName задано прямоугольное поле:
 *
 * 0 2 3 2 4 1
 * 1 5 3 4 6 2
 * 2 6 2 5 1 3
 * 1 4 3 2 6 2
 * 4 2 3 1 5 0
 *
 * Можно совершать шаги длиной в одну клетку вправо, вниз или по диагонали вправо-вниз.
 * В каждой клетке записано некоторое натуральное число или нуль.
 * Необходимо попасть из верхней левой клетки в правую нижнюю.
 * Вес маршрута вычисляется как сумма чисел со всех посещенных клеток.
 * Необходимо найти маршрут с минимальным весом и вернуть этот минимальный вес.
 *
 * Здесь ответ 2 + 3 + 4 + 1 + 2 = 12
 */
fun shortestPathOnField(inputName: String): Int {
    TODO()
}

// Задачу "Максимальное независимое множество вершин в графе без циклов"
// смотрите в уроке 5
