import org.junit.Test

/**
 * Created by Manmeet on 31-May-17.
 */
class GameTest {
    @Test
    fun kingInFirstFoundationPilr(){
        // arrange
        var numGames =0
        var maxGames= 10000

        // act
        for (i in 1..maxGames) {
            numGames++
            GameModel.resetGame()
            for (j in 1..100) {
                GamePresenter.onDeckTap()
                GamePresenter.onWasteTap()
                GameModel.tableauPiles.forEachIndexed { index, tableauPile ->
                    GamePresenter.onTableauTap(index, tableauPile.cards.lastIndex)
                }
            }
            if (GameModel.foundationPiles[0].cards.size == 13){
                break
            }
        }


        // assert
        GameModel.debugPrint()
        println(" # Games: $numGames")
        assert(numGames<maxGames)
    }
}