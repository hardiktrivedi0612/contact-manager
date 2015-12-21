package com.utdallas.hpt150030.mycontactmanager;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Hardik on 11/2/2015.
 */

/*
* This class is the custom adapter that is used to display the contact list in our custom format.
* This adapter helps to display the name of the contact, the phone number of the contact, appropriate message if the phone number is not present
* and it also helps in assigning a icon to the contact.
*/
public class MyContactsAdapter extends ArrayAdapter<ContactBean> {

    private Context mContext;
    private List<ContactBean> list;

    public MyContactsAdapter(Context context, int textViewResourceId, List<ContactBean> objects) {
        super(context, textViewResourceId, objects);
        this.mContext = context;
        this.list = objects;
    }

    class ViewHolder {
        TextView name;
        TextView phoneNumber;
        ImageView icon;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_contacts, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.nameTextView);
            viewHolder.phoneNumber = (TextView) convertView.findViewById(R.id.phoneNumberTextView);
            viewHolder.icon = (ImageView) convertView.findViewById(R.id.icon);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ContactBean bean = ContactList.list.get(position);
        if (bean != null) {

            int value = (Math.abs(bean.getFirstName().hashCode()) % 5) + 1;
            switch (value) {
                case 1:
                    viewHolder.icon.setImageResource(R.mipmap.contact1);
                    break;
                case 2:
                    viewHolder.icon.setImageResource(R.mipmap.contact2);
                    break;
                case 3:
                    viewHolder.icon.setImageResource(R.mipmap.contact3);
                    break;
                case 4:
                    viewHolder.icon.setImageResource(R.mipmap.contact4);
                    break;
                case 5:
                    viewHolder.icon.setImageResource(R.mipmap.contact5);
                    break;

            }

            viewHolder.name.setText(bean.getFirstName() + " " + bean.getLastName());

            if (!bean.getPhoneNumber().equalsIgnoreCase("")) {
                viewHolder.phoneNumber.setTextColor(ContextCompat.getColor(mContext, android.R.color.tertiary_text_dark));
                viewHolder.phoneNumber.setText("Phone no: " + bean.getPhoneNumber());
            } else {
                viewHolder.phoneNumber.setTextColor(ContextCompat.getColor(mContext, android.R.color.holo_orange_light));
                viewHolder.phoneNumber.setText("Phone number not available");
            }
        }
        return convertView;
    }
}
