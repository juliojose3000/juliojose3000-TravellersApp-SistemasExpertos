package com.example.travellersapp_sistemasexpertos.activities;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.travellersapp_sistemasexpertos.R;
import com.example.travellersapp_sistemasexpertos.domain.TouristDestination;
import com.example.travellersapp_sistemasexpertos.utilities.Data;
import com.example.travellersapp_sistemasexpertos.utilities.Dates;

public class TravelChosen extends BaseActivity {

    TextView textViewTravelName;

    TextView textViewDescription;

    int idTouristDestination;

    private HorizontalScrollView horizontalScrollView;

    private VideoView videoView;

    private MediaController mediacontroller;

    private Uri uri;

    private boolean firstTime = true;

    private ProgressBar progressBar;

    private ImageView imageViewPlay;

    private Dates dates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_travel_chosen);

        dates=new Dates();

        textViewTravelName = findViewById(R.id.textView_travel_name);

        textViewDescription = findViewById(R.id.textView_description_travel_chosen);

        Bundle bundle = getIntent().getExtras();

        idTouristDestination = bundle.getInt("tourist_destination");

        TouristDestination touristDestination = Data.getTouristDestinationById(idTouristDestination);

        textViewTravelName.setText(touristDestination.getName());

        textViewDescription.setText("Descripción: "+touristDestination.getDescription());

        RequestOptions requestOptions = new RequestOptions();

        requestOptions.placeholder(R.drawable.preview);

        //----------------------------------------------------------------------------------------//

        LinearLayout gallery = findViewById(R.id.linear_layout_gallery);

        horizontalScrollView = findViewById(R.id.horizontal_scroll_view);

        LayoutInflater inflater = LayoutInflater.from(this);

        View viewImages;

        for(int i=0; i<touristDestination.getListImages().size(); i++){

            viewImages = inflater.inflate(R.layout.images, gallery, false);

            ImageView imageView =  viewImages.findViewById(R.id.imageView_images);

            Glide.with(TravelChosen.this)
                    .load(touristDestination.getListImages().get(i).getUrl())
                    .apply(requestOptions)
                    .into(imageView);

            gallery.addView(viewImages);

        }

        //----------------------------------------------------------------------------------------//

        viewImages = inflater.inflate(R.layout.video, gallery, false);

        progressBar = viewImages.findViewById(R.id.progrss);
        videoView = viewImages.findViewById(R.id.videoView);
        imageViewPlay = viewImages.findViewById(R.id.imageView_play);

        mediacontroller = new MediaController(this);
        mediacontroller.setAnchorView(videoView);
        String uriPath = touristDestination.getURLVideo(); //update package name
        uri = Uri.parse(uriPath);

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(firstTime){
                    videoView.start();
                }
            }
        });

        imageViewPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(firstTime){firstTime = false;
                    progressBar.setVisibility(View.VISIBLE);
                    videoView.setMediaController(mediacontroller);
                    videoView.setVideoURI(uri);
                    videoView.requestFocus();

                }
                imageViewPlay.setVisibility(View.INVISIBLE);

                videoView.start();
            }
        });

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            // Close the progress bar and play the video
            public void onPrepared(MediaPlayer mp) {
                progressBar.setVisibility(View.GONE);
            }
        });

        //----------------------------------------------------------------------------------------//

        gallery.addView(viewImages);

        horizontalScrollView.postDelayed(new Runnable() {

            @Override
            public void run() {
                horizontalScrollView.fullScroll(HorizontalScrollView.FOCUS_LEFT);
            }
        }, 10);


        //----------------------------------------------------------------------------------------//
    }


    public void back(View v){

        finish();

    }


}
