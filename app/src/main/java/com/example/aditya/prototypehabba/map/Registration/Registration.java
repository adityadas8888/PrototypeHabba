package com.example.aditya.prototypehabba.map.Registration;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aditya.prototypehabba.R;

import java.util.HashMap;




public class Registration extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextName;
    private EditText editTextUsername;
    private EditText editTextPhone;
    private EditText editTextEmail;
    Spinner s1,s2;
    TextView textView;

    private Button buttonRegister;

    private static final String REGISTER_URL = "http://theprince.96.lt//android/register.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextUsername = (EditText) findViewById(R.id.editTextUserName);
        editTextPhone = (EditText) findViewById(R.id.editPhone);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        s1 = (Spinner)findViewById(R.id.spinner);
        s2 = (Spinner)findViewById(R.id.spinner2);

        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String name = s1.getSelectedItem().toString();
                int idSpinner = getResources().getIdentifier(name, "array", Registration.this.getPackageName());

                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(idSpinner));
                s2.setAdapter(spinnerArrayAdapter);
                String name1 = s2.getSelectedItem().toString();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        buttonRegister = (Button) findViewById(R.id.buttonRegister);

        buttonRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == buttonRegister){
            registerUser();
        }
    }

    private void registerUser() {
        String name = editTextName.getText().toString().trim().toLowerCase();
        if( editTextName.getText().toString().trim().equals(""))
            editTextName.setError( "Name is required!" );

        String clg = editTextUsername.getText().toString().trim().toLowerCase();

        if( editTextUsername.getText().toString().trim().equals(""))
            editTextUsername.setError( "College's name is required!" );

        String number = editTextPhone.getText().toString().trim().toLowerCase();

        if( editTextPhone.getText().toString().trim().equals(""))
            editTextPhone.setError( "Phone number is required!" );

        String email = editTextEmail.getText().toString().trim().toLowerCase();

        if( editTextEmail.getText().toString().trim().equals(""))
            editTextEmail.setError( "Email address is required!" );
        String ctg = s1.getSelectedItem().toString();

        String sctg = s2.getSelectedItem().toString();

        register(name,clg,number,email,ctg,sctg);
    }

    private void register(String name, String clg, String number, String email, String ctg,String sctg) {
        class RegisterUser extends AsyncTask<String, Void, String>{
            ProgressDialog loading;
            RegisterUserClass ruc = new RegisterUserClass();


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Registration.this, "Please Wait",null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(String... params) {

                HashMap<String, String> data = new HashMap<String,String>();
                data.put("name",params[0]);
                data.put("clg",params[1]);
                data.put("number",params[2]);
                data.put("email",params[3]);
                data.put("ctg",params[4]);
                data.put("sctg",params[5]);

                String result = ruc.sendPostRequest(REGISTER_URL,data);

                return  result;
            }
        }

        RegisterUser ru = new RegisterUser();
        ru.execute(name,clg,number,email,ctg,sctg);
    }
}