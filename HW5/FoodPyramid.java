/**
 * The <code>FoodPyramid</code> is the main class
 * and print the menu.
 *
 * @author Fengwei Zhang
 * e-mail: fengwei.zhang@stonybrook.edu
 * Stony Brook ID: 111252554
 * R04
 */

import java.util.Scanner;


public class FoodPyramid {
    private OrganismTree tree;

    public FoodPyramid() {

    }

    public static void main(String[] args) throws IsPlantException{
        FoodPyramid foodPyramid = new FoodPyramid();

        int x = 1;
        Scanner input = new Scanner(System.in);
        //set root and cursor
        OrganismNode root = new OrganismNode();
        System.out.println("What is the name of the apex predator?");
        String apexName = input.nextLine();
        root.setName(apexName);
        System.out.println("Is the organism an herbivore / a carnivore / an omnivore? (H / C / O):");
        String dietSelection = input.nextLine().toUpperCase();
        if (dietSelection.equals("H")) {
            root.setIsHerbivore(true);
            root.setIsCarnivore(false);
            root.setIsPlant(false);
        } else if (dietSelection.equals("C")) {
            root.setIsHerbivore(false);
            root.setIsCarnivore(true);
            root.setIsPlant(false);
        } else if (dietSelection.equals("O")) {
            root.setIsHerbivore(true);
            root.setIsCarnivore(true);
            root.setIsPlant(false);
        }
        OrganismTree tree = new OrganismTree(root);
        tree.setRoot(root);
        tree.setCursor(root);
        //loop menu
        while (x == 1) {
            /**
             * print the main menu
             */
            System.out.println("(PC) - Create New Plant Child");
            System.out.println("(AC) - Create New Animal Child");
            System.out.println("(RC) - Remove Child");
            System.out.println("(P) - Print Out Cursor's Prey");
            System.out.println("(C) - Print Out Food Chain");
            System.out.println("(F) - Print Out Food Pyramid at Cursor");
            System.out.println("(LP) - List All Plants Supporting Cursor");
            System.out.println("(R) - Reset Cursor to Root");
            System.out.println("(M) - Move Cursor to Child");
            System.out.println("Please select an option: ");
            String selection = input.nextLine();
            switch (selection.toUpperCase()) {
                case "PC":
                    System.out.println("What is the name of the organism?:");
                    String nameOfPlantChild = input.nextLine();
                    tree.addPlantChild(nameOfPlantChild);
                    break;
                case "AC":
                    System.out.println("What is the name of the organism?:");
                    String nameOfAnimalChild = input.nextLine();
                    System.out.println("Is the organism an herbivore / a carnivore " +
                            " an omnivore? (H / C / O):");
                    dietSelection = input.nextLine().toUpperCase();
                    if (dietSelection.equals("H")) {
                        tree.addAnimalChild(nameOfAnimalChild, true, false);
                    } else if (dietSelection.equals("C")) {
                        tree.addAnimalChild(nameOfAnimalChild, false, true);
                    } else if (dietSelection.equals("O")) {
                        tree.addAnimalChild(nameOfAnimalChild, true, true);
                    }
                    break;
                case "RC":
                    System.out.println("What is the name of the organism to be removed?:");
                    String nameOfRemovedChild = input.nextLine();
                    tree.removeChild(nameOfRemovedChild);
                    break;
                case "P":
                    tree.listPrey();
                    break;
                case "C":
                    String foodChain = tree.listFoodChain(tree.getRoot());
                    System.out.println(foodChain );
                    break;
                case "F":
                    tree.printOrganismTree(tree.getCursor());
                    break;
                case "LP":
                        tree.listAllPlants(tree.getCursor());
                    break;
                case "R":
                    tree.cursorReset();
                    break;
                case "M":
                    System.out.println("Move to?: ");
                    String nameOfChildMovedTo = input.nextLine();
                    tree.moveCursor(nameOfChildMovedTo);
            }
        }
    }
}
