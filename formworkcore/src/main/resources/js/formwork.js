	 $(document).ready(function() {
		$("input").change(function() {
	 		$.ajax({url:"/au/update",
	 			type:"post",
	 			dataType: "json",
	 			data: {"id": this.id, "value":this.value},
	 			success:function(result){
	 				$("#"+result.id).val(result.value);
			  }});	 	
		});
	 });
