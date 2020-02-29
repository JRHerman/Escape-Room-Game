package GameLevels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.mygdx.game.EscapeRoomGame;
import com.mygdx.game.RoomObjects.Wall;
import com.mygdx.game.mechanics.Level;

import java.util.ArrayList;

public class Level3 extends Level {

    public Level3() {
        super();
    }

    @Override
    protected void setLevelName() {

    }

    @Override
    protected void setCountdown() {

    }

    @Override
    protected void addHints() {

    }

    @Override
    protected void addWalls() {
        levelWalls = new ArrayList<Wall>(0);

        // right wall
        levelWalls.add(new Wall(Wall.WallType.VERTICAL, Gdx.graphics.getWidth() - levelOverlay.getOverlayWidth() - 30, 30,
                0, Gdx.graphics.getHeight())
        );

        // left wall
        levelWalls.add(new Wall(Wall.WallType.VERTICAL, 0, 30, 0, Gdx.graphics.getHeight()));
        // bottom wall
        levelWalls.add(new Wall(Wall.WallType.HORIZONTAL, 0, Gdx.graphics.getWidth() -
                levelOverlay.getOverlayWidth(), 0, 30));
        // top wall
        levelWalls.add(new Wall(Wall.WallType.HORIZONTAL, 0,
                Gdx.graphics.getWidth() - levelOverlay.getOverlayWidth(), Gdx.graphics.getHeight() - 30, 30));


        levelWalls.add(new Wall(Wall.WallType.VERTICAL, 150, 10, 30, Gdx.graphics.getHeight() - 60));
        levelWalls.add(new Wall(Wall.WallType.VERTICAL, 300, 10, 30, Gdx.graphics.getHeight() - 60));
        levelWalls.add(new Wall(Wall.WallType.VERTICAL, 500, 10, 30, Gdx.graphics.getHeight() - 60));


    }

    @Override
    protected void defineRooms() {

    }

    @Override
    protected void addDoors() {

    }

    @Override
    protected void addPuzzles() {

    }

    @Override
    public void drawElements(EscapeRoomGame game, float delta) {

        Gdx.gl.glClearColor(160/255f,160/255f,160/255f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();

        drawWalls(game.batch);
        levelOverlay.draw(game.batch, this);

        if (Gdx.input.isKeyPressed(Input.Keys.P) && movementEnabled) {
            // game state is set to paused
            game.setGameState(EscapeRoomGame.GameState.PAUSED);
            game.setScreen(game.getPauseScreen());
        }

        game.batch.end();
    }

    @Override
    public void resetLevel() {

    }
}
