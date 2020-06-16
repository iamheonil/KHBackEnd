package service.impl;

import java.util.ArrayList;
import java.util.List;

import dao.face.CalendarDao;
import dao.impl.CalendarDaoImpl;
import dto.Calendar;
import service.face.CalendarService;

public class CalendarServiceImpl implements CalendarService {

	private CalendarDao calendarDao = new CalendarDaoImpl();

	@Override
	public List<Calendar> calendarAdd() {

		List<Calendar> cal = new ArrayList<>();

		calendarDao.add();

		return cal;
	}

	@Override
	public void calendarModify(Calendar calendar) {

		calendarDao.modify(calendar);

	}

	@Override
	public void calendarDelete(Calendar calendar) {

		calendarDao.delete(calendar);

	}

}
