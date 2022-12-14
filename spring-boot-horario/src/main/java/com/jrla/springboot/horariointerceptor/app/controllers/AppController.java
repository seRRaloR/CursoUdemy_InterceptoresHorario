package com.jrla.springboot.horariointerceptor.app.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

	/*
	@Value("${config.horario.apertura}")
	private Integer horaInicio;

*/
	
	@Value("${config.horario.cierre}")
	private Integer horaFin;
	

	@GetMapping({"/","/index"})
	public String index(Model modelo) {
		modelo.addAttribute("titulo","Bienvenido al horario de atención al público");
		return "index";
	}
	
	@GetMapping("/cerrado")
	public String cerrado(Model modelo) {
		
		StringBuilder mensaje = new StringBuilder("Cerrado. Visítenos desde las ");
		//mensaje.append(horaInicio);
		mensaje.append(" hrs. hasta las ");
		mensaje.append(horaFin);
		mensaje.append(" hrs.");
		modelo.addAttribute("titulo", "Fuera del horario de atención");
		modelo.addAttribute("mensaje",mensaje.toString());
		return "cerrado";
	}

}
