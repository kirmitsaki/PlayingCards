/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agwnia;

/**
 *
 * @author chryski
 */
import java.util.ArrayList;
import java.util.Scanner;

public class GameCLI {

    private GameLogic game;

    private void showInfo() {
        System.out.println(" Ο νικητής του γύρου είναι: mpla mpla");
        for (int i = 0; i < game.getPlayers().size(); i++) {
            if (game.getPlayers().get(i).getClass() == HumanPlayer.class) {
                System.out.print("Οι πόντοι σου σε αυτόν τον γύρο είναι : " + game.getPlayers().get(i).getCurrentPoints());
                System.out.print(". Και οι συνολικοί πόντοι σου : " + game.getPlayers().get(i).getFinalPoints() + "\n");
            } else {
                System.out.print("Οι πόντοι του " + game.getPlayers().get(i).name + " είναι : " + game.getPlayers().get(i).getCurrentPoints());
                System.out.print(". Και οι συνολικοί πόντοι του : " + game.getPlayers().get(i).getFinalPoints() + "\n");
            }

        }
    }

    private void showOpenedCard() {
        System.out.println("___________________________________________________________");
        System.out.println("-> Η ανοικτή κάρτα είναι: " + game.getTopCard());
    }

    private void showHumanHand() {
        ArrayList<Card> list = game.getCurrentPlayer().hand.getList();
        if (list.isEmpty()) {
            System.out.println("Έχεις 0 κάρτες");

        } else {
            System.out.print("Έχεις : \n");
            for (int i = 1; i <= list.size(); i++) {
                System.out.println(" [" + i + "] " + list.get(i - 1));
            }
        }
    }

    private void humanCardAce() {
        int suit = 0;
        System.out.println("Διάλεξε το χρώμα του άσου ή 0 για να μην αλλάξει χρώμα : ");
        System.out.println("  [1] για Clubs");
        System.out.println("  [2] για Diamonds");
        System.out.println("  [3] για Hearts");
        System.out.println("  [4] για Spade");
        Scanner scanner = new Scanner(System.in);
        suit = scanner.nextInt();
        switch (suit) {
            case 0:
                break;
            case 1:
                game.setSuit(Suit.CLUBS);
                break;
            case 2:
                game.setSuit(Suit.DIAMONDS);
                break;
            case 3:
                game.setSuit(Suit.HEARTS);
                break;
            case 4:
                game.setSuit(Suit.SPADES);
                break;
        }
    }

    private void humanCard(Card card) {
    }

