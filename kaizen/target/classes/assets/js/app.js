	$("#login").click(function() {
		var username = $('#username').val();
		var password = $('#password').val();
		alert(username);
		var strData={"username":username,"password":password};
		
		$.ajax({
			type : 'POST',
			url : '/kaizen/validate',
			contentType : 'application/json',
			dataType : 'json',
			data:JSON.stringify(strData),
			success : function(response) {
				alert("you are logged in!");
				location.href = "/com/kaizen/yelp/ui/views/home.mustache";
			},
			error:function(response)
			{
				alert("error");
			}
		});
		
		
		
	});

