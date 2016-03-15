package com.martn.enjoytime.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.widget.ImageButton;

import com.martn.enjoytime.R;



/**
 * Title: EnjoyTime
 * Package: com.martn.enjoytime.view
 * Description: ("自定义的button")
 * Date 2016/3/11 16:46
 *
 * @author MartnLei MartnLei_163_com
 * @version V1.0
 */
public class MyActionButton extends ImageButton {

    int mColorRound;
    private float mIconSize;
    int mColorNormal;
    int mColorPressed;
    int mColorDisabled;

    private int mIcon;
    private Drawable mIconDrawable;
    private float mSize;
    private float mBorder;
    //整个显示背景区域的大小
    private int mDrawableSize;
    //圆圈的size
    private float mCircleSize;

    private boolean isShowIcon = true;

    public MyActionButton(Context context) {
        this(context, null);
    }

    public MyActionButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public MyActionButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }


    void init(Context context, AttributeSet attributeSet) {
        TypedArray attr = context.obtainStyledAttributes(attributeSet, R.styleable.MyActionButton, 0, 0);
        mColorNormal = attr.getColor(R.styleable.MyActionButton_colorNormal, getColor(android.R.color.holo_blue_dark));
        mColorPressed = attr.getColor(R.styleable.MyActionButton_colorPressed, getColor(android.R.color.holo_blue_light));
        mColorDisabled = attr.getColor(R.styleable.MyActionButton_colorDisabled, getColor(android.R.color.darker_gray));
        mColorRound = attr.getColor(R.styleable.MyActionButton_colorRound, getColor(android.R.color.darker_gray));

        mSize = attr.getDimensionPixelSize(R.styleable.MyActionButton_sizeR, 0);

        mIconSize = attr.getDimensionPixelSize(R.styleable.MyActionButton_iconSize,0);
        mIcon = attr.getResourceId(R.styleable.MyActionButton_iconA, 0);

        mBorder = attr.getDimensionPixelSize(R.styleable.MyActionButton_border,0);

        updateCircleSize();
        updateDrawableSize();
        updateBackground();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(mDrawableSize, mDrawableSize);
    }

    void updateBackground() {

        StateListDrawable drawable = new StateListDrawable();
        drawable.addState(new int[] { -android.R.attr.state_enabled }, createPressedDrawable());
        drawable.addState(new int[] { android.R.attr.state_pressed }, createPressedDrawable());
        drawable.addState(new int[] { }, createNormalDrawable());

        setBackgroundCompat(drawable);
    }


    /**
     * 三层颜色
     * @return
     */
    private Drawable createPressedDrawable() {
        Drawable[] drawableArr = new Drawable[3];
        drawableArr[0] = createCircleDrawable(mColorRound);
        drawableArr[1] = createCircleDrawable(mColorPressed);
        drawableArr[2] = getIconDrawable();
        LayerDrawable layerDrawable = new LayerDrawable(drawableArr);
        int iconOffset = ((int) (mDrawableSize - mIconSize)) / 2;
        int boderOffset = (int) mBorder;
        layerDrawable.setLayerInset(0,0,0,0,0);
        layerDrawable.setLayerInset(1, boderOffset, boderOffset, boderOffset, boderOffset);
        layerDrawable.setLayerInset(2, iconOffset, iconOffset, iconOffset, iconOffset);
        return layerDrawable;
    }

    private Drawable createNormalDrawable() {
        Drawable[] drawableArr = new Drawable[3];
        drawableArr[0] = createCircleDrawable(Color.TRANSPARENT);
        drawableArr[1] = createCircleDrawable(mColorNormal);
        drawableArr[2] = getIconDrawable();
        LayerDrawable layerDrawable = new LayerDrawable(drawableArr);
        int iconOffset = ((int) (mDrawableSize - mIconSize)) / 2;
        int boderOffset = (int) mBorder;
        layerDrawable.setLayerInset(0,0,0,0,0);
        layerDrawable.setLayerInset(1, boderOffset, boderOffset, boderOffset, boderOffset);
        layerDrawable.setLayerInset(2, iconOffset, iconOffset, iconOffset, iconOffset);
        return layerDrawable;
    }

    /**
     * 创建圆形的drawable
     * @param color
     * @return
     */
    private Drawable createCircleDrawable(int color) {
        ShapeDrawable fillDrawable = new ShapeDrawable(new OvalShape());
        final Paint paint = fillDrawable.getPaint();
        paint.setAntiAlias(true);
        paint.setColor(color);
        return fillDrawable;
    }


    Drawable getIconDrawable() {
        if (mIconDrawable != null) {
            return mIconDrawable;
        } else if (mIcon != 0) {
            return getResources().getDrawable(mIcon);
        } else {
            return new ColorDrawable(Color.TRANSPARENT);
        }
    }

    private void updateDrawableSize() {
        mDrawableSize = (int) (mCircleSize + 2 * mBorder);
    }

    private void updateCircleSize() {
        mCircleSize = mSize;
    }

    int getColor(@ColorRes int id) {
        return getResources().getColor(id);
    }

    float getDimension(@DimenRes int id) {
        return getResources().getDimension(id);
    }

    @SuppressWarnings("deprecation")
    @SuppressLint({"NewApi"})
    private void setBackgroundCompat(Drawable drawable) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            setBackground(drawable);
        } else {
            setBackgroundDrawable(drawable);
        }
    }

    public void setColorNormalResId(@ColorRes int colorNormal) {
        setColorNormal(getColor(colorNormal));
    }

    public void setColorNormal(int color) {
        if (this.mColorNormal != color) {
            this.mColorNormal = color;
            updateBackground();
        }
    }

    public void setColorDisabledResId(@ColorRes int colorDisabled) {
        setColorDisabled(getColor(colorDisabled));
    }

    public void setColorDisabled(int color) {
        if (this.mColorDisabled != color) {
            this.mColorDisabled = color;
            updateBackground();
        }
    }

    public void setColorPressedResId(@ColorRes int color) {
        setColorPressed(getColor(color));
    }

    public void setColorPressed(int color) {
        if (this.mColorPressed != color) {
            this.mColorPressed = color;
            updateBackground();
        }
    }

    public void setColorBorderResId(@ColorRes int color) {
        setColorBorder(getColor(color));
    }

    public void setColorBorder(int color) {
        if (this.mColorRound != color) {
            this.mColorRound = color;
            updateBackground();
        }
    }

    public void setIcon(@DrawableRes int icon) {
        if (this.mIcon != icon) {
            this.mIcon = icon;
            this.mIconDrawable = null;
            updateBackground();
        }
    }

    public void setIconDrawable(@NonNull Drawable iconDrawable) {
        if (this.mIconDrawable != iconDrawable) {
            this.mIcon = 0;
            this.mIconDrawable = iconDrawable;
            updateBackground();
        }
    }

}
