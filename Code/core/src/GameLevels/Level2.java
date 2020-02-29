package GameLevels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.EscapeRoomGame;
import com.mygdx.game.RoomObjects.*;
import com.mygdx.game.RoomObjects.Door;
import com.mygdx.game.RoomObjects.FloorTilePuzzle;
import com.mygdx.game.RoomObjects.LockKey;
import com.mygdx.game.RoomObjects.Wall;
import com.mygdx.game.mechanics.Level;
import com.mygdx.game.mechanics.Player;

import com.mygdx.game.RoomObjects.AnagramPuzzle;
import com.mygdx.game.RoomObjects.WordPuzzle;
import com.mygdx.game.RoomObjects.NumberPuzzle;
import com.mygdx.game.RoomObjects.SequencePuzzle;
import com.mygdx.game.RoomObjects.Cipher;
import com.mygdx.game.RoomObjects.CipherPuzzle;
import com.mygdx.game.screens.RoomCompleteScreen;
import com.mygdx.game.screens.RoomSelectionScreen;

import java.util.ArrayList;


public class Level2 extends Level {

    private Player player;

    private float inputDelay = 2;

    private AnagramPuzzle anagram1;
    private AnagramPuzzle anagram2;
    private AnagramPuzzle anagram3;
    private AnagramPuzzle anagram4;
    private AnagramPuzzle anagram5;
    private WordPuzzle anagrams;
    private AnagramPuzzle selectedAnagram;

    private SequencePuzzle sequence1;
    private SequencePuzzle sequence2;
    private SequencePuzzle sequence3;
    private SequencePuzzle sequence4;
    private SequencePuzzle sequence5;
    private NumberPuzzle sequences;
    private SequencePuzzle selectedSequence;

    private CipherPuzzle cipher1;
    private CipherPuzzle cipher2;
    private CipherPuzzle cipher3;
    private CipherPuzzle cipher4;
    private CipherPuzzle cipher5;
    private Cipher ciphers;

    private RiddlePuzzle riddles;

    private Stage stage;
    private TextField inputText;
    private TextButton confirmButton;


    // used to get coordinates from mouse click
    private int xClick, yClick = 0;

    private Texture anagramPuzzleTexture;
    private Texture sequencePuzzleTeture;
    private Texture riddlePuzzleTexture;

    private String riddlePhrase;
    private String riddleAnswer;


    private FloorTilePuzzle floorTilePuzzle;
    private LeverPuzzle leverPuzzle;

    BitmapFont timerFont;
    BitmapFont font;
    public Level2() {
        super();

        player = new Player(60, 60);

        timerFont = new BitmapFont();
        timerFont.setColor(Color.RED);

        font = new BitmapFont();
        font.setColor(Color.WHITE);

        addAnagrams();

        addCiphers();

        addSequences();
        addRiddles();
        //addHints();

        setupFloorTilePuzzle();
        setUpLeverPuzzle();
        Skin uiSkin = new Skin(Gdx.files.internal("uiskin.json"));


        stage = new Stage();

        inputText = new TextField("", uiSkin);
        inputText.setPosition(710, 170);
        inputText.setSize(100, 30);
        inputText.setDisabled(true);
        inputText.setVisible(false);





        confirmButton = new TextButton("Enter", uiSkin);

        confirmButton.setPosition(830, 170);
        confirmButton.setSize(70, 30);
        confirmButton.setDisabled(true);
        confirmButton.setVisible(false);

        anagramPuzzleTexture = new Texture("anagrampuzzle_texture.png");
        sequencePuzzleTeture = new Texture("sequencepuzzle_texture.png");
        riddlePuzzleTexture = new Texture("riddlepuzzle_texture.png");

        riddlePhrase = riddles.randomlyDisplayRiddle();
        riddleAnswer = riddles.answerForPhrase(riddlePhrase).toLowerCase();

        stage.addActor(inputText);
        stage.addActor(confirmButton);
        Gdx.input.setInputProcessor(stage);

        // starting step number
        gameProgression.setStepNumber(1);
        levelRooms.get(0).uncoverRoom();
    }

