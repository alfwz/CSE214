import java.util.Scanner;

/**
 * The <code>Applicant</code> class implements a table
 * of <code>HiringTable</code>.
 *
 * @author Fengwei Zhang
 * e-mail: fengwei.zhang@stonybrook.edu
 * Stony Brook ID: 111252554
 * R04
 */
public class Applicant {

    //Instance variables
    private String[] companyName = new String[3];
    private String applicantName = "";
    private double applicantGPA = 0.0;
    private String applicantCollege = "";
    private String[] applicantSkills = new String[3];

    //constructor
    public Applicant() {
        companyName[0] = "";
        companyName[1] = "";
        companyName[2] = "";
        applicantSkills[0] = "";
        applicantSkills[1] = "";
        applicantSkills[2] = "";
    }

    public Applicant(String[] companyName,
                     String applicantName,
                     double applicantGPA,
                     String applicantCollege,
                     String[] applicantSkills) {
    }

    //getters
    public String[] getCompanyName() {
        return companyName;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public double getApplicantGPA() {

        return applicantGPA;
    }

    public String getApplicantCollege() {
        return applicantCollege;
    }

    public String[] getApplicantSkills() {
        return applicantSkills;
    }

    //setters
    public void setCompanyName(String[] companyName) {
        this.companyName = companyName;

        for (int i = 3; i <= 1; i--) {
            Scanner input = new Scanner(System.in);
            System.out.print("Enter Up to " + i + " Companies: ");
            String str = input.nextLine();
            companyName[3 - i] = str;
            if (str.length() == 0)
                break;
        }
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public void setApplicantGPA(double applicantGPA) {
        this.applicantGPA = applicantGPA;
    }

    public void setApplicantCollege(String applicantCollege) {
        this.applicantCollege = applicantCollege;
    }

    public void setApplicantSkills(String[] applicantSkills) {
        this.applicantSkills = applicantSkills;

    }

    //method
    public Object clone() {
        Object newApplicant = new Applicant(this.companyName.clone(),
                this.applicantName,
                this.applicantGPA,
                this.applicantCollege,
                this.applicantSkills.clone());
//        newApplicant.companyName=companyName;
//        newApplicant.applicantName=applicantName;
//        newApplicant.applicantGPA= applicantGPA;
//        newApplicant.applicantCollege=applicantCollege;
//        newApplicant.applicantSkills=applicantSkills;
        return newApplicant;
    }

    public boolean equals(Object obj) {
        if (!this.applicantCollege.equals(((Applicant) obj).applicantCollege))
            return false;
        if (this.applicantGPA != (((Applicant) obj).applicantGPA))
            return false;
        //compare skills
        if (this.applicantSkills.length != ((Applicant) obj).applicantSkills.length) {
            return false;
        } else {
            for (int i = 0; i <= this.applicantSkills.length; i++) {
                if (!this.applicantSkills[i].equals(((Applicant) obj).applicantSkills[i]))
                    return false;
            }
        }
        //compare company
        if (this.companyName.length != ((Applicant) obj).companyName.length) {
            return false;
        } else {
            for (int i = 0; i <= this.companyName.length; i++) {
                if (this.companyName[i].equals(((Applicant) obj).companyName[i]))
                    return false;
            }
        }
        return true;
    }

    public String toString() {
        String printCompanyName = String.join(",", companyName);
        String printSkills = String.join(",", applicantSkills);
        String abc = String.format("%-30s%-30s%-30s%-30s%-30s", printCompanyName, applicantName,
                applicantGPA, applicantCollege, printSkills);
        return abc;
    }


}
