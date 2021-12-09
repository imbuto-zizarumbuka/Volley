package com.example.crud_lab;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactHolder> {
    private Boolean isDelete = false;
    private View.OnClickListener listener;
    private List<Contact> contactList;

    public ContactAdapter(List<Contact> contactList){
        this.contactList=contactList;
    }
    public ContactAdapter(List<Contact> contactList, View.OnClickListener listener){
        this.contactList=contactList;
        this.listener = listener;
    }
    public ContactAdapter(List<Contact> contactList, View.OnClickListener listener, Boolean isDelete){
        this.contactList=contactList;
        this.listener = listener;
        this.isDelete = isDelete;
    }


    @Override
    public ContactHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_contact ,parent,false);
        return new ContactHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactHolder holder, int position) {
        Contact contact=contactList.get(position);
//        holder.avatar_url.setText(contact.getFirstname());
        holder.save.setTag(contact);
        if(listener != null)
            holder.save.setOnClickListener(listener);

        holder.login.setText(contact.getLogin());
        if(isDelete)
            holder.save.setText("DELETE");


//        holder.phone.setText(contact.getPhone());
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }
}
