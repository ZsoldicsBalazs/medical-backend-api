package ro.blz.medical.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseList {

    private int statusCode;
    private List<String> message;

    public ErrorResponseList(List<String> messages) {
        this.message = messages;
    }
}
