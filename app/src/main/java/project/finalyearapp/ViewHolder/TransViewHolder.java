package project.finalyearapp.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import project.finalyearapp.Interface.ItemClickListener;
import project.finalyearapp.R;


public class TransViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtTransactionA;
    public TextView txtTransactionC;
    public TextView txtTransactionR;

    private ItemClickListener itemClickListener;

    public TransViewHolder(View itemView) {
        super(itemView);

        txtTransactionA = (TextView) itemView.findViewById(R.id.trans_amount);
        txtTransactionC = (TextView) itemView.findViewById(R.id.trans_currency);
        txtTransactionR = (TextView) itemView.findViewById(R.id.trans_receiver);

        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view, getAdapterPosition(),false);
    }
}
