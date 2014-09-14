package org.lunding.sleepguru;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Lunding on 13/09/14.
 */
public class StatusContract {

    public static final String DB_NAME = "sleep.db";
    public static final int DB_VERSION = 2;
    public static final String TABLE = "tests";

    public static final String AUTHORITY = "org.lunding.sleepguru.StatusProvider";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + TABLE);
    public static final int STATUS_ITEM = 1;
    public static final int STATUS_DIR = 2;
    public static final String STATUS_TYPE_ITEM =
            "vnd.android.cursor.item/vnd.org.lunding.sleepguru.provider.status";
    public static final String STATUS_TYPE_DIR =
            "vnd.android.cursor.item/vnd.org.lunding.sleepguru.provider.status";

    public static final String DEFAULT_SORT = Column.CREATED_AT + " DESC";

    public class Column {
        public static final String ID = BaseColumns._ID;
        public static final String USER = "user";
        public static final String SCORE = "score";
        public static final String CREATED_AT = "created_at";
    }
}
