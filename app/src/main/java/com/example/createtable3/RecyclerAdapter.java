
package com.example.createtable3;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerItemViewHolder> {
    private ArrayList<RecyclerData> myList;
    int mLastPosition = 0;
    private RemoveClickListner mListener;

    public RecyclerAdapter(ArrayList<RecyclerData> myList,RemoveClickListner listener) {
        this.myList = myList;
        mListener = listener;
    }

    public RecyclerItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tester_view_layout, parent, false);
        RecyclerItemViewHolder holder = new RecyclerItemViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerItemViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        Log.d("onBindViewHolder ", myList.size() + "");
        holder.etTitleTextView.setText(myList.get(position).getWord());
        holder.etDescriptionTextView.setText(myList.get(position).getDefinition());
        holder.etSentenceTextView.setText(myList.get(position).getSentence());
        mLastPosition =position;
    }

    @Override
    public int getItemCount() {
        return(null != myList?myList.size():0);
    }
    public void notifyData(ArrayList<RecyclerData> myList) {
        this.myList = myList;
        notifyDataSetChanged();
    }

    public class RecyclerItemViewHolder extends RecyclerView.ViewHolder {
        private final TextView etTitleTextView;
        private final TextView etDescriptionTextView;
        private final TextView etSentenceTextView;
        private LinearLayout mainLayout;
        public ImageView crossImage;
        public RecyclerItemViewHolder(final View parent) {
            super(parent);
            etTitleTextView = (TextView) parent.findViewById(R.id.word);
            etDescriptionTextView = (TextView) parent.findViewById(R.id.definition);
            etSentenceTextView = (TextView) parent.findViewById(R.id.sentence);
            crossImage = (ImageView) parent.findViewById(R.id.crossImage);
            mainLayout = (LinearLayout) parent.findViewById(R.id.mainLayout);
            mainLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(itemView.getContext(), "Position:" + Integer.toString(getPosition()), Toast.LENGTH_SHORT).show();
                }
            });
            crossImage.setOnClickListener(new AdapterView.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.OnRemoveClick(getAdapterPosition()
                    );
                }
            });
        }
    }
}