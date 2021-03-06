package de.aps.prinz_of_pc.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import de.aps.prinz_of_pc.PrinzGame;
import de.aps.prinz_of_pc.fonts.MyFonts;
import de.aps.prinz_of_pc.tool_methods.ToolMethods;

public class DescriptionScreen extends Games implements Screen{

	GlyphLayout layout;
	ShapeRenderer shapeRenderer;
	Texture background;
	final float boxWidth = 275;
	final float boxHeight = 100;

	public DescriptionScreen(PrinzGame game) {
		super(game);
		this.game = game;
		this.fonts = new MyFonts();
		this.layout = new GlyphLayout();
		this.shapeRenderer = new ShapeRenderer();
		this.background = new Texture("MenueScreen.jpeg");
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float delta) {

		String menu = "Menue";
		layout.setText(fonts.arial, menu);
		float positionXBack = PrinzGame.WIDTH * 0.025f;
		float positionYBack = PrinzGame.HEIGHT * 0.95f;
		float positionXSecondBack = positionXBack + layout.width;

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		// Drwas background
		game.batch.begin();
		game.batch.draw(background, 0, 10);
		game.batch.end();
		
		//Draws menue Box
		drawBoxes(positionXBack, positionYBack);

		// Draws fonts
		game.batch.begin();

		drawFont(menu, positionXBack, positionXSecondBack, positionYBack, PrinzGame.HEIGHT - 
				PrinzGame.HEIGHT * 0.1f, PrinzGame.HEIGHT, fonts.arial);
		createHeading();
		createDescription();

		game.batch.end();

	}

	private void createDescription(){
		String description = "Ziel:\n"
				+ "Ziel des Spiels ist es, den entfuehrten Prinzen aus der Gefangenschaft "
				+ "zu befreien.\nIndem man Leute anspricht und deren Fragen richtig beantwortet, "
				+ "kommt man dem\nZiel naeher. Bei falschen Antworten verliert man an Leben."
				+ "Wenn alle Leben\naufgebraucht sind, ist das Spiel vorbei\n\n"
				+ "Steuerung:" 
				+ "Bewegung nach oben (w), nach links (a), nach unten (s), nach rechts (d)\n"
				+ "Dialoge weiterklicken: Leertaste\n"
				+ "Dialog beginnen: Leertaste";
		layout.setText(fonts.description, description);
		float positionXDescription = PrinzGame.WIDTH / 2 - layout.width / 2;
		float positionYDescription = PrinzGame.HEIGHT / 2 + layout.height / 2;

		fonts.description.setColor(Color.WHITE);
		fonts.description.draw(game.batch, description, positionXDescription, positionYDescription);
	}

	private void createHeading(){
		String heading = "Anleitung";
		layout.setText(fonts.arial, heading);
		float positionXHeading = PrinzGame.WIDTH / 2 - layout.width / 2;
		float positionYHeading = PrinzGame.HEIGHT * 0.95f;
		
		fonts.arial.setColor(Color.WHITE);
		fonts.arial.draw(game.batch, heading, positionXHeading, positionYHeading);
	}

	private void drawBoxes(float positionX, float positionY){
		float boxGapX = PrinzGame.WIDTH * 0.05f;
		float boxGapY = PrinzGame.HEIGHT * 0.1f;
		float positionXBox = positionX - boxGapX;
		float positionYBox = positionY - boxGapY;

		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(Color.DARK_GRAY);
		shapeRenderer.rect(positionXBox, positionYBox, boxWidth, boxHeight);
		shapeRenderer.rect(45, 200, 1000, 300);
		shapeRenderer.end();

		checkIfButtonIsClicked(positionXBox, positionYBox);
	}

	private void checkIfButtonIsClicked(float positionX, float positionY){
		float positionSecondX = positionX + boxWidth;
		float positionSecondY = positionY + boxHeight;

		if(Gdx.input.isButtonPressed(Buttons.LEFT) && ToolMethods.checkIfMouseIsInTheArea(positionX, 			positionY, positionSecondX, positionSecondY)){
			this.dispose();
			game.setScreen(new MainMenuScreen(game));
		}
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		super.dispose();
		shapeRenderer.dispose();
		background.dispose();
	}

}
