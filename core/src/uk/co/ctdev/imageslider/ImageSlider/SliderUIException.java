package uk.co.ctdev.imageslider.ImageSlider;

/**
 * Created by carl on 01/11/2016.
 */
public class SliderUIException extends Throwable {
    public SliderUIException() {
        super();
    }

    public SliderUIException(String message) {
        super(message);
    }

    public SliderUIException(String message, Throwable cause) {
        super(message, cause);
    }

    public SliderUIException(Throwable cause) {
        super(cause);
    }

    protected SliderUIException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
