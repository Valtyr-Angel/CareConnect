public class PatientActivitylog {
    private int activityLogId;
    private int patientId;
    private String activityDetails;
    //private Timestamp logTimestamp??? må finne ut hvordan fikse dette;

    // Konstruktør
    public PatientActivitylog(int activityLogId, int patientId, String activityDetails) {
        this.activityLogId = activityLogId;
        this.patientId = patientId;
        this.activityDetails = activityDetails;
        
    }

    // Gettere og settere
    public int getActivityLogId() {
        return activityLogId;
    }

    public void setActivityLogId(int activityLogId) {
        this.activityLogId = activityLogId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getActivityDetails() {
        return activityDetails;
    }

    public void setActivityDetails(String activityDetails) {
        this.activityDetails = activityDetails;
    }

    
}