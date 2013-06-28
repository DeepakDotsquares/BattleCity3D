/*
* GameLoop.java
*
* Created on 26 06 2013, 15:16
*
* Copyright (C) 2013 Alexey 'lh' Antonov
*
* For more info read COPYRIGHT file in project root directory.
*
*/

package com.lifchicker.battlecity3d.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.lifchicker.battlecity3d.render.Renderer;
import com.lifchicker.battlecity3d.simulation.Simulation;

/**
 * @author Alexey 'lh' Antonov
 * @since 2013-06-26
 */
public class GameLoop extends BattleCity3DScreen {

    private final Simulation simulation;
    private final Renderer renderer;

    private boolean isDone = false;

    public GameLoop() {
        simulation = new Simulation();
        renderer = new Renderer();
    }

    @Override
    public void dispose() {
        renderer.dispose();
        simulation.dispose();
    }

    @Override
    public void update(float delta) {
        simulation.update(delta);

        if (Gdx.input.isKeyPressed(Input.Keys.Q))
            isDone = true;

        Vector2 moveDirection = new Vector2(0.0f, 0.0f);
        boolean moveXKeyPressed = false;
        boolean moveYKeyPressed = false;

        if (Gdx.input.isKeyPressed(Input.Keys.DPAD_UP) || Gdx.input.isKeyPressed(Input.Keys.W)) {
            moveDirection.add(0.0f, 0.5f);
            moveXKeyPressed = !moveXKeyPressed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DPAD_DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) {
            moveDirection.add(0.0f, -0.5f);
            moveXKeyPressed = !moveXKeyPressed;
        }

        if (!moveXKeyPressed && (Gdx.input.isKeyPressed(Input.Keys.DPAD_LEFT) || Gdx.input.isKeyPressed(Input.Keys.A))) {
            moveDirection.add(0.5f, 0.0f);
            moveYKeyPressed = !moveYKeyPressed;
        }
        if (!moveXKeyPressed && (Gdx.input.isKeyPressed(Input.Keys.DPAD_RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D))) {
            moveDirection.add(-0.5f, 0.0f);
            moveYKeyPressed = !moveYKeyPressed;
        }

        if (moveXKeyPressed || moveYKeyPressed)
            simulation.moveTank(delta, moveDirection);
    }

    @Override
    public void draw(float delta) {
        renderer.render(simulation, delta);
    }

    @Override
    public boolean isDone() {
        return isDone;
    }
}