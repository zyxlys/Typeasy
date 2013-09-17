package me.llss.utils;

import java.text.SimpleDateFormat;

import me.llss.service.impl.OptionsServiceImpl;

/**
 * 更改时间显示的格式
 * 
 * @author Administrator
 * 
 */
public class SwitchDateFormat {
	/**
	 * 格式化时间
	 * 
	 * @param created
	 * @return
	 */
	public static String SwitchFormat(String created) {
		OptionsServiceImpl os = new OptionsServiceImpl();
		String d = os.findByName("date").getValue();
		String t = os.findByName("time").getValue();
		String dt = d + " " + t;
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf2 = new SimpleDateFormat(dt);

		String time2 = "";
		try {
			time2 = sdf2.format(sdf1.parse(created));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return time2;
	}
}
