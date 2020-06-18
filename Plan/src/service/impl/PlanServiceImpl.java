package service.impl;

import java.util.ArrayList;
import java.util.List;

import dao.face.PlanDao;
import dao.impl.PlanDaoImpl;
import dto.Plan;
import service.face.PlanService;

public class PlanServiceImpl implements PlanService {

	private PlanDao calendarDao = new PlanDaoImpl();

	@Override
	public List<Plan> calendarAdd() {

		List<Plan> cal = new ArrayList<>();

		calendarDao.add();

		return cal;
	}

	@Override
	public void calendarModify(Plan calendar) {

		calendarDao.modify(calendar);

	}

	@Override
	public void calendarDelete(Plan calendar) {

		calendarDao.delete(calendar);

	}

}
