package com.project.reactdashboard;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses(packages = "com.project.reactdashboard")
public class HexagonalArchitectureTest {

    @ArchTest
    static final ArchRule domainShouldNotDependOnExternalClasses = noClasses()
            .that().resideInAnyPackage("com.project.reactdashboard.domain..")
            .and().haveSimpleNameNotEndingWith("Test")
            .should().accessClassesThat()
            .resideOutsideOfPackages("com.project.reactdashboard.domain..", "java..");
}
