package com.mygdx.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * Created by Pulkit on 06-Mar-17.
 */

public class GameStateManager {

    private Stack<State> states;

    public  GameStateManager(){
        states = new Stack<State>();
    }

    public void push (State state){
        states.push(state);
    }

    public void pop(State state){
        states.pop().dispose();
    }

    public void  set(State state){
        states.pop();
        states.push(state);
    }

    public void update(float dt){
        states.peek().update(dt);
    }

    public void render(SpriteBatch sb){
        states.peek().render(sb);
    }


}
