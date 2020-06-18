package dao.impl;

import java.util.List;

import dao.face.PlanDao;
import dto.Plan;

public class PlanDaoImpl implements PlanDao {

	@Override
	public List<Plan> add() {

		System.out.println("ADD");

		return null;
	}

	@Override
	public void modify(Plan calendar) {
		System.out.println("Modify");

	}

	@Override
	public void delete(Plan calendar) {
		System.out.println("Delete");

	}

}
