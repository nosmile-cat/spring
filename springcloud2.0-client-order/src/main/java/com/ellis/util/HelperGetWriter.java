package com.ellis.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

public class HelperGetWriter {
	
	public  static void toJson(Object data,HttpServletResponse res){
		res.setCharacterEncoding("utf-8");
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out;
		try {
			out = res.getWriter();
			out.write(JSON.toJSONString(data));
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
