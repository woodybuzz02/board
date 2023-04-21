let order;

let postId = $("#postId").val();
let principalId = $("#principalId").val();
let username = $("#username").val();

findAllReply()

// 댓글 보기
function findAllReply() {
	
	$.ajax({
		type: "get",
		url: `/api/${postId}/reply`,
		dataType: "json"
	}).done(res => {
		let replys = res.data
		console.log("성공", replys);	
		
		res.data.forEach((reply) => {
			
			let items1 = `<div id="postReplyInput-${reply.id}">
				</div>`
			
			$("#readReply").append(items1);
			
			let items2 = `
					<tr>
						<!-- 아이디, 작성날짜 -->
						<td width="150">
							<div>
								<c:if test="${reply.depth}">
									왜안되징...ㅎ
								</c:if>
								${reply.user.username}<br>
								<font size="2" color="lightgray">${reply.createdAt}</font>
							</div>
						<td>
						<!-- 본문내용 -->
						<td width="800">
							<div class="text_wrapper">
								${reply.content}
							</div>
						</td>
						<!-- 버튼 -->
						<td width="100">
							<div id="btn" style="text-align:center;">
								<a href="#" onclick="cmReplyOpen(${reply.id}, ${reply.replyOrder})" >[답변]</a><br>
							<!-- 댓글 작성자만 수정, 삭제 가능하도록 -->
							<c:if test="${reply.user.id == principalId}">
								<a href="#">[수정]</a><br>
								<a href="#">[삭제]</a>
							</c:if>
							</div>
						</td>
					</tr>	
			`
			$(`#postReplyInput-${reply.id}`).append(items2);
			
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
function addReply(postId, depth) {
	
	let replyContent = $('#replyContent').val();

	if(depth ){
		
	}
	
	let data = {
		postId: postId,
		content: replyContent,
		depth: depth
	}

	if (data.content === "") {
		alert("댓글을 작성해주세요!");
		return;
	}

	$.ajax({
		type: "post",
		url: `/api/reply/${depth}`,
		data: JSON.stringify(data),
		contentType: "application/json;charset=utf-8",
		dataType: "json"
	}).done(res => {
		console.log("성공", res);
		$('#replyContent').val("");
		order = 0;
		document.location.reload(); // 새로고침
	}).fail(error => {
		console.log("오류", error);
	});
	
}


function cmReplyOpen(replyId){
	
	if(principalId == "" || principalId == null){
		alert("로그인후 사용가능합니다.");
		return false;
	}	
	else{
		let items2 = `
			<tr>
				<td width="150">
				</td>
				<td width="800">
					<textarea class="form-control" id="replyContent" rows="4" cols="70"></textarea>
				</td>
				<td width="100">
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="btn btn-primary" onClick="addReply(${postId}, 1)">등록</button>
				</td>	
			</tr>
		`
		$(`#postReplyInput-${replyId}`).append(items2);
							
	}
	
	
	
	
}



