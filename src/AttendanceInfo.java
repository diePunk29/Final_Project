/**
 * CSE360
 * Final Project
 * This class is the base for storing all of
 * the attendance info needed for the graph
 * and the table.
 */

public class AttendanceInfo {
    // fields
    private String asurite;
    private String timeElapsed;
    private String date;

    /**
     * This gets the student's asurite
     * @return The student's asurite
     */
    public String getAsurite() {
        return asurite;
    }
    
    /**
     * This sets the student's asurite.
     * @param asurite The new asurite.
     */
    public void setAsurite(String asurite) {
        this.asurite = asurite;
    }
    
    /**
     * This gets the time the student was in class.
     * @return The attendance time.
     */
    public String getTimeElapsed() {
        return timeElapsed;
    }
    
    /**
     * This sets the time the student was in class.
     * @param timeElapsed The new amount of attendance time.
     */
    public void setTimeElapsed(String timeElapsed) {
        this.timeElapsed = timeElapsed;
    }
    
    /**
     * This gets the Date the student attended class.
     * @return The attendance date.
     */
    public String getDate() {
        return date;
    }
    
    /**
     * This sets the Date the student attended class.
     * @param date The new attendance date.
     */
    public void setDate(String date) {
        this.date = date;
    }
}
