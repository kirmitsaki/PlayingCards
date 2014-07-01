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
public class CardNo7 extends Card{
    private static final int nCardsNormal = 2;//with one 7 open player must take 2 cards
    private static int nCardsAll = 0; //if he drops 7 next player takes +2 cards
    private static boolean mustTakeCards = true;

    public CardNo7(Rank rank, Suit suit, ImageIcon pic) {
        super(rank, suit, pic);
    }

    public static boolean isMustTakeCards() {
        return mustTakeCards;
    }

    public static void setMustTakeCards(boolean mustTakeCards) {
        CardNo7.mustTakeCards = mustTakeCards;
    }
    
    /**
     * Get the value of card to draw for 1 seven
     *
     * @return the value of nCardsNormal
     */
    public static int getNCardsNormal() {
        return nCardsNormal;
    }

    /**
     * Get the value of nCardsAll
     *
     * @return the value of nCardsAll
     */
    public static int getNCardsAll() {
        return nCardsAll;
    }

    /**
     * Set the value of nCardsAll
     *
     * @param nCardsAll new value of nCardsAll
     */
    public static void increaseNCardsAll() {
        CardNo7.nCardsAll += nCardsNormal;
        setMustTakeCards(true);
    }
    
    public static void makeNCardsAllNormal(){
//        CardNo7.nCardsAll = nCardsNormal;
        CardNo7.nCardsAll = 0;
        setMustTakeCards(false);
        
    }

}
