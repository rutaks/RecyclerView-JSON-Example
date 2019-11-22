package com.example.recyclerviewjsonexample.adapters;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.recyclerviewjsonexample.R;
import com.example.recyclerviewjsonexample.models.CardItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * The CardItemAdapter is a class to handle xml & code actions
 */
public class CardItemAdapter extends RecyclerView.Adapter<CardItemAdapter.CardItemViewHolder> {
    private Context context;
    private ArrayList<CardItem> cardItems;

    /**
     * Constructor.
     *
     * @param context (required) Context In Which Adapter Is Used
     * @param cardItems (required) An ArrayList Containing all card
     * item elements to be displayed
     */
    public CardItemAdapter(Context context, ArrayList<CardItem> cardItems) {
        this.context = context;
        this.cardItems = cardItems;
    }

    /**
     * The CardItemViewHolder is a class to obtain the view from xml to represent in code
     */
    public class CardItemViewHolder extends RecyclerView.ViewHolder{
        //CardItem Elements
        private ImageView imageView;
        private TextView textViewCreator;
        private TextView textViewLikes;

        /**
         * Constructor.
         *
         * @param itemView (required) Elements Of The Card View Item
         */
        public CardItemViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_image_view);
            textViewCreator = itemView.findViewById(R.id.text_view_creator);
            textViewLikes = itemView.findViewById(R.id.text_view_likes);
        }
    }

    @NonNull
    @Override
    public CardItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.card_item,parent,false);
        return new CardItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CardItemViewHolder holder, int position) {
        //Get Item In ArrayList At Row Selected
        CardItem item = cardItems.get(position);

        //Get Values From the selected item
        String imgURL = item.getImageURL();
        String creatorName = item.getCreator();
        int likesNo = item.getLikes();

        //Set Item Text Details To View Holder
        holder.textViewCreator.setText(creatorName);
        holder.textViewLikes.setText("Likes: " + likesNo);

        //Set Image To View Holder's Image View
        Picasso.with(context).load(imgURL).fit().centerInside().into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return cardItems.size();
    }
}
