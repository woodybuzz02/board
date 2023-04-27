
let postId = $("#postId").val();
let principalId = $("#principalId").val();
let username = $("#username").val();

// 페이지 초기화
let page = 0;

findAllReply()

// 댓글 보기
function findAllReply() {
	
	$.ajax({
		type: "get",
		url: `/api/${postId}/reply?page=${page}`,
		dataType: "json"
	}).done(res => {
	
		if(res.data.last == true){
			$("#replyBtn-more").css("display", "none");
		}
		
		res.data.content.forEach((reply) => {
			
			let items1 = `<div id="postReplyInput-${reply.id}">
				</div>
				`
			
			$("#readReply").append(items1);
			
			let items2 = `
					<tr>
						<!-- 아이디, 작성날짜 -->
						<td width="150">
							<div>`;
			
			items2 += `&nbsp ${reply.user.username}<br>
								<font size="2" color="lightgray">${reply.createdAt}</font>
							</div>
						<td>`;
			
			items2 += 	`<!-- 본문내용 -->
						<td width="800">
							<div class="text_wrapper" id="replyContent-${reply.id}" onclick="findChildReply(${reply.id})">
								${reply.content}
							</div>
						</td>
						<!-- 버튼 -->
						<td width="100">`;
			if (principalId == "" || principalId == null || reply.content === "삭제된 댓글입니다.") {
				items2 += `
					</td>
					</tr>	
			`;
			} else {
				items2 += `<div id="replyBtn-${reply.id}" style="text-align:center;">
							<a href="#" onclick="replyOpen(${reply.id}, ${reply.replyGroup})" >[답변]</a><br>
						<!-- 댓글 작성자만 수정, 삭제 가능하도록 -->`;
						if (reply.user.id == principalId){
							items2 += `
										<a href="#" onclick="replyModifyOpen(${reply.id},'${reply.content}')">[수정]</a><br>
										<a href="#" onclick="deleteReply(${reply.id})">[삭제]</a><br>
										</div>
									</td>
								</tr>	
								`
								;
						}
			}

			$(`#postReplyInput-${reply.id}`).append(items2);
			
		}
				
		);
		
			
	}).fail(error => {
		console.log("오류", error);
	});
	
}

// 대댓글 보기
function findChildReply(parentReplyId) {
	
	$.ajax({
		type: "get",
		url: `/api/${postId}/reply/${parentReplyId}`,
		dataType: "json"
	}).done(res => {
		let replys = res.data
		console.log("성공", replys);	
		
		res.data.forEach((reply) => {
			
			$(`#postChildReplyInput-${reply.id}`).remove();
					
			let items1 = `
					<tr id = "postChildReplyInput-${reply.id}">
						<!-- 아이디, 작성날짜 -->
						<td width="150">
							<div>`;
			items1 += `&nbsp >> ${reply.user.username}<br>
							<font size="2" color="lightgray">${reply.createdAt}</font>
						</div>
					<td>`;
			
			items1 += 	`<!-- 본문내용 -->
						<td width="800">
							<div class="text_wrapper" id="replyContent-${reply.id}" >
								${reply.content}
							</div>
						</td>
						<!-- 버튼 -->
						<td width="100">`;
			if (principalId == "" || principalId == null || reply.content === "삭제된 댓글입니다.") {
				items1 += `</td>
					</tr>	
			`;
			} else {
				items1 += `<div id="replyBtn-${reply.id}" style="text-align:center;">
						<!-- 댓글 작성자만 수정, 삭제 가능하도록 -->`;
						if (reply.user.id == principalId){
							items1 += `
										<a href="#" onclick="replyModifyOpen(${reply.id},'${reply.content}')">[수정]</a><br>
										<a href="#" onclick="deleteReply(${reply.id})">[삭제]</a><br>
										</div>
									</td>
								</tr>	`;
						}
			}

			$(`#postReplyInput-${parentReplyId}`).append(items1);
			
		});
		
		
			
	}).fail(error => {
		console.log("오류", error);
	});
	
}

function more(){
	page++;
	findAllReply();
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
function addReply(postId, depth, replyGroup, replyId) {
	
	let replyContent = $('#replyContent').val();
	let replyContent2 = $('#replyContent2').val();
	let data;

	if(depth == 1){
		
		data = {
			postId: postId,
			content: replyContent2,
			depth: depth,
			replyGroup:replyGroup,
			parentReplyId: replyId
		}
		
		if (data.content === "") {
			alert("댓글을 작성해주세요!");
			return;
		}
	
	}else{
		
		data = {
			postId: postId,
			content: replyContent,
			depth: depth
		}
	
		if (data.content === "") {
			alert("댓글을 작성해주세요!");
			return;
		}
		
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
		document.location.reload(); // 새로고침
	}).fail(error => {
		console.log("오류", error);
	});
	
}

// 대댓글 작성시 댓글창 추가
function replyOpen(replyId, replyGroup){
	
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
					<textarea class="form-control" id="replyContent2"></textarea>
				</td>
				<td width="100">
					<div id="replyBtn" style="text-align:center;">
						<button class="btn btn-primary" onClick="addReply(${postId}, 1, ${replyGroup}, ${replyId})">등록</button>
						<button class="btn btn-primary" onClick="document.location.reload()">취소</button>
					</div>
				</td>	
			</tr>
		`
		$(`#postReplyInput-${replyId}`).append(items2);
							
	}

}

// 대댓글 수정시 댓글 수정창 추가
function replyModifyOpen(replyId, content){	
		$(`#replyContent-${replyId}`).empty();
		$(`#replyBtn-${replyId}`).empty();
		
		let items = `
			<textarea class="form-control" rows="4" cols="70" id=modifyContent>${content}</textarea>
		`
		let items2 = `
			&nbsp;&nbsp;&nbsp;&nbsp;
			<button class="btn btn-primary" onClick="modifyReply(${replyId})">수정</button>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<button class="btn btn-primary" onClick="document.location.reload()">취소</button>
		`
		$(`#replyContent-${replyId}`).append(items);
		$(`#replyBtn-${replyId}`).append(items2);					
	}

// 대댓글 수정
function modifyReply(replyId) {
	
	let modifyContent = $('#modifyContent').val();
	let data;
	
		data = {
			content: modifyContent,
		}
	
		if (data.content === "") {
			alert("댓글을 작성해주세요!");
			return;
		}
	
	$.ajax({
		type: "put",
		url: `/api/reply/${replyId}`,
		data: JSON.stringify(data),
		contentType: "application/json;charset=utf-8",
		dataType: "json"
	}).done(res => {
		console.log("성공", res);
		$('#modifyContent').val("");
		document.location.reload(); // 새로고침
	}).fail(error => {
		console.log("오류", error);
	});
	
}

// 댓글 삭제
function deleteReply(replyId){
	
	$.ajax({
		type: "delete",
		url: `/api/reply/${replyId}`,
		contentType: "application/json;charset=utf-8",
		dataType: "json"
	}).done(res => {
		console.log("성공", res);
		document.location.reload(); // 새로고침
	}).fail(error => {
		console.log("오류", error);
	});
	
}