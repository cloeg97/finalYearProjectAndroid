package project.finalyearapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import project.finalyearapp.Common.Common;
import project.finalyearapp.Model.Transaction;
import project.finalyearapp.Model.User;
import project.finalyearapp.LogIn;

public class Create extends AppCompatActivity {

    MaterialEditText edtAmount, edtCurrency, edtReceiver;
    Button btnCreate;
    Object obj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        edtAmount = (MaterialEditText)findViewById(R.id.edtAmount);
        edtCurrency = (MaterialEditText)findViewById(R.id.edtCurrency);
        edtReceiver = (MaterialEditText)findViewById(R.id.edtReceiver);

        btnCreate = (Button)findViewById(R.id.btnCreate);

        //Init Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_trans = database.getReference("Transaction");

        //Find current user email for transaction key
        //String name = Common.currentUser.getFirstName();
        DatabaseReference user = database.getReference("user");

        user.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot objSnapshot: snapshot.getChildren()) {
                    obj = objSnapshot.getKey();
                }
            }
            @Override
            public void onCancelled(DatabaseError firebaseError) {
                Log.e("Read failed", firebaseError.getMessage());
            }
        });
        //System.out.println(obj); this == null :(
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog mDialog = new ProgressDialog(Create.this);
                mDialog.setMessage("Please Wait...");
                mDialog.show();

                table_trans.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String email = obj.toString();
                        if(dataSnapshot.child(email).exists()){
                            mDialog.dismiss();
                            Transaction trans = new Transaction(edtAmount.getText().toString(), edtCurrency.getText().toString(), edtReceiver.getText().toString());
                            table_trans.child(email).setValue(trans);
                            Toast.makeText(Create.this, "Transaction Created!", Toast.LENGTH_SHORT).show();
                            finish();
                            Intent mvWelcome = new Intent(Create.this, Welcome.class);
                            startActivity(mvWelcome);
                        }
                        else {
                            mDialog.dismiss();
                            Toast.makeText(Create.this, "Cannot find account " + email  , Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
