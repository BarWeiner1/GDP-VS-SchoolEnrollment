package model;

/**
 * enum with attributes for type of indicator
 * @author Foothill College, Juntao Ren
 */
public enum IndicatorType {

    GDP_PER_CAPITA ("GDP per capita (Current US$)"),
    INVALID("Invalid Data"),
    SCHOOL_ENROLLMENT_PRIMARY("School Enrollment In Primary (% net)"),
    SCHOOL_ENROLLMENT_SECONDARY("School Enrollment In Secondary (%net)"),
    SCHOOL_ENROLLMENT("School Enrollment in Primary and Secondary")
    ;


    private final String label;

    /**
     * Constructor for an Indicator
     * @param label Sets label
     */
    IndicatorType(String label) {
        this.label = label;
    }
    /**
     * Accessor method to get the String Label
     */
    public String getLabel() {
        return this.toString();
    }
}