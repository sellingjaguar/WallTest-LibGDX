package com.walcollisiontest.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.Random;

public class Player {

    private int size = 20;
    private int speed = 400;
    private float posX;
    private float posY;

    private int dirX = 0;
    private int dirY = 0;

    private int[][] walls;

    public Player(int x, int y, int[][] walls){

        this.posX = x;
        this.posY = y;

        this.walls = walls;

    }

    public void update(){

        //X movement
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) dirX = 1;
        else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) dirX = -1;
        else dirX = 0;

        //Y movement
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) dirY = 1;
        else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) dirY = -1;
        else dirY = 0;

        //check collision
        if (!willCollide()) {
            //apply movement
            posX = posX + (speed * dirX * Gdx.graphics.getDeltaTime());
            posY = posY + (speed * dirY * Gdx.graphics.getDeltaTime());
        }

    }

    private boolean willCollide(){

        int wallSize = 50;

        boolean xCollision;
        boolean yCollision;

        float possibleMovementX = (speed * dirX * Gdx.graphics.getDeltaTime());
        float possibleMovementY = (speed * dirY * Gdx.graphics.getDeltaTime());

        float pR = posX + size + possibleMovementX;
        float pL = posX - size + possibleMovementX;
        float pT = posY + size + possibleMovementY;
        float pB = posY - size + possibleMovementY;

        for (int i = 0; i < walls.length; i++){

            int wallPosX = i * wallSize;

            for (int j = 0; j < walls[0].length; j++){

                int wallPosY = j * wallSize;

                xCollision = false;
                yCollision = false;

                //if it is a wall
                if (walls[i][j] == 1) {

                    float wR = wallPosX + wallSize;
                    float wL = wallPosX;
                    float wT = wallPosY + wallSize;
                    float wB = wallPosY;

                    //check left collision
                    if (pL < wR && pL > wL){
                        xCollision = true;
                    }
                    //check right collision
                    else if (pR > wL && pR < wR){
                        xCollision = true;
                    }

                    //check bot collision
                    if (pB < wT && pB > wB){
                        yCollision = true;
                    }
                    //check top collision
                    else if (pT > wB && pT < wT){
                        yCollision = true;
                    }

                    if (xCollision && yCollision){
                        System.out.println("true");
                        return true;
                    }

                    /*//check left collision
                    if (posX + possibleMovementX < wallPosX + wallSize) {
                        xCollision = true;
                        System.out.println("LX");
                    }
                    //check right collision
                    else if (posX + possibleMovementX + size * 2 > wallPosX && ) {
                        xCollision = true;
                        System.out.println("RX");
                    }

                    //check bot collision
                    if (posY + possibleMovementY < wallPosY + wallSize) {
                        yCollision = true;
                        System.out.println("BY");
                    }
                    //check top collision
                    else if (posY + possibleMovementY + size * 2 > wallPosY) {
                        yCollision = true;
                        System.out.println("TY");
                    }

                    if (xCollision && yCollision) {
                        System.out.println("true -> " + i + " " + j);
                        return true;
                    }*/

                }

            }
        }

        return false;
    }

    public void draw(ShapeRenderer renderer){
        renderer.setColor(Color.BLUE);
        renderer.circle(posX, posY, size);
    }

}