    protected void addCiphers()
    {
        cipher1 = new CipherPuzzle("there is a snake in my boot", "Toy Story - Andy");
        cipher2 = new CipherPuzzle("to be or not to be", "Hamlet - Shakespeare");
        cipher3 = new CipherPuzzle("go ahead make my day", "Sudden Impact - Clint Eastwood");
        cipher4 = new CipherPuzzle("may the force be with you", "Star Wars, Obi-Wan Kenobi");
        cipher5 = new CipherPuzzle("there is no place like home", "Wizard of Ozz - Dorothy");

        ciphers = new Cipher();

        ciphers.addCipher(cipher1);
        ciphers.addCipher(cipher2);
        ciphers.addCipher(cipher3);
        ciphers.addCipher(cipher4);
        ciphers.addCipher(cipher5);

        CipherPuzzle selectedCipher = ciphers.chooseRandomCipher();

        selectedCipher.makeCipher();

        //String a = selectedCipher.getAnswer();
        //String b = selectedCipher.getCipher();

        //System.out.println(a);
        //System.out.println(b);
        /**
         if(selectedCipher.checkIfCorrect(selectedCipher.getAnswer()))
         {
         System.out.println("success");
         }
         **/

        //to check if correct do selectedCipher.checkIfCorrect(String userAns)
        // returns true if they answer correctly
        // false otherwise
    }
    protected void addSequences(){
        sequence1 = new SequencePuzzle("1 1 2 3 5 8 ?", 13, "Fionacci sequence"); //next fibonacci
        sequence2 = new SequencePuzzle("2 3 5 11 13 17 19 ?", 23, "Prime numbers"); //next prime
        sequence3 = new SequencePuzzle("2 3 5 9 17 ?", 33, "Multiply by x add y"); //*2+1
        sequence4 = new SequencePuzzle("1 1 4 27 ?", 256, "x to the y (x^y)"); //0^0 1^1 2^2 3^3 4^4
        sequence5 = new SequencePuzzle("-3 9 -27 ?", 81, "x to the y (x^y)"); //3^1, -3^2...

        sequences = new NumberPuzzle();

        sequences.addSequence(sequence1);
        sequences.addSequence(sequence2);
        sequences.addSequence(sequence3);
        sequences.addSequence(sequence4);
        sequences.addSequence(sequence5);

        selectedSequence = sequences.chooseRandomSequence();

        /**
         String a = selectedSequence.getSequence();

         System.out.println(a);
         if(selectedSequence.checkAnswer(selectedSequence.getAnswer()))
         {
         System.out.println("success");
         }
         **/
        //to check if correct do selectedSequence.checkAnswer(int userAns)
        // will return boolean true is correct answer was input by user
        // false otherwise

    }
    protected void addAnagrams(){
        anagram1 = new AnagramPuzzle("programming", "Another word for coding");
        anagram2 = new AnagramPuzzle("astronomer", "A scientist in the branch of science that deals with space");
        anagram3 = new AnagramPuzzle("redundant", "Unnecessary extra details");
        anagram4 = new AnagramPuzzle("algorithm", "Step by step method of solving a problem");
        anagram5 = new AnagramPuzzle("composite", "A partitioning design pattern ");



        anagrams = new WordPuzzle();

        anagrams.addAnagram(anagram1);
        anagrams.addAnagram(anagram2);
        anagrams.addAnagram(anagram3);
        anagrams.addAnagram(anagram4);
        anagrams.addAnagram(anagram5);

        selectedAnagram = anagrams.chooseRandomAnagram();

        selectedAnagram.shufflePhrase();

        /**
         if(selectedAnagram.checkIfCorrect(selectedAnagram.getWord()))
         {
         System.out.println("success");
         }**/
        //to check if correct do anagram.checkIfCorrect(String UserInput)
        //return true if they guess the correct anagram
        //String a = anagram.getAnagram();
        //String b = anagram.getWord();

        //System.out.println(a);
        //System.out.println(b);
    }

