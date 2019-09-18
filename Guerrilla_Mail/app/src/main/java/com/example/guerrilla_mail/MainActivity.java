package com.example.guerrilla_mail;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    String reply;
    String session_id;
    String count;
    String email_created;
    String email_address_timestamp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnEmail = findViewById(R.id.btn5);
        Button btnSessionID = findViewById(R.id.btn6);
        Button btnFetch = findViewById(R.id.btn4);
        Button btnSubmit = findViewById(R.id.btn1);
        Button btnCheck = findViewById(R.id.btn2);
        Button btnSet = findViewById(R.id.btn3);
        TextView txtEmail = findViewById(R.id.txtEmail);
        TextView txtID = findViewById(R.id.txtID);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String URL = "http://api.guerrillamail.com/ajax.php?f=get_email_address&lang=en";

                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(URL)
                        .build();
                client.newCall(request).enqueue(new Callback() {
                    @Override

                    public void onResponse(Call call, final Response response) throws IOException {
                        if (!response.isSuccessful()) {
                            throw new IOException("Unexpected code " + response);
                        } else {
                            reply = response.body().string();

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {


                                    try {

                                        //System.out.println(reply);

                                        JSONObject jsonObject = new JSONObject(reply);
                                        session_id = jsonObject.getString("sid_token");
                                        email_created = jsonObject.getString("email_addr");
                                        email_address_timestamp = jsonObject.getString("email_timestamp");
                                        txtEmail.setText(email_created);
                                        txtID.setText(session_id);

                                        //Toast("Session ID is: " + session_id, 1);
                                        //Toast("Email addr is: " + email_created, 1);

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                            });
                        }

                    }

                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                        return;
                    }


                });


            }
        });


        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String URL = "http://api.guerrillamail.com/ajax.php?f=check_email";

                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(URL)
                        .build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {

                    }

                    @Override

                    public void onResponse(Call call, final Response response) throws IOException {
                        if (!response.isSuccessful()) {
                            throw new IOException("Unexpected code " + response);
                        } else {
                            reply = response.body().string();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    Toast(reply, 1);



                                }
                            });

                        }

                    }

                });

            }

        });

        btnSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String URL = "http://api.guerrillamail.com/ajax.php?f=set_email_user&email_user=" + email_created;

                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(URL)
                        .build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {

                    }

                    @Override

                    public void onResponse(Call call, final Response response) throws IOException {
                        if (!response.isSuccessful()) {
                            throw new IOException("Unexpected code " + response);
                        } else {
                            reply = response.body().string();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    Toast(reply, 1);


                                }
                            });

                        }

                    }

                });

            }
        });

        btnFetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String URL = "http://api.guerrillamail.com/ajax.php?f=fetch_email&email_id" + email_created;

                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(URL)
                        .build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {

                    }

                    @Override

                    public void onResponse(Call call, final Response response) throws IOException {
                        if (!response.isSuccessful()) {
                            throw new IOException("Unexpected code " + response);
                        } else {
                            reply = response.body().string();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    Toast(reply, 1);


                                }
                            });

                        }

                    }

                });
            }
        });

        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Toast("Your email is: " + email_created, 1);
            }
        });

        btnSessionID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Toast("Your session id is: " + session_id, 1);
            }
        });



    }



    public void Toast(String msg, int Length) {

        // Length = 0 SHORT MSG, Length = 1 LONG MSG
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), msg, Length).show();
            }
        });

    }

}


