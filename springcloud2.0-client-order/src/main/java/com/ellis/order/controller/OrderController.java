package com.ellis.order.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.ellis.order.entity.Order;
import com.ellis.order.service.OrderService;

@Controller
@RefreshScope//动态更新
public class OrderController {
	@Value("${myname}")
	private String myname;
	@Value("${age}")
	private String age;
	
	@Autowired
	private OrderService orderService;
	//测试配置中心参数
	@RequestMapping("/order")
	public String order(ModelMap model){
		model.addAttribute("name", myname);
		model.addAttribute("age", age);
		return "orderIndex";
	}
	
	@RequestMapping("/findOrders")
	@ResponseBody
	public List<Order> findOrder(){
		List<Order> findList = orderService.findList();
		return findList;
	}
	
	/**
	 * 列表
	 * @param model
	 * @return
	 */
	@RequestMapping("/orderList")
	public String orderList(ModelMap model){
		List<Order> findList = orderService.findList();
		model.addAttribute("list", findList);
		return "listOrder";
	}
	
	/**
	 * 跳转到页面
	 * @return
	 */
	@RequestMapping("/toAddOrder")
	public String toAddOrder(){
		return "formOrder";
	}
	
	/**
	 * 保存与更新
	 * @param order
	 * @return
	 */
	@RequestMapping("/addOrder")
	public String addOrder(Order order,HttpServletResponse res){
		//Order order = new Order("02","牛仔裤","56");
		if("".equals(order.getId())){
			order.setId(UUID.randomUUID().toString().replace("-", ""));
			orderService.addOrder(order);
		}else{
			orderService.updateOrder(order);
		}
		res.setCharacterEncoding("utf-8");
		res.setContentType("text/html; charset=utf-8");
		PrintWriter writer;
		try {
			writer = res.getWriter();
			writer.write("<script type='text/javascript'>alert('保存成功！');window.location.href = '/orderList'</script>");
//			Map<String, Object> map = new HashMap<String,Object>();
//			map.put("code", "success");
//			map.put("msg", "保存成功！");
//			writer.print(JSON.toJSONString(map));
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//return "redirect:/orderList";//不能跟writer.write('xxxx')一起用
		return null;
	}
	
	/**
	 * 编辑
	 * @param id
	 * @param map
	 * @return
	 */
	@RequestMapping("/editOrder")
	public String editOrder(@RequestParam("id")String id,ModelMap model,HttpServletResponse res){
		Order order = orderService.getOrderByID(id);
		model.addAttribute("order", order);
		return "formOrder";
	}
	/**
	 * 删除
	 * @param id
	 * @param res
	 * @return
	 */
	@RequestMapping("/deleteById")
	public String deleteById(@RequestParam("id")String id,HttpServletResponse res){
		try {
			int result = orderService.deleteById(id);
			res.setCharacterEncoding("utf-8");
			res.setContentType("text/html; charset=utf-8");
			PrintWriter out = res.getWriter();
			String info = "";
			if(result>0){
				info = "删除成功!";
			}else{
				info = "删除失败！";
			}
			out.write("<script language='javascript'>alert('"+info+"')</script>");
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
