<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="head.jsp" />
<div id="content" class="span10">
	<!-- content starts -->




    <div class="row-fluid sortable">
				<div class="box span12">
					<div class="box-header well" data-original-title>
						<h2><i class="icon-edit"></i>日志检索</h2>
						<div class="box-icon">
							<a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a>
							<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
							<a href="#" class="btn btn-close btn-round"><i class="icon-remove"></i></a>
						</div>
					</div>
					<div class="box-content">
						<form class="form-horizontal">
							<fieldset>
							  <div class="control-group">
								<label class="control-label" for="prependedInput">登录名</label>
								<div class="controls">
								  <div class="input-prepend">
									<input id="prependedInput" name="uname" size="16" type="text">
								  </div>
								</div>
							  </div>
							  <div class="control-group">
								<label class="control-label" for="appendedInput">登录ip</label>
								<div class="controls">
								  <div class="input-append">
									<input id="prependedInput" size="16" type="text" name="ip">
								  </div>
								</div>
							  </div>
							  <div class="control-group">
							  <label class="control-label" for="date01">开始时间</label>
							  <div class="controls">
								<input type="text" class="input-xlarge datepicker" id="date01" name="startTime">
							  </div>
							</div>
							
						     <div class="control-group">
							  <label class="control-label" for="date01">结束时间时间</label>
							  <div class="controls">
								<input type="text" class="input-xlarge datepicker" id="date02" name="endTime">
							  </div>
							</div>
							
							  <div class="form-actions">
								<button type="button" class="btn btn-primary" onclick="search()">检索</button>
								<button class="btn" type="reset">重置</button>
							  </div>
							</fieldset>
						</form>
						
							<div style="text-align: center;">
	                       	<div id="jtcontainer" style="width:80%"></div>
	                        </div>
					</div>
				</div><!--/span-->

			</div><!--/row-->








	<!-- content ends -->
</div>
<!--/#content.span10-->
</div>
<!--/fluid-row-->


<jsp:include page="foot.jsp"></jsp:include>
<script type="text/javascript">
    var uname ='';
	$(document).ready(function() {
		$('#jtcontainer').jtable({
			title : '登录日志列表',
			paging : true, //Enable paging
			pageSize : 10, //Set page size (default: 10)           
			actions : {
				listAction : 'front/api/queryLog.do'
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
		$("#date01").datepicker( "option", "dateFormat","yy-mm-dd");
		$("#date02").datepicker( "option", "dateFormat","yy-mm-dd");
	});
	function search(){
		$('#jtcontainer').jtable('load',
				{uname:$("input[name='uname']").val(),
			        ip:$("input[name='ip']").val(),
			        startTime:$("input[name='startTime']").val(),
			        endTime:$("input[name='endTime']").val()
			        
				}
		);
	}
	
</script>