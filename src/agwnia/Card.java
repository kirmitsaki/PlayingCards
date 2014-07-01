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
public abstract class Card {
    private Rank rank;
    private Suit suit;
    ImageIcon pic;
    
    public Card(Rank rank, Suit suit, ImageIcon pic) {
        this.rank = rank;
        this.suit = suit;
        this.pic = pic;
    }

    public Suit getSuit() {
        return suit;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    public int getPoints() {
        return rank.getPoints();
    }
    
    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }
    
    public ImageIcon getPic(){
        return pic;
    }
    
    public void setPic(ImageIcon pic){
        this.pic = pic;
    }

       @Override
    public String toString() {
        return "Card{" + "rank=" + rank + ", suit=" + suit + '}';
    }

//
//    public void SetSuit(Suit suit){
//        if(rank==Rank.ACE){
//            this.suit = suit;
//        }
//    }
//
//    public boolean Special(){
//        if(rank==Rank.ACE||rank==Rank.SEVEN||rank==Rank.EIGHT||rank==Rank.NINE){
//            return true;
//        }
//        return false;
//    }
//
//    public String toString(){
//        return rank+" of "+suit;
//    }
//

//
//    public int SignificancePoints(){
//        switch (rank) {
//            case TWO:
//                return 1;
//            case THREE:
//                return 2;
//            case FOUR:
//                return 3;
//            case FIVE:
//                return 4;
//            case SIX:
//                return 5;
//            case TEN:
//                return 6;
//            case JACK:
//                return 7;
//            case QUEEN:
//                return 7;
//            case KING:
//                return 7;
//            case SEVEN:
//                return 8;
//            case EIGHT:
//                return 9;
//            case NINE:
//                return 10;
//            case ACE:
//                return 11;
//            default :
//                return 0;
//        }
//    }



}//end of Card class