    protected void addRiddles()
    {
        riddles = new RiddlePuzzle();
        String key;
        String value;

        key = "I can fly but have no \nwings. I can cry but I \nhave no eyes.\nWherever I go, darkness \nfollows me. What\nam I?";
        value = "Clouds";
        riddles.addToHashMap(key,value);

        key = "The more you take,\n the more you leave\nbehind. What am I?";
        value = "Footsteps";
        riddles.addToHashMap(key,value);

        key = "What 4-letter word can be written forward, backward or upside down, and can still be read from left to right?";
        value = "Noon";
        riddles.addToHashMap(key,value);

        key = "What is easy to\nget into, but\nhard to get out\nof?";
        value = "Trouble";
        riddles.addToHashMap(key,value);

        key = "Feed me and I live,\nyet give me a\ndrink and I die";
        value = "Fire";
        riddles.addToHashMap(key,value);

        riddles.randomlyDisplayRiddle();

//            riddles.isCorrect(String playerGuess)
    }




    @Override
    protected void setLevelName() {
        levelName = "Level 2";
    }

    @Override
    protected void setCountdown() {
        countdown = 600;
    }

    @Override
    protected void addHints() {

        //Progression 1
        roomClues.addClue("Welcome to the Level\nLook around for a clue");
        //Progression 2
        roomClues.addClue("That green box looks interesting");
        //roomClues.addClue(selectedAnagram.getClue());
        //selectedAnagram.getClue();
        //Progression 3
        roomClues.addClue("There is a door up ahead");
        //Progression 4
        //roomClues.addClue(selectedSequence.getClue());
        roomClues.addClue("That red box looks interesting");
        //Progression 5
        roomClues.addClue("This door looks interesting");
        //Progression 6
        roomClues.addClue("These tiles looks interesting");
        //Progression 7 looks like it was skipped? Door immediately opens
        roomClues.addClue("Door opens");
        //Progression 8
        //roomClues.addClue(riddles.showHint());
        roomClues.addClue("This look interesting");
        //roomClues.addClue()
        //Progression 9 looks like it gets skipped? Door immediately opens
        roomClues.addClue("Door opens");
        //Progression 10
        roomClues.addClue("These levers look interesting");
        //Progression 11
        roomClues.addClue("There is a sequence here");
        //Progression 12
        roomClues.addClue("You have finished the level feel free to exit.");
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

        // bottom wall left side
        levelWalls.add(new Wall(Wall.WallType.HORIZONTAL, 0, 520, 0, 30));

        // bottom wall - hidden component - INDEX 3
        levelWalls.add(new Wall(Wall.WallType.HORIZONTAL, 520, 50, 0, 30));

        // bottom wall - right side
        levelWalls.add(new Wall(Wall.WallType.HORIZONTAL, 570, 130, 0, 30));

        // top wall
        levelWalls.add(new Wall(Wall.WallType.HORIZONTAL, 0,
                Gdx.graphics.getWidth() - levelOverlay.getOverlayWidth(), Gdx.graphics.getHeight() - 30, 30));




        // Room 1 to Room 2 Walls
        levelWalls.add(new Wall(Wall.WallType.HORIZONTAL, 30, 60, 300, 5));
        levelWalls.add(new Wall(Wall.WallType.HORIZONTAL, 140, 60, 300, 5));  // BUG on this wall


        // Second vertical wall from the left
        levelWalls.add(new Wall(Wall.WallType.VERTICAL, 200, 5, 30, 480));

        // Room 3 horizontal walls
        levelWalls.add(new Wall(Wall.WallType.HORIZONTAL, 205,  100, 400, 5));
        levelWalls.add(new Wall(Wall.WallType.HORIZONTAL, 380,  290, 400, 5));

        // Room 4 to Room 5 Walls
        levelWalls.add(new Wall(Wall.WallType.VERTICAL, 435, 5, 30, 250));
        levelWalls.add(new Wall(Wall.WallType.VERTICAL, 435, 5, 330, 70));
    }

