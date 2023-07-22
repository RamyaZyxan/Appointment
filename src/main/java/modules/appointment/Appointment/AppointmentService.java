package modules.appointment.Appointment;

import java.util.List;

import javax.enterprise.inject.Default;
import javax.inject.Inject;

import org.skyve.CORE;
import org.skyve.domain.types.DateOnly;
import org.skyve.domain.types.TimeOnly;
import org.skyve.persistence.Persistence;

import modules.appointment.domain.Appointment;
import java.util.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import org.skyve.util.Time;

@Default
public class AppointmentService {
	@Inject
	private transient Persistence persistence;
	
	public List<Appointment> getAllAppointments() {
		return persistence.newDocumentQuery(Appointment.MODULE_NAME, Appointment.DOCUMENT_NAME).beanResults();
	}
	
	@SuppressWarnings("static-method")
	public Appointment getAppointment(final String apptBizId) {
		return CORE.getPersistence().retrieve(Appointment.MODULE_NAME, Appointment.DOCUMENT_NAME, apptBizId);
	}
	
	@SuppressWarnings("static-method")
	public Appointment createAppointment(LocalDateTime startDate, LocalDateTime endDate, String title) {
		Appointment appt = Appointment.newInstance();

		final DateOnly apptDate = Time.asDateOnly(startDate.toLocalDate());
		appt.setAppointmentDate(apptDate);
		appt.setTitle(title);	
		appt.setAppointmentTime(startDate.toLocalTime().toString()+"-"+endDate.toLocalTime().toString());
		appt.setStatus(appt.getStatus());

		return CORE.getPersistence().save(appt);
	}

	
	public void updateAppointmentDate(String apptBizId, int dayDelta) {
		final Appointment appointment = getAppointment(apptBizId);

		final DateOnly existingApptDate = appointment.getAppointmentDate();
		final DateOnly newApptDate = new DateOnly(Date.from(existingApptDate.toInstant().plus(dayDelta, ChronoUnit.DAYS)));

		appointment.setAppointmentDate(newApptDate);
		CORE.getPersistence().save(appointment);
	}
	
}
