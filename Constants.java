/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.graphics.PorterDuff
 *  android.graphics.PorterDuff$Mode
 *  android.graphics.drawable.Drawable
 *  android.view.MotionEvent
 *  android.view.View
 *  android.view.View$OnTouchListener
 *  java.lang.Object
 */
package cn.pedant.SweetAlert;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;

public class Constants {
    public static final View.OnTouchListener FOCUS_TOUCH_LISTENER = new View.OnTouchListener(){

        public boolean onTouch(View view, MotionEvent motionEvent) {
            block2 : {
                Drawable drawable2;
                block0 : {
                    block1 : {
                        drawable2 = view.getBackground();
                        int n = motionEvent.getAction();
                        if (n == 0) break block0;
                        if (n == 1 || n == 3) break block1;
                        if (n == 11) break block0;
                        break block2;
                    }
                    drawable2.clearColorFilter();
                    view.invalidate();
                    break block2;
                }
                drawable2.setColorFilter(536870912, PorterDuff.Mode.SRC_ATOP);
                view.invalidate();
            }
            return false;
        }
    };

}

