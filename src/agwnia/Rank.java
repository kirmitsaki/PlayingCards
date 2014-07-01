/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agwnia;

/**
 *
 * @author chryski
 */
public enum Rank {
    ACE(11), KING(10), QUEEN(10), JACK(10), TEN(10), NINE(9), EIGHT(8), SEVEN(7), SIX(6), FIVE(5), FOUR(4), THREE(3), TWO(2);
   /* ACE(11), TWO(2), THREE(3), FOUR(4), FIVE(5),
    SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10),
    JACK(10), QUEEN(10), KING(10);
   */
    private int points;

    Rank(int x) {
        points = x;
    }

    public int getPoints() {
        return points;
    }
}
