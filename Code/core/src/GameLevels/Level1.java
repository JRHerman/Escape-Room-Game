package GameLevels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.mygdx.game.EscapeRoomGame;
import com.mygdx.game.RoomObjects.*;
import com.mygdx.game.button.GameButton;
import com.mygdx.game.mechanics.Level;
import com.mygdx.game.mechanics.Player;
import com.mygdx.game.screens.LevelSelectionScreen;

import java.util.ArrayList;
// There should only be one instance of this room

/**
 * Room 1 will contain 3 puzzles for the user to solve and 3 "rooms" within this room
 * It will also have keys the user will get when they solve the puzzles and
 * doors for the user to use their keys on. User moves with WASD and interacts
 * with objects with the space bar. There will be hints for the user when they get stuck
 * and there is fully implemented wall collision. The puzzles consist of guessing a random number,
 * step tile sequence puzzle, and lever puzzle.
 */
public class Level1 extends Level {

    private Player player;
    private Texture leftWall;
    private Texture rightWall;
    private Texture bottomWall;
    private Texture roomWall;
    private final int wallThickness = 30;

    /** The doors in the level that will initiallty be locked.
     * The player has to cross Door3 to finish the level
     */
    private Door door1;
    private Door door2;
    private Door door3;

    private LockKey door1Key;
    private LockKey door2Key;
    private LockKey door3Key;

    private Drawer room1Drawer;

    private Texture playerTexture;


    private int playerXPos;
    private int playerYPos;
    private final int playerMovementConstant = 5;

    private FloorTilePuzzle floorTilePuzzle;
    private LeverPuzzle leverPuzzle;

    private GameButton toggleHintButton;

    private BitmapFont timerFont;
    private BitmapFont font;

    private float inputDelay;

    public Level1() {
        super();    // calls Level constructor which will add this room to the static list of Rooms
        inputDelay = 1;

        player = new Player(550, 60);

        leftWall = new Texture("wall.png");
        rightWall = new Texture("wall.png");
        bottomWall = new Texture("wall.png");

        roomWall = new Texture("wall.png");

        playerTexture = new Texture("player_symbol.png");
        playerXPos = 550;
        playerYPos = 60;

        timerFont = new BitmapFont();
        timerFont.setColor(Color.LIGHT_GRAY);


        font = new BitmapFont();
        font.setColor(Color.WHITE);

        toggleHintButton = new GameButton("togglehint_button.png", 715, 300);

        door1Key = new LockKey();
        door1Key.setLock(door1.getLock());
        door1.getLock().setKey(door1Key);

        door2Key = new LockKey();
        door2Key.setLock(door2.getLock());
        door2.getLock().setKey(door2Key);

        door3Key = new LockKey();
        door3Key.setLock(door3.getLock());
        door3.getLock().setKey(door3Key);

        // puts the door1 key inside the room1 drawer, where the player should find it.
        room1Drawer = new Drawer(door1Key);

        // sets up the floor tile puzzle
        floorTilePuzzle = new FloorTilePuzzle(door2Key);
        floorTilePuzzle.addTile(100, 200, 50, 50, 0);
        floorTilePuzzle.addTile(100, 300, 50, 50, 1);
        floorTilePuzzle.addTile(200, 200, 50, 50, 2);
        floorTilePuzzle.addTile(200, 300, 50, 50, 3);
        floorTilePuzzle.addTile(300, 200, 50, 50, 4);
        floorTilePuzzle.addTile(300, 300, 50, 50, 5);
        floorTilePuzzle.initializeSequence();

        leverPuzzle = new LeverPuzzle(door3Key);
        leverPuzzle.addLever(80, 555, 40, 20, 0);
        leverPuzzle.addLever(140, 555, 40, 20, 1);
        leverPuzzle.addLever(200, 555, 40, 20, 2);
        leverPuzzle.initialize();

        // starting step number
        gameProgression.setStepNumber(1);
    }


    @Override
    protected void setLevelName() {
        levelName = "Level 1";
    }

