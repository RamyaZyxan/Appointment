package faces;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import org.skyve.util.Util;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.*;
import org.primefaces.model.*;
import org.skyve.util.Time;
import org.skyve.impl.web.faces.beans.FacesView;

import modules.appointment.Appointment.AppointmentService;
import modules.appointment.domain.Appointment;

@ManagedBean(name = "scheduleView")
@ViewScoped
public class ScheduleView extends FacesView<modules.appointment.domain.Appointment> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5885014553965371209L;

	private ScheduleModel lazyEventModel;
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm-HH:mm");
	private ScheduleModel eventModel;
	private ScheduleEvent<?> event = new DefaultScheduleEvent<>();
	private String rightHeaderTemplate = "dayGridMonth,timeGridWeek,timeGridDay,listYear";
	private String view = "timeGridDay";
	private String slotDuration = "00:15:00";
	private String serverTimeZone = ZoneId.systemDefault().toString();

	@Inject
	private transient AppointmentService appointmentService;

	@PostConstruct
	public void init() {

		eventModel = new DefaultScheduleModel();
		lazyEventModel = new LazyScheduleModel() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1163546514962071948L;

			@Override
			public void loadEvents(LocalDateTime start, LocalDateTime end) {
				// load appointments from the database
				List<Appointment> appointments = appointmentService.getAllAppointments();
				for (Appointment appt : appointments) {

					LocalDateTime startDateTime = LocalDateTime.of(Time.asLocalDate(appt.getAppointmentDate()),
							LocalTime.parse(appt.getStartTime()));
					LocalDateTime endDateTime = LocalDateTime.of(Time.asLocalDate(appt.getAppointmentDate()),
							LocalTime.parse(appt.getEndTime()));

					addEvent(DefaultScheduleEvent.builder().title(appt.getTitle()).startDate(startDateTime)
							.endDate(endDateTime).data(appt.getBizId()) // the bizId of the Skyve record
							.build());
				}
			}
		};
	}

	public LocalDateTime getRandomDateTime(LocalDateTime base) {
		LocalDateTime dateTime = base.withMinute(0).withSecond(0).withNano(0);
		return dateTime.plusDays(((int) (Math.random() * 30)));
	}

	public ScheduleModel getLazyEventModel() {
		return lazyEventModel;
	}

	public ScheduleModel getEventModel() {
		return eventModel;
	}

	public ScheduleEvent<?> getEvent() {
		return event;
	}

	public void setEvent(ScheduleEvent<?> event) {
		this.event = event;
	}

	public String getRightHeaderTemplate() {
		return rightHeaderTemplate;
	}

	public String getServerTimeZone() {
		return serverTimeZone;
	}

	public void setServerTimeZone(String serverTimeZone) {
		this.serverTimeZone = serverTimeZone;
	}

	public void setRightHeaderTemplate(String rightHeaderTemplate) {
		this.rightHeaderTemplate = rightHeaderTemplate;
	}

	public String getSlotDuration() {
		return slotDuration;
	}

	public void setSlotDuration(String slotDuration) {
		this.slotDuration = slotDuration;
	}

	public void addEvent() {

		if (event.getId() == null) {
			Appointment newAppt = appointmentService.createAppointment(event.getStartDate(), event.getEndDate(),
					event.getTitle());
			DefaultScheduleEvent newEvent = (DefaultScheduleEvent) event;
			newEvent.setData(newAppt.getBizId());
			eventModel.addEvent(newEvent);
		} else {

			eventModel.updateEvent(event);
		}
		event = new DefaultScheduleEvent<>();
	}

	public void onDateSelect(SelectEvent<LocalDateTime> selectEvent) {
		event = DefaultScheduleEvent.builder().startDate(selectEvent.getObject())
				.endDate(selectEvent.getObject().plusHours(1)).build();
	}

	public void onEventSelect(SelectEvent<ScheduleEvent<?>> selectEvent) {
		event = selectEvent.getObject();
	}

	public void onEventMove(ScheduleEntryMoveEvent moveEvent) {
		Util.LOGGER.info(String.format("Event %s moved by days %s %s", moveEvent.getScheduleEvent().getId(),
				Integer.valueOf(moveEvent.getDayDelta()), moveEvent.getScheduleEvent().getStartDate()));

		final String apptBizId = (String) moveEvent.getScheduleEvent().getData();
		appointmentService.updateAppointmentDate(apptBizId, moveEvent.getDayDelta());
	}

	public void onEventResize(ScheduleEntryResizeEvent event) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event resized",
				"Start-Delta:" + event.getDeltaStartAsDuration() + ", End-Delta: " + event.getDeltaEndAsDuration());

		addMessage(message);
	}

	private void addMessage(FacesMessage message) {
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view;
	}

}
