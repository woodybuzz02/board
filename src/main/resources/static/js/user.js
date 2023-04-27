
let userRole = $("#userRole").val();

function modifyUserRole(){
	
	var user_arr = new Array();

	var dataObj={};
	
	// for문으로 데이터 생성
	for (var i = 0 ; i < $("#user_table tbody tr").length; i++){
	    // 행 별로 첫번째 input 값을 찾아서 newcode로 초기화 
	    var id = $("#user_table tbody tr").eq(i).children().children().eq(0).val();
	    var role1 = $("#user_table tbody tr").eq(i).children().children().eq(1).val();
	    var role2 = $("#user_table tbody tr").eq(i).children().children().eq(2).val();
	    
	    // object에 id와 role이라는 key로 값 바인딩
	    if(role1 != role2){
		    dataObj.id = id;
			dataObj.role = role2;
			
		// array에 object push
	    user_arr.push(dataObj);
	    
		}
	
	    // push 후 초기화 해주지 않으면 같은 object로 loop 만큼 push됨 
	    dataObj ={};
	}
	
	console.log(user_arr);
	
	$.ajax({
		type: "put",
		url: `/api/users`,
		data: JSON.stringify(user_arr),
		contentType: "application/json;charset=utf-8",
		dataType: "json"
	}).done(res => {
		console.log("성공", res);
		document.location.reload();
	}).fail(error => {
		console.log("오류", error);
	});
	
	
}