    @Override
    protected void setCountdown() {
        countdown = 300;
    }
    /**
     * Draw the elements of the room and consists of the game functionality
     * Deals with solving puzzles, enabling or disabling hints, confirming keys
     * from users and more.
     * @param game Passes in the EscapeRoomGame
     * @param delta:float the number of time the user has to solve the room goes down by delta
     */
    @Override
    public void drawElements(EscapeRoomGame game, float delta) {

        updateCountdown(delta);

        if (inputDelay < 1) {
            inputDelay -= delta;
            if (inputDelay <= 0)
                inputDelay = 1;

        }


        Gdx.gl.glClearColor(160/255f,160/255f,160/255f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();


        //game draws the player
        game.batch.draw(playerTexture, playerXPos, playerYPos);

        game.batch.draw(leftWall, 0, 30, wallThickness, Gdx.graphics.getHeight());
        game.batch.draw(rightWall, Gdx.graphics.getWidth() - wallThickness - levelOverlay.getOverlayWidth(), 0, wallThickness, Gdx.graphics.getHeight());
        game.batch.draw(bottomWall, 0, 0, Gdx.graphics.getWidth() - 30 - levelOverlay.getOverlayWidth(), wallThickness);

        floorTilePuzzle.drawTiles(game.batch);
        leverPuzzle.drawLevers(game.batch);

        // draw overlay on the right side
        //game.batch.draw(overlayTexture, Gdx.graphics.getWidth() - levelOverlay.getOverlayWidth(), 0, levelOverlay.getOverlayWidth(), Gdx.graphics.getHeight());
        game.batch.draw(toggleHintButton, toggleHintButton.getStartingX(), toggleHintButton.getStartingY());

        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && toggleHintButton.checkMouseOnButton()) {
            hintsEnabled = !hintsEnabled;
            try {
                Thread.sleep(150);
            } catch (InterruptedException ie) {

            }

        }


        if (hintsEnabled) {
            // TODO - catch exception or do some kind of error checking
            //Checking specifically for game room 1
            if ( (gameProgression.getCurrentStepNumber() > 0) || (gameProgression.getCurrentStepNumber()) < 11) {
                // current hint will be based on what the current step number is
                font.draw(game.batch, roomClues.getCurrentHint(gameProgression.getCurrentStepNumber()), 720, 450);
            }
        }

        if (playerAtEnd()) {
            LevelSelectionScreen.unlockedLevel2(); // unlocks level 2 when this level is complete
            game.setScreen(game.getRoomCompleteScreen());
        }
        // draw top walls for room 1
        game.batch.draw(roomWall, 30, 150, 400, 30);
        game.batch.draw(roomWall, 500, 150, Gdx.graphics.getWidth() - 500 - levelOverlay.getOverlayWidth() - 30, 30);

        // draw top walls for room 2
        game.batch.draw(roomWall, 30, 400, 400, 30);
        game.batch.draw(roomWall, 500, 400, Gdx.graphics.getWidth() - 500 - levelOverlay.getOverlayWidth() - 30, 30);

        // draw top walls for room 3
        game.batch.draw(roomWall, 30, Gdx.graphics.getHeight() - 30, 400, 30);
        game.batch.draw(roomWall, 500, Gdx.graphics.getHeight() - 30, Gdx.graphics.getWidth() - 500 - levelOverlay.getOverlayWidth() - 30, 30);

        // draw the drawer in room 1
        game.batch.draw(room1Drawer.getTexture(), 30, 30, 50, 50);


        drawDoors(game.batch);

         // Useful for checking coordinates-
         font.draw(game.batch, "x: " + playerXPos, 750, 580);
         font.draw(game.batch, "y: " + playerYPos, 750, 560);



        int minutes = (int)countdown / 60;
        int seconds = (int)countdown % 60;

        // as long as there are more than 0 seconds left
        if (countdown > 0)
            timerFont.draw(game.batch, "Time Left: " + String.format("%02d", minutes) + ":" + String.format("%02d", seconds), 750, 540);

            // if time runs out, the game goes to the Game Over Screen
        else {
            game.setScreen(game.getGameOverScreen());
            game.resetGame(new Level1());
        }



        // if the user pressed the P key, the game should pause
        if (Gdx.input.isKeyPressed(Input.Keys.P)) {
            // game state is set to paused
            game.setGameState(EscapeRoomGame.GameState.PAUSED);
            game.setScreen(game.getPauseScreen());
        }

        // pressing control while moving will ignore collision, used for testing
        // remove this functionality after testing
        boolean ctrlPressed = Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT);


