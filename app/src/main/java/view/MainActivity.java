package view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.levelapp.converge.convergelevelapp.R;

import presenter.GithubPresenter;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GithubPresenter githubPresenter = new GithubPresenter(MainActivity.this,this);
        githubPresenter.getJavaDevelopers();

    }

}
