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
import com.example.travellersapp_sistemasexpertos.utilities.Dates;
import com.example.travellersapp_sistemasexpertos.domain.TouristDestination;
import com.example.travellersapp_sistemasexpertos.domain.TravelPackage;
import com.example.travellersapp_sistemasexpertos.utilities.Data;

import org.w3c.dom.Text;

public class PackageChosen extends BaseActivity {

    TextView textViewPackageName;

    TextView textViewPackagePrice;

    TextView textViewHotel;

    TextView textViewAirport;

    TextView textViewStartDate;

    TextView textViewEndDate;

    TextView textViewDuration;

    TextView textViewDescription;

    TextView textViewTouristType;

    TextView textViewRouteType;

    TextView textViewCantidadPersonas;

    int idPackageTravel;

    private HorizontalScrollView horizontalScrollView;

    private Dates dates;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_package_chosen);

        dates=new Dates();

        textViewPackageName = findViewById(R.id.textView_package_name);

        textViewPackagePrice = findViewById(R.id.textView_price_package_chosen);

        textViewHotel = findViewById(R.id.textView_hotel_package_chosen);

        textViewAirport = findViewById(R.id.textView_airport_package_chosen);

        textViewStartDate = findViewById(R.id.textView_start_date_package_chosen);

        textViewEndDate = findViewById(R.id.textView_start_date_package_chosen);

        textViewDuration = findViewById(R.id.textView_duration_package_chosen);

        textViewDescription = findViewById(R.id.textView_description_package_chosen);

        textViewTouristType = findViewById(R.id.textView_tourist_type_package_chosen);

        textViewRouteType = findViewById(R.id.textView_route_type_package_chosen);

        textViewCantidadPersonas = findViewById(R.id.textView_cantidad_personas);

        Bundle bundle = getIntent().getExtras();

        idPackageTravel = bundle.getInt("travelPackage");

        TravelPackage travelPackage = Data.getTravelPackageById(idPackageTravel);

        textViewPackagePrice.setText("Predio: $ "+travelPackage.getCost());

        textViewPackageName.setText(travelPackage.getName());

        textViewHotel.setText("Hotel: "+travelPackage.getHotel().getName());

        textViewAirport.setText("Aeropuerto: "+travelPackage.getAirport().getName());

        textViewStartDate.setText("Fecha de inicio: "+travelPackage.getStartDate());

        textViewEndDate.setText("Fecha de fin: "+travelPackage.getEndDate());

        textViewDuration.setText("Duración: "+travelPackage.getDuration());

        textViewDescription.setText("Descripción: "+travelPackage.getDescription());

        textViewTouristType.setText("Tipo de turista: "+travelPackage.getTouristType());

        textViewRouteType.setText("Tipo de ruta: "+travelPackage.getTypeOfRoute());

        textViewCantidadPersonas.setText("Cantidad de personas: "+travelPackage.getNumberOfPersons());

        RequestOptions requestOptions = new RequestOptions();

        requestOptions.placeholder(R.drawable.preview);


        //----------------------------------------------------------------------------------------//

        LinearLayout gallery = findViewById(R.id.linear_layout_gallery);

        horizontalScrollView = findViewById(R.id.horizontal_scroll_view);

        LayoutInflater inflater = LayoutInflater.from(this);

        View viewImages;

        for(int i=0; i<travelPackage.getTouristDestinations().size(); i++){

            viewImages = inflater.inflate(R.layout.tourist_destiny, gallery, false);

            ImageView imageView =  viewImages.findViewById(R.id.imageView_tourist_destiny);

            TextView textViewTouristDestinyName = viewImages.findViewById(R.id.textView_tourist_destiny_name);

            final TouristDestination touristDestination = travelPackage.getTouristDestinations().get(i);

            Glide.with(PackageChosen.this)
                    .load(travelPackage.getTouristDestinations().get(i).getListImages().get(0).getUrl())
                    .apply(requestOptions)
                    .into(imageView);

            textViewTouristDestinyName.setText(travelPackage.getTouristDestinations().get(i).getName());

            imageView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {

                    if(!isThereInternetAccess()){
                        Toast.makeText(PackageChosen.this,"Compruebe su conexión a internet e intente de nuevo",Toast.LENGTH_SHORT).show();
                        return;
                    }

                    int idTouristDestination = touristDestination.getIdTouristDestination();

                    Intent i = new Intent(PackageChosen.this, TravelChosen.class);

                    i.putExtra("tourist_destination", idTouristDestination);

                    startActivity(i);

                }
            });

            gallery.addView(viewImages);

        }

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

        if(Data.loggedUser==null){

            AlertDialog diaBox = askOption(
                    "No ha iniciado sesión","¿Desea iniciar sesión para poder realizar la reservación?",
                    "Aceptar","Cancelar","inisiarSesion",this);

            diaBox.show();

        }else {

            AlertDialog diaBox = askOption(
                    "Confirme la reservación", "¿Está seguro que desea reservar este paquete turístico?",
                    "Aceptar", "Cancelar", "makePayment", this);

            diaBox.show();

        }


    }

}
