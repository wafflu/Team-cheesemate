<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="UTF-8">
    <script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>
    <style>
        input:invalid {
            border: 2px solid red;
        }

        hr {
            width: 40%;
        }

        form {
            border: 1px solid rgb(218, 222, 229);
            width: 1200px;
            height: 90%;
            margin: 0 auto;
            display: block;
        }

        .reg_btn {
            display: inline-block;
            width: 95%;
            height: 80px;
            font-weight: 500;
            font-size: 25px;
            border: 1px solid #ee8703;
            border-radius: 5px;
            color: white;
            background-color: #ee8703;
            padding: 3px;
            margin-bottom: 10px;
        }

        label {
            display: inline;
            text-align: left;
            margin: 10px 0;
        }

        .inputBox {
            width: 100%;
            padding-top: 5px;
            padding-bottom: 5px;
            padding-left: 10px;
            margin-top: 3px;
            margin-bottom: 3px;
            border: 1px solid rgb(218 222 229);
        }

        .inputAdressBoxLarge {
            width: 110px;
            height: 40px;
            padding-top: 5px;
            padding-bottom: 5px;
            padding-left: 10px;
            margin-left: 100px;
            margin-top: 3px;
            margin-bottom: 3px;
            border: 1px solid rgb(218 222 229);
        }

        .inputAdressBoxMedium {
            width: 150px;
            padding-top: 5px;
            padding-bottom: 5px;
            padding-left: 10px;
            margin-top: 3px;
            margin-bottom: 3px;
            border: 1px solid rgb(218 222 229);
        }

        .inputAdressBoxSmall {
            width: 30%;
            padding-top: 5px;
            padding-bottom: 5px;
            padding-left: 10px;
            margin-top: 3px;
            margin-bottom: 3px;
            border: 1px solid rgb(218 222 229);
        }

        .inputBirthBox {
            width: 85%;
            padding-top: 5px;
            padding-bottom: 5px;
            padding-left: 10px;
            margin-top: 3px;
            margin-bottom: 3px;
            border: 1px solid rgb(218 222 229);
        }

        .center {
            max-width: 630px;
            height: auto;
            background-color: #fff;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
            border-radius: 12px;
            text-align: center;
        }

        .form-group {
            height: 70px;
            margin-bottom: 5px;
            padding: 5px;
            display: flex;
        }

        .form-title {
            margin-top: 15px;
            margin-left: 50px;
            font-size: 14px;
        }

        .form-group div {
            width: 120px; /* 레이블의 너비를 지정 */
            text-align: left; /* 텍스트를 왼쪽 정렬 */
            margin-right: 10px;
        }

        .form-group input {
            width: 300px;
            height: 45px;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 4px;
            margin-left: 40px;
        }

        .error-message {
            color: red;
            font-size: 0.9em;
            font-weight: bold;
            margin-top: 5px;
            margin-left: 50px;
            white-space: nowrap;
        }

        /* Hide the default calendar icon */
        input[type="date"]::-webkit-calendar-picker-indicator {
            display: none;
        }

        /* 성별 선택을 가로로 정렬 */
        .gender-container {
            display: flex;
            align-items: center;
        }

        .gender-option {
            display: inline-block;
            margin-right: 10px;
            position: relative;
            padding-left: 30px; /* 충분히 넓게 설정하여 커스텀 라디오 버튼을 위한 공간 확보 */
            cursor: pointer;
            font-size: 18px; /* 텍스트 크기 조정 */
            white-space: nowrap;
        }

        .gender-option input[type="radio"] {
            position: absolute;
            opacity: 0;
            cursor: pointer;
        }

        .gender-option .custom-radio {
            position: absolute;
            top: 50%;
            left: 0;
            height: 25px; /* 커스텀 라디오 버튼의 높이 */
            width: 25px; /* 커스텀 라디오 버튼의 너비 */
            background-color: #eee;
            border-radius: 50%;
            transform: translateY(-50%);
        }

        .gender-option input[type="radio"]:checked ~ .custom-radio {
            background-color: #ee8703;
        }

        .gender-option .custom-radio:after {
            content: "";
            position: absolute;
            display: none;
        }

        .gender-option input[type="radio"]:checked ~ .custom-radio:after {
            display: block;
        }

        .gender-option .custom-radio:after {
            top: 50%;
            left: 50%;
            width: 12px;
            height: 12px;
            border-radius: 50%;
            background: white;
            transform: translate(-50%, -50%);
        }

        .agree-container {
            display: flex;
            flex-direction: column; /* 세로로 정렬 */
            align-items: flex-start;
        }

        .agree-option {
            display: flex; /* 플렉스 박스 사용 */
            align-items: center; /* 세로 중앙 정렬 */
            margin-bottom: 10px;
            position: relative;
            cursor: pointer;
            white-space: nowrap;
            font-size: 14px;
        }

        .agree-option input[type="checkbox"] {
            margin-right: 10px; /* 체크박스와 텍스트 간의 간격 조정 */
            width: 18px;
            height: 18px;
            accent-color: #ee8703;
        }

        .agreeCheck {
            color: darkgrey;
        }

        /* 모달 스타일링 */
        .modal {
            display: none; /* 기본적으로 모달을 숨김 */
            position: fixed;
            z-index: 1;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            overflow: auto;
            background-color: rgba(0, 0, 0, 0.4); /* 검은색 배경 */
        }

        .modal-content {
            background-color: white;
            margin-top: 2%;
            margin-left: 30%;
            padding: 20px;
            border: 1px solid #888;
            width: 40%; /* 모달 창 너비 */
        }

        .close {
            color: #aaa;
            float: right;
            font-size: 28px;
            font-weight: bold;
        }

        .close:hover,
        .close:focus {
            color: black;
            text-decoration: none;
            cursor: pointer;
        }

        .hr {
            width: 100%; /* 너비를 100%로 설정하여 가로 전체를 차지하도록 변경 */
            height: 1px; /* 선의 높이를 설정하여 선이 보이도록 변경 */
            border: none;
            border-top: 1px solid rgb(218, 222, 229); /* 상단 테두리 추가 */
        }

        .addr_class {
            flex: 1;
        }

        #openModalBtn {
            all: unset;
            color: white;
            padding: 10px;
            border-radius: 5px;
            cursor: pointer;
            background-color: #ee8703;
            font-size: 16px;
            font-weight: 600;
            height: 20px;
            margin-left: 40px;
            width: 280px;
            text-align: center;
            margin-bottom: 10px;
        }

        .SaleModal {
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            display: flex;
            justify-content: center;
            align-items: center;
            z-index: 30;
        }

        .sale_modal_overlay {
            background-color: rgba(0, 0, 0, 0.6);
            width: 100%;
            height: 100%;
            position: absolute;
        }

        .sale_modal_content {
            background-color: white;
            padding: 50px 100px;
            text-align: center;
            position: relative;
            width: 50%;
            top: 0px;
            border-radius: 10px;
            box-shadow: 0 10px 20px rgba(0, 0, 0, 0.19), 0 6px 6px rgba(0, 0, 0, 0.23);
            overflow-y: auto;
            /* 세로 스크롤이 필요한 경우 스크롤 허용 */
            max-height: 70%;
            /* 모달 창의 최대 높이 설정 */
        }

        #closeModalBtn {
            position: absolute;
            top: 10px;
            /* 원하는 위치로 조정 */
            right: 10px;
            /* 원하는 위치로 조정 */
            background-color: transparent;
            /* 배경색 설정 */
            border: none;
            /* 테두리 제거 */
            cursor: pointer;
            /* 마우스 커서를 포인터로 변경 */
        }

        .sale-table-wrapper {
            overflow-y: auto;
            max-height: 200px;
        }

        .sale-addr-tr:hover {
            background-color: rgba(245, 157, 28, 0.5);
            cursor: pointer;
        }

        #saleSearchInput {
            /* 모달 창의 너비에 맞게 조정 */
            width: 100%;
            padding: 10px;
            /* 내부 여백 설정 */
            margin: 0 auto;
            /* 가운데 정렬 */
        }


        #addrTable {
            width: 100%;
            /* addrTable의 너비를 100%로 설정하여 모달 창에 맞춤 */
        }

        #sale_search_addr {
            margin: 0;
        }

        .SaleHidden {
            display: none;
        }
    </style>

    <title>치즈메이트 - 회원가입</title>
