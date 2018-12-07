package project.finalyearapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import project.finalyearapp.Common.Common;
import project.finalyearapp.Interface.ItemClickListener;
import project.finalyearapp.Model.Transaction;
import project.finalyearapp.ViewHolder.TransViewHolder;

public class View extends AppCompatActivity {

    //Firebase Stuff
    FirebaseDatabase database;
    DatabaseReference transaction;
    DatabaseReference user;

    RecyclerView recycler_menu;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        //Init Firebase
        database = FirebaseDatabase.getInstance();
        transaction = database.getReference("Transaction");

        /*
        user = database.getReference("user");

        user.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot objSnapshot: snapshot.getChildren()) {
                    Object obj = objSnapshot.getKey();
                }
            }
            @Override
            public void onCancelled(DatabaseError firebaseError) {
                Log.e("Read failed", firebaseError.getMessage());
            }
        });
        */

        //Load Menu
        recycler_menu = (RecyclerView)findViewById(R.id.recycler_menu);
        recycler_menu.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recycler_menu.setLayoutManager(layoutManager);

        loadMenu();
    }

    private void loadMenu() {
        FirebaseRecyclerAdapter<Transaction, TransViewHolder> adapter = new FirebaseRecyclerAdapter<Transaction, TransViewHolder>(Transaction.class, R.layout.trans_item, TransViewHolder.class, transaction) {
            @Override
            protected void populateViewHolder(TransViewHolder viewHolder, Transaction model, int position) {
                viewHolder.txtTransactionA.setText(model.getAmount());
                viewHolder.txtTransactionC.setText(model.getCurrency());
                viewHolder.txtTransactionR.setText(model.getReceiver());
                final Transaction clickItem = model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(android.view.View view, int position, boolean isLongClick) {
                        Toast.makeText(View.this, "" + clickItem.getCurrency(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };
        recycler_menu.setAdapter(adapter);
    }
}
