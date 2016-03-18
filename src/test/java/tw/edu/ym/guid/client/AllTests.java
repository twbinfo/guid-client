package tw.edu.ym.guid.client;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import tw.edu.ym.guid.client.annotation.FactorExtractorTest;
import tw.edu.ym.guid.client.field.BaseNationalIdTest;
import tw.edu.ym.guid.client.field.BirthdayTest;
import tw.edu.ym.guid.client.field.BirthplaceTest;
import tw.edu.ym.guid.client.field.NameTest;
import tw.edu.ym.guid.client.field.NationalityTest;
import tw.edu.ym.guid.client.field.SexTest;
import tw.edu.ym.guid.client.field.TWNationalIdTest;
import tw.edu.ym.guid.client.hashcode.FieldSetTest;
import tw.edu.ym.guid.client.hashcode.GuidHashcodeGeneratorTest;
import wmw.i18n.NationTest;
import wmw.validate.TWNationalIdValidatorTest;

@RunWith(Suite.class)
@SuiteClasses({ GuidClientTest.class, GuidHashcodeGeneratorTest.class,
    PIITest.class, BaseNationalIdTest.class, BirthdayTest.class,
    BirthplaceTest.class, NameTest.class, NationalityTest.class, SexTest.class,
    TWNationalIdTest.class, NationTest.class, TWNationalIdValidatorTest.class,
    FieldSetTest.class, FactorExtractorTest.class })
public class AllTests {}
