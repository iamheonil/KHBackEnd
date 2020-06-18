package dao.face;

import java.util.List;

import dto.Plan;

public interface PlanDao {

	/**
	 * 일정을 추가한다.
	 * 
	 * @return ListObj - 일정 추가할 정보
	 */
	public List<Plan> add();

	/**
	 * 
	 * 일정을 수정한다.
	 * 
	 * @param calendar - 수정할 객체의 정보
	 */
	public void modify(Plan calendar);

	/**
	 * 
	 * 일정을 삭제한다.
	 * 
	 * @param calendar - 삭제할 객체의 정보
	 */
	public void delete(Plan calendar);

}
