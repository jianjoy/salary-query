<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="head.jsp" />
<div id="content" class="span10">
	<!-- content starts -->




	<div class="row-fluid sortable">
		<div class="box span12">
			<div class="box-header well" data-original-title>
				<h2>
					<i class="icon-edit"></i>系统账号信息
				</h2>
				<div class="box-icon">
					<a href="#" class="btn btn-setting btn-round"><i
						class="icon-cog"></i></a> <a href="#"
						class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
					<a href="#" class="btn btn-close btn-round"><i
						class="icon-remove"></i></a>
				</div>
			</div>
			<div class="box-content">
				<div style="text-align: center;">
					<div id="jtcontainer" style="width: 80%"></div>
				</div>
			</div>
		</div>
		<!--/span-->

	</div>
	<!--/row-->






	<div class="row-fluid sortable">
		<div class="box span12">
			<div class="box-header well" data-original-title>
				<h2>
					<i class="icon-edit"></i>添加账号
				</h2>
				<div class="box-icon">
					<a href="#" class="btn btn-setting btn-round"><i
						class="icon-cog"></i></a> <a href="#"
						class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
					<a href="#" class="btn btn-close btn-round"><i
						class="icon-remove"></i></a>
				</div>
			</div>
			<div class="box-content">
				<form class="form-horizontal" action="front/api/addAccount.do"
					method="post" onsubmit="return addAccount()">
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
							<label class="control-label" for="prependedInput">密码</label>
							<div class="controls">
								<div class="input-prepend">
									<input id="prependedInput" name="pass" size="16" type="text">
								</div>
							</div>
						</div>

						<div class="control-group">
							<label class="control-label" for="appendedInput">员工ID</label>
							<div class="controls">
								<div class="input-append">
									<input id="appendedInput" size="16" type="text" name="empId">
								</div>
							</div>
						</div>

						<div class="control-group">
							<label class="control-label" for="selectError3">账户类型</label>
							<div class="controls">
								<select id="selectError3" name="accountRoleType">
									<option value="2">员工</option>
									<option value="3">财务</option>
									<option value="1">管理员</option>
								</select>
							</div>
						</div>

						<div class="form-actions">
							<button type="submit" class="btn btn-primary" >添加</button>
							<button class="btn" type="reset">重置</button>
						</div>

					</fieldset>
				</form>
			</div>

		</div>
		<!--/span-->

	</div>
	<!--/row-->
	<!-- content ends -->
</div>
<!--/#content.span10-->
</div>
<!--/fluid-row-->


<jsp:include page="foot.jsp"></jsp:include>
<script type="text/javascript">
	$(document).ready(function() {
		$('#jtcontainer').jtable({
			title : '账号列表',
			paging : true, //Enable paging
			pageSize : 10, //Set page size (default: 10)           
			actions : {
				listAction : 'front/api/getAccountList.do',
				deleteAction:'front/api/deleteAccount.do'
			},
			fields : {
				id : {
					key : true,
					title : 'ID',
					width : '2%',
					delete:true
				},
				uname : {
					title : '登录名',
					width : '6%'
				},
				roleType : {
					title : '系统角色',
					width : '5%'
				},
				status : {
					title : '账号状态',
					width : '5%'
				},
				name : {
					title : '姓名',
					width : '5%'
				},
				sex : {
					title : '性别',
					width : '3%'
				},
				department : {
					title : '部门',
					width : '6%'
				},
				identityNo : {
					title : '员工编号',
					width : '10%'
				},
				email : {
					title : '邮箱',
					width : '10%',
				}
			}
		});
		$('#jtcontainer').jtable('load');
	});
	
	function addAccount(){
		var uname = $('input[name="uname"]').val();
		var pass = $('input[name="pass"]').val();
		var empId = $('input[name="empId"]').val();
		if(uname.trim().length==0||pass.trim().length==0||empId.trim().length()){
			alert('账户信息不能为空!');
			return false;
		}
		return true;
	}
	
</script>