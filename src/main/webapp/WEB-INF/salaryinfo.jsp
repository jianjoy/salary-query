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
				   width:'3%'
				}
				,
				bonus:{
				   title: '奖金',
				   width:'3%'
				},
				absentSalaryDeduction:{
				   title: '事假金额',
				   width:'3%'
				},
				sickLeaveSalaryDeduction:{
				   title: '病假金额',
				   width:'3%'
				},
				personPensionPayment:{
				   title: '个人养老金',
				   width:'3%'
				},
				personMedicalInsurancePayment:{
				   title: '个人医保基金',
				   width:'3%'
				},
				personalIncomeTax:{
				   title: '个人所得税',
				   width:'3%'
				},
				personalIncomeTax:{
				   title: '个人所得税',
				   width:'3%'
				},
				personalProvidentFund:{
				   title: '个人公积金',
				   width:'3%'
				},
				realSalary:{
				   title: '实发工资金额',
				   width:'3%'
				},
				salaryDate:{
				   title:'工资月份',
				   width:'3%'
				}
				
				
			}
		});
		$('#jtcontainer').jtable('load');
	});
</script>