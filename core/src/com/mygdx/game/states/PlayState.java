package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.FlappyBird;
import com.mygdx.game.sprites.Bird;
import com.mygdx.game.sprites.Tube;

/**
 * Created by Pulkit on 06-Mar-17.
 */

public class PlayState extends State {
    private static final int TUBE_SPACING = 125;
    public static final int TUBE_COUNT= 4;

    private Bird bird;
    private Texture bg;
    //private Tube tube;

    private Array<Tube> tubes;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        bird  = new Bird(50,300);
        cam.setToOrtho(false, FlappyBird.WIDTH/2,FlappyBird.HEIGHT/2);
        bg= new Texture("background.png");
      //  tube = new Tube(100);

        tubes = new Array<Tube>();
        for(int i =0 ;i<=TUBE_COUNT;i++){
            tubes.add(new Tube(i*(TUBE_SPACING+Tube.TUBE_WIDTH)));
        }

    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()){
            bird.jump();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        bird.update(dt);

        cam.position.x=bird.getPosition().x + 80;
        for (Tube tube : tubes){
            if(cam.position.x - (cam.viewportWidth/2) > tube.getPosTopTube().x + tube.getTopTube().getWidth()){
                tube.reposition(tube.getPosTopTube().x + ((Tube.TUBE_WIDTH + TUBE_SPACING) *TUBE_COUNT));
            }

            if (tube.collides(bird.getBounds()))
                gsm.set(new MenuState(gsm));
        }
        cam.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bg,cam.position.x -(cam.viewportWidth/2),0);
        //sb.draw(bg ,0 , 0, FlappyBird.WIDTH , FlappyBird.HEIGHT );
        sb.draw(bird.getBird(),bird.getPosition().x,bird.getPosition().y);
        for (Tube tube : tubes) {
            sb.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
            sb.draw(tube.getBottomTube(), tube.getPosBotTube().x, tube.getPosBotTube().y);
        }
        sb.end();
    }

    @Override
    public void dispose() {
        bg.dispose();
        bird.dispose();
        for (Tube tube : tubes){
            tube.dispose();
        }
        System.out.println("PLAY STATE DISPOSED");
    }
}
