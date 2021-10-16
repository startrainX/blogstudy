package com.example.blogstudy.utils;

/**
 * 各地图API坐标系统比较与转换;
 * WGS84坐标系：即地球坐标系，国际上通用的坐标系。设备一般包含GPS芯片或者北斗芯片获取的经纬度为WGS84地理坐标系,
 * 谷歌地图采用的是WGS84地理坐标系（中国范围除外）;
 * GCJ02坐标系：即火星坐标系，是由中国国家测绘局制订的地理信息系统的坐标系统。由WGS84坐标系经加密后的坐标系。
 * 谷歌中国地图和搜搜中国地图采用的是GCJ02地理坐标系; BD09坐标系：即百度坐标系，GCJ02坐标系经加密后的坐标系;
 * 搜狗坐标系、图吧坐标系等，估计也是在GCJ02基础上加密而成的。
 * <p>
 * <p>
 * WGS-84 地心坐标系，即GPS原始坐标体系
 * GCJ-02火星坐标系，国测局02年发布的坐标体系，它是一种对经纬度数据的加密算法，即加入随机的偏差。高德、腾讯、Google中国地图使用。国内最广泛使用的坐标体系；
 * 一般都是由GCJ-02进过偏移算法得到的。这种体系就根据每个公司的不同，坐标体系都不一样了。
 * 比如，百度的BD-09坐标、搜狗坐标等
 */
public class GPSUtils {

    public static double pi = Math.PI;
    public static double x_pi = Math.PI * 3000.0 / 180.0;
    public static double a = 6378245.0;
    public static double ee = 0.00669342162296594323;

    public static double transformLat(double x, double y) {
        double ret = -100.0 + 2.0 * x + 3.0 * y + 0.2 * y * y + 0.1 * x * y
                + 0.2 * Math.sqrt(Math.abs(x));
        ret += (20.0 * Math.sin(6.0 * x * pi) + 20.0 * Math.sin(2.0 * x * pi)) * 2.0 / 3.0;
        ret += (20.0 * Math.sin(y * pi) + 40.0 * Math.sin(y / 3.0 * pi)) * 2.0 / 3.0;
        ret += (160.0 * Math.sin(y / 12.0 * pi) + 320 * Math.sin(y * pi / 30.0)) * 2.0 / 3.0;
        return ret;
    }

    public static double transformLon(double x, double y) {
        double ret = 300.0 + x + 2.0 * y + 0.1 * x * x + 0.1 * x * y + 0.1
                * Math.sqrt(Math.abs(x));
        ret += (20.0 * Math.sin(6.0 * x * pi) + 20.0 * Math.sin(2.0 * x * pi)) * 2.0 / 3.0;
        ret += (20.0 * Math.sin(x * pi) + 40.0 * Math.sin(x / 3.0 * pi)) * 2.0 / 3.0;
        ret += (150.0 * Math.sin(x / 12.0 * pi) + 300.0 * Math.sin(x / 30.0
                * pi)) * 2.0 / 3.0;
        return ret;
    }

    public static boolean outOfChina(double lat, double lon) {
        if (lon < 72.004 || lon > 137.8347)
            return true;
        if (lat < 0.8293 || lat > 55.8271)
            return true;
        return false;
    }

    /**
     * 84 to 火星坐标系 (GCJ-02) World Geodetic System ==> Mars Geodetic System
     *
     * @param lat
     * @param lon
     * @return
     */
    public static double[] wgs84ToGCJ02(double lat, double lon) {
        if (outOfChina(lat, lon)) {
            return new double[]{lat, lon};
        }
        double dLat = transformLat(lon - 105.0, lat - 35.0);
        double dLon = transformLon(lon - 105.0, lat - 35.0);
        double radLat = lat / 180.0 * pi;
        double magic = Math.sin(radLat);
        magic = 1 - ee * magic * magic;
        double sqrtMagic = Math.sqrt(magic);
        dLat = (dLat * 180.0) / ((a * (1 - ee)) / (magic * sqrtMagic) * pi);
        dLon = (dLon * 180.0) / (a / sqrtMagic * Math.cos(radLat) * pi);
        double mgLat = lat + dLat;
        double mgLon = lon + dLon;
        mgLat = ArithUtil.round(mgLat, 7);
        mgLon = ArithUtil.round(mgLon, 7);
        return new double[]{mgLat, mgLon};
    }

