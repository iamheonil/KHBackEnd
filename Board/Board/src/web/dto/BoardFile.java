package web.dto;

import java.util.Date;

public class BoardFile {
	private int fileno;
	private int boardno;
	private String originname;
	private String storedname;
	private int filesize;
	private Date writedate;
	
	@Override
	public String toString() {
		return "BoardFile [fileno=" + fileno + ", boardno=" + boardno + ", originname=" + originname + ", storedname="
				+ storedname + ", filesize=" + filesize + ", writedate=" + writedate + "]";
	}
	
	public int getFileno() {
		return fileno;
	}
	public void setFileno(int fileno) {
		this.fileno = fileno;
	}
	public int getBoardno() {
		return boardno;
	}
	public void setBoardno(int boardno) {
		this.boardno = boardno;
	}
	public String getOriginname() {
		return originname;
	}
	public void setOriginname(String originname) {
		this.originname = originname;
	}
	public String getStoredname() {
		return storedname;
	}
	public void setStoredname(String storedname) {
		this.storedname = storedname;
	}
	public int getFilesize() {
		return filesize;
	}
	public void setFilesize(int filesize) {
		this.filesize = filesize;
	}
	public Date getWritedate() {
		return writedate;
	}
	public void setWritedate(Date writedate) {
		this.writedate = writedate;
	}
	
}
