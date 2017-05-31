/**
 * Created by Manmeet on 29-May-17.
 */

// This is the java form of the Card class

public class JavaCard {
    private int value;
    private String suit;
    private boolean faceUp = false;

    public JavaCard(int value, String suit, boolean faceUp ){
        this.value = value;
        this.suit = suit;
        this.faceUp = faceUp;
    }

    public int getValue() {
        return value;
    }

    public String getSuit() {
        return suit;
    }

    public boolean isFaceUp() {
        return faceUp;
    }

    public void setFaceUp(boolean faceUp) {
        this.faceUp = faceUp;
    }
}
