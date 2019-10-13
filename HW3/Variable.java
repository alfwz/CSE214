/**
 * The <code>Applicant</code> class implements the code
 * of <code>Block</code>.
 *
 * @author Fengwei Zhang
 * e-mail: fengwei.zhang@stonybrook.edu
 * Stony Brook ID: 111252554
 * R04
 */

public class Variable {
    /**
     * State the static variables
     */
    private String name;
    private int initialValue;

    /**
     * Constructor
     */
    public Variable(){

    }
    public Variable(String name, int initialValue){
        name=name;
        initialValue=0;
    }

    /**
     * Getter and Setter
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getInitialValue() {
        return initialValue;
    }

    public void setInitialValue(int initialValue) {
        this.initialValue = initialValue;
    }
    /**
     * Print the variable's name and value based on the name of the variable
     * @param variableFound the name of the variable found in the block array
     */
    public String toString(Variable variableFound){
        System.out.printf("%-20s", "Variable Name");
        System.out.printf("%-20s", "Initial Value");
        System.out.println();
        System.out.printf("%-20s", variableFound.getName());
        System.out.printf("%-20s", variableFound.getInitialValue());
        System.out.println();
        return "";
    }
}
