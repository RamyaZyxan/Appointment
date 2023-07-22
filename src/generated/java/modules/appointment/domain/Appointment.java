package modules.appointment.domain;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.skyve.CORE;
import org.skyve.domain.messages.DomainException;
import org.skyve.domain.types.DateOnly;
import org.skyve.domain.types.Enumeration;
import org.skyve.impl.domain.AbstractPersistentBean;
import org.skyve.impl.domain.types.jaxb.DateOnlyMapper;
import org.skyve.metadata.model.document.Bizlet.DomainValue;
import org.skyve.util.ExpressionEvaluator;
import org.skyve.util.Util;

/**
 * Appointment
 * 
 * @depend - - - Branch
 * @depend - - - Department
 * @depend - - - Unit
 * @depend - - - Status
 * @navhas n doctors 0..1 Doctors
 * @stereotype "persistent"
 */
@XmlType
@XmlRootElement
public class Appointment extends AbstractPersistentBean {
	/**
	 * For Serialization
	 * @hidden
	 */
	private static final long serialVersionUID = 1L;

	/** @hidden */
	public static final String MODULE_NAME = "appointment";

	/** @hidden */
	public static final String DOCUMENT_NAME = "Appointment";

	/** @hidden */
	public static final String titlePropertyName = "title";

	/** @hidden */
	public static final String appointmentDatePropertyName = "appointmentDate";

	/** @hidden */
	public static final String startTimePropertyName = "startTime";

	/** @hidden */
	public static final String endTimePropertyName = "endTime";

	/** @hidden */
	public static final String appointmentTimePropertyName = "appointmentTime";

	/** @hidden */
	public static final String branchPropertyName = "branch";

	/** @hidden */
	public static final String departmentPropertyName = "department";

	/** @hidden */
	public static final String unitPropertyName = "unit";

	/** @hidden */
	public static final String statusPropertyName = "status";

	/** @hidden */
	public static final String doctorsPropertyName = "doctors";

	/**
	 * Branch
	 **/
	@XmlEnum
	public static enum Branch implements Enumeration {
		KPHB("KPHB", "KPHB"),
		kukatpally("Kukatpally", "Kukatpally");

		private String code;
		private String description;

		/** @hidden */
		private DomainValue domainValue;

		/** @hidden */
		private static List<DomainValue> domainValues;

		private Branch(String code, String description) {
			this.code = code;
			this.description = description;
			this.domainValue = new DomainValue(code, description);
		}

		@Override
		public String toCode() {
			return code;
		}

		@Override
		public String toLocalisedDescription() {
			return Util.i18n(description);
		}

		@Override
		public DomainValue toDomainValue() {
			return domainValue;
		}

		public static Branch fromCode(String code) {
			Branch result = null;

			for (Branch value : values()) {
				if (value.code.equals(code)) {
					result = value;
					break;
				}
			}

			return result;
		}

		public static Branch fromLocalisedDescription(String description) {
			Branch result = null;

			for (Branch value : values()) {
				if (value.toLocalisedDescription().equals(description)) {
					result = value;
					break;
				}
			}

			return result;
		}

		public static List<DomainValue> toDomainValues() {
			if (domainValues == null) {
				Branch[] values = values();
				domainValues = new ArrayList<>(values.length);
				for (Branch value : values) {
					domainValues.add(value.domainValue);
				}
			}

			return domainValues;
		}
	}

	/**
	 * Department
	 **/
	@XmlEnum
	public static enum Department implements Enumeration {
		dental("Dental", "Dental"),
		ENT("ENT", "ENT");

		private String code;
		private String description;

		/** @hidden */
		private DomainValue domainValue;

		/** @hidden */
		private static List<DomainValue> domainValues;

		private Department(String code, String description) {
			this.code = code;
			this.description = description;
			this.domainValue = new DomainValue(code, description);
		}

		@Override
		public String toCode() {
			return code;
		}

		@Override
		public String toLocalisedDescription() {
			return Util.i18n(description);
		}

		@Override
		public DomainValue toDomainValue() {
			return domainValue;
		}

		public static Department fromCode(String code) {
			Department result = null;

			for (Department value : values()) {
				if (value.code.equals(code)) {
					result = value;
					break;
				}
			}

			return result;
		}

		public static Department fromLocalisedDescription(String description) {
			Department result = null;

			for (Department value : values()) {
				if (value.toLocalisedDescription().equals(description)) {
					result = value;
					break;
				}
			}

			return result;
		}