</head>
<body>
<div id="openModal" class="SaleModal SaleHidden">
    <div class="sale_modal_overlay"> <!--모달창의 배경색--></div>
    <div class="sale_modal_content">
        <button id="closeModalBtn">x</button>
        <p class="font30" id="sale_search_addr">주소 검색</p>
        <input class="font16" id="saleSearchInput" type="text" placeholder="동(읍/면/리)을 입력해주세요(enter).">
        <div class="sale-table-wrapper">
            <table id="addrTable" class="table text-center">
                <thead>
                <tr>
                    <th style="width:150px;"><p class="font20 marginTopFont10 fontWeight">행정동코드</p></th>
                    <th style="width:600px;"><p class="font20 marginTopFont10 fontWeight">주소명</p></th>
                </tr>
                </thead>
                <tbody id="addrList"></tbody>
            </table>
        </div>
    </div>
</div>

<form action="/createAccount" method="post" class="center" onsubmit="return submitCheck();">

    <input type="hidden" name="${_csrf.parameterName}" value="<c:out value='${_csrf.token}' />"/>

    <br><h1>회원가입</h1><br>
    <hr class="hr"><br>

    <div>
        <div>
            <div class="form-group">
                <div class="form-title">아이디</div>
                <div>
                    <input type="text" class="inputBox" id="id" name="id" value="<c:out value='${userDto.id}' />" pattern="^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{6,20}$" minlength="6" maxlength="20" title="아이디는 영어와 숫자가 포함되어야하며, 길이는 6글자이상 20글자까지 가능합니다." oninput="checkIdDuplication()" required><br>
                    <p class="error-message" id="idCheckResult"></p>
                </div>
            </div>
            <div class="form-group">
                <div class="form-title">비밀번호</div>
                <div>
                    <input type="password" class="inputBox" id="pw" name="pw" value="<c:out value='${userDto.pw}' />" pattern="^[A-Za-z\d@$!%*?&]{6,16}$" minlength="6" maxlength="16" title="비밀번호는 영어, 숫자 및 특수문자로 가능하며 최소 6자리부터 최대 16자리까지 가능합니다." oninput="validatePw()">
                    <div class="error-message" id="pwErrorMSG"></div>
                </div>

            </div>
            <div class="form-group">
                <div class="form-title">비밀번호 확인</div>
                <div>
                    <input type="password" class="inputBox" id="inputPwCheck" name="inputPwCheck" value="<c:out value='${userDto.pw}' />" pattern="^[A-Za-z\d@$!%*?&]*$" minlength="5" maxlength="16" title="동일한 비밀번호를 입력해주세요." oninput="validatePasswordMatch()">
                    <div class="error-message" id="pwDoubleCheckErrorMSG"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="form-title">이메일</div>
                <div>
                    <input type="text" class="inputBox" id="email" name="email" value="<c:out value='${userDto.email}' />" pattern="^[a-zA-Z0-9._%+\-]+@[a-zA-Z0-9.\-]+\.[a-zA-Z]{2,}$" minlength="5" maxlength="50" title="이메일 주소를 정확하게 입력해주세요." oninput="validateEmail()">
                    <div class="error-message" id="emailErrorMSG"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="form-title">주소</div>

