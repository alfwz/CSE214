/**
 * The <code>BlockerTracer</code> class contains the code
 * of <code>Block</code> object.
 *
 * @author Fengwei Zhang
 * e-mail: fengwei.zhang@stonybrook.edu
 * Stony Brook ID: 111252554
 * R04
 */

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.io.*;
import java.util.Stack;
import java.util.Scanner;
import java.lang.String;


//41 what's the problem of my constructor
//how to make a method when read int
//stop when no next line

public class BlockTracer {
    public static void main(String[] args) throws FileNotFoundException {
        int x = 1;
        while (x == 1) {
            System.out.println("Type into the name of the file you want to read");
            Scanner input = new Scanner(System.in);
            String fileName = input.nextLine();
            File file = new File(fileName);
            Scanner stdin = new Scanner(file);
            String data;
            Stack<Block> blockStack = new Stack<>();

            while (stdin.hasNextLine()) {
                data = stdin.nextLine();
                data = data.trim();

                //int j = 2, n;   /*..........*/
                if (data.contains("{") == true) {
                    //create a new block and push into stack
                    Block newBlock = new Block();
                    blockStack.push(newBlock);
                    data = data.replaceAll("\\{", "");
                    String dataTemp = data;
                    while (data.length() != 0) {
                        //data=    { int i=5; /*$print LOCAL*/ k = i;}
                        dataTemp = data.trim();
                        //data={ int i=5; /*$print LOCAL*/ k = i;}
                        //dataTemp={ int i=5; /*$print LOCAL*/ k = i;}
                        if (dataTemp.contains("int ") == true) {
                            dataTemp = dataTemp.substring(dataTemp.indexOf("int ") + 4, dataTemp.indexOf(";"));
                            //dataTemp:int i=5
                            //dataTemp = dataTemp.replaceAll(" ", "");
                            //j=2,n
                            String[] str = dataTemp.split(",");
                            //j=2
                            //n
                            for (int i = 0; i < str.length; i++) {
                                if (str[i].contains("=")) {

                                    String[] str1 = str[i].split("=");
                                    String name = str1[0];
                                    System.out.println("Print str1[]" + str1[1]);
                                    int initialValue = Integer.parseInt((str1[1].trim()));

                                    Variable newVariable = new Variable();
                                    newVariable.setName(name);
                                    newVariable.setInitialValue(initialValue);
                                    blockStack.peek().addVariable(newVariable);


//                                    String[] str1 = str[i].split("=");
//                                    String name = str1[0];
//                                    str1[1] = str1[1].replaceAll(";", "");
//                                    Character c = str1[1].toString().charAt(0);
//                                    int initialValue = Integer.parseInt(str1[1].trim());
//                                    Variable newVariable = new Variable();
//                                    newVariable.setName(name);
//                                    newVariable.setInitialValue(initialValue);
//                                    blockStack.peek().addVariable(newVariable);

                                } else {
                                    String name = str[i];
                                    Variable newVariable = new Variable();
                                    newVariable.setName(name);
                                    blockStack.peek().addVariable(newVariable);
                                }
                            }

                            data = data.substring(data.indexOf("/*"), data.indexOf("}"));
                            //data: /*$print LOCAL*/ k = i;}
                            continue;
                        }
                        else if  (dataTemp.contains("/*$print") == true) {
                            //print all variables
                            if (dataTemp.contains("LOCAL") == true) {
                                blockStack.peek().toString();
                                if (blockStack.peek().getVariableArray()[0] == null) {
                                    System.out.println("No local variables to print.");
                                }
                            }
                            //search variable in the top block in the stack
                            else {
                                dataTemp = dataTemp.trim();
                                dataTemp = dataTemp.replaceAll("\\/\\*\\$print ", "");
                                dataTemp = dataTemp.replaceAll("\\*/", "");
                                Stack<Block> tempStack = new Stack<>();
                                //if stack is not empty
                                while (blockStack.isEmpty() == false) {
                                    Variable variableFound = new Variable();
                                    variableFound = blockStack.peek().searchVariableArray(data);
                                    //if found in the top block print it
                                    if (variableFound != null) {
                                        variableFound.toString(variableFound);
                                        break;
                                        //if not found in the top block of stack && only one block in the stack
                                    } else if (variableFound == null && blockStack.size() == 1) {
                                        System.out.println("Variable not found:" + data);
                                        break;
                                    }
                                    //if not found at the top but maybe in the block below the top
                                    else if (variableFound == null && blockStack.size() != 1) {
                                        //put the top of main stack to the temp stack
                                        tempStack.push(blockStack.pop());
                                    }
                                }

//                        if(tempStack.size()==1){
//                            blockStack.push(tempStack.pop());
//                        }
//                        if(tempStack.size()==2){
//                            blockStack.push(tempStack.pop());
//                            blockStack.push(tempStack.pop());
//                        }
                                //put back
                                while (tempStack.isEmpty() == false) {
                                    blockStack.push(tempStack.pop());
                                }
                            }
                            data = data.substring(data.indexOf("/ ") + 1, data.indexOf("}"));
                            //data: k = i;}
                            continue;
                        }
                        else if (data.contains("}") == true) {
                            blockStack.pop();
                            data = data.replaceAll("}", "");
                            continue;
                        }
                        else {
                            continue;
                        }
                    }
                }
                else if (data.contains("*$print") == true) {
                    //print all variables
                    if (data.contains("LOCAL")) {
                        blockStack.peek().toString();
                        if (blockStack.peek().getVariableArray()[0] == null) {
                            System.out.println("No local variables to print.");
                        }
                    }
                    //search variable in the top block in the stack
                    else {
                        data = data.trim();
                        data = data.replaceAll("\\/\\*\\$print ", "");
                        data = data.replaceAll("\\*/", "");
                        Stack<Block> tempStack = new Stack<>();
                        //if stack is not empty
                        while (blockStack.isEmpty() == false) {
                            Variable variableFound = new Variable();
                            variableFound = blockStack.peek().searchVariableArray(data);
                            //if found in the top block print it
                            if (variableFound != null) {
                                variableFound.toString(variableFound);
                                break;
                                //if not found in the top block of stack && only one block in the stack
                            } else if (variableFound == null && blockStack.size() == 1) {
                                System.out.println("Variable not found:" + data);
                                break;
                            }
                            //if not found at the top but maybe in the block below the top
                            else if (variableFound == null && blockStack.size() != 1) {
                                //put the top of main stack to the temp stack
                                tempStack.push(blockStack.pop());
                            }
                        }

//                        if(tempStack.size()==1){
//                            blockStack.push(tempStack.pop());
//                        }
//                        if(tempStack.size()==2){
//                            blockStack.push(tempStack.pop());
//                            blockStack.push(tempStack.pop());
//                        }
                        //put back
                        while (tempStack.isEmpty() == false) {
                            blockStack.push(tempStack.pop());
                        }

                    }
                }
                else if (data.contains("int ") == true) {
                    data = data.substring(4, data.length());
                    //j = 2, n;   /*..........*/
                    data = data.substring(0, data.indexOf(";"));
                    //j = 2, n
                    data = data.replaceAll(" ", "");
                    //j=2,n
                    String[] str = data.split(",");
                    //j=2
                    //n
                    for (int i = 0; i < str.length; i++) {
                        if (str[i].contains("=")) {

                            String[] str1 = str[i].split("=");
                            String name = str1[0];
                            int initialValue = Integer.valueOf(str1[1].trim());

                            Variable newVariable = new Variable();
                            newVariable.setName(name);
                            newVariable.setInitialValue(initialValue);
                            blockStack.peek().addVariable(newVariable);
                        }
                        else {
                            String name = str[i];
                            Variable newVariable = new Variable();
                            newVariable.setName(name);
                            blockStack.peek().addVariable(newVariable);
                        }
                    }
                } else if (data.equals("}")) {
                    blockStack.pop();
                } else {
                    continue;
                }
            }
        }
    }
}
