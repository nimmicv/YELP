$("#subscriber").click(function() {
		alert("inside business home");
		var business_id=this.business_id;
		var userid=this.userid;
		var strData={"business_id":business_id,"userid":userid};
		
		$.ajax({
			type:'GET',
			url:'/business/sns',
			contentType : 'application/json',
			dataType : 'json',
			data:JSON.stringify(strData),
			success : function(response) {
				 alert("Response: " + response);
			}
			
			
		});
	});
