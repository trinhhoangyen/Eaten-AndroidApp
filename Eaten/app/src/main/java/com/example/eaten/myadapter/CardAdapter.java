package com.example.eaten.myadapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.eaten.DTO.Card;
import com.example.eaten.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CardAdapter extends ArrayAdapter<Card> {
    private Context context;
    private Bitmap bitmap;
    private List<Card> cardList;

    public CardAdapter(List<Card> cardList, Context context){
        super(context, R.layout.dong_imgfood, cardList);
        this.cardList = cardList;
        this.context = context;
    }

    static class viewHolder{
        ImageView img_Picture;
        TextView txt_PostName;
        TextView txt_Address;
        TextView txt_reactQuantity;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        viewHolder viewHolder;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.dong_imgfood, null, true);
        viewHolder = new viewHolder();

        viewHolder.img_Picture = view.findViewById(R.id.id_Picture);
        viewHolder.txt_PostName = view.findViewById(R.id.id_PostName);
        viewHolder.txt_Address = view.findViewById(R.id.id_Address);
        viewHolder.txt_reactQuantity = view.findViewById(R.id.id_loves);
        view.setTag(viewHolder);

        Card card = cardList.get(position);

        String _Picture = card.getPicture();
        String _PostName = card.getPostName();
        String _Address = card.getAddress();
        int _reactQuantity = card.getReactQuantity();

        viewHolder.txt_PostName.setText(_PostName);
        viewHolder.txt_Address.setText(_Address);
        viewHolder.txt_reactQuantity.setText(String.valueOf(_reactQuantity));

        Picasso.with(getContext())
                .load(card.getPicture())
                //.resize(Home_sub.this.getWindow().getDecorView().getWidth(), 300)
                //.centerInside()
                .fit()
                .centerCrop()
                .into(viewHolder.img_Picture);
//        if (viewHolder.img_Picture!=null){
//            new ImageDownloaderTask(viewHolder.img_Picture).execute(_Picture);
//        }
//        viewHolder.img_Picture.setImageBitmap(bitmap);

        return view;
    }

    //public class CardAdapter extends BaseAdapter {
//    private Context context;
//    private int layout;
//    private List<Card> cardList;
//
//    public CardAdapter(Context context, int layout, List<Card> cardList) {
//        this.context = context;
//        this.layout = layout;
//        this.cardList = cardList;
//    }
//
//    @Override
//    public int getCount() {
//        return cardList.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return null;
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return 0;
//    }
//
//    private class ViewHolder{
//        ImageView imageView;
//        TextView postName, address, reactQuantity;
//    }
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        ViewHolder viewHolder;
//        if(convertView == null){
//            viewHolder = new ViewHolder();
//            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
//            convertView = layoutInflater.inflate(layout,null);
//            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.id_Picture);
//            viewHolder.postName = (TextView) convertView.findViewById(R.id.id_PostName);
//            viewHolder.address = (TextView) convertView.findViewById(R.id.id_Address);
//            viewHolder.reactQuantity = (TextView) convertView.findViewById(R.id.id_loves);
//            convertView.setTag(viewHolder);
//        }else{
//            viewHolder = (ViewHolder) convertView.getTag();
//        }
//
//        Card card = cardList.get(position);
//
//        viewHolder.imageView.setImageResource(card.getPicture());
//        viewHolder.postName.setText(card.getPostName());
//        viewHolder.address.setText(card.getAddress());
//        viewHolder.reactQuantity.setText(card.getReactQuantity());
//        return convertView;
//    }
}
