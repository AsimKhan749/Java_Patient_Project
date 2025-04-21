package service;

import model.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class ClinicSystem {
    private List<Patient> patients = new ArrayList<>();
    private List<Physiotherapist> physios = new ArrayList<>();
    private List<Appointment> appointments = new ArrayList<>();

    public void addPatient(Patient p) {
        patients.add(p);
    }

    public void removePatient(String id) {
        patients.removeIf(p -> p.getId().equals(id));
    }

    public void addPhysiotherapist(Physiotherapist p) {
        physios.add(p);
    }

    public List<Physiotherapist> findByExpertise(String area) {
        return physios.stream()
                .filter(p -> p.getExpertise().contains(area))
                .collect(Collectors.toList());
    }

    public List<Physiotherapist> getAllPhysiotherapists() {
        return physios;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void bookAppointment(Patient patient, Physiotherapist physio, Treatment treatment) {
        Appointment appt = new Appointment(treatment, physio, patient);
        appointments.add(appt);
    }

    public void cancelAppointment(Appointment appt) {
        appt.cancel();
    }

    public void markAttended(Appointment appt) {
        appt.attend();
    }

    public void printReport() {
        System.out.println("\n=== End-of-Term Report ===");
        for (Appointment a : appointments) {
            System.out.println(a.toReportString());
        }

        System.out.println("\n--- Physiotherapist Ranking by Attended Appointments ---");
        physios.stream()
                .sorted((p1, p2) -> Long.compare(
                        appointments.stream().filter(a -> a.getPhysiotherapist().equals(p2) && a.getStatus() == Appointment.Status.ATTENDED).count(),
                        appointments.stream().filter(a -> a.getPhysiotherapist().equals(p1) && a.getStatus() == Appointment.Status.ATTENDED).count()
                ))
                .forEach(p -> System.out.println(p.getName()));
    }

    public Patient getPatientById(String pid) {
        for (Patient p : patients) {
            if (p.getId().equals(pid)) {
                return p;
            }
        }
        return null;
    }

}
