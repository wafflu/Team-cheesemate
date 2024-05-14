package team.cheese.entity;

import java.util.Objects;

public class PageHandler {
    private int startPage; // 시작 페이지
    private int endPage; // 마지막 페이지
    private int totalCnt; // 전체 게시글 수
    private int totalPage; // 전체페이지 수
    private int page; // 현재 페이지
    private int navSize = 10; // 네비 사이즈
    private int pageSize; // 보여줄 게시글 수

    private boolean prevPage; // 이전 페이지로 이동할 수 있는지 여부

    private boolean nextPage; // 다음 페이지로 이동할 수 있는지 여부

    public PageHandler(int totalCnt, int page, int pageSize) {
        this.page = page;
        this.totalPage =  (totalCnt/pageSize) + (totalCnt%pageSize > 0 ? 1 : 0); // 전체 페이지 수
        this.startPage = ((page/navSize) * 10) + 1;
        if(startPage > totalPage) {
            startPage = 1;
        }
        this.endPage = (this.startPage + (navSize - 1)) > totalPage ? totalPage : (this.startPage + (navSize - 1));
    }

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public int getTotalCnt() {
        return totalCnt;
    }

    public void setTotalCnt(int totalCnt) {
        this.totalCnt = totalCnt;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getNavSize() {
        return navSize;
    }

    public void setNavSize(int navSize) {
        this.navSize = navSize;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public boolean isPrevPage() {
        return prevPage;
    }

    public void setPrevPage(boolean prevPage) {
        this.prevPage = prevPage;
    }

    public boolean isNextPage() {
        return nextPage;
    }

    public void setNextPage(boolean nextPage) {
        this.nextPage = nextPage;
    }

    @Override
    public String toString() {
        return "PageHandler{" +
                "startPage=" + startPage +
                ", endPage=" + endPage +
                ", totalCnt=" + totalCnt +
                ", totalPage=" + totalPage +
                ", page=" + page +
                ", navSize=" + navSize +
                ", pageSize=" + pageSize +
                ", prevPage=" + prevPage +
                ", nextPage=" + nextPage +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PageHandler that = (PageHandler) o;
        return startPage == that.startPage && endPage == that.endPage && totalCnt == that.totalCnt && totalPage == that.totalPage && page == that.page && navSize == that.navSize && pageSize == that.pageSize && prevPage == that.prevPage && nextPage == that.nextPage;
    }

    @Override
    public int hashCode() {
        return Objects.hash(startPage, endPage, totalCnt, totalPage, page, navSize, pageSize, prevPage, nextPage);
    }
}
