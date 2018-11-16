package com.levelapp.converge.convergelevelapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;
import adapter.DevelopersAdapter;
import model.DeveloperModel;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        generateList();
    }

    private void generateList(){

        RecyclerView rv;

        rv = findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        // Set the ArrayList
        ArrayList<DeveloperModel> developers = new ArrayList<>();

        //Populate some sample Data
        developers.add(new DeveloperModel("Huxy", "http://i.imgur.com/DvpvklR.png","http://github.com/huxy"));
        developers.add(new DeveloperModel("Muda", "http://i.imgur.com/DvpvklR.png","http://github.com/muda"));
        developers.add(new DeveloperModel("Abu", "http://i.imgur.com/DvpvklR.png","http://github.com/abu"));
        developers.add(new DeveloperModel("Nabil", "http://i.imgur.com/DvpvklR.png","http://github.com/nabil"));
        developers.add(new DeveloperModel("Waliyaa", "http://i.imgur.com/DvpvklR.png","http://github.com/waliyaa"));
        developers.add(new DeveloperModel("Esther", "http://i.imgur.com/DvpvklR.png","http://github.com/essa"));
        developers.add(new DeveloperModel("Santos", "http://i.imgur.com/DvpvklR.png","http://github.com/santos"));
        developers.add(new DeveloperModel("Colline", "http://i.imgur.com/DvpvklR.png","http://github.com/colline"));
        developers.add(new DeveloperModel("Kamanzi", "http://i.imgur.com/DvpvklR.png","http://github.com/kamanzi"));
        developers.add(new DeveloperModel("Jauha", "http://i.imgur.com/DvpvklR.png","http://github.com/jauha"));
        developers.add(new DeveloperModel("Bridget", "http://i.imgur.com/DvpvklR.png","http://github.com/bridget"));
        developers.add(new DeveloperModel("patrick", "http://i.imgur.com/DvpvklR.png","http://github.com/abu"));
        developers.add(new DeveloperModel("Sekitoleko", "http://i.imgur.com/DvpvklR.png","http://github.com/seki"));
        developers.add(new DeveloperModel("Kawenda", "http://i.imgur.com/DvpvklR.png","http://github.com/kawenda"));

        DevelopersAdapter developersAdapter = new DevelopersAdapter(developers, MainActivity.this);
        rv.setAdapter(developersAdapter);
    }
}
