package cn.litchi.wyf.mymovies;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by wangyifeng on 2017/1/20.
 */

public class MovieGridViewAdapter extends ArrayAdapter<MovieItem>{
    private Context context;
    private int layoutResourceId;
    private ArrayList<MovieItem> mGridData = new ArrayList<MovieItem>();

    public MovieGridViewAdapter(Context context, int resource, ArrayList<MovieItem> objects){
        super(context,resource,objects);
        this.context = context;
        this.layoutResourceId = resource;
        this.mGridData = objects;
    }

    public void setGridData(ArrayList<MovieItem> mGridData){

        this.mGridData = mGridData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHold holder;
        if(convertView == null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            convertView = inflater.inflate(layoutResourceId,parent,false);
            holder = new ViewHold();
            holder.textView = (TextView) convertView.findViewById(R.id.movieTitle);
            holder.imageView = (ImageView)convertView.findViewById(R.id.movieImg);
            convertView.setTag(holder);
        }else{
            holder = (ViewHold)convertView.getTag();
        }
        MovieItem item= mGridData.get(position);
        holder.textView.setText(item.getTitle());
        Glide.with(context).load(item.getImage()).into(holder.imageView);
        return convertView;
    }
    private class ViewHold{
        TextView textView;
        ImageView imageView;
    }
}