    @Override
    protected void addDoors() {

        levelDoors = new ArrayList<Door>(0);
        Door door1 = new Door(Door.DoorType.HORIZONTAL, 90, 50, 300, 5, -50);
        Door door2 = new Door(Door.DoorType.VERTICAL, 200, 5, 510, 60, -60);
        Door door3 = new Door(Door.DoorType.HORIZONTAL, 305, 75, 400, 5, -75);
        Door door4 = new Door(Door.DoorType.VERTICAL, 435, 5, 280, 50, -50);

        levelDoors.add(door1);
        levelDoors.add(door2);
        levelDoors.add(door3);
        levelDoors.add(door4);
    }

    @Override
    protected void addPuzzles() {

    }

    @Override
    protected void defineRooms() {
        levelRooms = new ArrayList<Room>(0);
        levelRooms.add(new Room(30, 200, 30, 300));     // room 1
        levelRooms.add(new Room(30, 205, 305, 570));    // room 2
        levelRooms.add(new Room(205,670, 400, 570));    // room 3
        levelRooms.add(new Room(205, 440, 30, 400));    // room 4
        levelRooms.add(new Room(440, 670, 30, 400));    // room 5
    }

    @Override
    public void drawElements(EscapeRoomGame game, float delta) {

        updateCountdown(delta);
        if (inputDelay < 2) {
            inputDelay -= delta;
            if (inputDelay <= 0)
                inputDelay = 2;

        }
        Gdx.gl.glClearColor(160/255f,160/255f,160/255f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();



        //stage.setKeyboardFocus(inputText);
        game.batch.begin();

        progressGame(game, inputDelay);

        int minutes = (int)countdown / 60;
        int seconds = (int)countdown % 60;

        // as long as there are more than 0 seconds left
        if (countdown > 0)
            timerFont.draw(game.batch, "Time Left: " + String.format("%02d", minutes) + ":" + String.format("%02d", seconds), 750, 540);


            // if time runs out, the game goes to the Game Over Screen
        else {
            game.setScreen(game.getGameOverScreen());
            game.resetGame(new GameLevels.Level2());
        }

        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
            xClick = Gdx.input.getX();
            yClick = Gdx.graphics.getHeight() - Gdx.input.getY();
        }
//                        // Debugging to open doors
//                        if (Gdx.input.isKeyPressed(Input.Keys.U)){
//                            for (Door d: levelDoors) {
//                            for (Door d: levelDoors) {
//                                if (d.isLocked() == true)
//                                    d.unlockLock();
//                            }
//
//                        }

        // Debugging to progress current step
        if (Gdx.input.isKeyPressed(Input.Keys.I) && movementEnabled){
            gameProgression.setStepNumber(gameProgression.getCurrentStepNumber() + 1);
        }

        // Useful for checking coordinates-
        font.draw(game.batch, "x: " + player.getxPos(), 750, 580);
        font.draw(game.batch, "y: " + player.getyPos(), 750, 560);

        // displays coordinates of last click
        font.draw(game.batch, "x Click: " + xClick, 830, 580);
        font.draw(game.batch, "y Click: " + yClick, 830, 560);

        // displays current step number
        font.draw(game.batch, "Current Step: " + gameProgression.getCurrentStepNumber(), 750, 100);


        // if the user pressed the P key, the game should pause
        if (Gdx.input.isKeyPressed(Input.Keys.P) && movementEnabled) {
            // game state is set to paused
            game.setGameState(EscapeRoomGame.GameState.PAUSED);
            game.setScreen(game.getPauseScreen());
        }

        drawWalls(game.batch);
        drawDoors(game.batch);
        player.draw(game.batch);
        levelOverlay.draw(game.batch, this);
        floorTilePuzzle.drawTiles(game.batch);
        leverPuzzle.drawLevers(game.batch);

        floorTilePuzzle.tileCheck(player.getxPos(), player.getyPos());

        //levelRooms.get(0).uncoverRoom();
        drawRoomCovers(game.batch);
        player.move(this, movementEnabled);

        game.batch.end();


    }

    @Override
    public void resetLevel() {

    }

