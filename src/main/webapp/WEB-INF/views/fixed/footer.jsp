<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<footer id="footer">
    <div id="footersub">
        <ul>
            <li class="cheesecompany"><b>(주)치즈마켓 사업자 정보</b></li>
            <li>
                <ul class="footerulflex">
                    <li>(주)치즈마켓</li>
                    <li>대표자 : 치즈메이트</li>
                </ul>
            </li>
            <li>사업자 등록번호 : 123-45-56789</li>
            <li>통신판매신고번호 : 제2024-서울강남-0001호</li>
            <li>주소 : 서울특별시 강남구 강남대로 364 미왕빌딩 10층</li>
            <li>대표번호 : 1234-1234</li>
            <li>메일 : cheezmate@cheezmate.co.kr</li>
            <li>호스팅제공자 : 개인PC</li>
            <li>
                <ul class="footerulflex">
                    <li><b style="color:#5a616b;">Contact</b></li>
                    <li>치즈마켓 VIP 회원 신청</li>
                    <li>치즈마켓 광고 문의</li>
                </ul>
            </li>
        </ul>

        <div id="footergflexgroup1">
            <ul class="footerulflex">
                <li>이용약관</li>
                <li><b>개인정보처리방침</b></li>
                <li>분쟁처리절차</li>
                <li>청소년보호정책</li>
                <li>사업자정보확인</li>
            </ul>
            <ul class="footerulflex">
                <li>게시글 수집 및 이용 안내</li>
                <li>치즈마켓 고객센터</li>
            </ul>
        </div>


        <p class="footermsg">"치즈마켓" 상점의 판매상품을 제외한 모든 상품들에 대하여, (주)치즈마켓은 통신판매중개자로서 거래 당사자가 아니며 판매 회원과 구매 회원 간의 상품거래 정보 및 거래에 관여하지 않고, 어떠한 의무와 책임도 부담하지 않습니다.</p>
    </div>
</footer>

<script>
    document.addEventListener("DOMContentLoaded", function() {
        let imgInfo = cssImage.getImgInfo();
        $("#logoimg").attr("src", "/img/display?fileName=" + imgInfo['logo']);
        $("#chaticon").attr("src", "/img/display?fileName=" + imgInfo['chat']);
        $("#storeicon").attr("src", "/img/display?fileName=" + imgInfo['store']);
        $("#usericon").attr("src", "/img/display?fileName=" + imgInfo['person']);
        $("#search").css("background-image", "url('/img/display?fileName=" + imgInfo['search'] + "')");
    });
</script>


</body>
</html>
