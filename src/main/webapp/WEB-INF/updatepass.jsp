<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="head.jsp" />
<div id="content" class="span10">
	<!-- content starts -->


 <div class="row-fluid sortable">
				<div class="box span12">
					<div class="box-header well" data-original-title>
						<h2><i class="icon-edit"></i>修改账户密码</h2>
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
						<label class="control-label" for="prependedInput">原密码</label>
						<div class="controls">
							<div class="input-prepend">
								<input id="prependedInput" name="oldPass" size="16"
									type="password">
							</div>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="appendedInput">新密码</label>
						<div class="controls">
							<div class="input-prepend">
								<input id="prependedInput" size="16" type="password"
									name="newPass">
							</div>
						</div>
					</div>

					<div class="control-group">
						<label class="control-label" for="appendedInput">确认新密码</label>
						<div class="controls">
							<div class="input-prepend">
								<input id="prependedInput" size="16" type="password"
									name="newPassConfirm">
							</div>
						</div>
					</div>
					<div class="form-actions">
						<button type="button" class="btn btn-primary"
							onclick="updatePass()">修改</button>
						<button class="btn" type="reset">重置</button>
					</div>
				</fieldset>
			</form>
					</div>
				</div><!--/span-->

			</div><!--/row-->

	<!-- content ends -->
</div>
<!--/#content.span10-->
</div>
<!--/fluid-row-->


<jsp:include page="foot.jsp"></jsp:include>
<script>
	function resetForm() {
		$('input[name="oldPass"]').val('');
		$('input[name="newPass"]').val('');
		$('input[name="newPassConfirm"]').val('');
	}
	function updatePass() {
		var oldPass = $('input[name="oldPass"]').val();
		var newPass = $('input[name="newPass"]').val();
		var newConfirmPass = $('input[name="newPassConfirm"]').val();
		if(oldPass.trim().length==0||newPass.trim().length==0||newConfirmPass.trim().length==0){
			alert('密码不能为空!');
			return;
		}
		if(newPass.trim()!=newConfirmPass.trim()){
			alert('修改密码和确认修改密码不符!');
			return;
		}
		
		jQuery.post('front/api/updatePass.do', 'oldPass=' + oldPass
				+ '&newPass=' + newPass, function(data) {
			if (data != null) {
				if (data.status != 'success') {
					resetForm();
					alert(data.message);
				} else {
					resetForm();
					alert('修改成功');
				}
			} else {
				resetForm();
				alert('修改失败！');
			}
		}, 'json');

	}
</script>