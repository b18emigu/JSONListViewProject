package com.example.project;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView lw;
    private PyramidAdapter adapter;
    private ArrayList<Pyramid> pyramids;
    private final String JSONURL = "http://wwwlab.iit.his.se/brom/kurser/mobilprog/dbservice/admin/getdataasjson.php?type=b18emigu";
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setLogo(R.drawable.icon);

        context = this;
        new FetchData().execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_about) {
            Log.d("EMIL","Show the about page!");
            Toast.makeText(getApplicationContext(), Html.fromHtml("<strong>Pyramid Explorer</strong><br><br>For those who love traveling and want to experience the most from the great Pyramids!"), Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class FetchData extends AsyncTask<Void,Void,String> {
        @Override
        protected String doInBackground(Void... params) {
            // These two variables need to be declared outside the try/catch
            // so that they can be closed in the finally block.
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            // Will contain the raw JSON response as a Java string.
            String jsonStr = null;

            try {
                // Construct the URL for the Internet service
                URL url = new URL(JSONURL);

                // Create the request to the PHP-service, and open the connection
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    Log.d("EMIL", "THERE WAS NOTHING IN THE STREAM!");
                    return null;
                }

                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                    // But it does make debugging a *lot* easier if you print out the completed
                    // buffer for debugging.
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    return null;
                }

                jsonStr = buffer.toString();
                return jsonStr;
            } catch (IOException e) {
                Log.e("PlaceholderFragment", "Error ", e);
                // If the code didn't successfully get the weather data, there's no point in
                // attempting to parse it.
                return null;
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e("Network error", "Error closing stream", e);
                    }
                }
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            pyramids = new ArrayList<>();
            try {
                JSONArray jsonArray = new JSONArray(s);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    JSONObject jsonAuxObject = new JSONObject(jsonObject.getString("auxdata"));

                    Log.d("EMIL NORMAL OBJECT", jsonObject.toString());
                    Log.d("EMIL AUX OBJECT", jsonAuxObject.toString());

                    int id = jsonObject.getInt("ID");
                    int volume = jsonAuxObject.getInt("volume");
                    String pharaoh = jsonAuxObject.getString("pharaoh");
                    String name = jsonAuxObject.getString("name");
                    String dynasty = jsonAuxObject.getString("dynasty");
                    String location = jsonAuxObject.getString("location");
                    String image = jsonAuxObject.getString("img");


                    Pyramid pyramid = new Pyramid(id, volume, pharaoh, name, dynasty, location, image);
                    pyramids.add(pyramid);
                }
            } catch (JSONException e) {
                Log.e("EMIL", "E: " + e.getMessage());
            }

            adapter = new PyramidAdapter(context, pyramids);
            lw = (ListView) findViewById(R.id.pyramid_list);
            lw.setAdapter(adapter);

            /*
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.mountain_item_layout, R.id.mountain_layout_listview, mountainStringList);
            ListView lw = (ListView) findViewById(R.id.mountain_listview);
            lw.setAdapter(arrayAdapter);
            lw.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(getApplicationContext(), mountains.get(position).getToastInformation(), Toast.LENGTH_LONG).show();
                }
            });
            */
        }
    }
}
