package io.altar.projetoFichaColaborador.repositories;

import java.util.List;

import javax.faces.bean.RequestScoped;
import javax.persistence.Query;

import io.altar.projetoFichaColaborador.models.Employee;
import io.altar.projetoFichaColaborador.models.Filters;;

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

	public boolean countEmployeeExistsByEntity(Employee employee) {
		long id = employee.getId();
		Query query = em.createQuery(
				"SELECT e FROM Employee e" + " WHERE EXISTS (SELECT e FROM Employee e WHERE e.id =:employeeId)");
		query.setParameter("employeeId", id);
		List<?> valid = query.getResultList();
		if (valid.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	public boolean countEmployeeExistsById(long id) {
		Query query = em.createQuery(
				"SELECT e FROM Employee e" + " WHERE EXISTS (SELECT e FROM Employee e WHERE e.id =:employeeId)");
		query.setParameter("employeeId", id);
		List<?> valid = query.getResultList();
		if (valid.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	public List<?> filterEmployees(Filters filter) {
		boolean previousQueryEntry = false;
		boolean queryEntryAdmissionDates = false;
		boolean queryEntryTech = false;
		boolean queryEntryDistrict = false;
		String queryInputs = "";

		if (filter.getAdmissionDateMIN() != null) {
			queryEntryAdmissionDates = true;
			queryInputs = "e.admissionDate >=:admissionDateMIN AND e.admissionDate <=:admissionDateMAX";
			previousQueryEntry = true;
		}
		if (filter.getSpecialTech() != null) {
			queryEntryTech = true;
			if (previousQueryEntry) {
				queryInputs += "AND e.specialTech =:specialTech";
				previousQueryEntry = true;
			} else {
				queryInputs += "e.specialTech =:specialTech";
				previousQueryEntry = true;
			}
		}
		if (filter.getDistrict() != null) {
			queryEntryDistrict = true;
			if (previousQueryEntry) {
				queryInputs += "AND e.district =:district";
			} else {
				queryInputs += "e.district =:district";
			}
		}

		Query query = em.createQuery("SELECT e FROM Employee e WHERE " + queryInputs);
		if (queryEntryAdmissionDates) {
			query.setParameter("admissionDateMIN", filter.getAdmissionDateMIN());
			query.setParameter("admissionDateMAX", filter.getAdmissionDateMAX());
		}
		if (queryEntryTech) {
			query.setParameter("specialTech", filter.getSpecialTech());
		}
		if (queryEntryDistrict) {
			query.setParameter("district", filter.getDistrict());
		}
		List<?> filteredEmployeesList = query.getResultList();
		return filteredEmployeesList;
	}
}
