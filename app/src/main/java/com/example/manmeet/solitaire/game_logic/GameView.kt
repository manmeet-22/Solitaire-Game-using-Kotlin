package com.example.manmeet.solitaire

/**
 * Created by Manmeet on 31-May-17.
 */
//This class deals with user interface ie whenever there is change in Gamemodel this class will be notified

interface GameView {
    fun update() // by doing " = GameModel" we will not require to pass in GameModel everytime we update it in GamePresenter class
}