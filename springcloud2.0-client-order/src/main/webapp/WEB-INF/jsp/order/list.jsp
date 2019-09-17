<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<script type="text/javascript" src="../static/js/jquery-1.12.3.min.js"></script>
    <script src="../static/miniui/miniui.js" type="text/javascript"></script>
    <link href="../static/miniui/themes/default/miniui.css" rel="stylesheet" type="text/css" />
    <link href="../static/miniui/themes/icons.css" rel="stylesheet" type="text/css" />

<title>Insert title here</title>
</head>
<body>
<form id="form1" method="post">
      <div style="width:100%;">
        <div class="mini-toolbar" style="border-bottom:0;padding:0px;">
            <table style="width:100%;">
                 <tr>
                    <td style="white-space:nowrap;">                       
                        <input id="orderName" name="orderName" class="mini-textbox" emptyText="请输入订单名称" style="width:150px;" onenter="onKeyEnter" />
                        <a class="mini-button" onclick="search()">查询</a>
                    </td>
                </tr>
                 <tr>
                    <td style="width:100%;">
                        <a class="mini-button" iconCls="icon-add" onclick="add()">增加</a>
                        <a class="mini-button" iconCls="icon-edit" onclick="edit()">编辑</a>
                        <a class="mini-button" iconCls="icon-remove" onclick="remove()">删除</a>
                         <a class="mini-button"  onclick="view()">查看</a>       
                    </td>
                   </tr>
            </table>           
        </div>
    </div>
    <div id="datagrid1" class="mini-datagrid" style="width:100%;height:600px;" allowResize="true"
        url="/order/datagrids"  idField="id" multiSelect="true"  >
        <div property="columns">
            <div type="checkcolumn" width="20px"></div>                   
       			
                <div field="orderName"  headerAlign="center" allowSort="true">订单名称</div>
                <div field="price"  headerAlign="center" allowSort="true">价格</div>
                                             
        </div>
    </div>
  </form>
  
     <script type="text/javascript">
        mini.parse();

        var grid = mini.get("datagrid1");
        grid.load();
      
      /*
      *功能：新增
      */
        function add() {
            
            mini.open({
                url: "/order/edit",
                title: "新增订单", width: 400, height: 300,
                onload: function () {
                    var iframe = this.getIFrameEl();
                    var data = { action: "new"};
                    iframe.contentWindow.SetData(data);
                },
                ondestroy: function (action) {

                    grid.reload();
                }
            });
        }
        
        /**
        *功能：编辑
        */
        function edit() {
            var row = grid.getSelected();
            if (row) {
                mini.open({
                    url: "/order/edit",
                    title: "编辑订单", width: 400, height: 300,
                    onload: function () {
                        var iframe = this.getIFrameEl();
                        var data = { action: "edit", id: row.id };
                        iframe.contentWindow.SetData(data);
                        
                    },
                    ondestroy: function (action) {
                        grid.reload();
                        
                    }
                });
                
            } else {
                alert("请选中一条记录");
            }
            
        }
        
         /**
        *功能：查看
        */
        function view() {
         
            var row = grid.getSelected();
            if (row) {
                mini.open({
                    url: "/order/edit",
                    title: "查看订单", width: 400, height: 300,
                    onload: function () {
                        var iframe = this.getIFrameEl();
                        var data = { action: "view", id: row.id };
                        iframe.contentWindow.SetData(data);
                        
                    },
                    ondestroy: function (action) {
                        grid.reload();
                        
                    }
                });
                
            } else {
                alert("请选中一条记录");
            }
            
        }
        /**
        *功能：删除选中的记录
        */
        function remove() {
            var rows = grid.getSelecteds();
            if (rows.length > 0) {
                if (confirm("确定删除选中记录？")) {
                    var ids = [];
                    for (var i = 0, l = rows.length; i < l; i++) {
                        var r = rows[i];
                        ids.push(r.id);
                    }
                    var id = ids.join(',');
                    grid.loading("操作中，请稍后......");
                    $.ajax({
                    
                        url: "/order/delete",
                        type: 'post',
              			data: { id: id },
               			 cache: false,
                        success: function (text) {
                        	alert("成功删除!")
                            grid.reload();
                        },
                        error: function (text) {
                        	alert("删除失败!");
                        }
                    });
                }
            } else {
                alert("请选中一条记录");
            }
        }
        
        function search() {
              var form = new mini.Form("form1");
                  var o = form.getData(); 
              grid.load(o);
        }
        function onKeyEnter(e) {
            search();
        }
        /////////////////////////////////////////////////
       




    </script>  

</body>
</html>