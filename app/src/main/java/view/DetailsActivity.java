package view;
import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;
import com.levelapp.converge.convergelevelapp.R;

import adapter.DevelopersAdapter;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Developer Details");
        setDetailsData();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:
                onBackPressed();
                break;
                default:
                    break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void setDetailsData(){

        Intent i = getIntent();
        String userName = i.getStringExtra(DevelopersAdapter.USERNAME);

        String usernameUrl = i.getStringExtra(DevelopersAdapter.URL);
        String profilePic = i.getStringExtra(DevelopersAdapter.PROFILE_PIC);

        TextView usernameDetailsTxt = findViewById(R.id.username_details_txt);
        TextView urlDetailsTxt = findViewById(R.id.url_details_txt);
        ImageView profileIdDetailsImg = findViewById(R.id.profile_id_details_img);

        usernameDetailsTxt.setText(userName);
        urlDetailsTxt.setText(usernameUrl);
        Ion.with(DetailsActivity.this)
                .load(profilePic)
                .intoImageView(profileIdDetailsImg);

    }

}
