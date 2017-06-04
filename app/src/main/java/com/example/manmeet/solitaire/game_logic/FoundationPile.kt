package com.example.manmeet.solitaire

/**
 * Created by Manmeet on 31-May-17.
 */
// Foundation pile is the that section in the game which has 4 slots each belonging to 1 of the suits and the cards in one slot is in order A - king
//    -> We can add a card
//    -> we can remove a card
//    -> We can reset to empty pile

// add const with suit as parameter
class FoundationPile( val suit : String) {
    val cards: MutableList<Card> = mutableListOf()

    fun reset(){
        cards.clear()
    }

    fun removeCard( card: Card){
        cards.remove(card)
    }

    fun addCard(card : Card): Boolean {
        var nextValue=0
        if (cards.size > 0){
            nextValue = cards.last().value + 1
        }
        if (card.suit == suit && card.value == nextValue){
            cards.add(card)
            return true
        }
        return false
    }
}