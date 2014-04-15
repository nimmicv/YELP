$(document).ready(function(){
	alert("Hello")
	$("#login").click(function() {
		var username = $('#username').val();

	var password = $('#password').val();
	alert(username);
	$.ajax({		
	    	type:'GET',
	    	url:'http://localhost:8080/kaizen/validate',
	    	contentType: 'application/json',
			dataType: 'json',
			data:{
				"username":username,
				"password":password
				
			},
			success:function(response){
				alert(response);
			}
	    	
	    	
	    });
	});	
	});