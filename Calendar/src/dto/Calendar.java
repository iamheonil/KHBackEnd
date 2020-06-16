package dto;

import java.util.Date;

public class Calendar {

	private int userid;
	private String pname;
	private Date ptime;
	private int ptime_end;

	@Override
	public String toString() {
		return "Calendar [userid=" + userid + ", pname=" + pname + ", ptime=" + ptime + ", ptime_end=" + ptime_end
				+ "]";
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public Date getPtime() {
		return ptime;
	}

	public void setPtime(Date ptime) {
		this.ptime = ptime;
	}

	public int getPtime_end() {
		return ptime_end;
	}

	public void setPtime_end(int ptime_end) {
		this.ptime_end = ptime_end;
	}

}
