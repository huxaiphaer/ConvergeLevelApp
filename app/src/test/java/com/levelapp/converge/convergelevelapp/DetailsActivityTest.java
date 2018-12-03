package com.levelapp.converge.convergelevelapp;

import android.os.Build;
import android.widget.ImageView;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import view.DetailsActivity;

import static junit.framework.TestCase.assertNotNull;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.N)
public class DetailsActivityTest {

    private DetailsActivity detailsActivity;


    @Before
    public void setUp() throws Exception
    {
        detailsActivity = Robolectric.buildActivity( DetailsActivity.class )
                .create()
                .resume()
                .get();
    }

    @Test
    public void shouldNotBeNull() throws Exception
    {
        assertNotNull( detailsActivity );
    }

    @Test
    public void validateTextViewContent() {
        TextView textView =  detailsActivity.findViewById(R.id.username_details_txt);
        assertNotNull("TextView is null", textView);
    }

    @Test
    public void validateTextViewContentTwo() {

        TextView url_details_txt = detailsActivity.findViewById(R.id.url_details_txt);
        assertNotNull("TextView is null", url_details_txt);
    }

    @Test
    public void checkImageView(){
        ImageView imageView = detailsActivity.findViewById(R.id.profile_id_details_img);
        assertNotNull(imageView);
    }



}
