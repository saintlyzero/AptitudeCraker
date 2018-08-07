package com.example.shubham_pc.aptitudecracker;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Shubham_pc on 25/10/2017.
 */

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ProductViewHolder> {


    private Context mCtx;
    private List<Question> productList;

    public QuestionAdapter(Context mCtx, List<Question> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.question_list, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        Question product = productList.get(position);

        holder.textViewQuestion.setText(product.getQuestion());
        holder.textViewUans.setText(product.getOption1());
        holder.textViewAns.setText(product.getAnswer());

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView textViewQuestion;

        TextView textViewUans, textViewAns;

        public ProductViewHolder(View itemView) {
            super(itemView);

            textViewQuestion = itemView.findViewById(R.id.tv_question);
            textViewUans = itemView.findViewById(R.id.tv_yourAnswer);
            textViewAns = itemView.findViewById(R.id.tv_correctAnswer);

        }
    }
}
