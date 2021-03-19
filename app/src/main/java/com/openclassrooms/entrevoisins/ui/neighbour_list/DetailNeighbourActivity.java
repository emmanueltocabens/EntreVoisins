package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import org.greenrobot.eventbus.Subscribe;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class DetailNeighbourActivity extends AppCompatActivity {

    private NeighbourApiService mApiService;
    private Neighbour selectedNeighbour;

    @BindView(R.id.detail_image_view)
    ImageView mImageView;
    @BindView(R.id.fab_favoris)
    FloatingActionButton mFabFav;
    @BindDrawable(R.drawable.ic_fav_on)
    Drawable fav_on;
    @BindDrawable(R.drawable.ic_fav_off)
    Drawable fav_off;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_neighbour);
        ButterKnife.bind(this);
        mApiService = DI.getNeighbourApiService();

        selectedNeighbour = (Neighbour)getIntent().getSerializableExtra("neighbour");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Glide.with(this)
                .load(selectedNeighbour.getAvatarUrl())
                .into(mImageView);

        mFabFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               favoriteButtonEvent();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home : {
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }


    /**
     * Used to reload the favorite button display
     */
    private void reloadButton(){
        if(selectedNeighbour.isFavorite()){
            mFabFav.setImageDrawable(fav_on);
        } else {
            mFabFav.setImageDrawable(fav_off);
        }
    }

    /**
     * Called when the user clicks on the favorite button
     */
    private void favoriteButtonEvent(){
        selectedNeighbour = mApiService.toggleFavorite(selectedNeighbour);
        reloadButton();
    }

    /**
     * Used to navigate to this activity
     * @param context context for the new activity
     * @param neighbour the neighbour that we will show details about
     *
     */
    public static void navigate(Context context, Neighbour neighbour) {
        Intent intent = new Intent().setClass(context,DetailNeighbourActivity.class);
        intent.putExtra("neighbour",neighbour);
        ActivityCompat.startActivity(context, intent, null);
    }
}