package com.cts.airticketreservation.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cts.airticketreservation.model.Admin;
import com.cts.airticketreservation.model.Flights;
import com.cts.airticketreservation.service.FlightService;
import com.cts.airticketreservation.service.LoginService;

@SessionAttributes({"admin"})
@Controller
public class AdminController {

	@Autowired
	LoginService service;
	@Autowired
	FlightService fservice;
	
	Admin admindata;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	ArrayList<Flights> Flight_list = new ArrayList<Flights>();
	

	@GetMapping("/")
	public String showWelcomePage() {
		return "welcome";
	}

	@GetMapping("/admin_login")
	public String showAdminLogin(@ModelAttribute("admin") Admin admin, BindingResult result) {
		return "admin_login";
	}

	@PostMapping("/admin_login")
	public String validateAdmin(@RequestParam String username, @RequestParam String password, ModelMap model) {

		admindata = new Admin(username, password);
		admindata.setUsername(username);
		admindata.setPassword(password);
		if (service.validateAdmin(username, password))
		{	
		
			return "admin_rights";
		}
		else {
			model.addAttribute("message", "Wrong Credentials. Please try again!");
			return "admin_login";
		}
	}

	@GetMapping("/admin_rights")
	public String showAdminRights(@SessionAttribute("admin") Admin admin) {
		return "admin_rights";
	}

	@GetMapping("/admin_edit_flight_details")
	public String showFlightDetailstoEdit(ModelMap model,@SessionAttribute("admin") Admin admin ) {

		LOGGER.info("Start");
		model.addAttribute("Flight_list", fservice.getFlight_details());
		LOGGER.info("End");
		return "admin_edit_flight_details";
	}

	@GetMapping("/admin_add_flight")
	public String showAddFlightForm(@SessionAttribute("admin") Admin admin) {
		return "admin_add_flight";
	}

	@PostMapping("/admin_add_flight")
	public String addFlightDetails(ModelMap model, @RequestParam String flightno, @RequestParam String from,
			@RequestParam String to, @RequestParam String dept_date, @RequestParam String arr_date,
			@RequestParam String dept_time, @RequestParam String arr_time, @RequestParam int e_seats_left,
			@RequestParam int b_seats_left, @RequestParam float e_seat_price, @RequestParam float b_seat_price,
			@RequestParam String flight_company, @RequestParam String status, @SessionAttribute("admin") Admin admin) {
	
		LOGGER.info("Start");
		fservice.addFlight(flightno, from, to, dept_date, arr_date, dept_time, arr_time, e_seats_left, b_seats_left,
				e_seat_price, b_seat_price, flight_company, status);
		model.addAttribute("Flight_list", fservice.getFlight_details());
		LOGGER.info("End");

		return "admin_edit_flight_details";
	}

	@GetMapping("/edit_flight_details")
	public String showEditFlightdetails(@RequestParam String flightno, ModelMap model,@SessionAttribute("admin") Admin admin ) {
		model.addAttribute("flight", fservice.getFlight(flightno));
		return "edit_flight_details";
	}

	@PostMapping("/edit_flight_details")
	public String modifyFlightDetails(@RequestParam String flightno,  Flights flight,
			BindingResult bindingResult, ModelMap model,@SessionAttribute("admin") Admin admin ) {
		LOGGER.info("Start");
		if (bindingResult.hasErrors()) {
			return "admin_edit_flight_details";
		}

		fservice.updateFlight(flight);
		model.addAttribute("Flight_list", fservice.getFlight_details());
		LOGGER.info("End");

		return "admin_edit_flight_details";
	}

	@GetMapping("/delete_flight_details")
	public String showDeletingFlightDetail(@SessionAttribute("admin") Admin admin) {
		return "delete_flight_details";
	}

	@PostMapping("/delete_flight_details")
	public String deleteFlightDetails(@RequestParam String flightno, ModelMap model,@SessionAttribute("admin") Admin admin ) {
		LOGGER.info("Start");
		fservice.deleteFlight(flightno);
		model.addAttribute("Flight_list", fservice.getFlight_details());
		LOGGER.info("Start");
		return "admin_edit_flight_details";
	}
	
	@GetMapping("/logout")
	public String logoutAdmin(@ModelAttribute Admin admin, HttpSession session, SessionStatus status) {

		status.setComplete();
		session.removeAttribute("admin");
		return "redirect:/";
	}
	
	@ExceptionHandler(value = Exception.class)
	public String exceptionHandlerGeneric(HttpSession session, RedirectAttributes r) {
		session.invalidate();
		r.addFlashAttribute("message", "Please login first.");
		return "redirect:/";
	}
	
	   @ModelAttribute("admin")
	   public Admin populateForm() {
	       return new Admin(); // populates form for the first time if its null
	   }


}
