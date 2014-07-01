/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package agwnia;

/**
 *
 * @author chryski
 */
public class Hand  extends CardList{
    
    public Hand() {
    }

    public int getPoints(){
        return super.calculatePoints();
    }

    public void clearHand(){
        super.clearList();
    }

}//end of Hand class
