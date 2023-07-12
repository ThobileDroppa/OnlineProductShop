package com.example.onlinestore;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlinestore.model.OnBoardingClass;

import java.util.List;

public class PagerAdapter extends RecyclerView.Adapter<PagerAdapter.PagerAdapterViewHolder>{

    private List<OnBoardingClass> onBoardingClassList;

    public PagerAdapter(List<OnBoardingClass> onBoardingClassList) {
        this.onBoardingClassList = onBoardingClassList;
    }

    @NonNull
    @Override
    public PagerAdapter.PagerAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new PagerAdapterViewHolder(
                LayoutInflater.from(parent.getContext()).
                inflate(R.layout.oboarding_screen,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull PagerAdapterViewHolder holder, int position) {
        holder.setOnBoardingData(onBoardingClassList.get(position));

    }

    @Override
    public int getItemCount() {
        return onBoardingClassList.size();
    }


    class PagerAdapterViewHolder extends RecyclerView.ViewHolder{

        //initialize all widgets in onboarding layout
        private ImageView imageView;
        private TextView textView;

        public PagerAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.onboarding_textView);
            imageView = itemView.findViewById(R.id.onboarding_image);

        }

        private void setOnBoardingData(OnBoardingClass onBoardingClass){
            imageView.setImageResource(onBoardingClass.getImageView());
            textView.setText((CharSequence) onBoardingClass.getTextView());

        }
    }

}
