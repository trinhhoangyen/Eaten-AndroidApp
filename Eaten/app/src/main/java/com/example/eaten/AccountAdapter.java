//package com.example.eaten;
//
//import android.content.Context;
//import android.graphics.Bitmap;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//
//import com.example.eaten.DTO.Account;
//
//import java.util.List;
//
//public class AccountAdapter extends ArrayAdapter<Account> {
//    private List<Account> accountList;
//    private Bitmap bitmap;
//    private Context context;
//
//
//    public AccountAdapter(Context context, List<Account> accountList) {
//        super(context, R.layout.row_ngon_ngu_lap_trinh, accountList);
//        this.context = context;
//        this.accountList = accountList;
//    }
//
//    static class ViewHolder{
//        TextView tenNgonNgu;
//        TextView noiDungNgonNgu;
//        ImageView imgNgonNgu;
//    }
//
//    @Override
//    public View getView(int position, View view, ViewGroup parent) {
//        LayoutInflater inflater = LayoutInflater.from(context);
//        view = inflater.inflate(R.layout.row_ngon_ngu_lap_trinh, null,true);
//        ViewHolder holder = new ViewHolder();
//        holder.tenNgonNgu = (TextView) view.findViewById(R.id.tenNgonNgu);
//        holder.noiDungNgonNgu = (TextView) view.findViewById(R.id.noiDungNgonNgu);
//        holder.imgNgonNgu = (ImageView) view.findViewById(R.id.imgNgonNgu);
//        view.setTag(holder);
//
//        NgonNguLapTrinh ngonNguLapTrinh = ngonNguList.get(position);
//        String imageURL = ngonNguLapTrinh.getImageURL();
//        String name = ngonNguLapTrinh.getName();
//        String description = ngonNguLapTrinh.getDescription();
//
//        holder.tenNgonNgu.setText(name);
//        holder.noiDungNgonNgu.setText(description);
//
//        if(holder.imgNgonNgu != null){
//            new ImageDownloaderTask(holder.imgNgonNgu).execute(imageURL);
//        }
//        holder.imgNgonNgu.setImageBitmap(bitmap);
//        return view;
//    }
//}
