$("#subscriber").click(function() {
		//var name=this.name;
	var name='Turf Paradise Race Course';
	var userid='r-t7IiTSD0QZdt8lOUCqeQ';
	//	var userid=this.userid;
		var strData={"name":name,"userid":userid};
		alert(name);
		$.ajax({
			type:'POST',
			url:'/kaizen/subscribe',
			contentType : 'application/json',
			dataType : 'json',
			data:JSON.stringify(strData),
			success : function(response) {
				 alert("You have successfully subscribed to the business " + name);
			}	
		});
	});

