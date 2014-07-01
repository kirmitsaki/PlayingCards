/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package agwnia;

/**
 *
 * @author chryski
 */
public class ClosedCards  extends CardList{

    public ClosedCards() {
    }

    public Card drawCard() {
        Card card = super.getFirstCard();
        super.removeFirstCard();
        return card;
    }

}//end of ClosedCards class
