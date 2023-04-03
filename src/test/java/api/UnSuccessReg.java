package api;

import com.fasterxml.jackson.annotation.JsonCreator;

public class UnSuccessReg {
    public String error;

    @JsonCreator
    public UnSuccessReg(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
