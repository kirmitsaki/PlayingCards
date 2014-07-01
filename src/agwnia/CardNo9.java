/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agwnia;

import javax.swing.ImageIcon;

/**
 *
 * @author chryski
 */


public class CardNo9 extends Card{
    private static boolean lostTurn = false;

    public CardNo9(Rank rank, Suit suit, ImageIcon pic) {
        super(rank, suit, pic);
    }

    /**
     * Get the value of lostTurn
     *
     * @return the value of lostTurn
     */
    public static boolean playerLosesTurn() {
        return lostTurn;
    }
    
    public static void card9isThrown(){
        CardNo9.lostTurn = true;
    }

    /**
     * Set the value of lostTurn
     *
     * @param lostTurn new value of lostTurn
     */
    public static void playerLostHisTurn() {
        CardNo9.lostTurn = false;
    }
    
    

}
