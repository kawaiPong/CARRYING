package kr.hs.emirim.lyn.carrying.API;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class OpenWeatherAPITask extends AsyncTask<Integer, Void, Weather> {
    protected static final String TAG = "OpenWeatherAPITask";


    @Override

    public Weather doInBackground(Integer... params) {

        OpenWeatherAPIClient client = new OpenWeatherAPIClient();



        int lat = params[0];

        int lon = params[1];

        // API 호출

        Weather w = client.getWeather(lat,lon);


        System.out.println("Weather : "+w.getTemprature());
        Log.e(TAG, "open >>"+w.getTemprature());


        // 작업 후 리

        return w;

    }

}



