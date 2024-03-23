package gr.marou.samplemoviedatabase.model.enums;

public enum Professions {
    ACTOR("ACTOR"),
    DIRECTOR("DIRECTOR"),
    PRODUCER("PRODUCER"),
    CREW_MEMBER("CREW_MEMBER");

    private String value;

    Professions(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