<%--      주소 모달 작성          --%>
                <div class="addr_class">
                    <div>
                        <button class="maincolor font30" id="openModalBtn">주소 검색</button>
                    </div>
                    <div>
                        <input class="font16" id="addr_cd" name="addr_cd" type="text" hidden>
                        <input id="addr_name" name="addr_name" type="text" disabled >
                    </div>

                </div>
            </div>
            <div class="form-group" style="margin-top: 50px">
                <div class="form-title">상세주소</div>
                <div>
                    <input type="text" class="inputBox" id="addr_det" name="addr_det" value="<c:out value='${userDto.addr_det}' />" pattern="^[가-힣a-zA-Z0-9\s]+$" minlength="6" maxlength="50" title="거주하고 있는 주소를 정확하게 입력해주세요." oninput="validateAddrDet()">
                    <div class="error-message" id="addrDetailErrorMSG"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="form-title">이름</div>
                <div>
                    <input type="text" class="inputBox" id="name" name="name" value="<c:out value='${userDto.name}' />" minlength="2" maxlength="30" pattern="^[A-Za-z가-힣]+$" title="이름은 2글자 이상 30자 이하입니다." oninput="validateName()">
                    <div class="error-message" id="nameErrorMSG"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="form-title">별명</div>
                <div>
                    <input type="text" class="inputBox" id="nick" name="nick" value="<c:out value='${userDto.nick}' />" minlength="2" maxlength="20" pattern="^[A-Za-z가-힣]+$" title="별명은 2자리 이상 20자 이하입니다." oninput="validateNick()">
                    <div class="error-message" id="nickErrorMSG"></div>
                </div>
            </div>
            <div class="form-group">
                <span style="margin-left: 50px; margin-top: 15px; white-space: nowrap;">생년월일</span>
                <input placeholder="생년월일" class="inputBirthBox" type="date" id="birth" name="birth" value="<c:out value='${userDto.birth}' />" title="생년월일을 입력해주세요" style="margin-left: 105px">
                <br>
            </div>
            <div class="form-group">
                <div class="form-title">휴대전화번호</div>
                <div>
                    <input type="text" class="inputBox" id="phone_num" name="phone_num" value="<c:out value='${userDto.phone_num}' />" minlength="7" maxlength="15" pattern="^\d{7,15}$" title="하이픈(-)을 뺀 전화번호를 입력해주세요" placeholder="하이픈(-)을 뺀 전화번호를 입력해주세요" oninput="validatePhoneNum()">
                    <div class="error-message" id="phoneNumberErrorMSG"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="form-title" style="margin-top: 25px">성별</div>
                <div class="gender-container">
                    <label class="gender-option" style="margin-left: 100px;">
                        <span>남자</span>
                        <input type="radio" id="genderM" name="gender" value="M" title="성별 하나를 선택 해주세요" required>
                        <span class="custom-radio"></span>
                    </label>
                    <label class="gender-option" style="margin-left: 40px;">
                        <span>여자</span>
                        <input type="radio" id="genderF" name="gender" value="F" title="성별 하나를 선택 해주세요">
                        <span class="custom-radio"></span>
                    </label>
                </div>
            </div>
            <div class="form-group">
                <div class="form-title" style="margin-top: 25px">내외국인</div>
                <div class="gender-container">
                    <label class="gender-option" style="margin-left: 100px;">
                        <span>내국인</span>
                        <input type="radio" id="" name="foreigner" value="Y" checked>
                        <span class="custom-radio"></span>
                    </label>
                    <label class="gender-option" style="margin-left: 25px;">
                        <span>외국인</span>
                        <input type="radio" name="foreigner" value="N">
                        <span class="custom-radio"></span>
                    </label>
                </div>
            </div>
        </div>
        <hr class="hr">

        <!-- <%--        약관 확인란--%> -->
        <div class="form-group">
            <div class="form-title">이용약관동의</div>
            <div class="agree-container" style="margin-top: 5px;">
                <label class="agree-option">
                    <input type="checkbox" id="agreeToTerms" name="agreeToTerms" value="Y" required>
                    <span id="openAgreeToTerms">이용약관동의 </span><span class="agreeCheck"> (필수)</span>
                </label>
                <label class="agree-option">
                    <input type="checkbox" id="agreeCollection" name="agreeCollection" value="Y" required>
                    <span id="openAgreeCollection">개인정보 수집 및 이용 동의 </span><span class="agreeCheck"> (필수)</span>
                </label>
                <label class="agree-option">
                    <input type="checkbox" id="agreeBenefits" name="agreeBenefits" value="Y">
                    <span>무료배송 및 할인쿠폰 등 혜택/정보 수신 동의 </span><span class="agreeCheck"> (선택)</span>
                </label>
            </div>
        </div>
    </div>

    <button type="submit" class="reg_btn" style="margin-top: 80px;">회원가입</button>

