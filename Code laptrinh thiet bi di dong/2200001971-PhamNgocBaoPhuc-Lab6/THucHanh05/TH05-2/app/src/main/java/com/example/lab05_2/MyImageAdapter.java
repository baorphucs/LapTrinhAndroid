package com.example.lab05_2;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class MyImageAdapter extends BaseAdapter
{
    private Context mContext;
    private Integer []mThumbIds;

    //Constructor
    public MyImageAdapter(Context context_)
    {
        mContext = context_;
    }

    public MyImageAdapter(Context context_, Integer []arrIds){
        mContext=context_;
        mThumbIds=arrIds;
    }

    @Override
    public int getCount() {
        return mThumbIds.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    //Cần override lại hàm này để hiển thị hình ảnh
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ImageView imgView;
        if(convertView==null){
            imgView = new ImageView(mContext);
            //canh chỉnh lại hình cho đẹp
            imgView.setLayoutParams(new GridView.LayoutParams(300, 300)); //Có thể tùy chỉnh lại
            imgView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imgView.setPadding(4, 4, 4, 4); //Có thể tùy chỉnh lại
        }
        else
        {
            imgView=(ImageView) convertView;
        }

        //lấy đúng vị trí hình ảnh được chọn
        //gán lại ImageResource
        imgView.setImageResource(mThumbIds[position]);
        return imgView;
    }
}