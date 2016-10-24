package uk.co.ctdev.imageslider.ImageSlider;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import java.util.ArrayList;
import java.util.List;

public abstract class ImageSlider extends Stage implements GestureListener {

    protected boolean horizontal;
    protected int currentViewId = 0;
    protected ScrollPane contentPane;
    protected int viewCount = 0;

    protected Table contentTable;

    private int width;
    private int height;

    private static final String TAG = "ImageSlider";

    public ImageSlider(boolean horizontal, int width, int height){
        this.horizontal = horizontal;
        this.width = width;
        this.height = height;
        currentViewId = 0;
    }

    public void addView(Actor view){
        viewCount++;
        if(contentPane == null){
            contentTable = new Table();
            contentTable.setDebug(true);
            contentTable.add(view).width(this.width).height(this.height);
            contentPane = new ScrollPane(contentTable);
            contentPane.setFillParent(true);
            contentPane.setOverscroll(false, false);
        }else{
            contentTable.add(view).width(this.width).height(this.height);
            contentPane.setWidget(contentTable);
        }
        this.clear();
        this.addActor(contentPane);
    }

    public void setViews(List<View> views){
        for(View v : views){
            addView(v);
        }
    }

    public void start(){
        Gdx.app.log(TAG, "Start Called");
    }

    public abstract void nextView();


    public void setView(View v){
        this.clear();
        this.addActor(v);
    }

}