		public static List<DomainValue> toDomainValues() {
			if (domainValues == null) {
				Department[] values = values();
				domainValues = new ArrayList<>(values.length);
				for (Department value : values) {
					domainValues.add(value.domainValue);
				}
			}

			return domainValues;
		}
	}

	/**
	 * Unit
	 **/
	@XmlEnum
	public static enum Unit implements Enumeration {
		dental("Dental", "Dental"),
		ENT("ENT", "ENT");

		private String code;
		private String description;

		/** @hidden */
		private DomainValue domainValue;

		/** @hidden */
		private static List<DomainValue> domainValues;

		private Unit(String code, String description) {
			this.code = code;
			this.description = description;
			this.domainValue = new DomainValue(code, description);
		}

		@Override
		public String toCode() {
			return code;
		}

		@Override
		public String toLocalisedDescription() {
			return Util.i18n(description);
		}

		@Override
		public DomainValue toDomainValue() {
			return domainValue;
		}

		public static Unit fromCode(String code) {
			Unit result = null;

			for (Unit value : values()) {
				if (value.code.equals(code)) {
					result = value;
					break;
				}
			}

			return result;
		}

		public static Unit fromLocalisedDescription(String description) {
			Unit result = null;

			for (Unit value : values()) {
				if (value.toLocalisedDescription().equals(description)) {
					result = value;
					break;
				}
			}

			return result;
		}

		public static List<DomainValue> toDomainValues() {
			if (domainValues == null) {
				Unit[] values = values();
				domainValues = new ArrayList<>(values.length);
				for (Unit value : values) {
					domainValues.add(value.domainValue);
				}
			}

			return domainValues;
		}
	}

	/**
	 * Status
	 **/
	@XmlEnum
	public static enum Status implements Enumeration {
		available("Available", "Available"),
		booked("Booked", "Booked");

		private String code;
		private String description;

		/** @hidden */
		private DomainValue domainValue;

		/** @hidden */
		private static List<DomainValue> domainValues;

		private Status(String code, String description) {
			this.code = code;
			this.description = description;
			this.domainValue = new DomainValue(code, description);
		}

		@Override
		public String toCode() {
			return code;
		}

		@Override
		public String toLocalisedDescription() {
			return Util.i18n(description);
		}

		@Override
		public DomainValue toDomainValue() {
			return domainValue;
		}

		public static Status fromCode(String code) {
			Status result = null;

			for (Status value : values()) {
				if (value.code.equals(code)) {
					result = value;
					break;
				}
			}

			return result;
		}

		public static Status fromLocalisedDescription(String description) {
			Status result = null;

			for (Status value : values()) {
				if (value.toLocalisedDescription().equals(description)) {
					result = value;
					break;
				}
			}

			return result;
		}

		public static List<DomainValue> toDomainValues() {
			if (domainValues == null) {
				Status[] values = values();
				domainValues = new ArrayList<>(values.length);
				for (Status value : values) {
					domainValues.add(value.domainValue);
				}
			}

			return domainValues;
		}
	}

	/**
	 * Title
	 * <br/>
	 * The title of the appointment.
	 **/
	private String title;

	/**
	 * Appointment Date
	 **/
	private DateOnly appointmentDate = (DateOnly) ExpressionEvaluator.evaluate("{DATE}", this);

	/**
	 * Start Time
	 **/
	private String startTime;

	/**
	 * End Time
	 **/
	private String endTime;

	/**
	 * Appointment Time
	 **/
	private String appointmentTime;

	/**
	 * Branch
	 **/
	private Branch branch;

	/**
	 * Department
	 **/
	private Department department;

	/**
	 * Unit
	 **/
	private Unit unit;

	/**
	 * Status
	 **/
	private Status status;

	/**
	 * Doctor
	 **/
	private Doctors doctors = null;

	@Override
	@XmlTransient
	public String getBizModule() {
		return Appointment.MODULE_NAME;
	}

	@Override
	@XmlTransient
	public String getBizDocument() {
		return Appointment.DOCUMENT_NAME;
	}

	public static Appointment newInstance() {
		try {
			return CORE.getUser().getCustomer().getModule(MODULE_NAME).getDocument(CORE.getUser().getCustomer(), DOCUMENT_NAME).newInstance(CORE.getUser());
		}
		catch (RuntimeException e) {
			throw e;
		}
		catch (Exception e) {
			throw new DomainException(e);
		}
	}

