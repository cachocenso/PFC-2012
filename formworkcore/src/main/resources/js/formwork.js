	 $(document).ready(function() {
		$("input").change(function() {
	 		$.ajax({url:"/au/update",
	 			type:"post",
	 			dataType: "json",
	 			data: {"id": this.id, "value":this.value},
	 			success:function(result){
	 				$.each(result, function(i, object){
	 					if (object.tipoMensaje != null) {
	 						alert(object.idPartida + " " + object.mensaje);
	 						$("#"+object.idPartida).tooltip({
	 							content: object.mensaje,
	 							item: "input"
	 						});
	 						
	 						tooltips.tooltip("open");
	 					} else {
	 						$("#"+object.id).val(object.value);
	 					}
	 				})
	 				
			  }});	 	
		});
	 });
