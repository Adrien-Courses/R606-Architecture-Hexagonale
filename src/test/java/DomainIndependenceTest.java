import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses(
        packages = "fr.school.library",
        importOptions = ImportOption.DoNotIncludeTests.class
)
class DomainIndependenceTest {

    @ArchTest
    static final ArchRule domain_must_not_depend_on_spring_or_jpa =
            noClasses().that().resideInAPackage("..domain..")
                    .should().dependOnClassesThat().resideInAnyPackage(
                            "org.springframework..",
                            "jakarta.persistence..",
                            "jakarta.transaction..",
                            "org.hibernate.."
                    );
}