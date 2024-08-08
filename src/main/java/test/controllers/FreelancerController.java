package test.controllers;


import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import test.beans.Freelancer;
import test.dao.ProjectDao;

@Controller
public class FreelancerController {
	
	@Autowired
	ProjectDao pd;
	
	
	//Mapping for Register Page
	@RequestMapping("/registerfreelancer")
	public String Registerpage() {
		
		
		return "registerfreelancer";
	}
	
	//Mapping for Login Page
	@RequestMapping("/loginfreelancer")
	public String loginpage() {
		
		return "loginfreelancer";
	}
	
	//Mapping for Register Freelancer
	@RequestMapping(value = "/registerfreelancerdata",method = RequestMethod.POST)
	public String registerdataoffreelancer(@ModelAttribute("c1") Freelancer c1,@RequestParam("filnamef")MultipartFile filename) throws IOException {
		
		
		        //for file name
				String f=filename.getOriginalFilename();
				
				//for file Storage
				String path="C:\\Users\\gaura\\eclipse-workspace\\ProjectSpringMVC_Java1\\src\\main\\webapp\\files\\webimages";
				
				//Concate of file name and file storage
				BufferedOutputStream bf = new BufferedOutputStream(new FileOutputStream(path+"/"+f));
				
				byte b []=filename.getBytes();
				
				bf.write(b);
				
				bf.close();
				
				c1.setProfilef(f);
				
				
				pd.registerfreelancer(c1);
		
		
		return "registerfreelancer";
	}
	
	//Mapping for Login Freelancer
	@RequestMapping(value = "/loginfreelancer",method = RequestMethod.POST)
	public String loginfreelancerdata(@RequestParam("email")String email,@RequestParam("password") String password) {
		
		
	List<Freelancer> freelancerdata =pd.loginfreelancerdetails(email,password);
	
	if(freelancerdata.isEmpty())
	{
		return "loginfreelancer";
	}
	
	return "homefreelancer";
		
		
	}
	
	//Mapping for Freelancer Home Page
	@RequestMapping("/homefreelancer")
	public String homefreelancer() {
		
		return "homefreelancer";
		
	}

}
