package com.avraghosh.mylocationweather;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    TextView t1_temp,t2_city,t3_description,t4_date;
    ImageView img_weather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img_weather = (ImageView)findViewById(R.id.img_weather);
        t1_temp = (TextView)findViewById(R.id.temp);
        t2_city = (TextView)findViewById(R.id.city);
        t3_description = (TextView)findViewById(R.id.desc);
        t4_date = (TextView)findViewById(R.id.date);

        //Getting the Latitude and Longitude from Initial Activity
        Bundle extras = getIntent().getExtras();
        double latitude = extras.getDouble("Latitude");
        double longitude = extras.getDouble("Longitude");

        //Calling the function to get the weather details
        check_weather(latitude,longitude);
    }



    public void check_weather(final double lat, final double lon){
        //Calling Openweathermap Api with the latitude and longitude received from the Initial Activity and setting the
        //textviews accordingly
        String url = "http://api.openweathermap.org/data/2.5/weather?lat="+lat+"&lon="+lon+"&cnt=1&appid=66a289b4109a07f6fcdf2992879e35ea&units=Imperial";
        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject main_object = response.getJSONObject("main");
                    JSONArray array = response.getJSONArray("weather");
                    JSONObject object = array.getJSONObject(0);
                    String temp = String.valueOf(main_object.getDouble("temp"));
                    String description = object.getString("description");
                    String city = response.getString("name");
                    t1_temp.setText(temp);
                    t2_city.setText(city);
                    t3_description.setText(description);
                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                    String date = sdf.format(calendar.getTime());
                    t4_date.setText(date);
                    double temp_int = Double.parseDouble(temp);
                    double temp_cel = (temp_int - 32)/ 1.8;
                    temp_cel = Math.round(temp_cel);
                    int final_temp = (int)temp_cel;
                    t1_temp.setText(String.valueOf(final_temp));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jor);
    }
}