    private void setupFloorTilePuzzle() {
        floorTilePuzzle = new FloorTilePuzzle(new LockKey());
        floorTilePuzzle.addTile(520, 520, 40, 40, 0);
        floorTilePuzzle.addTile(580, 520, 40, 40, 1);
        floorTilePuzzle.addTile(620, 470, 40, 40, 2);
        floorTilePuzzle.addTile(520, 420, 40, 40, 3);
        floorTilePuzzle.addTile(580, 420, 40, 40, 4);
        floorTilePuzzle.initializeSequence();
    }

    private void setUpLeverPuzzle() {
        leverPuzzle = new LeverPuzzle(new LockKey());
        leverPuzzle.addLever(465,380, 40, 20, 0);
        leverPuzzle.addLever(515,380, 40, 20, 0);
        leverPuzzle.addLever(565,380, 40, 20, 0);
        leverPuzzle.initialize();
    }

    private void progressGame(EscapeRoomGame game, float delay) {

        // Outline Steps here

        if (gameProgression.getCurrentStepNumber() == 1) {
            if (player.getyPos() > 120) {
                gameProgression.setStepNumber(2);
            }
        }

        if (gameProgression.getCurrentStepNumber() == 2) {
            // player has to solve anagram puzzle

            game.batch.draw(anagramPuzzleTexture, 165,265, 35, 35);
            // player in the top right corner of room 1
            if (player.getxPos() == 165 && player.getyPos() == 265) {

                movementEnabled = false;
                font.setColor(Color.YELLOW);

                font.draw(game.batch, "Rearrange the the given\nletters into a real word", 730, 360);
                font.draw(game.batch, selectedAnagram.getAnagram(), 750, 300);
                inputText.setVisible(true);
                inputText.setDisabled(false);

                confirmButton.setVisible(true);
                confirmButton.setDisabled(false);

                confirmButton.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {

                        // if user got the word right
                        if (inputText.getText().trim().equals(selectedAnagram.getWord())) {

                            // user unlocks progression step 3
                            inputText.setText("");
                            inputText.setDisabled(true);
                            inputText.setVisible(false);

                            confirmButton.setDisabled(true);
                            confirmButton.setVisible(false);
                            gameProgression.setStepNumber(3);
                        }

                        // if user didn't get the word right
                        else {
                            player.setxPos(160);
                            player.setyPos(260);
                        }
                        // re-enables movement
                        movementEnabled = true;
                    }
                });
            }

