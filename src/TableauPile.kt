/**
 * Created by Manmeet on 31-May-17.
 */
// The Tableau pile contains 7 slots each containg the random no of cards in the starting of the game , with the last card faceup
//  -> We can pickup one card from waste pile everytime and add it in one of the slots
//  -> We can also add one card from foundation pile too
// -> We can add and remove multiple cards at once between the different 8 slots
class TableauPile (var cards : MutableList<Card> = mutableListOf()){
    init {
        // when the game starts
        if( cards.size > 0)
            cards.last().faceUp = true
    }

    fun addCards(newCards : MutableList<Card>): Boolean {
        if( cards.size > 0) {
            if (newCards.first().value == cards.last().value - 1 && suitCheck(newCards.first(), cards.last())) {
                cards.addAll(newCards)
                return true
            }
        }
        else if (newCards.first().value == 12 ){
            cards.addAll(newCards)
            return true
        }
        return false
    }

    private fun  suitCheck(c1: Card, c2: Card): Boolean {
        if ( ( redSuits.contains(c1.suit) && blackSuits.contains(c2.suit) ) || ( redSuits.contains(c2.suit) && blackSuits.contains(c1.suit) ) ){
            return true
        }
        return false
    }

    fun removeCards ( tappedIndex : Int) {
        for (i in tappedIndex..cards.lastIndex ){
            cards.removeAt(tappedIndex)
        }
        if ( cards.size > 0){
            cards.last().faceUp = true
        }

    }


}