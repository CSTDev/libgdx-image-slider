package uk.co.ctdev.imageslider.ImageSlider;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class View extends Table {

    private static final String TAG = "VIEW";

    public View(){
    }

    public View(Actor content, int width, int height){
        this.add(content).expand().fill();
    }

}
