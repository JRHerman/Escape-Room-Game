package GameLevels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.mygdx.game.EscapeRoomGame;
import com.mygdx.game.mechanics.Level;
import com.mygdx.game.mechanics.Player;

public class Level4 extends Level {

    private Player player1;
    private Player player2;

    public Level4() {
        super();
        player1 = new Player(40, 40);
        player2 = new Player(300, 300);
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
        player1.draw(game.batch);
        player2.draw(game.batch);


        // player 1 movement
        if (Gdx.input.isKeyPressed(Input.Keys.W))  {
                player1.setyPos(player1.getyPos() + 5);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            player1.setyPos(player1.getyPos() - 5);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            player1.setxPos(player1.getxPos() - 5);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            player1.setxPos(player1.getxPos() + 5);
        }

        // player 2 movement
        if (Gdx.input.isKeyPressed(Input.Keys.UP))  {
            player2.setyPos(player2.getyPos() + 5);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            player2.setyPos(player2.getyPos() - 5);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            player2.setxPos(player2.getxPos() - 5);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            player2.setxPos(player2.getxPos() + 5);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.P)) {
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
