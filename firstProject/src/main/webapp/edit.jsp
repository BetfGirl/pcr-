<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html >
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="https://www.layuicdn.com/layui/css/layui.css" />
<script type="text/javascript" src="lib/layui/layui.all.js"></script>
<script src="js/jquery-2.2.4.min.js" ></script>
<script type="text/javascript" src="js/my.js"></script>
<title></title>
</head>
<body>
<style>
.layui-input{width:200px;}
</style>

<c:if test="${param.id==null}">
<form class="layui-form" lay-filter="myform" action="Book/insert.action">
</c:if>
<c:if test="${param.id!=null}">
<form class="layui-form" lay-filter="myform" action="Book/update.action">
<input type="hidden" name="id" >
</c:if>
  <div class="layui-form-item">
    <label class="layui-form-label">名称</label>
    <div class="layui-input-block">
      <input type="text" name="name" autocomplete="off" class="layui-input">
    </div>
  </div>
   <div class="layui-form-item">
    <label class="layui-form-label">性别</label>
    <div class="layui-input-block">
      <select name="sex" >
      </select>
    </div>
  </div>
   <div class="layui-form-item">
    <label class="layui-form-label">类型</label>
    <div class="layui-input-block">
      <select name="typeid" >
      </select>
    </div>
    
  </div>
  
   <div class="layui-form-item">
    <div class="layui-input-block">
      <button class="layui-btn" lay-submit="" lay-filter="demo1">保存</button>
    </div>
  </div>
</form>

<script type="text/javascript">
layui.use(['form',], function(){
	  var form = layui.form;
	  form.on('submit(demo1)', function(data){
		 $.post($("form").attr("action"), data.field, function(json) {
			  closeFrame();
			  parent.fresh('demo');
			}, "json");
		    
		    return false;
		  });
});



var id="${param.id}";
function init(){
	$.post("Book/edit.action",{cmd:"edit",id:id}, function(json) {
		render('myform', json);
		getarray("Book/sexs.action",{cmd:"getSexs"},"[name=sex]",json.sex);
		getlist("Book/type.action",{cmd:"getTypes"},"[name=typeid]",json.typeid);
	},"json");
	
}
if(id.length>0){
	init();
}else{
	getarray("Book/sexs.action",{cmd:"getSexs"},"[name=sex]",1);
	getlist("Book/type.action",{cmd:"getTypes"},"[name=typeid]",1);
}

</script>
</body>
</html>