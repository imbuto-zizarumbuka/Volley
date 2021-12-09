package com.example.crud_lab;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ContactHolder extends RecyclerView.ViewHolder {
    // each data item is just a string in this case
//    public TextView avatar_url;
    public  TextView login;
    public Button save;
//    public TextView phone;
    //initilising Custom row in constructor
    public ContactHolder(View view) {
        super(view);
//        avatar_url =(TextView)view.findViewById(R.id.firstname);
        login =(TextView)view.findViewById(R.id.loginN);
        save = view.findViewById(R.id.saveB);
//        phone=(TextView)view.findViewById(R.id.telephone);
    }

}
