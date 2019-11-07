/**
 * The <code>OrganismTree</code> class include
 * <code>OrganismNode</code> class.
 *
 * @author Fengwei Zhang
 * e-mail: fengwei.zhang@stonybrook.edu
 * Stony Brook ID: 111252554
 * R04
 */
public class OrganismTree {
    //member fields
    private OrganismNode root;
    private OrganismNode cursor;
    //constructor
    public OrganismTree(OrganismNode apexPredator) {
        this.root = apexPredator;
        this.cursor = apexPredator;
    }
    //getter and setter
    public OrganismNode getRoot() {
        return root;
    }
    public void setRoot(OrganismNode root) {
        this.root = root;
    }
    public OrganismNode getCursor() {
        return cursor;
    }
    public void setCursor(OrganismNode cursor) {
        this.cursor = cursor;
    }

    /**
     * Set the cursor to the root.
     */
    public void cursorReset() {
        this.cursor = root;
        System.out.println("Cursor has been successfully reset to " + root.getName());
    }

    /**
     * Move cursor to the one of the prey based on the name user input.
     * @param name
     * @throws IllegalArgumentException
     */
    public void moveCursor(String name) throws IllegalArgumentException {
        if (cursor.getLeft() != null && cursor.getLeft().getName().equals(name)) {
            cursor = cursor.getLeft();
            System.out.println("Cursor successfully moved to " + name);
        } else if (cursor.getMiddle() != null && cursor.getMiddle().getName().equals(name)) {
            cursor = cursor.getMiddle();
            System.out.println("Cursor successfully moved to " + name);
        } else if (cursor.getRight() != null && cursor.getRight().getName().equals(name)) {
            cursor = cursor.getRight();
            System.out.println("Cursor successfully moved to " + name);
        } else {
            try {
                throw new IllegalArgumentException();
            }catch (IllegalArgumentException e){
                System.out.println("ERROR: This prey does not exist for this predator.");
            }
        }
    }

    /**
     * List cursor's all prey.
     * @return
     * @throws IsPlantException
     */
    public String listPrey() throws IsPlantException {
        try {
            if (cursor.getIsPlant()) {
                throw new IsPlantException("The cursor is a plant does not has prey.");
            }

            System.out.println(cursor.getName() + " -> ");
            if (cursor.getLeft() != null) {
                System.out.println(cursor.getLeft().getName());
            }
            if (cursor.getMiddle() != null) {
                System.out.println(cursor.getMiddle().getName());
            }
            if (cursor.getRight() != null) {
                System.out.println(cursor.getRight().getName());
            }
        } catch (IsPlantException e) {
            System.out.println(e);
        }
        return "";
    }


//    public boolean isOnPath(OrganismNode node) {
//        if (root == null) {
//            return false;
//        }
//        if (root.getName().equals(node.getName())) {
//            System.out.println(root.getName());
//            return true;
//        }
//        if (root.getLeft() != null) {
//            boolean onPath = isOnPath(root.getLeft());
//        } else if (root.getMiddle() != null) {
//            boolean onPath = isOnPath(root.getMiddle());
//        } else if (root.getRight() != null) {
//            boolean onPath = isOnPath(root.getRight());
//        }
//        return false;
//    }

//    public String listFoodChain(OrganismNode root) {
//        System.out.print(root.getName()+" -> ");
//        if(root.getLeft()!=null && this.isOnPath(root.getLeft())){
//            listFoodChain(root.getLeft());
//        }
//        if(root.getMiddle()!=null && this.isOnPath(root.getMiddle())){
//            listFoodChain(root.getMiddle());
//        }
//        if(root.getRight()!=null && this.isOnPath(root.getRight())){
//            listFoodChain(root.getRight());
//        }
//        return "";
//    }

    /**
     * List path from root to the cursor.
     * @param root
     * @return
     */
    public String listFoodChain(OrganismNode root) {
        if (root == null) {
            return "";
        }
        // Found cursor
        if (root.getName().equals(cursor.getName())) {
            return root.getName();
        }
        // root is not cursor
        String left, middle, right;
        left = listFoodChain(root.getLeft());
        if (!left.equals("")) {
            return root.getName() + " -> " + left;
        } else {
            middle = listFoodChain(root.getMiddle());
            if (!middle.equals("")) {
                return root.getName() + " -> " + middle;
            } else {
                right = listFoodChain(root.getRight());
                if (!right.equals("")) {
                    return root.getName() + " -> " + right;
                } else {
                    return "";
                }
            }
        }
    }

//    private int depthOfNode(OrganismNode cursor) {
//        if (root == cursor) {
//            return 0;
//        }
//        if (root.getLeft() == cursor) {
//            return depthOfNode(root.getLeft()) + 1;
//        } else {
//            if (root.getMiddle() == cursor) {
//                return depthOfNode(root.getMiddle()) + 1;
//            } else {
//                if (root.getRight() == cursor) {
//                    return depthOfNode(root.getRight()) + 1;
//                } else {
//                    return 0;
//                }
//            }
//        }
//    }

    /**
     * Help method for the printOrganismTree to initialize the valve of level.
     * @param cursor
     */
    public void printOrganismTree(OrganismNode cursor) {
        this.printOrganismTree(cursor, 0);
    }

