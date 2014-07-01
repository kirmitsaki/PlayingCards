/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package agwnia;

/**
 *
 * @author chryski
 */
public class ComputerPlayer extends Player{

    public ComputerPlayer(String newname) {
        super();
        name = newname;
    }

    public Card chooseCardToThrow(Card openedcard) {
        Card matchcard = null;
        int matchCardIndex = -1;
        int maxpoints = 0;

        for (int i = 0; i < hand.cardlistSize(); i++) {
            if (GameLogic.MatchRankSuit(hand.getList().get(i), openedcard)) {
                if (maxpoints < hand.getList().get(i).getPoints()) {
                    matchcard = hand.getList().get(i);
                    matchCardIndex = i;
                    maxpoints = hand.getList().get(i).getPoints();
                }
            }
        }
        //TODO ????ti kanei edo?
        if (matchCardIndex != -1){
            hand.getList().remove(matchCardIndex);
        }
        return matchcard;
    }


    public Suit changeSuit() {
        int suitClubs = 0;
        int suitDiamonds = 0;
        int suitHearts = 0;
        int suitSpade = 0;

        for (int i = 0; i < hand.cardlistSize(); i++) {
            if (hand.getList().get(i).getSuit().equals(Suit.CLUBS)) {
                suitClubs++;
            } else if (hand.getList().get(i).getSuit().equals(Suit.DIAMONDS)) {
                suitDiamonds++;
            } else if (hand.getList().get(i).getSuit().equals(Suit.HEARTS)) {
                suitHearts++;
            } else if (hand.getList().get(i).getSuit().equals(Suit.SPADES)) {
                suitSpade++;
            }
        }

        int maxSuit = Math.max(suitClubs, Math.max(suitDiamonds, Math.max(suitHearts, suitSpade)));

        if (maxSuit == suitClubs) {
            return Suit.CLUBS;
        } else if (maxSuit == suitDiamonds) {
            return Suit.DIAMONDS;
        } else if (maxSuit == suitHearts) {
            return Suit.HEARTS;
        } else {
            return Suit.SPADES;
        }
    }

}//end of ComputerPlayer class
