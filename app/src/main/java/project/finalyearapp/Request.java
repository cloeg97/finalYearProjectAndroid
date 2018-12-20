package project.finalyearapp;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import project.finalyearapp.Interface.ItemClickListener;
import project.finalyearapp.Model.Transaction;
import project.finalyearapp.ViewHolder.TransViewHolder;

public class Request extends AppCompatActivity {

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

    private void loadMenu() {
        FirebaseRecyclerAdapter<Transaction, TransViewHolder> adapter = new FirebaseRecyclerAdapter<Transaction, TransViewHolder>(Transaction.class, R.layout.trans_item, TransViewHolder.class, transaction) {
            @Override
            protected void populateViewHolder(TransViewHolder viewHolder, Transaction model, int position) {
                final String passedUserType= getIntent().getStringExtra("userType");
                final String passedEmail= getIntent().getStringExtra("Email");
                final String receiver = model.getReceiver().toString().replace('.', ' ');
                final String shop = model.getShop().toString().replace('.', ' ');
                if(passedUserType == "customer"){
                    if(receiver.equalsIgnoreCase(passedEmail)) {
                        if (model.getCustflag() == false) {
                            viewHolder.txtTransactionA.setText(model.getAmount());
                            viewHolder.txtTransactionC.setText(model.getCurrency());
                            viewHolder.txtTransactionR.setText(model.getReceiver());
                            final Transaction clickItem = model;
                            viewHolder.setItemClickListener(new ItemClickListener() {
                                @Override
                                public void onClick(android.view.View view, int position, boolean isLongClick) {
                                    Toast.makeText(Request.this, "" + clickItem.getShop(), Toast.LENGTH_SHORT).show();
                                }
                            });

                        }
                    }
                }
                else {
                    if (shop.equalsIgnoreCase(passedEmail)) {
                        if (model.getCustflag() == true && (model.getShopAflag() == false || model.getShopBflag() == false)) {
                            viewHolder.txtTransactionA.setText(model.getAmount());
                            viewHolder.txtTransactionC.setText(model.getCurrency());
                            viewHolder.txtTransactionR.setText(model.getReceiver());
                            final Transaction clickItem = model;
                            viewHolder.setItemClickListener(new ItemClickListener() {
                                @Override
                                public void onClick(android.view.View view, int position, boolean isLongClick) {
                                    Toast.makeText(Request.this, "" + clickItem.getShop(), Toast.LENGTH_SHORT).show();
                                }
                            });

                        }
                    }
                }
                /*viewHolder.txtTransactionA.setText(model.getAmount());
                viewHolder.txtTransactionC.setText(model.getCurrency());
                viewHolder.txtTransactionR.setText(model.getReceiver());
                final Transaction clickItem = model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(android.view.View view, int position, boolean isLongClick) {
                        Toast.makeText(View.this, "" + clickItem.getCurrency(), Toast.LENGTH_SHORT).show();
                    }
                });*/
            }
        };
        recycler_menu.setAdapter(adapter);
    }
}
