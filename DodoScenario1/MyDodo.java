    import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 *
 * @author Sjaak Smetsers & Renske Smetsers-Weeda
 * @version 3.0 -- 20-01-2017
 */
public class MyDodo extends Dodo
{
    private int myNrOfEggsHatched;
    public MyDodo() {
        super( EAST );
        myNrOfEggsHatched = 0;
    }

    public void act() {
    }

    /**
     * Move one cell forward in the current direction.
     * 
     * <P> Initial: Dodo is somewhere in the world
     * <P> Final: If possible, Dodo has moved forward one cell
     *
     */
    public void move() {
        if ( canMove() ) {
            step();
        } else {
            showError( "I'm stuck!" );
        }
    }

    /**
     * Test if Dodo can move forward, (there are no obstructions
     *    or end of world in the cell in front of her).
     * 
     * <p> Initial: Dodo is somewhere in the world
     * <p> Final:   Same as initial situation
     * 
     * @return boolean true if Dodo can move (no obstructions ahead)
     *                 false if Dodo can't move
     *                      (an obstruction or end of world ahead)
     */
    public boolean canMove() {
        if ( borderAhead() ){ 
            if( fenceAhead()) {
                return false;
            }
            else {
                return false;
            }
        } else {
            return true;
        }
    }

    
    public void Monument() {
        int X = getWorld().getWidth();
        int Y = getWorld().getHeight();
        faceEast();
        int MonumentY = 1;
        goToLocation(0,0);
        boolean test = false;
        int amount = X /= 4;
        while(test == false) {
            if (MonumentY == 4) {
                for (int i = 0; i < amount; i++) {
                    layEgg();
                    step();
                    layEgg();
                    step();
                    layEgg();
                    step();
                    layEgg();
                    goToLocation(getX()+1,getY());
                }
                MonumentY = 0;
                while(!borderAhead()) {
                    faceEast();
                    step();
                }
            }
            if (MonumentY == 3) {
                for (int i = 0; i < amount; i++) {
                    layEgg();
                    step();
                    layEgg();
                    step();
                    layEgg();
                    goToLocation(getX()+2,getY());
                }
                while(!borderAhead()) {
                    faceEast();
                    step();
                }
            }
            if (MonumentY == 2) {
                for (int i = 0; i < amount; i++) {
                    layEgg();
                    step();
                    layEgg();
                    goToLocation(getX()+3,getY());
                }
                while(!borderAhead()) {
                    faceEast();
                    step();
                }
            }
            if (MonumentY == 1) {
                for (int i = 0; i < amount; i++) {
                    layEgg();
                    goToLocation(getX()+4,getY());
                }
                while(!borderAhead()) {
                    faceEast();
                    step();
                }
            }
            int LocationX = getX();
            int LocationY = getY();
            if(LocationX == getWorld().getWidth()-1 && LocationY == getWorld().getHeight()-1) {
                test = true;
            }
            if(borderAhead()) {
                turnRight();
                step();
                faceEast();
                goBackToStartOfRowAndFaceBack();
            }
            MonumentY++;
        }
    }
    /**
     * Hatches the egg in the current cell by removing
     * the egg from the cell.
     * Gives an error message if there is no egg
     * 
     * <p> Initial: Dodo is somewhere in the world. There is an egg in Dodo's cell.
     * <p> Final: Dodo is in the same cell. The egg has been removed (hatched).     
     */    
    public void hatchEgg () {
        if ( onEgg() ) {
            pickUpEgg();
            myNrOfEggsHatched++;
        } else {
            showError( "There was no egg in this cell" );
        }
    }
    
    
    public void eggTrailToNest() {
        boolean eggfound = false;
        while (!onNest()) {
            if (eggAhead()) {
                step();
            }
            else {
                turnLeft();
                if (nestAhead()) {
                    step();
                    layEgg();
                    eggfound = true;
                }
                if (eggAhead() && eggfound == false) {
                    step();
                }
                else { 
                turnRight();
                turnRight();
                if (nestAhead()) {
                    step();
                    layEgg();
                    eggfound = true;
                }
                if (eggAhead() && eggfound == false) {
                    step();
                }
                }
            }
        }
    }
    
    public void faceEast() {
        int dir = getDirection();
        while(dir != 1 ) {
            turnRight();
            dir = getDirection();
        }
    }
    
    public void Lastigdoolhof() 
    {
        int attempts = 0;
        while(!onNest()) {
            int random = randomDirection();
            attempts += 1;
            if (random == 0) {
                turnRight();
                if (!fenceAhead()) {
                    step();
                }
                else { 
                turnLeft();
                }
            }
             if (random == 1) {
                turnLeft();
                if (!fenceAhead()) {
                    step();
                }
                else { 
                turnRight();
                }
            }
            if (random == 2) {
                if (!fenceAhead()) {
                    step();
                }
            }
            if (random == 3) {
                turnLeft();
                turnLeft();
                if (!fenceAhead()) {
                    step();
                }
                else { 
                turnRight();
                turnRight();
                }
            }
        }
        layEgg();
        showCompliment("Je hebt het nest gevonden in "+ attempts +" stappen");
        System.out.println(attempts);
    }
    
    
    
