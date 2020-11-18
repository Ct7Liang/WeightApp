package greendao.ct7liang.weight;

import com.ct7liang.weight.bean.Weight;

import java.util.ArrayList;
import java.util.List;

public class WeightDaoUtils {

    private static WeightDao dao;

    private static void getWeightDao(){
        if (dao == null){
            dao = GreenDaoHelper.getDao().getWeightDao();
        }
    }

    /**
     * 获取所有录入的数据
     * @return List
     */
    public static List<Weight> getAllDatas(){
        getWeightDao();
        return dao.queryBuilder().where(
                WeightDao.Properties.Am.gt(0f),
                WeightDao.Properties.Pm.gt(0f)
        ).orderAsc(WeightDao.Properties.Time).list();
    }

    /**
     * 根据日期查询记录数据
     * @param year 年
     * @param month 月
     * @param day 日
     */
    public static Weight getWeightByDate(int year, int month, int day){
        getWeightDao();
        return dao.queryBuilder().where(
                WeightDao.Properties.Year.like(String.valueOf(year)),
                WeightDao.Properties.Month.like(String.valueOf(month)),
                WeightDao.Properties.Day.like(String.valueOf(day))
        ).build().unique();
    }

    /**
     * 获取一个月中有数据的天数
     * @param year
     * @param month
     * @return
     */
    public static List<String> getMonthWeight(int year, int month){
        getWeightDao();
        List<Weight> list = dao.queryBuilder().where(
                WeightDao.Properties.Year.like(String.valueOf(year)),
                WeightDao.Properties.Month.like(String.valueOf(month))
        ).build().list();
        List<String> str = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Weight weight = list.get(i);
            if (weight.getAm() > 0 && weight.getPm() > 0){
                str.add(weight.getDay());
            }
        }
        return str;
    }

    /**
     * 新增记录
     * @param year 年
     * @param month 月
     * @param day 日
     * @param am 上午数据
     * @param pm 下午数据
     */
    public static void insertWeight(int year, int month, int day, float am, float pm, long time){
        getWeightDao();
        dao.insert(new Weight(null, am, pm, String.valueOf(year), String.valueOf(month), String.valueOf(day), time));
    }

    /**
     * 修改记录
     * @param weight 数据模型
     */
    public static void updateWeight(Weight weight){
        getWeightDao();
        dao.update(weight);
    }

}
