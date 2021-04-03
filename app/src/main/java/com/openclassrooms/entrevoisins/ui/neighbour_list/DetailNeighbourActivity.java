package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;


import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;


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


    @BindView(R.id.toolbar_detail)
    Toolbar toolbar;

    @BindView(R.id.textview_name)
    TextView textView_title;
    @BindView(R.id.textview_address)
    TextView textView_Address;
    @BindView(R.id.textview_number)
    TextView textView_Number;
    @BindView(R.id.textview_link)
    TextView textView_Link;

    @BindView(R.id.tv_aboutMe_title)
    TextView textView_aboutMe_title;
    @BindView(R.id.tv_aboutMe_desc)
    TextView textView_aboutMe_desc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_neighbour);
        ButterKnife.bind(this);
        mApiService = DI.getNeighbourApiService();

        selectedNeighbour = (Neighbour)getIntent().getSerializableExtra("neighbour");
        toolbar.setTitle(selectedNeighbour.getName());
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Glide.with(this)
                .load(selectedNeighbour.getAvatarUrl())
                .into(mImageView);

        reloadButton();
        loadTextViewContents();

        mFabFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                favoriteButtonClicked();
            }
        });
    }

    /**
     * Loads text views with the neighbour's information
     */
    public void loadTextViewContents(){
        textView_title.setText(selectedNeighbour.getName());
        textView_Address.setText(selectedNeighbour.getAddress());
        textView_Number.setText(selectedNeighbour.getPhoneNumber());
        String link = "www.facebook.fr/"+selectedNeighbour.getName();
        textView_Link.setText(link);
        textView_aboutMe_desc.setText(selectedNeighbour.getAboutMe());
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
    private void favoriteButtonClicked(){
        selectedNeighbour.setIsFavorite(!selectedNeighbour.isFavorite());
        mApiService.toggleFavorite(selectedNeighbour);
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