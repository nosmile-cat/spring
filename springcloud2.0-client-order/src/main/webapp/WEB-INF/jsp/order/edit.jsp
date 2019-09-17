<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<title>Insert title here</title>
	<script type="text/javascript" src="../static/js/jquery-1.12.3.min.js"></script>
    <script src="../static/miniui/miniui.js" type="text/javascript"></script>
    <link href="../static/miniui/themes/default/miniui.css" rel="stylesheet" type="text/css" />
    <link href="../static/miniui/themes/icons.css" rel="stylesheet" type="text/css" />
</head>
<body>
     
    <form id="form1" method="post">
        <div style="padding-left:11px;padding-bottom:5px;">            
	    	<input name="id" id="id" class="mini-hidden" required="false"/>
        	<table style="table-layout:fixed;" class="table2">
                                           
                   <tr>
                    <td class="tdtitle">订单名称：</td>
                    <td class="tdcontent">                             
                           <input name="orderName" id="orderName" class="mini-textbox"  required="false"  emptyText="请输入订单名称"  vtype="maxLength:64"/>
                                                   
                    </td>
                    
                </tr>                             
                   <tr>
                    <td class="tdtitle">价格：</td>
                    <td class="tdcontent">                             
                           <input name="price" id="price" class="mini-textbox"  required="false"  emptyText="请输入价格"   vtype="maxLength:64"/>￥
                                                   
                    </td>
                    
                </tr>                     
            </table>
        </div>        
        <div id="btnControl" style="text-align:center;padding:10px;">               
            <a id="submit" class="mini-button" onclick="onOk" style="width:60px;margin-right:20px;">确定</a>       
            <a class="mini-button" onclick="onCancel" style="width:60px;">取消</a>       
        </div>        
    </form>
    <script type="text/javascript">
        mini.parse();

        var form = new mini.Form("form1");        function SaveData() {
            var o = form.getData();            

            form.validate();
            if (form.isValid() == false) return;
            var json = mini.encode(o);
            $.ajax({
                url: "/order/save",
				type: 'post',
                data: { 
                  data: json                 },
                cache: false,
                success: function (text) {
                    CloseWindow("save");
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.responseText);
                    CloseWindow();
                }
            });
        }

        ////////////////////
        //标准方法接口定义
        function SetData(data) {
        	if (data.action == "edit" || data.action == "view") {
                //跨页面传递的数据对象，克隆后才可以安全使用
                data = mini.clone(data);
                $.ajax({
                    url: "/order/get",
                    type:"post",
                    data:{
                    	id:data.id
                    },
                    cache: false,
                    success: function (text) {
                        var o = mini.decode(text);
                     
                        form.setData(o);
                        form.setChanged(false);
                        if(data.action=="view"){
							var fields = form.getFields();   
                            //$("#btnControl").hide();             
                            $("#submit").hide();             
				            for (var i = 0, l = fields.length; i < l; i++) {
				                var c = fields[i];
				                if (c.setReadOnly) c.setReadOnly(true);     //只读
				                if (c.setIsValid) c.setIsValid(true);      //去除错误提示
				                if (c.addCls) c.addCls("asLabel");          //增加asLabel外观
                            
				            }
						}
                    }
                });            
            }
        }

        function GetData() {
            var o = form.getData();
            return o;
        }
        function CloseWindow(action) {            
            if (action == "close" && form.isChanged()) {
                if (confirm("数据被修改了，是否先保存？")) {
                    return false;
                }
            }
            if (window.CloseOwnerWindow) return window.CloseOwnerWindow(action);
            else window.close();            
        }
        function onOk(e) {
            SaveData();
        }
        function onCancel(e) {
            CloseWindow("cancel");
        }
       
       

   	//唯一性校验
		function onUniqueValidate(e){
			 if (e.isValid) {
				 	$.ajax({
				 		url:"/order/validateUnique",
				 		type:"post",
				 		data: {
				 			data : e.sender.name+"~"+e.value,
                           id:mini.get("id").value
						},
				 		cache:false,
				 		success: function(text) {
							if(text==true){
								 alert( "该____已经存在，请重新输入");
								$(e.sender).focus();
							}
							
						},
						error: function(jqXHR, textStatus, errorThrown) {
							alert("校验___唯一性失败!");
				 			}
				 	}
				 	);	
			 }
		}
 
    </script>

</body>
</html>