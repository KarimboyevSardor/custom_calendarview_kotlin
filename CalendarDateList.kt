import java.time.LocalDate
import java.time.YearMonth

class CalendarDateList {
    private var year = 2025
    private var month = 2
    private fun getDayWeek() : Int {
        val date = LocalDate.of(year, month, 1)
        return date.dayOfWeek.value
    }

    fun generateMonthDays(): MutableList<Date> {
        val daysList: MutableList<Date> = mutableListOf()
        val weekday = getDayWeek()
        var i = 1
        var i1 = 2
        var weekcounter = 1
        var dayCounter = 1
        var isAdd = false
        while (i < 42) {
            if (dayCounter - 1 == getDayCount(year, month)) {
                dayCounter = 1
                daysList.add(Date(month + 1, year, weekcounter, dayCounter, false))
                dayCounter++
                break
            }
            if (isAdd) {
                daysList.add(Date(month, year, weekcounter, dayCounter, true))
                dayCounter++
            } else {
                if (weekcounter == weekday) {
                    isAdd = true
                    daysList.add(Date(month, year, weekcounter, dayCounter, true))
                    dayCounter++
                } else {
                    isAdd = false
                    var month1: Int
                    var year1: Int
                    if (month == 1) {
                        month1 = 12
                        year1 = year - 1
                    } else {
                        year1 = year
                        month1 = month - 1
                    }
                    val day = getDayCount(year1, month1) - (weekday - i1)
                    daysList.add(Date(month1, year1, weekcounter, day, false))
                }
            }
            if (weekcounter == 7) {
                weekcounter = 0
            }
            weekcounter++
            i++
            i1++
        }
        while (daysList.size < 42) {
            weekcounter++
            daysList.add(Date(month + 1, year, weekcounter, dayCounter, false))
            if (weekcounter == 7) {
                weekcounter = 1
            }
            dayCounter++
            weekcounter++
        }
        return daysList
    }
    private fun getDayCount(year1: Int, month1: Int) : Int {
        val yearMonth = YearMonth.of(year1, month1)
        return yearMonth.lengthOfMonth()
    }
}

data class Date(
    var month: Int = 0,
    var year: Int = 0,
    var weekday: Int = 0,
    var monthDay: Int = 0,
    var CurrentMonth: Boolean = false
)

fun main() {
    val calendarDateList = CalendarDateList()
    println(calendarDateList.generateMonthDays())
}