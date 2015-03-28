package by.ostis.common.sctpclientIT.test;

/**
 * @author Andrew Nepogoda Feb 15, 2015
 */
public abstract class AbstractIntegrationTest implements IntegrationTest {

    private String testName;

    public String getTestName() {

        return testName;
    }

    private String testMessage="";

    public String getTestMessage() {

        return testMessage;
    }

    public void setTestMessage(String testMessage) {

        this.testMessage = testMessage;
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

        return "Test : " + this.testName + "\nState: " + this.state + "\nMessage: "
                + this.testMessage + "\n";
    }
}
