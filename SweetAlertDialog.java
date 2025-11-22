/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.app.Dialog
 *  android.content.Context
 *  android.content.res.Resources
 *  android.graphics.Color
 *  android.graphics.drawable.Drawable
 *  android.graphics.drawable.GradientDrawable
 *  android.os.Bundle
 *  android.os.IBinder
 *  android.text.Html
 *  android.util.DisplayMetrics
 *  android.util.TypedValue
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.View$OnTouchListener
 *  android.view.ViewGroup
 *  android.view.Window
 *  android.view.WindowManager
 *  android.view.WindowManager$LayoutParams
 *  android.view.animation.Animation
 *  android.view.animation.Animation$AnimationListener
 *  android.view.animation.AnimationSet
 *  android.view.animation.Transformation
 *  android.view.inputmethod.InputMethodManager
 *  android.widget.Button
 *  android.widget.FrameLayout
 *  android.widget.ImageView
 *  android.widget.LinearLayout
 *  android.widget.TextView
 *  com.pnikosis.materialishprogress.ProgressWheel
 *  java.lang.CharSequence
 *  java.lang.Float
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.Runnable
 *  java.lang.String
 *  java.util.List
 */
package cn.pedant.SweetAlert;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.IBinder;
import android.text.Html;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.Transformation;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.pedant.SweetAlert.Constants;
import cn.pedant.SweetAlert.OptAnimationLoader;
import cn.pedant.SweetAlert.ProgressHelper;
import cn.pedant.SweetAlert.R;
import cn.pedant.SweetAlert.SuccessTickView;
import cn.pedant.SweetAlert.ViewUtils;
import com.pnikosis.materialishprogress.ProgressWheel;
import java.util.List;

