package com.example.lenovo.any;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;

public class NotificationAdapter extends FirestoreRecyclerAdapter<Notification,NotificationAdapter.NotificationHolder> {
private OnItemClickListener listener;

    public NotificationAdapter(@NonNull FirestoreRecyclerOptions<Notification> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull NotificationHolder holder, int position, @NonNull Notification model) {
//holder.textViewidofnotification.setText(model.getId());
        holder.textViewidofnotification.setText(model.getText());
holder.textViewPriorityofnotification.setText(String.valueOf(model.getPriorityofdoc()));


    }

    @NonNull
    @Override
    public NotificationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

       View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_item,parent,false);


        return new NotificationHolder(view);
    }

        public void deleteItem(int position){

        getSnapshots().getSnapshot(position).getReference().delete();
        }




    class NotificationHolder extends RecyclerView.ViewHolder {
        TextView textViewidofnotification;
        TextView textViewPriorityofnotification;

        public NotificationHolder(View itemView) {
            super(itemView);
            textViewidofnotification= itemView.findViewById(R.id.text_view_id);
            textViewPriorityofnotification = itemView.findViewById(R.id.textview_priority);

itemView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        int position=getAdapterPosition();

        if (position != RecyclerView.NO_POSITION && listener != null) {
            listener.onItemClick(getSnapshots().getSnapshot(position), position);
        }




    }
});



        }
    }


    public interface OnItemClickListener {
        void onItemClick(DocumentSnapshot documentSnapshot, int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

}



