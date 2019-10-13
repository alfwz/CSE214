//Fengwei Zhang
//111252554
//R04

/**
 * The <code>HiringTable</code> class contains an array
 * of <code>Applicant</code> objects.
 *
 * @author Fengwei Zhang
 * e-mail: fengwei.zhang@stonybrook.edu
 * Stony Brook ID: 111252554
 * R04
 */

import java.util.Scanner;

public class HiringTable {
    /**
     * State the static variables
     */

    //static constants
    public static final int MAX_SKILLS = 3;
    public static final int MAX_COMPANIES = 3;
    public static final int MAX_APPLICANTS = 50;
    public static int numOfApp = 0;
    private static Applicant[] data = new Applicant[MAX_APPLICANTS];

    //constructor
    public HiringTable() {
    }

    public HiringTable(Applicant data[]) {
    }

    /**
     * Add additional counter variable numOfApp that add or delete member
     * in the table. Operation of the size method is O(1).
     * @return
     */
    public int size() {
        return numOfApp;
    }


    public void addApplicant(Applicant newApplicant) throws FullTableException {

        if (numOfApp < MAX_APPLICANTS) {
            data[numOfApp] = newApplicant;
            numOfApp++;
        }

    }

    //custom full table exception
    public class FullTableException extends Exception {
        public FullTableException(String msg) {
            super(msg);
        }
    }

    public class ApplicantNotFoundException extends Exception {
        public ApplicantNotFoundException(String msg) {
            super(msg);
        }
    }

    /**
     *First find out which applicant is the one we want to remove. Find the location
     * in the array of this applicant. Move all the applicant after this location
     * one spot forward will remove this applicant.
     * @param name the name of the applicant we want to remove
     */
    public void removeApplicant(String name) throws ApplicantNotFoundException {
        for (int i = 0; i < numOfApp; i++) {
            if (name.equals(this.data[i].getApplicantName())) {
                for (int j = i; j < numOfApp; j++) {
                    data[j] = data[j + 1];
                }
            }
        }
    }
    /**
     *Search all the Applicant objects in the table to find which applicant has the same name
     * as the input.
     * @param
     * @return
     */
    public Applicant getApplicant(String name) throws ApplicantNotFoundException {
        for (int i = 0; i <= numOfApp; i++) {
            if (name.equals(this.data[i].getApplicantName())) {
                return data[i];
            }
        }
        return null;
    }


    public String[] getApplicantName(HiringTable table){
        String[] nameArray =new String[table.size()];
        for(int i=0; i<table.size(); i++){
            nameArray[i]=this.data[i].getApplicantName();
        }
        return nameArray;
    }

    public Object clone() throws CloneNotSupportedException {
        HiringTable backupTable = (HiringTable) super.clone();

        return backupTable;
    }

    /**
     * First display the top part of the chart which contains several
     * element of the applicant, then print out each applicant within
     * the applicant array accordingly.
     *
     */
    public void printApplicantTable() {

        System.out.printf("%-30s", "Company Name");
        System.out.printf("%-30s", "Applicant");
        System.out.printf("%-30s", "GPA");
        System.out.printf("%-30s", "College");
        System.out.printf("%-30s", "Skills");
        System.out.println();
        System.out.println("-------------------------------------------------------------------" +
                "------------------------------------------------------------------------------");

        for (int i = 0; i < numOfApp; i++) {

            String company = String.join(",", data[i].getCompanyName());
            if (company.equals(null)) {
                System.out.printf("%-30s", "N/A");
            } else {
                System.out.printf("%-30s", company);
            }
            System.out.printf("%-30s", data[i].getApplicantName());
            System.out.printf("%-30s", data[i].getApplicantGPA());
            System.out.printf("%-30s", data[i].getApplicantCollege());
            String skill = String.join(",", data[i].getApplicantSkills());
            if (skill.equals(null)) {
                System.out.printf("%-30s", "N/A");
            } else {
                System.out.printf("%-30s", skill);
            }
            System.out.println();
//            for (int j=0;j<data[i].getApplicantSkills().length;j++){
//                System.out.printf(data[i].getApplicantSkills()[j]);
//            }
        }


    }


    /**
     * Refine search use several if loop, if the input value of any one of the value
     * does not satisfy the four conditions or there are no inputs then continue to
     * next applicant in the applicant array Once all the filters are finished,
     * print out the remaining applicants.
     * @param table the table we are looking into
     * @param company the company the applicant come from
     * @param skill the skill is refined for the applicants
     * @param college the college is refined for the applicants
     * @param GPA the minimum gpa for the applicants
     */
    public static void refineSearch(HiringTable table,
                                    String company,
                                    String skill,
                                    String college,
                                    double GPA) {
//        System.out.println("rs with: company -> " + company + ", skill -> " + skill + ", college -> " + college + ", gpa -> " + GPA);
//        Applicant[] refinedData = new Applicant[numOfApp];
//        HiringTable filteredApplicant = new HiringTable();
//        Applicant satisfiedApplicant = new Applicant();


//        for (int i = 0; i <= numOfApp; i++) {
//            if (company.equals(data[i].getCompanyName()[0]) ||
//                    company.equals(data[i].getCompanyName()[1]) ||
//                    company.equals(data[i].getCompanyName()[2]) ||
//                    company.equals("")) {
//                if (skill.equals(data[i].getApplicantSkills()[0]) ||
//                        skill.equals(data[i].getApplicantSkills()[1]) ||
//                        skill.equals(data[i].getApplicantSkills()[2]) ||
//                        company.equals("")) {
//                    if (college.equals(data[i].getCompanyName())) {
//                        if (GPA <= data[i].getApplicantGPA()) {
//                            System.out.println(data[i].toString());
//                        }
//                    }
//                }
//
//            }
//        }

        //i filtered applicant
        //j number of company
        //k number of skill
        for (int i = 0; i < numOfApp; i++) {
//            System.out.println("iter: " + i + " App data: " + table.data[i].toString());
//            for (int j = 0; j <= data[i].getCompanyName().length; j++) {
//                if (!company.equals(data[i].getCompanyName()[j]))       //if company not satisfy skip
//                    continue;
//            }
            if (!(table.data[i].getCompanyName()[0].equals(company) ||
                    table.data[i].getCompanyName()[1].equals(company) ||
                    table.data[i].getCompanyName()[2].equals(company))
            )
            {
//                System.out.println("company mismatch");
                continue;
            }
            if (!(table.data[i].getApplicantSkills()[0].equals(skill) ||
                    table.data[i].getApplicantSkills()[1].equals(skill) ||
                    table.data[i].getApplicantSkills()[2].equals(skill))
            )
            {
//                System.out.println("skills mismatch");
                continue;
            }

            if (!college.equals("") && !college.equals(table.data[i].getApplicantCollege()))  //skip if college not satisfy
            {
//                System.out.println("college mismatch: wanted: " + college + ", got: " +table.data[i].getApplicantCollege());
                continue;
            }
            if (GPA >= table.data[i].getApplicantGPA()){
//                System.out.println("gpa mismatch");
                continue;
            }
            System.out.println(table.data[i].toString());


//
//                        if(!(skill.equals("")||skill.equals(null)))
//                 {
//                    if (!skill.equals(data[i].getApplicantSkills()[k])) { //if skills  not satisfy skip
//                        {   //if college satisfy
//                            if (GPA <= data[i].getApplicantGPA()) {
//
//
//                            }
//
//                        }


        }


    }
}


