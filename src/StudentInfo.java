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

    // constructor
    public StudentInfo(String studentId, String firstName, String lastName, String progPlan, String academicLvl, String asurite) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.progPlan = progPlan;
        this.academicLvl = academicLvl;
        this.asurite = asurite;
    }


}
