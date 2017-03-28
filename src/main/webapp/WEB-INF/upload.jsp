<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="head.jsp" />
<div id="content" class="span10">
	<!-- content starts -->



	<div class="control-group">
		<label class="control-label" for="fileInput">File input</label>
		<div class="controls">
		     <form method="post" action="front/api/upload.do" enctype="multipart/form-data">
			<input class="input-file uniform_on" id="fileInput" name="file" type="file">
			&nbsp;&nbsp;&nbsp;<input type="submit" value="上传">
			</form>
		</div>
	</div>


	<!-- content ends -->
</div>
<!--/#content.span10-->
</div>
<!--/fluid-row-->


<jsp:include page="foot.jsp"></jsp:include>
