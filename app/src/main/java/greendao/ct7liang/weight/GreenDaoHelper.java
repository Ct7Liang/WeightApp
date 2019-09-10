package greendao.ct7liang.weight;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.ct7liang.weight.bean.Weight;
import com.github.yuweiguocn.library.greendao.MigrationHelper;

import org.greenrobot.greendao.database.Database;

public class GreenDaoHelper {
    private static DaoSession mDaoSession;
    private static GreenDaoHelper mGreenDaoHelper;
    /**
     * 初始化工具类
     * @param context 应用
     * @param dbName 数据库名称
     */
    public static void init(Context context, String dbName){
        if (mGreenDaoHelper==null){
            mGreenDaoHelper = new GreenDaoHelper(context, dbName);
        }
    }
    /**
     * 创建GreenDao工具类
     * @param context 应用
     * @param dbName 数据库名称
     */
    private GreenDaoHelper(Context context, String dbName){
        DaoMaster.DevOpenHelper mHelper = new MyDevOpenHelper(context, dbName, null);
        SQLiteDatabase db = mHelper.getWritableDatabase();
        DaoMaster mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }
    /**
     * 获取DaoSession
     * @return DaoSession
     * @throws Exception 工具类未初始化
     */
    public static DaoSession getDao() {
        return mDaoSession;
    }
    /**
     * 继承DaoMaster.DevOpenHelper类
     * 重写onUpgrade()方法
     */
    private class MyDevOpenHelper extends DaoMaster.DevOpenHelper{
        MyDevOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
            super(context, name, factory);
        }
        @Override
        public void onUpgrade(Database db, int oldVersion, int newVersion) {
            MigrationHelper.migrate(db, new MigrationHelper.ReCreateAllTableListener() {
                @Override
                public void onCreateAllTables(Database db, boolean ifNotExists) {
                    DaoMaster.createAllTables(db, ifNotExists);
                }
             	  @Override
                public void onDropAllTables(Database db, boolean ifExists) {
                    DaoMaster.dropAllTables(db, ifExists);
                }
           }, WeightDao.class); //传入需要更新的表实体类
        }
    }
}