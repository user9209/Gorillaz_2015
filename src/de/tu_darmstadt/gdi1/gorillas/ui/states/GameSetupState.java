package de.tu_darmstadt.gdi1.gorillas.ui.states;

import de.matthiasmann.twl.Button;
import de.matthiasmann.twl.EditField;
import de.matthiasmann.twl.slick.BasicTWLGameState;
import de.matthiasmann.twl.slick.RootPane;
import de.tu_darmstadt.gdi1.gorillas.main.Gorillas;
import eea.engine.entity.StateBasedEntityManager;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;


public class GameSetupState extends BasicTWLGameState {

    private int stateID;
    private StateBasedEntityManager entityManager;
    private RootPane rp;

    private Image background;
    private Button btnStart;
    private EditField txtName1, txtName2;

    public GameSetupState(int sid) {
        stateID = sid;
        entityManager = StateBasedEntityManager.getInstance();
    }

    @Override
    public int getID() {
       return stateID;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame game) throws SlickException {
        rp = super.createRootPane();

        background = new Image("assets/menu/background.png");

        txtName1 = new EditField();
        txtName2 = new EditField();

        txtName1.setText(Gorillas.player1);
        txtName2.setText(Gorillas.player2);

        btnStart = new Button("GO");
        btnStart.addCallback(new Runnable() {
            public void run() {
                String n1 = txtName1.getText();
                String n2 = txtName2.getText();

                if(!(n1.isEmpty()) && !(n2.isEmpty()) && !(n1.equals(n2)) && (n1.length() < 13) && (n2.length() < 13)) {
                    // Only update player names, if we have valid inputs
                    Gorillas.player1 = n1;
                    Gorillas.player2 = n2;
                    game.enterState(Gorillas.GAMEPLAYSTATE);
                }
                else {
                    btnStart.setText("Error");
                }
            }
        });

        rp.add(txtName1);
        rp.add(txtName2);
        rp.add(btnStart);
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        graphics.drawImage(background, -10, -20);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {

    }

    @Override
    protected RootPane createRootPane() {
        return rp;
    }

    @Override
    protected void layoutRootPane() {
        int paneHeight = this.getRootPane().getHeight();
        int paneWidth = this.getRootPane().getWidth();


        txtName1.setSize(256, 32);
        txtName2.setSize(256, 32);
        btnStart.setSize(256, 32);

        int x = paneWidth + txtName1.getWidth();

        // Embrace thy glorious operator :D
        x >>>= 2;

        txtName1.setPosition(x,  40);
        txtName2.setPosition(x,  80);
        btnStart.setPosition(x, 120);

    }

}