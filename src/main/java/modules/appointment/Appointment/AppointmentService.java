package modules.appointment.Appointment;

import java.util.List;

import javax.enterprise.inject.Default;
import javax.inject.Inject;

import org.skyve.persistence.Persistence;

import modules.appointment.domain.Appointment;

@Default
public class AppointmentService {
	@Inject
	private transient Persistence persistence;
	
	public List<Appointment> getAllAppointments() {
		return persistence.newDocumentQuery(Appointment.MODULE_NAME, Appointment.DOCUMENT_NAME).beanResults();
	}
}