    /**
     * Returns the number of eggs Dodo has hatched so far.
     * 
     * @return int number of eggs hatched by Dodo
     */
    public int getNrOfEggsHatched() {
        return myNrOfEggsHatched;
    }
    
    /**
     * Move given number of cells forward in the current direction.
     * 
     * <p> Initial:   
     * <p> Final:  
     * 
     * @param   int distance: the number of steps made
     */
    public void jump( int distance ) {
        int nrStepsTaken = 0;   
        while ( nrStepsTaken < distance ) {
            move();                        
            nrStepsTaken++;   
            System.out.println("moved "+ nrStepsTaken);
        }
    }

    public void turn180(){
        turnRight();
        turnRight();
    }
    public boolean validCoordinates(int x, int y) {
    int wereldWidth = getWorld().getWidth();
    int wereldHeight = getWorld().getHeight();

    if (x >= 0 && x < wereldWidth && y >= 0 && y < wereldHeight) {
        return true;
    } else {
        showError("Invalid coordinates");
        return false;
    }
}

    
    public void goToLocation(int coordX, int coordY) {
        int locatieX = getX();
        int locatieY = getY();
        int stappenX = 0;
        int stappenY = 0;
        int wereldWidth = getWorld().getWidth();
        int wereldHeight = getWorld().getHeight();
        boolean kanUitvoeren = validCoordinates(coordX, coordY);
        if(kanUitvoeren) {
        if(coordX != locatieX) {
            stappenX = coordX -= locatieX;
        }
        if(coordY != locatieY) {
            stappenY = coordY -= locatieY;
        }
        while (stappenX != 0 || stappenY != 0) {
          System.out.println(stappenX);
          if (stappenX > 0) {
              step();
              stappenX --;
          }
          else if (stappenX < 0) {
              turn180();
              step();
              turn180();
              stappenX ++;
          }
          if (stappenY > 0) {
              turnRight();
              step();
              turnLeft();
              stappenY --;
          }
          else if (stappenY < 0) {
              turnLeft();
              step();
              turnRight();
              stappenY ++;
          }
        }
    }
}


    
    public void pickUpGrainsAndPrintCoordinates()
    {
        while (!borderAhead()) {
            if (onGrain()) {
                pickUpGrain();
                int X = getX();
                int Y = getY();
                System.out.println("Graan op: X:"+X+" Y: "+Y);
            }
            step();
        }
    }
public void GemiddeldEieren(){
    double Gemiddeld = 0;
    int height = getWorld().getHeight();
    int eiAantal = countAllEggsInWorld();
    Gemiddeld = (double) eiAantal / height;
    showCompliment("Gemiddelde eieren per rij: " + Gemiddeld);
}
    
