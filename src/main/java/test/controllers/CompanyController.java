package test.controllers;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import test.beans.Company;
import test.dao.ProjectDao;

@Controller
public class CompanyController {
	
	@Autowired
	ProjectDao pd;
	
	//RegisterPage Mapping
	@RequestMapping("/registerCompany")
	public String RegisterPage() {
		
		return "registerCompany";
	}
	
	//LoginPage Mapping
	@RequestMapping("/loginCompany")
	public String LoginCompany() {
		
		return "loginCompany";
	}
	
	//Registration Mapping or Handler
	@RequestMapping(value = "/registercompany" ,method = RequestMethod.POST)
	public String registerCompanydata(@ModelAttribute("c1") Company c1,@RequestParam("filename") MultipartFile filename,ModelMap mm) throws IOException {
		
		if(c1.getPassword().equals(c1.getCpassword()))
		{
		
		//for file name
		String f=filename.getOriginalFilename();
		
		//for file Storage
		String path="C:\\Users\\gaura\\eclipse-workspace\\ProjectSpringMVC_Java1\\src\\main\\webapp\\files\\webimages";
		
		//Concate of file name and file storage
		BufferedOutputStream bf = new BufferedOutputStream(new FileOutputStream(path+"/"+f));
		
		byte b []=filename.getBytes();
		
		bf.write(b);
		
		bf.close();
		
		c1.setProfilec(f);
		
		
		
		
		pd.registercompany(c1);
		
		mm.addAttribute("registermessage","Registration Succesfully Completed Login Now");
		
		return "loginCompany";
		
		}
		
		mm.addAttribute("registererror","Something Went Wrong!");
		
		return "registerCompany";
		
	}
	
	//mapping of Login Company
	@RequestMapping(value = "/logincompany",method = RequestMethod.POST)
	public String logincompany(@RequestParam("email")String email,@RequestParam("password")String password,ModelMap mm) {
		
	List<Company> companydata=pd.checklogindetails(email,password);
	
	if(companydata.isEmpty()) {
		
		mm.put("loginkey", "Something Went Wrong Check Your Details!");
		
		return "loginCompany";
	}
	
	return "homecompany";
		
	}
	
	
	//mapping of home Company
	@RequestMapping("/homecompany")
	public String homepagecompany() {
		
		
		return "homecompany";
	}
	
	
	

}
