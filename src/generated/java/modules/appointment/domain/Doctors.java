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
import org.skyve.domain.types.Enumeration;
import org.skyve.domain.types.TimeOnly;
import org.skyve.impl.domain.AbstractPersistentBean;
import org.skyve.impl.domain.types.jaxb.TimeOnlyMapper;
import org.skyve.metadata.model.document.Bizlet.DomainValue;
import org.skyve.util.Util;

/**
 * Doctors
 * 
 * @depend - - - Gender
 * @stereotype "persistent"
 */
@XmlType
@XmlRootElement
public class Doctors extends AbstractPersistentBean {
	/**
	 * For Serialization
	 * @hidden
	 */
	private static final long serialVersionUID = 1L;

	/** @hidden */
	public static final String MODULE_NAME = "appointment";

	/** @hidden */
	public static final String DOCUMENT_NAME = "Doctors";

	/** @hidden */
	public static final String namePropertyName = "name";

	/** @hidden */
	public static final String genderPropertyName = "gender";

	/** @hidden */
	public static final String startTimePropertyName = "startTime";

	/** @hidden */
	public static final String endTimePropertyName = "endTime";

	/**
	 * Gender
	 **/
	@XmlEnum
	public static enum Gender implements Enumeration {
		male("Male", "Male"),
		female("Female", "Female");

		private String code;
		private String description;

		/** @hidden */
		private DomainValue domainValue;

		/** @hidden */
		private static List<DomainValue> domainValues;

		private Gender(String code, String description) {
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

		public static Gender fromCode(String code) {
			Gender result = null;

			for (Gender value : values()) {
				if (value.code.equals(code)) {
					result = value;
					break;
				}
			}

			return result;
		}

		public static Gender fromLocalisedDescription(String description) {
			Gender result = null;

			for (Gender value : values()) {
				if (value.toLocalisedDescription().equals(description)) {
					result = value;
					break;
				}
			}

			return result;
		}

		public static List<DomainValue> toDomainValues() {
			if (domainValues == null) {
				Gender[] values = values();
				domainValues = new ArrayList<>(values.length);
				for (Gender value : values) {
					domainValues.add(value.domainValue);
				}
			}

			return domainValues;
		}
	}

	/**
	 * Name
	 **/
	private String name;

	/**
	 * Gender
	 **/
	private Gender gender;

	/**
	 * Start Time
	 **/
	private TimeOnly startTime;

	/**
	 * End Time
	 **/
	private TimeOnly endTime;

	@Override
	@XmlTransient
	public String getBizModule() {
		return Doctors.MODULE_NAME;
	}

	@Override
	@XmlTransient
	public String getBizDocument() {
		return Doctors.DOCUMENT_NAME;
	}

	public static Doctors newInstance() {
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
			return org.skyve.util.Binder.formatMessage("{name}", this);
		}
		catch (@SuppressWarnings("unused") Exception e) {
			return "Unknown";
		}
	}

	@Override
	public boolean equals(Object o) {
		return ((o instanceof Doctors) && 
					this.getBizId().equals(((Doctors) o).getBizId()));
	}

	/**
	 * {@link #name} accessor.
	 * @return	The value.
	 **/
	public String getName() {
		return name;
	}

	/**
	 * {@link #name} mutator.
	 * @param name	The new value.
	 **/
	@XmlElement
	public void setName(String name) {
		preset(namePropertyName, name);
		this.name = name;
	}

	/**
	 * {@link #gender} accessor.
	 * @return	The value.
	 **/
	public Gender getGender() {
		return gender;
	}

	/**
	 * {@link #gender} mutator.
	 * @param gender	The new value.
	 **/
	@XmlElement
	public void setGender(Gender gender) {
		preset(genderPropertyName, gender);
		this.gender = gender;
	}

	/**
	 * {@link #startTime} accessor.
	 * @return	The value.
	 **/
	public TimeOnly getStartTime() {
		return startTime;
	}

	/**
	 * {@link #startTime} mutator.
	 * @param startTime	The new value.
	 **/
	@XmlElement
	@XmlSchemaType(name = "time")
	@XmlJavaTypeAdapter(TimeOnlyMapper.class)
	public void setStartTime(TimeOnly startTime) {
		preset(startTimePropertyName, startTime);
		this.startTime = startTime;
	}

	/**
	 * {@link #endTime} accessor.
	 * @return	The value.
	 **/
	public TimeOnly getEndTime() {
		return endTime;
	}

	/**
	 * {@link #endTime} mutator.
	 * @param endTime	The new value.
	 **/
	@XmlElement
	@XmlSchemaType(name = "time")
	@XmlJavaTypeAdapter(TimeOnlyMapper.class)
	public void setEndTime(TimeOnly endTime) {
		preset(endTimePropertyName, endTime);
		this.endTime = endTime;
	}
}
