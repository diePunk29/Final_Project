/**
 * Cristian Mosqueda, Eric Fahy, Albert Schaffer, Will Lord, and Tyler Vaillancourt
 * CSE360
 * Final Project
 * class used for arrayList type
 * the data fields in CSV file will be copied into an instance of StudentInfo
 * and will be pushed in the ArrayList studentEntries
 */

public class StudentInfo {
    // fields
    private String studentId;
    private String firstName;
    private String lastName;
    private String progPlan;
    private String academicLvl;
    private String asurite;

    // getters
    /**
     * this gets the id of the student.
     * @return the student id.
     */
    public String getStudentId() {
        return studentId;
    }
    
    /**
     * This gets the student's first name.
     * @return the student's first name.
     */
    public String getFirstName() {
        return firstName;
    }
    
    /**
     * This gets the student's last name.
     * @return the student's last name.
     */
    public String getLastName() {
        return lastName;
    }
    
    /**
     * This gets the student's progress plan.
     * @return The student's progress plan.
     */
    public String getProgPlan() {
        return progPlan;
    }
    
    /**
     * This gets the student's academic level.
     * @return the student's academic level.
     */
    public String getAcademicLvl() {
        return academicLvl;
    }
    
    /**
     * This gets the student's asurite.
     * @return the student's asurite.
     */
    public String getAsurite() {
        return asurite;
    }

    // setters
    /**
     * This sets the student's id to the new id.
     * @param studentId the new student id.
     */
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
    
    /**
     * This sets the student's first name to the new first name.
     * @param firstName the new student first name.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * This sets the student's last name to the new last name.
     * @param lastName the new student last name.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * This sets the student's progress plan to the new progress plan.
     * @param progPlan the new student progress plan.
     */
    public void setProgPlan(String progPlan) {
        this.progPlan = progPlan;
    }
    
    /**
     * This sets the student's academic level to the new academic level.
     * @param academicLvl the new academic level.
     */
    public void setAcademicLvl(String academicLvl) {
        this.academicLvl = academicLvl;
    }
    
    /**
     * This sets the student's asurite to the new asurite.
     * @param asurite the new student asurite.
     */
    public void setAsurite(String asurite) {
        this.asurite = asurite;
    }
}