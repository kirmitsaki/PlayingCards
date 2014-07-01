/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package agwnia;

/**
 *
 * @author chryski
 */
public class Player {
    String name;
    Hand hand;
    private int points;
    private Boolean drawCard;
    
    public Player() {
        this.name = "";
        this.hand = new Hand();
        this.points = 0;
        this.drawCard = false;
    }

    public Boolean getDrawCard() {
        return drawCard;
    }

    public void setDrawCard(Boolean drawCard) {
        this.drawCard = drawCard;
    }

    /**
     * Get the points of one game
     *
     * @return the points of one game
     */
    public int getCurrentPoints() {
        return hand.getPoints();
    }

    /**
     * Get the final points of all games
     *
     * @return the the final points
     */
    public int getFinalPoints() {
        return points;
    }

    public void addCurrentPoints() {
        this.points += hand.getPoints();
    }

    public void takeCard(Card card) {
        hand.addCardEnd(card);
    }
    
    public void trowCard(Card card){
        
    }
    
    public void clearHand(){
        hand.clearHand();
    }
    
    /**
     * Get the number of cards
     *
     * @return the number of cards
     */
    public int nCards() {
        return hand.cardlistSize();
    }

}//end of Player class
