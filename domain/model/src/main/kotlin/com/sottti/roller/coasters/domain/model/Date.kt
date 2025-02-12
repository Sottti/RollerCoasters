package com.sottti.roller.coasters.domain.model

import java.time.LocalDate
import java.time.Year
import java.time.YearMonth

public sealed class Date {
    public data class FullDate(val localDate: LocalDate) : Date()
    public data class YearAndMonth(val yearMonth: YearMonth) : Date()
    public data class YearOnly(val year: Year) : Date()
}