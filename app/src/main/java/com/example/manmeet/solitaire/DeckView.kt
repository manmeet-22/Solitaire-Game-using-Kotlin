package com.example.manmeet.solitaire

import android.content.Context
import android.view.ViewManager
import android.widget.ImageView
import org.jetbrains.anko.custom.ankoView
import org.jetbrains.anko.imageResource
import org.jetbrains.anko.onClick

/**
 * Created by Manmeet on 04-Jun-17.
 */
class DeckView(context: Context) : ImageView(context) {
    init {
        imageResource = cardBackDrawable
        onClick {
            GamePresenter.onDeckTap()

        }
    }
     fun update() {
         val cards = GameModel.deck.cardsInDeck
         imageResource = if(cards.size > 0) cardBackDrawable else emptyPileDrawable

     }

}

// as the above DeckView is refering to nothing and to show the DeckView on the UI we need it to call anko view
// creating DeckView constant
/*

val DECKVIEW_FACTORY = { ctx: Context -> DeckView(ctx)}
// creating custom method to call ankoView
fun ViewManager.deckView(init :DeckView.() -> Unit = {}) = ankoView(DECKVIEW_FACTORY, 0 , init)*/

// shorter way
fun ViewManager.deckView(init :DeckView.() -> Unit = {}) = ankoView({DeckView(it)}, 0 , init)