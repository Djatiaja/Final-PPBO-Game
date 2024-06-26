package Gamestate;

import java.awt.event.MouseEvent;

import Main.Game;
import ui.MenuButton;

import Main.Game;

public class State {
    protected Game game;

    public State(Game game){

        this.game = game;
    }

    public boolean isIn(MouseEvent e, MenuButton mb){
        return mb.getBounds().contains(e.getX(), e.getY());
    }

    public Game getGame(){
        return game;
    }
    public final int dialogueState = 3;

}
