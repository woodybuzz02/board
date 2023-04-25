
// 모달 보기
function userInfoModalOpen(userId) {
	
	$("#modal-user").css("display", "flex");
	
	console.log(userId);

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
			      <p>권한설정</p>
			      <label><input type="radio" name="role"  value="USER"> USER</label> &nbsp &nbsp
			      <label><input type="radio" name="role" value="ADMIN"> ADMIN</label>
			      <br>
			      <br>
			      <p><input type="submit" value="저장"></p>
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
