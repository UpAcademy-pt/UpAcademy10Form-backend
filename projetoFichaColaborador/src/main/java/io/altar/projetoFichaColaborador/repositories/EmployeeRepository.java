package io.altar.projetoFichaColaborador.repositories;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.TypedQuery;

import io.altar.projetoFichaColaborador.models.Employee;
import io.altar.projetoFichaColaborador.models.Filters;

@RequestScoped
public class EmployeeRepository extends EntityRepository<Employee> {

	@Override
	protected Class<Employee> getEntityClass() {
		return Employee.class;
	}

	@Override
	protected String getByIdQuery() {
		return Employee.GET_EMPLOYEE_BY_ID;
	}

	@Override
	protected String getAllQuery() {
		return Employee.GET_ALL_EMPLOYEES;
	}

	@Override
	protected String checkEntityExistsByIdQuery() {
		return Employee.CHECK_EMPLOYEE_EXISTS_BY_ID;
	}

	public boolean checkEmployeeExistsByEntity(Employee employee) {
		return checkEntityExistsById(employee.getId());
	}

	public List<Employee> filterEmployees(Filters filter) {
		boolean previousQueryEntry = false;
		boolean queryEntryAdmissionDates = false;
		boolean queryEntryTech = false;
		boolean queryEntryDistrict = false;
		String queryInputs = "";
		if (filter.getAdmissionDateMIN() != null) {
			queryEntryAdmissionDates = true;
			queryInputs = " e.admissionDate >=:admissionDateMIN AND e.admissionDate <=:admissionDateMAX";
			previousQueryEntry = true;
		}
		if (filter.getSpecialTech() != null) {
			queryEntryTech = true;
			if (previousQueryEntry) {
				queryInputs += " AND e.specialTech =:specialTech";
				previousQueryEntry = true;
			} else {
				queryInputs += " e.specialTech =:specialTech";
				previousQueryEntry = true;
			}
		}
		if (filter.getDistrict() != null) {
			queryEntryDistrict = true;
			if (previousQueryEntry) {
				queryInputs += " AND e.district =:district";
			} else {
				queryInputs += " e.district =:district";
			}
		}
		TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e WHERE" + queryInputs, Employee.class);
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
		
		query.setFirstResult(filter.getStartPage());
		query.setMaxResults(10);
		return query.getResultList();
	}

	public long countFilterEmployees(Filters filter) {
		boolean previousQueryEntry = false;
		boolean queryEntryAdmissionDates = false;
		boolean queryEntryTech = false;
		boolean queryEntryDistrict = false;
		String queryInputs = "";
		
		if (filter.getAdmissionDateMIN() != null) {
			queryEntryAdmissionDates = true;
			queryInputs = " e.admissionDate >=:admissionDateMIN AND e.admissionDate <=:admissionDateMAX";
			previousQueryEntry = true;
		}
		if (filter.getSpecialTech() != null) {
			queryEntryTech = true;
			if (previousQueryEntry) {
				queryInputs += " OR e.specialTech =:specialTech";
				previousQueryEntry = true;
			} else {
				queryInputs += " e.specialTech =:specialTech";
				previousQueryEntry = true;
			}
		}
		if (filter.getDistrict() != null) {
			queryEntryDistrict = true;
			if (previousQueryEntry) {
				queryInputs += " OR e.district =:district";
			} else {
				queryInputs += " e.district =:district";
			}
		}
		
		TypedQuery<Long> maxResultsInQuery = em.createQuery("SELECT COUNT(e) FROM Employee e WHERE" + queryInputs,
				Long.class);
		
		if (queryEntryAdmissionDates) {
			maxResultsInQuery.setParameter("admissionDateMIN", filter.getAdmissionDateMIN());
			maxResultsInQuery.setParameter("admissionDateMAX", filter.getAdmissionDateMAX());
		}
		if (queryEntryTech) {
			maxResultsInQuery.setParameter("specialTech", filter.getSpecialTech());
		}
		if (queryEntryDistrict) {
			maxResultsInQuery.setParameter("district", filter.getSpecialTech());
		}
		System.out.println(maxResultsInQuery.getSingleResult());
		return maxResultsInQuery.getSingleResult();
	}
	
}
