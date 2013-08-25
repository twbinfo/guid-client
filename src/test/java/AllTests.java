import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import tw.edu.ym.guid.client.GuidClientTest;
import tw.edu.ym.guid.client.HashcodeBuilderTest;
import tw.edu.ym.guid.client.PIITest;
import tw.edu.ym.guid.client.field.BirthdayTest;
import tw.edu.ym.guid.client.field.NameTest;
import tw.edu.ym.guid.client.field.NationalIdTest;
import tw.edu.ym.guid.client.field.SexTest;
import wmw.util.InputStreamUtilTest;
import wmw.validate.NationalIdValidatorTest;

@RunWith(Suite.class)
@SuiteClasses({ GuidClientTest.class, HashcodeBuilderTest.class, PIITest.class,
    BirthdayTest.class, NameTest.class, NationalIdTest.class, SexTest.class,
    InputStreamUtilTest.class, NationalIdValidatorTest.class })
public class AllTests {}
