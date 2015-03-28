package by.ostis.common.sctpclientIT.test;

import java.util.ArrayList;
import java.util.List;

import by.ostis.common.sctpclientIT.constants.TestMessage;

/**
 * @author Andrew Nepogoda Feb 15, 2015
 */
public abstract class AbstractTestPackage implements TestPackage {

    protected List<AbstractIntegrationTest> tests = new ArrayList<>();

    protected String packageName = "Default package";

    public AbstractTestPackage(final String packageName) {

        this.packageName = packageName;
        setTests();
    }

    protected void printTestsState() {

        System.out.println(TestMessage.RUN_TEST_PACKAGE.getValue() + this.packageName);
        for (final AbstractIntegrationTest test : this.tests) {
            System.out.println(test);
        }
    }

    protected abstract void setTests();
}
