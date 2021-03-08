/**
 * 회원스크립트
 */
var ajaxFunc = function(array){
	$.ajax({
        url     : '/addMembers',
        type    : 'POST',
        data    : JSON.stringify(array),
        contentType : 'application/json; charset=UTF-8',
        dataType: 'json',
        success : function(data) {
        	
        },
        error : function(xhr,status,error) {
        	console.log("xhr: " + xhr);
        	console.log("status: " + status);
        	console.log("error: " + error);
        }
    });
};

//회원 다중 등록
$(document).on('click', '#addMembers', function(){

	var addMembers = $('#addMemberBody tr');
	var arrayMembers = []; 
	var validationCheck = false;
	
	for(var i = 0; i < addMembers.length; i++){
		var memberInfo = {};
		var bindElement = addMembers.eq(i).find('input[type="text"]');
		
		$.each(bindElement, function(){
			var memberInfoKey = $(this).attr('name');
			var memberInfoValue = $(this).val();
			
			if(memberInfoValue == null || memberInfoValue == undefined || memberInfoValue == ''){
				$(this).focus();
				alert('입력하지 않은 정보가 있습니다.');
				validationCheck = false;
				return false;
			}
			
			memberInfo[memberInfoKey] = memberInfoValue;
			validationCheck = true;
		});
		
		if(!validationCheck) return false;
		
		arrayMembers.push(memberInfo);
	};
	
	console.log(JSON.stringify(arrayMembers));
	
	//if(validationCheck) ajaxFunc(arrayMembers);

});

//체크된 회원 등록
$(document).on('click', '#addCheckedMembers', function(){
	var memberCheckObj = $('input[class="memberCheck"]:checked').parent().parent();
	// 회원정보리스트 JSON Array
	var arrayMembers = []; 
	// 체크된 항목 수
	var memberCheckCount = memberCheckObj.length;
	// 유효성 여부
	var validationCheck = true;
	
	
	if(memberCheckCount < 1){
		alert('체크된 항목이 없습니다.');
		return false;
	}
	
	// 아이디 중복 체크 배열
	var checksum = [];
	
	$.each(memberCheckObj, function(index, item){
					
		var memberInfo = {};
		var bindElement = $(this).find('input[type="text"]');
		var memberIdCheck = $(this).find('input[name="memberId"]');
		
		// 동일 아이디 유효성 검사
		$.each(memberIdCheck, function(index, item){
			
			if ($.inArray(memberIdCheck.val(), checksum) > -1) {
				alert('동일한 아이디를 입력할 수 없습니다.')
				memberIdCheck.focus();
				validationCheck = false;				
				return false;
			}
			
			checksum.push(memberIdCheck.val());           						
		});
		
		
		// 입력한 항목 유효성 검사
		$.each(bindElement, function(index, item){
			var memberInfoKey = $(this).attr('name');
			var memberInfoValue = $(this).val();
			if(memberInfoValue == null || memberInfoValue == undefined || memberInfoValue == ''){
				$(this).focus();
				alert('입력하지 않은 정보가 있습니다.');
				validationCheck = false;
				return false;
			}
			memberInfo[memberInfoKey] = memberInfoValue;
		});

		arrayMembers.push(memberInfo);
		
		if(!validationCheck) return false;
	});
	
	if(validationCheck) console.log(JSON.stringify(arrayMembers));
});

// 다중 행 추가
$(document).on('click', '#addRows', function(){
	var tbody = $('#addMemberBody');
	
	var count = $('#trCount').val();
	
	if( count != null && count != undefined && count > 0){		
		$('#addMemberBody tr:last td:last button').attr('class', 'removeButton');
		$('#addMemberBody tr:last td:last button').text('삭제');

		
		for(var i = 0; i < count; i++){	
			var lastIndex = $(tbody).find('tr:last').length;
			var trInnerHtml = '<tr>';
			trInnerHtml += '<td><input type="checkbox" class="memberCheck"/></td>';
			trInnerHtml += '<td><input type="text" name="memberId" /></td>';
			trInnerHtml += '<td><input type="text" name="memberPw" /></td>';
			trInnerHtml += '<td><input type="text" name="memberName" /></td>';
			trInnerHtml += '<td><input type="text" name="memberAddr" /></td>';
			trInnerHtml += '<td><input type="text" name="memberEmail" /></td>';
			
			if(i == (count - 1)){
				trInnerHtml += '<td><button type="button" class="addButton">추가</button></td>';
			}else{
				trInnerHtml += '<td><button type="button" class="removeButton">삭제</button></td>';	
			}
			
			trInnerHtml += '</tr>';
		
			tbody.append(trInnerHtml);		
			
		}
	}else{
		alert('추가할 행의 갯수를 입력하세요.');
		$('#trCount').focus();
		return false;
	}
	
	$('#trCount').val(0);
});

// 체크된 행 삭제
$(document).on('click', '#removeRows', function(){
	var memberCheckObj = $('input[class="memberCheck"]:checked');
	// 체크된 항목 수
	var memberCheckCount = memberCheckObj.length;
	// 전체 행의 수
	var addMemberBodyCount = $('#addMemberBody tr').length;
	
	if(memberCheckCount < 1){
		alert('체크된 항목이 없습니다.');
		return false;
	}
	
	if(memberCheckCount >= addMemberBodyCount) {
		alert('모든 행을 지울 수 없습니다.');
		return false;
	}

	$.each(memberCheckObj, function(){
		$(this).parent().parent().remove();			
	});
	
	var lastTrRemoveBtnObj = $('#addMemberBody tr:last td').find('.removeButton');
	
	lastTrRemoveBtnObj.attr('class', 'addButton');
	lastTrRemoveBtnObj.text('추가');
	
});

// 행 추가
$(document).on('click', '.addButton', function(){
	var tr = $(this).parent().parent();
	var addMemberBodyObj = $('#addMemberBody');

	$(this).attr('class', 'removeButton');
	$(this).text('삭제');
	
	var trInnerHtml = '<tr>';
	trInnerHtml += '<td><input type="checkbox" class="memberCheck"/></td>';
	trInnerHtml += '<td><input type="text" name="memberId" /></td>';
	trInnerHtml += '<td><input type="text" name="memberPw" /></td>';
	trInnerHtml += '<td><input type="text" name="memberName" /></td>';
	trInnerHtml += '<td><input type="text" name="memberAddr" /></td>';
	trInnerHtml += '<td><input type="text" name="memberEmail" /></td>';
	trInnerHtml += '<td><button type="button" class="addButton">추가</button></td>';
	trInnerHtml += '</tr>';
	
	addMemberBodyObj.append(trInnerHtml);
	
});

// 행 삭제
$(document).on('click', '.removeButton', function(){
	var tr = $(this).parent().parent();
	tr.remove();
	$('#addMemberBody tr:last td:last button').attr('class', 'addButton');
	$('#addMemberBody tr:last td:last button').text('추가');
});