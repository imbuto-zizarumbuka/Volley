package com.example.crud_lab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    List<Contact> contacts;
    RecyclerView recyclerView;
    ContactAdapter adapter;
    DatabaseHandler dbHandler;
    Intent intent;
    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Log.d("TestingD","button clicked");
            dbHandler = new DatabaseHandler(MainActivity.this);
            intent = new Intent(MainActivity.this, SavedActivity.class);
            Contact contact = (Contact) view.getTag();
            dbHandler.addContact(contact);
            intent.putExtra("contact",contact);
            startActivity(intent);
            Toast.makeText(MainActivity.this, "message", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contacts);
        recyclerView=(RecyclerView) findViewById(R.id.recyclerView);
        //recyclerView.setAdapter(adapter);
        loadList();




        Button viewSaved = findViewById(R.id.viewSaved);
        viewSaved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SavedActivity.class);
                startActivity(intent);
            }
        });
    }
    public void displayContacts(){
        Log.d("TEST","DADADADADAD");
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);
    }
    public void loadLocalList(){
        contacts=new ArrayList<Contact>();
        contacts.add(new Contact("test","tets","test"));
        //contacts.add(contact);
        adapter=new ContactAdapter(contacts, listener);
        displayContacts();
    }
    public void loadList(){
        //contacts=new ArrayList<Contact>();

        ArrayList<Contact> contacts = new ArrayList<>();
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        String url="https://api.github.com/users";

        //"https://task-force-backend-challenge.herokuapp.com/api/v1/user/list";
        //https://challengebackends.herokuapp.com/api/v1/albums/2/photos
        //https://api.github.com/users/
        StringRequest request=new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                       // Log.d("RESPONSE",response.toString());
                        try {
                            //JSONObject jsonObject=new JSONObject(response);
                            JSONArray jsonArray= new JSONArray(response);

                            Log.d("array:",jsonArray.toString());

                           // JSONArray jsonArray = jsonObject.getJSONArray("employees");
                            //jsonArray =
                            for(int i=0;i<jsonArray.length();i++){
                                JSONObject res=jsonArray.getJSONObject(i);

                                Log.d("id",res.getString("node_id"));

                                String login=(String) res.getString("login");
                                String avatar_url=(String) res.getString("avatar_url");
                                String html_url=res.getString("html_url");

                                Contact contact=new Contact(avatar_url,login,html_url);
                                contacts.add(contact);
                            }
                            adapter=new ContactAdapter(contacts, listener);
                            displayContacts();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error",error.toString());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return super.getParams();
            }
        };
        requestQueue.add(request);
    }

}