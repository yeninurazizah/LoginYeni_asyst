package id.co.asyst.yeni.loginyeni.adapter;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import id.co.asyst.yeni.loginyeni.R;
import id.co.asyst.yeni.loginyeni.model.PgnModel;
import id.co.asyst.yeni.loginyeni.utility.DateUtils;

public class PgnAdapter extends RecyclerView.Adapter<PgnAdapter.MyViewHolder> implements Parcelable {

    public static final Creator<PgnAdapter> CREATOR = new Creator<PgnAdapter>() {
        @Override
        public PgnAdapter createFromParcel(Parcel in) {
            return new PgnAdapter(in);
        }

        @Override
        public PgnAdapter[] newArray(int size) {
            return new PgnAdapter[size];
        }
    };
    Context mContext;
    ArrayList<PgnModel> mListPgn;
    onItemClickListener mListener;

    public PgnAdapter(Context context, ArrayList<PgnModel> listPgn, onItemClickListener listener) {
        this.mContext = context;
        this.mListPgn = listPgn;
        this.mListener = listener;
    }

    protected PgnAdapter(Parcel in) {
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);

        return new PgnAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final PgnModel pgnModel = mListPgn.get(position);

        holder.nameTV.setText(pgnModel.getCustomer_name());
        holder.locationTV.setText(pgnModel.getCustomer_address());

        holder.startDateTV.setText(pgnModel.getStartDate());
        holder.startDateTV.setText(DateUtils.formatDate("yyyy-MM-dd", "EEEE, dd MMMM yyyy", pgnModel.getStartDate()));

        if (pgnModel.getFinishDate() == null) {
            holder.finishDateTV.setVisibility(View.GONE);
        } else {
            holder.finishDateTV.setVisibility(View.VISIBLE);
            holder.finishDateTV.setText("Selesai pada " + DateUtils.formatDate("yyyy-MM-dd", "dd MMMM yyyy", pgnModel.getFinishDate()));
        }

        holder.customerIdTV.setText(pgnModel.getCustomer_id());
        holder.taskIdTV.setText(pgnModel.getTask_id());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClickListener(pgnModel);
            }
        });

    }

    @Override
    public int getItemCount() {

        return mListPgn.size();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public interface onItemClickListener {
        void onItemClickListener(PgnModel pgnModel);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nameTV, locationTV, startDateTV, finishDateTV, customerIdTV, taskIdTV;
        CardView cardView;

        public MyViewHolder(View itemView) {

            super(itemView);

            nameTV = itemView.findViewById(R.id.name_tv);
            locationTV = itemView.findViewById(R.id.location_tv);
            startDateTV = itemView.findViewById(R.id.startdate_tv);
            finishDateTV = itemView.findViewById(R.id.finishdate_tv);
            customerIdTV = itemView.findViewById(R.id.customerid_tv);
            taskIdTV = itemView.findViewById(R.id.taskid_tv);

            cardView = itemView.findViewById(R.id.cardview);

        }


    }
}
