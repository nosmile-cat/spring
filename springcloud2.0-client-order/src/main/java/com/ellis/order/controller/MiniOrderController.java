package com.ellis.order.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bouncycastle.jcajce.provider.asymmetric.rsa.DigestSignatureSpi.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.ellis.order.entity.Order;
import com.ellis.order.service.OrderService;
import com.ellis.util.HelperGetWriter;

@Controller
@RequestMapping("/order")
public class MiniOrderController {
	@Autowired
	private OrderService orderService;
	
	@RequestMapping("/toListPage")
	public String toOrderPage(){
		
		return "/order/list";
	}
	
	@RequestMapping("/datagrids")
	public String findOrderLists(HttpServletRequest req,HttpServletResponse res){
		Map<String, Object> params = new HashMap<String, Object>();
		
        String orderName= req.getParameter("orderName");
        if (!StringUtils.isEmpty(orderName)) {
            params.put("orderName",orderName);
        }
		//mysql
        int pageindex= Integer.valueOf(req.getParameter("pageIndex"));
		int pageSize= Integer.valueOf(req.getParameter("pageSize"));
		int start = pageindex * pageSize;
		String sortField= req.getParameter("sortField");
		if(!StringUtils.isEmpty(sortField)){
			String sortOrder= req.getParameter("sortOrder");
			if(StringUtils.isEmpty(sortOrder)){
				params.put("sortField", sortField + " desc");
			}else{
				params.put("sortField", sortField +" "+ sortOrder);
			}
		}
		Map<String, Object> result = orderService.findListPage(params, start, pageSize);
		HelperGetWriter.toJson(result, res);
		return null;
	}
	
	@RequestMapping("/edit")
	public String toEditPage(){
		
		return "/order/edit";
	}
	
	@RequestMapping("/save")
	public String saveOrder(HttpServletRequest req,HttpServletResponse res){
		String data=req.getParameter("data");
		data=data.replace("[", "");
		data=data.replace("]", "");
		Order order = JSON.parseObject(data,Order.class);
		//List<Order> parseArray = JSON.parseArray(data,Order.class);
		//Order order = parseArray.get(0);
		if(StringUtils.isEmpty(order.getId())){
			order.setId(UUID.randomUUID().toString().replace("-", ""));
			orderService.addOrder(order);
		}else{
			orderService.updateOrder(order);
		}		
		HelperGetWriter.toJson("success", res);
		return null;
	}
	
	@RequestMapping("/get")
	public String getOrder(HttpServletRequest req,HttpServletResponse res){
		String orderId=req.getParameter("id");
		Order order = orderService.getOrderByID(orderId);
		HelperGetWriter.toJson(order, res);
		return null;
	}
	@RequestMapping("/delete")
	public String delele(HttpServletRequest req,HttpServletResponse res){
		String orderId=req.getParameter("id");
		orderService.deleteById(orderId);
		HelperGetWriter.toJson("success", res);
		return null;
	}
	
}
