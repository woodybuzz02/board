
// 모달 보기
function userInfoModalOpen(userId) {
	
	$("#modal-user").css("display", "flex");
	
	console.log(userId);
	
	$("#userModalList").empty();

	$.ajax({
		type: "get",
		url: `/api/users/${userId}`,
		dataType: "json"
	}).done(res => {
		let user = res.data;
		console.log(user);
		let item = `<div class="user__item" id="userModalItem-${user.id}">
		
				<form>
				 <br>
			      <p>${user.username}의 권한설정</p>
			      <label><input type="radio" name="role" value="USER"> USER</label> &nbsp &nbsp
			      <label><input type="radio" name="role" value="ADMIN"> ADMIN</label>
			      <br>
			      <br>
			      <button class="btn btn-primary" onClick="modifyUserRole(${user.id},$("input[name='role']:checked").val())>저장</button>
			    </form> 	
				</div>
	`;
		$("#userModalList").append(item);
		
	}).fail(error => {
		console.log("계정정보 불러오기 오류", error);
	});
}

// 모달 닫기
function modalClose() {
	$("#modal-user").css("display", "none");
	location.reload();
}

function modifyUserRole(userId, role){
	
	let data = {
		role: role
	}
	
	$.ajax({
		type: "put",
		url: `/api/users/${userId}`,
		data: JSON.stringify(data),
		contentType: "application/json;charset=utf-8",
		dataType: "json"
	}).done(res => {
		console.log("성공", res);
		document.location.reload();
	}).fail(error => {
		console.log("오류", error);
	});
	
	
}