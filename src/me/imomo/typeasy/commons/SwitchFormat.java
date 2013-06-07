package me.imomo.typeasy.commons;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import me.imomo.typeasy.service.OptionsService;

/**
 * 更改时间显示的格式
 * @author Administrator
 *
 */
public class SwitchFormat {
	/**
	 * 格式化时间
	 * @param created
	 * @return
	 */
	public static String SwitchFormat(String created){
		OptionsService os = new OptionsService();
		String d = os.findByName("date").getValue();
		String t = os.findByName("time").getValue();
		String dt = d+" "+t;
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		SimpleDateFormat sdf2 = new SimpleDateFormat(dt);

		String time2 = "";
		try{
		    time2 = sdf2.format(sdf1.parse(created));  
		}catch(Exception e){
		    e.printStackTrace();
		}
		 
		return time2;
	}
}
