
/*$("#login").click(function() {
		
		var username = $('#username').val();
		var password = $('#password').val();
		var strData={"username":username,"password":password};
		
		$.ajax({
			type : 'POST',
			url : '/kaizen/validate',
			contentType : 'application/json',
			dataType : 'json',
			data:JSON.stringify(strData),
			success : function(response) {
				 //alert("Response: " + response);
				 parent.document.getElementById("mustFrame").src="http://localhost:8080/home/"+username;
				    //$("#mustFrame",parent.document).load("http://localhost:8080/home/"+username);
			
				//alert("you are logged in!");
				//location.href = "http://localhost:8080/home/"+username;
			},
			error:function(response)
			{
				$.get("http://localhost:8080/home/"+username,function(data,status){
				    alert("Data: " + data + "\nStatus: " + status);
				    $(body).html(response);
				  });
				
				}
		});
		
	});
	*/

var userid = "parul";
$("#login").submit(function(event){
	//alert("Form Submitted");
	//window.location.reload();
	event.preventDefault();
	var $form = $( this );
	var userName = $form.find( "input[name='username']" ).val();
	userid = userName;
	var formdata = $form.serialize();
	$.ajax({
		  type: 'POST',
		  data:formdata,
		  url: '/kaizen/authenticate/',
		  success: function() {
			    window.location = '/kaizen/'+userName+'/home';
			  },
			error:function() {
				alert("Invalid Username or Password");
				/*resetFields();*/
			}
	});
});

$("#register").submit(function(event){
	alert("Form Submitted");
	
	//window.location.reload();
	event.preventDefault();
	var $form = $( this );
	var formdata = $form.serialize();
	$.ajax({
		  type: 'POST',
		  data:formdata,
		  url: '/kaizen/register/',
		  success: function() {
			    window.location = '/kaizen/';
			  },
			error:function() {
				alert("Registration failed");
			}
	});
});

$("#searchBiz").submit(function(event){
	//window.location.reload();
	var pathArray=window.location.pathname.split('/');
	var user=pathArray[2];
	event.preventDefault();
	
	var $form = $( this );
	var formdata = $form.serialize();
	$.ajax({
		  type: 'POST',
		  data:formdata,
		  url: '/kaizen/'+user+'/home/search',
		  success: function() {
			  	window.location = '/kaizen/'+user+'/home';
			  	
			  },
			error:function() {
				alert("Search failed");
			}
	});
});

$("#saveReview").submit(function(event){
	//window.location.reload();
	var pathArray=window.location.pathname.split('/');
	var user=pathArray[2];
	var business_id = pathArray[4];
	event.preventDefault();
	var $form = $( this );
	var formdata = $form.serialize();
	$.ajax({
		  type: 'POST',
		  data:formdata,
		  url: '/kaizen/'+user+'/review/'+business_id,
		  success: function() {
			  	alert("Review saved successfully!!");
			  	window.location = '/kaizen/'+user+'/home';
			  },
			error:function() {
				/*alert("Failed to submit.");*/
			}
	});
});
		
/*function resetFields(){
	$("#login").find( "input[name='username']" ).val()= '';
	document.getElementById('password').value = '';
}*/