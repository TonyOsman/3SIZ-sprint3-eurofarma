package com.eurofarma

import com.eurofarma.model.*
import com.eurofarma.repository.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Tag("integration")
class CursoControllerIntegrationTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var cursoRepository: CursoRepository

    @Autowired
    private lateinit var usuarioRepository: UsuarioRepository

    @BeforeEach
    fun setup() {
        cursoRepository.deleteAll()
        usuarioRepository.deleteAll()

        cursoRepository.save(Curso(titulo = "Treinamento Obrigatorio", tipo = TipoCurso.OBRIGATORIO))
        cursoRepository.save(Curso(titulo = "Curso Especifico", tipo = TipoCurso.ESPECIFICO))
        usuarioRepository.save(Usuario(nome = "Test User", email = "test@test.com", senha = "123", departamento = "TI"))
    }

    @Test
    fun `GET cursos deve retornar lista de cursos`() {
        mockMvc.get("/api/cursos") {
            contentType = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
            jsonPath("$.content") { isArray() }
            jsonPath("$.totalElements") { value(2) }
        }
    }

    @Test
    fun `GET cursos com filtro tipo deve filtrar resultados`() {
        mockMvc.get("/api/cursos?tipo=OBRIGATORIO") {
            contentType = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
            jsonPath("$.totalElements") { value(1) }
            jsonPath("$.content[0].titulo") { value("Treinamento Obrigatorio") }
        }
    }

    @Test
    fun `GET curso por id deve retornar curso`() {
        val curso = cursoRepository.findAll().first()

        mockMvc.get("/api/cursos/${curso.id}") {
            contentType = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
            jsonPath("$.titulo") { value(curso.titulo) }
        }
    }

    @Test
    fun `GET curso inexistente deve retornar 404`() {
        mockMvc.get("/api/cursos/99999") {
            contentType = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isNotFound() }
        }
    }
}
