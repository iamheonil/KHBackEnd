package dao.impl;

import java.util.List;

import dao.face.CalendarDao;
import dto.Calendar;

public class CalendarDaoImpl implements CalendarDao {

	@Override
	public List<Calendar> add() {

		System.out.println("ADD");

		return null;
	}

	@Override
	public void modify(Calendar calendar) {
		System.out.println("Modify");

	}

	@Override
	public void delete(Calendar calendar) {
		System.out.println("Delete");

	}

}
