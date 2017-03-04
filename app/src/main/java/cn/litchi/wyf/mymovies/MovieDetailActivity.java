package cn.litchi.wyf.mymovies;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by wangyifeng on 2017/1/21.
 */

public class MovieDetailActivity extends AppCompatActivity{
    private TextView titleTextView;
    private ImageView imageView;
    private TextView dateTextView;
    private TextView voteTextView;
    private TextView overviewTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        String title = getIntent().getStringExtra("title");
        String imageUrl= getIntent().getStringExtra("img");
        String date = getIntent().getStringExtra("date");
        Double vote = getIntent().getDoubleExtra("vote",0);
        String overview = getIntent().getStringExtra("overview");

        titleTextView=(TextView)findViewById(R.id.movie_title);
        dateTextView= (TextView)findViewById(R.id.movie_date);
        voteTextView= (TextView)findViewById(R.id.vote_average);
        overviewTextView=(TextView)findViewById(R.id.movie_overview);
        imageView = (ImageView)findViewById(R.id.move_img);

        titleTextView.setText(title);
        dateTextView.setText(date);
        voteTextView.setText(vote.toString()+"分");
        overviewTextView.setText(overview);
        Glide.with(this).load(imageUrl).into(imageView);
    }
}
