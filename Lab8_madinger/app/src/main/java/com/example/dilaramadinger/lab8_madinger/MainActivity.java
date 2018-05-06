package com.example.dilaramadinger.lab8_madinger;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private String[] categories = {"people", "planets", "species", "starships", "vehicles"};
    private static final String API_URL = "https://swapi.co/api/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ArrayAdapter<String> listAdapter;

        listView = (ListView) findViewById(R.id.myList);
        listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, categories);
        listView.setAdapter(listAdapter);

        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                new RetrieveFeedTask().execute(listAdapter.getItem(position));
            }
        };
        listView.setOnItemClickListener(itemClickListener);
    }


    class RetrieveFeedTask extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... args) {
            String category = args[0];
            try {
                //create full URL for search
                URL url = new URL(API_URL + category);
                //create open connection to URL
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    //create an InputStreamReader with the JSON stream
                    InputStreamReader is = new InputStreamReader(urlConnection.getInputStream());
                    //convert the byte stream to a character stream using BufferedReader
                    BufferedReader bufferedReader = new BufferedReader(is);
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    //loop through the character stream and build a sequence of characters using StringBuilder
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();
                    //convert character sequence to a String
                    return stringBuilder.toString();
                } finally {
                    //disconnect the http connection
                    urlConnection.disconnect();
                }
            } catch (Exception e) {
                Log.e("ERROR", e.getMessage(), e);
                return null;
            }
        }

        //invoked on the UI thread after the background computation finishes
        //response is the result of doInBackground
        protected void onPostExecute(String response) {
            String title;
            String name;
            String tagWord = "here";
            String category;
            List<String> titlesArr = new ArrayList<>();
            //response should be a String with JSON objects
            if (response == null) {
                response = "THERE WAS AN ERROR";
            }
            //parse JSON object
            try {
                // create intent
                Intent intent = new Intent(MainActivity.this, ResultsActivity.class);
                Bundle extras = new Bundle();

                // get category and add to intent
                JSONObject object = (JSONObject) new JSONTokener(response).nextValue();
                try {
                    Object cat = object.getString("next");
                        String categ = cat.toString();
                        String[] split = categ.split("/\\?");
                        String part1 = split[0];
                        String[] split2 = part1.split("/");
                        category = split2[split2.length - 1];
                } catch (Exception e){
                    category = "films";
                }
                intent.putExtra("category", category);

                switch(category){
                    case "films":
                        JSONArray resultsArr = object.getJSONArray("results");
                        for (int i = 0; i < resultsArr.length(); i++) {
                            JSONObject titles = resultsArr.getJSONObject(i);
                            title = titles.getString("title");
                            titlesArr.add(title);
                        }
                        String results = String.valueOf(object);

                        intent.putExtra("number", Integer.toString(titlesArr.size()));
                        for(int i = 0; i < titlesArr.size(); i++){
                            extras.putString(tagWord+i, titlesArr.get(i));
                        }
                        intent.putExtras(extras);
                        startActivity(intent);
                        break;
                    case "people":
                        JSONArray resultsArr2 = object.getJSONArray("results");
                        for (int i = 0; i < resultsArr2.length(); i++) {
                            JSONObject titles = resultsArr2.getJSONObject(i);
                            name = titles.getString("name");
                            titlesArr.add(name);
                        }
                        intent.putExtra("number", Integer.toString(titlesArr.size()));
                        for(int i = 0; i < titlesArr.size(); i++){
                            extras.putString(tagWord+i, titlesArr.get(i));
                        }
                        intent.putExtras(extras);
                        startActivity(intent);
                        break;
                    case "planets":
                        JSONArray resultsArr3 = object.getJSONArray("results");
                        for (int i = 0; i < resultsArr3.length(); i++) {
                            JSONObject titles = resultsArr3.getJSONObject(i);
                            name = titles.getString("name");
                            titlesArr.add(name);
                        }
                        intent.putExtra("number", Integer.toString(titlesArr.size()));
                        for(int i = 0; i < titlesArr.size(); i++){
                            extras.putString(tagWord+i, titlesArr.get(i));
                        }
                        intent.putExtras(extras);
                        startActivity(intent);
                        break;
                    case "species":
                        JSONArray resultsArr4 = object.getJSONArray("results");
                        for (int i = 0; i < resultsArr4.length(); i++) {
                            JSONObject titles = resultsArr4.getJSONObject(i);
                            name = titles.getString("name");
                            titlesArr.add(name);
                        }
                        intent.putExtra("number", Integer.toString(titlesArr.size()));
                        for(int i = 0; i < titlesArr.size(); i++){
                            extras.putString(tagWord+i, titlesArr.get(i));
                        }
                        intent.putExtras(extras);
                        startActivity(intent);
                        break;
                    case "starships":
                        JSONArray resultsArr5 = object.getJSONArray("results");
                        for (int i = 0; i < resultsArr5.length(); i++) {
                            JSONObject titles = resultsArr5.getJSONObject(i);
                            name = titles.getString("name");
                            titlesArr.add(name);
                        }
                        intent.putExtra("number", Integer.toString(titlesArr.size()));
                        for(int i = 0; i < titlesArr.size(); i++){
                            extras.putString(tagWord+i, titlesArr.get(i));
                        }
                        intent.putExtras(extras);
                        startActivity(intent);
                        break;
                    case "vehicles":
                        JSONArray resultsArr6 = object.getJSONArray("results");
                        for (int i = 0; i < resultsArr6.length(); i++) {
                            JSONObject titles = resultsArr6.getJSONObject(i);
                            name = titles.getString("name");
                            titlesArr.add(name);
                        }
                        intent.putExtra("number", Integer.toString(titlesArr.size()));
                        for(int i = 0; i < titlesArr.size(); i++){
                            extras.putString(tagWord+i, titlesArr.get(i));
                        }
                        intent.putExtras(extras);
                        startActivity(intent);
                        break;
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}