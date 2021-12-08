package com.example.crud_lab;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ContactHolder extends RecyclerView.ViewHolder {
    // each data item is just a string in this case
    public TextView fname;
    public  TextView lname;
    public TextView phone;
    //initilising Custom row in constructor
    public ContactHolder(View view) {
        super(view);
        fname=(TextView)view.findViewById(R.id.firstname);
        lname=(TextView)view.findViewById(R.id.lastname);
        phone=(TextView)view.findViewById(R.id.telephone);
    }

}
