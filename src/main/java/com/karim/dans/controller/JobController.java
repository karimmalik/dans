package com.karim.dans.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.karim.dans.model.Job;

@RestController
public class JobController {

	@GetMapping("/list")
	public String jobList(Model model){
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Job> jobs = restTemplate.getForEntity("https://jobs.github.com/positions.json", Job.class);
		
		model.addAttribute("jobs", jobs);
		
		return "list";
	}
	
	@GetMapping("/list/{id}")
	public String jobListDetail(Model model){
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Job> jobs = restTemplate.getForEntity("https://jobs.github.com/positions.json/{id}", Job.class);
		
		model.addAttribute("jobs", jobs);
		
		return "list";
	}
	
	@GetMapping("/listJob")
	public String helloWorld(Model model) {
//		RestTemplate restTemplete = new RestTemplate();
//		ResponseEntity<Userx> user = restTemplete.getForEntity("https://jsonplaceholder.typicode.com/todos/1", Userx.class);
		
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			List<Job> jobs = (List<Job>) mapper.readValue(new URL("https://jobs.github.com/positions.json"), Job.class);
			model.addAttribute("jobs", jobs);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "list";
	}
	
//	public String helloWorld(Model model) throws IOException {		
//		
//		URL url;
//		try {
//			url = new URL ("https://jobs.github.com/positions/4ea5f5a8-51fd-4c6d-ae29-040f3ab569c5.json");
//			InputStreamReader reader = new InputStreamReader(url.openStream());
//			Job job = new Gson().fromJson(reader, Job.class);
//			model.addAttribute("jobs", job);
//		} catch (MalformedURLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		return "list";
//	}
	
	
}
