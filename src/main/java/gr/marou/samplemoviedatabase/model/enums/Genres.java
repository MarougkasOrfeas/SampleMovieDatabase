package gr.marou.samplemoviedatabase.model.enums;

public enum Genres {
    ACTION("ACTION"),
    ADVENTURE("ADVENTURE"),
    ANIMATION("ANIMATION"),
    BIOGRAPHY("BIOGRAPHY"),
    COMEDY("COMEDY"),
    CRIME("CRIME"),
    DOCUMENTARY("DOCUMENTARY"),
    DRAMA("DRAMA"),
    FAMILY("FAMILY"),
    FANTASY("FANTASY"),
    FILM_NOIR("FILM_NOIR"),
    HISTORY("HISTORY"),
    HORROR("HORROR"),
    MUSICAL("MUSICAL"),
    MYSTERY("MYSTERY"),
    ROMANCE("ROMANCE"),
    SPORT("SPORT"),
    THRILLER("THRILLER"),
    WAR("WAR"),
    WESTERN("WESTERN");

    private String value;

    Genres(String value) {
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
