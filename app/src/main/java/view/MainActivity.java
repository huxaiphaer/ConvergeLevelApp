package view;

import android.os.Parcelable;
import android.os.PersistableBundle;
import android.support.test.espresso.idling.CountingIdlingResource;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.levelapp.converge.convergelevelapp.R;

import presenter.GithubPresenter;

public class MainActivity extends AppCompatActivity {

    private RecyclerView.LayoutManager mLayoutManager;
    Parcelable listState;
    public final static String LIST_STATE_KEY = "recycler_list_state";
    private RecyclerView rv;

    CountingIdlingResource espressoTestIdlingResource = new CountingIdlingResource("Network_Call");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv = findViewById(R.id.rv);
        mLayoutManager = new GridLayoutManager(this,2);
        rv.setLayoutManager(mLayoutManager);

        GithubPresenter githubPresenter = new GithubPresenter(MainActivity.this,this);
        githubPresenter.getJavaDevelopers(espressoTestIdlingResource);

    }


    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        listState = mLayoutManager.onSaveInstanceState();
        outState.putParcelable(LIST_STATE_KEY, listState);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState!= null)
            listState = savedInstanceState.getParcelable(LIST_STATE_KEY);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(listState != null){
            mLayoutManager.onRestoreInstanceState(listState);

        }

    }

    public CountingIdlingResource getEspressoIdlingResourceForMainActivity() {
        return espressoTestIdlingResource;
    }


}
