function deleteSlang(){
	
	var slang_arr = new Array();

	var dataObj={};
	
	// for문으로 데이터 생성
	for (var i = 0 ; i < $("#slang_table tbody tr").length; i++){
	    // 행 별로 첫번째 input 값을 찾아서 newcode로 초기화 
	    if($("#slang_table tbody tr").eq(i).children().children().eq(0).is(":checked")){
			var id = $("#slang_table tbody tr").eq(i).children().children().eq(0).val();
			
			dataObj.id = id;
			
			// array에 object push
		    slang_arr.push(dataObj);
		}

	}
	
	// push 후 초기화 해주지 않으면 같은 object로 loop 만큼 push됨 
	dataObj ={};
	
	
	console.log(slang_arr);
	
	$.ajax({
		type: "delete",
		url: `/slang-filter`,
		data: JSON.stringify(slang_arr),
		contentType: "application/json;charset=utf-8",
		dataType: "json"
	}).done(res => {
		console.log("성공", res);
		window.location.reload();
	}).fail(error => {
		console.log("오류", error);
	});
	
	
}