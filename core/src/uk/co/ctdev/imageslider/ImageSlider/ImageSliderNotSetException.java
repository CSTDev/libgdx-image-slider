package uk.co.ctdev.imageslider.ImageSlider;

/**
 * Created by carl on 28/10/2016.
 */
public class ImageSliderNotSetException extends Throwable {
    public ImageSliderNotSetException() {
        super();
    }

    public ImageSliderNotSetException(String message) {
        super(message);
    }

    public ImageSliderNotSetException(String message, Throwable cause) {
        super(message, cause);
    }

    public ImageSliderNotSetException(Throwable cause) {
        super(cause);
    }

    protected ImageSliderNotSetException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