</form>

<!-- 모달 창 -->
<div id="agreeToTermsView" class="modal">
    <div class="modal-content">
        <span class="close">&times;</span>
        <h2>이용약관</h2>
        <p>안녕하세요, 치즈메이트(이하 '회사')는 고객님의 이용에 불편함이 없도록 최선을 다하고 있습니다. 다음은 저희 서비스의 이용약관입니다.</p>
        <h3>제1조 (목적)</h3>
        <p>이 약관은 치즈메이트가 제공하는 모든 서비스(이하 '서비스')의 이용조건 및 절차, 이용자와 회사의 권리, 의무 및 책임사항, 기타 필요한 사항을 규정함을 목적으로 합니다.</p>
        <h3>제2조 (용어의 정의)</h3>
        <p>이 약관에서 사용하는 용어의 정의는 다음과 같습니다.</p>
        <ul>
            <li>'서비스'라 함은 회사가 제공하는 모든 온라인 서비스를 의미합니다.</li>
            <li>'이용자'라 함은 회사의 서비스에 접속하여 이 약관에 따라 회사가 제공하는 서비스를 받는 회원 및 비회원을 말합니다.</li>
            <li>'회원'이라 함은 회사에 개인정보를 제공하여 회원등록을 한 자로서, 회사가 제공하는 서비스를 지속적으로 이용할 수 있는 자를 말합니다.</li>
            <li>'비회원'이라 함은 회원으로 등록하지 않고 회사가 제공하는 서비스를 이용하는 자를 말합니다.</li>
        </ul>
        <h3>제3조 (약관의 게시 및 개정)</h3>
        <p>회사는 이 약관의 내용을 이용자가 쉽게 알 수 있도록 서비스 초기 화면에 게시합니다. 회사는 약관의 규제에 관한 법률 등 관련법을 위배하지 않는 범위에서 이 약관을 개정할 수 있습니다.</p>
        <h3>제4조 (서비스의 제공 및 변경)</h3>
        <p>회사는 이용자에게 아래와 같은 서비스를 제공합니다.</p>
        <ul>
            <li>온라인 상거래 플랫폼 서비스</li>
            <li>기타 회사가 추가 개발하거나 다른 회사와의 제휴 계약 등을 통해 이용자에게 제공하는 일체의 서비스</li>
        </ul>
        <p>회사는 서비스의 내용이 변경되는 경우, 변경사항을 이용자에게 공지합니다.</p>
        <h3>제5조 (서비스의 중단)</h3>
        <p>회사는 컴퓨터 등 정보통신설비의 보수점검, 교체 및 고장, 통신의 두절 등의 사유가 발생한 경우에는 서비스의 제공을 일시적으로 중단할 수 있습니다. 이 경우 회사는 사전 또는 사후에 이를 공지합니다.</p>
        <h3>제6조 (이용계약의 성립)</h3>
        <p>이용계약은 이용자가 약관 내용에 대해 동의하고 이용신청에 대하여 회사가 승낙함으로써 성립합니다.</p>
        <h3>제7조 (이용자의 의무)</h3>
        <p>이용자는 다음 행위를 하여서는 안 됩니다.</p>
        <ul>
            <li>신청 또는 변경 시 허위 내용의 등록</li>
            <li>타인의 정보 도용</li>
            <li>회사에 게시된 정보의 변경</li>
            <li>회사 또는 제3자의 저작권 등 지적 재산권에 대한 침해</li>
            <li>회사 또는 제3자의 명예를 손상시키거나 업무를 방해하는 행위</li>
            <li>외설 또는 폭력적인 메시지, 화상, 음성, 기타 공공질서 및 미풍양속에 반하는 정보를 공개 또는 게시하는 행위</li>
        </ul>
        <h3>제8조 (회사의 의무)</h3>
        <p>회사는 법령과 이 약관이 금지하거나 공서양속에 반하는 행위를 하지 않으며, 지속적이고 안정적으로 서비스를 제공하기 위해 최선을 다합니다.</p>
        <p>회사는 이용자가 안전하게 인터넷 서비스를 이용할 수 있도록 이용자의 개인정보 보호를 위한 보안 시스템을 갖추어야 합니다.</p>
        <h3>제9조 (개인정보 보호)</h3>
        <p>회사는 이용자의 정보수집 시 서비스 제공에 필요한 최소한의 정보를 수집합니다.</p>
        <p>회사는 이용자의 개인정보를 보호하고 존중합니다. 자세한 사항은 개인정보 처리방침을 따릅니다.</p>
        <h3>제10조 (분쟁 해결)</h3>
        <p>회사는 이용자로부터 제출되는 불만사항 및 의견을 우선적으로 처리합니다. 신속한 처리가 곤란한 경우 그 사유와 처리 일정을 이용자에게 통지합니다.</p>
        <p>본 약관에서 정하지 아니한 사항과 이 약관의 해석에 관하여는 대한민국 법률에 따릅니다.</p>
    </div>
