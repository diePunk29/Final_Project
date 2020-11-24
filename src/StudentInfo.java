import java.util.ArrayList;

// class used for arrayList type
// the data fields in CSV file will be copied into an instance of StudentInfo
// and will be pushed in the ArrayList studentEntries
public class StudentInfo {
    // fields
    private String studentId;
    private String firstName;
    private String lastName;
    private String progPlan;
    private String academicLvl;
    private String asurite;

    // getters
    public String getStudentId() {
        return studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getProgPlan() {
        return progPlan;
    }

    public String getAcademicLvl() {
        return academicLvl;
    }

    public String getAsurite() {
        return asurite;
    }

    // setters
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setProgPlan(String progPlan) {
        this.progPlan = progPlan;
    }

    public void setAcademicLvl(String academicLvl) {
        this.academicLvl = academicLvl;
    }

    public void setAsurite(String asurite) {
        this.asurite = asurite;
    }

}
