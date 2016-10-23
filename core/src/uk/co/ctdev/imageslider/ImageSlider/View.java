package uk.co.ctdev.imageslider.ImageSlider;

import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class View extends Table {

    public View(Table contentTable){
        this.add(contentTable);
        this.setFillParent(true);
    }

    public View(){
        this.setFillParent(true);
        this.setDebug(true);
    };

}
