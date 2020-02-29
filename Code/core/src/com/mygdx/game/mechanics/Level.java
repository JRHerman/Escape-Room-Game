package com.mygdx.game.mechanics;// CS 440 - Group 4 Coding Project
// Luqmaan Baiyat, Patrick Gundry, Joshua Herman, Hamza Shahid


import GameLevels.LevelOverlay;
import GameLevels.Room;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.game.EscapeRoomGame;
import com.mygdx.game.RoomObjects.Door;
import com.mygdx.game.RoomObjects.Puzzle;
import com.mygdx.game.RoomObjects.Wall;
import com.mygdx.game.screens.PlayScreen;

/**
 * A room has a static list of rooms, doors, and puzzles. They also have a countdown
 * hints to enable, progression, and clues. A room can return hints, rooms, and the countdown
 */
import java.util.ArrayList;

public abstract class Level {

    protected String levelName;

    private static ArrayList<Level> listOfLevels = new ArrayList<Level>(0);

    //private ArrayList<Door> roomDoors = new ArrayList<Door>(0);
    //private ArrayList<Puzzle> roomPuzzles = new ArrayList<Puzzle>(0);

    protected float countdown;
    protected LevelOverlay levelOverlay;
    //protected int overlayWidth = PlayScreen.gameOverlayWidth;
    protected boolean hintsEnabled;

    protected ArrayList<Wall> levelWalls;
    protected ArrayList<Room> levelRooms;
    protected ArrayList<Door> levelDoors;


    // TODO - update this
    protected Texture overlayTexture;

    protected Progression gameProgression;
    protected ClueSet roomClues;

    private String currentHint;
    protected boolean movementEnabled = true;

    public Level() {
        setLevelName();
        listOfLevels.add(this);
        hintsEnabled = false;
        levelOverlay = new LevelOverlay();
        gameProgression = new Progression();
        roomClues = new ClueSet();
        addHints();
        setCountdown();
        addWalls();
        addDoors();
        addPuzzles();
        defineRooms();
    }


    protected abstract void setLevelName();

    // set the game timer here for the instance of level
    protected abstract void setCountdown();

    // add hints for the instance of Level
    protected abstract void addHints();

    // function where walls should be defined and added
    protected abstract void addWalls();

    // define the room coordinates for individual rooms;
    protected abstract void defineRooms();

    // add the doors for the level
    protected abstract void addDoors();

    // add the puzzles for the room
    protected abstract void addPuzzles();

    // put game logic and drawing procedures for instance of Level
    public abstract void drawElements(EscapeRoomGame game, float delta);

    // this function will decrease the countdown timer
    protected void updateCountdown(float delta) { countdown -= delta; }



    // returns the list of level names, used with interface showing the levels
    public static ArrayList<String> getLevelNames() {
        ArrayList<String> nameList = new ArrayList<String>(0);
        for (Level l : listOfLevels) {
            nameList.add(l.levelName);
        }

        return nameList;
    }

    public abstract void resetLevel();

    public static int numberOfLevels() { return listOfLevels.size();}

    public void setCurrentHint(String hint) {currentHint = hint;}
    protected String getCurrentHint() {return currentHint;}

    protected void drawWalls(Batch gameBatch) {
        for (Wall w: levelWalls)
            w.draw(gameBatch);
    }

    protected void drawRoomCovers(Batch gameBatch) {
        for (Room r: levelRooms)
            if (r.covered())
                r.drawCover(gameBatch);
    }

    protected void drawDoors(Batch gameBatch) {

        for (Door d: levelDoors)
            d.draw(gameBatch);
    }

    protected boolean detectCollision(Player player, Player.MoveDirection moveDirection) {
        for (Wall w : levelWalls) {
            switch (moveDirection) {
                case UP: {
                    if (w.topCollision(player))
                        return true;
                    break;
                }

                case DOWN: {
                    if (w.bottomCollision(player))
                        return true;
                    break;
                }

                case RIGHT: {
                    if (w.rightCollision(player))
                        return true;
                    break;
                }

                case LEFT: {
                    if (w.leftCollision(player))
                        return true;
                    break;
                }

                // Should never be default
                default: {
                    break;
                }
            }
        }

        for (Door d : levelDoors) {
            switch (moveDirection) {
                case UP: {
                    if (d.topCollision(player))
                        return true;
                    break;
                }

                case DOWN: {
                    if (d.bottomCollision(player))
                        return true;
                    break;
                }

                case RIGHT: {
                    if (d.rightCollision(player))
                        return true;
                    break;
                }

                case LEFT: {
                    if (d.leftCollision(player))
                        return true;
                    break;
                }

                // Should never be default
                default: {
                    break;
                }
            }
        }
        // false if no collisions are detected
        return false;
    }

    public void toggleHints() {
        hintsEnabled = !hintsEnabled;
    }

}
