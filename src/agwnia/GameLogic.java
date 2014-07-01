/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package agwnia;

/**
 *
 * @author chryski
 */
import java.util.*;

public class GameLogic {
    private ClosedCards closedCards;
    private OpenedCards openedCards;
    private ArrayList<Player> players;
    private Card tmpCard;//temporary card. in case of Ace setSuit of this card so as to not change the suit of the object
    private gameType type;
    private int totalPoints;
    private int totalGames;
    private int currentGames;
    private int previousGameStartingPlayer;
    private int CurrentPlayer;
    private boolean endOfGame;

    public GameLogic(gameType type, int points, int games, int numberOfComputerPlayers) {
        this.type = type;
        this.totalPoints = points;
        this.totalGames = games;
        this.currentGames = 0;
        this.previousGameStartingPlayer = -1;

        players = new ArrayList<Player>();

        HumanPlayer hplayer = new HumanPlayer();
        players.add(hplayer);
        for (int i = 0; i < numberOfComputerPlayers; i++) {
            ComputerPlayer player = new ComputerPlayer("Computer"+(i+1));
            players.add(player);
        }

        this.closedCards = new ClosedCards();
        this.openedCards = new OpenedCards();
    }

    public boolean isEndOfAllGames() {
        switch (type) {
            case PointsOnly:
                int maxPoints = 0;
                for (int i = 0; i < this.players.size(); i++) {
                    maxPoints = Math.max(maxPoints, this.players.get(i).getFinalPoints());
                }
                if (maxPoints >= this.totalPoints) {
                    return true;
                }
                break;
            case GamesOnly:
                if (this.totalGames == this.currentGames) {
                    return true;
                }
        }
        return false;
    }

