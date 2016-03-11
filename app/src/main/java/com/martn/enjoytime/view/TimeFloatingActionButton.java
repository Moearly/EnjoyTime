package com.martn.enjoytime.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.ShapeDrawable.ShaderFactory;

import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.widget.TextView;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.martn.enjoytime.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import uk.co.senab.photoview.IPhotoView;

/**
 * Title: EnjoyTime
 * Package: com.martn.enjoytime.view
 * Description: ("自定义的floating---button")
 * Date 2016/3/7 12:18
 *
 * @author MartnLei MartnLei_163_com
 * @version V1.0
 */
public class TimeFloatingActionButton extends FloatingActionButton {
    public static final int SIZE_MINI = 1;
    public static final int SIZE_NORMAL = 0;
    private float mCircleSize;
    int mColorDisabled;
    int mColorNormal;
    int mColorPressed;
    private int mDrawableSize;
    @DrawableRes
    private int mIcon;
    private Drawable mIconDrawable;
    private float mShadowOffset;
    private float mShadowRadius;
    private int mSize;
    boolean mStrokeVisible;
    String mTitle;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({ SIZE_NORMAL, SIZE_MINI })
    public @interface FAB_SIZE {
    }

    private static class TranslucentLayerDrawable extends LayerDrawable {
        private final int mAlpha;

        public TranslucentLayerDrawable(int alpha, Drawable... layers) {
            super(layers);
            this.mAlpha = alpha;
        }


        @Override
        public void draw(Canvas canvas) {
            Rect bounds = getBounds();
            canvas.saveLayerAlpha((float) bounds.left, (float) bounds.top, (float) bounds.right, (float) bounds.bottom, this.mAlpha,  Canvas.ALL_SAVE_FLAG);
            super.draw(canvas);
            canvas.restore();
        }
    }

    public TimeFloatingActionButton(Context context) {
        this(context, null);
    }

    public TimeFloatingActionButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public TimeFloatingActionButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    void init(Context context, AttributeSet attributeSet) {
        TypedArray attr = context.obtainStyledAttributes(attributeSet, R.styleable.FloatingActionButton, 0, 0);
        this.mColorNormal = attr.getColor(R.styleable.FloatingActionButton_fab_colorNormal, getColor(android.R.color.holo_blue_dark));
        this.mColorPressed = attr.getColor(R.styleable.FloatingActionButton_fab_colorPressed, getColor(android.R.color.holo_blue_light));
        this.mColorDisabled = attr.getColor(R.styleable.FloatingActionButton_fab_colorDisabled, getColor(android.R.color.darker_gray));
        this.mSize = attr.getInt(R.styleable.FloatingActionButton_fab_size, SIZE_NORMAL);
        this.mIcon = attr.getResourceId(R.styleable.FloatingActionButton_fab_icon, 0);
        this.mTitle = attr.getString(R.styleable.FloatingActionButton_fab_title);
        this.mStrokeVisible = attr.getBoolean(R.styleable.FloatingActionButton_fab_stroke_visible, true);
        attr.recycle();
        updateCircleSize();
        this.mShadowRadius = getDimension(R.dimen.fab_shadow_radius);
        this.mShadowOffset = getDimension(R.dimen.fab_shadow_offset);
        updateDrawableSize();
        updateBackground();
    }

    private void updateDrawableSize() {
        this.mDrawableSize = (int) (this.mCircleSize + (2.0f * this.mShadowRadius));
    }

    private void updateCircleSize() {
        this.mCircleSize = getDimension(this.mSize == 0 ? R.dimen.fab_size_normal : R.dimen.fab_size_mini);
    }

    public void setSize(int size) {
        if (size != SIZE_MINI && size != 0) {
            throw new IllegalArgumentException("Use @FAB_SIZE constants only!");
        } else if (this.mSize != size) {
            this.mSize = size;
            updateCircleSize();
            updateDrawableSize();
            updateBackground();
        }
    }