public class SweetAlertDialog
extends Dialog
implements View.OnClickListener {
    public static final int BUTTON_CANCEL = -2;
    public static final int BUTTON_CONFIRM = -1;
    public static final int CUSTOM_IMAGE_TYPE = 4;
    public static boolean DARK_STYLE = false;
    public static final int ERROR_TYPE = 1;
    public static final int NORMAL_TYPE = 0;
    public static final int PROGRESS_TYPE = 5;
    public static final int SUCCESS_TYPE = 2;
    public static final int WARNING_TYPE = 3;
    private int contentTextSize;
    private final float defStrokeWidth;
    private int mAlertType;
    private LinearLayout mButtonsContainer;
    private Button mCancelButton;
    private Integer mCancelButtonBackgroundColor;
    private Integer mCancelButtonTextColor;
    private OnSweetClickListener mCancelClickListener;
    private String mCancelText;
    private boolean mCloseFromCancel;
    private Button mConfirmButton;
    private Integer mConfirmButtonBackgroundColor;
    private Integer mConfirmButtonTextColor;
    private OnSweetClickListener mConfirmClickListener;
    private String mConfirmText;
    private String mContentText;
    private TextView mContentTextView;
    private ImageView mCustomImage;
    private Drawable mCustomImgDrawable;
    private View mCustomView;
    private FrameLayout mCustomViewContainer;
    private View mDialogView;
    private FrameLayout mErrorFrame;
    private Animation mErrorInAnim;
    private ImageView mErrorX;
    private AnimationSet mErrorXInAnim;
    private boolean mHideConfirmButton;
    private boolean mHideKeyBoardOnDismiss;
    private AnimationSet mModalInAnim;
    private AnimationSet mModalOutAnim;
    private Button mNeutralButton;
    private Integer mNeutralButtonBackgroundColor;
    private Integer mNeutralButtonTextColor;
    private OnSweetClickListener mNeutralClickListener;
    private String mNeutralText;
    private Animation mOverlayOutAnim;
    private FrameLayout mProgressFrame;
    private ProgressHelper mProgressHelper;
    private boolean mShowCancel;
    private boolean mShowContent;
    private Animation mSuccessBowAnim;
    private FrameLayout mSuccessFrame;
    private AnimationSet mSuccessLayoutAnimSet;
    private View mSuccessLeftMask;
    private View mSuccessRightMask;
    private SuccessTickView mSuccessTick;
    private String mTitleText;
    private TextView mTitleTextView;
    private FrameLayout mWarningFrame;
    private float strokeWidth;

    public SweetAlertDialog(Context context) {
        this(context, 0);
    }

    public SweetAlertDialog(Context context, int n) {
        AnimationSet animationSet;
        float f;
        Animation animation;
        int n2 = DARK_STYLE ? R.style.alert_dialog_dark : R.style.alert_dialog_light;
        super(context, n2);
        this.mHideConfirmButton = false;
        this.mHideKeyBoardOnDismiss = true;
        this.contentTextSize = 0;
        this.strokeWidth = 0.0f;
        this.setCancelable(true);
        this.setCanceledOnTouchOutside(true);
        this.defStrokeWidth = f = this.getContext().getResources().getDimension(R.dimen.buttons_stroke_width);
        this.strokeWidth = f;
        this.mProgressHelper = new ProgressHelper(context);
        this.mAlertType = n;
        this.mErrorInAnim = OptAnimationLoader.loadAnimation(this.getContext(), R.anim.error_frame_in);
        this.mErrorXInAnim = (AnimationSet)OptAnimationLoader.loadAnimation(this.getContext(), R.anim.error_x_in);
        this.mSuccessBowAnim = OptAnimationLoader.loadAnimation(this.getContext(), R.anim.success_bow_roate);
        this.mSuccessLayoutAnimSet = (AnimationSet)OptAnimationLoader.loadAnimation(this.getContext(), R.anim.success_mask_layout);
        this.mModalInAnim = (AnimationSet)OptAnimationLoader.loadAnimation(this.getContext(), R.anim.modal_in);
        this.mModalOutAnim = animationSet = (AnimationSet)OptAnimationLoader.loadAnimation(this.getContext(), R.anim.modal_out);
        animationSet.setAnimationListener(new Animation.AnimationListener(){

            public void onAnimationEnd(Animation animation) {
                SweetAlertDialog.this.mDialogView.setVisibility(8);
                if (SweetAlertDialog.this.mHideKeyBoardOnDismiss) {
                    SweetAlertDialog.this.hideSoftKeyboard();
                }
                SweetAlertDialog.this.mDialogView.post(new Runnable(){

                    public void run() {
                        if (SweetAlertDialog.this.mCloseFromCancel) {
                            SweetAlertDialog.super.cancel();
                            return;
                        }
                        SweetAlertDialog.super.dismiss();
                    }
                });
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }

        });
        this.mOverlayOutAnim = animation = new Animation(){

            protected void applyTransformation(float f, Transformation transformation) {
                WindowManager.LayoutParams layoutParams = SweetAlertDialog.this.getWindow().getAttributes();
                layoutParams.alpha = 1.0f - f;
                SweetAlertDialog.this.getWindow().setAttributes(layoutParams);
            }
        };
        animation.setDuration(120L);
    }

    private void adjustButtonContainerVisibility() {
        boolean bl;
        block1 : {
            for (int i = 0; i < this.mButtonsContainer.getChildCount(); ++i) {
                View view = this.mButtonsContainer.getChildAt(i);
                if (!(view instanceof Button) || view.getVisibility() != 0) continue;
                bl = true;
                break block1;
            }
            bl = false;
        }
        LinearLayout linearLayout = this.mButtonsContainer;
        int n = bl ? 0 : 8;
        linearLayout.setVisibility(n);
    }

    private void applyStroke() {
        if (Float.compare((float)this.defStrokeWidth, (float)this.strokeWidth) != 0) {
            Resources resources = this.getContext().getResources();
            this.setButtonBackgroundColor(this.mConfirmButton, resources.getColor(R.color.main_green_color));
            this.setButtonBackgroundColor(this.mNeutralButton, resources.getColor(R.color.main_disabled_color));
            this.setButtonBackgroundColor(this.mCancelButton, resources.getColor(R.color.red_btn_bg_color));
        }
    }

    private void changeAlertType(int n, boolean bl) {
        this.mAlertType = n;
        if (this.mDialogView != null) {
            if (!bl) {
                this.restore();
            }
            Button button = this.mConfirmButton;
            int n2 = this.mHideConfirmButton ? 8 : 0;
            button.setVisibility(n2);
            int n3 = this.mAlertType;
            if (n3 != 1) {
                if (n3 != 2) {
                    if (n3 != 3) {
                        if (n3 != 4) {
                            if (n3 == 5) {
                                this.mProgressFrame.setVisibility(0);
                                this.mConfirmButton.setVisibility(8);
                            }
                        } else {
                            this.setCustomImage(this.mCustomImgDrawable);
                        }
                    } else {
                        this.mWarningFrame.setVisibility(0);
                    }
                } else {
                    this.mSuccessFrame.setVisibility(0);
                    this.mSuccessLeftMask.startAnimation((Animation)this.mSuccessLayoutAnimSet.getAnimations().get(0));
                    this.mSuccessRightMask.startAnimation((Animation)this.mSuccessLayoutAnimSet.getAnimations().get(1));
                }
            } else {
                this.mErrorFrame.setVisibility(0);
            }
            this.adjustButtonContainerVisibility();
            if (!bl) {
                this.playAnimation();
            }
        }
    }

    private void dismissWithAnimation(boolean bl) {
        this.mCloseFromCancel = bl;
        ((ViewGroup)this.mDialogView).getChildAt(0).startAnimation(this.mOverlayOutAnim);
        this.mDialogView.startAnimation((Animation)this.mModalOutAnim);
    }

    private int genStrokeColor(int n) {
        float[] arrf = new float[3];
        Color.colorToHSV((int)n, (float[])arrf);
        arrf[2] = 0.7f * arrf[2];
        return Color.HSVToColor((float[])arrf);
    }

    private void hideSoftKeyboard() {
        InputMethodManager inputMethodManager;
        Activity activity = this.getOwnerActivity();
        if (activity != null && (inputMethodManager = (InputMethodManager)activity.getSystemService("input_method")) != null && activity.getCurrentFocus() != null) {
            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        }
    }

    private void playAnimation() {
        int n = this.mAlertType;
        if (n == 1) {
            this.mErrorFrame.startAnimation(this.mErrorInAnim);
            this.mErrorX.startAnimation((Animation)this.mErrorXInAnim);
            return;
        }
        if (n == 2) {
            this.mSuccessTick.startTickAnim();
            this.mSuccessRightMask.startAnimation(this.mSuccessBowAnim);
        }
    }

    private void restore() {
        ImageView imageView = this.mCustomImage;
        int n = 8;
        imageView.setVisibility(n);
        this.mErrorFrame.setVisibility(n);
        this.mSuccessFrame.setVisibility(n);
        this.mWarningFrame.setVisibility(n);
        this.mProgressFrame.setVisibility(n);
        Button button = this.mConfirmButton;
        if (!this.mHideConfirmButton) {
            n = 0;
        }
        button.setVisibility(n);
        this.adjustButtonContainerVisibility();
        this.mConfirmButton.setBackgroundResource(R.drawable.green_button_background);
        this.mErrorFrame.clearAnimation();
        this.mErrorX.clearAnimation();
        this.mSuccessTick.clearAnimation();
        this.mSuccessLeftMask.clearAnimation();
        this.mSuccessRightMask.clearAnimation();
    }

    private void setButtonBackgroundColor(Button button, Integer n) {
        Drawable[] arrdrawable;
        if (button != null && n != null && (arrdrawable = ViewUtils.getDrawable((View)button)) != null) {
            GradientDrawable gradientDrawable = (GradientDrawable)arrdrawable[1];
            gradientDrawable.setColor(n.intValue());
            gradientDrawable.setStroke((int)this.strokeWidth, this.genStrokeColor(n));
        }
    }

    public static int spToPx(float f, Context context) {
        return (int)TypedValue.applyDimension((int)2, (float)f, (DisplayMetrics)context.getResources().getDisplayMetrics());
    }

    public void cancel() {
        this.dismissWithAnimation(true);
    }

    public void changeAlertType(int n) {
        this.changeAlertType(n, false);
    }

    public void dismissWithAnimation() {
        this.dismissWithAnimation(false);
    }

    public int getAlertType() {
        return this.mAlertType;
    }

    public Button getButton(int n) {
        if (n != -3) {
            if (n != -2) {
                return this.mConfirmButton;
            }
            return this.mCancelButton;
        }
        return this.mNeutralButton;
    }

    public Integer getCancelButtonBackgroundColor() {
        return this.mCancelButtonBackgroundColor;
    }

    public Integer getCancelButtonTextColor() {
        return this.mCancelButtonTextColor;
    }

    public String getCancelText() {
        return this.mCancelText;
    }

    public Integer getConfirmButtonBackgroundColor() {
        return this.mConfirmButtonBackgroundColor;
    }

    public Integer getConfirmButtonTextColor() {
        return this.mConfirmButtonTextColor;
    }

    public String getConfirmText() {
        return this.mConfirmText;
    }

    public String getContentText() {
        return this.mContentText;
    }

    public int getContentTextSize() {
        return this.contentTextSize;
    }

    public Integer getNeutralButtonBackgroundColor() {
        return this.mNeutralButtonBackgroundColor;
    }

    public Integer getNeutralButtonTextColor() {
        return this.mNeutralButtonTextColor;
    }

    public ProgressHelper getProgressHelper() {
        return this.mProgressHelper;
    }

    public String getTitleText() {
        return this.mTitleText;
    }

    public SweetAlertDialog hideConfirmButton() {
        this.mHideConfirmButton = true;
        return this;
    }

    public boolean isHideKeyBoardOnDismiss() {
        return this.mHideKeyBoardOnDismiss;
    }

    public boolean isShowCancelButton() {
        return this.mShowCancel;
    }

    public boolean isShowContentText() {
        return this.mShowContent;
    }

    public void onClick(View view) {
        if (view.getId() == R.id.cancel_button) {
            OnSweetClickListener onSweetClickListener = this.mCancelClickListener;
            if (onSweetClickListener != null) {
                onSweetClickListener.onClick(this);
                return;
            }
            this.dismissWithAnimation();
            return;
        }
        if (view.getId() == R.id.confirm_button) {
            OnSweetClickListener onSweetClickListener = this.mConfirmClickListener;
            if (onSweetClickListener != null) {
                onSweetClickListener.onClick(this);
                return;
            }
            this.dismissWithAnimation();
            return;
        }
        if (view.getId() == R.id.neutral_button) {
            OnSweetClickListener onSweetClickListener = this.mNeutralClickListener;
            if (onSweetClickListener != null) {
                onSweetClickListener.onClick(this);
                return;
            }
            this.dismissWithAnimation();
        }
    }

    protected void onCreate(Bundle bundle) {
        Button button;
        Button button2;
        Button button3;
        FrameLayout frameLayout;
        super.onCreate(bundle);
        this.setContentView(R.layout.alert_dialog);
        this.mDialogView = this.getWindow().getDecorView().findViewById(16908290);
        this.mTitleTextView = (TextView)this.findViewById(R.id.title_text);
        this.mContentTextView = (TextView)this.findViewById(R.id.content_text);
        this.mCustomViewContainer = (FrameLayout)this.findViewById(R.id.custom_view_container);
        this.mErrorFrame = frameLayout = (FrameLayout)this.findViewById(R.id.error_frame);
        this.mErrorX = (ImageView)frameLayout.findViewById(R.id.error_x);
        this.mSuccessFrame = (FrameLayout)this.findViewById(R.id.success_frame);
        this.mProgressFrame = (FrameLayout)this.findViewById(R.id.progress_dialog);
        this.mSuccessTick = (SuccessTickView)this.mSuccessFrame.findViewById(R.id.success_tick);
        this.mSuccessLeftMask = this.mSuccessFrame.findViewById(R.id.mask_left);
        this.mSuccessRightMask = this.mSuccessFrame.findViewById(R.id.mask_right);
        this.mCustomImage = (ImageView)this.findViewById(R.id.custom_image);
        this.mWarningFrame = (FrameLayout)this.findViewById(R.id.warning_frame);
        this.mButtonsContainer = (LinearLayout)this.findViewById(R.id.buttons_container);
        this.mConfirmButton = button = (Button)this.findViewById(R.id.confirm_button);
        button.setOnClickListener((View.OnClickListener)this);
        this.mConfirmButton.setOnTouchListener(Constants.FOCUS_TOUCH_LISTENER);
        this.mCancelButton = button2 = (Button)this.findViewById(R.id.cancel_button);
        button2.setOnClickListener((View.OnClickListener)this);
        this.mCancelButton.setOnTouchListener(Constants.FOCUS_TOUCH_LISTENER);
        this.mNeutralButton = button3 = (Button)this.findViewById(R.id.neutral_button);
        button3.setOnClickListener((View.OnClickListener)this);
        this.mNeutralButton.setOnTouchListener(Constants.FOCUS_TOUCH_LISTENER);
        this.mProgressHelper.setProgressWheel((ProgressWheel)this.findViewById(R.id.progressWheel));
        this.setTitleText(this.mTitleText);
        this.setContentText(this.mContentText);
        this.setCustomView(this.mCustomView);
        this.setCancelText(this.mCancelText);
        this.setConfirmText(this.mConfirmText);
        this.setNeutralText(this.mNeutralText);
        this.applyStroke();
        this.setConfirmButtonBackgroundColor(this.mConfirmButtonBackgroundColor);
        this.setConfirmButtonTextColor(this.mConfirmButtonTextColor);
        this.setCancelButtonBackgroundColor(this.mCancelButtonBackgroundColor);
        this.setCancelButtonTextColor(this.mCancelButtonTextColor);
        this.setNeutralButtonBackgroundColor(this.mNeutralButtonBackgroundColor);
        this.setNeutralButtonTextColor(this.mNeutralButtonTextColor);
        this.changeAlertType(this.mAlertType, true);
    }

    protected void onStart() {
        this.mDialogView.startAnimation((Animation)this.mModalInAnim);
        this.playAnimation();
    }

    public SweetAlertDialog setCancelButton(int n, OnSweetClickListener onSweetClickListener) {
        this.setCancelButton(this.getContext().getResources().getString(n), onSweetClickListener);
        return this;
    }

    public SweetAlertDialog setCancelButton(String string2, OnSweetClickListener onSweetClickListener) {
        this.setCancelText(string2);
        this.setCancelClickListener(onSweetClickListener);
        return this;
    }

    public SweetAlertDialog setCancelButtonBackgroundColor(Integer n) {
        this.mCancelButtonBackgroundColor = n;
        this.setButtonBackgroundColor(this.mCancelButton, n);
        return this;
    }

    public SweetAlertDialog setCancelButtonTextColor(Integer n) {
        this.mCancelButtonTextColor = n;
        Button button = this.mCancelButton;
        if (button != null && n != null) {
            button.setTextColor(n.intValue());
        }
        return this;
    }

    public SweetAlertDialog setCancelClickListener(OnSweetClickListener onSweetClickListener) {
        this.mCancelClickListener = onSweetClickListener;
        return this;
    }

    public SweetAlertDialog setCancelText(String string2) {
        this.mCancelText = string2;
        if (this.mCancelButton != null && string2 != null) {
            this.showCancelButton(true);
            this.mCancelButton.setText((CharSequence)this.mCancelText);
        }
        return this;
    }

    public SweetAlertDialog setConfirmButton(int n, OnSweetClickListener onSweetClickListener) {
        this.setConfirmButton(this.getContext().getResources().getString(n), onSweetClickListener);
        return this;
    }

    public SweetAlertDialog setConfirmButton(String string2, OnSweetClickListener onSweetClickListener) {
        this.setConfirmText(string2);
        this.setConfirmClickListener(onSweetClickListener);
        return this;
    }

    public SweetAlertDialog setConfirmButtonBackgroundColor(Integer n) {
        this.mConfirmButtonBackgroundColor = n;
        this.setButtonBackgroundColor(this.mConfirmButton, n);
        return this;
    }

    public SweetAlertDialog setConfirmButtonTextColor(Integer n) {
        this.mConfirmButtonTextColor = n;
        Button button = this.mConfirmButton;
        if (button != null && n != null) {
            button.setTextColor(n.intValue());
        }
        return this;
    }

    public SweetAlertDialog setConfirmClickListener(OnSweetClickListener onSweetClickListener) {
        this.mConfirmClickListener = onSweetClickListener;
        return this;
    }

    public SweetAlertDialog setConfirmText(String string2) {
        this.mConfirmText = string2;
        Button button = this.mConfirmButton;
        if (button != null && string2 != null) {
            button.setText((CharSequence)string2);
        }
        return this;
    }

    public SweetAlertDialog setContentText(String string2) {
        this.mContentText = string2;
        if (this.mContentTextView != null && string2 != null) {
            this.showContentText(true);
            int n = this.contentTextSize;
            if (n != 0) {
                this.mContentTextView.setTextSize(0, (float)SweetAlertDialog.spToPx(n, this.getContext()));
            }
            this.mContentTextView.setText((CharSequence)Html.fromHtml((String)this.mContentText));
            this.mContentTextView.setVisibility(0);
            this.mCustomViewContainer.setVisibility(8);
        }
        return this;
    }

    public SweetAlertDialog setContentTextSize(int n) {
        this.contentTextSize = n;
        return this;
    }

    public SweetAlertDialog setCustomImage(int n) {
        return this.setCustomImage(this.getContext().getResources().getDrawable(n));
    }

    public SweetAlertDialog setCustomImage(Drawable drawable2) {
        this.mCustomImgDrawable = drawable2;
        ImageView imageView = this.mCustomImage;
        if (imageView != null && drawable2 != null) {
            imageView.setVisibility(0);
            this.mCustomImage.setImageDrawable(this.mCustomImgDrawable);
        }
        return this;
    }

    public SweetAlertDialog setCustomView(View view) {
        FrameLayout frameLayout;
        this.mCustomView = view;
        if (view != null && (frameLayout = this.mCustomViewContainer) != null) {
            frameLayout.addView(view);
            this.mCustomViewContainer.setVisibility(0);
            this.mContentTextView.setVisibility(8);
        }
        return this;
    }

    public SweetAlertDialog setHideKeyBoardOnDismiss(boolean bl) {
        this.mHideKeyBoardOnDismiss = bl;
        return this;
    }

    public SweetAlertDialog setNeutralButton(int n, OnSweetClickListener onSweetClickListener) {
        this.setNeutralButton(this.getContext().getResources().getString(n), onSweetClickListener);
        return this;
    }

    public SweetAlertDialog setNeutralButton(String string2, OnSweetClickListener onSweetClickListener) {
        this.setNeutralText(string2);
        this.setNeutralClickListener(onSweetClickListener);
        return this;
    }

    public SweetAlertDialog setNeutralButtonBackgroundColor(Integer n) {
        this.mNeutralButtonBackgroundColor = n;
        this.setButtonBackgroundColor(this.mNeutralButton, n);
        return this;
    }

    public SweetAlertDialog setNeutralButtonTextColor(Integer n) {
        this.mNeutralButtonTextColor = n;
        Button button = this.mNeutralButton;
        if (button != null && n != null) {
            button.setTextColor(n.intValue());
        }
        return this;
    }

    public SweetAlertDialog setNeutralClickListener(OnSweetClickListener onSweetClickListener) {
        this.mNeutralClickListener = onSweetClickListener;
        return this;
    }

    public SweetAlertDialog setNeutralText(String string2) {
        this.mNeutralText = string2;
        if (this.mNeutralButton != null && string2 != null && !string2.isEmpty()) {
            this.mNeutralButton.setVisibility(0);
            this.mNeutralButton.setText((CharSequence)this.mNeutralText);
        }
        return this;
    }

    public SweetAlertDialog setStrokeWidth(float f) {
        this.strokeWidth = SweetAlertDialog.spToPx(f, this.getContext());
        return this;
    }

    public void setTitle(int n) {
        this.setTitleText(this.getContext().getResources().getString(n));
    }

    public void setTitle(CharSequence charSequence) {
        this.setTitleText(charSequence.toString());
    }

    public SweetAlertDialog setTitleText(int n) {
        return this.setTitleText(this.getContext().getResources().getString(n));
    }

    public SweetAlertDialog setTitleText(String string2) {
        this.mTitleText = string2;
        if (this.mTitleTextView != null && string2 != null) {
            if (string2.isEmpty()) {
                this.mTitleTextView.setVisibility(8);
                return this;
            }
            this.mTitleTextView.setVisibility(0);
            this.mTitleTextView.setText((CharSequence)Html.fromHtml((String)this.mTitleText));
        }
        return this;
    }

    public SweetAlertDialog showCancelButton(boolean bl) {
        this.mShowCancel = bl;
        Button button = this.mCancelButton;
        if (button != null) {
            int n = bl ? 0 : 8;
            button.setVisibility(n);
        }
        return this;
    }

    public SweetAlertDialog showContentText(boolean bl) {
        this.mShowContent = bl;
        TextView textView = this.mContentTextView;
        if (textView != null) {
            int n = bl ? 0 : 8;
            textView.setVisibility(n);
        }
        return this;
    }

    public static interface OnSweetClickListener {
        public void onClick(SweetAlertDialog var1);
    }

}

