package com.project.reactdashboard;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses(packages = "com.project.reactdashboard")
public class HexagonalArchitectureTest {

    private static final DescribedPredicate<JavaClass> domainClasses =
            new DescribedPredicate<>("domain classes excluding JPA interfaces and Test classes") {

                public boolean test(JavaClass input) {
                    boolean inDomain = input.getPackageName().startsWith("com.project.reactdashboard.domain");
                    boolean notTest = !input.getSimpleName().endsWith("Test");
                    return inDomain && notTest;
                }
            };

    @ArchTest
    static final ArchRule domainShouldNotDependOnExternalClasses = noClasses()
            .that(domainClasses)
            .should().dependOnClassesThat()
            .resideOutsideOfPackages("com.project.reactdashboard.domain..", "java..");
}
