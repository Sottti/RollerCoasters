package com.sottti.roller.coasters.domain.model

public data class PageNumber(public val value: Int) {
    override fun toString(): String = "PageNumber($value)"
    public operator fun compareTo(other: PageNumber): Int = value.compareTo(other.value)
    public operator fun div(other: Int): Int = value / other
    public operator fun minus(other: Int): PageNumber = PageNumber(value - other)
    public operator fun plus(other: Int): PageNumber = PageNumber(value + other)
    public operator fun times(other: Int): Int = value * other

    public companion object {
        public fun initial(): PageNumber = PageNumber(0)
    }
}