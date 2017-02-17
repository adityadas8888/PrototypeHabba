package com.example.aditya.prototypehabba.map.Scroll;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.aditya.prototypehabba.R;
import com.example.aditya.prototypehabba.map.Registration.Registration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Scroll extends AppCompatActivity {
    String st;
    public String url;
    public int val;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll);
        contactList = new ArrayList<>();
        button = (Button)findViewById(R.id.button3);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent5 = new Intent(Scroll.this,Registration.class);
                startActivity(intent5);
            }
        });

        Intent mIntent=getIntent();
        val=mIntent.getIntExtra("num",-1);
        url = "http://theprince.96.lt/android/json.php?id="+val;
        new GetContacts().execute();
        //Toast.makeText(getBaseContext(),""+val,Toast.LENGTH_LONG).show();

    }
    private String TAG = Scroll.class.getSimpleName();

    private ProgressDialog pDialog;
    //private ListView lv;

    // URL to get contacts JSON


    private static String name;
    private static String about;
    private static String rules;
    private static String amount;
    private static String image;
    private static String number;
    ArrayList<HashMap<String, String>> contactList;
    TextView tv1,tv2,tv3,tv4,tv5;
    ImageView imageView;


    protected void onPause(){
        super.onPause();
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
    protected void onResume(){
        super.onResume();
        if (pDialog.isShowing())
            pDialog.dismiss();
        new GetContacts().execute();
    }
    private class GetContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(Scroll.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url);
            tv1=(TextView)findViewById(R.id.name);
            tv2=(TextView)findViewById(R.id.about);
            tv3=(TextView)findViewById(R.id.rules);
            tv4=(TextView)findViewById(R.id.amount);
            tv5=(TextView)findViewById(R.id.number);
            imageView = (ImageView)findViewById(R.id.imageView);
            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    JSONArray contacts = jsonObj.getJSONArray("result");

                    JSONObject c = contacts.getJSONObject(0);
                    name = c.getString("name");
                    about = c.getString("about");
                    rules = c.getString("rules");
                    amount = c.getString("amount");
                    image = c.getString("image");
                    number = c.getString("number");

                    HashMap<String, String> contact = new HashMap<>();

                    contact.put("name", name);
                    contact.put("about", about);
                    contact.put("rules", rules);
                    contact.put("amount", amount);
                    contact.put("image",image);
                    contact.put("number",number);

                    contactList.add(contact);

                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tv1.setText(name);
                    tv2.setText(about);
                    tv3.setText(rules);
                    tv4.setText(amount);
                    tv5.setText(number);
                    Glide.with(getBaseContext()).load(image).into(imageView);

                }
            });
        }

    }
}