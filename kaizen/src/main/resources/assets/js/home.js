
	$("#search").click(function() {
		var strData={"name":search};
alert(search);
		$.ajax({
			type : 'POST',
			url : '/kaizen/business',
			contentType : 'application/json',
			dataType : 'json',
			data:JSON.stringify(strData),
			success : function(response) {
				 alert("Response: " + response);
				 parent.document.getElementById("mustFrame").src="http://localhost:8080/business/"+username;
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

