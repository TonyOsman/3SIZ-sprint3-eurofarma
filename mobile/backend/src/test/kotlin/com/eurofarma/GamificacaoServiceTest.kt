package com.eurofarma

import com.eurofarma.model.*
import com.eurofarma.repository.*
import com.eurofarma.service.GamificacaoService
import io.mockk.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import java.util.*

class GamificacaoServiceTest {

    private lateinit var usuarioRepository: UsuarioRepository
    private lateinit var conquistaRepository: ConquistaRepository
    private lateinit var conquistaUsuarioRepository: ConquistaUsuarioRepository
    private lateinit var service: GamificacaoService

    @BeforeEach
    fun setup() {
        usuarioRepository = mockk()
        conquistaRepository = mockk()
        conquistaUsuarioRepository = mockk()
        service = GamificacaoService(usuarioRepository, conquistaRepository, conquistaUsuarioRepository)
    }

    @Test
    fun `getRanking deve retornar usuarios ordenados por XP`() {
        val usuarios = listOf(
            Usuario(id = 1, nome = "User1", email = "u1@test.com", senha = "123", departamento = "TI", xpTotal = 1000),
            Usuario(id = 2, nome = "User2", email = "u2@test.com", senha = "123", departamento = "RH", xpTotal = 500)
        )
        val pageable = PageRequest.of(0, 10)
        every { usuarioRepository.findAllByOrderByXpTotalDesc(pageable) } returns PageImpl(usuarios)

        val result = service.getRanking(pageable)

        assertEquals(2, result.totalElements)
        assertEquals(1000, result.content[0].xpTotal)
    }

    @Test
    fun `desbloquearConquista deve adicionar XP ao usuario`() {
        val usuario = Usuario(id = 1, nome = "User", email = "u@test.com", senha = "123", departamento = "TI", xpTotal = 100)
        val conquista = Conquista(id = 1, nome = "LGPD", xp = 500)

        every { conquistaUsuarioRepository.findByUsuarioIdAndConquistaId(1L, 1L) } returns null
        every { usuarioRepository.findById(1L) } returns Optional.of(usuario)
        every { conquistaRepository.findById(1L) } returns Optional.of(conquista)
        every { usuarioRepository.save(any()) } returns usuario
        every { conquistaUsuarioRepository.save(any()) } answers { firstArg() }

        val result = service.desbloquearConquista(1L, 1L)

        assertEquals(600, usuario.xpTotal)
        assertEquals(conquista, result.conquista)
    }

    @Test
    fun `desbloquearConquista deve lancar excecao se ja desbloqueada`() {
        val conquistaUsuario = mockk<ConquistaUsuario>()
        every { conquistaUsuarioRepository.findByUsuarioIdAndConquistaId(1L, 1L) } returns conquistaUsuario

        assertThrows(IllegalStateException::class.java) {
            service.desbloquearConquista(1L, 1L)
        }
    }

    @Test
    fun `atualizarNivel deve mudar nivel quando XP suficiente`() {
        val usuario = Usuario(id = 1, nome = "User", email = "u@test.com", senha = "123", departamento = "TI", xpTotal = 3500, nivel = "Intermediario")
        every { usuarioRepository.save(any()) } returns usuario

        service.atualizarNivel(usuario)

        assertEquals("Avancado", usuario.nivel)
    }
}
