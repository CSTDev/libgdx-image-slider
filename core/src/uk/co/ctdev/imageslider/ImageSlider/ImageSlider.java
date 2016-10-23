package uk.co.ctdev.imageslider.ImageSlider;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import java.util.ArrayList;
import java.util.List;

public abstract class ImageSlider extends Stage implements GestureListener {

    protected boolean horizontal;
    protected int currentViewId;
    protected Table views;
    protected int viewCount = 0;

    private static final String TAG = "ImageSlider";

    public ImageSlider(boolean horizontal, int width, int height){
        this.horizontal = horizontal;
        currentViewId = 0;
        this.views = new Table();
        this.views.setFillParent(true);
        this.views.setPosition(width / 2, -100);
    }

    public void addView(View view){
        viewCount++;
        views.add(view).width(Gdx.graphics.getWidth());
    }

    public void setViews(List<View> views){
        for(View v : views){
            addView(v);
        }
    }

    public void start(){

        Gdx.app.log(TAG, "Start Called");
        this.clear();
        this.addActor(views);
    }

    public abstract void nextView();


    public void setView(View v){
        this.clear();
        this.addActor(v);
    }


    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        Gdx.app.log(TAG, "Touch Down");
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        Gdx.app.log(TAG, "Tap");
        return true;
    }

    @Override
    public boolean longPress(float x, float y) {
        Gdx.app.log(TAG, "Long Press");
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        Gdx.app.log(TAG, "Fling");
        nextView();
        return true;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        Gdx.app.log(TAG, "Pan");
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }
}
