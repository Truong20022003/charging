package com.charging.animation.mobile.battery.service;

import android.graphics.Canvas;
import android.graphics.Movie;
import android.os.Handler;
import android.os.SystemClock;
import android.service.wallpaper.WallpaperService;
import android.view.SurfaceHolder;


import com.charging.animation.mobile.battery.util.Data;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class LiveWallpaperService extends WallpaperService {

    static final Handler mHANDLER = new Handler();

    @Override
    public Engine onCreateEngine() {
        try {
            return new WallEngine();
        } catch (IOException e) {
            stopSelf();
            return null;
        }
    }

    class WallEngine extends Engine {
        Movie movie;
        int duration;
        Runnable mThreadUI;
        float scaleX;
        float scaleY;
        int mWhen;
        long mStart;

        void tick() {
            try {
                if (mWhen == -1) {
                    mWhen = 0;
                    mStart = SystemClock.uptimeMillis();
                } else {
                    long mDiff = SystemClock.uptimeMillis() - mStart;
                    mWhen = (int) (mDiff % duration);
                }

            } catch (Exception ignored) {

            }
        }

        void drawCanvas(Canvas canvas) {
            try {
                canvas.save();
                canvas.scale(scaleX, scaleY);
                movie.setTime(mWhen);
                movie.draw(canvas, 0, 0);
                canvas.restore();
            } catch (Exception ignored) {

            }
        }

        void updateUI() {
            tick();
            SurfaceHolder surfaceHolder = getSurfaceHolder();
            Canvas canvas = null;
            try {
                canvas = surfaceHolder.lockCanvas();
                if (canvas != null)
                    drawCanvas(canvas);
            } finally {
                if (canvas != null)
                    surfaceHolder.unlockCanvasAndPost(canvas);
            }
            mHANDLER.removeCallbacks(mThreadUI);
            if (isVisible()) {
                mHANDLER.postDelayed(mThreadUI, 1000 / 25);
            }
        }

        WallEngine() throws IOException {
            try {
                InputStream stream = null;
                if(Data.TYPE == 0){
                    stream  =  getBaseContext().getAssets().open(Data.PATH.replace("file:///android_asset/",""));
                }else if(Data.TYPE == 2|| Data.TYPE == 3){
                    FileInputStream fileInputStream;
                    File file = new File(Data.PATH);
                    fileInputStream = new FileInputStream(file);
                    stream = fileInputStream;
                }
                if (stream != null) {
                    try {
                        movie = Movie.decodeStream(stream);
                        duration = movie.duration();
                    } finally {
                        stream.close();
                    }
                } else {
                    throw new IOException("Unable");
                }
                mWhen = -1;
                mThreadUI = this::updateUI;
            } catch (Exception ignored) {

            }
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            try {
                mHANDLER.removeCallbacks(mThreadUI);

            } catch (Exception ignored) {

            }
        }

        @Override
        public void onVisibilityChanged(boolean visible) {
            super.onVisibilityChanged(visible);
            try {
                if (visible) {
                    updateUI();
                } else {
                    mHANDLER.removeCallbacks(mThreadUI);
                }

            } catch (Exception ignored) {

            }
        }

        @Override
        public void onSurfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            super.onSurfaceChanged(holder, format, width, height);
            try {

                scaleX = (float) width / movie.width();
                scaleY = (float) height / movie.height();


                updateUI();

            } catch (Exception ignored) {

            }
        }

        @Override
        public void onOffsetsChanged(float xOffset, float yOffset, float xOffsetStep, float yOffsetStep, int xPixelOffset, int yPixelOffset) {
            super.onOffsetsChanged(xOffset, yOffset, xOffsetStep, yOffsetStep, xPixelOffset, yPixelOffset);
            updateUI();
        }
    }

}
