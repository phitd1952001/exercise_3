package com.example.exercise_3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class UserDataAdapter extends BaseAdapter {

    private Context context;
    private int layoutResourceId;
    private List<UserData> userList;

    public UserDataAdapter(Context context, int layoutResourceId, List<UserData> userList) {
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.userList = userList;
    }

    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public Object getItem(int position) {
        return userList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(layoutResourceId, parent, false);
        }

        // Get the data item for this position
        UserData user = userList.get(position);

        // Populate views in the cardview_item.xml layout with user data
        TextView textViewName = convertView.findViewById(R.id.textView_NameDetails);
        TextView textViewDob = convertView.findViewById(R.id.textView_DobDetails);
        TextView textViewEmail = convertView.findViewById(R.id.textView_EmailDetails);
        ImageView imageViewSelectedImage = convertView.findViewById(R.id.imageView_SelectedImage);

        textViewName.setText("Name: " + user.getName());
        textViewDob.setText("DOB: " + user.getDateOfBirth());
        textViewEmail.setText("Email: " + user.getEmail());

        // Handle the selected tag to set the ImageView based on user.getSelectedTag()
        String selectedTag = user.getSelectedImageTag();
        int imageResourceId = context.getResources().getIdentifier(selectedTag, "drawable", context.getPackageName());

        if (imageResourceId != 0) {
            // Set the image for the ImageView based on the selectedTag
            imageViewSelectedImage.setImageResource(imageResourceId);
        } else {
            // Set a default image if the selectedTag does not correspond to a valid resource
            imageViewSelectedImage.setImageResource(R.drawable.img1);
        }

        return convertView;
    }
}
