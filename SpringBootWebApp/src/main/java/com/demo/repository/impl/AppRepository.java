package com.demo.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.demo.entity.Member;
import com.demo.entity.Person;
import com.demo.entity.UserDetails;
import com.demo.exceptions.DataNotFoundException;
import com.demo.repository.IAppRepository;
import com.demo.type.RoleType;

@Repository
public class AppRepository implements IAppRepository {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	private static final String APPROVER_NAME = "approver_name";
	private static final String REQUESTER_NAME = "requester_name";
	private static final String YOB = "yob";

	private static final String ROLE = "role";
	private static final String SPECIALITY = "speciality";
	private static final String RECORDS = "records";

	private static final String PRESCRIPTIONS = "prescriptions";
	private static final String REQUEST_TYPE = "request_type";

	private String GET_PATIENT_SQL = "Select p.NAME as REQUESTER_NAME ,p.SPECIALITY ,p.ROLE ,p.YOB, a.REQUEST_TYPE   from person as p  JOIN"
			+ " authorizations as a ON  p.PERSON_ID =a.REQUESTER_ID JOIN "
			+ "person as p2  ON a.APPROVER_ID =p2.PERSON_ID "
			+ "where p2.NAME=:name AND p2.YOB=:yob AND p2.ROLE=:role AND a.REQUEST_TYPE='requested'";

	private String GET_DOCTOR_SQL_MAIN = "Select p.NAME as APPROVER_NAME ," + "p.SPECIALITY ," + "p.ROLE ," + "p.YOB ,"
			+ "p.RECORDS ," + "p.PRESCRIPTIONS," + "ath.REQUEST_TYPE "
			+ "from person as p  LEFT OUTER JOIN authorizations as ath ON  p.PERSON_ID =ath.REQUESTER_ID where p.ROLE='patient'";

	private String GET_DOCTOR_SQL_MINOR = "Select p.NAME as APPROVER_NAME ," + "p.SPECIALITY ," + "p.ROLE ," + "p.YOB ,"
			+ "p.RECORDS ," + "p.PRESCRIPTIONS," + "ath.REQUEST_TYPE "
			+ "from person as p  LEFT OUTER JOIN authorizations as ath ON  p.PERSON_ID  = ath.APPROVER_ID "
			+ "where p.ROLE ='patient'  AND "
			+ "ath.REQUESTER_ID IS (Select PERSON_ID  from person where person.NAME =:name)";

	private String INSERT_QUERY = "insert into person(NAME,SPECIALITY,ROLE,YOB,RECORDS,PRESCRIPTIONS,PASSWORD"
			+ ") values( :name,:speciality,:role,:yob,:record,:prescription,:password)";

	private String REQUEST_UPDATE_QUERY = "UPDATE AUTHORIZATIONS  as a" + " SET  a.REQUEST_TYPE = 'approved' "
			+ "WHERE REQUESTER_ID is (select PERSON_ID from PERSON where NAME = :requesterName) AND APPROVER_ID is (select PERSON_ID from PERSON where NAME =:approverName)";

	private String REQUEST_INSERT_QUERY = "INSERT INTO AUTHORIZATIONS (REQUESTER_ID, APPROVER_ID, REQUEST_TYPE ) "
			+ "VALUES ((select PERSON_ID from PERSON where NAME = :requesterName),(select PERSON_ID from PERSON where NAME = :approverName),'requested');";

	private String SELECT_QUERY = "SELECT p.NAME, p.YOB,p.PASSWORD from PERSON as p where p.Name=:name AND p.YOB=:yob AND p.PASSWORD=:password ;";

	private List<UserDetails> executeQuery(String query, Map<String, Object> parameters) {
		try {
			return jdbcTemplate.query(query, parameters, new RowMapper<UserDetails>() {
				public UserDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
					return mapUserDeatils(rs);
				}
			});
		} catch (DataAccessException e) {
			throw new DataNotFoundException();
		}
	}

	public List<UserDetails> execute(String name, String yob, RoleType role) {
		final Map<String, Object> parameters = new LinkedHashMap<>();
		parameters.put("name", name);
		parameters.put("yob", yob);
		parameters.put("role", role.getRoleType());
		String query = null;
		List<UserDetails> records = null;
		switch (role) {
		case PATIENT:
			query = GET_PATIENT_SQL;
			records = executeQuery(query, parameters);
			break;
		case DOCTOR:
		case PHARMACIST:
			query = GET_DOCTOR_SQL_MAIN;
			List<UserDetails> major = executeQuery(query, parameters);
			query = GET_DOCTOR_SQL_MINOR;
			List<UserDetails> minor = executeQuery(query, parameters);
			records = UtilityClass.processDocAndPharmaDeatils(major, minor);
			break;
		default:
			break;
		}
		return records;
	}

	private UserDetails mapUserDeatils(ResultSet rs) throws SQLException {

		UserDetails details = new UserDetails();

		details.setRole(rs.getString(ROLE));
		if (details.getRole().equals(RoleType.PATIENT.getRoleType())) {
			details.setApproverName(rs.getString(APPROVER_NAME));
			details.setRecord(rs.getString(RECORDS));
			details.setPrescription(rs.getString(PRESCRIPTIONS));
		} else
			details.setRequesterName(rs.getString(REQUESTER_NAME));

		details.setYob(rs.getString(YOB));

		details.setSpeciality(rs.getString(SPECIALITY));
		details.setRequestType(rs.getString(REQUEST_TYPE));

		return details;
	}

	public void createRequest(UserDetails userDeatils) {
		final Map<String, Object> parameters = new LinkedHashMap<>();
		parameters.put("requesterName", userDeatils.getRequesterName());
		parameters.put("approverName", userDeatils.getApproverName());
		jdbcTemplate.update(REQUEST_INSERT_QUERY, parameters);
	}

	public void createUser(Person person) {
		final Map<String, Object> parameters = new LinkedHashMap<>();
		parameters.put("name", person.getPersonName());
		parameters.put("yob", person.getPersonYob());
		parameters.put("role", person.getPersonRole());
		parameters.put("speciality", person.getSpeciality());
		parameters.put("record", person.getRecord());
		parameters.put("prescription", person.getPrescription());
		parameters.put("password", person.getPassword());
		jdbcTemplate.update(INSERT_QUERY, parameters);
	}

	public void update(UserDetails userDeatils) {
		final Map<String, Object> parameters = new LinkedHashMap<>();
		parameters.put("requesterName", userDeatils.getRequesterName());
		parameters.put("approverName", userDeatils.getApproverName());
		jdbcTemplate.update(REQUEST_UPDATE_QUERY, parameters);
	}

	public boolean validate(String name, String yob, String password) {
		final Map<String, Object> parameters = new LinkedHashMap<>();
		parameters.put("name", name);
		parameters.put("yob", yob);
		parameters.put("password", password);
		List<Member> record = jdbcTemplate.query(SELECT_QUERY, parameters, new CustomeRowMapper());
		if (record.size() != 1)
			return false;
		else
			return true;
	}

	private class CustomeRowMapper implements RowMapper<Member> {
		public Member mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			final Member me = new Member();
			me.setPersonName(resultSet.getString("NAME"));
			me.setPersonYob(resultSet.getString("YOB"));
			return me;
		}
	}
}
