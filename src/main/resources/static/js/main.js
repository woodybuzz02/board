// post 삭제
function deletePost(postId) {
	$.ajax({
		type: "delete",
		url: `/api/post/${postId}`,
		dataType: "json"
	}).done(res => {
		console.log("성공", res);
		location.href = `/post/main`;
	}).fail(error => {
		console.log("오류", error);
	});
}

// 게시글수정
function modifyStatus(postId) {
	event.preventDefault();
	
	let data = {
		title: $(`#title-${postId}`).val(),
		content: $(`#content-${postId}`).val(),
		status: 16
	};
	
	console.log(data);
	
	$.ajax({
		type: "put",
		url: `/api/post/${postId}`,
		data: data,
		contentType: "application/x-www-form-urlencoded; charset=utf-8",
		dataType: "json"
	}).done(res => {
		console.log("update 성공", res)
		location.href = `/post/main`;
	}).fail(error => {
		console.log(error);
		if (error.responseJSON.data == null) {
			alert(error.responseJSON.message)
		} else {
			alert(JSON.stringify(error.responseJSON.data));
		}
	});

}