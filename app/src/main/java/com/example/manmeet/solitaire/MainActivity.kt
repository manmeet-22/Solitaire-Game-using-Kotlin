package com.example.manmeet.solitaire

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import org.jetbrains.anko.*

val cardBackDrawable = R.drawable.cardback_red5
val emptyPileDrawable = R.drawable.cardback_blue1
fun View.getResIdForCard(card: Card): Int {
    val resourceName = "card${card.suit}${cardMap[card.value]}".toLowerCase()
    return context.resources.getIdentifier(resourceName, "drawable", context.packageName)
}
val Context.cardWidth: Int
        get() = (displayMetrics.widthPixels - dip(8)) / 7
val Context.cardHeight: Int
        get() = cardWidth * 190 / 140

class MainActivity : AppCompatActivity(), GameView {
    var deckView: DeckView? = null //property
    var wastePileView: WastePileView? = null //property
    val foundationPileViews: Array<FoundationPileView?> = arrayOfNulls(4)
    val tableauPileViews: Array<TableauPileView?> = arrayOfNulls(7)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        GamePresenter.setGameView(this)
        GameModel.resetGame()

        verticalLayout {
            leftPadding = dip(4)
            rightPadding = dip(4)
            topPadding = dip(4)
            linearLayout {
                /*imageView {
                    imageResource = R.drawable.cardback_red5
                }.lparams{
                    width = cardWidth
                    height = cardHeight
                }*/
                // There is a better way and shorter way then above
                deckView = deckView().lparams(cardWidth,cardHeight)  //for the deck pile setting deckview property equl to deckView in DeckView class
                wastePileView = wastePileView().lparams(cardWidth, cardHeight)//for the waste pile
                view().lparams(cardWidth, 0) //This view refers to the emty space of card between wastepile and foundation pile
                for (i in 0..3) {
                    foundationPileViews[i] = foundationPileView(i).lparams(cardWidth, cardHeight) //for foundation pile
                }
            }
            linearLayout {
                for (i in 0..6) {
                    tableauPileViews[i] = tableauPileViews(i).lparams(cardWidth, matchParent)
                }
            }. lparams(height = matchParent){
                topMargin = cardHeight / 2
            }
        }
    }

    override fun update() {
        deckView!! .update()
        wastePileView!!.update()
        foundationPileViews.forEach{
            it!!.update()
        }
        tableauPileViews.forEach{
            it!!.update()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
      menu.add("New Game")
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        GameModel.resetGame()
        update()
        return true
    }
}
