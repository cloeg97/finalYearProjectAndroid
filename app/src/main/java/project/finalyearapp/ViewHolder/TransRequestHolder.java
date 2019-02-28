package project.finalyearapp.ViewHolder;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import project.finalyearapp.Interface.ItemClickListener;
import project.finalyearapp.R;


public class TransRequestHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtTransactionA;
    public TextView txtTransactionC;
    public TextView txtTransactionR;
    //public TextView txtTransactionS;
    public Button approveBtn;

    private ItemClickListener itemClickListener;

    public TransRequestHolder(View itemView) {
        super(itemView);

        txtTransactionA = (TextView) itemView.findViewById(R.id.trans_amount);
        txtTransactionC = (TextView) itemView.findViewById(R.id.trans_currency);
        txtTransactionR = (TextView) itemView.findViewById(R.id.trans_receiver);
        //txtTransactionS = (TextView) itemView.findViewById(R.id.trans_shop);
        approveBtn = (Button) itemView.findViewById(R.id.btnApprove);

        approveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                @SuppressLint("ValidFragment")
                class FireMissilesDialogFragment extends DialogFragment {
                    @Override
                    public Dialog onCreateDialog(Bundle savedInstanceState) {
                        // Use the Builder class for convenient dialog construction
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setMessage(R.string.dialog_approve)
                                .setPositiveButton(R.string.dialog_approve, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        System.out.println("Approve button clicked");
                                    }
                                })
                                .setNegativeButton(R.string.dialog_cancel, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        // User cancelled the dialog
                                        System.out.println("Cancel approval button clicked");
                                    }
                                });
                        // Create the AlertDialog object and return it
                        return builder.create();
                    }
                }
            }
        });

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
