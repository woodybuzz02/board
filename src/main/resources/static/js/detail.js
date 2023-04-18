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

var share = document.getElementById('btn-share');

share.addEventListener("click", async () => {
  try {
    await navigator.share({
      title: $("#title").val(),
      text: $("#content").val(),
      url: `/post/${postId}`,
    });
    console.log("공유 성공");
  } catch (e) {
    console.log("공유 실패");
  }
});

// Web Share API는 HTTPS 환경에서만 사용 가능하며 _사용자의 직접적인 액션으로만 호출_할 수 있습니다. 어떻게 해야되징...? https 적용해야될듯...
