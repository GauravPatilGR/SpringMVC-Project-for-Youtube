package test.dao;

import java.awt.RadialGradientPaint;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;

import test.beans.Company;
import test.beans.Freelancer;
import test.beans.applyjob;
import test.beans.applyproject;
import test.beans.postjob;
import test.beans.postproject;
import test.beans.showjobs;


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

	public void postdetails(postjob c1) {
	
		t1.update("insert into postjob (name,email,discription,tittle,skills,salary,role) values ('"+c1.getName()+"','"+c1.getEmail()+"','"+c1.getDicription()+"','"+c1.getTittle()+"','"+c1.getSkill()+"','"+c1.getSalary()+"','"+c1.getRole()+"')");
		
	}

	public List<showjobs> Getalljobsdata() {
		
		return t1.query("select *from postjob right join company on postjob.name = company.name where postjob.name Is NOT NULL Order BY RANDOM()", new RowMapper<showjobs>() {

			@Override
			public showjobs mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				showjobs s1= new showjobs();
				s1.setId(rs.getInt(1));
				s1.setProfilec(rs.getString(16));
				s1.setCompanyname(rs.getString(2));
				s1.setPosition(rs.getString(5));
				s1.setSkills(rs.getString(6));
				
				return s1;
			}
			
			
			
		});
		
	}

	public void postprojectdata(postproject c1) {
		
		t1.update("insert into postproject (projectd,projectt,projectpdf,projects,projectb,cname,cemail) values ('"+c1.getProjectd()+"','"+c1.getProjectt()+"','"+c1.getProjectpdf()+"','"+c1.getProjects()+"','"+c1.getProjectb()+"','"+c1.getCname()+"','"+c1.getCemail()+"')");
		
	}

	public List<postproject> showallprojectdetails() {
		
		return t1.query("Select *from postproject", new RowMapper<postproject>() {

			@Override
			public postproject mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				
				postproject p1= new postproject();
				p1.setId(rs.getInt(1));
				p1.setProjectt(rs.getString(3));
				p1.setCname(rs.getString(7));
				p1.setProjects(rs.getString(5));
				p1.setProjectpdf(rs.getString(4));
				return p1;
			}
			
			
			
		});
		
	}

	public List<postjob> getjobdata(int id) {
		
		return t1.query("select *from postjob where id='"+id+"'", new RowMapper<postjob>() {

			@Override
			public postjob mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				postjob p1= new postjob();
				p1.setId(rs.getInt(1));
				p1.setDicription(rs.getString(4));
				p1.setTittle(rs.getString(5));
				p1.setRole(rs.getString(8));
				p1.setSalary(rs.getString(7));
				p1.setSkill(rs.getString(6));
				p1.setName(rs.getString(2));
				p1.setEmail(rs.getString(3));
				return p1;
			}
			
			
			
		});
	}

	public List<postproject> getallprojectdatabyid(int id) {
	
		return t1.query("select *from postproject where id='"+id+"'", new RowMapper<postproject>() {

			@Override
			public postproject mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				
				postproject p1= new postproject();
				p1.setId(rs.getInt(1));
				p1.setProjectd(rs.getString(2));
				p1.setProjectt(rs.getString(3));
				p1.setCname(rs.getString(7));
				p1.setProjects(rs.getString(5));
				p1.setProjectb(rs.getString(6));
				p1.setProjectpdf(rs.getString(4));
				p1.setCemail(rs.getString(8));
				
				return p1;
			}
			
			
		});
		
	}

	public void getapplyjobdata(applyjob c1) {
		
		t1.update("insert into applyjob (cname,position,fname,femail,fresume,status) values ('"+c1.getCname()+"','"+c1.getPosition()+"','"+c1.getFname()+"','"+c1.getFemail()+"','"+c1.getFresume()+"','"+c1.getStatus()+"')");
		
	}

	public void applyforproject(applyproject c1) {
		
		t1.update("insert into applyproject (projectt,cname,cemail,fname,femail,resumef,status) values ('"+c1.getProjectt()+"','"+c1.getCname()+"','"+c1.getCemail()+"','"+c1.getFname()+"','"+c1.getFemail()+"','"+c1.getResumef()+"','"+c1.getStatus()+"')");
		
	}

	public List<applyjob> getmyapplicationdata(String email) {
		
		return t1.query("select *from applyjob where femail Like '%"+email+"%'", new RowMapper<applyjob>() {

			@Override
			public applyjob mapRow(ResultSet rs, int rowNum) throws SQLException {
				
			     applyjob a1= new applyjob();
			     a1.setId(rs.getInt(1));
			     a1.setCname(rs.getString(2));
			     a1.setPosition(rs.getString(3));
			     a1.setFname(rs.getString(4));
			     a1.setFemail(rs.getString(5));
			     a1.setFresume(rs.getString(6));
			     a1.setStatus(rs.getString(7));
				
				return a1;
			}
			
			
			
		});
	}

	public List<applyproject> getprojectapplicationdata(String email) {
		
		return t1.query("select *from applyproject where femail Like '%"+email+"%'", new RowMapper<applyproject>() {

			@Override
			public applyproject mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				applyproject a1= new applyproject();
				a1.setId(rs.getInt(1));
				a1.setProjectt(rs.getString(2));
				a1.setCname(rs.getString(3));
				a1.setCemail(rs.getString(4));
				a1.setFname(rs.getString(5));
				a1.setFemail(rs.getString(6));
				a1.setResumef(rs.getString(7));
				a1.setStatus(rs.getString(8));
				
				return a1;
			}
			
			
		});
	}

	

}
