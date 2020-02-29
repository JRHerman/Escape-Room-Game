package GameLevels;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.game.mechanics.Player;

public class Room {

    private int xStart, xEnd, yStart, yEnd;
    private boolean isCovered;
    private Texture roomCoverTexture = new Texture("roomcover.png");

    public Room(int xStart, int xEnd, int yStart, int yEnd  ) {
        this.xStart = xStart;
        this.xEnd = xEnd;
        this.yStart = yStart;
        this.yEnd = yEnd;
        isCovered = true;
    }

    public void uncoverRoom() {isCovered = false;}

    public boolean covered() { return isCovered; }

    public void drawCover(Batch gameBatch) {
        gameBatch.draw(roomCoverTexture, xStart, yStart, xEnd - xStart, yEnd - yStart);
    }

    // returns true if the player is in the bounds of the room
    public boolean playerInRoom(Player player) {

        return (player.getxPos() >= xStart && player.getxPos() <= xEnd
            && player.getyPos() >= yStart && player.getyPos() <= yEnd);
    }


}
