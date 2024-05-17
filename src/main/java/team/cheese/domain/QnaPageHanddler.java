package team.cheese.domain;


public class QnaPageHanddler {
	private int MAXCONTENT = 8;
	private int maxlist = 10;
	private int Max_page_num;
	private int nowpage;
	private int maxnum;
	public int startBno;
	
	public int getStartBno() {
		return startBno;
	}

	public void setStartBno(int startBno) {
		this.startBno = startBno;
	}
	
	public QnaPageHanddler(int maxnum, int nowpage){
		this.maxnum=maxnum;
		this.Max_page_num=(maxnum%MAXCONTENT)==0?(maxnum/MAXCONTENT):(maxnum/MAXCONTENT)+1;
		this.nowpage = nowpage;
		this.startBno = (this.nowpage -1) * MAXCONTENT+1;
	}
	
	@Override
	public String toString() {
		return "PageHanddler [MAXCONTENT=" + MAXCONTENT + ", maxlist=" + this.maxlist + ", Max_page_num=" + Max_page_num
				+ ", nowpage=" + this.nowpage + ", maxnum=" + this.maxnum + ", startBno=" + this.startBno+"]";
	}

	public int getMAXCONTENT() {
		return MAXCONTENT;
	}

	public void setMAXCONTENT(int MAXCONTENT) {
		this.MAXCONTENT = MAXCONTENT;
	}

	public int getMaxlist() {
		return maxlist;
	}

	public void setMaxlist(int maxlist) {
		this.maxlist = maxlist;
	}

	public int getMax_page_num() {
		return Max_page_num;
	}

	public void setMax_page_num(int max_page_num) {
		Max_page_num = max_page_num;
	}

	public int getNowpage() {
		return nowpage;
	}

	public void setNowpage(int nowpage) {
		this.nowpage = nowpage;
	}

	public int getMaxnum() {
		return maxnum;
	}

	public void setMaxnum(int maxnum) {
		this.maxnum = maxnum;
	}

	public void setMaxList(int listnum) {
		this.maxlist = listnum;
	}
	public void setMaxContent(int maxcontents) {
		this.MAXCONTENT = maxcontents;
	}
	public int getstartPage() {
		return ((this.nowpage-1) /this.maxlist)*this.maxlist+1;
	}
	public int getEndPage() {
		return (this.getstartPage()+maxlist-1)<Max_page_num?(this.getstartPage()+this.maxlist-1):Max_page_num;
	}
	public int getMaxPageNum() {
		return Max_page_num;
	}
	public boolean isNotFirstPage() {
		return this.getstartPage()!=1;
	}
	public boolean isNotLastPage() {
		return this.getEndPage()!=this.getMaxPageNum();
	}
}
