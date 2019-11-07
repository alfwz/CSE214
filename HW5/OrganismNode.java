/**
 * The <code>OrganismNode</code> class contains a
 * node in the tree.
 *
 * @author Fengwei Zhang
 * e-mail: fengwei.zhang@stonybrook.edu
 * Stony Brook ID: 111252554
 * R04
 */
public class OrganismNode {
    //member fields
    private String name;
    private boolean isPlant;
    private boolean isHerbivore;
    private boolean isCarnivore;
    private OrganismNode left;
    private OrganismNode middle;
    private OrganismNode right;
    //constructor
    public OrganismNode(){
    }
    //getter and setter
    public String getName() {return name;}
    public boolean getIsPlant(){return isPlant;}
    public boolean getIsHerbivore(){return isHerbivore;}
    public boolean getIsCarnivore(){return isCarnivore;}
    public OrganismNode getLeft(){return left;}
    public OrganismNode getRight(){return right;}
    public OrganismNode getMiddle(){return middle;}
    public void setLeft(OrganismNode node){this.left=node;}
    public void setMiddle(OrganismNode node){this.middle=node;}
    public void setRight(OrganismNode node){this.right=node;}
    public void setName(String name){this.name=name;}
    public void setIsPlant(boolean isPlant){this.isPlant=isPlant;}
    public void setIsHerbivore(boolean isHerbivore){this.isHerbivore=isHerbivore;}
    public void setIsCarnivore(boolean isCarnivore){this.isCarnivore=isCarnivore;}

    /**
     * Add Node to the current node.
     * @param preyNode
     * @throws PositionNotAvailableException
     * @throws IsPlantException
     * @throws DietMismatchException
     */
    public void addPrey(OrganismNode preyNode)
            throws PositionNotAvailableException, IsPlantException, DietMismatchException{
        if(this.getIsPlant()){
            throw new IsPlantException("ERROR: The cursor is at a plant node. Plants cannot be predators.");
        }

        if(this.getIsHerbivore() && this.getIsCarnivore()==false){
            if(preyNode.getIsPlant()==false){
                throw new DietMismatchException("This prey cannot be added as it does not match the diet of the predator.");

            }
        }
        if(this.getIsHerbivore()==false && this.getIsCarnivore()==true){
            if(preyNode.getIsPlant()==true){
                throw new DietMismatchException("This prey cannot be added as it does not match the diet of the predator.");
            }
        }

        if(this.getLeft()==null){
            this.left = preyNode;
            System.out.println(preyNode.getName() + " has successfully been added as prey for the " + this.getName());
        }else if (this.getMiddle()==null){
            if (this.getLeft().getName().equals(preyNode.getName())) throw new IllegalArgumentException();
            this.middle = preyNode;
            System.out.println(preyNode.getName() + " has successfully been added as prey for the " + this.getName());
        }else if (this.getRight()==null){
            if (this.getLeft().getName().equals(preyNode.getName())) throw new IllegalArgumentException();
            this.right = preyNode;
            System.out.println(preyNode.getName() + " has successfully been added as prey for the " + this.getName());
        }else{
            throw new PositionNotAvailableException("ERROR: There is no more room for more prey for this predator.");
        }
    }
}