    public int getSize() {
        return this.mSize;
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

    public int getColorNormal() {
        return this.mColorNormal;
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

    public int getColorPressed() {
        return this.mColorPressed;
    }

    public void setColorPressedResId(@ColorRes int colorPressed) {
        setColorPressed(getColor(colorPressed));
    }

    public void setColorPressed(int color) {
        if (this.mColorPressed != color) {
            this.mColorPressed = color;
            updateBackground();
        }
    }

    public int getColorDisabled() {
        return this.mColorDisabled;
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

    public void setStrokeVisible(boolean visible) {
        if (this.mStrokeVisible != visible) {
            this.mStrokeVisible = visible;
            updateBackground();
        }
    }

    public boolean isStrokeVisible() {
        return this.mStrokeVisible;
    }

    int getColor(@ColorRes int id) {
        return getResources().getColor(id);
    }

    float getDimension(@DimenRes int id) {
        return getResources().getDimension(id);
    }

    public void setTitle(String title) {
        this.mTitle = title;
        TextView label = getLabelView();
        if (label != null) {
            label.setText(title);
        }
    }

    TextView getLabelView() {
        return (TextView) getTag(R.id.fab_label);
    }

    public String getTitle() {
        return this.mTitle;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(this.mDrawableSize, this.mDrawableSize);
    }

    void updateBackground() {
        float strokeWidth = getDimension(R.dimen.fab_stroke_width);
        float halfStrokeWidth = strokeWidth / 2.0f;
        Drawable[] drawableArr = new Drawable[4];
        drawableArr[0] = getResources().getDrawable(this.mSize == 0 ? R.drawable.fab_bg_normal : R.drawable.fab_bg_mini);
        drawableArr[SIZE_MINI] = createFillDrawable(strokeWidth);
        drawableArr[2] = createOuterStrokeDrawable(strokeWidth);
        drawableArr[3] = getIconDrawable();
        LayerDrawable layerDrawable = new LayerDrawable(drawableArr);
        int iconOffset = ((int) (this.mCircleSize - getDimension(R.dimen.fab_icon_size))) / 2;
        int circleInsetHorizontal = (int) this.mShadowRadius;
        int circleInsetTop = (int) (this.mShadowRadius - this.mShadowOffset);
        int circleInsetBottom = (int) (this.mShadowRadius + this.mShadowOffset);
        layerDrawable.setLayerInset(SIZE_MINI, circleInsetHorizontal - 3, circleInsetTop - 3, circleInsetHorizontal - 3, circleInsetBottom - 3);
        layerDrawable.setLayerInset(2, (int) (((float) circleInsetHorizontal) - halfStrokeWidth), (int) (((float) circleInsetTop) - halfStrokeWidth), (int) (((float) circleInsetHorizontal) - halfStrokeWidth), (int) (((float) circleInsetBottom) - halfStrokeWidth));
        layerDrawable.setLayerInset(3, circleInsetHorizontal + iconOffset, circleInsetTop + iconOffset, circleInsetHorizontal + iconOffset, circleInsetBottom + iconOffset);
        setBackgroundCompat(layerDrawable);
    }

    Drawable getIconDrawable() {
        if (this.mIconDrawable != null) {
            return this.mIconDrawable;
        }
        if (this.mIcon != 0) {
            return getResources().getDrawable(this.mIcon);
        }
        return new ColorDrawable(0);
    }

    private StateListDrawable createFillDrawable(float strokeWidth) {
        StateListDrawable drawable = new StateListDrawable();
        drawable.addState(new int[] { -android.R.attr.state_enabled }, createCircleDrawable(mColorDisabled, strokeWidth));
        drawable.addState(new int[] { android.R.attr.state_pressed }, createCircleDrawable(mColorPressed, strokeWidth));
        drawable.addState(new int[] { }, createCircleDrawable(mColorNormal, strokeWidth));
        return drawable;
    }

    private Drawable createCircleDrawable(int color, float strokeWidth) {
        int alpha = Color.alpha(color);
        int opaqueColor = opaque(color);//不透明的颜色--转换
        ShapeDrawable fillDrawable = new ShapeDrawable(new OvalShape());
        final Paint paint = fillDrawable.getPaint();
        paint.setAntiAlias(true);
        paint.setColor(opaqueColor);
        Drawable[] layers = new Drawable[]{fillDrawable, createInnerStrokesDrawable(opaqueColor, strokeWidth)};
        LayerDrawable drawable = (alpha == 255 || !this.mStrokeVisible) ? new LayerDrawable(layers) : new TranslucentLayerDrawable(alpha, layers);
        int halfStrokeWidth = (int) (strokeWidth / 2.0f);
        drawable.setLayerInset(SIZE_MINI, halfStrokeWidth, halfStrokeWidth, halfStrokeWidth, halfStrokeWidth);
        return drawable;
    }

    private Drawable createOuterStrokeDrawable(float strokeWidth) {
        ShapeDrawable shapeDrawable = new ShapeDrawable(new OvalShape());
        Paint paint = shapeDrawable.getPaint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(strokeWidth);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(ViewCompat.MEASURED_STATE_MASK);
        paint.setAlpha(opacityToAlpha(0.02f));
        return shapeDrawable;
    }

    /**
     * 转换透明度
     * @param opacity
     * @return
     */
    private int opacityToAlpha(float opacity) {
        return (int) (255.0f * opacity);
    }

    private int darkenColor(int argb) {
        return adjustColorBrightness(argb, 0.9f);
    }

    private int lightenColor(int argb) {
        return adjustColorBrightness(argb, 1.1f);
    }

    private int adjustColorBrightness(int argb, float factor) {
        float[] hsv = new float[3];
        Color.colorToHSV(argb, hsv);
        hsv[2] = Math.min(hsv[2] * factor, 1.0f);
        return Color.HSVToColor(Color.alpha(argb), hsv);
    }

    private int halfTransparent(int argb) {
        return Color.argb(Color.alpha(argb) / 2, Color.red(argb), Color.green(argb), Color.blue(argb));
    }

    /**
     * 转换为不透明
     * @param argb
     * @return
     */
    private int opaque(int argb) {
        return Color.rgb(Color.red(argb), Color.green(argb), Color.blue(argb));
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
        shapeDrawable.setShaderFactory(new ShaderFactory() {
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

    @SuppressWarnings("deprecation")
    @SuppressLint({"NewApi"})
    private void setBackgroundCompat(Drawable drawable) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            setBackground(drawable);
        } else {
            setBackgroundDrawable(drawable);
        }
    }

    public void setVisibility(int visibility) {
        TextView label = getLabelView();
        if (label != null) {
            label.setVisibility(visibility);
        }
        super.setVisibility(visibility);
    }
}