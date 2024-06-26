package ui;

import Entity.NPC;
import Gamestate.Playing;
import Main.Game;

import java.awt.*;
import java.util.ArrayList;

public class DialogWindow {
    private ArrayList<String[]> text;
    private boolean Show = false;
    private int dialogCounter = 0;
    private NPC currentnpc;
    Playing playing;
    Font font1= new Font("TimesRoman", Font.PLAIN, 24);
    Font font2 = new Font("TimesRoman", Font.PLAIN, 20);
    public DialogWindow(Playing playing){
        this.playing = playing;
    }
    public void draw(Graphics g){

        g.setColor(Color.WHITE);
        g.fillRoundRect(Game.GAME_WIDTH/2-(Game.GAME_WIDTH-400)/2-2, Game.GAME_HEIGHT/8 +Game.GAME_HEIGHT/2+30-2, Game.GAME_WIDTH-400+4, Game.GAME_HEIGHT/2-150+4, 35, 35);

        Color color = new Color(0,0,0,200);
        g.setColor(color);
        g.fillRoundRect(Game.GAME_WIDTH/2-(Game.GAME_WIDTH-400)/2, Game.GAME_HEIGHT/8 +Game.GAME_HEIGHT/2+30, Game.GAME_WIDTH-400, Game.GAME_HEIGHT/2-150, 35, 35);


        g.setColor(Color.WHITE);
        g.setFont(font1);
        g.drawString(text.get(dialogCounter)[1], Game.GAME_WIDTH/2-(Game.GAME_WIDTH-400)/2+20+100, Game.GAME_HEIGHT/8 +Game.GAME_HEIGHT/2+50);
        g.setFont(font2);
        int yoffset = 0;
        int xoffset= 0;
        for (String line: text.get(dialogCounter)[0].split("\n")){
            g.drawString(line, Game.GAME_WIDTH/2-(Game.GAME_WIDTH-400)/2+20+100-xoffset, Game.GAME_HEIGHT/8 +Game.GAME_HEIGHT/2+50 +40+yoffset);
            yoffset+=20;
            if (xoffset==0){
                xoffset = 8;
            }


        }
        if (text.get(dialogCounter)[1]!="Pemain"){
            g.drawImage(this.currentnpc.getNpcImage(), Game.GAME_WIDTH/2-(Game.GAME_WIDTH-400)/2-100, Game.GAME_HEIGHT/8 +Game.GAME_HEIGHT/2+30, Game.GAME_HEIGHT/2-150+4, Game.GAME_HEIGHT/2-150, null);
        }


    }

    public void setText(ArrayList<String[]> text){
        this.text = text;
    }



    public void setShow(boolean show){
        this.Show = show;
    }

    public void update(){

    }

    public void DialogCounter(){
        dialogCounter++;
        if (dialogCounter >= text.size()){
            dialogCounter = 0;
            playing.setShow(false);
            playing.getcurrentNPC().setDialogShow(false);
        }
    }


    public void setNPC(NPC npc){
        this.currentnpc = npc;
    }
}
