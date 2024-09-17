private class patient {//hvorfor funker ikke dette her, plis hjelp Ludvig ^^ -emil

    private int patientId;

    private String lastName;

    private String firstName;

    //birthday, pls fiks dette, Ludvig ^^ -emil

    private String notes; //denne burde være en egen klasse ^^ -emil

    //encryptionkey, hva mente vi her egentlig? Thoughts, Ludvig? ^^ -emil

    public patient(int patientId, String lastName, String firstName, String notes) {
        this.patientId = patientId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.notes = notes;
    }
    public int getPatientId() {
        return patientId;
    }
    public String getLastName() {
        return lastName;
    }
    public String getFirstName() {
        return firstName;
    }
    //noen flere skal her, men jeg tenker at vi venter med det til shit funker^^ -emil
}
