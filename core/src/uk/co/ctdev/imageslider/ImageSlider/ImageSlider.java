package uk.co.ctdev.imageslider.ImageSlider;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public abstract class ImageSlider extends Stage implements GestureListener {

    protected int currentViewId = 0;
    protected ScrollPane contentPane;
    protected int viewCount = 0;
    protected InputMultiplexer inputHandler;
    protected SliderUI ui;

    protected Table contentTable;

    private int width;
    private int height;

    private static final String TAG = "ImageSlider";

    public ImageSlider(int width, int height){
        this.width = width;
        this.height = height;
    }

    public ImageSlider(Skin skin, int width, int height, Class<? extends SliderUI> ui) throws SliderUIException{
        this.width = width;
        this.height = height;
        currentViewId = 0;
        try {
            this.ui = (SliderUI) ui.getConstructor(Skin.class, ImageSlider.class).newInstance(skin, this);
            inputHandler = new InputMultiplexer(this.ui);
            inputHandler.addProcessor(this);
        } catch (InstantiationException e) {
            log(e);
        } catch (IllegalAccessException e) {
            log(e);
        } catch (NoSuchMethodException e) {
            log(e);
        } catch (InvocationTargetException e) {
            log(e);
        }
    }

    private void log(Exception e) throws SliderUIException {
        Gdx.app.error(TAG, "Unable to initialise ui.", e);
        throw new SliderUIException("Unable to initialise ui.", e);
    }

    public abstract void addView(Table view);

    public void setViews(List<Table> views){
        for(Table v : views){
            addView(v);
        }
    }

    public int getViewCount(){
        return viewCount;
    }

    public abstract void nextView();

    public abstract void prevView();

    @Override
    public void draw(){
        super.draw();
        if(ui != null) {
            ui.draw();
        }
    }

    public InputMultiplexer getInputProcessor(){
        return inputHandler;
    }

    public abstract void goToView(int viewId);
}
