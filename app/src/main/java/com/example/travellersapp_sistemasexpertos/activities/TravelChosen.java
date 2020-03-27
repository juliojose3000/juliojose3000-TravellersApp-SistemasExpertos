package com.example.travellersapp_sistemasexpertos.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.travellersapp_sistemasexpertos.R;
import com.example.travellersapp_sistemasexpertos.database.DBHelper;
import com.example.travellersapp_sistemasexpertos.utilities.Data;
import com.example.travellersapp_sistemasexpertos.domain.Dates;
import com.example.travellersapp_sistemasexpertos.domain.TravelPackage;

public class TravelChosen extends BaseActivity {

    TextView textViewTravelName;

    TextView textViewTravelPrice;

    TextView textViewHotel;

    TextView textViewAirport;

    TextView textViewStartDate;

    TextView textViewEndDate;

    TextView textViewDuration;

    TextView textViewDescription;

    TextView textViewTouristType;

    TextView textViewRouteType;

    int idPackageTravel;

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

        textViewTravelPrice = findViewById(R.id.textView_price_travel_chosen);

        textViewHotel = findViewById(R.id.textView_hotel_travel_chosen);

        textViewAirport = findViewById(R.id.textView_airport_travel_chosen);

        textViewStartDate = findViewById(R.id.textView_start_date_travel_chosen);

        textViewEndDate = findViewById(R.id.textView_start_date_travel_chosen);

        textViewDuration = findViewById(R.id.textView_duration_travel_chosen);

        textViewDescription = findViewById(R.id.textView_description_travel_chosen);

        textViewTouristType = findViewById(R.id.textView_tourist_type_travel_chosen);

        textViewRouteType = findViewById(R.id.textView_route_type_travel_chosen);

        Bundle bundle = getIntent().getExtras();

        idPackageTravel = bundle.getInt("travelPackage");

        TravelPackage travelPackage = Data.getTravelPackageById(idPackageTravel);

        textViewTravelPrice.setText("Predio: $ "+travelPackage.getCost());

        textViewTravelName.setText(travelPackage.getName());

        textViewHotel.setText("Hotel: "+travelPackage.getHotel().getName());

        textViewAirport.setText("Aeropuerto: "+travelPackage.getAirport().getName());

        textViewStartDate.setText("Fecha de inicio: "+travelPackage.getStartDate());

        textViewEndDate.setText("Fecha de fin: "+travelPackage.getEndDate());

        textViewDuration.setText("Duración: "+travelPackage.getDuration());

        textViewDescription.setText("Descripción: "+travelPackage.getDescription());

        textViewTouristType.setText("Tipo de turista: "+travelPackage.getTouristType());

        textViewRouteType.setText("Tipo de ruta: "+travelPackage.getTypeOfRoute());

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

            Glide.with(TravelChosen.this)
                    .load(travelPackage.getListImages().get(i).getUrl())
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

    public void makePayment(){

        Intent i = new Intent(this, MadePayment.class);

        i.putExtra("idTravelPackage",idPackageTravel);

       final String fecha= dates.getDateOfToday();


        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {

                DBHelper.insertReservationPackage(Data.loggedUser,idPackageTravel,fecha);

                return null;
            }

        }.execute();

        startActivity(i);

    }

    public void makePayment(View v){

        if(!isThereInternetAccess()){
            Toast.makeText(this,"Compruebe su conexión a internet e intente de nuevo",Toast.LENGTH_SHORT).show();
            return;
        }

        AlertDialog diaBox = askOption();
        diaBox.show();

    }


    public AlertDialog askOption()
    {

        AlertDialog myQuittingDialogBox =new AlertDialog.Builder(this)
                //set message, title, and icon
                .setTitle("Confirme la reservación")
                .setMessage("¿Está seguro que desea reservar este paquete turístico?")


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
