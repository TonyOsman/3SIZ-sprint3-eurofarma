package com.eurofarma

import com.eurofarma.model.*
import com.eurofarma.repository.*
import com.eurofarma.service.CursoService
import io.mockk.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import java.util.*

class CursoServiceTest {

    private lateinit var cursoRepository: CursoRepository
    private lateinit var licaoRepository: LicaoRepository
    private lateinit var progressoRepository: ProgressoRepository
    private lateinit var cursoService: CursoService

    @BeforeEach
    fun setup() {
        cursoRepository = mockk()
        licaoRepository = mockk()
        progressoRepository = mockk()
        cursoService = CursoService(cursoRepository, licaoRepository, progressoRepository)
    }

    @Test
    fun `listarCursos deve retornar todos os cursos quando tipo e null`() {
        val cursos = listOf(
            Curso(id = 1, titulo = "Curso 1", tipo = TipoCurso.OBRIGATORIO),
            Curso(id = 2, titulo = "Curso 2", tipo = TipoCurso.ESPECIFICO)
        )
        val pageable = PageRequest.of(0, 10)
        every { cursoRepository.findAll(pageable) } returns PageImpl(cursos)

        val result = cursoService.listarCursos(null, pageable)

        assertEquals(2, result.totalElements)
        verify { cursoRepository.findAll(pageable) }
    }

    @Test
    fun `listarCursos deve filtrar por tipo quando especificado`() {
        val cursos = listOf(Curso(id = 1, titulo = "Curso 1", tipo = TipoCurso.OBRIGATORIO))
        val pageable = PageRequest.of(0, 10)
        every { cursoRepository.findByTipo(TipoCurso.OBRIGATORIO, pageable) } returns PageImpl(cursos)

        val result = cursoService.listarCursos(TipoCurso.OBRIGATORIO, pageable)

        assertEquals(1, result.totalElements)
        assertEquals(TipoCurso.OBRIGATORIO, result.content[0].tipo)
    }

    @Test
    fun `calcularProgresso deve retornar 0 quando nao ha licoes`() {
        every { licaoRepository.findByCursoId(1L) } returns emptyList()

        val progresso = cursoService.calcularProgresso(1L, 1L)

        assertEquals(0f, progresso)
    }

    @Test
    fun `calcularProgresso deve calcular corretamente`() {
        val modulo = mockk<Modulo>()
        val licoes = listOf(
            Licao(id = 1, titulo = "Licao 1", modulo = modulo),
            Licao(id = 2, titulo = "Licao 2", modulo = modulo)
        )
        every { licaoRepository.findByCursoId(1L) } returns licoes
        every { progressoRepository.countConcluidasByUsuarioIdAndCursoId(1L, 1L) } returns 1L

        val progresso = cursoService.calcularProgresso(1L, 1L)

        assertEquals(0.5f, progresso)
    }

    @Test
    fun `incrementarVisualizacoes deve incrementar contador`() {
        val curso = Curso(id = 1, titulo = "Curso", tipo = TipoCurso.OBRIGATORIO, visualizacoes = 10)
        every { cursoRepository.findById(1L) } returns Optional.of(curso)
        every { cursoRepository.save(any()) } returns curso

        cursoService.incrementarVisualizacoes(1L)

        assertEquals(11, curso.visualizacoes)
        verify { cursoRepository.save(curso) }
    }
}
