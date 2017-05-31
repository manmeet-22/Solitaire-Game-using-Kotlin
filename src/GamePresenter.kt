/**
 * Created by Manmeet on 31-May-17.
 */
// GamePresenter is a singleton, as we will only have one presenter
object GamePresenter {

    var view : GameView? = null // In kotlin if we want a variable to be null we add '?' at the end of datatype name to make it null

    fun setGameView(gameView: GameView){
        view=gameView
    }

    fun onDeckTap(){
        GameModel.onDeckTap() // here in this line onDeckTap belongs  to GameModel singleton
        // If we have declared view as null so there can be a chance that we are sending null view in gamemodel so to deal with it we add '?'
        // So if the view is not null the below statement will implement and vise versa
     /*  view?.update(GameModel)*/
        // as we have done "model: GameModel = GameModel" in GameView class, so no need of passing GameModel again and again
        view?.update()

       /*
       // Another way to tackle this problem is to use '!!' , this implies that what ever is before !! is not null
       // If the view is null then it will give null pointer exception
       view?.update(GameModel)*/
    }

    fun onWasteTap(){
        GameModel.onWasteTap()
        view?.update()
    }

    fun onFoundationTap( foundationIndex : Int ){
        GameModel.onFoundationTap(foundationIndex)
        view?.update()
    }

    fun onTableauTap( tableauIndex : Int, cardIndex : Int) {
        GameModel.onTableauTap(tableauIndex,cardIndex)
        view?.update()

    }


}