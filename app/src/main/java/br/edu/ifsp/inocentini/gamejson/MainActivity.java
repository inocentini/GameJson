package br.edu.ifsp.inocentini.gamejson;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayList<Game> arrayList;
    ListView lv;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dialog = new ProgressDialog(this);
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);
        dialog.setTitle("Aguarde.");
        dialog.setMessage("Carregando...");

        arrayList = new ArrayList<>();
        lv = (ListView) findViewById(R.id.listView);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new ReadJSON().execute("https://dl.dropboxusercontent.com/s/1b7jlwii7jfvuh0/games");
            }
        });
    }

    class ReadJSON extends AsyncTask<String, Integer, ArrayList<Game>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.show();
        }

        @Override
        protected ArrayList<Game> doInBackground(String... params) {
            StringBuilder content = new StringBuilder();
            try {
                URL url = new URL(params[0]);
                URLConnection urlConnection = url.openConnection();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    content.append(line + "\n");
                }

                JSONObject jsonObject = new JSONObject(content.toString());
                JSONArray jsonArray =  jsonObject.getJSONArray("games");

                for(int i =0;i<jsonArray.length(); i++){
                    JSONObject gameObject = jsonArray.getJSONObject(i);
                    Game game = new Game();
                    game.setImage(gameObject.getString("image"));
                    game.setName(gameObject.getString("name"));
                    game.setRelease_date(gameObject.getString("release_date"));
                    game.setTrailer(gameObject.getString("trailer"));
                    List<Game.Plataform> plataformList = new ArrayList<>();
                    for(int j=0;j<gameObject.getJSONArray("platforms").length();j++){
                        Game.Plataform plataform = new Game.Plataform();
                        plataform.setName(gameObject.getJSONArray("platforms").getString(j));
                        plataformList.add(plataform);
                    }
                    game.setPlataformList(plataformList);
                    arrayList.add(game);
                }
                return arrayList;

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(final ArrayList<Game> content) {
            super.onPostExecute(content);
            dialog.dismiss();
            if (content != null) {
                CustomListAdapter adapter = new CustomListAdapter(
                        getApplicationContext(), R.layout.custom_list_layout, arrayList
                );
                lv.setAdapter(adapter);
            }else{
                Toast.makeText(getApplicationContext(),"Não foi possível carregar dados do servidor",Toast.LENGTH_SHORT).show();
            }

        }
    }


    /*private static String readURL(String theUrl) {
        StringBuilder content = new StringBuilder();
        try {
            URL url = new URL(theUrl);
            URLConnection urlConnection = url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }*/
}

