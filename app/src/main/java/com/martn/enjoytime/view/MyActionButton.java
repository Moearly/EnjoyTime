package com.martn.enjoytime.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.IntDef;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.widget.ImageButton;

import com.martn.enjoytime.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import uk.co.senab.photoview.IPhotoView;

/**
 * Title: EnjoyTime
 * Package: com.martn.enjoytime.view
 * Description: ("请描述功能")
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
    private float mRadius;
    private int mDrawableSize;
    private float mCircleSize;
    private float defaultButtonSize = 40;

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
        mIconSize = attr.getFloat(R.styleable.MyActionButton_iconSize,0);
        mRadius = attr.getFloat(R.styleable.MyActionButton_radius,0);
        mSize = attr.getFloat(R.styleable.MyActionButton_size,defaultButtonSize);
        mIcon = attr.getResourceId(R.styleable.MyActionButton_icon, 0);
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

        LayerDrawable layerDrawable = new LayerDrawable(drawableArr);

        int iconOffset = (int) (mCircleSize - mIconSize) / 2;

        int circleInsetHorizontal = (int) (mShadowRadius);
        int circleInsetTop = (int) (mShadowRadius - mShadowOffset);
        int circleInsetBottom = (int) (mShadowRadius + mShadowOffset);

        layerDrawable.setLayerInset(0,
                circleInsetHorizontal,
                circleInsetTop,
                circleInsetHorizontal,
                circleInsetBottom);

        layerDrawable.setLayerInset(1,
                (int) (circleInsetHorizontal - halfStrokeWidth),
                (int) (circleInsetTop - halfStrokeWidth),
                (int) (circleInsetHorizontal - halfStrokeWidth),
                (int) (circleInsetBottom - halfStrokeWidth));

        layerDrawable.setLayerInset(2,
                circleInsetHorizontal + iconOffset,
                circleInsetTop + iconOffset,
                circleInsetHorizontal + iconOffset,
                circleInsetBottom + iconOffset);

        setBackgroundCompat(createFillDrawable());
    }

    /**
     * 创建一个带状态的drawable
     * @return
     */
    private StateListDrawable createFillDrawable() {
        StateListDrawable drawable = new StateListDrawable();
//        drawable.addState(new int[] { -android.R.attr.state_enabled }, createCircleDrawable());
        drawable.addState(new int[] { android.R.attr.state_pressed }, createPressedDrawable());
        drawable.addState(new int[] { }, createNormalDrawable());
        return drawable;
    }

    private Drawable createPressedDrawable() {

    }

    private Drawable createNormalDrawable() {
        Drawable[] drawableArr = new Drawable[2];
        drawableArr[0] = createCircleDrawable(mColorNormal);
        drawableArr[1] = getIconDrawable();
        LayerDrawable layerDrawable = new LayerDrawable(drawableArr);
        int iconOffset = ((int) (mCircleSize - mIconSize)) / 2;
        layerDrawable.setLayerInset(0,0,0,0,0);
        layerDrawable.setLayerInset(1, iconOffset, iconOffset, iconOffset, iconOffset);
        return layerDrawable;
    }

    /**
     * 创建圆形的drawable
     * @param color
     * @return
     */
    private Drawable createCircleDrawable(int color) {
        int alpha = Color.alpha(color);
        int opaqueColor = opaque(color);//不透明的颜色--转换
        ShapeDrawable fillDrawable = new ShapeDrawable(new OvalShape());
        final Paint paint = fillDrawable.getPaint();
        paint.setAntiAlias(true);
        paint.setColor(color);
        return fillDrawable;
    }

    /**
     * 转换为不透明
     * @param argb
     * @return
     */
    private int opaque(int argb) {
        return Color.rgb(Color.red(argb), Color.green(argb), Color.blue(argb));
    }

    /**
     * 画一个线宽为width---的圆
     * @param width
     * @return
     */
    private Drawable createOuterStrokeDrawable(float width, int color) {
        ShapeDrawable shapeDrawable = new ShapeDrawable(new OvalShape());
        Paint paint = shapeDrawable.getPaint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(width);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(opaque(color));
        paint.setAlpha(opacityToAlpha(0.3f));
        return shapeDrawable;
    }

    private int opacityToAlpha(float opacity) {
        return (int) (255.0f * opacity);
    }

    private Drawable createInnerStrokesDrawable(final int color, float strokeWidth) {
        if (!this.mStrokeVisible) {
            return new ColorDrawable(Color.TRANSPARENT);
        }
        ShapeDrawable shapeDrawable = new ShapeDrawable(new OvalShape());
        final int bottomStrokeColor = darkenColor(color);
        final int bottomStrokeColorHalfTransparent = halfTransparent(bottomStrokeColor);
        final int topStrokeColor = lightenColor(color);
        final int topStrokeColorHalfTransparent = halfTransparent(topStrokeColor);
        Paint paint = shapeDrawable.getPaint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(strokeWidth);
        paint.setStyle(Paint.Style.STROKE);
        shapeDrawable.setShaderFactory(new ShapeDrawable.ShaderFactory() {
            @Override
            public Shader resize(int width, int height) {
                return new LinearGradient((float) (width / 2), 0.0f, (float) (width / 2),
                        (float) height,
                        new int[]{
                                topStrokeColor,
                                topStrokeColorHalfTransparent,
                                color,
                                bottomStrokeColorHalfTransparent,
                                bottomStrokeColor},
                        new float[]{0.0f, 0.2f, 0.5f, 0.8f, IPhotoView.DEFAULT_MIN_SCALE}, Shader.TileMode.CLAMP);
            }
        });
        return shapeDrawable;
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
        mDrawableSize = (int) (mCircleSize + 2 * mRadius);
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


}
