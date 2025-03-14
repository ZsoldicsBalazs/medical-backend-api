package ro.blz.medical.exceptions;

import ro.blz.medical.domain.BaseEntity;

public class ProcedureNotFoundException extends BaseException {
    public ProcedureNotFoundException(String message) {
        super(message);
    }
}
