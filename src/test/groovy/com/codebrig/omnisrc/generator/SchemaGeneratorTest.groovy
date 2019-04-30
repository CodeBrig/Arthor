package com.codebrig.omnisrc.generator

import com.codebrig.omnisrc.SourceLanguage
import com.codebrig.omnisrc.observe.ObservationConfig
import com.codebrig.omnisrc.observe.ObservedLanguages
import com.codebrig.omnisrc.observe.structure.filter.MultiFilter
import com.codebrig.omnisrc.observe.structure.filter.RoleFilter
import com.codebrig.omnisrc.observe.structure.filter.TypeFilter
import com.codebrig.omnisrc.schema.grakn.GraknSchemaWriter
import org.junit.Test

import static org.junit.Assert.assertEquals

class SchemaGeneratorTest {

    @Test
    void fileAndFunctionOnlySchema() {
        def schemaGenerator = new SchemaGenerator(ObservationConfig.baseStructureWithIndividualAndActualSemanticRoles())
        def multiFilter = new MultiFilter(MultiFilter.MatchStyle.ANY)
        def roleFilter = new RoleFilter("FILE", "DECLARATION_FUNCTION")
        multiFilter.accept(roleFilter)
        multiFilter.accept(new TypeFilter("MethodDeclaration"))
        schemaGenerator.filter = multiFilter

        def goLanguage = schemaGenerator.observeLanguage(SourceLanguage.Go, new File("src/test/resources/same/"))
        def javaLanguage = schemaGenerator.observeLanguage(SourceLanguage.Java, new File("src/test/resources/same/"))
        def javascriptLanguage = schemaGenerator.observeLanguage(SourceLanguage.Javascript, new File("src/test/resources/same/"))
        def phpLanguage = schemaGenerator.observeLanguage(SourceLanguage.Php, new File("src/test/resources/same/"))
        def pythonLanguage = schemaGenerator.observeLanguage(SourceLanguage.Python, new File("src/test/resources/same/"))
        def rubyLanguage = schemaGenerator.observeLanguage(SourceLanguage.Ruby, new File("src/test/resources/same/"))
        def omniLanguage = ObservedLanguages.mergeLanguages(goLanguage, javaLanguage, javascriptLanguage, phpLanguage, pythonLanguage, rubyLanguage)
        def schemaWriter = new GraknSchemaWriter(omniLanguage, goLanguage, javaLanguage, javascriptLanguage, phpLanguage, pythonLanguage, rubyLanguage)
        def verifyFile = new File("src/test/resources/schema/", "Same_Schema.gql")
        assertEquals(verifyFile.text, schemaWriter.fullSchemaDefinition)
    }
}