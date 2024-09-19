package com.charging.animation.mobile.battery.database;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class ActiveDao_Impl implements ActiveDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Active> __insertionAdapterOfActive;

  private final EntityDeletionOrUpdateAdapter<Active> __deletionAdapterOfActive;

  private final EntityDeletionOrUpdateAdapter<Active> __updateAdapterOfActive;

  public ActiveDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfActive = new EntityInsertionAdapter<Active>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `Active` (`id`,`Ads`) VALUES (nullif(?, 0),?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Active entity) {
        statement.bindLong(1, entity.id);
        if (entity.ads == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.ads);
        }
      }
    };
    this.__deletionAdapterOfActive = new EntityDeletionOrUpdateAdapter<Active>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `Active` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Active entity) {
        statement.bindLong(1, entity.id);
      }
    };
    this.__updateAdapterOfActive = new EntityDeletionOrUpdateAdapter<Active>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `Active` SET `id` = ?,`Ads` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Active entity) {
        statement.bindLong(1, entity.id);
        if (entity.ads == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.ads);
        }
        statement.bindLong(3, entity.id);
      }
    };
  }

  @Override
  public void insert(final Active active) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfActive.insert(active);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final Active active) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfActive.handle(active);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final Active active) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfActive.handle(active);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public boolean getByLink(final String link) {
    final String _sql = "SELECT * FROM Active WHERE ads LIKE ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (link == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, link);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final boolean _result;
      if (_cursor.moveToFirst()) {
        final int _tmp;
        _tmp = _cursor.getInt(0);
        _result = _tmp != 0;
      } else {
        _result = false;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
