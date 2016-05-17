<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
  
<div class="toolbg">
<c:if test="${level eq 2 }">
<form action="/files/upload" enctype="multipart/form-data" method="post">
	
	<input type="text" name="name" class="pull-left" class="form-control input-sm" required="required" placeholder="在这里输入文件标题" style="line-height: normal;">
	<input type="file" name="file" class="pull-left">
	<input type="hidden" name="type" value="file">
	<button class="btn btn-default btn-xs pull-left">上传资料</button>
</form>
</c:if>
</div>

<table class="table">
	<tr>
		<th>#</th>
		<th>文件名</th>
		<th>上传时间</th>
		<th>上传者</th>
		<th>下载</th>
	</tr>
	<c:forEach items="${list }" var="i">
	<tr>
		<td>${i.id }</td>
		<td>${i.name }</td>
		<td> <fmt:formatDate value="${i.create_time }" pattern="yyyy-MM-dd HH:mm"/> </td>
		<td>${i.create_user }</td>
		<td><a href="/files/download/${i.id }">下载</a></td>
	</tr>
	</c:forEach>
</table>

<div class="toolbg"></div>