</div>

<div id="agreeCollectionView" class="modal">
    <div class="modal-content">
        <span class="close">&times;</span>
        <h2>이용약관</h2>
        <p>안녕하세요, 치즈메이트(이하 '회사')는 고객님의 이용에 불편함이 없도록 최선을 다하고 있습니다. 다음은 저희 서비스의 이용약관입니다.</p>
        <h3>제1조 (목적)</h3>
        <p>이 약관은 치즈메이트가 제공하는 모든 서비스(이하 '서비스')의 이용조건 및 절차, 이용자와 회사의 권리, 의무 및 책임사항, 기타 필요한 사항을 규정함을 목적으로 합니다.</p>
        <h3>제2조 (용어의 정의)</h3>
        <p>이 약관에서 사용하는 용어의 정의는 다음과 같습니다.</p>
        <ul>
            <li>'서비스'라 함은 회사가 제공하는 모든 온라인 서비스를 의미합니다.</li>
            <li>'이용자'라 함은 회사의 서비스에 접속하여 이 약관에 따라 회사가 제공하는 서비스를 받는 회원 및 비회원을 말합니다.</li>
            <li>'회원'이라 함은 회사에 개인정보를 제공하여 회원등록을 한 자로서, 회사가 제공하는 서비스를 지속적으로 이용할 수 있는 자를 말합니다.</li>
            <li>'비회원'이라 함은 회원으로 등록하지 않고 회사가 제공하는 서비스를 이용하는 자를 말합니다.</li>
        </ul>
        <h3>제3조 (약관의 게시 및 개정)</h3>
        <p>회사는 이 약관의 내용을 이용자가 쉽게 알 수 있도록 서비스 초기 화면에 게시합니다. 회사는 약관의 규제에 관한 법률 등 관련법을 위배하지 않는 범위에서 이 약관을 개정할 수 있습니다.</p>
        <h3>제4조 (서비스의 제공 및 변경)</h3>
        <p>회사는 이용자에게 아래와 같은 서비스를 제공합니다.</p>
        <ul>
            <li>온라인 상거래 플랫폼 서비스</li>
            <li>기타 회사가 추가 개발하거나 다른 회사와의 제휴 계약 등을 통해 이용자에게 제공하는 일체의 서비스</li>
        </ul>
        <p>회사는 서비스의 내용이 변경되는 경우, 변경사항을 이용자에게 공지합니다.</p>
        <h3>제5조 (서비스의 중단)</h3>
        <p>회사는 컴퓨터 등 정보통신설비의 보수점검, 교체 및 고장, 통신의 두절 등의 사유가 발생한 경우에는 서비스의 제공을 일시적으로 중단할 수 있습니다. 이 경우 회사는 사전 또는 사후에 이를 공지합니다.</p>
        <h3>제6조 (이용계약의 성립)</h3>
        <p>이용계약은 이용자가 약관 내용에 대해 동의하고 이용신청에 대하여 회사가 승낙함으로써 성립합니다.</p>
        <h3>제7조 (이용자의 의무)</h3>
        <p>이용자는 다음 행위를 하여서는 안 됩니다.</p>
        <ul>
            <li>신청 또는 변경 시 허위 내용의 등록</li>
            <li>타인의 정보 도용</li>
            <li>회사에 게시된 정보의 변경</li>
            <li>회사 또는 제3자의 저작권 등 지적 재산권에 대한 침해</li>
            <li>회사 또는 제3자의 명예를 손상시키거나 업무를 방해하는 행위</li>
            <li>외설 또는 폭력적인 메시지, 화상, 음성, 기타 공공질서 및 미풍양속에 반하는 정보를 공개 또는 게시하는 행위</li>
        </ul>
        <h3>제8조 (회사의 의무)</h3>
        <p>회사는 법령과 이 약관이 금지하거나 공서양속에 반하는 행위를 하지 않으며, 지속적이고 안정적으로 서비스를 제공하기 위해 최선을 다합니다.</p>
        <p>회사는 이용자가 안전하게 인터넷 서비스를 이용할 수 있도록 이용자의 개인정보 보호를 위한 보안 시스템을 갖추어야 합니다.</p>
        <h3>제9조 (개인정보 보호)</h3>
        <p>회사는 이용자의 정보수집 시 서비스 제공에 필요한 최소한의 정보를 수집합니다.</p>
        <p>회사는 이용자의 개인정보를 보호하고 존중합니다. 자세한 사항은 개인정보 처리방침을 따릅니다.</p>
        <h3>제10조 (분쟁 해결)</h3>
        <p>회사는 이용자로부터 제출되는 불만사항 및 의견을 우선적으로 처리합니다. 신속한 처리가 곤란한 경우 그 사유와 처리 일정을 이용자에게 통지합니다.</p>
        <p>본 약관에서 정하지 아니한 사항과 이 약관의 해석에 관하여는 대한민국 법률에 따릅니다.</p>
        <p>이 약관은 2024년 5월 24일부터 적용됩니다.</p>
    </div>
