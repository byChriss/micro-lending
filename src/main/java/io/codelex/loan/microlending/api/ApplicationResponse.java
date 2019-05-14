package io.codelex.loan.microlending.api;

public class ApplicationResponse {
    private ApplicationStatus status;

    public ApplicationResponse(ApplicationStatus status) {
        this.status = status;
    }

    public ApplicationStatus getStatus() {
        return status;
    }
}
