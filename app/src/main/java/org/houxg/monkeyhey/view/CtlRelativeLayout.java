package org.houxg.monkeyhey.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * Des:
 * Created by houxg on 2015/2/15.
 */
public class CtlRelativeLayout extends RelativeLayout {
    public CtlRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CtlRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    boolean canTouch = true;

    public void canTouch(boolean canTouch) {
        this.canTouch = canTouch;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!canTouch) {
            return true;
        }
        return super.onTouchEvent(event);
    }
}
