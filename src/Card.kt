/**
 * Created by Manmeet on 29-May-17.
 */
/*
1)
class Card( value : Int, suit : String, faceUp : Boolean )  //declaring constructor along with the declaration of class (Part 1 of constructor)
{
    //initialising value of variables with the values received in const
    //val means immutable ie constant value
    //var means mutable ie variable
    val value : Int = value
    val suit : String = suit
    var faceUp : Boolean = faceUp

    init {
        //its the constructor's 2nd part
    }
}
*/

// If we want to upgrade the const to a property ,simply add the val or var in front of variables
// Thus no need of intialising values

/*
class Card( val value : Int, val suit : String, var faceUp : Boolean )
*/



// The scope of these vals are throughout the package ie like a global variable in c
val clubs = "Clubs"
val diamonds = "Diamonds"
val hearts = "Hearts"
val spades = "Spade"
val redSuits = arrayOf(diamonds,hearts)
val blackSuits =  arrayOf(spades,clubs)
val cardMap = mapOf(0 to "A", 1 to "2", 2 to "3" , 3 to "4", 4 to "5", 5 to "6", 6 to "7", 7 to "8", 8 to "9", 9 to "10", 10 to "J", 11 to "Q", 12 to "K")
// If we want to add default constructor then we can simply add value like below
// As we already know that the card is always face down in the start so simply make faceup false

data class Card( val value : Int, val suit : String, var faceUp : Boolean = false){
  /*  // as there is no static in Kotlin , we can use companion objects which work similar to static in java
    // Each class can only have one companion object
    companion object {
        val clubs = "Clubs"
        val diamonds = "Diamonds"
        val hearts = "Hearts"
        val spades = "Spade"
        // Now we can easily use the direct names rather  than typing the value again and again
    }
*/

    override fun toString(): String = if(faceUp) "${cardMap[value]}".padEnd(2) + "${getSuitChar(suit)}" else "xxx" // Making it ternary operator

    // There is a symbol for all the suits in unicode, so to access it
    private fun  getSuitChar(suit: String): String = when(suit){
        // "\u26.." refers to the unicode
        diamonds -> "\u2666"
        clubs -> "\u2663"
        hearts -> "\u2665"
        spades -> "\u2660"
        else -> "incorrect suit"
    }
}
