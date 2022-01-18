package com.walcollisiontest.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Random;

public class WallCollisionTest extends ApplicationAdapter {

	//render
	private SpriteBatch batch;
	private ShapeRenderer shapeRenderer;

	//grid
	private int gridSpace;
	private int[][] gridMatrix;

	//player
	private Player player;

	public void create () {

		//render
		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();

		//camera
		OrthographicCamera camera = new OrthographicCamera();
		camera.setToOrtho(false, 1200, 700);

		//grid
		gridSpace = 50;

		int xSpaces = (int)Math.ceil(1200 / gridSpace);
		int ySpaces = (int)Math.ceil(700 / gridSpace);

		gridMatrix = new int[xSpaces][ySpaces];

		for (int i = 0; i < gridMatrix.length; i++){
			for (int j = 0; j < gridMatrix[0].length; j++){

				//clear center of walls
				if (i > 9 && i < 15 && j > 4 && j < 10){
					gridMatrix[i][j] = 0;
				}
				else{
					gridMatrix[i][j] = new Random().nextInt(2);
				}
			}
		}

		//player
		player = new Player(600, 350, gridMatrix);

	}

	public void render () {

		ScreenUtils.clear(1, 0, 0, 1);

		player.update();

		//render grid boxes
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

		//loop trough grid matrix
		for (int i = 0; i < gridMatrix.length; i++){
			for (int j = 0; j < gridMatrix[0].length; j++){

				//if grid value is 0 change to black (background is already black but added it for easy reading purposes)
				if (gridMatrix[i][j] == 0){
					shapeRenderer.setColor(Color.BLACK);
				}

				//if grid value is 1 change to white
				else if (gridMatrix[i][j] == 1){
					shapeRenderer.setColor(Color.WHITE);
				}

				shapeRenderer.rect(gridSpace * i, gridSpace * j, gridSpace, gridSpace);

			}
		}

		player.draw(shapeRenderer);

		shapeRenderer.end();

	}

	public void dispose () {
		batch.dispose();
	}
}
