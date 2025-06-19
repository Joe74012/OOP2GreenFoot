import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

/**
 *
 * @author Sjaak Smetsers & Renske Smetsers-Weeda
 * @version 3.1 -- 29-07-2017
 */
public class MyDodo extends Dodo
{

    public MyDodo() {
        super( EAST );
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
     * Test if Dodo can move forward, 
     * i.e. there are no obstructions or end of world in the cell in front of her.
     * 
     * <p> Initial:   Dodo is somewhere in the world
     * <p> Final:     Same as initial situation
     * 
     * @return  boolean true if Dodo can move (thus, no obstructions ahead)
     *                  false if Dodo can't move
     *                      there is an obstruction or end of world ahead
     */
    public boolean canMove() {
        if ( borderAhead() || fenceAhead() ){
            return false;
        } else {
            return true;
        }
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
        int nrStepsTaken = 0;               // set counter to 0
        while ( nrStepsTaken < distance ) { // check if more steps must be taken  
            move();                         // take a step
            nrStepsTaken++;                 // increment the counter
        }
    }

    /**
     * Places all the Egg objects in the world in a list.
     * 
     * @return List of Egg objects in the world
     */
    public List<Egg> getListOfEggsInWorld() {
        return getWorld().getObjects(Egg.class);
    }

    public List<Integer> createListOfNumbers() {
        return new ArrayList<> (Arrays.asList( 2, 43, 7, -5, 12, 7 ));
    }

    /**
     * Method for praciticing with lists.
     */
    public void practiceWithLists( ){
        List<Integer> listOfNumbers = createListOfNumbers();
        
        //the following is incorrect and is to be fixed in challenge 6.1c
        System.out.println("First element: " + listOfNumbers.get(1) ); 
    }

    public void practiceWithListsOfSurpriseEggs( ){
        List<SurpriseEgg>  listOfEgss = SurpriseEgg.generateListOfSurpriseEggs( 12, getWorld() );
    }
    
    public void goBackToStartOfRowAndFaceBack(){
        turnRight();
        turnRight();
        while( ! borderAhead() ){
            move();
        }
        turnRight();
        turnRight();
    }
    
    public void faceEast() {
        int dir = getDirection();
        while(dir != 1 ) {
            turnRight();
             dir = getDirection();
        }
    }
    
    public void turn180(){
        turnRight();
        turnRight();
    }

    public void goToLocation(int coordX, int coordY) {
        int locatieX = getX();
        int locatieY = getY();
        int stappenX = 0;
        int wereldWidth = getWorld().getWidth();
        int wereldHeight = getWorld().getHeight();
        boolean kanUitvoeren = false;
        int stappenY = 0;
        if(wereldWidth > coordX && wereldHeight > coordY && 0 <= coordX && 0 <= coordY) {
            kanUitvoeren = true;
        }
        else if (wereldWidth < coordX || wereldHeight < coordY || 0 > coordY || 0 > coordX) {
            kanUitvoeren = false;
        }
        if(kanUitvoeren == true) {
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
    else {
      System.out.println("Invalid coordinates");
      System.out.println("X moet lager dan "+wereldWidth+" zijn en hoger dan 0 zijn "); 
      System.out.println("Y moet lager dan "+wereldHeight+" zijn en hoger dan 0 zijn"); 
    }
}
    
    public int countEggsInRow() {
    faceEast();
    goToLocation(0, 0);
    faceEast();
    int eggCount = 0;
    int somWaarden = 0;
    int locatieX;
    int locatieY;
    int hoogsteWaarde = 0;
    int totaal = 0;
    if (onEgg()) {
        eggCount++;
    }
    while (!(getX() == 0 && getY() == 11)) {
        if (!borderAhead()) {
            step();
            if (onEgg()) {
                Actor actor = getOneObjectAtOffset(0, 0, Egg.class);
                    if (actor instanceof Egg) {
                        Egg egg = (Egg) actor;
                        int waarde = egg.getValue();
                        somWaarden += waarde;
                        eggCount++;
                        if (waarde > hoogsteWaarde) {
                            hoogsteWaarde = waarde;
                            locatieX = getX();
                            locatieY = getY();
                        }
                        System.out.println("Egg on " + getX() + "," + getY() + " With value: " + waarde);
                    }
            }
        } else {
            if (getY() % 2 == 0) {
                turnRight();
                if (!borderAhead()) {
                    step();
                    turnRight();
                }
            } else {
                turnLeft();
                if (!borderAhead()) {
                    step();
                    turnLeft();
                }
            }
            if (onEgg()) {
                    Actor actor = getOneObjectAtOffset(0, 0, Egg.class);
                    if (actor instanceof Egg) {
                        Egg egg = (Egg) actor;
                        int waarde = egg.getValue();
                        somWaarden += waarde;
                        eggCount++;
                        if (waarde > hoogsteWaarde) {
                            hoogsteWaarde = waarde;
                            locatieX = getX();
                            locatieY = getY();
                        }
                        System.out.println("Egg on " + getX() + "," + getY() + " With value: " + waarde);
                    }
                }
        }
    }
    double gemiddeld = somWaarden /= eggCount;
    showCompliment("Gemiddelde waarden: " + gemiddeld);
    goBackToStartOfRowAndFaceBack();
    showCompliment("Aantal eieren: " + eggCount);
    return eggCount;
    
}
public void moveRandomly() {
    Mauritius world = (Mauritius) getWorld();
    int maximaleStappen = Mauritius.MAXSTEPS;
    int score = Mauritius.Score;

    while (maximaleStappen >= 1) {
        Egg targetEgg = null;
        int closestDistance = Integer.MAX_VALUE;
        int highestValue = -1;

        for (Object obj : getWorld().getObjects(Egg.class)) {
            Egg egg = (Egg) obj;
            int value = egg.getValue(); 
            int dx = egg.getX() - getX();
            int dy = egg.getY() - getY();
            int distance = dx * dx + dy * dy;

            if (value > highestValue || (value == highestValue && distance < closestDistance)) {
                highestValue = value;
                closestDistance = distance;
                targetEgg = egg;
            }
        }

        if (targetEgg == null) {
            break;
        }
        int stappenX = targetEgg.getX() - getX();
        int stappenY = targetEgg.getY() - getY();

        while ((stappenX != 0 || stappenY != 0) && maximaleStappen > 0) {
            if (stappenX > 0) {
                step(); stappenX--; maximaleStappen--;
            } else if (stappenX < 0) {
                turn180(); step(); turn180(); stappenX++; maximaleStappen--;
            }
            if (onEgg()) {
                Egg egg = (Egg) getOneIntersectingObject(Egg.class);
                if (egg != null) {
                    score += egg.getValue();
                    pickUpEgg();
                }
            }
            if (stappenY > 0) {
                turnRight(); step(); turnLeft(); stappenY--; maximaleStappen--;
            } else if (stappenY < 0) {
                turnLeft(); step(); turnRight(); stappenY++; maximaleStappen--;
            }
            if (onEgg()) {
                Egg egg = (Egg) getOneIntersectingObject(Egg.class);
                if (egg != null) {
                    score += egg.getValue();
                    pickUpEgg();
                }
                
            }
            world.updateScore(maximaleStappen,score);
        }

        if (onEgg()) {
            pickUpEgg();
            score += targetEgg.getValue();
        }
        
        world.updateScore(maximaleStappen, score);
    }
}
}


