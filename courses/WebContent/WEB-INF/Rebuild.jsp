<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
  
<div class="toolbg">
<c:if test="${level eq 3 }">
	
	&nbsp;<button class="btn btn-default btn-xs pull-left" onclick="apply();">申请重修</button>
</c:if>
</div>

<table class="table">
	<tr>
		<th>#</th>
		<th>重修人</th>
		<th>申请时间</th>
		<th>申请结果</th>
		<th>操作</th>
	</tr>
	<c:forEach items="${list }" var="i">
	<tr>
		<td>${i.id }</td>
		<td>${i.create_user }</td>
		<td> <fmt:formatDate value="${i.create_time }" pattern="yyyy-MM-dd HH:mm"/> </td>
		<td>${i.result }</td>
		<td>
			<c:if test="${level eq 1 }">
				<button class="btn btn-default btn-xs" onclick="process('同意申请',${i.id})">同意</button>
				<button class="btn btn-default btn-xs" onclick="process('不同意申请',${i.id})">不同意</button>
			</c:if>
		</td>
	</tr>
	</c:forEach>
</table>

<div class="toolbg"></div>
<script>
function apply(){
	if(confirm('确认申请重修？')){
		$.get('/rebuild/apply',function(data){
			location.reload();
		});
	}
}
function process(msg,id){
	if(confirm('确认处理？')){
		$.post('/rebuild/process',{
			result:msg,
			id:id
		},function(data){
			location.reload();
		});
	}
}
</script>