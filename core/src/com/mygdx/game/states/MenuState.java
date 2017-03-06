package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Gdx2DPixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.FlappyBird;

/**
 * Created by Pulkit on 06-Mar-17.
 */

public class MenuState extends State {

    private Texture background;
    private Texture play;
    public MenuState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, FlappyBird.WIDTH/2,FlappyBird.HEIGHT/2);
        background = new Texture("background.png");
        play = new Texture("play.png");
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm));
//            dispose();

        }

    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background ,0 , 0 );
        sb.draw(play,cam.position.x-play.getWidth()/2,cam.position.y);
        sb.end();
    }

    @Override
    public void dispose() {
       background.dispose();
        play.dispose();
        System.out.println("MENU STATE DISPOSED");
    }
}
