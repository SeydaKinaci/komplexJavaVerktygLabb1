package org.example.komplexJavaLabb1.presentation;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

public class ArchitectureTest {

    JavaClasses importedClasses = new ClassFileImporter().importPackages("org.example.komplexJavaLabb1");

    @Test
    void presentationLayerShouldOnlyDependOnBusinessLayer() {

        ArchRule rule = noClasses().that().resideInAPackage("..presentation")
                .should().dependOnClassesThat().resideInAnyPackage( "..persistence")
        .because("Presentation layer should only depend on business layer");

        rule.check(importedClasses);
    }

    @Test
    void businessLayerShouldOnlyDependOnPersistenceLayer() {

        ArchRule rule = noClasses().that().resideInAPackage("..business")
                .should().dependOnClassesThat().resideInAnyPackage( "..presentation..")
                .because("Business layer should only depend on presentation layer");

        rule.check(importedClasses);
    }

    @Test
    void onlyClassesNamedRepositoryAllowedInPersistencePackage() {
        ArchRule rule =
                classes().that()
                        .haveSimpleNameEndingWith("Repository")
                        .should().resideInAPackage("..persistence..")
                        .because("repository belongs to persistence layer");

        rule.check(importedClasses);
    }
}
