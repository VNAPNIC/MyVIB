package com.vnapnic.myvib.fragments.listphone;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.vnapnic.myvib.MainActivity;
import com.vnapnic.myvib.R;
import com.vnapnic.myvib.common.ToolbarTyper;
import com.vnapnic.myvib.fragments.topup.TopUpAddNewFragment;
import com.vnapnic.myvib.utils.Logger;

import java.util.ArrayList;

/**
 * Created by vnapnic on 7/10/2016.
 */
public class PhoneSelectListFragment extends Fragment implements HandlerClick {

    public static final String TAG = "ListContacts";
    private String title;
    private ListView lvContacts;
    private ContactsAdapter adapter;
    private ArrayList<ContactsModel> arrContacts;
    private GetContacts getContacts;
    private GetMail getMail;
    private String[] arrTitle;
    private LinearLayout llAreaTitle;
    private static final String TYPE = "key.type";
    private static final String TYPE_DATA = "key.type.data";
    private int type;
    private int typeData = 0;

    public static PhoneSelectListFragment newInstance(int type, int typeData) {
        PhoneSelectListFragment fragment = new PhoneSelectListFragment();
        fragment.setArguments(newBundle(type, typeData));
        return fragment;
    }

    private static Bundle newBundle(int type, int typeData) {
        Bundle bundle = new Bundle();
        bundle.putInt(TYPE, type);
        bundle.putInt(TYPE_DATA, typeData);
        return bundle;
    }

    //  lifecycle
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            initDataFromBundle(savedInstanceState);
        } else {
            initDataFromBundle(getArguments());
        }

        arrContacts = new ArrayList<>();
        arrTitle = getActivity().getResources().getStringArray(R.array.arrTitle);
        ArrayList<ContactsModel> arrayList = new ArrayList<>();

        if (typeData == 1) {
            ArrayList<ContactsModel> arrContacts;
            try {
                getMail = new GetMail(getActivity());
                arrContacts = getMail.getArrContacts();
            } catch (Exception e) {
                ((MainActivity) getActivity()).addPermission();
                arrContacts = new ArrayList<>();
            }
            arrayList.addAll(arrContacts);
        } else {
            ArrayList<ContactsModel> arrContacts;
            try {
                getContacts = new GetContacts(getActivity());
                arrContacts = getContacts.getArrContacts();
            } catch (Exception e) {
                ((MainActivity) getActivity()).addPermission();
                arrContacts = new ArrayList<>();
            }
            arrayList.addAll(arrContacts);
        }
        asyncConvertArrContacts.execute(arrayList);
    }

    private void initDataFromBundle(Bundle savedInstanceState) {
        type = savedInstanceState.getInt(TYPE);
        typeData = savedInstanceState.getInt(TYPE_DATA);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.fragment_select_list, container, false);
        return viewRoot;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setBarTitle(view);
        lvContacts = (ListView) view.findViewById(R.id.lsv_contacs);
        adapter = new ContactsAdapter(this, getActivity(), android.R.layout.simple_list_item_1, arrContacts);
        lvContacts.setAdapter(adapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (isAdded()) {
            ((MainActivity) getActivity()).setUptoolBar(ToolbarTyper.NONE_BACK);
            ((MainActivity) getActivity()).setTitle(getActivity().getResources().getString(R.string.from_phone_contact_list));
        }
    }

    private void setBarTitle(View mView) {
        llAreaTitle = (LinearLayout) mView.findViewById(R.id.ll_area_title);
        llAreaTitle.setOrientation(LinearLayout.VERTICAL);
        for (int i = 0; i < arrTitle.length; i++) {
            TextView tvT = new TextView(getActivity());
            tvT.setTextColor(getActivity().getResources().getColor(R.color.black));
            tvT.setText(arrTitle[i]);

            LinearLayout.LayoutParams centerHorizontal = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT, 1);

            centerHorizontal.gravity = Gravity.CENTER_HORIZONTAL;
            tvT.setLayoutParams(centerHorizontal);
            llAreaTitle.addView(tvT);
        }
    }

    private AsyncTask<ArrayList<ContactsModel>, Void, ArrayList<ContactsModel>> asyncConvertArrContacts =
            new AsyncTask<ArrayList<ContactsModel>, Void, ArrayList<ContactsModel>>() {
                @Override
                protected ArrayList<ContactsModel> doInBackground(ArrayList<ContactsModel>... params) {

                    ArrayList<ContactsModel> arrayList = params[0];
                    if (arrayList.size() > 0) {
                        String firstName = arrayList.get(0).getContactsName();
                        title = firstName.substring(0, 1);

                        ArrayList<ContactsModel> temp = new ArrayList<>();

                        temp.add(new ContactsModel(0, null, title, null));
                        for (int i = 0; i < arrayList.size(); i++) {
                            String firstCharaterOfName = arrayList.get(i).getContactsName().substring(0, 1);
                            if (firstCharaterOfName.equals(title)) {
                                temp.add(arrayList.get(i));
                            } else {
                                title = firstCharaterOfName;
                                temp.add(new ContactsModel(0, null, title, null));
                                temp.add(arrayList.get(i));

                            }
                        }
                        return temp;
                    } else {
                        return null;
                    }
                }

                @Override
                protected void onPostExecute(ArrayList<ContactsModel> arrayList) {
                    super.onPostExecute(arrayList);
                    if (arrayList != null) {
                        arrContacts.clear();
                        arrContacts.addAll(arrayList);
                        adapter.notifyDataSetChanged();
                    }
                }
            };

    public int getType() {
        return type;
    }

    private ContactsModel contactsModel;

    public ContactsModel getContactsModel() {
        return contactsModel;
    }

    public void setContactsModel(ContactsModel contactsModel) {
        this.contactsModel = contactsModel;
    }

    @Override
    public void onItemListViewClickListener(ContactsModel contactsModel) {
        if (contactsModel == null) {
            Log.e(TAG, "onItemListViewClickListener: " + "null");
        }
        if (type == 1) {
            setContactsModel(contactsModel);
            ((MainActivity) getActivity()).onBackPressed();
        } else {
            ((MainActivity) getActivity()).replaceFragment(TopUpAddNewFragment.newInstance(contactsModel));
        }
    }

}
