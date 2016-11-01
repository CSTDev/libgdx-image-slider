package uk.co.ctdev.imageslider.ImageSlider;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

/**
 * Class to base the UI's off of
 */
public class ImageSliderUI extends SliderUI implements InputProcessor{

    private static final String TAG = "IMAGESLIDERUI";

    private ImageSlider imageSlider;

    private Table dotLayout;

    public ImageSliderUI(Skin skin, ImageSlider slider){
        this(skin);
        imageSlider = slider;
    }

    public ImageSliderUI(Skin skin){
        Table layout = new Table();
        Label label = new Label("STAGE", skin);
        layout.add(label).colspan(2);
        //Create buttons
        Button leftButton = new Button(skin);
        Button rightButton = new Button(skin);

        rightButton.addListener(new InputListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log(TAG, "Button left touched");
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log(TAG, "Button left up");
                try{
                    if(imageSlider == null){
                        throw new ImageSliderNotSetException("Image Slider has not been set for this UI to use.");
                    }else {
                        imageSlider.nextView();
                    }
                } catch (ImageSliderNotSetException e){
                    Gdx.app.error(TAG, "Exception on UI touch.", e);
                }
            }
        });

        leftButton.addListener(new InputListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log(TAG, "Button left touched");
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log(TAG, "Button left up");
                try{
                    if(imageSlider == null){
                        throw new ImageSliderNotSetException("Image Slider has not been set for this UI to use.");
                    }else {
                        imageSlider.prevView();
                    }
                } catch (ImageSliderNotSetException e){
                    Gdx.app.error(TAG, "Exception on UI touch.", e);
                }
            }
        });

        leftButton.setWidth(200f);
        rightButton.setWidth(100f);

        layout.row().expand();
        layout.add(leftButton).left().width(50f);
        layout.add(rightButton).right().width(50f);
        layout.row();

        layout.setFillParent(true);
        layout.setDebug(true);

        this.addActor(layout);
    }

    public void setImageSlider(ImageSlider imageSlider) {
        this.imageSlider = imageSlider;
    }

    public void updateSize() {
        //Create Dots
        Table layout = (Table)this.getActors().get(0);
        if(dotLayout == null){
            dotLayout = new Table();
        }
        Texture img = new Texture("lizard_minion.png");
        if(imageSlider != null) {
            Image dot = new Image(img);
            dotLayout.add(dot).width(10f).height(10f);
            layout.add(dotLayout).center().colspan(layout.getColumns());
            this.clear();
            this.addActor(layout);
        }
    }
}
