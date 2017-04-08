<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="head.jsp" />
<div id="content" class="span10">
	<!-- content starts -->
  <div class="row-fluid sortable">
				<div class="box span12">
					<div class="box-header well" data-original-title>
						<h2><i class="icon-edit"></i>工资信息检索</h2>
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
							  <label class="control-label" for="date01">开始时间</label>
							  <div class="controls">
								<input type="text" class="input-xlarge datepicker" id="date01" name="beginDate">
							  </div>
							</div>
							
						     <div class="control-group">
							  <label class="control-label" for="date01">结束时间时间</label>
							  <div class="controls">
								<input type="text" class="input-xlarge datepicker" id="date02" name="endDate">
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
	$(document).ready(function() {
		$('#jtcontainer').jtable({
			title : '工资信息列表',
			paging : true, //Enable paging
			pageSize : 20, //Set page size (default: 10)           
			actions : {
				listAction : 'front/api/getSalaryList.do',
			},
			fields : {
				id : {
					key : true,
					title : 'ID',
					width : '2%'
				},
				salary:{
				   title: '工资金额',
				   width:'10%'
				}
				,
				bonus:{
				   title: '奖金',
				   width:'10%'
				},
				absentSalaryDeduction:{
				   title: '事假金额',
				   width:'10%'
				},
				sickLeaveSalaryDeduction:{
				   title: '病假金额',
				   width:'10%'
				},
				personPensionPayment:{
				   title: '个人养老金',
				   width:'10%'
				},
				personMedicalInsurancePayment:{
				   title: '个人医保基金',
				   width:'10%'
				},
				personalIncomeTax:{
				   title: '个人所得税',
				   width:'10%'
				},
				personalIncomeTax:{
				   title: '个人所得税',
				   width:'10%'
				},
				personalProvidentFund:{
				   title: '个人公积金',
				   width:'10%'
				},
				realSalary:{
				   title: '实发工资金额',
				   width:'10%'
				},
				salaryDate:{
				   title:'工资月份',
				   width:'10%'
				}
				
				
			}
		});
		$("#date01").datepicker("option", "dateFormat","yy-mm");
		$("#date02").datepicker( "option", "dateFormat","yy-mm");
		$('#jtcontainer').jtable('load');
		
	});
	
		function search(){
		$('#jtcontainer').jtable('load',
				{
			        beginDate:$("input[name='beginDate']").val(),
			        endDate:$("input[name='endDate']").val()
			        
				}
		);
	}
	
</script>