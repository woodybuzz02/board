let order;

let postId = $("#postId").val();

findAllReply()

// 댓글 보기
function findAllReply() {
	
	$.ajax({
		type: "get",
		url: `/api/${postId}/reply`,
		dataType: "json"
	}).done(res => {
		let replys = res.data
		console.log("성공", reply);
		replys.forEach((replys) => {
		});
	}).fail(error => {
		console.log("오류", error);
	});
	
}

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

// post 공유
function sharePost(postId) {
	window.navigator.share({
	  title: $("#title").val(), // 공유될 제목
	  text: $("#content").val(), // 공유될 설명
	  url: `post/${postId}` // 공유될 URL
	});
}

// 댓글 작성
function addReply(postId) {
	
	let replyContent = $('#content').val();

	let data = {
		postId: postId,
		content: replyContent,
		replyOrder: 0
	}

	if (data.content === "") {
		alert("댓글을 작성해주세요!");
		return;
	}

	$.ajax({
		type: "post",
		url: "/api/reply",
		data: JSON.stringify(data),
		contentType: "application/json;charset=utf-8",
		dataType: "json"
	}).done(res => {
		console.log("성공", res);
		$('#replyContent').val("");
		order = 0;
		findAllReply();
	}).fail(error => {
		console.log("오류", error);
	});
	
}