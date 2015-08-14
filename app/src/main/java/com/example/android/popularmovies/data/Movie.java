package com.example.android.popularmovies.data;

import android.content.ContentValues;
import android.content.Context;
import android.os.Parcelable;
import android.util.Log;

import com.example.android.popularmovies.R;
import com.example.android.popularmovies.utils.AppUtil;
import com.example.android.popularmovies.utils.JsonUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by Denny on 7/28/2015.
 */
public class Movie implements Serializable {
    private static final String LOG_TAG = Movie.class.getSimpleName();

    private boolean adult;
    private String backdropPath;
    private String[] genreIds;
    private String id;
    private String originalLanguage;
    private String originalTitle;
    private String overview;
    private String releaseDate;
    private String posterPath;
    private String popularity;
    private String title;
    private boolean video;
    private double voteAverage;
    private int voteCount;
    private String posterUrl;

    public Movie(final Context c, final JSONObject obj) {
        deserialize(c, obj);
    }

    public Movie(final Context context, final Parcelable values) {
        final ContentValues v = (ContentValues) values;
        setId(
                (String) v.get(context.getString(R.string.moviedb_id_param)));
        setAdult(
                Boolean.valueOf(v.get(context.getString(R.string.moviedb_adult_param)).toString()));
        setOriginalLanguage(
                (String)v.get(context.getString(R.string.moviedb_original_language_param)));
        setOriginalTitle(
                (String)v.get(context.getString(R.string.moviedb_original_title_param)));
        setOverview(
                (String)v.get(context.getString(R.string.moviedb_overview_param)));
        setReleaseDate(
                (String)v.get(context.getString(R.string.moviedb_release_date_param)));
        setPosterPath(
                (String)v.get(context.getString(R.string.moviedb_poster_path_param)));
        setTitle(
                (String)v.get(context.getString(R.string.moviedb_title_param)));
        setVideo(
                Boolean.valueOf(v.get(context.getString(R.string.moviedb_video_param)).toString()));
        setVoteAverage(
                Double.valueOf(v.get(context.getString(R.string.moviedb_vote_avg_param)).toString()));
        setVoteCount(
                Integer.valueOf(v.get(context.getString(R.string.moviedb_vote_count_param)).toString()));
        setPosterUrl(
                (String)v.get(context.getString(R.string.movie_poster_url_param)));

    }

    public void deserialize(final Context c, final JSONObject obj) {
        if(obj == null) {
            return;
        }
        try {
            setAdult(obj.getBoolean(c.getString(R.string.moviedb_adult_param)));
            setBackdropPath(obj.getString(c.getString(R.string.moviedb_backdrop_path_param)));
            final JSONArray a = obj.getJSONArray(c.getString(R.string.moviedb_genre_ids_param));
            setGenreIds(new String[a.length()]);
            for(int i = 0; i < a.length(); ++i) {
                getGenreIds()[i] = a.getString(i);
            }
            setId(obj.getString(c.getString(R.string.moviedb_id_param)));
            setOriginalLanguage(
                    obj.getString(c.getString(R.string.moviedb_original_language_param)));
            setOriginalTitle(JsonUtil.getString(obj,
                    c.getString(R.string.moviedb_original_title_param)));
            setOverview(JsonUtil.getString(obj, c.getString(R.string.moviedb_overview_param)));
            final String inputDateFormat = c.getString(R.string.date_format_yyyy_mm_dd);
            final String outputDateFormat = c.getString(R.string.date_format_mm_dd_yyyy_use_slash);
            final String date = AppUtil.convertDateString(
                    obj.getString(c.getString(R.string.moviedb_release_date_param)),
                    inputDateFormat,
                    outputDateFormat);
            setReleaseDate(date);
            setPosterPath(obj.getString(c.getString(R.string.moviedb_poster_path_param)));
            setPopularity(obj.getString(c.getString(R.string.moviedb_popularity_param)));
            setTitle(JsonUtil.getString(obj, c.getString(R.string.moviedb_title_param)));
            setVideo(obj.getBoolean(c.getString(R.string.moviedb_video_param)));
            setVoteAverage(obj.getDouble(c.getString(R.string.moviedb_vote_avg_param)));
            setVoteCount(obj.getInt(c.getString(R.string.moviedb_vote_count_param)));
            setPosterUrl(buildPosterUrl(c, getPosterPath()));
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Unable to deserialize JSONObject.", e);
        }
    }

    public ContentValues createContentValues(final Context context) {
        final ContentValues values = new ContentValues();
        values.put(context.getString(R.string.moviedb_id_param),
                getId());
        values.put(context.getString(R.string.moviedb_adult_param),
                isAdult());
        values.put(context.getString(R.string.moviedb_original_language_param),
                getOriginalLanguage());
        values.put(context.getString(R.string.moviedb_original_title_param),
                getOriginalTitle());
        values.put(context.getString(R.string.moviedb_overview_param),
                getOverview());
        values.put(context.getString(R.string.moviedb_release_date_param),
                getReleaseDate());
        values.put(context.getString(R.string.moviedb_poster_path_param),
                getPosterPath());
        values.put(context.getString(R.string.moviedb_title_param),
                getTitle());
        values.put(context.getString(R.string.moviedb_video_param),
                isVideo());
        values.put(context.getString(R.string.moviedb_vote_avg_param),
                getVoteAverage());
        values.put(context.getString(R.string.moviedb_vote_count_param),
                getVoteCount());
        values.put(context.getString(R.string.movie_poster_url_param),
                getPosterUrl());
        return values;
    }

    private String buildPosterUrl(final Context c, final String posterPath) {
        return c.getString(R.string.moviedb_poster_path_base_url)
                + c.getString(R.string.moviedb_poster_path_size_w185)
                + posterPath;
    }

    @Override
    public boolean equals(Object o) {
        if(!super.equals(o)) {
            return getId().equals(((Movie) o).getId());
        }
        return true;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String[] getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(String[] genreIds) {
        this.genreIds = genreIds;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }
}
