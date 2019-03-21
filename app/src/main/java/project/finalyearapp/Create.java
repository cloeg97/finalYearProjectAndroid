package project.finalyearapp;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;

import org.json.JSONException;

import java.text.DecimalFormat;

import project.finalyearapp.Model.Transaction;

import static java.lang.Boolean.TRUE;
import static java.lang.Thread.sleep;
import static project.finalyearapp.Order.OrderMe;
import static project.finalyearapp.Price.findPrice;

public class Create extends AppCompatActivity {

    MaterialEditText edtAmount, edtCurrentCurrency, edtReceiver, edtShopA, edtShopB;
    Button btnCreate;
    TextView textAfterconv;
    //usually the product id for testing would be BTC-EUR for the buy, but the sandbox has one BTC worth 9billion EUR so we have to USE USD
    String product_id = "BTC-USD";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        final String passedEmail= getIntent().getStringExtra("Email");

        edtAmount = (MaterialEditText)findViewById(R.id.edtAmount);
        edtCurrentCurrency = (MaterialEditText)findViewById(R.id.edtCurrentCurrency);
        edtReceiver = (MaterialEditText)findViewById(R.id.edtReceiver);
        edtShopA = (MaterialEditText)findViewById(R.id.edtShopA);

        btnCreate = (Button)findViewById(R.id.btnCreate);

        textAfterconv=(TextView)findViewById(R.id.textAfterConversion);

        //Init Firebase
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_trans = database.getReference("Transaction");
        final DatabaseReference loc = table_trans.child(passedEmail);



            /* final Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {

                    while(true) {

                        try {

                             textAfterconv.setText(findPrice(product_id));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            sleep(100000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }

            });
            thread.start();*/

       /* final Thread threadBuy = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    OrderMe(edtAmount.getText().toString(),"buy", product_id);
                    Log.i("Order placed", "Order placed");
                } catch (JSONException e) {
                    Log.i("Error", e.toString());

                    e.printStackTrace();
                }
            }

        });
        threadBuy.start();*/

        //get current price, multiply by funds, take away 3% under a certain amrount... do conversion, sell currency
        double currentPrice= 0;
        final DecimalFormat f = new DecimalFormat("##.00");
        try {
            Double btcUSDPrice= Double.parseDouble(findPrice("BTC-USD"));
            //3838- price on 14th/03/19
            btcUSDPrice=3838.00;
            System.out.println("The price of BTC-USD "+ btcUSDPrice);


            Double btcEURPrice= Double.parseDouble(findPrice("BTC-EUR"));
            System.out.println("The price of BTC-EUR "+ btcEURPrice);
            //3396- price on 14th/03/19
            btcEURPrice=3396.00;

            currentPrice=(btcUSDPrice/btcEURPrice);
            System.out.println("Exchange rate "+ f.format(currentPrice));



        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {

            textAfterconv.setText(findPrice(product_id));
            //sandbox override
            textAfterconv.setText(f.format(currentPrice));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        final double finalCurrentPrice = currentPrice;
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog mDialog = new ProgressDialog(Create.this);
                mDialog.setMessage("Please Wait...");
                mDialog.show();

                String receiver = edtReceiver.getText().toString().replace('.', ' ');
                String shopA = edtShopA.getText().toString().replace('.', ' ');

                try {
                    Double tobuydouble= Double.parseDouble(edtAmount.getText().toString());
                   String tobuy=f.format(tobuydouble);
                    OrderMe(tobuy,"buy", product_id);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                //override for sandbox,

                double toSell= Double.parseDouble(edtAmount.getText().toString());
                System.out.println("To sell before "+ finalCurrentPrice +": "+ toSell);

                toSell=Double.parseDouble(f.format(toSell* finalCurrentPrice));
                System.out.println("To sell after  "+ finalCurrentPrice +": "+ toSell);

                System.out.println("To sell before .97: "+ toSell);
                toSell=Math.round(toSell*0.97);
                System.out.println("To sell after .97: "+ toSell);


                product_id= "BTC-USD";

                try {
                    OrderMe(Double.toString(toSell),"sell", product_id);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                mDialog.dismiss();
                final String keyRef = table_trans.push().getKey();
                Transaction trans = new Transaction(edtAmount.getText().toString(), edtCurrentCurrency.getText().toString(), receiver, shopA, " ", false, false, false, keyRef, passedEmail);
                table_trans.child(keyRef).setValue(trans);
                Toast.makeText(Create.this, "Transaction Created!", Toast.LENGTH_SHORT).show();
                finish();
                Intent mvWelcome = new Intent(Create.this, Welcome.class);
                mvWelcome.putExtra("Email", passedEmail);
                startActivity(mvWelcome);

            }
        });
    }
}
