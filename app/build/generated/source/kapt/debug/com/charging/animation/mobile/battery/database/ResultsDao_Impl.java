package com.charging.animation.mobile.battery.database;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.charging.animation.mobile.battery.api.Results;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class ResultsDao_Impl implements ResultsDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Results> __insertionAdapterOfResults;

  private final EntityDeletionOrUpdateAdapter<Results> __deletionAdapterOfResults;

  private final EntityDeletionOrUpdateAdapter<Results> __updateAdapterOfResults;

  public ResultsDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfResults = new EntityInsertionAdapter<Results>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `Results` (`id`,`link`,`type`,`folder`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Results entity) {
        statement.bindLong(1, entity.id);
        if (entity.link == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.link);
        }
        statement.bindLong(3, entity.type);
        if (entity.folder == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.folder);
        }
      }
    };
    this.__deletionAdapterOfResults = new EntityDeletionOrUpdateAdapter<Results>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `Results` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Results entity) {
        statement.bindLong(1, entity.id);
      }
    };
    this.__updateAdapterOfResults = new EntityDeletionOrUpdateAdapter<Results>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `Results` SET `id` = ?,`link` = ?,`type` = ?,`folder` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Results entity) {
        statement.bindLong(1, entity.id);
        if (entity.link == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.link);
        }
        statement.bindLong(3, entity.type);
        if (entity.folder == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.folder);
        }
        statement.bindLong(5, entity.id);
      }
    };
  }

  @Override
  public void insert(final Results results) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfResults.insert(results);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final Results results) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfResults.handle(results);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final Results results) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfResults.handle(results);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<Results> getAll(final String folder) {
    final String _sql = "SELECT * FROM results WHERE folder LIKE ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (folder == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, folder);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfLink = CursorUtil.getColumnIndexOrThrow(_cursor, "link");
      final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
      final int _cursorIndexOfFolder = CursorUtil.getColumnIndexOrThrow(_cursor, "folder");
      final List<Results> _result = new ArrayList<Results>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Results _item;
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        final String _tmpLink;
        if (_cursor.isNull(_cursorIndexOfLink)) {
          _tmpLink = null;
        } else {
          _tmpLink = _cursor.getString(_cursorIndexOfLink);
        }
        final int _tmpType;
        _tmpType = _cursor.getInt(_cursorIndexOfType);
        final String _tmpFolder;
        if (_cursor.isNull(_cursorIndexOfFolder)) {
          _tmpFolder = null;
        } else {
          _tmpFolder = _cursor.getString(_cursorIndexOfFolder);
        }
        _item = new Results(_tmpId,_tmpLink,_tmpType,_tmpFolder);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Results> getAll(final int type) {
    final String _sql = "SELECT * FROM results WHERE  type=? ORDER BY id DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, type);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfLink = CursorUtil.getColumnIndexOrThrow(_cursor, "link");
      final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
      final int _cursorIndexOfFolder = CursorUtil.getColumnIndexOrThrow(_cursor, "folder");
      final List<Results> _result = new ArrayList<Results>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Results _item;
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        final String _tmpLink;
        if (_cursor.isNull(_cursorIndexOfLink)) {
          _tmpLink = null;
        } else {
          _tmpLink = _cursor.getString(_cursorIndexOfLink);
        }
        final int _tmpType;
        _tmpType = _cursor.getInt(_cursorIndexOfType);
        final String _tmpFolder;
        if (_cursor.isNull(_cursorIndexOfFolder)) {
          _tmpFolder = null;
        } else {
          _tmpFolder = _cursor.getString(_cursorIndexOfFolder);
        }
        _item = new Results(_tmpId,_tmpLink,_tmpType,_tmpFolder);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Results> getAll() {
    final String _sql = "SELECT * FROM results";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfLink = CursorUtil.getColumnIndexOrThrow(_cursor, "link");
      final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
      final int _cursorIndexOfFolder = CursorUtil.getColumnIndexOrThrow(_cursor, "folder");
      final List<Results> _result = new ArrayList<Results>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Results _item;
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        final String _tmpLink;
        if (_cursor.isNull(_cursorIndexOfLink)) {
          _tmpLink = null;
        } else {
          _tmpLink = _cursor.getString(_cursorIndexOfLink);
        }
        final int _tmpType;
        _tmpType = _cursor.getInt(_cursorIndexOfType);
        final String _tmpFolder;
        if (_cursor.isNull(_cursorIndexOfFolder)) {
          _tmpFolder = null;
        } else {
          _tmpFolder = _cursor.getString(_cursorIndexOfFolder);
        }
        _item = new Results(_tmpId,_tmpLink,_tmpType,_tmpFolder);
        _result.add(_item);
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
