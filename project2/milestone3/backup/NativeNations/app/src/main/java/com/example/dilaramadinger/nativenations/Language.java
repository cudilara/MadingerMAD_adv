package com.example.dilaramadinger.nativenations;


public class Language {
    private String language;
    private String website;

    private Language(String lang, String website){
        this.language = lang;
        this.website = website;
    }

    public static final Language[] langList = {
            new Language("Apache", "http://www2.nau.edu/~jar/TIL_11.html"),
            new Language("Arapahoe", "https://www.colorado.edu/csilw/alp/"),
            new Language("Blackfoot", "http://www.native-languages.org/blackfoot.htm"),
            new Language("Cheyenne", "https://www.omniglot.com/writing/cheyenne.htm"),
            new Language("Crow", "http://www.native-languages.org/crow.htm"),
            new Language("Hidasta", "http://hidatsa.org/portfolios/language-materials/"),
            new Language("Lakota", "http://lakhota.org"),
            new Language("Maidu", "http://www.native-languages.org/maidu.htm"),
            new Language("Mescalero-Chiricahua", "https://www.omniglot.com/writing/mescalero-chiricahua.htm"),
            new Language("Mesquakie-Sauk", "http://www.native-languages.org/meskwaki-sauk.htm"),
            new Language("Miwok", "http://www.nsmiwok.com/language/nsmiwok.htm"),
            new Language("Navajo", "http://www.native-languages.org/navajo.htm"),
            new Language("Nez Perce", "http://www.nimipuutimt.org/uploads/1/4/0/6/14060280/nimipuutimt_volume_1.pdf"),
            new Language("Otoe-Missouria", "http://iowayotoelang.nativeweb.org"),
            new Language("Paiute", "http://paiute.ucsc.edu"),
            new Language("Pawnee", "http://pawneelanguage.com"),
            new Language("Ponca", "http://www.native-languages.org/omaha-ponca.htm"),
            new Language("Quechua", "http://www.quechua.org.uk/Eng/Main/i_LEARN.HTM"),
            new Language("Shoshoni", "http://www.native-languages.org/shoshone.htm"),
            new Language("Southern Iroquoian (Cherokee)", "http://www.native-languages.org/cherokee.htm"),
            new Language("Tsinuk (Chinook)", "https://www.omniglot.com/writing/chinook.htm"),
            new Language("Wintu", "http://www.native-languages.org/wintu.htm"),
            new Language("Yokuts", "http://www.languagegeek.com/california/yokuts.html"),
            new Language("Yurok", "http://linguistics.berkeley.edu/~yurok/web/sentences.php")
    };

    public String getLanguage() {
        return language;
    }

    public String getWebsite() {
        return website;
    }

    public String toString(){
        return this.language;
    }
}
