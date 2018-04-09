package br.edu.ifsp.inocentini.gamejson;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

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
        Picasso.get().load(game.getImage()).into(imageView);

        TextView txtName = (TextView) convertView.findViewById(R.id.txtTitulo);
        txtName.setText(game.getName());

        TextView txtRelease = (TextView) convertView.findViewById(R.id.txtLancamento);
        txtRelease.setText("Lançamento: "+ game.getRelease_data());

        return convertView;
    }
}

