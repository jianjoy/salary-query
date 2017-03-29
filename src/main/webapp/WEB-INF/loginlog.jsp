<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="head.jsp" />
<div id="content" class="span10">
	<!-- content starts -->



	<div style="text-align: center;">
		<div id="jtcontainer" style="width:60%"></div>
	</div>


	<!-- content ends -->
</div>
<!--/#content.span10-->
</div>
<!--/fluid-row-->


<jsp:include page="foot.jsp"></jsp:include>
<script type="text/javascript">
	$(document).ready(function() {
		$('#jtcontainer').jtable({
			title : '登录日志列表',
			paging : true, //Enable paging
			pageSize : 20, //Set page size (default: 10)           
			actions : {
				listAction : 'front/api/queryLog.do',
			},
			fields : {
				id : {
					key : true,
					title : 'ID',
					width : '2%'
				},
				uname:{
				   title: 'uname',
				   width:'6%'
				}
				,
				loginIp : {
					title : 'loginIp',
					width : '10%'
				},
				loginTime : {
					title : 'loginTime',
					width : '10%'
				}
			}
		});
		$('#jtcontainer').jtable('load');
	});
</script>