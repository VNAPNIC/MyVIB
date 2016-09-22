package com.vnapnic.myvib.fragments.listphone;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.vnapnic.myvib.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by ThoNguyenHuu on 01/08/2016.
 */
public class ContactsAdapter extends ArrayAdapter<ContactsModel> {

    // style == 0 : title
    // style == 1 : content

    private LayoutInflater inflater;
    private Context context;
    private ArrayList<ContactsModel> arrContacts;
    private HandlerClick click;

    public ContactsAdapter(HandlerClick click, Context context, int resource, ArrayList<ContactsModel> arrContacts) {
        super(context, resource, arrContacts);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
        this.arrContacts = arrContacts;
        this.click = click;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (arrContacts.get(position).getStyle() == 0) {
            convertView = inflater.inflate(R.layout.item_title_contacs, parent, false);
            TextView textView = (TextView) convertView.findViewById(R.id.tv_title_contacts);
            textView.setText(arrContacts.get(position).getContactsName());
        } else {
            convertView = inflater.inflate(R.layout.item_contacs, parent, false);
            CircleImageView imageView = (CircleImageView) convertView.findViewById(R.id.imv_profile_image);
            TextView tvName = (TextView) convertView.findViewById(R.id.tv__contacts_name);
            TextView tvPhoneNum = (TextView) convertView.findViewById(R.id.tv_contacts_phone_number);

            if (arrContacts.get(position).getImage() == null) {
                imageView.setImageResource(R.drawable.top_up_icon_yellow);
            } else {
                imageView.setImageURI(Uri.parse(arrContacts.get(position).getImage()));
            }

            tvName.setText(arrContacts.get(position).getContactsName());
            tvPhoneNum.setText(arrContacts.get(position).getContactsPhoneNumber());
            convertView.setOnClickListener(new Click(arrContacts.get(position)));
        }

        return convertView;
    }


    private class Click implements View.OnClickListener {
        private ContactsModel contactsModel;

        public Click(ContactsModel contactsModel) {
            this.contactsModel = contactsModel;
        }

        @Override
        public void onClick(View v) {
            click.onItemListViewClickListener(contactsModel);
        }
    }
}
