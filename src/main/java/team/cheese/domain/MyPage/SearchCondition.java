package team.cheese.domain.MyPage;

import java.util.Objects;

public class SearchCondition {

    private Integer page; // 현재 페이지
    private Integer pageSize = 5; // 페이지 크기
    private String option = ""; // 판매내역 or 구매내역
    private String sal_s_cd=""; // 예약중/판매중/거래완료
    private String keyword =""; // 상품명검색
    private String ur_id; // 회원 ID
    public SearchCondition(
    ){}

    public SearchCondition(Integer page, Integer pageSize, String option, String sal_s_cd, String keyword, String ur_id, Integer offset) {
        this.page = page;
        this.pageSize = pageSize;
        this.option = option;
        this.sal_s_cd = sal_s_cd;
        this.keyword = keyword;
        this.ur_id = ur_id;
    }

    @Override
    public String toString() {
        return "SearchCondition{" +
                "page=" + page +
                ", pageSize=" + pageSize +
                ", option='" + option + '\'' +
                ", sal_s_cd='" + sal_s_cd + '\'' +
                ", keyword='" + keyword + '\'' +
                ", ur_id='" + ur_id + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchCondition that = (SearchCondition) o;
        return Objects.equals(page, that.page) && Objects.equals(pageSize, that.pageSize) && Objects.equals(option, that.option) && Objects.equals(sal_s_cd, that.sal_s_cd) && Objects.equals(keyword, that.keyword) && Objects.equals(ur_id, that.ur_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(page, pageSize, option, sal_s_cd, keyword, ur_id);
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public String getSal_s_cd() {
        return sal_s_cd;
    }

    public void setSal_s_cd(String sal_s_cd) {
        this.sal_s_cd = sal_s_cd;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getUr_id() {
        return ur_id;
    }

    public void setUr_id(String ur_id) {
        this.ur_id = ur_id;
    }

    public Integer getOffset() {
         return (page-1)*pageSize;
    }

}
