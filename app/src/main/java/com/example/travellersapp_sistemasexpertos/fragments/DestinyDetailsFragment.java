package com.example.travellersapp_sistemasexpertos.fragments;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.travellersapp_sistemasexpertos.MainActivity;
import com.example.travellersapp_sistemasexpertos.R;
import com.example.travellersapp_sistemasexpertos.activities.MapsActivity;
import com.example.travellersapp_sistemasexpertos.domain.TouristDestination;
import com.example.travellersapp_sistemasexpertos.utilities.Data;
import com.example.travellersapp_sistemasexpertos.utilities.Dates;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DestinyDetailsFragment extends Fragment {

    private TextView textViewTravelName;

    private TextView textViewDescription;

    private TextView textViewDestinyUbication;

    private int idTouristDestination;

    private HorizontalScrollView horizontalScrollView;

    private VideoView videoView;

    private MediaController mediacontroller;

    private Uri uri;

    private boolean firstTime = true;

    private ProgressBar progressBar;

    private ImageView imageViewPlay;

    private Dates dates;

    private Button backButton;

    private Bundle bundle;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        bundle = getArguments();

        if(bundle != null){

            MainActivity.SAVED_STATE_DESTINY_DESTAILS_FRAGMENT = bundle;

        }else{

            bundle = MainActivity.SAVED_STATE_DESTINY_DESTAILS_FRAGMENT;

        }

        MainActivity.LAST_FRAGMENT = MainActivity.DESTINY_DETAILS_FRAGMENT;

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_travel_chosen, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        dates=new Dates();

        textViewTravelName = getView().findViewById(R.id.textView_travel_name);

        textViewDescription = getView().findViewById(R.id.textView_description_travel_chosen);

        textViewDestinyUbication = getView().findViewById(R.id.textView_watch_destiny_ubication);

        textViewDestinyUbication.setText(Html.fromHtml("<u>Ver ubicación en Google Maps</u>"));

        idTouristDestination = bundle.getInt("tourist_destination");

        final TouristDestination touristDestination = Data.getTouristDestinationById(idTouristDestination);

        textViewTravelName.setText(touristDestination.getName());

        textViewDescription.setText("Descripción: "+touristDestination.getDescription());

        textViewDestinyUbication.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(getActivity(), MapsActivity.class);//me dirijo a la interfaz de inicio

                        double latitud = touristDestination.getLatitud();
                        double longitud =  touristDestination.getLogintud();
                        String title = touristDestination.getName();

                        i.putExtra("latitud", latitud);
                        i.putExtra("longitud", longitud);
                        i.putExtra("title", title);

                        startActivity(i);
                    }
                }
        );



        RequestOptions requestOptions = new RequestOptions();

        requestOptions.placeholder(R.drawable.preview);

        //----------------------------------------------------------------------------------------//

        LinearLayout gallery = getView().findViewById(R.id.linear_layout_gallery);

        horizontalScrollView = getView().findViewById(R.id.horizontal_scroll_view);

        LayoutInflater inflater = LayoutInflater.from(getActivity());

        View viewImages;

        for(int i=0; i<touristDestination.getListImages().size(); i++){

            viewImages = inflater.inflate(R.layout.images, gallery, false);

            ImageView imageView =  viewImages.findViewById(R.id.imageView_images);

            Glide.with(getActivity())
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

        mediacontroller = new MediaController(getActivity());
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


        backButton = getView().findViewById(R.id.button_back);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                back();

            }
        });


    }

    private void back(){

        getFragmentManager().beginTransaction().remove(this).commit();

        getFragmentManager().beginTransaction().replace(R.id.fragment_container, new PackageDetailsFragment()).commit();

    }


}