</div>

</body>
</html>

<script>
    document.addEventListener('DOMContentLoaded', function () {
        var modalAgreeToTerms = document.getElementById("agreeToTermsView");
        var checkboxAgreeToTerms = document.getElementById("agreeToTerms");
        var btnAgreeToTerms = document.getElementById("openAgreeToTerms");

        var modalAgreeCollection = document.getElementById("agreeCollectionView");
        var checkboxAgreeCollection = document.getElementById("agreeCollection");
        var btnAgreeCollection = document.getElementById("openAgreeCollection");

        var closeButtons = document.getElementsByClassName("close");

        btnAgreeToTerms.onclick = function () {
            var agreeToTermsCheckbox = document.getElementById("agreeToTerms");
            if (!agreeToTermsCheckbox.checked) {
                modalAgreeToTerms.style.display = "block";
            }
        }

        checkboxAgreeToTerms.onclick = function () {
            var agreeToTermsCheckbox = document.getElementById("agreeToTerms");
            if (agreeToTermsCheckbox.checked) {
                modalAgreeToTerms.style.display = "block";
            }
        }

        btnAgreeCollection.onclick = function () {
            var agreeCollectionCheckbox = document.getElementById("agreeCollection");
            if (!agreeCollectionCheckbox.checked) {
                modalAgreeCollection.style.display = "block";
            }
        }

        checkboxAgreeCollection.onclick = function () {
            var agreeCollectionCheckbox = document.getElementById("agreeCollection");
            if (agreeCollectionCheckbox.checked) {
                modalAgreeCollection.style.display = "block";
            }
        }

        for (var i = 0; i < closeButtons.length; i++) {
            closeButtons[i].onclick = function () {
                modalAgreeToTerms.style.display = "none";
                modalAgreeCollection.style.display = "none";
            }
        }

        window.onclick = function (event) {
            if (event.target == modalAgreeToTerms) {
                modalAgreeToTerms.style.display = "none";
            }
            else if (event.target == modalAgreeCollection) {
                modalAgreeCollection.style.display = "none";
            }
        }
    });


    const openModalBtn = document.getElementById("openModalBtn");
    const modal = document.querySelector(".SaleModal");
    const overlay = modal.querySelector(".sale_modal_overlay");
    const closeModalBtn = document.getElementById("closeModalBtn");
    const openModal = (event) => {
        event.preventDefault(); // form의 기본 동작을 막음
        modal.classList.remove("SaleHidden");
    }
    const closeModal = () => {
        event.preventDefault(); // form의 기본 동작을 막음
        modal.classList.add("SaleHidden");
    }
    document.addEventListener("keydown", (event) => {
        if (event.key === "Escape" || event.keyCode === 27) {
            closeModal();
        }
    });
    overlay.addEventListener("click", closeModal);
    closeModalBtn.addEventListener("click", closeModal);
    openModalBtn.addEventListener("click", openModal);

    $("#saleSearchInput").on("keydown", function (event) {
        // 엔터 키(키 코드 13)가 눌렸는지 확인
        if (event.which === 13 || event.keycode === 13) {
            event.preventDefault(); // 기본 엔터 동작(폼 제출 등)을 막기 위해 추가

            let searchLetter = $(this).val();
            $.ajax({
                type: "POST",
                url: "/sale/searchLetter",
                data: {searchLetter: searchLetter},
                dataType: "json",
                success: function (data) {
                    // 검색 결과를 처리하여 addrTable에 추가
                    $("#addrList").empty(); // 기존 내용 초기화
                    if (data.length > 0) {
                        data.forEach(function (addr) {
                            let row = $("<tr class='sale-addr-tr'>");
                            row.append($("<td>").html("<p class='font20 marginTopFont10'>" + addr.addr_cd + "</p>")); // 행정구역 코드
                            row.append($("<td>").html("<p class='font20 marginTopFont10'>" + addr.addr_name + "</p>")); // 주소명
                            $("#addrList").append(row);
                        });
                    } else {
                        $("#addrTable").append("<tr><td colspan='2'>검색 결과가 없습니다.</td></tr>");
                    }
                },
                error: function (xhr, status, error) {
                    $("#addrTable").append("<tr><td colspan='2'>검색 결과가 없습니다.</td></tr>");
                    alert("주소 검색에 실패하였습니다.");
                }
            });
        }
    });

    // 주소를 클릭했을 때의 이벤트 핸들러
    $("#addrList").on("click", "tr", function () {
        // 클릭한 행에서 주소 코드와 주소명을 가져옴
        let addrCode = $(this).find("td:eq(0)").text(); // 첫 번째 td 열의 텍스트 (주소 코드)
        let addrName = $(this).find("td:eq(1)").text(); // 두 번째 td 열의 텍스트 (주소명)

        // pickup_addr input에 선택한 주소 정보를 추가
        $("#addr_cd").val(addrCode);
        $("#addr_name").val(addrName);

        // 모달 닫기
        closeModal();
    });

    // input에서 Enter 키 입력 허용
    $(document).on("keydown", "input", function (event) {
        // 현재 포커스된 요소가 input인지 확인
        if ($(this).is("input")) {
            if (event.keyCode === 13) {
                event.preventDefault(); // Enter 키의 기본 동작 막기
            }
        }
    });

    let check = true;

    // *** 아이디 중복 확인(Ajax) ***
    function checkIdDuplication() {
        let inputId = document.getElementById("id").value;
        let resultElement = document.getElementById("idCheckResult");

        if (inputId.length > 5) {
            fetch('/checkIdDuplication?id=' + encodeURIComponent(inputId))
                .then(response => response.text())
                .then(data => {
                    let trimmedData = data.trim();
                    resultElement.innerText = trimmedData;
                    if (trimmedData === "아이디에 띄어쓰기를 넣을 수 없습니다.") {
                        resultElement.style.color = 'red';
                        check = false;
                    } else if (trimmedData === "이미 존재하는 아이디입니다.") {
                        resultElement.style.color = 'red';
                        check = false;
                    } else if (trimmedData === "아이디에는 반드시 영어와 숫자가 섞여있어야 합니다.") {
                        resultElement.style.color = 'red';
                        check = false;
                    } else if (trimmedData === "아이디에 한글 또는 특수문자는 불가능합니다.") {
                        resultElement.style.color = 'red';
                        check = false;
                    } else if (trimmedData === "사용 가능한 아이디입니다.") {
                        document.getElementById("id").style.borderColor = "green";
                        resultElement.style.color = 'green';
                        check = true;
                    }
                    else {
                        resultElement.style.color = 'green';
                    }
                });
        } else {
            document.getElementById("id").style.borderColor = "red";
            document.getElementById("idCheckResult").innerText = "아이디는 6글자 이상 가능합니다.";
            resultElement.style.color = 'red';
        }
    }

    function validatePw() {
        var password = document.getElementById('pw').value;
        var errorMSG = document.getElementById('pwErrorMSG');
        var patternLength = /^.{6,16}$/;
        var patternChar = /^(?=.*[A-Za-z])(?=.*\d).*$/;
        var patternSpace = /\s/;


        if(!patternLength.test(password)) {
            errorMSG.textContent = '비밀번호는 최소 6자리부터 가능합니다.';
            document.getElementById("pw").style.borderColor = "red";
        }
        else if(!patternChar.test(password)) {
            errorMSG.textContent = '비밀번호는 반드시 영어와 숫자가 섞여있어야 합니다.';
            document.getElementById("pw").style.borderColor = "red";
        }
        else if(patternSpace.test(password)) {
            errorMSG.textContent = '비밀번호에 공백은 넣을 수 없습니다.';
            document.getElementById("pw").style.borderColor = "red";
        }
        else {
            document.getElementById("pw").style.borderColor = "";
            errorMSG.textContent = '';
        }
    }

    function validatePasswordMatch() {
        var password = document.getElementById('pw').value;
        var confirmPassword = document.getElementById('inputPwCheck').value;
        var errorMSG = document.getElementById('pwDoubleCheckErrorMSG');

        if(confirmPassword === "") {
            errorMSG.textContent = '비밀번호를 한 번 더 입력해주세요.';
            document.getElementById("inputPwCheck").style.borderColor = "red";
        }
        else if (password !== confirmPassword) {
            errorMSG.textContent = '비밀번호가 일치하지 않습니다.';
            document.getElementById("inputPwCheck").style.borderColor = "red";
        } else {
            errorMSG.textContent = '';
            document.getElementById("inputPwCheck").style.borderColor = "";
        }
    }

    function validateEmail() {
        var email = document.getElementById("email").value;
        var errorMSG = document.getElementById("emailErrorMSG");
        var pattern = /^[a-zA-Z0-9._%+\-]+@[a-zA-Z0-9.\-]+\.[a-zA-Z]{2,}$/;

        if(email === "") {
            document.getElementById("email").style.borderColor = "red";
            errorMSG.textContent = "이메일을 입력해 주세요";
        }
        if(!pattern.test(email)) {
            document.getElementById("email").style.borderColor = "red";
            errorMSG.textContent = "이메일 형식을 맞춰주세요";
        }
        else {
            document.getElementById("email").style.borderColor = "";
            errorMSG.textContent = "";
        }
    }

    function validateAddrDet() {
        var addr_det = document.getElementById("addr_det").value;
        var errorMSG = document.getElementById("addrDetailErrorMSG");
        var pattern = /^.{6,}$/;

        if(addr_det === "") {
            document.getElementById("addr_det").style.borderColor = "red";
            errorMSG.textContent = "상세주소를 입력해 주세요";
        }
        else if(!pattern.test(addr_det)) {
            document.getElementById("addr_det").style.borderColor = "red";
            errorMSG.textContent = "상세주소를 정확히 입력해 주세요";
        }
        else {
            document.getElementById("addr_det").style.borderColor = "";
            errorMSG.textContent = "";
        }
    }

    function validateName() {
        var name = document.getElementById("name").value;
        var errorMSG = document.getElementById("nameErrorMSG");
        var pattern = /^.{2,}$/;

        if(name === "") {
            document.getElementById("name").style.borderColor = "red";
            errorMSG.textContent = "이름을 입력해 주세요";
        }
        if(!pattern.test(name)) {
            document.getElementById("name").style.borderColor = "red";
            errorMSG.textContent = "이름을 입력해 주세요";
        }
        else {
            document.getElementById("name").style.borderColor = "";
            errorMSG.textContent = "";
        }
    }

    function validateNick() {
        var nick = document.getElementById("nick").value;
        var errorMSG = document.getElementById("nickErrorMSG");
        var pattern = /^.{2,}$/;

        if(nick === "") {
            document.getElementById("nick").style.borderColor = "red";
            errorMSG.textContent = "닉네임을 입력해 주세요";
        }
        if(!pattern.test(nick)) {
            document.getElementById("nick").style.borderColor = "red";
            errorMSG.textContent = "닉네임은 2글자 이상입니다.";
        }
        else {
            document.getElementById("nick").style.borderColor = "";
            errorMSG.textContent = "";
        }
    }

    function validatePhoneNum() {
        var input = document.getElementById('phone_num');
        input.value = input.value.replace(/[^\d]/g, '');

        var phoneNum = document.getElementById("phone_num").value;
        var errorMSG = document.getElementById("phoneNumberErrorMSG");
        var pattern = /^\d{7,15}$/;

        if(phoneNum === "") {
            document.getElementById("phone_num").style.borderColor = "red";
            errorMSG.textContent = "전화번호를 입력해 주세요";
        }
        if(!pattern.test(phoneNum)) {
            document.getElementById("phone_num").style.borderColor = "red";
            errorMSG.textContent = "전화번호를 정확하게 입력해주세요.";
        }
        else {
            document.getElementById("phone_num").style.borderColor = "";
            errorMSG.textContent = "";
        }
    }

    // *** 생년월일 입력시 미래 날짜 사용 불가능 ***
    document.addEventListener('DOMContentLoaded', function() {
        let today = new Date();
        let dd = today.getDate();
        let mm = today.getMonth() + 1;
        let yyyy = today.getFullYear();

        if (dd < 10) {
            dd = '0' + dd;
        }
        if (mm < 10) {
            mm = '0' + mm;
        }
        today = yyyy + '-' + mm + '-' + dd;

        let minDate = '1950-01-01';

        let birthInput = document.getElementById("birth");
        birthInput.setAttribute("max", today);
        birthInput.setAttribute("min", minDate);

        // Trigger date picker on input box click
        birthInput.addEventListener('click', function() {
            this.showPicker();
        });
    });

    // *** 확인 버튼 누를 시 부적합한 입력을 화면에 표시 ***
    function submitCheck() {

        let inputId = document.getElementById("id").value;
        let inputPw = document.getElementById("pw").value;
        let inputPwCheck = document.getElementById("inputPwCheck").value;
        let inputEmail = document.getElementById("email").value;
        let inputAddress = document.getElementById("addr_det").value.trim();
        let inputName = document.getElementById("name").value;
        let inputNick = document.getElementById("nick").value;
        let inputBirth = document.getElementById("birth").value;
        let inputPhoneNum = document.getElementById("phone_num").value;
        let resultElement = document.getElementById("idCheckResult");

        if(resultElement.style.color === 'red') {
            document.getElementById("id").style.borderColor = 'red';
            check = false;
        }

        if(inputId === "" || inputId.trim().length !== inputId.length) {
            document.getElementById("id").style.borderColor = 'red';
            check = false;
        } else {
            document.getElementById("id").style.borderColor = '';
        }

        if(inputPw === "") {
            document.getElementById("pw").style.borderColor = 'red';
            check = false;
        } else if(inputPw !== inputPwCheck ) {
            document.getElementById("inputPwCheck").style.borderColor = 'red';
            check = false;
        } else {
            document.getElementById("pw").style.borderColor = '';
            document.getElementById("inputPwCheck").style.borderColor = '';
        }

        if(inputEmail === "") {
            document.getElementById("email").style.borderColor = 'red';
            check = false;
        } else {
            document.getElementById("email").style.borderColor = '';
        }

        if(inputAddress.trim() === "" || inputAddress.replace(/\s/g, "").length < 6) {
            document.getElementById("addr_det").style.borderColor = 'red';
            check = false;
        } else {
            document.getElementById("addr_det").style.borderColor = '';
        }

        if(inputName === "") {
            document.getElementById("name").style.borderColor = 'red';
            check = false;
        } else {
            document.getElementById("name").style.borderColor = '';
        }

        if(inputNick === "") {
            document.getElementById("nick").style.borderColor = 'red';
            check = false;
        } else {
            document.getElementById("nick").style.borderColor = '';
        }

        if(inputBirth === "") {
            document.getElementById("birth").style.borderColor = 'red';
            check = false;
        } else {
            document.getElementById("birth").style.borderColor = '';
        }

        if(inputPhoneNum === "") {
            document.getElementById("phone_num").style.borderColor = 'red';
            check = false;
        } else {
            document.getElementById("phone_num").style.borderColor = '';
        }
        console.log(check);

        if(!check) {
            alert("입력한 정보를 다시 확인해주세요.");
        } else {
            alert("회원가입이 완료되었습니다.")
        }

        return check;
    }

</script>
