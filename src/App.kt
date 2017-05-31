/**
 * Created by Manmeet on 29-May-17.
 */
fun main(args: Array<String>) {
    GameModel.resetGame()
    GamePresenter.onDeckTap()
    GameModel.debugPrint()
}