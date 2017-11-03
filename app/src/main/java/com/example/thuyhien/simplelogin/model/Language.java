package com.example.thuyhien.simplelogin.model;

/**
 * Created by thuyhien on 11/2/17.
 */

public class Language {
    private String langCode;
    private String langName;

    public Language(String langCode, String langName, boolean isSelected) {
        this.langCode = langCode;
        this.langName = langName;
    }

    public String getLangCode() {
        return langCode;
    }

    public void setLangCode(String langCode) {
        this.langCode = langCode;
    }

    public String getLangName() {
        return langName;
    }

    public void setLangName(String langName) {
        this.langName = langName;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Language language = (Language) obj;
        return this.langCode != null
                ? this.langCode.equals(language.langCode) : language.langCode == null;

    }
}
