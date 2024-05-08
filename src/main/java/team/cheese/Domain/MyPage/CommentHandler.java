//package team.cheese.Domain.MyPage;
//
//import java.util.Objects;
//
//public class CommentHandler {
//
//        private int navSize = 10; // 내비게이션 크기
//        private Integer totalCnt; // 총 게시물 갯수
//        private Integer page; // 현재 페이지
//        private Integer pageSize; // 페이지 크기
//        private int totalPage; // 총 페이지 갯수
//        private int beginPage; // 제일 첫번쨰 페이지
//        private int endPage; // 제일 마지막 페이지
//        private boolean prevPage; // 이전페이지로 이동할수있는지
//        private boolean nextPage; // 다음페이지로 이동할수있는지
//        public PageHandler(){} // 기본 생성자
//
//        public PageHandler(Integer totalCnt,Integer page) {
//            this(totalCnt,page,10);
//        }
//
//        public PageHandler(Integer totalCnt, SearchCondition sc) {
//            this.totalCnt = totalCnt;
//            this.sc = sc;
//            this.totalPage = (int) Math.ceil(totalCnt/(double)sc.getPageSize());
//            this.beginPage = ((sc.getPage()-1)/navSize)*navSize+1;
//            this.endPage = Math.min(beginPage+(navSize-1),totalPage);
//            this.prevPage = beginPage != 1;
//            this.nextPage = endPage != totalPage;
//            doPaging();
//        }
//        public void doPaging() {
//
//        }
//        void print() {
//            System.out.println("page = " + sc.getPage());
//            System.out.println(prevPage ? "[PREV]":"");
//            for(int i =beginPage; i<=endPage; i++) {
//                System.out.println(i+" ");
//            }
//            System.out.println(nextPage ? "[NEXT]":"");
//        }
//
//
//        @Override
//        public boolean equals(Object o) {
//            if (this == o) return true;
//            if (o == null || getClass() != o.getClass()) return false;
//            PageHandler that = (PageHandler) o;
//            return totalCnt == that.totalCnt && sc.getPage() == that.sc.getPage() && sc.getPageSize() == that.sc.getPageSize();
//        }
//
//        @Override
//        public int hashCode() {
//            return Objects.hash(totalCnt, sc.getPage(), sc.getPageSize());
//        }
//
//        @Override
//        public String toString() {
//            return "PageHandler{" +
//                    "navSize=" + navSize +
//                    ", totalCnt=" + totalCnt +
//                    ", sc ="+sc+
//                    ", totalPage=" + totalPage +
//                    ", beginPage=" + beginPage +
//                    ", endPage=" + endPage +
//                    ", prevPage=" + prevPage +
//                    ", nextPage=" + nextPage +
//                    '}';
//        }
//
//
//
//        public int getNavSize() {
//            return navSize;
//        }
//
//        public void setNavSize(int navSize) {
//            this.navSize = navSize;
//        }
//
//        public int getTotalCnt() {
//            return totalCnt;
//        }
//
//        public void setTotalCnt(int totalCnt) {
//            this.totalCnt = totalCnt;
//        }
//
//    //    public Integer getPage() {
//    //        return page;
//    //    }
//    //
//    //    public void setPage(Integer page) {
//    //        this.page = page;
//    //    }
//    //
//    //    public Integer getPageSize() {
//    //        return pageSize;
//    //    }
//    //
//    //    public void setPageSize(Integer pageSize) {
//    //        this.pageSize = pageSize;
//    //    }
//
//        public int getTotalPage() {
//            return totalPage;
//        }
//
//        public void setTotalPage(int totalPage) {
//            this.totalPage = totalPage;
//        }
//
//        public int getBeginPage() {
//            return beginPage;
//        }
//
//        public void setBeginPage(int beginPage) {
//            this.beginPage = beginPage;
//        }
//
//        public int getEndPage() {
//            return endPage;
//        }
//
//        public void setEndPage(int endPage) {
//            this.endPage = endPage;
//        }
//
//        public boolean isPrevPage() {
//            return prevPage;
//        }
//
//        public void setPrevPage(boolean prevPage) {
//            this.prevPage = prevPage;
//        }
//
//        public boolean isNextPage() {
//            return nextPage;
//        }
//
//        public void setNextPage(boolean nextPage) {
//            this.nextPage = nextPage;
//        }
//    }
//
//
