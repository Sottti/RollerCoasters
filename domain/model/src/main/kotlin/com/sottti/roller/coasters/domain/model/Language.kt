package com.sottti.roller.coasters.domain.model

public sealed class Language {
    public data object EnglishGbLanguage : Language()
    public data object GalicianLanguage : Language()
    public data object SpanishSpainLanguage : Language()
    public data object SystemLanguage : Language()
}