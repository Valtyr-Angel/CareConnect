// første utkast på patient treamtemtts class - nødvendig? tok det public ellers få feil
public class PatientTreatments {
    private int treatmentId;
    private int patientId;
    private String treatmentDetails;

    // kontrk. 
    public PatientTreatments(int treatmentId, int patientId, String treatmentDetails) {
        this.treatmentId = treatmentId;
        this.patientId = patientId;
        this.treatmentDetails = treatmentDetails;
    }

    //get og set 
    public int getTreatmentId() {
        return treatmentId;
    }

    public void setTreatmentId(int treatmentId) {
        this.treatmentId = treatmentId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getTreatmentDetails() {
        return treatmentDetails;
    }

    public void setTreatmentDetails(String treatmentDetails) {
        this.treatmentDetails = treatmentDetails;
    }
}