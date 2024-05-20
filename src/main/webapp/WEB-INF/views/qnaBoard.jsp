<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="fixed/header.jsp"%>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <style>
        body {
            display: flex;
            flex-direction: column;
            min-height: 100vh;
            margin: 0;
        }
        header, footer {
            background-color: #f8f9fa;
            padding: 1rem;
        }
        .container {
            display: flex;
            flex: 1;
            max-width: 1200px;
            margin: 0 auto;
        }
        aside {
            width: 200px;
            background-color: #eee;
            padding: 1rem;
        }
        main {
            flex: 1;
            padding: 1rem;
        }
        .header {
            margin-bottom: 20px;
        }
        .contents {
            margin-bottom: 20px;
        }
        .buttons {
            margin-bottom: 20px;
        }
    </style>

<div class="container">
    <aside>
        <h3>고객센터</h3>
        <ul class="QnaSide">
            <li><a href="<c:url value='/faq/list'/>">FAQ</a></li>
            <li><a href="<c:url value='/qna/new'/>">1:1 문의하기</a></li>
            <li><a href="<c:url value='/qna/list'/>">나의 문의내역</a></li>
        </ul>
    </aside>
    <main>
        <form id="modifyForm" action="/qna/modify" method="post" onsubmit="return validateForm();">
            <input type="hidden" name="no" value="${qna.no}">
            <div class="header">
                <h1 contenteditable="false" id="title">${qna.title}</h1>
                <input type="hidden" name="title" id="hiddenTitle" value="${qna.title}">
                <p><strong>작성일:</strong> <c:out value="${qna.r_date}"/></p>
            </div>
            <div class="contents">
                <textarea name="contents" id="content" rows="10" readonly><c:out value="${qna.contents}"/></textarea>
            </div>
            <div class="buttons">
                <button type="button" onclick="modify()" id="editBtn">수정</button>
                <button type="submit" id="saveBtn" style="display:none;">저장</button>
            </div>
        </form>
        <c:if test="${qna.q_s_cd != 'Q001Y'}">
            <form action="/qna/delete" method="post" onsubmit="return confirmDelete();">
                <input type="hidden" name="no" value="${qna.no}" />
                <button type="submit">삭제</button>
            </form>
        </c:if>
    </main>
</div>
<footer>
    <!-- 푸터 내용 추가 -->
</footer>

<script>
    function modify() {
        $('#title').attr('contenteditable', 'true');
        $('#title').on('input', function() {
            $('#hiddenTitle').val($(this).text());
        });
        $('#content').removeAttr('readonly');
        $('#editBtn').hide();
        $('#saveBtn').show();
    }

    function validateForm() {
        var title = $('#hiddenTitle').val().trim();
        var content = $('#content').val().trim();

        if (!title) {
            alert('제목을 입력해주세요.');
            return false;
        }
        if (!content || content.length < 20) {
            alert('내용을 20자 이상 입력해주세요.');
            return false;
        }
        return true; // 모든 검사가 통과하면 폼 제출을 허용
    }

    function confirmDelete() {
        return confirm('정말 삭제하시겠습니까?');
    }
</script>
<%@include file="fixed/footer.jsp"%>
