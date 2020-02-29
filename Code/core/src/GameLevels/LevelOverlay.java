package GameLevels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.game.button.GameButton;
import com.mygdx.game.mechanics.Level;

public class LevelOverlay {

    private int xStart;
    private int yStart;
    private final int overlayWidth = Gdx.graphics.getWidth() - 700;

    private Texture texture = new Texture("overlay_texture.png");
    private GameButton toggleHintButton;


    public LevelOverlay() {
        toggleHintButton = new GameButton("togglehint_button.png", 715, 40);
    }

    public void draw(Batch gameBatch, Level level) {
        gameBatch.draw(texture, Gdx.graphics.getWidth() - overlayWidth, 0, overlayWidth, Gdx.graphics.getHeight());
        gameBatch.draw(toggleHintButton, toggleHintButton.getStartingX(), toggleHintButton.getStartingY());

        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && toggleHintButton.checkMouseOnButton()) {
            level.toggleHints();
        }
    }

    public int getOverlayWidth() { return overlayWidth;}

}
