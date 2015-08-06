package com.example.android.popularmovies.data;

import android.content.Context;
import android.util.Log;

import com.example.android.popularmovies.utils.JsonUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by Denny on 8/2/2015.
 */
public class MovieReview implements Serializable {
    private static final String LOG_TAG = MovieReview.class.getSimpleName();

    private String id;
    private String author;
    private String content;

    public MovieReview() {

    }

    public MovieReview(Context c, JSONObject obj) {
        deserialize(c, obj);
    }

    public void deserialize(Context c, JSONObject obj) {
        if(obj == null) {
            return;
        }
        try {
            setId(obj.getString("id"));
            setAuthor(JsonUtil.getString(obj, "author"));
            setContent(JsonUtil.getString(obj, "content"));
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Unable to deserialize JSONObject.", e);
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
