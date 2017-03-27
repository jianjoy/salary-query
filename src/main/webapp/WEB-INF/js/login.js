function login(){
	jQuery.post('front/api/login.do','user='+$('#username').val()+'&pass='+$('#password').val(),function(data){
		if(data!=null){
			if(data.status!='success'){
				clearInput();
				alert(data.message);
			}else{
				window.location='front/api/index.do';
			}
		}else{
			clearInput();
			alert('用户名或密码错误，请重新输入！');
		}
	
		
	},'json');
}

function clearInput(){
	$('#username').val('');
	$('#password').val('');
}