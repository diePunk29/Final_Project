public class StudentInfo {
    // fields
    private long studentId;
    private String firstName;
    private String lastName;
    private String progPlan;
    private String academicLvl;
    private String asurite;

    // constructor
    public StudentInfo(long studentId, String firstName, String lastName, String progPlan, String academicLvl, String asurite) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.progPlan = progPlan;
        this.academicLvl = academicLvl;
        this.asurite = asurite;
    }
}