        if (Gdx.input.isKeyPressed(Input.Keys.W) && (!topCollisonDetected() || ctrlPressed))  {
            playerYPos += playerMovementConstant;

        }

        if (Gdx.input.isKeyPressed(Input.Keys.S) && (!bottomCollisionDetected() || ctrlPressed)) {
            playerYPos -= playerMovementConstant;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.A) && (!leftCollisionDetected() || ctrlPressed)) {
            playerXPos -= playerMovementConstant;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.D) && (!rightCollisionDetected() || ctrlPressed)) {
            playerXPos += playerMovementConstant;
        }

        if (playerAtLockedDrawer()) {
            // when the player reaches the drawer, the step number increments
            gameProgression.setStepNumber(2);
            if (hintsEnabled)
               font.draw(game.batch, roomClues.getCurrentHint(gameProgression.getCurrentStepNumber()), 720, 450);


            String guess = "guess";
            if (Gdx.input.isKeyPressed(Input.Keys.NUM_0))
                guess = "0";
            else if (Gdx.input.isKeyPressed(Input.Keys.NUM_1))
                guess = "1";
            else if (Gdx.input.isKeyPressed(Input.Keys.NUM_2))
                guess = "2";
            else if (Gdx.input.isKeyPressed(Input.Keys.NUM_3))
                guess = "3";
            else if (Gdx.input.isKeyPressed(Input.Keys.NUM_4))
                guess = "4";
            else if (Gdx.input.isKeyPressed(Input.Keys.NUM_5))
                guess = "5";
            else if (Gdx.input.isKeyPressed(Input.Keys.NUM_6))
                guess = "6";
            else if (Gdx.input.isKeyPressed(Input.Keys.NUM_7))
                guess = "7";
            else if (Gdx.input.isKeyPressed(Input.Keys.NUM_8))
                guess = "8";
            else if (Gdx.input.isKeyPressed(Input.Keys.NUM_9))
                guess = "9";

            if (room1Drawer.getComboLock().compareSolution(guess)) {
                // if player guesses the right number, the drawers will unlock
                room1Drawer.getComboLock().unlock();
            }
        }

        // At this point, the player must press the spacebar in front of the drawer to get the key
        if (playerAtUnlockedDrawer()) {

            gameProgression.setStepNumber(3);
            if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                // add interaction functionality to this conditional
                player.addKey(room1Drawer.getKey());

            }
        }

        /* if the first door is locked and the player has the key,
            they can press the spacebar to unlock and open the door
        */
        if (player.hasKey(door1Key)) {
            gameProgression.setStepNumber(4);
                if (playerAtFirstDoor())
                    if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                        door1.unlockLock();
                        player.removeKey(door1Key);     // removes the key from the player, since they won't need it
                                                        // again
                    }


        }

        if (gameProgression.getCurrentStepNumber() == 4 && playerYPos >= 180)  {
            gameProgression.setStepNumber(5);
        }

        if (gameProgression.getCurrentStepNumber() == 5 && playerYPos >= 180 && playerXPos < 350) {
            gameProgression.setStepNumber(6);
        }

        // if the player is at the floor tile puzzle stage
        if (gameProgression.getCurrentStepNumber() == 6) {
            floorTilePuzzle.tileCheck(playerXPos, playerYPos);
            if (floorTilePuzzle.checkIfPuzzleSolved()) {

                // player gets key
                player.addKey(floorTilePuzzle.getKey());
                gameProgression.setStepNumber(7);
            }
        }

        if (gameProgression.getCurrentStepNumber() == 7 && playerAtSecondDoor()) {
            if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                door2.unlockLock();
                gameProgression.setStepNumber(8);
            }
        }

        // player enters room 3
        if (gameProgression.getCurrentStepNumber() ==  8) {
            if (playerXPos < 320 && playerYPos >= 430)
                gameProgression.setStepNumber(9);
        }

        // player is close to the lever puzzle
        if (gameProgression.getCurrentStepNumber() == 9 ) {

            if (playerOnFirstLever()) {
                if (inputDelay == 1 &&  Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                    leverPuzzle.invertLever(0);
                    inputDelay -= delta;
                }
            }

            if (playerOnSecondLever()) {
                if (inputDelay == 1 && Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                    leverPuzzle.invertLever(1);
                    inputDelay -= delta;
                }
            }

            if (playerOnThirdLever()) {
                if (inputDelay == 1 && Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                    leverPuzzle.invertLever(2);
                    inputDelay -= delta;
                }
            }
            if (leverPuzzle.checkIfSolved()) {
                door3.unlockLock();
                gameProgression.setStepNumber(10);
            }
        }

        // door is unlocked
        if (gameProgression.getCurrentStepNumber() == 10) {
            //LevelSelectionScreen.unlockedLevel2(); // unlocks level 2 when this level is complete
        }


        game.batch.end();
    }

    /**
     * Updates the timer
     * @param delta the amount the timer will go down by
     */
    public void updateTimer(float delta) { countdown -= delta;}


    /**
     * Checks if the user collides into the left wall
     * @return True if collision
     */
    public boolean leftCollisionDetected() {
        int attemptedXPos = playerXPos - playerMovementConstant;
        boolean inFirstDoorWay = (playerYPos >= 120 && playerYPos < 180);
        boolean inSecondDoorWay = (playerYPos >= 370 && playerYPos <= 425);
        boolean inThirdDoorWay = (playerYPos >= 545);

        boolean room1DrawerCollision = (playerYPos <= 75 && attemptedXPos <= 80);

        return (attemptedXPos < wallThickness) ||
                (attemptedXPos <= 425 && (inFirstDoorWay || inSecondDoorWay || inThirdDoorWay)
                || room1DrawerCollision);

    }

    /**
     * Checks if the user collides into the right wall
     * @return True if collision
     */
    public boolean rightCollisionDetected() {
        int attemptedXPos = playerXPos + playerMovementConstant;

        boolean inFirstDoorWay = (playerYPos >= 120 && playerYPos < 180);
        boolean inSecondDoorWay = (playerYPos >= 370 && playerYPos <= 425);
        boolean inThirdDoorWay = (playerYPos >= 545);


        return (playerXPos + playerMovementConstant >= Gdx.graphics.getWidth() - 2 * wallThickness - levelOverlay.getOverlayWidth()) ||
                (attemptedXPos >= 470 && (inFirstDoorWay || inSecondDoorWay || inThirdDoorWay));

    }

    /**
     * Checks if the user collides into the top wall
     * @return True if collision
     */
    public boolean topCollisonDetected() {
        int attemptedYPos = playerYPos + playerMovementConstant;
        boolean facingWall = (playerXPos >= 30 && playerXPos <= 430) || (playerXPos >= 465 && playerXPos <= 635);
        boolean inFirstRoom = (playerYPos >= 30 && playerYPos < 120);
        boolean inSecondRoom = (playerYPos > 180 && playerYPos < 370);
        boolean inThirdRoom = (playerYPos >= 430 && playerYPos < 540);

        return  ((inFirstRoom && facingWall) && attemptedYPos >= 120) ||
                    (inFirstRoom && !facingWall && door1.isLocked() && attemptedYPos >= 120) ||
                (inSecondRoom && facingWall && attemptedYPos >= 370) ||
                    (inSecondRoom && !facingWall && door2.isLocked() && attemptedYPos >= 370) ||
                (inThirdRoom && facingWall && attemptedYPos >= 540) ||
                    (inThirdRoom && !facingWall && door3.isLocked() && attemptedYPos >= 540);
    }

    /**
     * Checks if the user collides into the bottom wall
     * @return True if collision
     */
    public boolean bottomCollisionDetected() {
        int attemptedYPos = playerYPos - playerMovementConstant;
        boolean facingWall = (playerXPos >= 30 && playerXPos <= 430) || (playerXPos >= 465 && playerXPos <= 635);
        boolean inFirstRoom = (playerYPos >= 30 && playerYPos < 120);
        boolean inSecondRoom = (playerYPos >= 180 && playerYPos < 370);
        boolean inThirdRoom = (playerYPos >= 430 && playerYPos < 540);

        boolean room1DrawerCollision = (playerXPos < 80 && attemptedYPos < 80 );

        return (inFirstRoom &&  attemptedYPos < 30) ||
                (inSecondRoom && facingWall && attemptedYPos <= 175) ||
                    (inSecondRoom && !facingWall && door1.isLocked() && attemptedYPos <= 175) ||
                (inThirdRoom && facingWall && attemptedYPos <= 425) ||
                    (inThirdRoom && !facingWall && door1.isLocked() && attemptedYPos <= 425)
                || room1DrawerCollision;
    }

    // function checks to see if player is standing in front of the drawer when it is locked
    public boolean playerAtLockedDrawer() {
        return (room1Drawer.isLocked() && playerXPos < 90 && playerYPos < 45);
    }

    // function checks to see if player is standing in front of the drawer when it is locked
    // also checks to see if the key is not taken yet
    public boolean playerAtUnlockedDrawer() {
        return (!room1Drawer.isLocked() && !room1Drawer.isKeyTaken() && playerXPos < 90 && playerYPos < 45);
    }

    //Checks to see if player is at the first door
    public boolean playerAtFirstDoor() {
        return (door1.isLocked() && playerYPos == 115 && playerXPos >= 425 && playerXPos <= 465);
    }

    //Checks to see if player is at the second door
    public boolean playerAtSecondDoor() {
        return (door2.isLocked() && playerYPos == 365 && playerXPos >= 425 && playerXPos <= 465);
    }

    //Checks to see if player is at the third door
    public boolean playerAtThirdDoor() {
        return (door3.isLocked() && playerYPos == 535 && playerXPos >= 425 && playerXPos <= 465);
    }

    //Checks to see if player is at the end
    public boolean playerAtEnd() {
        return (playerYPos >= 600);
    }

    //Checks to see if player is at the first lever
    public boolean playerOnFirstLever() {
        return (playerXPos >= 70 && playerXPos <= 95 && playerYPos >= 520);
    }

    //Checks to see if player is at the second lever
    public boolean playerOnSecondLever() {
        return (playerXPos >= 125 && playerXPos <= 155 && playerYPos >= 520);
    }

    //Checks to see if player is at the third lever
    public boolean playerOnThirdLever() {
        return (playerXPos >= 185 && playerXPos <= 220 && playerYPos >= 520);
    }

    /**
     * The progression hint progression of room 1. The progression will be placed
     * in an ArrayList from 0-9 and we will update the clues as we progress through
     * the room. These will show up on the game when the user toggles hints on
     * based on their current progression.
     */

    @Override
    protected void addHints() {
        //Progression 1
        roomClues.addClue("Welcome to the game. This \nis where hints will be provided\nto help you if you get stuck.\nYou are currently in room 1.\nMove with WASD. Interact with\nobjects with the space bar.");
        //Progression 2
        roomClues.addClue("It looks like there is a lock\non the drawer. \nGuess a number between 0-9, \nthen click that number on \nyour keyboard");
        //Progression 3
        roomClues.addClue("It looks like there might be\nsomething in the drawer. \nPress space to open it.");
        //Progression 4
        roomClues.addClue("You found a key in the drawer. \nIt looks like it could \nopen the door up ahead. \nPress space on the door \nto open it.");
        //Progression 5 TODO
        roomClues.addClue("Welcome to room 2.\nMove around to get your\nnext clue");
        //Progression 6 TODO
        roomClues.addClue("The tiles on the \nfloor look like \nthey activate something.");
        //Progression 7
        roomClues.addClue("After solving the floor \npuzzle, you pick up a key \nthat fell from the ceiling. \nYou should try it on the door \nahead.");
        //Progression 8
        roomClues.addClue("Welcome to Room 3. Look \naround for another clue");
        //Progression 9 TODO
        roomClues.addClue("These levers looks interesting");
        //Progression 10
        roomClues.addClue("The final door is now open! \nMake sure to leave the room.");
    }

    @Override
    public void addWalls() {

    }

    @Override
    public void addDoors() {
        levelDoors = new ArrayList<Door>(0);

        door1 = new Door(Door.DoorType.HORIZONTAL, 430, 70, 150, 30, 70);
        door2 = new Door(Door.DoorType.HORIZONTAL, 430, 70, 400, 30, 70);
        door3 = new Door(Door.DoorType.HORIZONTAL, 430, 70,Gdx.graphics.getHeight() - 30, 30, 70);

        levelDoors.add(door1);
        levelDoors.add(door2);
        levelDoors.add(door3);
    }
    @Override
    protected void addPuzzles() {

    }

    @Override
    protected void defineRooms() {

    }

    @Override
    public void resetLevel() {

    }


}
