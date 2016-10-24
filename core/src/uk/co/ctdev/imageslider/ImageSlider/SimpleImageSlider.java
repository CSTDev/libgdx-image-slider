package uk.co.ctdev.imageslider.ImageSlider;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class SimpleImageSlider extends ImageSlider {

    private static final String TAG = "SIMPLEIMAGESLIDER";

    private int width;
    private int height;

    public SimpleImageSlider(boolean horizontal, int width, int height) {
        super(horizontal, width, height);
        this.width = width;
        this.height = height;
    }

    @Override
    public void nextView() {
        //views.setPosition(-Gdx.graphics.getWidth() / 1.3f, -100);
    }

    @Override
    public boolean touchUp(int x, int y, int pointer, int button){
        float scrollX = contentPane.getScrollX();

        Gdx.app.log(TAG, "Middle of this view:" + ((width * currentViewId) + ( width / 2 )));
        Gdx.app.log(TAG, "Scroll X:" + scrollX);
        if((scrollX < (width * currentViewId) + ( width / 2 )) &&
                scrollX > (width * currentViewId) - (width / 2)) {
            Gdx.app.log(TAG, "Stay");
            contentPane.setScrollX(currentViewId * 640);
        }else if(scrollX < (width * currentViewId) - (width / 2)){
            Gdx.app.log(TAG, "Back");
            currentViewId--;
            contentPane.setScrollX(currentViewId == 0 ? 0 : currentViewId * 640);
        }else{
            Gdx.app.log(TAG, "Forward");
            currentViewId++;
            contentPane.setScrollX(currentViewId == 0 ? 640 : currentViewId * 640);
        }

        Gdx.app.log(TAG, "View Id:" + currentViewId);

        return true;
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
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
