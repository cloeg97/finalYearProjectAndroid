package project.finalyearapp;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.nfc.Tag;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import project.finalyearapp.Interface.ItemClickListener;
import project.finalyearapp.Model.Transaction;
import project.finalyearapp.ViewHolder.TransRequestHolder;
import project.finalyearapp.ViewHolder.TransViewHolder;

public class Request extends AppCompatActivity {

    private static final String TAG = "Request";

    //Firebase Stuff
    FirebaseDatabase database;
    DatabaseReference start, transaction, ref,use;
    DatabaseReference user;

    RecyclerView recycler_menu;
    RecyclerView.LayoutManager layoutManager;

    Button approveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        approveBtn = (Button)findViewById(R.id.btnApprove);

        final String passedEmail= getIntent().getStringExtra("Email");

        //Init Firebase
        database = FirebaseDatabase.getInstance();
        start = database.getReference("Transaction");
        transaction = start.child(passedEmail);
        ref = database.getReference("User");
        use = ref.child(passedEmail);

        //Load Menu
        recycler_menu = (RecyclerView)findViewById(R.id.recycler_menu);
        recycler_menu.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recycler_menu.setLayoutManager(layoutManager);

        //final String passedUserType= getIntent().getStringExtra("userType");

        loadMenu();
    }

    private void cancel(){
        Log.d(TAG, "cancelMethod: Called.");
        Toast.makeText(Request.this, "Canceled Approval", Toast.LENGTH_SHORT).show();
    }

    private void okMethod1(){
        Log.d(TAG, "okMethod1: Called.");
        Toast.makeText(Request.this, "CustB Approval", Toast.LENGTH_SHORT).show();
    }

    private void okMethod2(){
        Log.d(TAG, "okMethod2: Called.");
        Toast.makeText(Request.this, "ShopA Approval", Toast.LENGTH_SHORT).show();
    }

    private void okMethod3(){
        Log.d(TAG, "okMethod3: Called.");
        Toast.makeText(Request.this, "ShopB Approval", Toast.LENGTH_SHORT).show();
    }

    public void customDialog(String title, String message, final String cancelMethod, final String okMethod) {
        final android.support.v7.app.AlertDialog.Builder builderSingle = new android.support.v7.app.AlertDialog.Builder(this);
        builderSingle.setIcon(R.drawable.ic_stat_name);
        builderSingle.setTitle(title);
        builderSingle.setMessage(message);

        builderSingle.setNegativeButton(
                "Cancel",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d(TAG, "onClick: Cancel Called.");
                        cancel();
                    }
                }
        );
        builderSingle.setPositiveButton(
                "OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d(TAG, "onClick: OK Called.");
                        if(okMethod.equals("okMethod1")) {
                            okMethod1();
                        }
                        else if(okMethod.equals("okMethod2")) {
                            okMethod2();
                        }
                        else {
                            okMethod3();
                        }
                    }
                }
        );
        builderSingle.show();
    }

    private void loadMenu() {
        FirebaseRecyclerAdapter<Transaction, TransRequestHolder> adapter = new FirebaseRecyclerAdapter<Transaction, TransRequestHolder>(Transaction.class, R.layout.trans_item, TransRequestHolder.class, transaction) {
            @Override
            protected void populateViewHolder(TransRequestHolder viewHolder, final Transaction model, int position) {
                final String passedUserType= getIntent().getStringExtra("userType");
                final String passedEmail= getIntent().getStringExtra("Email");
                final String receiver = model.getReceiver().toString().replace('.', ' ');
                final String shopA = model.getShopA().toString().replace('.', ' ');
                final String shopB = model.getShopB().toString().replace('.', ' ');

                //receiver
                if(passedEmail.equalsIgnoreCase(receiver)) {
                    if(model.getCustflag() == false) {
                        viewHolder.txtTransactionA.setText(model.getAmount());
                        viewHolder.txtTransactionC.setText(model.getCurrency());
                        viewHolder.txtTransactionR.setText(model.getReceiver());
                        final Transaction clickItem = model;
                        viewHolder.setItemClickListener(new ItemClickListener() {
                            @Override
                            public void onClick(android.view.View view, int position, boolean isLongClick) {
                                Toast.makeText(Request.this, "Receiving: " + shopA + " Collection: " + shopB, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    else {
                        viewHolder.txtTransactionA.setText(" ");
                        viewHolder.txtTransactionC.setText(" ");
                        viewHolder.txtTransactionR.setText(" ");
                    }
                }
                //shopA
                else if (passedEmail.equalsIgnoreCase(model.getShopA())) {
                    if(model.getCustflag() == true && model.getShopAflag() == false) {
                        viewHolder.txtTransactionA.setText(model.getAmount());
                        viewHolder.txtTransactionC.setText(model.getCurrency());
                        viewHolder.txtTransactionR.setText(model.getReceiver());
                        final Transaction clickItem = model;
                        viewHolder.setItemClickListener(new ItemClickListener() {
                            @Override
                            public void onClick(android.view.View view, int position, boolean isLongClick) {
                                Toast.makeText(Request.this, "Receiving: " + shopA + " Collection: " + shopB, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    else {
                        viewHolder.txtTransactionA.setText(" ");
                        viewHolder.txtTransactionC.setText(" ");
                        viewHolder.txtTransactionR.setText(" ");
                    }
                }
                //shopB
                else if (passedEmail.equalsIgnoreCase(model.getShopB())) {
                    if(model.getShopAflag() == true && model.getShopBflag() == false) {
                        viewHolder.txtTransactionA.setText(model.getAmount());
                        viewHolder.txtTransactionC.setText(model.getCurrency());
                        viewHolder.txtTransactionR.setText(model.getReceiver());
                        final Transaction clickItem = model;
                        viewHolder.setItemClickListener(new ItemClickListener() {
                            @Override
                            public void onClick(android.view.View view, int position, boolean isLongClick) {
                                Toast.makeText(Request.this, "Receiving: " + shopA + " Collection: " + shopB, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    else {
                        viewHolder.txtTransactionA.setText(" ");
                        viewHolder.txtTransactionC.setText(" ");
                        viewHolder.txtTransactionR.setText(" ");
                    }
                }
                else {
                    viewHolder.txtTransactionA.setText(" ");
                    viewHolder.txtTransactionC.setText(" ");
                    viewHolder.txtTransactionR.setText(" ");
                }

                //original view transition that works
                /*viewHolder.txtTransactionA.setText(model.getAmount());
                viewHolder.txtTransactionC.setText(model.getCurrency());
                viewHolder.txtTransactionR.setText(model.getReceiver());
                final Transaction clickItem = model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(android.view.View view, int position, boolean isLongClick) {
                        Toast.makeText(Request.this, "Receiving: " + shopA + " Collection: " + shopB, Toast.LENGTH_SHORT).show();
                    }
                });*/
                /*viewHolder.getApproveBtn().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(Request.this, "" + clickItem.getShopAflag(), Toast.LENGTH_SHORT).show();
                    }
                });*/

            }
        };
        recycler_menu.setAdapter(adapter);
    }
}



//code for button
/*
if custB
    title, textbox, cancel, okMethod1
else if shopA
    title, are you sure, cancel, okMethod2
else if shopB
    title, are you sure, cancel, okMethod3
 */
/*
viewHolder.getApproveBtn().setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
    if(passedEmail.equalsIgnoreCase(receiver)) {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(Request.this);
        View mView = getLayoutInflater().inflate(R.layout.dialog_textbox, null);
        mBuilder.setTitle("Transaction Approval");
        MaterialEditText mEdit = (MaterialEditText) mView.findViewById(R.id.edtShopB);

        mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(Request.this, "CustB Approval", Toast.LENGTH_SHORT).show();
                dialogInterface.dismiss();
            }
        });

        mBuilder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        mBuilder.setView(mView);
        AlertDialog dialog = mBuilder.create();
        dialog.show();
    }
    else if(passedEmail.equalsIgnoreCase(model.getShopA())) {
        customDialog("Transaction Approval", "Are you sure you want to approve this transaction?", "cancel", "okMethod2");
        }
        else if(passedEmail.equalsIgnoreCase(model.getShopB())) {
        customDialog("Transaction Approval", "Are you sure you want to approve this transaction?", "cancel", "okMethod3");
        }
    }
});*/
