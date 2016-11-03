package uk.co.ctdev.imageslider;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import uk.co.ctdev.imageslider.ImageSlider.ImageSlider;
import uk.co.ctdev.imageslider.ImageSlider.ImageSliderUI;
import uk.co.ctdev.imageslider.ImageSlider.SimpleImageSlider;
import uk.co.ctdev.imageslider.ImageSlider.SliderUI;
import uk.co.ctdev.imageslider.ImageSlider.SliderUIException;
import uk.co.ctdev.imageslider.ImageSlider.View;

public class Game extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img, img2;
	ImageSlider imageSlider;
	ImageSliderUI ui;

    private static final String TAG = "GAME";
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		img2 = new Texture("lizard_minion.png");

		int width = Gdx.graphics.getWidth();
		int height = Gdx.graphics.getHeight();

        Gdx.app.log(TAG, "Width:" + width);
		Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));


		try {
			imageSlider = new SimpleImageSlider(skin, width, height, ImageSliderUI.class);
		} catch (SliderUIException e) {
			Gdx.app.error(TAG, "Image slider not created");
		}

		View v1 = new View();
		v1.add(new Image(img));
		v1.row();
		v1.add(new Label("One View", skin));

		View v2 = new View(new Image(img), width, height);
		v2.row();
		v2.add(new Label("Two View", skin));

		Table v3 = new Table();
		v3.add(new Image(img2));
		v3.row();
		v3.add(new Label("Three", skin));

		View v4 = new View(new Image(img), width, height);
		v4.row();
		v4.add(new Label("View Four", skin));


		imageSlider.addView(v1);
		imageSlider.addView(v2);
		imageSlider.addView(v3);
		imageSlider.addView(v4);





		Gdx.input.setInputProcessor(imageSlider.getInputProcessor());
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		/*batch.begin();
		batch.draw(img, 0, 0);
		batch.end();*/
		imageSlider.draw();
		imageSlider.act();
	}
}
