package team.cheese.Domain.Event;


public class PageHanddler {
	private int MAXCONTENT = 20;
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
	
	PageHanddler(int maxnum, int nowpage){
		this.maxnum=maxnum;
		this.Max_page_num=(maxnum/MAXCONTENT)==0?(maxnum/MAXCONTENT):(maxnum/MAXCONTENT)+1;
		this.nowpage = nowpage;
		this.startBno = (this.nowpage -1) * MAXCONTENT+1;
	}
	
	@Override
	public String toString() {
		return "PageHanddler [MAXCONTENT=" + MAXCONTENT + ", maxlist=" + maxlist + ", Max_page_num=" + Max_page_num
				+ ", nowpage=" + nowpage + ", maxnum=" + maxnum + ", startBno=" + startBno+"]";
	}

	public void setMaxList(int listnum) {
		this.maxlist = listnum;
	}
	public void setMaxContent(int maxcontents) {
		this.MAXCONTENT = maxcontents;
	}
	public int getstartPage() {
		return ((this.nowpage-1) /maxlist)*maxlist+1;
	}
	public int getEndPage() {
		return (this.getstartPage()+maxlist-1)<Max_page_num?(this.getstartPage()+maxlist-1):Max_page_num;
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
