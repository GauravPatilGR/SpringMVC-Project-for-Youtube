package test.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;

import test.beans.Company;
import test.beans.Freelancer;
import test.beans.postjob;

public class ProjectDao {
	
	
	JdbcTemplate t1;

	public JdbcTemplate getT1() {
		return t1;
	}

	public void setT1(JdbcTemplate t1) {
		this.t1 = t1;
	}

	public void registercompany(Company c1) {
		
		t1.update("insert into company (name,email,number,website,password,cpassword,profilec) values ('"+c1.getName()+"','"+c1.getEmail()+"','"+c1.getNumber()+"','"+c1.getWebsite()+"','"+c1.getPassword()+"','"+c1.getCpassword()+"','"+c1.getProfilec()+"')");
		
	}

	public List<Company> checklogindetails(String email, String password) {
		
		return t1.query("select *from company where email='"+email+"' and password='"+password+"'", new RowMapper<Company>() {

			@Override
			public Company mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				
				Company c1= new Company();
				
				c1.setId(rs.getInt(1));
				c1.setName(rs.getString(2));
				c1.setEmail(rs.getString(3));
				c1.setNumber(rs.getString(4));
				c1.setWebsite(rs.getString(5));
				c1.setPassword(rs.getString(6));
				c1.setCpassword(rs.getString(7));
				c1.setProfilec(rs.getString(8));
				c1.setAbout(rs.getString(9));
				
				
				
				
				return c1;
			}
			
			
			
		});
		
	}

	public void updatedataofprofile(Company c1) {
		
		t1.update("update company set name='"+c1.getName()+"',email='"+c1.getEmail()+"',number='"+c1.getNumber()+"',website='"+c1.getWebsite()+"',profilec='"+c1.getProfilec()+"',about='"+c1.getAbout()+"' where id='"+c1.getId()+"'");
		
	}

	public void registerfreelancer(Freelancer c1) {
		
		
		t1.update("insert into freelancer (name,email,number,date,linkedin,education,profilef,charge,gender,skills,password,cpassword) values ('"+c1.getName()+"','"+c1.getEmail()+"','"+c1.getNumber()+"','"+c1.getDate()+"','"+c1.getLinkedin()+"','"+c1.getEducation()+"','"+c1.getProfilef()+"','"+c1.getCharge()+"','"+c1.getGender()+"','"+c1.getSkills()+"','"+c1.getPassword()+"','"+c1.getCpassword()+"') ");
		
	}

	public List<Freelancer> loginfreelancerdetails(String email, String password) {
		
		return t1.query("select *from freelancer where email='"+email+"' and password='"+password+"'", new RowMapper<Freelancer> () {

			@Override
			public Freelancer mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				Freelancer f1= new Freelancer();
				
				f1.setId(rs.getInt(1));
				f1.setName(rs.getString(2));
				f1.setEmail(rs.getString(3));
				f1.setNumber(rs.getString(4));
				f1.setDate(rs.getDate(5));
				f1.setLinkedin(rs.getString(6));
				f1.setEducation(rs.getString(7));
				f1.setProfilef(rs.getString(8));
				f1.setCharge(rs.getString(9));
				f1.setGender(rs.getString(10));
				f1.setSkills(rs.getString(11));
				
				return f1;
			}
			
			
			
		});
		
	}

	public List<Freelancer> checkemailfreelancer(String email) {
		
		return t1.query("select *from freelancer where email='"+email+"'", new RowMapper<Freelancer>() {

			@Override
			public Freelancer mapRow(ResultSet rs, int rowNum) throws SQLException {
				 
				Freelancer f1= new Freelancer();
				f1.setEmail(rs.getString(3));
				
				
				return f1;
			}
			
			
		});
	}

	public void updatefreelancerprofile(Freelancer c1) {
		
		t1.update("update freelancer set name='"+c1.getName()+"',email='"+c1.getEmail()+"',number='"+c1.getNumber()+"',linkedin='"+c1.getLinkedin()+"',education='"+c1.getEducation()+"',profilef='"+c1.getProfilef()+"',charge='"+c1.getCharge()+"',skills='"+c1.getSkills()+"'");
	}

	public void postjobdeta(postjob c1) {
		
		t1.update("insert into postjob (discription,tittle,skills,budget,role) values ('"+c1.getDiscription()+"','"+c1.getTittle()+"','"+c1.getSkills()+"','"+c1.getBudget()+"','"+c1.getRole()+"')");
		
	}

}
