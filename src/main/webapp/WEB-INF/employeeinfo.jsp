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
								<label class="control-label" for="prependedInput">部门</label>
								<div class="controls">
								  <div class="input-prepend">
									<input id="prependedInput" name="department" size="16" type="text">
								  </div>
								</div>
							  </div>
							  <div class="control-group">
								<label class="control-label" for="appendedInput">姓名</label>
								<div class="controls">
								  <div class="input-append">
									<input id="appendedInput" size="16" type="text" name="name">
								  </div>
								</div>
							  </div>
							  
							   <div class="control-group">
								<label class="control-label" for="appendedInput">邮箱</label>
								<div class="controls">
								  <div class="input-append">
									<input id="appendedInput" size="16" type="text" name="email">
								  </div>
								</div>
							  </div>
				
							
							  <div class="form-actions">
								<button type="button" class="btn btn-primary" onclick="search()">检索</button>
								<button class="btn" type="reset">重置</button>
							  </div>
							</fieldset>
						</form>
						
							<div style="text-align: center;">
	                       	<div id="jtcontainer" style="width:60%"></div>
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
			title : '员工信息列表',
			paging : true, //Enable paging
			pageSize : 20, //Set page size (default: 10)           
			actions : {
				listAction : 'front/api/getEmployeeInfos.do'
			},
			fields : {
				id : {
					key : true,
					title : 'ID',
					width : '2%'
				},
				name:{
				   title: '名字',
				   width:'6%'
				},
				sex : {
					title : '性别',
					width : '10%'
				}
				,
				department : {
					title : '部门',
					width : '10%'
				},
				identityNo : {
					title : '员工编号',
					width : '10%'
				},
				email : {
					title : '邮箱',
					width : '10%'
				},
				regDate : {
					title : '注册日期',
					width : '10%'
				}
				
			}
		});
		$('#jtcontainer').jtable('load');
	});
	function search(){
		$('#jtcontainer').jtable('load',
				{department:$("input[name='department']").val(),
		         	name:$("input[name='name']").val(),
		         	email:$("input[name='email']").val()
			        
				}
		);
	}
	
</script>