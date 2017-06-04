package com.example.manmeet.solitaire

/**
 * Created by Manmeet on 31-May-17.
 */
// To call the GameModel in GamePresenter which works on Singleton we replace " class GameModel" with "object GameModel" which Makes GameModel a singleton
// The singleton pattern is a software design pattern that restricts the instantiation of a class to one object.
object GameModel {
    val deck = Deck()

    // Wastepile is the that section in the game ,when
    //    -> We tap the cards deck and add a card to waste pile
    //    -> We check the card in waste pile and if it is valid than add it to on of the Tableaus
    //    -> We can tap the card in waste pile and the shuffle the cards once again

    val wastePile: MutableList<Card> = mutableListOf() //list is empty right now

    // Meaning of Foundation Pile is written in Foundation file class

    // as told earlier ,Kotlin knows that it will return and array of FoundationPile we can remove ": Array<FoundationPile>"  from "val foundationPiles: Array<FoundationPile> "
    /*
    // if we use companion obj
    val foundationPiles = arrayOf( FoundationPile(Card.clubs), FoundationPile(Card.diamonds), FoundationPile(Card.hearts), FoundationPile(Card.spades))
    */

    val foundationPiles = arrayOf(FoundationPile(clubs), FoundationPile(diamonds), FoundationPile(hearts), FoundationPile(spades))

    val tableauPiles = Array(7, { TableauPile() })

    fun resetGame() {
        wastePile.clear()
        foundationPiles.forEach({ it.reset() })
        deck.reset()

        tableauPiles.forEachIndexed { i, tableauPile ->
            val cardInPile: MutableList<Card> = Array(i + 1, { deck.drawCard() }).toMutableList()
            tableauPiles[i] = TableauPile(cardInPile)
        }
    }

    fun onDeckTap() {
        if (deck.cardsInDeck.size > 0) {
            val card = deck.drawCard()
            card.faceUp = true
            wastePile.add(card)
        } else {
            deck.cardsInDeck = wastePile.toMutableList()
            wastePile.clear()
        }
    }

    fun onWasteTap() {
        if (wastePile.size > 0) {
            val card = wastePile.last()
            if (playCard(card)) {
                wastePile.remove(card)
            }
        }
    }

    private fun playCard(card: Card): Boolean {
        foundationPiles.forEach {
            if (it.addCard(card)) {
                return true
            }
        }

        tableauPiles.forEach {
            if (it.addCards(mutableListOf(card))) {
                return true
            }
        }
        return false
    }

    fun onFoundationTap(foundationIndex: Int) {
        val foundationPile = foundationPiles[foundationIndex]
        if (foundationPile.cards.size > 0) {
            val card = foundationPile.cards.last()
            if (playCard(card)) {
                foundationPile.removeCard(card)
            }
        }
    }

    fun onTableauTap(tableauIndex: Int, cardIndex: Int) {
        val tableauPile = tableauPiles[tableauIndex]
        if (tableauPile.cards.size > 0) {
            if (tableauPile.cards[cardIndex].faceUp) {
                val cards = tableauPile.cards.subList(cardIndex, tableauPile.cards.lastIndex + 1)
                if (playCards(cards)) {
                    tableauPile.removeCards(cardIndex)
                }
            }
        }
    }

    private fun playCards(cards: MutableList<Card>): Boolean {
        if (cards.size == 1) {
            return playCard(cards.first())
        } else {
            tableauPiles.forEach {
                if (it.addCards(cards)) {
                    return true
                }
            }
        }
        return false

    }

    fun debugPrint() {
        var firstLine = if (wastePile.size > 0) "${wastePile.last()}" else "___"
        firstLine = firstLine.padEnd(18)
        foundationPiles.forEach {
            firstLine += if (it.cards.size > 0) "${it.cards.last()}" else "___"
            firstLine += "   "
        }
        println(firstLine)
        println()

        for (i in 0..12) {
            var row = ""
            tableauPiles.forEach {
                row += if (it.cards.size > i) "${it.cards[i]}" else "   "
                row += "   "
            }
            println(row)
        }
    }
}
