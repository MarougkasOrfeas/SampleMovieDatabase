package gr.marou.samplemoviedatabase.exception;

public class SMDBException extends RuntimeException{

    private Boolean error;

    public SMDBException(String msg){
        super(msg);
    }

    public SMDBException(String msg, Boolean error){
        super(msg);
        this.error = error;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }
}
