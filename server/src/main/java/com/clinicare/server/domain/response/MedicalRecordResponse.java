package com.clinicare.server.domain.response;


import java.time.LocalDate;

public class MedicalRecordResponse {
    private Long id;
    private String diagnosis;
    private String treatment;
    private LocalDate createdAt;
    private String doctorName;
    private Long patientId;
    private long appointmentDetails;

    // Constructors
    public MedicalRecordResponse(Long id, String diagnosis, String treatment, LocalDate createdAt, String doctorName, Long patientId, long appointmentDetails) {
        this.id = id;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.createdAt = createdAt;
        this.doctorName = doctorName;
        this.patientId = patientId;
        this.appointmentDetails = appointmentDetails;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDiagnosis() { return diagnosis; }
    public void setDiagnosis(String diagnosis) { this.diagnosis = diagnosis; }

    public String getTreatment() { return treatment; }
    public void setTreatment(String treatment) { this.treatment = treatment; }

    public LocalDate getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDate createdAt) { this.createdAt = createdAt; }

    public String getDoctorName() { return doctorName; }
    public void setDoctorName(String doctorName) { this.doctorName = doctorName; }

    public Long getPatientId() { return patientId; }
    public void setPatientId(Long patientId) { this.patientId = patientId; }

    public long getAppointmentDetails() { return appointmentDetails; }
    public void setAppointmentDetails(long appointmentDetails) { this.appointmentDetails = appointmentDetails; }
}
