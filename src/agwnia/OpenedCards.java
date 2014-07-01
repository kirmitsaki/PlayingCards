/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package agwnia;

/**
 *
 * @author chryski
 */
public class OpenedCards  extends CardList{

    public OpenedCards() {
    }

    public void openCard(Card card){
        super.addCardEnd(card);
    }

    public Card getTopCard() {
        Card card = super.getLastCard();
        super.removeLastCard();
        return card;
    }

}//end of OpenedCards class