	@Override
	@XmlTransient
	public String getBizKey() {
		try {
			return org.skyve.util.Binder.formatMessage("{title}", this);
		}
		catch (@SuppressWarnings("unused") Exception e) {
			return "Unknown";
		}
	}

	@Override
	public boolean equals(Object o) {
		return ((o instanceof Appointment) && 
					this.getBizId().equals(((Appointment) o).getBizId()));
	}

	/**
	 * {@link #title} accessor.
	 * @return	The value.
	 **/
	public String getTitle() {
		return title;
	}

	/**
	 * {@link #title} mutator.
	 * @param title	The new value.
	 **/
	@XmlElement
	public void setTitle(String title) {
		preset(titlePropertyName, title);
		this.title = title;
	}

	/**
	 * {@link #appointmentDate} accessor.
	 * @return	The value.
	 **/
	public DateOnly getAppointmentDate() {
		return appointmentDate;
	}

	/**
	 * {@link #appointmentDate} mutator.
	 * @param appointmentDate	The new value.
	 **/
	@XmlElement
	@XmlSchemaType(name = "date")
	@XmlJavaTypeAdapter(DateOnlyMapper.class)
	public void setAppointmentDate(DateOnly appointmentDate) {
		preset(appointmentDatePropertyName, appointmentDate);
		this.appointmentDate = appointmentDate;
	}

	/**
	 * {@link #startTime} accessor.
	 * @return	The value.
	 **/
	public String getStartTime() {
		return startTime;
	}

	/**
	 * {@link #startTime} mutator.
	 * @param startTime	The new value.
	 **/
	@XmlElement
	public void setStartTime(String startTime) {
		preset(startTimePropertyName, startTime);
		this.startTime = startTime;
	}

	/**
	 * {@link #endTime} accessor.
	 * @return	The value.
	 **/
	public String getEndTime() {
		return endTime;
	}

	/**
	 * {@link #endTime} mutator.
	 * @param endTime	The new value.
	 **/
	@XmlElement
	public void setEndTime(String endTime) {
		preset(endTimePropertyName, endTime);
		this.endTime = endTime;
	}

	/**
	 * {@link #appointmentTime} accessor.
	 * @return	The value.
	 **/
	public String getAppointmentTime() {
		return appointmentTime;
	}

	/**
	 * {@link #appointmentTime} mutator.
	 * @param appointmentTime	The new value.
	 **/
	@XmlElement
	public void setAppointmentTime(String appointmentTime) {
		preset(appointmentTimePropertyName, appointmentTime);
		this.appointmentTime = appointmentTime;
	}

	/**
	 * {@link #branch} accessor.
	 * @return	The value.
	 **/
	public Branch getBranch() {
		return branch;
	}

	/**
	 * {@link #branch} mutator.
	 * @param branch	The new value.
	 **/
	@XmlElement
	public void setBranch(Branch branch) {
		preset(branchPropertyName, branch);
		this.branch = branch;
	}

	/**
	 * {@link #department} accessor.
	 * @return	The value.
	 **/
	public Department getDepartment() {
		return department;
	}

	/**
	 * {@link #department} mutator.
	 * @param department	The new value.
	 **/
	@XmlElement
	public void setDepartment(Department department) {
		preset(departmentPropertyName, department);
		this.department = department;
	}

	/**
	 * {@link #unit} accessor.
	 * @return	The value.
	 **/
	public Unit getUnit() {
		return unit;
	}

	/**
	 * {@link #unit} mutator.
	 * @param unit	The new value.
	 **/
	@XmlElement
	public void setUnit(Unit unit) {
		preset(unitPropertyName, unit);
		this.unit = unit;
	}

	/**
	 * {@link #status} accessor.
	 * @return	The value.
	 **/
	public Status getStatus() {
		return status;
	}

	/**
	 * {@link #status} mutator.
	 * @param status	The new value.
	 **/
	@XmlElement
	public void setStatus(Status status) {
		preset(statusPropertyName, status);
		this.status = status;
	}

	/**
	 * {@link #doctors} accessor.
	 * @return	The value.
	 **/
	public Doctors getDoctors() {
		return doctors;
	}

	/**
	 * {@link #doctors} mutator.
	 * @param doctors	The new value.
	 **/
	@XmlElement
	public void setDoctors(Doctors doctors) {
		if (this.doctors != doctors) {
			preset(doctorsPropertyName, doctors);
			this.doctors = doctors;
		}
	}
}
