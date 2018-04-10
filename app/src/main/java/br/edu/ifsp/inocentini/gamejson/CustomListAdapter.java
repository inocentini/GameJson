package br.edu.ifsp.inocentini.gamejson;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CustomListAdapter extends ArrayAdapter<Game> {
    ArrayList<Game> games;
    Context context;
    int resource;

    public CustomListAdapter(Context context, int resource, ArrayList<Game> games) {
        super(context, resource, games);
        this.games = games;
        this.context = context;
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) getContext()
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.custom_list_layout, null, true);

        }
        Game game = getItem(position);

        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageViewGame);
        TextView txtName = (TextView) convertView.findViewById(R.id.txtTitulo);
        TextView txtRelease = (TextView) convertView.findViewById(R.id.txtLancamento);
        TextView txtPlataforma = (TextView) convertView.findViewById(R.id.txtPlataforma);

        final ProgressBar progressBar = (ProgressBar) convertView.findViewById(R.id.progressBar);

        Picasso.get()
                .load(game.getImage())
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Exception e) {
                        e.printStackTrace();

                    }
                });

//        Picasso.get().load(game.getImage()).into(imageView);

        txtName.setText(game.getName());
        txtRelease.setText("Lançamento: "+ game.getRelease_date());
        StringBuffer stringBuffer = new StringBuffer();
        for(Game.Plataform plataform : games.get(position).getPlataformList()){
            stringBuffer.append(plataform.getName()+", ");
        }
        txtPlataforma.setText("Disponível em : " + stringBuffer);

        return convertView;
    }
}

