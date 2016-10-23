package uk.co.ctdev.imageslider.ImageSlider;


import com.badlogic.gdx.Gdx;

public class SimpleImageSlider extends ImageSlider {

    public SimpleImageSlider(boolean horizontal, int width, int height) {
        super(horizontal, width, height);
    }

    @Override
    public void nextView() {
        views.setPosition(-Gdx.graphics.getWidth() / 1.3f, -100);
    }
}
