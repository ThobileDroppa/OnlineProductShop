package com.example.onlinestore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.onlinestore.model.CardDetailsDialog;

public class PaymentActvity extends AppCompatActivity implements CardDetailsDialog.CardDetailsListener {

    private LinearLayout addCardDetails;
    
    private TextView cardNumber,cardHolderName,expiryDate,cVV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_actvity);

        
        cardNumber = findViewById(R.id.card_number_text);
        cardHolderName = findViewById(R.id.card_holder_name_text);
        expiryDate = findViewById(R.id.expiry_date_text);
        cVV = findViewById(R.id.cvv_number_text);
        addCardDetails = findViewById(R.id.add_credit_card_layout);
        
        addCardDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });
    }



    public void openDialog(){

        CardDetailsDialog cardDetailsDialog = new CardDetailsDialog();
        cardDetailsDialog.show(getSupportFragmentManager(),"card details");
        
    }

    @Override
    public void applyText(String cardNumbers, String name, String dateMonth, String dateYear, String cvvNumber) {
       cardNumber.setText(cardNumbers);
       cardHolderName.setText(name);
       expiryDate.setText(dateMonth+"/"+dateYear);
       cVV.setText(cvvNumber);
       addCardDetails.setVisibility(View.INVISIBLE);
    }
}