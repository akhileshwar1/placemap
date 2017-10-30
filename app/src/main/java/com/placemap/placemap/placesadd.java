package com.placemap.placemap;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.EditText;


public class placesadd extends AppCompatActivity {
    ProgressDialog pDialog;
    googlePlaces googlePlaces1;
    String message;
    String message1;
    String message2;
    String message3;
    String message4;
    placeadd p;
    AlertDialogManager alert = new AlertDialogManager();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placesadd);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });



    }


    class AddPlaces extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(placesadd.this);
            pDialog.setMessage(Html.fromHtml("<b>Search</b><br/>Adding Places..."));
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        protected String doInBackground(String... args) {
            // creating Places class object
            googlePlaces1 = new googlePlaces();

            try {

                p = googlePlaces1.add(message, message1, message2, message3, message4);


            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(String file_url) {
            pDialog.dismiss();
            runOnUiThread(new Runnable() {
                public void run() {
                    if (p == null) {
                        alert.showAlertDialog(placesadd.this, "Near Places",
                                "null!",
                                false);
                    } else {
                        String status = p.status;
                        if (status.equals("OK")) {
                            // Zero results found
                            alert.showAlertDialog(placesadd.this, "Near Places",
                                    "DONE!",
                                    false);
                        } else if (status.equals("UNKNOWN_ERROR")) {
                            alert.showAlertDialog(placesadd.this, "Places Error ",
                                    "Sorry unknown error occured.",
                                    false);
                        } else if (status.equals("OVER_QUERY_LIMIT")) {
                            alert.showAlertDialog(placesadd.this, "Places Error",
                                    "Sorry query limit to google places is reached",
                                    false);
                        } else if (status.equals("INVALID_REQUEST")) {
                            alert.showAlertDialog(placesadd.this, "Places Error",
                                    "Sorry error occured. Invalid Request",
                                    false);
                        } else if (status.equals("REQUEST_DENIED")) {
                            alert.showAlertDialog(placesadd.this, "Places Error",
                                    "Sorry error occured. Request is denied",
                                    false);
                        }
                    }
                }
            });


        }
    }
    public void sendMessage(View view)
    {
        EditText editText = (EditText) findViewById(R.id.editText);
        message = editText.getText().toString();
        EditText editText1 = (EditText) findViewById(R.id.editText2);
        message1 = editText1.getText().toString();
        EditText editText2 = (EditText) findViewById(R.id.editText3);
        message2 = editText2.getText().toString();
        EditText editText3 = (EditText) findViewById(R.id.editText4);
        message3 = editText3.getText().toString();
        EditText editText4 = (EditText) findViewById(R.id.editText5);
        message4 = editText4.getText().toString();
        new AddPlaces().execute();

    }
}
