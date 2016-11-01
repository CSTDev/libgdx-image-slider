package uk.co.ctdev.imageslider.ImageSlider;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class SimpleImageSlider extends ImageSlider {

    private static final String TAG = "SIMPLEIMAGESLIDER";

    private int width;
    private int height;

    public SimpleImageSlider(Skin skin, int width, int height, Class ui) {
        super(skin, width, height, ui);
        this.width = width;
        this.height = height;

        contentTable = new Table();
        contentPane = new ScrollPane(contentTable);
        contentPane.setFillParent(true);
        contentPane.setOverscroll(false, false);

    }

    @Override
    public void addView(Table view) {
        viewCount++;
        contentTable.add(view).width(this.width).height(this.height);
        contentPane.setWidget(contentTable);
        this.clear();
        this.addActor(contentPane);
        if(ui != null) {
            ui.updateSize();
        }
    }



    @Override
    public void nextView() {
        Gdx.app.log(TAG, "Forward");
        currentViewId++;
        contentPane.setScrollX(currentViewId == 0 ? 640 : currentViewId * 640);
    }

    @Override
    public void prevView() {
        Gdx.app.log(TAG, "Back");
        currentViewId--;
        contentPane.setScrollX(currentViewId == 0 ? 0 : currentViewId * 640);
    }

    @Override
    public boolean touchUp(int x, int y, int pointer, int button){
        float scrollX = contentPane.getScrollX();
        if(scrollX % 640 == 0){
            Gdx.app.log(TAG, "Not handled");
            return false;
        }else {
            Gdx.app.log(TAG, "Middle of this view:" + ((width * currentViewId) + (width / 2)));
            Gdx.app.log(TAG, "Scroll X:" + scrollX);
            if ((scrollX < (width * currentViewId) + (width / 2)) &&
                    scrollX > (width * currentViewId) - (width / 2)) {
                Gdx.app.log(TAG, "Stay");
                contentPane.setScrollX(currentViewId * 640);
            } else if (scrollX < (width * currentViewId) - (width / 2)) {
                Gdx.app.log(TAG, "Back");
                prevView();
            } else {
                Gdx.app.log(TAG, "Forward");
                nextView();
            }

            Gdx.app.log(TAG, "View Id:" + currentViewId);
            Gdx.app.log(TAG, "Handled");
            return true;
        }
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