    /**
     * * 火星坐标系 (GCJ-02) to 84 * * @param lon * @param lat * @return
     */
    public static double[] gcj02ToWGS84(double lat, double lon) {
        double[] gps = wgs84ToGCJ02(lat, lon);
        double lontitude = lon * 2 - gps[1];
        double latitude = lat * 2 - gps[0];
        return new double[]{latitude, lontitude};
    }

    /**
     * 火星坐标系 (GCJ-02) 与百度坐标系 (BD-09) 的转换算法 将 GCJ-02 坐标转换成 BD-09 坐标
     *
     * @param lat
     * @param lon
     */
    public static double[] gcj02ToBD09(double lat, double lon) {
        double x = lon, y = lat;
        double z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * x_pi);
        double theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * x_pi);
        double tempLon = z * Math.cos(theta) + 0.0065;
        double tempLat = z * Math.sin(theta) + 0.006;
        double[] gps = {tempLat, tempLon};
        return gps;
    }

    /**
     * * 火星坐标系 (GCJ-02) 与百度坐标系 (BD-09) 的转换算法 * * 将 BD-09 坐标转换成GCJ-02 坐标 * * @param
     * bd_lat * @param bd_lon * @return
     */
    public static double[] bd09ToGCJ02(double lat, double lon) {
        double x = lon - 0.0065, y = lat - 0.006;
        double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * x_pi);
        double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * x_pi);
        double tempLon = z * Math.cos(theta);
        double tempLat = z * Math.sin(theta);
        double[] gps = {tempLat, tempLon};
        return gps;
    }

    /**
     * 将wgs84转为bd09
     *
     * @param lat
     * @param lon
     * @return
     */
    public static double[] wgs84ToBD09(double lat, double lon) {
        double[] gcj02 = wgs84ToGCJ02(lat, lon);
        double[] bd09 = gcj02ToBD09(gcj02[0], gcj02[1]);
        return bd09;
    }

    /**
     * 百度转WGS84
     *
     * @param lat
     * @param lon
     * @return
     */
    public static double[] bd09ToWGS84(double lat, double lon) {
        double[] gcj02 = bd09ToGCJ02(lat, lon);
        double[] wgs84 = gcj02ToWGS84(gcj02[0], gcj02[1]);
        //保留小数点后10位
        wgs84[0] = ArithUtil.round(wgs84[0], 10);
        wgs84[1] = ArithUtil.round(wgs84[1], 10);
        return wgs84;
    }

    //经纬度转墨卡托
    // 经度(lon)，纬度(lat)
    public static double[] wgs84ToMercator(double lon, double lat) {
        double[] gps = new double[2];
        double x = lon * 20037508.34 / 180;
        double y = Math.log(Math.tan((90 + lat) * pi / 360)) / (pi / 180);
        y = y * 20037508.34 / 180;
        gps[0] = x;
        gps[1] = y;
        return gps;
    }

    //墨卡托转经纬度
    public static double[] mercatorToWGS84(double mercatorX, double mercatorY) {
        double[] gps = new double[2];
        double x = mercatorX / 20037508.34 * 180;
        double y = mercatorY / 20037508.34 * 180;
        y = 180 / pi * (2 * Math.atan(Math.exp(y * pi / 180)) - pi / 2);
        gps[0] = x;
        gps[1] = y;
        return gps;
    }

    /**
     * 墨卡托转火星
     *
     * @param mercatorX
     * @param mercatorY
     * @return
     */
    public static double[] mercatorToGCJ02(double mercatorX, double mercatorY) {
        double[] gps = mercatorToWGS84(mercatorX, mercatorY);
        gps = wgs84ToGCJ02(gps[0], gps[1]);
        return gps;
    }

    public static double[] gcj02ToMercator(double lon, double lat) {
        double[] gps = gcj02ToWGS84(lon, lat);
        gps = wgs84ToMercator(gps[0], gps[1]);
        return gps;
    }

    public static void main(String[] args) {
        String a = "[[120.74340052846823,30.526482549402758],[120.74386556146818,30.52550904340272],[120.74395636046815,30.52531893740273],[120.74592448146815,30.522495909402707],[120.74666752646819,30.52142994940276],[120.75047128846815,30.51920298940273],[120.75603436846815,30.516913749402747],[120.75577480846813,30.516179979402736],[120.75331204846817,30.515799099402713],[120.7533199684682,30.513833589402722],[120.75804748846816,30.51280659940271],[120.75806296846817,30.509054229402736],[120.75293656846817,30.507218499402754],[120.75131764846823,30.50130630940275],[120.74763700846815,30.497518749402715],[120.7464061684682,30.49714956940271],[120.74641948846819,30.493933329402736],[120.74744692846822,30.493764399402707],[120.7476676084682,30.490014099402728],[120.74398012846815,30.487834659402708],[120.74420152846814,30.483905529402747],[120.74051044846819,30.482619519402746],[120.73845808846819,30.480627639402755],[120.7304552884682,30.480762279402754],[120.73008628846823,30.48494520940271],[120.72550888846821,30.48510576940275],[120.72494584846812,30.479382309402737],[120.71814040846813,30.479113209402712],[120.71802520846815,30.478601559402748],[120.71333188846815,30.47845467940276],[120.71332684846813,30.479679939402715],[120.69983116846812,30.47985750940272],[120.69990028846816,30.491600619402718],[120.6960292084682,30.491257359402752],[120.69557092846823,30.48849606940273],[120.69064360846814,30.488244879402757],[120.68845048846812,30.488289789402742],[120.6882773284682,30.492919299402715],[120.69062380846823,30.493043769402718],[120.69062128846817,30.493656399402752],[120.69214696846815,30.493568829402747],[120.69224092846822,30.499287789402715],[120.69118360846814,30.499584069402758],[120.69094348846819,30.500909139402708],[120.68953480846814,30.500997789402717],[120.68949376846821,30.511003989402738],[120.68796808846822,30.511091559402743],[120.6877063684682,30.51762402940271],[120.68136928846822,30.517665609402727],[120.68075080846815,30.525420009402737],[120.67793200846815,30.52600572940274],[120.67871608846816,30.535100859402732],[120.66893164846819,30.534779379402707],[120.67260436846813,30.537390999402746],[120.67694200846815,30.538453449402752],[120.67693408846823,30.540393489402717],[120.68185960846814,30.54115515940275],[120.68302024846822,30.544331619402712],[120.68513200846816,30.54445383940275],[120.69440740846814,30.54321489940274],[120.69829828846822,30.538759269402746],[120.70089676846818,30.534699729402732],[120.70418140846813,30.53493525940273],[120.70618228846821,30.53352486940274],[120.71921392846822,30.53201538940276],[120.72884644846819,30.52965666940274],[120.73906612846817,30.527099319402723],[120.74340052846823,30.526482549402758]]";
        String substring = a.substring(1, a.length() - 1);
        String[] split = substring.split(",");
        for (int i = 0; i < split.length; i++) {
            if (i % 2 == 0) {
                split[i] = split[i].substring(1, split[i].length());
            } else {
                split[i] = split[i].substring(0, split[i].length() - 1);
            }

            String str = "[";
            for (int j = 0; j < split.length; j = j + 2) {
                double[] doubles = wgs84ToGCJ02(Double.valueOf(split[j + 1]), Double.valueOf(split[j]));
                str += "[" + doubles[1] + "," + doubles[0] + "],";
            }
            str = str.substring(0, str.length() - 1);
            str = str + "]";
            System.out.print(str);
        }
    }

}