    public void start() {
        System.out.println("___________________________________________________________");
        System.out.println("Πόσοι παίκτες H/Y θέλεις να είναι στο παιχνίδι; :");
        System.out.println(" [1] για 1 Παίκτη");
        System.out.println(" [2] για 2 Παίκτες");
        System.out.println(" [3] για 3 Παίκτες");
        Scanner scanner = new Scanner(System.in);
        int players = scanner.nextInt();
        //elegxos timon
        while (players < 1 || players > 3) {
            System.out.println("Λάθος αριθμός.. διάλεξε ξανά");
            System.out.println(" [1] για 1 Παίκτη");
            System.out.println(" [2] για 2 Παίκτες");
            System.out.println(" [3] για 3 Παίκτες");
            players = scanner.nextInt();
        }

        System.out.println("___________________________________________________________");
        System.out.println("Θα παίξεις με Παρτίδες ή Πόντους; :");
        System.out.println(" [1] για παρτίδες");
        System.out.println(" [2] για πόντους");
        int typeofgame = scanner.nextInt();
        //elegxos arithmon
        while (typeofgame < 1 || typeofgame > 2) {
            System.out.println("Λάθος αριθμός.. διάλεξε ξανά");
            System.out.println(" [1] για παρτίδες");
            System.out.println(" [2] για πόντους");
            typeofgame = scanner.nextInt();
        }

        int numberofgames = 0, numberofpoints = 0;
        gameType type = null;
        if (typeofgame == 1) {
            System.out.println("___________________________________________________________");
            System.out.print("Αριθμός παρτίδων για τέλος παιχνιδιού : ");
            numberofgames = scanner.nextInt();
            //elegxos arithmon
            while (numberofgames < 1) {
                System.out.println("Λάθος αριθμός.. διάλεξε ξανά");
                numberofgames = scanner.nextInt();
            }
            type = gameType.GamesOnly;
        } else if (typeofgame == 2) {
            System.out.println("___________________________________________________________");
            System.out.print("Αριθμός πόντων για τέλος παιχνιδιού : ");
            numberofpoints = scanner.nextInt();
            //elegxos arithmon
            while (numberofpoints < 1) {
                System.out.println("Λάθος αριθμός.. διάλεξε ξανά");
                numberofgames = scanner.nextInt();
            }
            type = gameType.PointsOnly;
        }

        game = new GameLogic(type, numberofpoints, numberofgames, players);

        //TODO elegxos poios paizei protos? kai na allazei h seira toy paikth

        while (!game.isEndOfAllGames()) { //until the end of all games
            game.createNewGame();
            System.out.println("___________________________________________________________");
            System.out.println("Πρώτος παίζει o " + game.getPlayers().get(game.getPlayers().indexOf(game.getCurrentPlayer())).name);
            while (!game.isEndOfGame()) { //for every round
                showOpenedCard();
                Player player = game.getCurrentPlayer();

                if (player.getClass() == HumanPlayer.class) { //human plays
                    Card throwingCard = null;
                    System.out.println("___________________________________________________________");
                    System.out.print("-> Παίζεις.");
                    int pass = 0;////////////////

                    showHumanHand();
                    if (game.hasPlayerDrawCard()) {
                        System.out.print("Διάλεξε τι θες να πετάξεις ή  δώσε 0 για να πας πασο: ");
                    } else {
                        System.out.print("Διάλεξε τι θες να πετάξεις ή  δώσε 0 για να τραβήξεις φύλλο: ");
                    }
                    int throwingCardIndex = scanner.nextInt();

                    //TODO elegxos timis kartas pou dinei o xristis
                    while (throwingCardIndex > player.nCards() || throwingCardIndex < 0) {
                        System.out.println("Πρόβλημα.Μη έγκυρος αριθμός!");
                        System.out.print("Διάλεξε τι θες να πετάξεις ή  δώσε 0 για να τραβήξεις φύλλο: ");
                        throwingCardIndex = scanner.nextInt();
                    }

                    System.out.println(" Έδωσες: " + throwingCardIndex);


                    //elegxos an tairiazei h karta me thn anoikti allios epilogi na dialexei pali o paikths
                    if (throwingCardIndex != 0) {
                        while ((throwingCardIndex != 0) && (!GameLogic.MatchRankSuit(player.hand.getCard(throwingCardIndex - 1), game.getTopCard()))) {
                            System.out.println("Δεν μπορείς να ρίξεις αυτή την κάρτα. Διάλεξε άλλη ή 0 για να τραβήξεις φύλλο : ");
                            showHumanHand();
                            throwingCardIndex = scanner.nextInt();
                            System.out.println(" Έδωσες: " + throwingCardIndex);
                        }
                    }

                    //an tairiazei kai einai diafori apo to 0 tote tin petaei
                    if (throwingCardIndex != 0) {
                        throwingCard = player.hand.getCard(throwingCardIndex - 1);
                        player.hand.removeCard(throwingCardIndex - 1);
                    }

                    game.playerPlays(throwingCard);

                    //if list==0 && last card thrown = ace || eight ->message
                    if(game.getCurrentPlayer().hand.getList().isEmpty()){
                        if(throwingCard.getRank().equals(Rank.ACE)){
                            System.out.println("Δεν μπορείς να βγεις με άσο, πρέπει να τραβήξεις μια κάρτα");
                            game.playerPlays(null);
                        }else if(throwingCard.getRank().equals(Rank.EIGHT)){
                            System.out.println("Δεν μπορείς να βγεις με 8, πρέπει να τραβήξεις μία κάρτα");
                            game.playerPlays(null);
                        }else{
                            System.out.println("Βγήκες!");
                        }
                    }

                    if (throwingCard != null) {
                        if (throwingCard.getRank().equals(Rank.ACE)) {
                            Suit suit = null;
                            humanCardAce();
                            if (player.nCards() == 0) {
                                //get a card if ace is last card
                                System.out.println("Δεν μπορείς να βγείς με άσο. Πρέπει να τραβήξεις μια κάρτα ακόμα.");
                            }
                        }
                        System.out.println(" Η κάρτα που έδωσες " + throwingCard);
                    } else {
                        pass++;
                        if (pass == 1) {
                        } else if (pass == 2) {
                            System.out.println(" Δεν είχες κάρτα, πάς πάσο");
                        }
                    }

                } else if (player.getClass() == ComputerPlayer.class) { //computer plays
                    System.out.println("___________________________________________________________");
                    System.out.println("-> Παίζει ο " + player.name + " που έχει " + player.nCards() + " κάρτες.");
                    showHumanHand();

                    Card throwingCard = ((ComputerPlayer) player).chooseCardToThrow(game.getTopCard());
                    game.playerPlays(throwingCard);
                    if (throwingCard != null) {
                        if (throwingCard.getRank().equals(Rank.ACE)) {
                            Suit suit = ((ComputerPlayer) player).changeSuit();
                            game.setSuit(suit);
                        }
                        System.out.println(" Ο " + player.name + " έδωσε " + throwingCard + " και έχει " + player.nCards() + " κάρτες.");
                    } else {
                        if (game.hasPlayerDrawCard()) {
                            System.out.println(" Ο " + player.name + " τράβηξε κάρτα, έχει " + player.nCards() + " κάρτες.");
                        } else {
                            System.out.println(" Ο " + player.name +" πάει πάσο, έχει " + player.nCards() + " κάρτες.");
                        }
                    }

                }
            }//end for each game
            showInfo();
        }//end of all games
    }//end start
}//end of CLI class