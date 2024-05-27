<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="true"%>
<c:set var="loginId" value="${sessionScope.userId}"/>
<%--<c:set var="loginId" value="${ur_id}"/>--%>
<html>
<head>
    <meta charset="UTF-8">
    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/3.1.9-1/crypto-js.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>saleInfo</title>
    <link rel="stylesheet" href="/css/saleInfo.css">

    <style>
        .mainContainer {
            width: 660px;
            height: 510px;
            background-color: #fff;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
            margin-top: 10px;
            border-radius: 12px;
            text-align: center;
        }

        .form-group {
            width: 630px;
            height: 80px;
            display: flex;
            justify-content: left; /* 가로 정렬을 중앙으로 설정 */
            align-items: center; /* 수직 정렬을 중앙으로 설정 */
        }

        .form-title {
            width: 150px;
            font-size: 14px;
            text-align: left;
            margin-left: 50px;
        }

        .inputBox {
            border: 1px solid rgb(218 222 229);
            width: 300px;
            height: 45px;
            padding-top: 5px;
            padding-bottom: 5px;
            padding-left: 10px;
            margin-top: 10px;
            margin-bottom: 3px;
            border-radius: 5px;
        }

        .reg_btn {
            display: inline-block;
            width: 30%;
            height: 50px;
            font-weight: 250;
            font-size: 25px;
            border: 1px solid #ee8703;
            border-radius: 5px;
            color: white;
            background-color: #ee8703;
            padding: 3px;
            margin-top: 10px;
            margin-bottom: 10px;
            margin-right: 60px;
        }

        .cancle_bnt {
            display: inline-block;
            width: 30%;
            height: 50px;
            font-weight: 250;
            font-size: 25px;
            border: 1px solid #ee8703;
            border-radius: 5px;
            color: #ee8703;
            background-color: #ffffff;
            padding: 3px;
            margin-top: 10px;
            margin-bottom: 10px;
        }

        .error-message {
            color: red;
            width: 200px;
            height: 10px;
            font-size: 0.9em;
            font-weight: bold;
            margin-top: 5px;
            white-space: nowrap;
            text-align: left;
        }

        .hr {
            width: 100%; /* 너비를 100%로 설정하여 가로 전체를 차지하도록 변경 */
            height: 1px; /* 선의 높이를 설정하여 선이 보이도록 변경 */
            border: none;
            border-top: 1px solid rgb(218, 222, 229); /* 상단 테두리 추가 */
        }
    </style>

</head>
<body>
<div class="navigation">
    <ul>
        <li><a href="/myPage/editMyInfo">개인 정보 수정</a></li>
        <li><a href="/myPage/changeMyPw">비밀번호 변경</a></li>
        <li><a href="/myPage/leaveAccountForm">회원 탈퇴</a></li>
    </ul>
</div>

<div class="mainContainer">
    <div class="container">
        <br><br>
        <h1 style="text-align: center">회원탈퇴 본인 확인</h1>
        <br><br>
        <hr class="hr">
        <div class="form-group">
            <div class="form-title"><span>비밀번호</span></div>
            <input type="text" class="inputBox" id="currentPw" name="currentPw" value="<c:out value='' />" pattern="^[A-Za-z\d@$!%*?&]{6,16}$" minlength="6" maxlength="16" title="기존의 비밀번호를 입력 해주세요.">
            <p class="error-message" id="currentPwErrorMSG"></p>
        </div>

        <div class="buttons">
            <button class="reg_btn" onclick="checkPw()">비밀번호 확인</button>
        </div>

    </div>

    <div class="container1" hidden>
        <br><br>
        <h1 style="text-align: center">회원탈퇴 유의사항</h1>
        <br><br>
        <hr class="hr">

        <ul>
            <li class="head">회원정보 삭제</li>
            <ul>
                <li>회원 탈퇴와 함께 치즈메이트에 등록된 모든 개인정보는 삭제, 폐기 처리되며 복구되지 않습니다.</li>
                <li>회원탈퇴 시 동일 아이디로 재가입하실 수 없습니다.</li>
            </ul>
            <li class="head">탈퇴 후에도 게시판형 서비스에 등록한 게시물은 그대로 남습니다.</li>
            <ul>
                <li>판매글에 남근 댓글, 좋아요, 커뮤니티 게시글, 채팅은 탈퇴 시 자동 삭제되지 않고 그대로 남아 있습니다.</li>
                <li>회원탈퇴를 진행하기전 비공개처리나 삭제하시기 바랍니다.</li>
            </ul>
        </ul>

        <div style="text-decoration: underline; color: #cb0003; text-align: center; padding-bottom: 10px;">
            <span style=" color: #cb0003; text-align: center">※</span>
            <span> 탈퇴 시 회원님의 치즈메이트 이용정보가 삭제되며</span><br>
            <span> 복구가 불가능하오니 신중히 선택하시기 바랍니다.</span>
        </div>

        <input type="checkbox" id="check"> 안내 사항을 모두 확인하였으며, 이에 동의합니다.

        <div class="buttons">
            <button class="reg_btn" id="confirmBtn" onclick="leaveBtn()">회원탈퇴 확인</button>
        </div>
    </div>
</div>
</body>
</html>

<script>
    const userPw = "<c:out value='${userDto.pw}' />";

    function checkPw() {
        var currentPw = document.getElementById("currentPw").value;
        var currentPwErrorMSG = document.getElementById("currentPwErrorMSG");
        var container = document.querySelector('.container');
        var container1 = document.querySelector('.container1');

        if(hashPassword(currentPw) === userPw) {
            currentPwErrorMSG.textContent = "";
            document.getElementById("currentPw").style.borderColor = "";
            container.setAttribute('hidden', true); // hidden 속성 추가
            container1.removeAttribute('hidden'); // hidden 속성 제거
        } else {
            check = false;
            document.getElementById("currentPw").style.borderColor = "red";
            currentPwErrorMSG.textContent = "기존 비밀번호와 일치하지 않습니다.";
            container.removeAttribute('hidden'); // hidden 속성 제거
        }
    }

    function hashPassword(password) {
        return CryptoJS.SHA256(password).toString(CryptoJS.enc.Hex);
    }

    function leaveBtn() {
        if(document.getElementById("check").checked === true) {
            if(window.confirm("정말 탈퇴 하시겠습니까?")) {
                // AJAX를 사용하여 서버로 회원 탈퇴 요청을 보냅니다.
                $.ajax({
                    url: "/myPage/leaveAccount",
                    type: "GET",
                    success: function(response) {
                        // 회원 탈퇴가 성공하면 홈페이지로 리다이렉트합니다.
                        window.alert("회원탈퇴가 완료 되었습니다.")
                        window.location.href = "/";
                    },
                    error: function(xhr, status, error) {
                        // 오류가 발생하면 적절한 오류 처리를 수행합니다.
                        console.error(xhr.responseText);
                    }
                });
            }
        }
        else {
            window.alert("회원탈퇴에 동의해야 탈퇴 가능합니다.")
        }
    }
</script>