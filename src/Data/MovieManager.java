package Data;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * 电影管理系统
 * 
 * @author 李晶晶
 *
 */
public class MovieManager {
	static Scanner scanner = new Scanner(System.in);

	/**
	 * 字符串转化整数
	 * <p>
	 * 将字符串的整数转成整数
	 * </p>
	 * 
	 * @param strInt 字符串
	 * @return num 整数
	 */
	private static int IntegerConvert(String strInt) {
		int num = 0;
		try {
			num = Integer.parseInt(strInt);
		} catch (NumberFormatException e) {
			System.err.println("你要转换的字符串不包含数字，无法转换");
		}
		return num;

	}

	/**
	 * 添加电影
	 * <p>
	 * 这个方法从.csv文件中读取电影信息，添加到数据库中
	 * </p>
	 * 
	 * @param fileUrl .csv文件路径
	 * @throws IOException
	 */
	public static void addMovie(String fileUrl) throws IOException {
		int id = 0;// 电影序号
		String name = null;// 电影名称
		String PhotoUrl = null;// 电影图片路径
		String MovieUrl = null;// 电影路径
		String nation = null;// 电影国家
		int typeid = 0;// 电影类型序号
		int yearReleased = 0;// 电影首映时间
		int runTime = 0;// 电影播放时间
		BufferedReader in = null;
		String line;
		try {
			File file = new File(fileUrl);
			in = new BufferedReader(new FileReader(file));
			String[] info = new String[8];
			DatabaseOperation dbop = new DatabaseOperation();
			while ((line = in.readLine()) != null) {
				info = line.split(",");
				id = IntegerConvert(info[0]);
				name = info[1];
				PhotoUrl = info[2];
				MovieUrl = info[3];
				nation = info[4];
				typeid = IntegerConvert(info[5]);
				yearReleased = IntegerConvert(info[6]);
				runTime = IntegerConvert(info[7]);
				dbop.query(
					"insert into films (movieid, movie_name, photo_url, movie_url, country, year_released, runtime) "
					     + "values('" + id + "','" + name + "','" + PhotoUrl + "','" + MovieUrl + "','" + nation
					     + "','" + yearReleased + "','" + runTime + "')");
				dbop.query("insert into type_film (filmid, typeid) " + "values('" + id + "','" + typeid + "')");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				System.out.println("添加电影信息成功");
				System.out.println("关闭文件输入");
				in.close();
			}
		}
	}
	/**
	 * 添加多类型的电影类型
	 * <p>
	 * 这个方法从.csv文件中读取电影信息，添加到数据库中
	 * </p>
	 * @param fileUrl .csv文件路径
	 * @throws IOException
	 */
	public static void addMutiType(String fileUrl) throws IOException {
		int typeid = 0;//电影类型序号
		int filmid = 0;// 电影序号
		BufferedReader in = null;
		String line;
		try {
			File file = new File(fileUrl);
			in = new BufferedReader(new FileReader(file));
			String[] info = new String[2];
			DatabaseOperation dbop = new DatabaseOperation();
			while ((line = in.readLine()) != null) {
				info = line.split(",");
				filmid = IntegerConvert(info[0]);
				typeid = IntegerConvert(info[1]);
				dbop.query("insert into type_film (filmid, typeid) " + "values('" + filmid + "','" + typeid + "')");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				System.out.println("添加电影类型信息成功");
				System.out.println("关闭文件输入");
				in.close();
			}
		}
	}


	/**
	 * 删除电影
	 * <p>
	 * 根据电影序号来删除电影
	 * </p>
	 */
	public static void deleteMovie(int id) {
		DatabaseOperation dbop = new DatabaseOperation();
		dbop.query("delete from type_film where filmid = '" + id + "'");
		dbop.query("delete from films where movieid = '" + id + "'");
	}

	/**
	 * 显示电影信息
	 * <p>
	 * 打印film表显示电影信息
	 * </p>
	 */
	public static void showMoList() {
		DatabaseOperation dbop = new DatabaseOperation();
		dbop.printTable();
	}

	/**
	 * 显示不同类型电影信息
	 * <p>
	 * 根据用户输入的电影类型显示对应类型的所有电影信息
	 * </p>
	 */
	public static void showTypeMoList() {
		DatabaseOperation dbop = new DatabaseOperation();
		System.out.println("请输入电影类型");
		String type = scanner.next();
		dbop.showtype(
			"select movieid, movie_name, country, year_released, runtime from films join type_film tf on movieid "
			+ "= filmid join types t on t.typeid = tf.typeid where t.type = '"
						+ type + "'");
	}


	/**
	 * 获取电影信息
	 * <p>
	 * 返回电影信息二维表的方法
	 * </p>
	 */
	public static String[][] getAllMovieInformation() {
		// TODO Auto-generated method stub
		DatabaseOperation dbop = new DatabaseOperation();
		String[][] movieinfo = dbop.dataToArray();
		return movieinfo;
	}
	/**
	 * 获取不同类型电影信息
	 * <p>
	 * 返回电影信息二维表的方法
	 * </p>
	 */
	public String[][] getTypeMovieInformation(String type){
		DatabaseOperation dbop = new DatabaseOperation();
		String[][] movieinfo = dbop.TypedataToArray(type);
		return movieinfo;
	}

}
