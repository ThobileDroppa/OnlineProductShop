package com.example.onlinestore.model;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.onlinestore.R;
import com.google.android.material.textfield.TextInputEditText;

public class CardDetailsDialog extends AppCompatDialogFragment {

    private TextInputEditText cardNumber,holderName,monthDate,yearDate,cvvNum;

    private CardDetailsListener cardDetailsListener;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.card_details,null);


        cardNumber  = view.findViewById(R.id.card_number_input);
        holderName = view.findViewById(R.id.card_holder_input);
        monthDate = view.findViewById(R.id.expiry_date_month_input);
        yearDate = view.findViewById(R.id.expiry_date_year_input);
        cvvNum =    view.findViewById(R.id.cvv_input);


        builder.setView(view)
                .setTitle("Add Card Details")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                       String cardNum = cardNumber.getText().toString();
                       String names  = holderName.getText().toString();
                       String dateMonth = monthDate.getText().toString();
                       String dateYear = yearDate.getText().toString();
                       String cvv = cvvNum.getText().toString();


                        if(validateText(cardNum,names,dateMonth,dateYear,cvv)){
                            cardDetailsListener.applyText(cardNum,names,dateMonth,dateYear,cvv);

                        }

                    }
                });

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            cardDetailsListener = (CardDetailsListener) context;
        } catch (ClassCastException e) {
           throw new ClassCastException(context.toString()+
                   "must implement CardDetailsListener");
        }

    }

    public interface  CardDetailsListener{

        void applyText(String cardNumber,String name,String dateMonth,String dateYear, String cvvNumber);

    }

    private boolean validateText(String cardNumber,String name,String dateMonth,String dateYear, String cvvNumber) {


        if(!TextUtils.isEmpty(cardNumber)&&!TextUtils.isEmpty(name)&&!TextUtils.isEmpty(dateMonth)&&!TextUtils.isEmpty(dateYear)&&!TextUtils.isEmpty(cvvNumber)) {

            return true;

        }else {

            Toast.makeText(getContext(), "Fill In All Details", Toast.LENGTH_SHORT).show();
                return false;

        }


    }
}
