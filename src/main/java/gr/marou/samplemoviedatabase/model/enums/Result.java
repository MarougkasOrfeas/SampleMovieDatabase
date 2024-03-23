package gr.marou.samplemoviedatabase.model.enums;

public enum Result {
    NOMINATED("NOMINATED"),
    WON("WON");

    private String value;

    Result(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }

}
