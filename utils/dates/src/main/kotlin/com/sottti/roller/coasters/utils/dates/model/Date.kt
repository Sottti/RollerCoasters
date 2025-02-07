package com.sottti.roller.coasters.utils.dates.model

import java.time.LocalDate
import java.time.Year
import java.time.YearMonth

public sealed class Date {
    public data class FullDate(val date: LocalDate) : Date()
    public data class YearAndMonth(val date : YearMonth) : Date()
    public data class YearOnly(val date: Year) : Date()
}