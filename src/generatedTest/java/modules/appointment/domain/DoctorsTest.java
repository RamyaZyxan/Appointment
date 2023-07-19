package modules.appointment.domain;

import org.skyve.util.DataBuilder;
import org.skyve.util.test.SkyveFixture.FixtureType;
import util.AbstractDomainTest;

/**
 * Generated - local changes will be overwritten.
 * Extend {@link AbstractDomainTest} to create your own tests for this document.
 */
public class DoctorsTest extends AbstractDomainTest<Doctors> {

	@Override
	protected Doctors getBean() throws Exception {
		return new DataBuilder()
			.fixture(FixtureType.crud)
			.build(Doctors.MODULE_NAME, Doctors.DOCUMENT_NAME);
	}
}