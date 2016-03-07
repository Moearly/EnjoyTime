package com.martn.enjoytime.view.WaveView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.martn.enjoytime.R;

/**
 * Title: EnjoyTime
 * Package: com.martn.enjoytime.view
 * Description: ("请描述功能")
 * Date 2016/3/7 16:35
 *
 * @author MartnLei MartnLei_163_com
 * @version V1.0
 */
public class WaveView extends LinearLayout {
    protected static final int LARGE = 1;
    protected static final int LITTLE = 3;
    protected static final int MIDDLE = 2;
    private final int BASE_PROGRESS = 10 ;
    private final int DEFAULT_ABOVE_WAVE_COLOR = Color.WHITE;
    private final int DEFAULT_BLOW_WAVE_COLOR = Color.WHITE;
    private int mAboveWaveColor;
    private int mBlowWaveColor;
    private int mProgress;
    private Solid mSolid;
    private Wave mWave;
    private int mWaveHeight;
    private int mWaveHz;
    private int mWaveMultiple;
    private int mWaveToTop;

    private static class SavedState extends BaseSavedState {
        int progress;

        SavedState(Parcelable superState) {
            super(superState);
        }

        private SavedState(Parcel in) {
            super(in);
            progress = in.readInt();
        }

        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeInt(progress);
        }

        public static final Creator<SavedState> CREATOR = new Creator<SavedState>() {
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
    }

    public WaveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(LARGE);
        TypedArray attributes = context.getTheme().obtainStyledAttributes(attrs, R.styleable.WaveView, R.attr.waveViewStyle, 0);
        mAboveWaveColor = attributes.getColor(R.styleable.WaveView_above_wave_color, DEFAULT_ABOVE_WAVE_COLOR);
        mBlowWaveColor = attributes.getColor(R.styleable.WaveView_blow_wave_color, DEFAULT_BLOW_WAVE_COLOR);
        mProgress = attributes.getInt(R.styleable.WaveView_progress, BASE_PROGRESS);
        mWaveHeight = attributes.getInt(R.styleable.WaveView_wave_height, MIDDLE);
        mWaveMultiple = attributes.getInt(R.styleable.WaveView_wave_length, LARGE);
        mWaveHz = attributes.getInt(R.styleable.WaveView_wave_hz, MIDDLE);
        attributes.recycle();
        mWave = new Wave(context, null);
        mWave.initializeWaveSize(mWaveMultiple, mWaveHeight, mWaveHz);
        mWave.setAboveWaveColor(mAboveWaveColor);
        mWave.setBlowWaveColor(mBlowWaveColor);
        mWave.initializePainters();
        mSolid = new Solid(context, null);
        mSolid.setAboveWavePaint(mWave.getAboveWavePaint());
        mSolid.setBlowWavePaint(mWave.getBlowWavePaint());
    }

    public void startUpdateProgress() {
        setProgress(0);
        addView(mWave);
        addView(mSolid);
    }

    public void stopUpdateProgress() {
        removeAllViews();
    }

    public void setProgress(int progress) {
        progress = ((int) (0.9 * ((double) progress))) + 10;
        if (progress > 100) {
            progress = 100;
        }
        mProgress = progress;
        computeWaveToTop();
    }

    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        if (hasWindowFocus) {
            computeWaveToTop();
        }
    }

    private void computeWaveToTop() {
        mWaveToTop = (int) (getHeight() * (1f - mProgress / 100f));
        if (this.mWave != null) {
            ViewGroup.LayoutParams params = mWave.getLayoutParams();
            if (params != null) {
                ((LayoutParams) params).topMargin = mWaveToTop;
            }
            mWave.setLayoutParams(params);
        }
    }

    public Parcelable onSaveInstanceState() {
        SavedState ss = new SavedState(super.onSaveInstanceState());
        ss.progress = mProgress;
        return ss;
    }

    public void onRestoreInstanceState(Parcelable state) {
        SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());
        setProgress(ss.progress);
    }
}
