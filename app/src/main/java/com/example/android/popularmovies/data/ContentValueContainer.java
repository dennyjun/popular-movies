package com.example.android.popularmovies.data;

import android.content.ContentValues;
import android.content.Context;

import java.io.Serializable;

/**
 * Created by Denny on 8/18/2015.
 */
public interface ContentValueContainer extends Serializable {
    void readContentValues(final Context context, final ContentValues values);
    ContentValues createContentValues(final Context context);
}
