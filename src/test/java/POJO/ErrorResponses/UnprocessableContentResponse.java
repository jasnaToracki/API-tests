package POJO.ErrorResponses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UnprocessableContentResponse {

    @JsonProperty("message")
    private String message;

    @JsonProperty("error")
    private Map<String, List<String>> error;
    @JsonProperty("errors")
    private Map<String, List<String>> errors;

    public String getMessage() {
        return message;
    }


    public UnprocessableContentResponse setMessage(String message) {
        this.message = message;
        return this;
    }

    public Map<String, List<String>> getError() {
        return error;
    }

    public UnprocessableContentResponse setError(Map<String, List<String>> error) {
        this.error = error;
        return this;
    }

    public Map<String, List<String>> getErrors() {
        return errors;
    }

    public UnprocessableContentResponse setErrors(Map<String, List<String>> errors) {
        this.errors = errors;
        return this;
    }
}
