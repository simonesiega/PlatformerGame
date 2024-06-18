package gamestates;

import mainpack.Game;
import ui.MenuButton;

import java.awt.event.MouseEvent;

public class State {

    protected Game _game;

    public State(Game game){
        this._game = game;
    }

    public Game get_game() {
        return _game;
    }

    public boolean isMouseIn(MouseEvent e, MenuButton mb){
        return mb.get_hitBoxButton().contains(e.getX(), e.getY());
    }
}
