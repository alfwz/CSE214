/**
 * The <code>Block</code> class contains an array
 * of <code>Variable</code> objects.
 *
 * @author Fengwei Zhang
 * e-mail: fengwei.zhang@stonybrook.edu
 * Stony Brook ID: 111252554
 * R04
 */
import java.util.Scanner;

public class Block {
    /**
     * State the static variables
     */
    private Variable[] variableArray;
    private int size=0;
    /**
     * Constructor
     */
    public Block(){
        variableArray= new Variable[10];
    }

    /**
     * Getter and Setter
     */
    public Variable[] getVariableArray(){
        return variableArray;
    }
    public void addVariable(Variable newVariable){
        variableArray[size]=newVariable;
        size++;
    }

    /**
     * Search the block based on the name of the variable
     * @param data the name of variable in the print instruction in the text file
     * @return null if not found
     * @return variable if found
     */
    public Variable searchVariableArray(String data){
        for(int i=0;i<size;i++){
            if (variableArray[i].getName().equals(data)){
                return variableArray[i];
            }
        }
        return null;
    }

    /**
     * Print all the variable's name and value in the block
     */
    public String toString(){
        System.out.printf("%-20s", "Variable Name");
        System.out.printf("%-20s", "Initial Value");
        System.out.println();
        for(int i=0;i<size;i++){
            System.out.printf("%-20s", variableArray[i].getName());
            System.out.printf("%-20s", variableArray[i].getInitialValue());
            System.out.println();
        }
        return "";
    }


}
