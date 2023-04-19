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
