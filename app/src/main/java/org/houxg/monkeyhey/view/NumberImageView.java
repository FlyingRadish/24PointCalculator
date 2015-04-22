package org.houxg.monkeyhey.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Des:
 * Created by houxg on 2015/3/25.
 */
public class NumberImageView extends ImageView {

    int mDx = 0;
    int mDy = 0;
    boolean needDrawNumber = false;
    int mNumber = 0;
    int mNumberTextSize = 28;
    int mNumberRadius = 18;
    int mOverrideRadius = 10;

    public NumberImageView(Context context) {
        super(context);
    }

    public NumberImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NumberImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setNormalImageResources(int resId) {
        setImageResource(resId);
        needDrawNumber = false;
    }

    @Override
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        needDrawNumber = false;
    }

    @Override
    public void setImageBitmap(Bitmap bm) {
        super.setImageBitmap(bm);
        needDrawNumber = false;
    }

    public void setNumberImageResources(int resId, int number, int dx, int dy) {
        setImageResource(resId);
        this.mDx = dx;
        this.mDy = dy;
        needDrawNumber = true;
        this.mNumber = number;
        invalidate();
    }

    public int getmNumber() {
        return mNumber;
    }

    public void setmNumber(int mNumber) {
        this.mNumber = mNumber;
        invalidate();
    }

    public void setmNumberTextSize(int mNumberTextSize) {
        this.mNumberTextSize = mNumberTextSize;
        invalidate();
    }

    public void setmNumberRadius(int mNumberRadius) {
        this.mNumberRadius = mNumberRadius;
        invalidate();
    }

    public void setmOverrideRadius(int mOverrideRadius) {
        this.mOverrideRadius = mOverrideRadius;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (needDrawNumber) {
            Drawable d = getDrawable();
            int dWidth = d.getIntrinsicWidth();
            int dHeight = d.getIntrinsicHeight();

            int dx = getWidth() / 2 + dWidth / 2 + mDx;
            int dy = getHeight() / 2 - dHeight / 2 + mDy;
            if (mNumber > 99) {
                drawCircle(canvas, dx, dy, mOverrideRadius);
            } else {
                drawCircle(canvas, dx, dy, mNumberRadius);
                drawNumber(canvas, dx, dy);
            }
        }
    }

    private void drawCircle(Canvas canvas, int dx, int dy, int radius) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.RED);
        canvas.drawCircle(dx, dy, radius, paint);
    }


    private void drawNumber(Canvas canvas, int dx, int dy) {
        TextPaint paint = new TextPaint(Paint.ANTI_ALIAS_FLAG | Paint.DEV_KERN_TEXT_FLAG);
        paint.setColor(Color.WHITE);
        paint.setTextSize(mNumberTextSize);
        Rect textBound = new Rect();
        String num = String.valueOf(mNumber);
        paint.getTextBounds(num, 0, num.length(), textBound);
        canvas.drawText(num, dx - textBound.width() / 2, dy + textBound.height() / 2, paint);
    }
}
