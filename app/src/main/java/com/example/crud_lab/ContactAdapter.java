package com.example.crud_lab;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactHolder> {
    private List<Contact> contactList;

    public ContactAdapter(List<Contact> contactList){
        this.contactList=contactList;
    }

    @Override
    public ContactHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_contact ,parent,false);
        return new ContactHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactHolder holder, int position) {
        Contact contact=contactList.get(position);
        holder.fname.setText(contact.getFirstname());
        holder.lname.setText(contact.getLastname());
        holder.phone.setText(contact.getPhone());
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }
}
