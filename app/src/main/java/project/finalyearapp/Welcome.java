package project.finalyearapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Welcome extends AppCompatActivity {

    Button btnMvCreate, btnMvView, btnMvRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        btnMvCreate = (Button) findViewById(R.id.btnMvCreate);
        btnMvView = (Button) findViewById(R.id.btnMvView);
        btnMvRequest = (Button) findViewById(R.id.btnMvRequest);

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