            else {
                // clears the text field
                inputText.setText("");
                inputText.setDisabled(true);
                inputText.setVisible(false);

                confirmButton.setDisabled(true);
                confirmButton.setVisible(false);

            }
        }

        // user has the key to open the door to room 2
        if (gameProgression.getCurrentStepNumber() == 3) {
            if (player.getyPos() == 265 && (player.getxPos() >= 80 && player.getxPos() <= 125)) {
                if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                    // if player presses space, door opens, room cover goes away, game goes to step 4
                    levelRooms.get(1).uncoverRoom();
                    levelDoors.get(0).unlockLock();
                    gameProgression.setStepNumber(4);
                }
            }
        }

        // user just opened door to room 2
        if (gameProgression.getCurrentStepNumber() == 4) {
            game.batch.draw(sequencePuzzleTeture, 165, 535, 35, 35);

            if (player.getxPos() == 165 && player.getyPos() == 535) {
                movementEnabled = false;

                font.draw(game.batch, "Find the next number\nin the sequence", 730, 360);
                font.draw(game.batch, selectedSequence.getSequence(), 750, 300);

                inputText.setVisible(true);
                inputText.setDisabled(false);

                confirmButton.setVisible(true);
                confirmButton.setDisabled(false);


                confirmButton.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {

                        // if user got the number right
                        if (inputText.getText().trim().equals(Integer.toString(selectedSequence.getAnswer()))) {

                            // user unlocks progression step 5
                            inputText.setText("");
                            inputText.setDisabled(true);
                            inputText.setVisible(false);

                            confirmButton.setDisabled(true);
                            confirmButton.setVisible(false);
                            gameProgression.setStepNumber(5);
                        }

                        // if user didn't get the number right
                        else {
                            player.setxPos(130);
                            player.setyPos(535);
                        }
                        // re-enables movement
                        movementEnabled = true;
                    }
                });
            }

            else {
                // clears the text field
                inputText.setText("");
                inputText.setDisabled(true);
                inputText.setVisible(false);

                confirmButton.setDisabled(true);
                confirmButton.setVisible(false);

            }

        }

        if (gameProgression.getCurrentStepNumber() == 5) {
            // player opens door to Room3
            if (player.getxPos() == 165 && (player.getyPos() >= 500 && player.getyPos() <= 535)) {
                if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {

                    levelRooms.get(2).uncoverRoom();   // room3
                    levelDoors.get(1).unlockLock();    // opens door 2
                    gameProgression.setStepNumber(6);
                }
            }

        }

        // player in Room3, tile puzzle is active
        if (gameProgression.getCurrentStepNumber() == 6) {

            if (floorTilePuzzle.checkIfPuzzleSolved()) {
                gameProgression.setStepNumber(7);
            }
        }

        if (gameProgression.getCurrentStepNumber() == 7) {

            // Room 4 door opens, Room4 uncovered
            levelDoors.get(2).unlockLock();
            levelRooms.get(3).uncoverRoom();
            gameProgression.setStepNumber(8);

        }

        if (gameProgression.getCurrentStepNumber() == 8) {

            game.batch.draw(riddlePuzzleTexture, 400, 290, 35, 35);

            if (player.getxPos() == 400 && player.getyPos() == 290) {
                movementEnabled = false;


                font.draw(game.batch, "Find the answer\nto the riddle", 730, 360);
                font.draw(game.batch, riddlePhrase, 720, 310);

                inputText.setVisible(true);
                inputText.setDisabled(false);

                confirmButton.setVisible(true);
                confirmButton.setDisabled(false);


                confirmButton.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {

                        // if user got the number right
                        if (inputText.getText().trim().toLowerCase().equals(riddleAnswer)) {

                            // user unlocks progression step 5
                            inputText.setText("");
                            inputText.setDisabled(true);
                            inputText.setVisible(false);

                            confirmButton.setDisabled(true);
                            confirmButton.setVisible(false);
                            gameProgression.setStepNumber(9);
                        }

                        // if user didn't get the number right
                        else {
                            player.setxPos(360);
                            player.setyPos(290);
                        }
                        // re-enables movement
                        movementEnabled = true;
                    }
                });
            }

            else {
                // clears the text field
                inputText.setText("");
                inputText.setDisabled(true);
                inputText.setVisible(false);

                confirmButton.setDisabled(true);
                confirmButton.setVisible(false);

            }
        }

        if (gameProgression.getCurrentStepNumber() == 9) {

            // when player uses key

            levelDoors.get(3).unlockLock();
            levelRooms.get(4).uncoverRoom();
            gameProgression.setStepNumber(10);
        }

        if (gameProgression.getCurrentStepNumber() == 10) {

            int x = player.getxPos();
            int y = player.getyPos();

            // player on first lever
            if (y == 365 && x >= 450 && x < 470) {
                if (inputDelay == 2 &&  Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                    leverPuzzle.invertLever(0);
                    inputDelay -= delay;
                }
            }

            // player on second lever
            if (y == 365 && x >= 505 && x < 530) {
                if (inputDelay == 2 &&  Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                    leverPuzzle.invertLever(1);
                    inputDelay -= delay;
                }
            }

            // player on third lever
            if (y == 365 && x >= 555 && x < 580) {
                if (inputDelay == 2 &&  Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                    leverPuzzle.invertLever(2);
                    inputDelay -= delay;
                }
            }

            if (leverPuzzle.checkIfSolved()) {
                gameProgression.setStepNumber(11);
            }
        }


        if (gameProgression.getCurrentStepNumber() == 11) {
            //Hidden wall opens up
            levelWalls.remove(3);
            gameProgression.setStepNumber(12);
        }

        // game ends
        if (gameProgression.getCurrentStepNumber() == 12) {
            if (player.getyPos() < 30) {
                RoomCompleteScreen.setPointsGained(Math.round(600 - countdown) * 10);
                game.setScreen(game.getRoomCompleteScreen());
            }


        }







    }
}
