package cn.litchi.wyf.mymovies;

import java.util.ArrayList;

/**
 * Created by wangyifeng on 2017/1/20.
 */

public class MoviesData {

    /**
     * page : 1
     * results :
     * total_results : 19533
     * total_pages : 977
     */

    private int page;
    private int total_results;
    private int total_pages;
    private ArrayList<ResultsBean> results;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public ArrayList<ResultsBean> getResults() {
        return results;
    }

    public void setResults(ArrayList<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * poster_path : /AgzX7mmCrQcSozvqWGwSpFAsEXj.jpg
         * adult : false
         * overview : 讲述了在纽约一幢热闹的公寓大楼里...
         * release_date : 2016-06-18
         * genre_ids : [12,16,35,10751]
         * id : 328111
         * original_title : The Secret Life of Pets
         * original_language : en
         * title : 爱宠大机密
         * backdrop_path : /lubzBMQLLmG88CLQ4F3TxZr2Q7N.jpg
         * popularity : 230.14855
         * vote_count : 1788
         * video : false
         * vote_average : 5.8
         */

        private String poster_path;
        private boolean adult;
        private String release_date;
        private String overview;
        private int id;
        private String original_title;
        private String original_language;
        private String title;
        private double popularity;
        private int vote_count;
        private boolean video;
        private double vote_average;

        public String getOverview() {
            return overview;
        }

        public void setOverview(String overview) {
            this.overview = overview;
        }

        public String getPoster_path() {
            return poster_path;
        }

        public void setPoster_path(String poster_path) {
            this.poster_path = poster_path;
        }

        public boolean isAdult() {
            return adult;
        }

        public void setAdult(boolean adult) {
            this.adult = adult;
        }


        public String getRelease_date() {
            return release_date;
        }

        public void setRelease_date(String release_date) {
            this.release_date = release_date;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getOriginal_title() {
            return original_title;
        }

        public void setOriginal_title(String original_title) {
            this.original_title = original_title;
        }

        public String getOriginal_language() {
            return original_language;
        }

        public void setOriginal_language(String original_language) {
            this.original_language = original_language;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public double getPopularity() {
            return popularity;
        }

        public void setPopularity(double popularity) {
            this.popularity = popularity;
        }

        public int getVote_count() {
            return vote_count;
        }

        public void setVote_count(int vote_count) {
            this.vote_count = vote_count;
        }

        public boolean isVideo() {
            return video;
        }

        public void setVideo(boolean video) {
            this.video = video;
        }

        public double getVote_average() {
            return vote_average;
        }

        public void setVote_average(double vote_average) {
            this.vote_average = vote_average;
        }

    }
}
