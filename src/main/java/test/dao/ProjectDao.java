package test.dao;

import org.springframework.jdbc.core.JdbcTemplate;

import test.beans.Company;

public class ProjectDao {
	
	
	JdbcTemplate t1;

	public JdbcTemplate getT1() {
		return t1;
	}

	public void setT1(JdbcTemplate t1) {
		this.t1 = t1;
	}

	public void registercompany(Company c1) {
		
		t1.update("insert into company (name,email,number,website,password,cpassword) values ('"+c1.getName()+"','"+c1.getEmail()+"','"+c1.getNumber()+"','"+c1.getWebsite()+"','"+c1.getPassword()+"','"+c1.getCpassword()+"')");
		
	}

}
