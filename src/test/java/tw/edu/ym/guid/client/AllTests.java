package tw.edu.ym.guid.client;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import tw.edu.ym.guid.client.field.BirthdayTest;
import tw.edu.ym.guid.client.field.NameTest;
import tw.edu.ym.guid.client.field.TWNationalIdTest;
import tw.edu.ym.guid.client.field.SexTest;
import wmw.util.InputStreamUtilTest;
import wmw.validate.TWNationalIdValidatorTest;

@RunWith(Suite.class)
@SuiteClasses({ GuidClientTest.class, HashcodeTest.class, PIITest.class,
    BirthdayTest.class, NameTest.class, TWNationalIdTest.class, SexTest.class,
    InputStreamUtilTest.class, TWNationalIdValidatorTest.class })
public class AllTests {}
