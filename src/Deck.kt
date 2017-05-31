import org.omg.CORBA.INTERNAL
import java.util.*

/**
 * Created by Manmeet on 31-May-17.
 */
/*
1)
class Deck {
    // In Kotlin we don't need new key word like ->  new Card(0,"Clubs")


    val cards: Array<Card> = arrayOf( Card(0,"Clubs"), Card(1,"Clubs")......)

}
*/


// The above method is very lengthy as we need to declare all the 52 cards


/*
2)
class Deck {
// There is an easy way by making instance of array class

    //below fun indexToCard (i: Int) : """Card"""  Card is the return type of function
    fun indexToCard (i: Int) : Card{

        val value = i % 13
        val suit = when(i / 13){
            0 -> "Clubs"
            1 -> "Diamonds"
            2 -> "Hearts"
            else -> "Spades"
        }
        return Card(value,suit)
    }
    val cards: Array<Card> = Array(52, )

}*/


/*
3)
class Deck {
// As we cannot call the indexToCard function in array instance directly
// We need to make function anonymous by removing its name and intializing it ina a variable

    val fn = fun (i: Int) : Card{

        val value = i % 13
        val suit = when(i / 13){
            0 -> "Clubs"
            1 -> "Diamonds"
            2 -> "Hearts"
            else -> "Spades"
        }
        return Card(value,suit)
    }
    val cards: Array<Card> = Array(52, fn)

}
*/

// Or even better add the whole function as 2nd parameter

/*
4)
class Deck {
// As we cannot call the indexToCard function in array instance directly
// We need to make function anonymous by removing its name and intializing it ina a variable

    val cards: Array<Card> = Array(52, fun(i: Int): Card {

        val value = i % 13
        val suit = when (i / 13) {
            0 -> "Clubs"
            1 -> "Diamonds"
            2 -> "Hearts"
            else -> "Spades"
        }
        return Card(value, suit)
    })

}
*/

//more condensing
/*
5)
class Deck {
// As we cannot call the indexToCard function in array instance directly
// We need to make function anonymous by removing its name and intializing it ina a variable

    val cards: Array<Card> = Array(52,
            fun(i: Int): Card {

                //passing 'value' as i%13 and then pass 'i' for suit name
        return Card(i % 13, getSuit(i))
    })

    // Making it single line using '=', hence no return type req
    private fun  getSuit(i: Int): String = when (i / 13) {
        0 -> "Clubs"
        1 -> "Diamonds"
        2 -> "Hearts"
        else -> "Spades"
    }
}*/

//Even more condensing by converting anonymous function into a lambda expr
/*
6)
class Deck {

   /* val cards: Array<Card> = Array(52, { i-> Card(i % 13, getSuit(i))} )*/

    // As there is only one parameter in lambda expr we can omit the parameter section entirely
    // Just by deleting 'i' and we can refer to it by using 'it'

    // PS-  we can also delete ": Array<Card>" from "val cards: Array<Card> " as kotlin knows that 'cards' is an Array of Csrd ,but it can cause confusion so we can rather stick to it

    val cards: Array<Card> = Array(52, {Card(it % 13, getSuit(it))} )

    // Similarly we can omit the return type of getSuit() ie ": String " from " private fun  getSuit(i: Int): String = when (i / 13)"
    // But if I delete it , Kotlin thinks that function can return any datatype aka "Any" and if I make any of the value return any other data type then String say Int in "else -> 100" it will show error of returning Any

    private fun  getSuit(i: Int): String = when (i / 13) {
        0 -> "Clubs"
        1 -> "Diamonds"
        2 -> "Hearts"
        else -> "Spades"
    }
}*/

// As the length of array is fixed it , we need to copy the array of cards in a list


class Deck {

    val cards: Array<Card> = Array(52, {Card(it % 13, getSuit(it))} )
    var cardsInDeck : MutableList<Card> = cards.toMutableList()

    // Every time we draw a card the card at that index ie at 0 is removed from list
    fun drawCard(): Card = cardsInDeck.removeAt(0)

    // When we want to restart the game
    fun reset(){
        cardsInDeck = cards.toMutableList()
        cardsInDeck.forEach{it.faceUp = false}
        // To shuffle the deck we can use java funtion of collections ie shuffle
        Collections.shuffle(cardsInDeck)
    }
    private fun  getSuit(i: Int): String = when (i / 13) {
        /*
        // we can't use the clubs , diamonds etc directly , as for companion obj name always atart with class name
        0 -> Card.clubs
        1 -> Card.diamonds
        2 -> Card.hearts
        else -> Card.spades
        */
        
        0 -> clubs
        1 -> diamonds
        2 -> hearts
        else -> spades
    }
}