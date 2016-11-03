package uk.co.ctdev.imageslider.ImageSlider;

import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Created by carl on 01/11/2016.
 */
public abstract class SliderUI extends Stage {

    public abstract void updateSize();

    public abstract void setCurrentView(int currentView);
}
