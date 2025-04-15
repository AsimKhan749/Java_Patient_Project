package model;

public class Appointment {
    public enum Status { BOOKED, CANCELLED, ATTENDED }

    private Treatment treatment;
    private Physiotherapist physiotherapist;
    private Patient patient;
    private Status status;

    public Appointment(Treatment treatment, Physiotherapist physiotherapist, Patient patient) {
        this.treatment = treatment;
        this.physiotherapist = physiotherapist;
        this.patient = patient;
        this.status = Status.BOOKED;
    }

    public void cancel() {
        status = Status.CANCELLED;
    }

    public void attend() {
        status = Status.ATTENDED;
    }

    public Status getStatus() {
        return status;
    }

    public Physiotherapist getPhysiotherapist() {
        return physiotherapist;
    }

    public String toReportString() {
        return physiotherapist.getName() + " | " + treatment.getName() + " | " + patient.getName() +
                " | " + treatment.getTime() + " | " + status;
    }
}
