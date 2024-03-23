package gr.marou.samplemoviedatabase.model.enums;

public enum SalaryType {
    PER_EPISODE("PER_EPISODE"),
    FULL_PROJECT("FULL_PROJECT");

    private String value;

    SalaryType(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
