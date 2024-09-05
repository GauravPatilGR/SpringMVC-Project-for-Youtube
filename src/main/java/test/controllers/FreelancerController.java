package test.controllers;


import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import test.beans.Freelancer;
import test.beans.applyjob;
import test.beans.postjob;
import test.beans.postproject;
import test.beans.showjobs;
import test.dao.ProjectDao;
import test.beans.applyproject;


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
	public String registerdataoffreelancer(@ModelAttribute("c1") Freelancer c1,@RequestParam("filnamef")MultipartFile filename,@RequestParam("email") String email,ModelMap mm) throws IOException {
		
		 //Check Dublicate Email
		  List<Freelancer> freelanceremaildata  = pd.checkemailfreelancer(email);
		  
		  if(c1.getPassword().equals(c1.getCpassword()) && freelanceremaildata.isEmpty())
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
				
				c1.setProfilef(f);
				
				
				pd.registerfreelancer(c1);
				
				mm.addAttribute("messagelogin","Registration Succesfully Done Login Now!");
				
				return "loginfreelancer";
				
				
		  }
		
		mm.addAttribute("messageerror","Check your password & confirm password or Email Alredy Present in Database.!");
		return "registerfreelancer";
	}
	
	//Mapping for Login Freelancer
	@RequestMapping(value = "/loginfreelancer",method = RequestMethod.POST)
	public String loginfreelancerdata(@RequestParam("email")String email,@RequestParam("password") String password,ModelMap mm,HttpSession h1) {
		
		             //Freelancer All Data
	List<Freelancer> freelancerdata =pd.loginfreelancerdetails(email,password);
	
	if(freelancerdata.isEmpty())
	{
		mm.addAttribute("messageloginfreelancer","you Don't Have An Account Register Now..");
		return "loginfreelancer";
	}
	
	
	h1.setAttribute("freelanceremail", email);
	
	h1.setAttribute("Freelanceralldata", freelancerdata);	
	
	return "redirect:/homefreelancer";
		
		
	}
	
	//Mapping for Freelancer Home Page
	@RequestMapping("/homefreelancer")
	public String homefreelancer(HttpSession h1,ModelMap mm) {
		
	String freelancersession=	(String) h1.getAttribute("freelanceremail");
	
	if(freelancersession==null)
	{
	    return	"loginfreelancer";
	}
	
	//All Freelancer Data Present 
	List<Freelancer> freelancerdatalist = (List<Freelancer>) h1.getAttribute("Freelanceralldata");
	
	mm.addAttribute("freelancerdata",freelancerdatalist);
	
	
		
		return "homefreelancer";
		
	}
	
	@RequestMapping("/logoutfreelancer")
	public String logoutfreelancer(HttpSession h1) {
		
		h1.invalidate();
		
		return "loginfreelancer";
		
	}
	
	@RequestMapping("/headerfreelancer")
	public String headerfreelancer() {
		
		
		return "headerfreelancer";
	}
	
	@RequestMapping("/profilefreelancer")
	public String profilefreelancer(HttpSession h1,ModelMap mm) {
		
		
		//All Freelancer Data Present 
		List<Freelancer> freelancerdatalist = (List<Freelancer>) h1.getAttribute("Freelanceralldata");
		
		mm.addAttribute("freelancerdata",freelancerdatalist);
		
		
		return "profilefreelancer";
	}
	
	@RequestMapping(value = "/updatefreelabcerprofile",method = RequestMethod.POST)
	public String updateprofile(@ModelAttribute("c1")Freelancer c1,@RequestParam("filename")MultipartFile filename) throws IOException {
		
		
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
		
		pd.updatefreelancerprofile(c1);
		
		
		return "loginfreelancer";
		
		
	}
	
	@RequestMapping("/Explorejobs")
	public String expolorejobs(ModelMap mm) {
		
		
	List<showjobs> showjobsdata =	pd.Getalljobsdata();
	                //key
	mm.addAttribute("jobsdata",showjobsdata);
		
		return "Explorejobs";
	}
	
	@RequestMapping("/Exploreproject")
	public String exploreprojectpage(ModelMap mm) {
		
		List<postproject> projectdata =  pd.showallprojectdetails();
		
		mm.addAttribute("projectinfo",projectdata);
		
		return "Exploreproject";
	}
	
	@RequestMapping(value = "viewandapplyjob/{id}",method = RequestMethod.GET)
	public String viewandapplypage(@PathVariable int id,ModelMap mm,HttpSession h1) {
		
	List<postjob> alldataofjobs= pd.getjobdata(id);
	
	mm.addAttribute("jobdata",alldataofjobs);
	
	//All Freelancer Data Present 
	List<Freelancer> freelancerdatalist = (List<Freelancer>) h1.getAttribute("Freelanceralldata");
	                //key
	mm.addAttribute("freelancerdata",freelancerdatalist);
	
	
		
		return "viewandapplyjob";
	}
	
	@RequestMapping(value = "/viewandapplyproject/{id}",method = RequestMethod.GET)
	public String viewallprojectsdetails(@PathVariable int id,ModelMap mm,HttpSession h1) {
		
		
	List<postproject> projectdataofid	=pd.getallprojectdatabyid(id);
	
	mm.addAttribute("projectdatakey",projectdataofid);
	
	//All Freelancer Data Present 
		List<Freelancer> freelancerdatalist = (List<Freelancer>) h1.getAttribute("Freelanceralldata");
		                //key
		mm.addAttribute("freelancerdata",freelancerdatalist);
		
		return "viewandapplyproject";
	}
	
	@RequestMapping(value = "/applyforjob",method = RequestMethod.POST)
	public String applyjobfreelancer(@ModelAttribute("c1") applyjob c1,@RequestParam("resumefile")MultipartFile filename,ModelMap mm) throws IOException {
		
		 //for file name
		String f=filename.getOriginalFilename();
		
		//for file Storage
		String path="C:\\Users\\gaura\\eclipse-workspace\\ProjectSpringMVC_Java1\\src\\main\\webapp\\files\\webimages";
		
		//Concate of file name and file storage
		BufferedOutputStream bf = new BufferedOutputStream(new FileOutputStream(path+"/"+f));
		
		byte b []=filename.getBytes();
		
		bf.write(b);
		
		bf.close();
		
		c1.setFresume(f);
		
		pd.getapplyjobdata(c1);
		
		mm.addAttribute("messageaftersubmitjob","Congratulations your Job Application Submitted Successfully...");
		
		return "Explorejobs";
		
	}
	
	
	@RequestMapping(value = "/applyforproject",method = RequestMethod.POST)
	public String applyproject(@ModelAttribute("c1") applyproject c1,@RequestParam("resumefile") MultipartFile filename,ModelMap mm) throws IOException{
		
		 //for file name
		String f=filename.getOriginalFilename();
		
		//for file Storage
		String path="C:\\Users\\gaura\\eclipse-workspace\\ProjectSpringMVC_Java1\\src\\main\\webapp\\files\\webimages";
		
		//Concate of file name and file storage
		BufferedOutputStream bf = new BufferedOutputStream(new FileOutputStream(path+"/"+f));
		
		byte b []=filename.getBytes();
		
		bf.write(b);
		
		bf.close();
		
		c1.setResumef(f);
		
		pd.applyforproject(c1);
		
		
		mm.addAttribute("messageaftersubmitproject","Congratulations your project Application Submitted Successfully...");
		
		return "Exploreproject";
	}
	
	@RequestMapping(value = "/myapplication/{email}")
	public String myapplication (@PathVariable String email,ModelMap mm) {
		
		
		//This is For Showing Job Application
		List<applyjob> myapplicationdata= pd.getmyapplicationdata(email);
		
		mm.addAttribute("myapplicationdetails",myapplicationdata);
		
	   List<applyproject> myprojectapplicationdata	=pd.getprojectapplicationdata(email);
	   
	   mm.addAttribute("myprojectapplications",myprojectapplicationdata);
		
		return "myapplication";
	}
	
  
	
	
	

}