    public void PariteitsbitAlgoritme() {
        int Rij = 0;
        int Eieren = 0;
        int LocatieX = 0;
        faceEast();
        goToLocation(0,0);
        faceEast();
        boolean RijKlaar = false;
        boolean HeleRijKlaar = false;
        while(HeleRijKlaar == false) {
        if(onEgg()) {
            Eieren++;
        }
        while(RijKlaar == false) {
            step();
            Rij = getY();
            if(onEgg()) {
                Eieren++;
            }
            if (getX() == getWorld().getWidth()-1){
                RijKlaar = true;
            }
        }
        if (getX() == getWorld().getWidth()-1 && getY() == getWorld().getHeight()-1) {
            HeleRijKlaar = true;
        }
        if(Eieren % 2 == 0){
            System.out.println("Het is even");
            faceEast();
            goBackToStartOfRowAndFaceBack();
            RijKlaar = false;
            turnRight();
            step();
            turnLeft();
            Eieren = 0;
        }
        else {
            System.out.println("Het is oneven");
            turn180();
            while(!eggAhead()) {
                step();
            }
            while(!canLayEgg()) {
                step();
            }
            layGoldenEgg();
            faceEast();
            goBackToStartOfRowAndFaceBack();
            RijKlaar = false;
            turnRight();
            step();
            turnLeft();
            Eieren = 0;
        }
    }
    HeleRijKlaar = false;
    RijKlaar = false;
    Rij = 0;
    Eieren = 0;
    faceEast();
    goToLocation(0,0);
    faceEast();
    turnRight();
    while(HeleRijKlaar == false) {
        if(onEgg()) {
            Eieren++;
        }
        while(RijKlaar == false) {
            step();
            Rij = getY();
            if(onEgg()) {
                Eieren++;
            }
            if (getY() == getWorld().getHeight()-1){
                RijKlaar = true;
            }
        }
        if (getY() == getWorld().getHeight()-1 && getX() == getWorld().getWidth()-1) {
            HeleRijKlaar = true;
        }
        if(Eieren % 2 == 0){
            System.out.println("Het is even");
            faceEast();
            turnRight();
            goBackToStartOfRowAndFaceBack();
            RijKlaar = false;
            turnLeft();
            step();
            turnRight();
            Eieren = 0;
        }
        else {
            System.out.println("Het is oneven");
            turn180();
            while(!eggAhead()) {
                step();
            }
            while(!canLayEgg()) {
                step();
            }
            layEgg();
            faceEast();
            if(!borderAhead() && !eggAhead()){
            step();
            layEgg();
            faceEast();
            turn180();
            step();
            }
            else if(!borderAhead() && !eggAhead()){
            step();
            layEgg();
            faceEast();
            turn180();
            step();
            }
            faceEast();
            turn180();
            
            faceEast();
            turnRight();
            goBackToStartOfRowAndFaceBack();
            RijKlaar = false;
            turnLeft();
            step();
            turnRight();
            Eieren = 0;
        }
    }
}

public int pickUpEggsInCurrentRow() {
    int totalEggs = 0;
    while (!borderAhead()) {
        if(eggAhead()) {
            totalEggs++;
        }
        step();
    }
    return totalEggs;
}
    
public int countAllEggsInWorld() {
    int totalEggs = 0;
    int height = getWorld().getHeight();
    goToLocation(0, 0);
    while (getY() < height - 1) {
        int eggsInRow = pickUpEggsInCurrentRow();
        totalEggs += eggsInRow;
        if (getY() < height - 1) {
            goBackToStartOfRowAndFaceBack();
            turnRight();
            step(); 
            turnLeft();
        }
    }
    int eggsInRow = pickUpEggsInCurrentRow();
    totalEggs += eggsInRow;
    System.out.println("Totaal aantal eieren: " + totalEggs);
    return totalEggs;
}

public void LayEggsOnWay()
{
    while (!borderAhead()) {
        if (nestAhead()) 
        {
            step();
            layEgg();
        }
        step();
    }
}
    
    public void layTrailOfEggs(int n) {
        if (n <= 0) {
        showError("Aantal mag niet negatief of 0 zijn.");
        return;
        }   
        for (int i = 0; i < n; i++) {
            layEgg();
            if (i < n - 1) {
                step();
            }
    }
}
    
    
    public void climbOverFence(){
        if(fenceAhead())
        {
        turnLeft();
        step();
        turnRight();
        step();
        step();
        turnRight();
        step();
        turnLeft();
        }
        else {
            step();
        }
    }
    public void walkToWorldEdgeClimbingOverFences(){
        while(!borderAhead()){
            climbOverFence();
            if (onNest()) 
            {
                layEgg();
                step();
            }
        }
    }
    
    

    public void grainahead(){
        while(!borderAhead() || onGrain()) {
            if(! onGrain()) {
                step();
            }
            else 
            {
                turn180();
                step();
                turn180();
            }
            
        }
    }
    
    
    public void gotoEgg(){
        while (! onEgg()){
            step();
        }
    }
    public void walkAroundFencedArea(){
        while(!borderAhead() && !onEgg()) {
            move();
            turnRight();
            if(fenceAhead()) {
                turnLeft();
                if (fenceAhead()){
                    turn180();
                    turnRight();
                }
            }
        }
    }
    
    public void stepOneCellBackwards(){
        turn180();
        step();
        turn180();
    }
    
    public void goBackToStartOfRowAndFaceBack(){
        turn180();
        while( ! borderAhead() ){
            move();
        }
        turn180();
    }
    
    
    
    /**
     * Walks to edge of the world printing the coordinates at each step
     * 
     * <p> Initial: Dodo is on West side of world facing East.
     * <p> Final:   Dodo is on East side of world facing East.
     *              Coordinates of each cell printed in the console.
     */

    public void walkToWorldEdgePrintingCoordinates( ){
        while( ! borderAhead() ){
            int X = getX();
            int Y = getY();
            System.out.println("X:"+X+" Y: "+Y);
            move();
        }
        
    }

    /**
     * Test if Dodo can lay an egg.
     *          (there is not already an egg in the cell)
     * 
     * <p> Initial: Dodo is somewhere in the world
     * <p> Final:   Same as initial situation
     * 
     * @return boolean true if Dodo can lay an egg (no egg there)
     *                 false if Dodo can't lay an egg
     *                      (already an egg in the cell)
     */

    public boolean canLayEgg( ){
if( onEgg() ){
        return false;
        }else{
            return true;
        }
    }
}
