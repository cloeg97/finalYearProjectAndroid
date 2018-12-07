package project.finalyearapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import project.finalyearapp.Common.Common;

public class Welcome extends AppCompatActivity {

    Button btnMvCreate, btnMvView, btnMvRequest;

    //Firebase Stuff
    FirebaseDatabase database;
    DatabaseReference transaction;

    TextView txtFullName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        btnMvCreate = (Button) findViewById(R.id.btnMvCreate);
        btnMvView = (Button) findViewById(R.id.btnMvView);
        btnMvRequest = (Button) findViewById(R.id.btnMvRequest);

        //Init Firebase
        database = FirebaseDatabase.getInstance();
        transaction = database.getReference("Transaction");

        //Set Name for User
        //View headerView = navigationView.getHeaderView(0);
        txtFullName = (TextView)findViewById(R.id.txtFullName);
        //String fullName = (Common.currentUser.getFirstName()) + " " + (Common.currentUser.getLastName());
       // txtFullName.setText(fullName);

        btnMvCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mvCreate = new Intent(Welcome.this, Create.class);
                startActivity(mvCreate);
            }
        });

        btnMvView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mvView = new Intent(Welcome.this, project.finalyearapp.View.class);
                startActivity(mvView);
            }
        });

        btnMvRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mvRequest = new Intent(Welcome.this, Request.class);
                startActivity(mvRequest);
            }
        });
    }
}
