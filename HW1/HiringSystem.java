//Fengwei Zhang
//111252554
//R04

/**
 * The <code>HiringSystem</code> class uses a table
 * of <code>HiringTable</code>.
 *
 * @author Fengwei Zhang
 * e-mail: fengwei.zhang@stonybrook.edu
 * Stony Brook ID: 111252554
 * R04
 */

import java.util.Scanner;

public class HiringSystem {
    public static final int MAX_SKILLS = 3;
    public static final int MAX_COMPANIES = 3;
    public static final int MAX_APPLICANTS = 50;

    public static void main(String[] args) {
        int i = 2;
        HiringTable table = new HiringTable();
        HiringTable tempTable = new HiringTable();
        HiringTable backupTable = new HiringTable();
        while (i > 1) {
            /**
             * print the main menu
             */
            System.out.println("(A)     Add Applicant");
            System.out.println("(R)     Remove Applicant");
            System.out.println("(G)     Get Applicant");
            System.out.println("(P)     Print List");
            System.out.println("(RS)    Refine Search");
            System.out.println("(S)     Size");
            System.out.println("(D)     Backup");
            System.out.println("(CB)    Compare Backup");
            System.out.println("(RB)    Revert Backup");
            System.out.println("(Q)     Quit");
            System.out.println();
            System.out.println("Please enter a command: ");
            Scanner input = new Scanner(System.in);
            String selection = input.nextLine();
            /**
             * Choose applicant options
             */
            switch (selection.toUpperCase()) {
                /**
                 * Add Applicant option
                 */
                case "A":
                    Applicant applicant = new Applicant();
                    System.out.print("Enter Applicant Name: ");
                    String name = input.nextLine();
                    applicant.setApplicantName(name);
                    System.out.print("Enter Applicant College: ");
                    String college = input.nextLine();
                    applicant.setApplicantCollege(college);
                    System.out.print("Enter Applicant GPA: ");
                    double GPA = Double.parseDouble(input.nextLine());
                    applicant.setApplicantGPA(GPA);
                    String skills[] = new String[MAX_SKILLS];
                    for (int j = MAX_SKILLS; j >= 1; j--) {
                        System.out.print("Enter Up to " + j + " Skills: ");
                        skills[MAX_SKILLS - j] = input.nextLine();
                        if (skills[MAX_SKILLS - j].equals(""))
                            continue;
                    }
                    applicant.setApplicantSkills(skills);
                    String companies[] = new String[MAX_COMPANIES];
                    for (int k = MAX_COMPANIES; k >= 1; k--) {
                        System.out.print("Enter Up to " + k + " Companies: ");
                        companies[MAX_COMPANIES - k] = input.nextLine();
                        if (skills[MAX_COMPANIES - k].equals(""))
                            continue;
                    }
                    applicant.setCompanyName(companies);
//                    companies, name, GPA, college, skills);

                    try {
                        table.addApplicant(applicant);
                        System.out.println("Applicant " + name + " has successfully added " +
                                "to the hiring system.");
                    } catch (Exception e) {
                        System.out.println("There is no more room in the table for new applicants.");
                    }
                    break;
                /**
                 * Remove the applicants in the hiring table
                 */
                case "R":
                    System.out.print("Enter applicant name: ");
                    String nameRemove = input.nextLine();
                    try {
                        table.removeApplicant(nameRemove);
                        System.out.println("Applicant " + nameRemove + " has been successfully removed " +
                                "from the hiring system.");
                    } catch (Exception e) {
                        System.out.println("The applicant with given name is not found.");
                    }
                    break;
                /**
                 * Get the user defined applicant information
                 */
                case "G":
                    System.out.print("Enter applicant name: ");
                    String nameGet = input.nextLine();

                    try {
                        Applicant applicantGet = table.getApplicant(nameGet);
                        System.out.println();
                        String company = String.join(",", applicantGet.getCompanyName());
                        String skill = String.join(",", applicantGet.getApplicantSkills());
                        System.out.println("Applicant name: " + applicantGet.getApplicantName());
                        System.out.println("Applicant applying from: " + company);
                        System.out.println("Applicant GPA: " + applicantGet.getApplicantGPA());
                        System.out.println("Applicant college: " + applicantGet.getApplicantCollege());
                        System.out.println("Applicant skills: " + skill);
                        System.out.println();
                    } catch (Exception e) {
                        System.out.println("The applicant with given name is not found.");
                    }
                    break;
                /**
                 * Print the current hiring table
                 */
                case "P":
                    table.printApplicantTable();
                    break;
                /**
                 * Letting user input several conditions and filter the applicants that satisfy all the conditions.
                 */
                case "RS":
                    System.out.print("Enter a company to filter for: ");
                    String companyFilter = input.nextLine();
                    System.out.print("Enter a skill to filter for: ");
                    String skillFilter = input.nextLine();
                    System.out.print("Enter a college to filter for: ");
                    String collegeFilter = input.nextLine();
                    System.out.print("Enter the minimum GPA to filter for: ");
                    String gpaFilter1 = input.nextLine();
                    double gpaFilter = 0.0;
                    try {
                        gpaFilter = Double.parseDouble(gpaFilter1);
                        System.out.println(gpaFilter);
                    } catch (NumberFormatException e) {
//                        System.out.println("Invalid GPA format. ");
                    }
                    System.out.printf("%-30s", "Company Name");
                    System.out.printf("%-30s", "Applicant");
                    System.out.printf("%-30s", "GPA");
                    System.out.printf("%-30s", "College");
                    System.out.printf("%-30s", "Skills");
                    System.out.println();
                    System.out.println("-------------------------------------------------------------------" +
                            "------------------------------------------------------------------------------");
                    table.refineSearch(table, companyFilter, skillFilter, collegeFilter, gpaFilter);
                    break;
                /**
                 * Use the size method in the <>Hiringtable</> to get
                 * the current size of the applicant array
                 *
                 */
                case "S":
                    System.out.println("There are " + table.size() + " applicants in the hiring system.");
                    break;
                /**
                 * Use the clone method in the <>HiringTable</> to get
                 *
                 *
                 */
                case "D":
                    try {
                        backupTable = (HiringTable)table.clone();
                        System.out.println("Back up successfully.");
                    }catch(CloneNotSupportedException e) {
                    }
                    break;
                case "CB":
                    boolean same=false;
                    if(table.size()==backupTable.size()){
                        for(int j=0; j<table.size(); j++) {
                            if (table.getApplicantName(table)[j].equals(backupTable.getApplicantName(backupTable)[j])){
                                same = true;
                            }
                        }
                        if(same){
                            System.out.println("Current table is same as the backup one");
                        }
                    }
                    break;
                case "RB":
                    try {
                        tempTable = (HiringTable)table.clone();
                        table= (HiringTable)backupTable.clone();
                        backupTable=(HiringTable)tempTable.clone();
                        System.out.println("Revert successfully.");
                    }catch(CloneNotSupportedException e){
                        System.out.println("Revert failed");

                    }
                    break;
                case "Q":
                    return;

            }

            i++;
        }
    }
}
