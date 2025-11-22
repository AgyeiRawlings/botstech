/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  com.pnikosis.materialishprogress.ProgressWheel
 *  java.lang.Object
 */
package cn.pedant.SweetAlert;

import android.content.Context;
import android.content.res.Resources;
import cn.pedant.SweetAlert.R;
import com.pnikosis.materialishprogress.ProgressWheel;

public class ProgressHelper {
    private int mBarColor;
    private int mBarWidth;
    private int mCircleRadius;
    private boolean mIsInstantProgress;
    private float mProgressVal;
    private ProgressWheel mProgressWheel;
    private int mRimColor;
    private int mRimWidth;
    private float mSpinSpeed = 0.75f;
    private boolean mToSpin = true;

    public ProgressHelper(Context context) {
        this.mBarWidth = 1 + context.getResources().getDimensionPixelSize(R.dimen.common_circle_width);
        this.mBarColor = context.getResources().getColor(R.color.success_stroke_color);
        this.mRimWidth = 0;
        this.mRimColor = 0;
        this.mIsInstantProgress = false;
        this.mProgressVal = -1.0f;
        this.mCircleRadius = context.getResources().getDimensionPixelOffset(R.dimen.progress_circle_radius);
    }

    private void updatePropsIfNeed() {
        ProgressWheel progressWheel = this.mProgressWheel;
        if (progressWheel != null) {
            if (!this.mToSpin && progressWheel.isSpinning()) {
                this.mProgressWheel.stopSpinning();
            } else if (this.mToSpin && !this.mProgressWheel.isSpinning()) {
                this.mProgressWheel.spin();
            }
            if (this.mSpinSpeed != this.mProgressWheel.getSpinSpeed()) {
                this.mProgressWheel.setSpinSpeed(this.mSpinSpeed);
            }
            if (this.mBarWidth != this.mProgressWheel.getBarWidth()) {
                this.mProgressWheel.setBarWidth(this.mBarWidth);
            }
            if (this.mBarColor != this.mProgressWheel.getBarColor()) {
                this.mProgressWheel.setBarColor(this.mBarColor);
            }
            if (this.mRimWidth != this.mProgressWheel.getRimWidth()) {
                this.mProgressWheel.setRimWidth(this.mRimWidth);
            }
            if (this.mRimColor != this.mProgressWheel.getRimColor()) {
                this.mProgressWheel.setRimColor(this.mRimColor);
            }
            if (this.mProgressVal != this.mProgressWheel.getProgress()) {
                if (this.mIsInstantProgress) {
                    this.mProgressWheel.setInstantProgress(this.mProgressVal);
                } else {
                    this.mProgressWheel.setProgress(this.mProgressVal);
                }
            }
            if (this.mCircleRadius != this.mProgressWheel.getCircleRadius()) {
                this.mProgressWheel.setCircleRadius(this.mCircleRadius);
            }
        }
    }

    public int getBarColor() {
        return this.mBarColor;
    }

    public int getBarWidth() {
        return this.mBarWidth;
    }

    public int getCircleRadius() {
        return this.mCircleRadius;
    }

    public float getProgress() {
        return this.mProgressVal;
    }

    public ProgressWheel getProgressWheel() {
        return this.mProgressWheel;
    }

    public int getRimColor() {
        return this.mRimColor;
    }

    public int getRimWidth() {
        return this.mRimWidth;
    }

    public float getSpinSpeed() {
        return this.mSpinSpeed;
    }

    public boolean isSpinning() {
        return this.mToSpin;
    }

    public void resetCount() {
        ProgressWheel progressWheel = this.mProgressWheel;
        if (progressWheel != null) {
            progressWheel.resetCount();
        }
    }

    public void setBarColor(int n) {
        this.mBarColor = n;
        this.updatePropsIfNeed();
    }

    public void setBarWidth(int n) {
        this.mBarWidth = n;
        this.updatePropsIfNeed();
    }

    public void setCircleRadius(int n) {
        this.mCircleRadius = n;
        this.updatePropsIfNeed();
    }

    public void setInstantProgress(float f) {
        this.mProgressVal = f;
        this.mIsInstantProgress = true;
        this.updatePropsIfNeed();
    }

    public void setProgress(float f) {
        this.mIsInstantProgress = false;
        this.mProgressVal = f;
        this.updatePropsIfNeed();
    }

    public void setProgressWheel(ProgressWheel progressWheel) {
        this.mProgressWheel = progressWheel;
        this.updatePropsIfNeed();
    }

    public void setRimColor(int n) {
        this.mRimColor = n;
        this.updatePropsIfNeed();
    }

    public void setRimWidth(int n) {
        this.mRimWidth = n;
        this.updatePropsIfNeed();
    }

    public void setSpinSpeed(float f) {
        this.mSpinSpeed = f;
        this.updatePropsIfNeed();
    }

    public void spin() {
        this.mToSpin = true;
        this.updatePropsIfNeed();
    }

    public void stopSpinning() {
        this.mToSpin = false;
        this.updatePropsIfNeed();
    }
}

