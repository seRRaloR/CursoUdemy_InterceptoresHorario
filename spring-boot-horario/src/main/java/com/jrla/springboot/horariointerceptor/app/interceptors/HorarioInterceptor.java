package com.jrla.springboot.horariointerceptor.app.interceptors;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component("horarioInterceptor")
public class HorarioInterceptor implements HandlerInterceptor {

	@Value("${config.horario.apertura}")
	private Integer horaInicio;

	@Value("${config.horario.cierre}")
	private Integer horaFin;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		Calendar calendario = Calendar.getInstance();
		int hora = calendario.get(Calendar.HOUR_OF_DAY);
		
		if (horaInicio <= hora && hora < horaFin) {
			
			StringBuilder mensaje = new StringBuilder("Bienvenido al horario de atención al cliente");
			mensaje.append(", atendemos desde las ");
			mensaje.append(horaInicio);
			mensaje.append(" hrs. hasta las ");
			mensaje.append(horaFin);
			mensaje.append(" hrs.");
			mensaje.append(" Gracias por su visita.");
			
			request.setAttribute("mensaje", mensaje.toString());

			return true;
		}
		
		response.sendRedirect(request.getContextPath().concat("/cerrado"));
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		String mensaje = (String) request.getAttribute("mensaje");
		modelAndView.addObject("mensaje", mensaje);
	}
	

}
