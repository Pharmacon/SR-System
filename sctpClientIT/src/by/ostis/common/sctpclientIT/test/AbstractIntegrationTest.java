package by.ostis.common.sctpclientIT.test;

/**
 * @author Andrew Nepogoda Feb 15, 2015
 */
public abstract class AbstractIntegrationTest implements IntegrationTest {

    private String testName;

    public String getTestName() {

        return testName;
    }

    private String state = "not run";

    public AbstractIntegrationTest(final String testName) {

        this.testName = testName;
    }

    public String getState() {

        return this.state;
    }

    public void setState(final String state) {

        this.state = state;
    }

    @Override
    public String toString() {

        return "Test : " + this.testName + "State: " + this.state;
    }
}
