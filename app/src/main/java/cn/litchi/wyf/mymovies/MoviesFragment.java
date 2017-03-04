package cn.litchi.wyf.mymovies;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by wangyifeng on 2017/1/18.
 */

public class MoviesFragment extends Fragment {

    private MovieGridViewAdapter movieGridViewAdapter;
    private ArrayList<MovieItem> movieItems;

    public MoviesFragment(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.moviesfragment,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.action_hot){
            FetchMoviesTask moviesTask = new FetchMoviesTask();
            moviesTask.execute("top_rated");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View moviesView = inflater.inflate(R.layout.movies_frag,container,false);
        GridView gridView = (GridView)moviesView.findViewById(R.id.movies_gridview);

        movieItems = new ArrayList<MovieItem>();
        movieGridViewAdapter=new MovieGridViewAdapter(inflater.getContext(),R.layout.movie_item,movieItems);
        gridView.setAdapter(movieGridViewAdapter);

        FetchMoviesTask moviesTask = new FetchMoviesTask();
        moviesTask.execute();
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MovieItem item = (MovieItem)parent.getItemAtPosition(position);

                Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
                intent.putExtra("title",item.getTitle());
                intent.putExtra("img",item.getImage());
                intent.putExtra("date",item.getReleaseDate());
                intent.putExtra("vote",item.getVoteAverage());
                intent.putExtra("overview",item.getOverview());
                startActivity(intent);
            }
        });
        return moviesView;
    }

    //内部类，获取电影数据
    public class FetchMoviesTask extends AsyncTask<String,Void,MoviesData>{
        private final String LOG_TAG = FetchMoviesTask.class.getSimpleName();
        @Override
        protected MoviesData doInBackground(String... params) {
            String movieSort;
            if(params.length==0){
                movieSort="popular";
            }else {
                movieSort=params[0];
            }
            //定义http连接
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            String moviesJsonStr = null;
            try{
                String baseUrl = "http://api.themoviedb.org/3/movie/"+movieSort+"?language=zh&api_key=";
                String apiKey = BuildConfig.OPEN_MOVIES_API_KEY;
                URL url = new URL(baseUrl.concat(apiKey));
                urlConnection = (HttpURLConnection)url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();
                //读取http返回的数据到字符串中
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if(inputStream == null){
                    return null;
                }
                reader =new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    return null;
                }
                moviesJsonStr = buffer.toString();

            }catch (IOException e){
                Log.v(LOG_TAG,"Error",e);
                return null;
            }finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e(LOG_TAG, "Error closing stream", e);
                    }
                }
            }

            return getMovies(moviesJsonStr);

        }

        @Override
        protected void onPostExecute(MoviesData data) {
            movieGridViewAdapter.clear();
            MovieItem movieItem = null;
            for(int i=0;i<data.getResults().size();i++){
                movieItem = new MovieItem();
                MoviesData.ResultsBean tmp = data.getResults().get(i);
                movieItem.setTitle(tmp.getTitle());
                movieItem.setImage(tmp.getPoster_path());
                movieItem.setReleaseDate(tmp.getRelease_date());
                movieItem.setOverview(tmp.getOverview());
                movieItem.setVoteAverage(tmp.getVote_average());
                movieItems.add(movieItem);
            }
            movieGridViewAdapter.setGridData(movieItems);
        }
        //json解析
        private MoviesData getMovies(String json){
            Gson gson = new Gson();
            return gson.fromJson(json, new TypeToken<MoviesData>() {}.getType());
        }
    }
}
