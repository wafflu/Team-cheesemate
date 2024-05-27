
// $(document).ready(function() {
//     $(".contentsBox").each(function() {
//         let $this = $(this);
//         let sDate = new Date('<fmt:formatDate value="${event.s_date}" pattern="yyyy-MM-dd" />');
//         let eDate = new Date('<fmt:formatDate value="${event.e_date}" pattern="yyyy-MM-dd" />');
//         let now = new Date();
//
//         let remainingTime = eDate.getTime() - now.getTime();
//         let remainingDays = Math.ceil(remainingTime / (1000 * 60 * 60 * 24));
//         let remain_id=`#remainingDays-${event.evt_no}`
//         $this.find(remain_id).text('이벤트 종료까지 ' + remainingDays + '일 남았습니다.');
//     });
// });
function loadDocArr(){
    // 폼 데이터를 담을 객체 생성
    // 폼 안의 각 input 및 select 요소를 순회하면서 값을 읽어와서 formData 객체에 추가
    let cd = $("#user_select").val();
    let inputcontents = $("#inputcontents").val();
    let formData={cd:cd, contents:inputcontents};
    let arrdata = {};
    // 만들어진 formData 객체를 사용하여 원하는 작업을 수행
    console.log("함수 실행");
    console.log(formData);
    console.log(JSON.stringify(formData));
    $.ajax({
        url: "/event/search",
        type : "POST",
        contentType: "application/json;",
        dataType : "json",
        data : JSON.stringify(formData),
        success : function (data) {
            arrdata=JSON.parse(data);
            let str = "<tr><th>이벤트 번호</th><th>이벤트 제목</th><th>이벤트 내용</th><th>상품</th><th>시작일</th><th>종료일</th><th>활성 상태</th></tr>";
            for(let i of arrdata){
                str+="<tr>";
                str+="<td>" + i.evt_no + "</td>";
                str+="<td><a href='/event/read?evt_no=" + i.evt_no + "'>" + i.title + "</a></td>";
                str+="<td><p>" + i.contents + "</p></td>";
                str+="</tr>"
            }
            $("#contents").html(str);
        }
    })
}
$(document).ready(function() {
    $("#searchform").submit(function(e) {
        e.preventDefault(); // 폼의 기본 동작 방지
        loadDocArr();
    });
});

function showThumbnailImage(uploadResultArr){

    /* 전달받은 데이터 검증 */
    if(!uploadResultArr || uploadResultArr.length == 0){return}

    let uploadResult = $("#uploadResult");
    let str = "";

    for(let i = 0; i<uploadResultArr.length; i++){
        let obj = uploadResultArr[i];

        let fileCallPath = encodeURIComponent(obj.filert + "/s_" + obj.u_name + "_" + (obj.o_name+obj.e_name));

        str += "<div id='result_card'>";
        str += "<img src='/img/display?fileName=" + fileCallPath +"'>";
        str += "</div>";
    }

    uploadResult.append(str);
}