    public boolean isEndOfGame() {
        return endOfGame;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Player getCurrentPlayer() {
        return this.players.get(CurrentPlayer);
    }

    public Card getTopCard() {
        return this.tmpCard;
    }

    private void setCurrentPlayer() {
        this.CurrentPlayer++;
        if (this.CurrentPlayer == this.players.size()) {
            this.CurrentPlayer = 0;
        }
    }

    private void setNextStartingPlayer() {
        this.previousGameStartingPlayer++;
        if (this.previousGameStartingPlayer == this.players.size()) {
            this.previousGameStartingPlayer = 0;
        }
    }

    public void setSuit(Suit suit) {
        this.tmpCard.setSuit(suit);
    }

    public void reShuffle(){
        if(closedCards.cardlistSize()==1){
            Card tmp1 = openedCards.getTopCard();
            Card tmp2 = closedCards.drawCard();

            closedCards.addCards(openedCards);
            closedCards.addCardBegin(tmp2);
            closedCards.ShuffleCardlist();

            openedCards.clearList();
            openedCards.addCardEnd(tmp1);
        }
    }

    public void playerDrawsCard() {
        reShuffle();//case closedCards==1, then reshuffle the openedcards and make them new close
        this.players.get(CurrentPlayer).takeCard(this.closedCards.drawCard());
    }

    public boolean hasPlayerDrawCard(){
        return this.players.get(this.CurrentPlayer).getDrawCard();
    }

    private void checkIfGameIsOver(){
        if (this.players.get(CurrentPlayer).nCards() == 0) {
                this.endOfGame = true;
                for (int i = 0; i < this.players.size(); i++) {
                    this.players.get(i).addCurrentPoints();
                }
            }
    }

    public void playerPlays(Card card) {
        if (card == null) {
            /* 1) den exei na paiksei kai dn exei traviksei karta
             * 2) den exei na paiksei kai exei traviksei karta
             * 3) katw einai 7 ari kai den exei 7 na paiksei = pairnei 2
             * 4) katw einai 7 ari alla einai apo ton proigoumeno paikti
             * 5) katw einai 7 ari kai den exei genika na paiksei = pairnei 1
             * 6) katw einai 7 ari kai den exei genika na paiksei k exei idi parei = xanei tin seira t
             *
             */
            if ((this.openedCards.getLastCard().getRank().equals(Rank.SEVEN)) && (CardNo7.isMustTakeCards())) {//an einai gia ton eauto tou
                for (int i = 0; i < CardNo7.getNCardsAll(); i++) {
                    this.playerDrawsCard();
                }
                CardNo7.makeNCardsAllNormal();
                return;
            }
            if (this.players.get(CurrentPlayer).getDrawCard()) { //an exei idi traviksei karta
                this.players.get(CurrentPlayer).setDrawCard(false);
                this.setCurrentPlayer();// paizei o epomenos
            } else {
                this.playerDrawsCard();
                this.players.get(CurrentPlayer).setDrawCard(true);
            }
        } else {
            this.players.get(CurrentPlayer).setDrawCard(false);
            this.openedCards.openCard(card);

            this.tmpCard.setRank(card.getRank());
            this.tmpCard.setSuit(card.getSuit());
            this.tmpCard.setPic(card.getPic());

            switch (card.getRank()) {
                case ACE:
                    this.setCurrentPlayer();
                    break;
                case EIGHT:
                    break;
                case NINE:
                    checkIfGameIsOver();
                    this.setCurrentPlayer();//o epomenos xanei ti seira toy
                    this.setCurrentPlayer();//paizei o methepomenos
                    break;
                case SEVEN:
                    checkIfGameIsOver();
                    CardNo7.increaseNCardsAll();
                    this.setCurrentPlayer();
                    break;
                default:
                    checkIfGameIsOver();
                    this.setCurrentPlayer();
            }

            //TODO elegxos an einai end of game o computer player den bgainei pote nikitis akoma kai an petaxei oles tis kares toy
            //TODO an o xristis rixei 8 tleytaia karta den bgainei. prepei na travixei mia

        }
    }

    public void createNewGame() {//ena paixnidi
        this.currentGames++;
        this.endOfGame = false;

        this.setNextStartingPlayer();
        this.CurrentPlayer = this.previousGameStartingPlayer;

        this.closedCards.clearList();
        this.openedCards.clearList();
        for (int j = 0; j < this.players.size(); j++) {
            this.players.get(j).clearHand();
        }

        this.closedCards.createNewDeck();
        this.closedCards.insertPics();
        this.closedCards.ShuffleCardlist();
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < this.players.size(); j++) {
                this.players.get(j).takeCard(this.closedCards.drawCard());
            }
        }

        Card topCard = this.closedCards.drawCard();
        this.openedCards.openCard(topCard);
        this.tmpCard = new SimpleCard(topCard.getRank(),topCard.getSuit(),topCard.getPic());

        switch (tmpCard.getRank()) {
            case NINE:
                this.setCurrentPlayer();//paizei o epomenos
                break;
            case SEVEN:
                CardNo7.increaseNCardsAll();
                break;
            default:
        }
    }

    public static boolean MatchRankSuit(Card playercard, Card openedcard) {
        if (playercard.getRank().equals(Rank.ACE)) {
            if (openedcard.getRank().equals(Rank.ACE)) {//ace on ace
                return false;
            } else {//ace match
                return true;
            }
        } else if ((openedcard.getRank().equals(Rank.SEVEN)) && (CardNo7.isMustTakeCards())) {
            if (playercard.getRank().equals(Rank.SEVEN)) { //αν δώσει 7 οκ
                return true;
            } else { //αλλιώς δεν ταιριάζει και θα πάρει κάρτες
                return false;
            }
        } else if (playercard.getRank().equals(openedcard.getRank())) { //rank match//&& !openedcard.GetRank().equals(Rank.ACE)) {
            return true;
        } else if (playercard.getSuit().equals(openedcard.getSuit())) {//suit match
            return true;
        } else { //no match
            return false;
        }
    }

//    public boolean winnerOfRound(){
//        if(players.get(CurrentPlayer).nCards()==0){
//            return true;
//        }
//        return false;
//    }

}//end of GameLogic