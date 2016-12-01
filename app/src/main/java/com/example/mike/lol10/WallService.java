package com.example.mike.lol10;

import android.app.Service;
import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.IBinder;
import android.util.Log;


import com.squareup.picasso.Picasso;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Mike on 11/1/2016.
 */

public class WallService extends Service {

    public native String stringFromJNI();
    static {
        System.loadLibrary("native-lib");
    }

    String forecastJsonStr;
    private final String hostName = stringFromJNI();
    private final String hostRequestName = "/paroonak/request/request.php";
    private final String hostWallpaperName = "/paroonak/uploads/wallpaper/";




    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onStart(Intent intent, int startId) {


        final Thread thread1 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        HttpURLConnection urlConnection = null;
                        BufferedReader reader = null;


                try {

                    URL url = new URL(hostName+hostRequestName);

                    urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("POST");
                    urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    urlConnection.setDoInput(true);
                    urlConnection.setDoOutput(true);
                    urlConnection.connect();
                    DataOutputStream wr = new DataOutputStream(
                            urlConnection.getOutputStream());
                    wr.write("method=get_random_wallpaper".getBytes());
                    wr.flush();
                    wr.close();

                    InputStream inputStream = urlConnection.getInputStream();
                    StringBuffer buffer = new StringBuffer();
                    if (inputStream == null) {
                    }
                    reader = new BufferedReader(new InputStreamReader(inputStream));

                    String line;
                    while ((line = reader.readLine()) != null) {

                        buffer.append(line + "\n");

                    }

                    if (buffer.length() == 0) {
                    }
                    forecastJsonStr = buffer.toString();

                } catch (IOException e) {

                } finally {
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                    if (reader != null) {
                        try {
                            reader.close();

                        } catch (final IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }

        });
        thread1.start();

        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {

                WallpaperManager wallpaperManager = WallpaperManager.getInstance(getApplicationContext());
                try {


                    Bitmap result = Picasso.with(getBaseContext())
                            .load(hostName + hostWallpaperName + forecastJsonStr)
                            .get();
               /*     WindowManager wm= (WindowManager) getSystemService(Context.WINDOW_SERVICE);
                    Display display = wm.getDefaultDisplay();
                    int a = display.getWidth();
                    int b = display.getHeight();
                    int width = result.getWidth();
                    int height = result.getHeight();
                    float widthMultiplier = a/width;

                    float heightMultiplier = b/height;
                    int bitmap_width = (int)(600 * widthMultiplier);

                    int bitmap_height = (int)(800 * heightMultiplier);
                    Bitmap bitmap = Bitmap.createScaledBitmap(result, bitmap_width, bitmap_height, false);*/

                    wallpaperManager.setBitmap(result);
                    Log.d("Note","Wallpaper Changed");

                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }

        });
        thread.start();
    }



    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }

}

