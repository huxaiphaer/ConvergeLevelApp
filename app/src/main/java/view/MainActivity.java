package view;

import android.os.Parcelable;
import android.os.PersistableBundle;
import android.support.design.widget.Snackbar;
import android.support.test.espresso.idling.CountingIdlingResource;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.levelapp.converge.convergelevelapp.R;

import presenter.GithubPresenter;
import util.NetworkUtility;

public class MainActivity extends AppCompatActivity {

    private RecyclerView.LayoutManager mLayoutManager;
    Parcelable listState;
    public final static String LIST_STATE_KEY = "recycler_list_state";
    private RecyclerView rv;
    private View view;

    CountingIdlingResource espressoTestIdlingResource = new CountingIdlingResource("Network_Call");
    GithubPresenter githubPresenter = new GithubPresenter(MainActivity.this, this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv = findViewById(R.id.rv);
        mLayoutManager = new GridLayoutManager(this, 2);
        rv.setLayoutManager(mLayoutManager);

        NetworkUtility networkUtility = new NetworkUtility(MainActivity.this);
        if (networkUtility.isOnline()) {

            githubPresenter.getJavaDevelopers(espressoTestIdlingResource);
        } else {

            view = findViewById(R.id.snakbar_id);
            Snackbar.make(view, "Check your network connection please", Snackbar.LENGTH_LONG)
                    .setAction("RETRY", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            githubPresenter.getJavaDevelopers(espressoTestIdlingResource);
                        }
                    }).show();
            ProgressBar pb = findViewById(R.id.pb);
            pb.setVisibility(View.INVISIBLE);
        }
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
        if (savedInstanceState != null)
            listState = savedInstanceState.getParcelable(LIST_STATE_KEY);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (listState != null) {
            mLayoutManager.onRestoreInstanceState(listState);

        }

    }

    public CountingIdlingResource getEspressoIdlingResourceForMainActivity() {
        return espressoTestIdlingResource;
    }


}
