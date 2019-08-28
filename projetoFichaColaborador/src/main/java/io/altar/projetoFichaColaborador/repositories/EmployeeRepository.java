package io.altar.projetoFichaColaborador.repositories;

import java.util.List;

import javax.persistence.TypedQuery;

import io.altar.projetoFichaColaborador.models.Employee;
import io.altar.projetoFichaColaborador.models.Filters;

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
		boolean queryEntryClient = false;
		boolean queryEntryProfessionalCategory = false;
		String queryInputs = "";

		if (filter.getAdmissionDateMIN() != null) {
			queryEntryAdmissionDates = true;
			queryInputs += " e.admissionDate >=:admissionDateMIN AND e.admissionDate <=:admissionDateMAX";
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
				previousQueryEntry = true;
			}
		}
		if (filter.getProfessionalCategory() != null) {
			queryEntryProfessionalCategory = true;
			if (previousQueryEntry) {
				queryInputs += " AND e.professionalCategory =:professionalCategory";
			} else {
				queryInputs += " e.professionalCategory =:professionalCategory";
				previousQueryEntry = true;
			}
		}
		if (filter.getClient() != null) {
			queryEntryClient = true;
			if (previousQueryEntry) {
				queryInputs += " AND e.client =:client";
			} else {
				queryInputs += " e.client =:client";
			}
		}
		TypedQuery<Employee> query = em.createQuery(
				"SELECT DISTINCT e FROM Employee e LEFT JOIN FETCH e.insuranceDetails WHERE" + queryInputs,
				Employee.class);

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
		if (queryEntryProfessionalCategory) {
			query.setParameter("professionalCategory", filter.getProfessionalCategory());
		}
		if (queryEntryClient) {
			query.setParameter("client", filter.getClient());
		}

		return query.getResultList();
	}

	public long countFilterEmployees(Filters filter) {
		boolean previousQueryEntry = false;
		boolean queryEntryAdmissionDates = false;
		boolean queryEntryTech = false;
		boolean queryEntryDistrict = false;
		boolean queryEntryClient = false;
		boolean queryEntryProfessionalCategory = false;
		String queryInputs = " WHERE";

		if (filter.getAdmissionDateMIN() != null) {
			queryEntryAdmissionDates = true;
			queryInputs += " e.admissionDate >=:admissionDateMIN AND e.admissionDate <=:admissionDateMAX";
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
				previousQueryEntry = true;
			}
		}
		if (filter.getProfessionalCategory() != null) {
			queryEntryProfessionalCategory = true;
			if (previousQueryEntry) {
				queryInputs += " AND e.professionalCategory =:professionalCategory";
			} else {
				queryInputs += " e.professionalCategory =:professionalCategory";
				previousQueryEntry = true;
			}
		}
		if (filter.getClient() != null) {
			queryEntryClient = true;
			if (previousQueryEntry) {
				queryInputs += " AND e.client =:client";
			} else {
				queryInputs += " e.client =:client";
				previousQueryEntry = true;
			}
		}
		if (previousQueryEntry == false) {
			queryInputs = "";
		}
		TypedQuery<Long> maxResultsInQuery = em.createQuery("SELECT COUNT(e) FROM Employee e" + queryInputs,
				Long.class);

		if (queryEntryAdmissionDates) {
			maxResultsInQuery.setParameter("admissionDateMIN", filter.getAdmissionDateMIN());
			maxResultsInQuery.setParameter("admissionDateMAX", filter.getAdmissionDateMAX());
		}
		if (queryEntryTech) {
			maxResultsInQuery.setParameter("specialTech", filter.getSpecialTech());
		}
		if (queryEntryDistrict) {
			maxResultsInQuery.setParameter("district", filter.getDistrict());
		}
		if (queryEntryProfessionalCategory) {
			maxResultsInQuery.setParameter("professionalCategory", filter.getProfessionalCategory());
		}
		if (queryEntryClient) {
			maxResultsInQuery.setParameter("client", filter.getClient());
		}
		return maxResultsInQuery.getSingleResult();
	}
}