    /**
     * Print all the prey of the cursor.
     * @param cursor
     * @param level
     */
    public void printOrganismTree(OrganismNode cursor, int level) {
        if (!cursor.getIsPlant()) {
            for (int i = 0; i < level; i++) {
                System.out.print("    ");
            }
            System.out.println(" |- " + cursor.getName());
        }
        if (cursor.getIsPlant()) {
            for (int i = 0; i < level; i++) {
                System.out.print("    ");
            }
            System.out.println(" - " + cursor.getName());
        }

        if (cursor.getLeft() != null) {
            printOrganismTree(cursor.getLeft(), level + 1);
        }

        if (cursor.getMiddle() != null) {
            printOrganismTree(cursor.getMiddle(), level + 1);
        }
        if (cursor.getRight() != null) {
            printOrganismTree(cursor.getRight(), level + 1);
        }
        if (cursor.getName() == null) {
        }
    }

    /**
     * Print all the plants in the tree.
     * @param cursor
     * @return
     */
    public String listAllPlants(OrganismNode cursor) {
        if (cursor.getIsPlant() == true) {
            System.out.println(cursor.getName());
        }

        if (cursor.getLeft() != null) {
            listAllPlants(cursor.getLeft());
        }
        if (cursor.getMiddle() != null) {
            listAllPlants(cursor.getMiddle());
        }
        if (cursor.getRight() != null) {
            listAllPlants(cursor.getRight());
        }
        return "";
    }

    /**
     * Add animal child.
     * @param name
     * @param isHerbivore
     * @param isCarnivore
     * @throws IllegalArgumentException
     */
    public void addAnimalChild(String name, boolean isHerbivore, boolean isCarnivore) throws IllegalArgumentException {
        OrganismNode prey = new OrganismNode();
        prey.setName(name);
        prey.setIsHerbivore(isHerbivore);
        prey.setIsCarnivore(isCarnivore);
        prey.setIsPlant(false);
        try {
            if (cursor.getLeft() != null && cursor.getLeft().getName().equals(name)) {
                throw new IllegalArgumentException();
            }
            if (cursor.getMiddle() != null && cursor.getMiddle().getName().equals(name)) {
                throw new IllegalArgumentException();
            }
        }catch (IllegalArgumentException e){
            System.out.println("ERROR: This prey already exists for this predator.");
        }
        try {
            cursor.addPrey(prey);
        } catch (DietMismatchException e) {
            System.out.println(e);
        } catch (PositionNotAvailableException e) {
            System.out.println(e);
        } catch (IsPlantException e) {
            System.out.println(e);
        } catch (IllegalArgumentException e) {
            System.out.println("ERROR: This prey already exists for this predator.");
        }
    }

    /**
     * Add plant child.
     * @param name
     * @throws IllegalArgumentException
     */
    public void addPlantChild(String name) throws IllegalArgumentException {
        OrganismNode prey = new OrganismNode();
        prey.setName(name);
        prey.setIsCarnivore(false);
        prey.setIsHerbivore(false);
        prey.setIsPlant(true);
        try {
            if (cursor.getLeft() != null && cursor.getLeft().getName().equals(name)) {
                throw new IllegalArgumentException();
            }

            if (cursor.getMiddle() != null && cursor.getMiddle().getName().equals(name)) {
                throw new IllegalArgumentException();
            }
        }catch(IllegalArgumentException e){
            System.out.println("ERROR: This prey already exists for this predator.");
        }
        try {
            cursor.addPrey(prey);
        } catch (DietMismatchException e) {
            System.out.println(e);
        } catch (PositionNotAvailableException e) {
            System.out.println(e);
        } catch (IsPlantException e) {
            System.out.println(e);
        } catch (IllegalArgumentException e) {
            System.out.println("ERROR: This prey already exists for this predator.");
        }
    }

    /**
     * Remove cursor's child based on the name user input.
     * @param name
     * @throws IllegalArgumentException
     */
    public void removeChild(String name) throws IllegalArgumentException {
        //exception cases: no child no name corresponding with the input
        try {
            if (cursor.getLeft() == null) {
                throw new IllegalArgumentException();
            }
            if (!cursor.getLeft().getName().equals(name) && cursor.getMiddle() == null) {
                throw new IllegalArgumentException();
            }
            if (!cursor.getLeft().getName().equals(name) && !cursor.getMiddle().getName().equals(name) && cursor.getRight() == null) {
                throw new IllegalArgumentException();
            }
            if (!cursor.getLeft().getName().equals(name) && !cursor.getMiddle().getName().equals(name) && !cursor.getRight().getName().equals(name)) {
                throw new IllegalArgumentException();
            }

            if (cursor.getLeft().getName().equals(name)) {
                cursor.setLeft(cursor.getMiddle());
                cursor.setMiddle(cursor.getRight());
                cursor.setRight(null);
                System.out.println(name + " has been successfully removed as prey for the " + cursor.getName());
            } else if (cursor.getMiddle().getName().equals(name)) {
                cursor.setMiddle(cursor.getRight());
                cursor.setRight(null);
                System.out.println(name + " has been successfully removed as prey for the " + cursor.getName());
            } else if (cursor.getRight().getName().equals(name)) {
                cursor.setRight(null);
                System.out.println(name + " has been successfully removed as prey for the " + cursor.getName());
            }
        }catch (IllegalArgumentException e){
            System.out.println("ERROR: This prey does not exist for this predator.");
        }
    }
}
