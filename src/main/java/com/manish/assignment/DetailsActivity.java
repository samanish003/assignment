package com.manish.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYouListener;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        TextView name = findViewById(R.id.name);
        TextView capital = findViewById(R.id.capital);
        TextView region = findViewById(R.id.region);
        TextView subRegion = findViewById(R.id.subRegion);
        TextView language = findViewById(R.id.language);
        TextView border = findViewById(R.id.border);
        TextView population = findViewById(R.id.population);
        ImageView flag = findViewById(R.id.flag);

        Bundle bundle = getIntent().getExtras();
        name.setText("Name: "+bundle.getString("name"));
        capital.setText("Capital: "+bundle.getString("capital"));
        region.setText("Region: "+bundle.getString("region"));
        subRegion.setText("SubRegion: "+bundle.getString("subregion"));
        language.setText("Language: "+bundle.getString("languages"));
        border.setText("Border: "+bundle.getString("borders"));
        population.setText("Population: "+String.valueOf(bundle.getInt("population")));
        Uri uri = Uri.parse(bundle.getString("flag"));
        GlideToVectorYou
                .init()
                .with(getApplicationContext())
                .withListener(new GlideToVectorYouListener() {
                    @Override
                    public void onLoadFailed() {
                    }

                    @Override
                    public void onResourceReady() {
                    }
                })
                .load(uri, flag);




    }
}