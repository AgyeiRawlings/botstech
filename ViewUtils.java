/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.graphics.drawable.Drawable
 *  android.graphics.drawable.Drawable$ConstantState
 *  android.graphics.drawable.DrawableContainer
 *  android.graphics.drawable.DrawableContainer$DrawableContainerState
 *  android.graphics.drawable.StateListDrawable
 *  android.view.View
 *  java.lang.Object
 */
package cn.pedant.SweetAlert;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.StateListDrawable;
import android.view.View;

public class ViewUtils {
    static Drawable[] getDrawable(View view) {
        DrawableContainer.DrawableContainerState drawableContainerState = (DrawableContainer.DrawableContainerState)((StateListDrawable)view.getBackground()).getConstantState();
        if (drawableContainerState != null) {
            return drawableContainerState.getChildren();
        }
        return null;
    }
}

