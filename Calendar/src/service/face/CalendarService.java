package service.face;

import java.util.List;

import dto.Calendar;

public interface CalendarService {

	/**
	 * 
	 * 일정을 추가한다.
	 * 
	 * @return calendar - 추가한 일정 List
	 */
	public List<Calendar> calendarAdd();

	/**
	 * 
	 * 일정을 수정한다.
	 * 
	 * @param calendar - 수정 할 일정의 정보
	 */
	public void calendarModify(Calendar calendar);

	/**
	 * 
	 * 일정을 삭제한다.
	 * 
	 * @param calendar - 삭제 할 일정의 정보
	 */
	public void calendarDelete(Calendar calendar);

}
