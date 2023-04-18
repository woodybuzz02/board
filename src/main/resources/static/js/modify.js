// 회원수정
function modify(postId) {
	event.preventDefault();
	
	let data = {
		title: $("#title").val(),
		content: $("#content").val()
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
		location.href = `/post/${postId}`;
	}).fail(error => {
		console.log(error);
		if (error.responseJSON.data == null) {
			alert(error.responseJSON.message)
		} else {
			alert(JSON.stringify(error.responseJSON.data));
		}
	});

}