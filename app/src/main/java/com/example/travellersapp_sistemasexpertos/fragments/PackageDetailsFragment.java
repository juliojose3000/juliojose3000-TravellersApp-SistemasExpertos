package com.example.travellersapp_sistemasexpertos.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.travellersapp_sistemasexpertos.MainActivity;
import com.example.travellersapp_sistemasexpertos.R;
import com.example.travellersapp_sistemasexpertos.activities.Login;
import com.example.travellersapp_sistemasexpertos.database.DBHelper;
import com.example.travellersapp_sistemasexpertos.domain.TouristDestination;
import com.example.travellersapp_sistemasexpertos.domain.TravelPackage;
import com.example.travellersapp_sistemasexpertos.utilities.Data;
import com.example.travellersapp_sistemasexpertos.utilities.Dates;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class PackageDetailsFragment extends Fragment {

    private TextView textViewPackageName;

    private TextView textViewPackagePrice;

    private TextView textViewHotel;

    private TextView textViewAirport;

    private TextView textViewStartDate;

    private TextView textViewEndDate;

    private TextView textViewDuration;

    private TextView textViewDescription;

    private TextView textViewTouristType;

    private TextView textViewRouteType;

    private TextView textViewCantidadPersonas;

    private int idPackageTravel;

    private HorizontalScrollView horizontalScrollView;

    private Dates dates;

    private Button backButton;

    private Button reserveButton;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();

        if(bundle != null){//save state

            MainActivity.SAVED_STATE_PACKAGE_DETAILS_FRAGMENT = bundle;

        }else{//load state

            bundle = MainActivity.SAVED_STATE_PACKAGE_DETAILS_FRAGMENT;

        }

        idPackageTravel = bundle.getInt("travelPackage");

        MainActivity.LAST_FRAGMENT = MainActivity.PACKAGES_DETAILS_FRAGMENT;

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_package_chosen, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        dates=new Dates();

        textViewPackageName = getView().findViewById(R.id.textView_package_name);

        textViewPackagePrice = getView().findViewById(R.id.textView_price_package_chosen);

        textViewHotel = getView().findViewById(R.id.textView_hotel_package_chosen);

        textViewAirport = getView().findViewById(R.id.textView_airport_package_chosen);

        textViewStartDate = getView().findViewById(R.id.textView_start_date_package_chosen);

        textViewEndDate = getView().findViewById(R.id.textView_start_date_package_chosen);

        textViewDuration = getView().findViewById(R.id.textView_duration_package_chosen);

        textViewDescription = getView().findViewById(R.id.textView_description_package_chosen);

        textViewTouristType = getView().findViewById(R.id.textView_tourist_type_package_chosen);

        textViewRouteType = getView().findViewById(R.id.textView_route_type_package_chosen);

        textViewCantidadPersonas = getView().findViewById(R.id.textView_cantidad_personas);

        TravelPackage travelPackage = Data.getTravelPackageById(idPackageTravel);

        textViewPackagePrice.setText("Precio: "+travelPackage.getCostWithFormat());

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

        LinearLayout gallery = getView().findViewById(R.id.linear_layout_gallery);

        horizontalScrollView = getView().findViewById(R.id.horizontal_scroll_view);

        LayoutInflater inflater = LayoutInflater.from(getActivity());

        View viewImages;

        for(int i=0; i<travelPackage.getTouristDestinations().size(); i++){

            viewImages = inflater.inflate(R.layout.tourist_destiny, gallery, false);

            ImageView imageView =  viewImages.findViewById(R.id.imageView_tourist_destiny);

            TextView textViewTouristDestinyName = viewImages.findViewById(R.id.textView_tourist_destiny_name);

            final TouristDestination touristDestination = travelPackage.getTouristDestinations().get(i);

            Glide.with(getActivity())
                    .load(travelPackage.getTouristDestinations().get(i).getListImages().get(0).getUrl())
                    .apply(requestOptions)
                    .into(imageView);

            textViewTouristDestinyName.setText(travelPackage.getTouristDestinations().get(i).getName());

            imageView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {

                    int idTouristDestination = touristDestination.getIdTouristDestination();

                    Fragment fragment = new DestinyDetailsFragment();

                    Bundle bundle = new Bundle();

                    bundle.putInt("tourist_destination", idTouristDestination);

                    fragment.setArguments(bundle);

                    getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();

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

        backButton = getView().findViewById(R.id.button_back);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back();
            }
        });

        reserveButton = getView().findViewById(R.id.button_reserve);

        reserveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doPayment();
            }
        });



    }


    public void back(){

        getFragmentManager().beginTransaction().remove(this).commit();

        getFragmentManager().beginTransaction().replace(R.id.fragment_container, new TravellsResultsFragment()).commit();

    }

    private void makePayment(){

        final String fecha= dates.getDateOfToday();

        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {

                DBHelper.insertReservationPackage(Data.loggedUser,idPackageTravel,fecha);

                return null;
            }

        }.execute();

        Fragment fragment = new ReservationDetailsFragment();

        Bundle bundle = new Bundle();

        bundle.putInt("idTravelPackage", idPackageTravel);

        fragment.setArguments(bundle);

        getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();


    }

    public void doPayment(){

        if(Data.loggedUser==null){

            AlertDialog diaBox = askOption(
                    "No ha iniciado sesión","¿Desea iniciar sesión para poder realizar la reservación?",
                    "Aceptar","Cancelar","inisiarSesion");

            diaBox.show();

        }else {

            AlertDialog diaBox = askOption(
                    "Confirme la reservación", "¿Está seguro que desea reservar este paquete turístico?",
                    "Aceptar", "Cancelar", "makePayment");

            diaBox.show();

        }


    }




    public AlertDialog askOption(String title, String message, String positive, String negative, final String function)
    {

        AlertDialog myQuittingDialogBox =new AlertDialog.Builder(getActivity())
                //set message, title, and icon
                .setTitle(title)
                .setMessage(message)


                .setPositiveButton(positive, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {


                        switch (function){
                            case "makePayment":
                                makePayment();
                                break;
                            case "inisiarSesion":
                                Intent i = new Intent(getActivity(), Login.class);
                                i.putExtra("whereIGo","makePayment");
                                startActivity(i);
                                break;
                        }

                        dialog.dismiss();
                    }

                })

                .setNegativeButton(negative, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //do nothing
                        dialog.dismiss();

                    }
                })
                .create();
        return myQuittingDialogBox;

    }







}
