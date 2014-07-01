/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agwnia;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import javax.swing.ImageIcon;

/**
 *
 * @author chryski
 */
public abstract class CardList {
    public ArrayList<Card> list;

    Images im;

    public CardList() {
        this.list = new ArrayList<Card>();
//        im = new Images(list);
    }

    public CardList(ArrayList<Card> list) {
        this.list = list;
//        im = new Images(list);
    }
  
    public void addCards(CardList newList){
        this.list.addAll(newList.list);
    }
        
    public ArrayList<Card> getList() {
        return list;
    }
//
//    public void setList(ArrayList<Card> list) {
//        this.list = list;
//    }

    public void addCardEnd(Card card) {
        list.add(card);
    }

    public void addCardBegin(Card card) {
        list.add(0, card);
    }

    public Card getCard(int i){
       return list.get(i);
    }
    public Card getLastCard() {
        return list.get(list.size() - 1);
    }

    public Card getFirstCard() {
        return this.list.get(0);
    }

    public void removeLastCard() {
        list.remove(list.size() - 1);
    }

    public void removeFirstCard() {
        list.remove(0);
    }
    
    public void removeCard(int i){
        list.remove(i);
    }

    public void clearList() {
        this.list.clear();
    }

//
//    public void RemoveCard(int index){
//        list.remove(index);
//    }
//    
//    public void RemoveCard(Card card){        
//        list.remove(card);
//    }
    public int calculatePoints() {
        int listpoints = 0;
        for (int i = 0; i < list.size(); i++) {
            listpoints += list.get(i).getPoints();
        }
        return listpoints;
    }

    public int cardlistSize() {
        return list.size();
    }

    public void ShuffleCardlist() {
        long seed = System.nanoTime();
        Collections.shuffle(list, new Random(seed));
    }

    public void createNewDeck() {

        for (Rank rank : Rank.values()) {
            for (Suit suit : Suit.values()) {
                Card card;
                switch (rank) {
                    case ACE:
                        card = new CardAce(rank, suit, null);

                        break;
                    case NINE:
                        card = new CardNo9(rank, suit, null);
                        break;
                    case SEVEN:
                        card = new CardNo7(rank, suit, null);
                        break;
                    default:
                        card = new SimpleCard(rank, suit, null);
                }
                this.list.add(card);
            }
        }
        
        
    }
    
    public void insertPics(){
        
        im = new Images(list);
       // for (ImageIcon pic : im.getPics()){
         //   for (Card card: list){
          //      card.setPic(pic);
          //  }
        //}
    }
}
