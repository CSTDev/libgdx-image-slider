package uk.co.ctdev.imageslider.ImageSlider;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to base the UI's off of
 */
public class ImageSliderUI extends SliderUI implements InputProcessor{

    private static final String TAG = "IMAGESLIDERUI";

    private ImageSlider imageSlider;
    private List<Button> dots;

    private Table dotLayout;
    private Skin skin;

    public ImageSliderUI(Skin skin, ImageSlider slider){
        this(skin);
        imageSlider = slider;
    }

    public ImageSliderUI(Skin skin){
        this.skin = skin;
        Table layout = new Table();
        Label label = new Label("STAGE", skin);
        layout.add(label).colspan(2);
        //Create buttons
        Button leftButton = new Button(this.skin);
        Button rightButton = new Button(this.skin);

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
            dotLayout.addAction(Actions.fadeOut(5f));
            dotLayout.setName("dots");
            layout.add(dotLayout).center().colspan(layout.getColumns());
            dots = new ArrayList<Button>();
        } else {
            dotLayout = layout.findActor("dots");
        }

        if(imageSlider != null) {
            final Button lButton = new Button(skin, "toggle");
            dots.add(lButton);

            lButton.addListener(new InputListener(){

                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }

                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    imageSlider.goToView(dots.indexOf(lButton));
                }
            });

            if(dots.size() == 1){
                lButton.setChecked(true);
            }
            dotLayout.add(lButton).width(30f).height(30f);

            this.clear();
            this.addActor(layout);
        }
    }

    @Override
    public void setCurrentView(int currentView) {
        Table layout = (Table)this.getActors().get(0);
        Table dotTable = layout.findActor("dots");
        dotTable.addAction(Actions.alpha(1));
        dotTable.addAction(Actions.fadeOut(5f));
        ((Button)dotTable.getCells().get(currentView).getActor()).setChecked(true);

        //Reset previous
        for(Cell c : dotTable.getCells()){
            if(c.getColumn() != currentView){
                ((Button)c.getActor()).setChecked(false);
            }
        }

    }

}
