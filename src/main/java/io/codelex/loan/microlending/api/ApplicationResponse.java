package io.codelex.loan.microlending.api;

public class ApplicationResponse {
    private Status status;

    public ApplicationResponse(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }
}
