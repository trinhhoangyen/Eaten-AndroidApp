package com.example.eaten.myadapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.eaten.DTO.HomeSubCmt;
import com.example.eaten.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HomeSubCmtAdapter extends ArrayAdapter<HomeSubCmt> {
    private Context context;
    private Bitmap bitmap;
    private List<HomeSubCmt> homeSubCmtList;

    public HomeSubCmtAdapter(List<HomeSubCmt> homeSubCmtList, Context context){
        super(context, R.layout.dong_imgfood, homeSubCmtList);
        this.homeSubCmtList = homeSubCmtList;
        this.context = context;
    }

    static class viewHolder{
        ImageView img_acc;
        TextView name_acc;
        TextView cmt;
    }

    @Override
    public View getView (int position, View view, ViewGroup viewGroup){
        HomeSubCmtAdapter.viewHolder viewHolder;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.row_cmt, null, true);
        viewHolder = new HomeSubCmtAdapter.viewHolder();

        viewHolder.img_acc = view.findViewById(R.id.id_img_acc);
        viewHolder.name_acc = view.findViewById(R.id.id_name_acc);
        viewHolder.cmt = view.findViewById(R.id.id_cmt);
        view.setTag(viewHolder);

        HomeSubCmt homeSubCmt = homeSubCmtList.get(position);

        String _Picture = homeSubCmt.getAvatarURL();
        String _Name = homeSubCmt.getDisplayName();
        String _Cmt = homeSubCmt.getContent();

        viewHolder.name_acc.setText(_Name);
        viewHolder.cmt.setText(_Cmt);

        Picasso.with(getContext())
                .load(homeSubCmt.getAvatarURL())
                //.resize(Home_sub.this.getWindow().getDecorView().getWidth(), 300)
                //.centerInside()
                .fit()
                .centerCrop()
                .into(viewHolder.img_acc);
//        if (viewHolder.img_Picture!=null){
//            new ImageDownloaderTask(viewHolder.img_Picture).execute(_Picture);
//        }
//        viewHolder.img_Picture.setImageBitmap(bitmap);

        return view;
    }
}
