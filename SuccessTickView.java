/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.graphics.Canvas
 *  android.graphics.Paint
 *  android.graphics.RectF
 *  android.util.AttributeSet
 *  android.util.DisplayMetrics
 *  android.view.View
 *  android.view.animation.Animation
 *  android.view.animation.Transformation
 */
package cn.pedant.SweetAlert;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import cn.pedant.SweetAlert.R;

public class SuccessTickView
extends View {
    private final float CONST_LEFT_RECT_W = this.dip2px(15.0f);
    private final float CONST_RADIUS = this.dip2px(1.2f);
    private final float CONST_RECT_WEIGHT = this.dip2px(3.0f);
    private final float CONST_RIGHT_RECT_W;
    private final float MAX_RIGHT_RECT_W;
    private final float MIN_LEFT_RECT_W;
    private float mDensity = -1.0f;
    private boolean mLeftRectGrowMode;
    private float mLeftRectWidth;
    private float mMaxLeftRectWidth;
    private Paint mPaint;
    private float mRightRectWidth;

    public SuccessTickView(Context context) {
        float f;
        super(context);
        this.CONST_RIGHT_RECT_W = f = this.dip2px(25.0f);
        this.MIN_LEFT_RECT_W = this.dip2px(3.3f);
        this.MAX_RIGHT_RECT_W = f + this.dip2px(6.7f);
        this.init();
    }

    public SuccessTickView(Context context, AttributeSet attributeSet) {
        float f;
        super(context, attributeSet);
        this.CONST_RIGHT_RECT_W = f = this.dip2px(25.0f);
        this.MIN_LEFT_RECT_W = this.dip2px(3.3f);
        this.MAX_RIGHT_RECT_W = f + this.dip2px(6.7f);
        this.init();
    }

    private void init() {
        Paint paint;
        this.mPaint = paint = new Paint();
        paint.setColor(this.getResources().getColor(R.color.success_stroke_color));
        this.mLeftRectWidth = this.CONST_LEFT_RECT_W;
        this.mRightRectWidth = this.CONST_RIGHT_RECT_W;
        this.mLeftRectGrowMode = false;
    }

    public float dip2px(float f) {
        if (this.mDensity == -1.0f) {
            this.mDensity = this.getResources().getDisplayMetrics().density;
        }
        return 0.5f + f * this.mDensity;
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        int n = this.getWidth();
        int n2 = this.getHeight();
        canvas.rotate(45.0f, (float)(n / 2), (float)(n2 / 2));
        int n3 = (int)((double)n / 1.2);
        int n4 = (int)((double)n2 / 1.4);
        float f = n3;
        this.mMaxLeftRectWidth = (f + this.CONST_LEFT_RECT_W) / 2.0f + this.CONST_RECT_WEIGHT - 1.0f;
        RectF rectF = new RectF();
        if (this.mLeftRectGrowMode) {
            rectF.left = 0.0f;
            rectF.right = rectF.left + this.mLeftRectWidth;
            rectF.top = ((float)n4 + this.CONST_RIGHT_RECT_W) / 2.0f;
            rectF.bottom = rectF.top + this.CONST_RECT_WEIGHT;
        } else {
            rectF.right = (f + this.CONST_LEFT_RECT_W) / 2.0f + this.CONST_RECT_WEIGHT - 1.0f;
            rectF.left = rectF.right - this.mLeftRectWidth;
            rectF.top = ((float)n4 + this.CONST_RIGHT_RECT_W) / 2.0f;
            rectF.bottom = rectF.top + this.CONST_RECT_WEIGHT;
        }
        float f2 = this.CONST_RADIUS;
        canvas.drawRoundRect(rectF, f2, f2, this.mPaint);
        RectF rectF2 = new RectF();
        rectF2.bottom = ((float)n4 + this.CONST_RIGHT_RECT_W) / 2.0f + this.CONST_RECT_WEIGHT - 1.0f;
        rectF2.left = (f + this.CONST_LEFT_RECT_W) / 2.0f;
        rectF2.right = rectF2.left + this.CONST_RECT_WEIGHT;
        rectF2.top = rectF2.bottom - this.mRightRectWidth;
        float f3 = this.CONST_RADIUS;
        canvas.drawRoundRect(rectF2, f3, f3, this.mPaint);
    }

    public void startTickAnim() {
        this.mLeftRectWidth = 0.0f;
        this.mRightRectWidth = 0.0f;
        this.invalidate();
        Animation animation = new Animation(){

            protected void applyTransformation(float f, Transformation transformation) {
                super.applyTransformation(f, transformation);
                double d = f;
                if (0.54 < d && 0.7 >= d) {
                    SuccessTickView.this.mLeftRectGrowMode = true;
                    SuccessTickView successTickView = SuccessTickView.this;
                    successTickView.mLeftRectWidth = successTickView.mMaxLeftRectWidth * ((f - 0.54f) / 0.16f);
                    if (0.65 < d) {
                        SuccessTickView successTickView2 = SuccessTickView.this;
                        successTickView2.mRightRectWidth = successTickView2.MAX_RIGHT_RECT_W * ((f - 0.65f) / 0.19f);
                    }
                    SuccessTickView.this.invalidate();
                    return;
                }
                if (0.7 < d && 0.84 >= d) {
                    SuccessTickView.this.mLeftRectGrowMode = false;
                    SuccessTickView successTickView = SuccessTickView.this;
                    successTickView.mLeftRectWidth = successTickView.mMaxLeftRectWidth * (1.0f - (f - 0.7f) / 0.14f);
                    SuccessTickView successTickView3 = SuccessTickView.this;
                    float f2 = successTickView3.mLeftRectWidth < SuccessTickView.this.MIN_LEFT_RECT_W ? SuccessTickView.this.MIN_LEFT_RECT_W : SuccessTickView.this.mLeftRectWidth;
                    successTickView3.mLeftRectWidth = f2;
                    SuccessTickView successTickView4 = SuccessTickView.this;
                    successTickView4.mRightRectWidth = successTickView4.MAX_RIGHT_RECT_W * ((f - 0.65f) / 0.19f);
                    SuccessTickView.this.invalidate();
                    return;
                }
                if (0.84 < d && 1.0f >= f) {
                    SuccessTickView.this.mLeftRectGrowMode = false;
                    SuccessTickView successTickView = SuccessTickView.this;
                    float f3 = successTickView.MIN_LEFT_RECT_W;
                    float f4 = SuccessTickView.this.CONST_LEFT_RECT_W - SuccessTickView.this.MIN_LEFT_RECT_W;
                    float f5 = (f - 0.84f) / 0.16f;
                    successTickView.mLeftRectWidth = f3 + f4 * f5;
                    SuccessTickView successTickView5 = SuccessTickView.this;
                    successTickView5.mRightRectWidth = successTickView5.CONST_RIGHT_RECT_W + (SuccessTickView.this.MAX_RIGHT_RECT_W - SuccessTickView.this.CONST_RIGHT_RECT_W) * (1.0f - f5);
                    SuccessTickView.this.invalidate();
                }
            }
        };
        animation.setDuration(750L);
        animation.setStartOffset(100L);
        this.startAnimation(animation);
    }

}

