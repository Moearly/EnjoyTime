package com.martn.enjoytime.view.WaveView;

import android.content.Context;
import android.content.res.TypedArray;
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
    private final int BASE_PROGRESS;
    private final int DEFAULT_ABOVE_WAVE_COLOR;
    private final int DEFAULT_BLOW_WAVE_COLOR;
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
        public static final Creator<SavedState> CREATOR;
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

        static {
            CREATOR = new Creator<SavedState>() {
                public SavedState createFromParcel(Parcel in) {
                    return new SavedState(in);
                }

                public SavedState[] newArray(int size) {
                    return new SavedState[size];
                }
            };
        }
    }

    public WaveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        DEFAULT_ABOVE_WAVE_COLOR = -1;
        DEFAULT_BLOW_WAVE_COLOR = -1;
        BASE_PROGRESS = 10;
        setOrientation(LARGE);
        TypedArray attributes = context.getTheme().obtainStyledAttributes(attrs, R.styleable.WaveView, R.attr.waveViewStyle, 0);
        mAboveWaveColor = attributes.getColor(R.styleable.WaveView_above_wave_color, -1);
        mBlowWaveColor = attributes.getColor(R.styleable.WaveView_blow_wave_color, -1);
        mProgress = attributes.getInt(R.styleable.WaveView_progress, 0);
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
        mWaveToTop = (int) (((float) getHeight()) * (1f - (((float) mProgress) / 100.0f)));
        if (mWave != null) {
            ViewGroup.LayoutParams params = mWave.getLayoutParams();
            if (params != null) {
                ((LinearLayout.LayoutParams) params).topMargin = mWaveToTop;
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
