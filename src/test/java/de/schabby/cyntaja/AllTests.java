package de.schabby.cyntaja;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        HelloWorld.class,
        StringAsArray.class,
        Fibonacci.class})
public class AllTests {

    public void runAllTests() {

    }

}
