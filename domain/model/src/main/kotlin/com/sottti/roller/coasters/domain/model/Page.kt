package com.sottti.roller.coasters.domain.model

@JvmInline
public value class PageNumber(public val value: Int) {
    public operator fun times(other: Int): Int = value * other
}