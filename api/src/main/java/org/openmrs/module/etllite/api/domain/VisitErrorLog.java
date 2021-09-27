package org.openmrs.module.etllite.api.domain;

public class VisitErrorLog {
    private String uuid;
    private String visitPurpose;
    private String dosingDate;

    public VisitErrorLog(String uuid, String visitPurpose, String dosingDate) {
        this.uuid = uuid;
        this.visitPurpose = visitPurpose;
        this.dosingDate = dosingDate;
    }

    public String getUuid() {
        return uuid;
    }

    public String getVisitPurpose() {
        return visitPurpose;
    }

    public String getDosingDate() {
        return dosingDate;
    }
}
