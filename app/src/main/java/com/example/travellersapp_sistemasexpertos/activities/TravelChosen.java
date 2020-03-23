package com.example.travellersapp_sistemasexpertos.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
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
import com.example.travellersapp_sistemasexpertos.R;
import com.example.travellersapp_sistemasexpertos.database.Data;
import com.example.travellersapp_sistemasexpertos.domain.TravelPackage;
import com.squareup.picasso.Picasso;

public class TravelChosen extends BaseActivity {

    //ImageView imageView;

    TextView textViewTravelName;

    TextView textViewTravelPrice;

    TextView textViewHotel;

    TextView textViewAirport;

    int idPackageTravel;

    private HorizontalScrollView horizontalScrollView;

    private Button btnstop, btnplay;
    private VideoView videoView;
    private MediaController mediacontroller;
    private Uri uri;
    private boolean firstTime = true;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_travel_chosen);

        textViewTravelName = findViewById(R.id.textView_travel_name);

        textViewTravelPrice = findViewById(R.id.textView_price);

        textViewHotel = findViewById(R.id.textView_hotel);

        textViewAirport = findViewById(R.id.textView_airport);

        Bundle bundle = getIntent().getExtras();

        idPackageTravel = bundle.getInt("travelPackage");

        TravelPackage travelPackage = Data.getTravelPackageById(idPackageTravel);

        textViewTravelPrice.setText("$ "+travelPackage.getCost());

        textViewTravelName.setText(travelPackage.getName());

        textViewHotel.setText("Hotel: "+travelPackage.getHotel().getName());

        textViewAirport.setText("Aeropuerto: "+travelPackage.getAirport().getName());

        RequestOptions requestOptions = new RequestOptions();

        requestOptions.placeholder(R.drawable.preview);




        //----------------------------------------------------------------------------------------//

        LinearLayout gallery = findViewById(R.id.linear_layout_gallery);

        horizontalScrollView = findViewById(R.id.horizontal_scroll_view);

        LayoutInflater inflater = LayoutInflater.from(this);

        View viewImages;

        for(int i=0; i<travelPackage.getListImages().size(); i++){

            viewImages = inflater.inflate(R.layout.images, gallery, false);

            ImageView imageView =  viewImages.findViewById(R.id.imageView_images);

            //todo probar

            Glide.with(TravelChosen.this)
                    .load(travelPackage.getListImages().get(i).getUrl())
                    .apply(requestOptions)
                    .into(imageView);

            gallery.addView(viewImages);

        }

        //----------------------------------------------------------------------------------------//

        viewImages = inflater.inflate(R.layout.video, gallery, false);

        progressBar = viewImages.findViewById(R.id.progrss);
        btnstop = viewImages.findViewById(R.id.btnstop);
        btnplay = viewImages.findViewById(R.id.btnplay);
        videoView = viewImages.findViewById(R.id.videoView);

        mediacontroller = new MediaController(this);
        mediacontroller.setAnchorView(videoView);
        String uriPath = travelPackage.getVideoURL(); //update package name
        uri = Uri.parse(uriPath);

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(firstTime){
                    videoView.start();
                }
            }
        });

        btnstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.pause();
            }
        });

        btnplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(firstTime){

                    firstTime = false;
                    progressBar.setVisibility(View.VISIBLE);
                    videoView.setMediaController(mediacontroller);
                    videoView.setVideoURI(uri);
                    videoView.requestFocus();

                }


                videoView.start();
            }
        });

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            // Close the progress bar and play the video
            public void onPrepared(MediaPlayer mp) {
                progressBar.setVisibility(View.GONE);
            }
        });


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

    public void makePayment(){

        Intent i = new Intent(this, MadePayment.class);

        i.putExtra("idTravelPackage",idPackageTravel);

        startActivity(i);

    }

    public void makePayment(View v){

        AlertDialog diaBox = askOption();
        diaBox.show();

    }


    public AlertDialog askOption()
    {

        AlertDialog myQuittingDialogBox =new AlertDialog.Builder(this)
                //set message, title, and icon
                .setTitle("Confirme la compra")
                .setMessage("¿Está seguro que desea realizar la compra de este paquete turístico?")


                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {

                        makePayment();

                        dialog.dismiss();
                    }

                })



                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //do nothing
                        dialog.dismiss();

                    }
                })
                .create();
        return myQuittingDialogBox;

    }


}
