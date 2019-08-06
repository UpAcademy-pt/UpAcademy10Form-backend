package io.altar.projetoFichaColaborador.repositories;

import javax.faces.bean.RequestScoped;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import io.altar.projetoFichaColaborador.models.Employee;;


@RequestScoped
public class EmployeeRepository extends EntityRepository<Employee> {

	@Override
	protected Class<Employee> getEntityClass() {
		return Employee.class;
	}

	@Override
	protected String getAllQuery() {
		return Employee.GET_ALL_EMPLOYEES;
	}
	
	@Override
	protected String getByIdQuery() {
		return Employee.GET_EMPLOYEE_BY_ID;
	}
	
	public boolean countEmployeeExists(Employee employee) {
		long id = employee.getId();
		Query query = em.createQuery("SELECT count(e) FROM Employee e WHERE e.id =:employeeId");
		query.setParameter("employeeId", id);
		int valid = query.getFirstResult();

		if (valid==1) {
			return true;
		}else {
			return false;
		}
	}

}
