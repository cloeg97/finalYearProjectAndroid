package project.finalyearapp;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import project.finalyearapp.Model.User;

public class Register extends AppCompatActivity {

    MaterialEditText edtFirstName, edtLastName, edtEmail, edtPassword;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtFirstName = (MaterialEditText) findViewById(R.id.edtFirstName);
        edtLastName = (MaterialEditText) findViewById(R.id.edtLastName);
        edtEmail = (MaterialEditText) findViewById(R.id.edtEmail);
        edtPassword = (MaterialEditText) findViewById(R.id.edtPassword);

        btnRegister = (Button) findViewById(R.id.btnRegister);

        //Init Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        final String email = edtEmail.getText().toString().replaceAll(".","");

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog mDialog = new ProgressDialog(Register.this);
                mDialog.setMessage("Please Wait...");
                mDialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        //Check if already a user
                        if(dataSnapshot.child(email).exists()){
                            mDialog.dismiss();
                            Toast.makeText(Register.this, "Email already registered", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            mDialog.dismiss();
                            User user = new User(edtFirstName.getText().toString(), edtLastName.getText().toString(), edtPassword.getText().toString());
                            table_user.child(email).setValue(user);
                            Toast.makeText(Register.this, "Register Successful!", Toast.LENGTH_SHORT).show();
                            finish();
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
