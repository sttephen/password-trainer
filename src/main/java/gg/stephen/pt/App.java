package gg.stephen.pt;

import gg.stephen.pt.game.Game;
import gg.stephen.pt.gui.GUI;

import java.io.IOException;

public class App {

    public static void main(String[] args) throws IOException {
        new GUI(new Game());
    }

}