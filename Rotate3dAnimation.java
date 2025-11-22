/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.TypedArray
 *  android.graphics.Camera
 *  android.graphics.Matrix
 *  android.util.AttributeSet
 *  android.util.TypedValue
 *  android.view.animation.Animation
 *  android.view.animation.Transformation
 *  java.lang.Object
 */
package cn.pedant.SweetAlert;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Camera;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import cn.pedant.SweetAlert.R;

public class Rotate3dAnimation
extends Animation {
    public static final int ROLL_BY_X = 0;
    public static final int ROLL_BY_Y = 1;
    public static final int ROLL_BY_Z = 2;
    private Camera mCamera;
    private float mFromDegrees;
    private float mPivotX;
    private int mPivotXType;
    private float mPivotXValue;
    private float mPivotY;
    private int mPivotYType;
    private float mPivotYValue;
    private int mRollType;
    private float mToDegrees;

    public Rotate3dAnimation(int n, float f, float f2) {
        this.mPivotXType = 0;
        this.mPivotYType = 0;
        this.mPivotXValue = 0.0f;
        this.mPivotYValue = 0.0f;
        this.mRollType = n;
        this.mFromDegrees = f;
        this.mToDegrees = f2;
        this.mPivotX = 0.0f;
        this.mPivotY = 0.0f;
    }

    public Rotate3dAnimation(int n, float f, float f2, float f3, float f4) {
        this.mRollType = n;
        this.mFromDegrees = f;
        this.mToDegrees = f2;
        this.mPivotXType = 0;
        this.mPivotYType = 0;
        this.mPivotXValue = f3;
        this.mPivotYValue = f4;
        this.initializePivotPoint();
    }

    public Rotate3dAnimation(int n, float f, float f2, int n2, float f3, int n3, float f4) {
        this.mRollType = n;
        this.mFromDegrees = f;
        this.mToDegrees = f2;
        this.mPivotXValue = f3;
        this.mPivotXType = n2;
        this.mPivotYValue = f4;
        this.mPivotYType = n3;
        this.initializePivotPoint();
    }

    public Rotate3dAnimation(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mPivotXType = 0;
        this.mPivotYType = 0;
        this.mPivotXValue = 0.0f;
        this.mPivotYValue = 0.0f;
        TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.Rotate3dAnimation);
        this.mFromDegrees = typedArray.getFloat(R.styleable.Rotate3dAnimation_fromDeg, 0.0f);
        this.mToDegrees = typedArray.getFloat(R.styleable.Rotate3dAnimation_toDeg, 0.0f);
        this.mRollType = typedArray.getInt(R.styleable.Rotate3dAnimation_rollType, 0);
        Description description = this.parseValue(typedArray.peekValue(R.styleable.Rotate3dAnimation_customPivotX));
        this.mPivotXType = description.type;
        this.mPivotXValue = description.value;
        Description description2 = this.parseValue(typedArray.peekValue(R.styleable.Rotate3dAnimation_customPivotY));
        this.mPivotYType = description2.type;
        this.mPivotYValue = description2.value;
        typedArray.recycle();
        this.initializePivotPoint();
    }

    private void initializePivotPoint() {
        if (this.mPivotXType == 0) {
            this.mPivotX = this.mPivotXValue;
        }
        if (this.mPivotYType == 0) {
            this.mPivotY = this.mPivotYValue;
        }
    }

    protected void applyTransformation(float f, Transformation transformation) {
        float f2 = this.mFromDegrees;
        float f3 = f2 + f * (this.mToDegrees - f2);
        Matrix matrix = transformation.getMatrix();
        this.mCamera.save();
        int n = this.mRollType;
        if (n != 0) {
            if (n != 1) {
                if (n == 2) {
                    this.mCamera.rotateZ(f3);
                }
            } else {
                this.mCamera.rotateY(f3);
            }
        } else {
            this.mCamera.rotateX(f3);
        }
        this.mCamera.getMatrix(matrix);
        this.mCamera.restore();
        matrix.preTranslate(-this.mPivotX, -this.mPivotY);
        matrix.postTranslate(this.mPivotX, this.mPivotY);
    }

    public void initialize(int n, int n2, int n3, int n4) {
        super.initialize(n, n2, n3, n4);
        this.mCamera = new Camera();
        this.mPivotX = this.resolveSize(this.mPivotXType, this.mPivotXValue, n, n3);
        this.mPivotY = this.resolveSize(this.mPivotYType, this.mPivotYValue, n2, n4);
    }

    Description parseValue(TypedValue typedValue) {
        Description description = new Description();
        if (typedValue == null) {
            description.type = 0;
            description.value = 0.0f;
        } else {
            if (typedValue.type == 6) {
                int n = 15 & typedValue.data;
                int n2 = 1;
                if (n == n2) {
                    n2 = 2;
                }
                description.type = n2;
                description.value = TypedValue.complexToFloat((int)typedValue.data);
                return description;
            }
            if (typedValue.type == 4) {
                description.type = 0;
                description.value = typedValue.getFloat();
                return description;
            }
            if (typedValue.type >= 16 && typedValue.type <= 31) {
                description.type = 0;
                description.value = typedValue.data;
                return description;
            }
        }
        description.type = 0;
        description.value = 0.0f;
        return description;
    }

    protected static class Description {
        public int type;
        public float value;

        protected Description() {
        }
    }

}

