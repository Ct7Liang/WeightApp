package greendao.ct7liang.weight;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.ct7liang.weight.bean.Weight;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "WEIGHT".
*/
public class WeightDao extends AbstractDao<Weight, Long> {

    public static final String TABLENAME = "WEIGHT";

    /**
     * Properties of entity Weight.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Weight = new Property(1, float.class, "weight", false, "WEIGHT");
        public final static Property Time = new Property(2, long.class, "time", false, "TIME");
        public final static Property Date = new Property(3, String.class, "date", false, "DATE");
        public final static Property Year = new Property(4, String.class, "year", false, "YEAR");
        public final static Property Month = new Property(5, String.class, "month", false, "MONTH");
        public final static Property Day = new Property(6, String.class, "day", false, "DAY");
        public final static Property Hour = new Property(7, String.class, "hour", false, "HOUR");
        public final static Property Minute = new Property(8, String.class, "minute", false, "MINUTE");
        public final static Property Second = new Property(9, String.class, "second", false, "SECOND");
    }


    public WeightDao(DaoConfig config) {
        super(config);
    }
    
    public WeightDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"WEIGHT\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"WEIGHT\" REAL NOT NULL ," + // 1: weight
                "\"TIME\" INTEGER NOT NULL ," + // 2: time
                "\"DATE\" TEXT," + // 3: date
                "\"YEAR\" TEXT," + // 4: year
                "\"MONTH\" TEXT," + // 5: month
                "\"DAY\" TEXT," + // 6: day
                "\"HOUR\" TEXT," + // 7: hour
                "\"MINUTE\" TEXT," + // 8: minute
                "\"SECOND\" TEXT);"); // 9: second
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"WEIGHT\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Weight entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindDouble(2, entity.getWeight());
        stmt.bindLong(3, entity.getTime());
 
        String date = entity.getDate();
        if (date != null) {
            stmt.bindString(4, date);
        }
 
        String year = entity.getYear();
        if (year != null) {
            stmt.bindString(5, year);
        }
 
        String month = entity.getMonth();
        if (month != null) {
            stmt.bindString(6, month);
        }
 
        String day = entity.getDay();
        if (day != null) {
            stmt.bindString(7, day);
        }
 
        String hour = entity.getHour();
        if (hour != null) {
            stmt.bindString(8, hour);
        }
 
        String minute = entity.getMinute();
        if (minute != null) {
            stmt.bindString(9, minute);
        }
 
        String second = entity.getSecond();
        if (second != null) {
            stmt.bindString(10, second);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Weight entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindDouble(2, entity.getWeight());
        stmt.bindLong(3, entity.getTime());
 
        String date = entity.getDate();
        if (date != null) {
            stmt.bindString(4, date);
        }
 
        String year = entity.getYear();
        if (year != null) {
            stmt.bindString(5, year);
        }
 
        String month = entity.getMonth();
        if (month != null) {
            stmt.bindString(6, month);
        }
 
        String day = entity.getDay();
        if (day != null) {
            stmt.bindString(7, day);
        }
 
        String hour = entity.getHour();
        if (hour != null) {
            stmt.bindString(8, hour);
        }
 
        String minute = entity.getMinute();
        if (minute != null) {
            stmt.bindString(9, minute);
        }
 
        String second = entity.getSecond();
        if (second != null) {
            stmt.bindString(10, second);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Weight readEntity(Cursor cursor, int offset) {
        Weight entity = new Weight( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getFloat(offset + 1), // weight
            cursor.getLong(offset + 2), // time
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // date
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // year
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // month
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // day
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // hour
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // minute
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9) // second
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Weight entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setWeight(cursor.getFloat(offset + 1));
        entity.setTime(cursor.getLong(offset + 2));
        entity.setDate(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setYear(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setMonth(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setDay(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setHour(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setMinute(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setSecond(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Weight entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Weight entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Weight entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}