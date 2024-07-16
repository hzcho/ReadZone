package com.example.cache

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        val input = readLine()!!.split(" ").map { it.toInt() }
        val N = input[0] // количество месяцев в году
        val L = input[1] // количество дней в неделе

        val daysInMonth = readLine()!!.split(" ").map { it.toInt() }

        val currentDateInfo = readLine()!!.split(" ").map { it.toInt() }
        val d1 = currentDateInfo[0] // день сегодняшней даты
        val m1 = currentDateInfo[1] // месяц сегодняшней даты
        val y1 = currentDateInfo[2] // год сегодняшней даты
        val t1 = currentDateInfo[3] // день недели сегодняшней даты

        val interestingDateInfo = readLine()!!.split(" ").map { it.toInt() }
        val d2 = interestingDateInfo[0] // день интересующей даты
        val m2 = interestingDateInfo[1] // месяц интересующей даты
        val y2 = interestingDateInfo[2] // год интересующей даты

        // Функция для определения дня недели по дате (1 января года year - это понедельник)
        fun getWeekday(day: Int, month: Int, year: Int): Int {
            val daysInMonths = daysInMonth.take(month - 1)
            val daysCount = daysInMonths.sum() + day - 1
            val totalDays = daysCount + (year - 1) * daysInMonth.sum()
            return (totalDays % L) + 1
        }

        // Находим день недели для сегодняшней даты
        val currentWeekday = getWeekday(d1, m1, y1)

        // Находим день недели для интересующей даты
        val interestingWeekday = getWeekday(d2, m2, y2)

        // Разница между днями недели
        var daysDiff = interestingWeekday - currentWeekday

        if (daysDiff <= 0) {
            daysDiff += L
        }

        // Выводим номер дня недели интересующей даты
        val nes=(t1 + daysDiff - 1) % L + 1
        assertEquals(1, nes)
    }
}