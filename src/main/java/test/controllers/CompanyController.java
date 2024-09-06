package test.controllers;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlReturnResultSet;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import test.beans.Company;
import test.beans.applyjob;
import test.beans.applyproject;
import test.beans.postjob;
import test.beans.postproject;
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
			
			//File Upload Code Start
		
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
		
		
		//File Upload Code End
		
		
		
		
		pd.registercompany(c1);
		
		mm.addAttribute("registermessage","Registration Succesfully Completed Login Now");
		
		return "loginCompany";
		
		}
		
		mm.addAttribute("registererror","Something Went Wrong!");
		
		return "registerCompany";
		
	}
	
	
	
	//mapping of Login Company
	@RequestMapping(value = "/logincompany",method = RequestMethod.POST)
	public String logincompany(@RequestParam("email")String email,@RequestParam("password")String password,ModelMap mm,HttpSession h1) {
	
	//Company Data Present
	List<Company> companydata=pd.checklogindetails(email,password);
	
	if(companydata.isEmpty()) {
		
		mm.put("loginkey", "Something Went Wrong Check Your Details!");
		
		return "loginCompany";
	}
	
	h1.setAttribute("SessionData", email);
	
	//Company data key
	h1.setAttribute("Companydata", companydata);
	
	return "redirect:/homecompany";
		
	}
	
	
	//mapping of home Company page
	@RequestMapping("/homecompany")
	public String homepagecompany(HttpSession h1,ModelMap mm) {
		
		
	String sessionKey=	(String) h1.getAttribute("SessionData");
	
	if(sessionKey==null)
	{
		return "loginCompany";
	}
	
	
	//Company Data
                    //value of data
    List<Company>  companydatalist=(List<Company>) h1.getAttribute("Companydata");
                     //key of companydata
    mm.addAttribute("companykey",companydatalist);
 
	return "homecompany";
	}
	
	//Logout Company
	@RequestMapping("/logoutCompany")
	public String logoutCompany (HttpSession h1) {
		
		h1.invalidate();
		
		return"loginCompany";
    }
	
	//Show Company Profile
	@RequestMapping("/profilec")
	public String companyprofile(HttpSession h1,ModelMap mm) {
		
		
		
		//Company Data
                   //value of data
     List<Company>  companydatalist=(List<Company>) h1.getAttribute("Companydata");
                    //key of companydata
     mm.addAttribute("companykey",companydatalist);
		
		
		return "profilec";
	}
	
	//Update Company Profile
	@RequestMapping(value = "/updateprofilec",method = RequestMethod.POST)
	public String updateprofile(@ModelAttribute("c1") Company c1, @RequestParam("filename") MultipartFile filename,ModelMap mm) throws IOException {
		
		
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
				
				pd.updatedataofprofile(c1);
				
				mm.addAttribute("profilem","Profile is Sucessfully Updated Login Now");
				
				return "loginCompany";
				
		
		
	}
	
	//Post Job Page
	@RequestMapping("/postjob")
	public String postjobpage(HttpSession h1,ModelMap mm) {
		
		//Company Data
        //value of data
List<Company>  companydatalist=(List<Company>) h1.getAttribute("Companydata");
         //key of companydata
mm.addAttribute("companykey",companydatalist);
		
		
		return "postjob";
	}
	
	//Post Job Mapping 
	@RequestMapping(value = "/postjob",method = RequestMethod.POST)
	public String postjobdata(@ModelAttribute("c1") postjob c1) {
		
		
		pd.postdetails(c1);
		
		return "postjob";
	}
	
	@RequestMapping("/postproject")
	public String postprojectpage(HttpSession h1,ModelMap mm) {
		
		//Company Data
        //value of data
List<Company>  companydatalist=(List<Company>) h1.getAttribute("Companydata");
         //key of companydata
mm.addAttribute("companykey",companydatalist);
		
		return "postproject";
	}
	
	@RequestMapping(value = "/postprojectdata",method = RequestMethod.POST)
	public String postprojectdetails(@ModelAttribute("c1") postproject c1,@RequestParam("projectfile")MultipartFile filename) throws IOException {
		
		        //for file name
				String f=filename.getOriginalFilename();
				
				//for file Storage
				String path="C:\\Users\\gaura\\eclipse-workspace\\ProjectSpringMVC_Java1\\src\\main\\webapp\\files\\webimages";
				
				//Concate of file name and file storage
				BufferedOutputStream bf = new BufferedOutputStream(new FileOutputStream(path+"/"+f));
				
				byte b []=filename.getBytes();
				
				bf.write(b);
				
				bf.close();
				
				c1.setProjectpdf(f);
		
		
		        pd.postprojectdata(c1);
		
		
		return "postproject";
		
	} 
	
	@RequestMapping("/viewcadidatesapplicartion/{name}")
	public String viewcadidatesapplicartion(@PathVariable String name,ModelMap mm) {
	
    //Show Jobs Application Applied By candidates
	List<applyjob> cadidatesalldata	=pd.getapplicationdata(name);
	
	mm.addAttribute("applicationdata",cadidatesalldata);
	
	
	//Show Project Application Applied By candidates
	List<applyproject> projectapplicationdata= pd.getprojectapplicationcompany(name);
	
	mm.addAttribute("projectapplicationdata",projectapplicationdata);
		
		
		return "viewcadidatesapplicartion";
	}
	
	
	
	
	
	
	
	
	
	
	

}
