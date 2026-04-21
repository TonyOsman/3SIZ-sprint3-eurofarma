package com.eurofarma.service

import com.eurofarma.model.*
import com.eurofarma.repository.*
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class GamificacaoService(
    private val usuarioRepository: UsuarioRepository,
    private val conquistaRepository: ConquistaRepository,
    private val conquistaUsuarioRepository: ConquistaUsuarioRepository
) {

    fun getRanking(pageable: Pageable): Page<Usuario> {
        return usuarioRepository.findAllByOrderByXpTotalDesc(pageable)
    }

    fun getRankingPorDepartamento(departamento: String, pageable: Pageable): Page<Usuario> {
        return usuarioRepository.findByDepartamentoOrderByXpTotalDesc(departamento, pageable)
    }

    fun listarConquistas(): List<Conquista> {
        return conquistaRepository.findAll()
    }

    fun listarConquistasUsuario(usuarioId: Long): List<ConquistaUsuario> {
        return conquistaUsuarioRepository.findByUsuarioId(usuarioId)
    }

    @Transactional
    fun desbloquearConquista(usuarioId: Long, conquistaId: Long): ConquistaUsuario {
        val existente = conquistaUsuarioRepository.findByUsuarioIdAndConquistaId(usuarioId, conquistaId)
        if (existente != null) {
            throw IllegalStateException("Conquista ja desbloqueada")
        }

        val usuario = usuarioRepository.findById(usuarioId)
            .orElseThrow { NoSuchElementException("Usuario nao encontrado") }

        val conquista = conquistaRepository.findById(conquistaId)
            .orElseThrow { NoSuchElementException("Conquista nao encontrada") }

        val conquistaUsuario = ConquistaUsuario(
            usuario = usuario,
            conquista = conquista
        )

        usuario.xpTotal += conquista.xp
        usuarioRepository.save(usuario)

        return conquistaUsuarioRepository.save(conquistaUsuario)
    }

    @Transactional
    fun verificarConquistas(usuario: Usuario): List<Conquista> {
        val novasConquistas = mutableListOf<Conquista>()
        val conquistasExistentes = conquistaUsuarioRepository.findByUsuarioId(usuario.id)
            .map { it.conquista.id }
            .toSet()

        val todasConquistas = conquistaRepository.findAll()

        for (conquista in todasConquistas) {
            if (conquista.id in conquistasExistentes) continue

            val deveDesbloquear = when (conquista.nome) {
                "Introducao" -> usuario.videosVistos >= 1
                "LGPD" -> usuario.cursosConcluidos >= 1
                "Etica no trabalho" -> usuario.xpTotal >= 500
                "Metas" -> usuario.xpTotal >= 1000
                else -> false
            }

            if (deveDesbloquear) {
                val cu = ConquistaUsuario(usuario = usuario, conquista = conquista)
                conquistaUsuarioRepository.save(cu)
                usuario.xpTotal += conquista.xp
                novasConquistas.add(conquista)
            }
        }

        if (novasConquistas.isNotEmpty()) {
            usuarioRepository.save(usuario)
        }

        return novasConquistas
    }

    fun atualizarNivel(usuario: Usuario) {
        val novoNivel = when {
            usuario.xpTotal >= NivelUsuario.MESTRE.xpMinimo -> "Mestre"
            usuario.xpTotal >= NivelUsuario.EXPERT.xpMinimo -> "Expert"
            usuario.xpTotal >= NivelUsuario.AVANCADO.xpMinimo -> "Avancado"
            usuario.xpTotal >= NivelUsuario.INTERMEDIARIO.xpMinimo -> "Intermediario"
            usuario.xpTotal >= NivelUsuario.BASICO.xpMinimo -> "Basico"
            else -> "Iniciante"
        }

        if (usuario.nivel != novoNivel) {
            usuario.nivel = novoNivel
            usuarioRepository.save(usuario)
        }
    }
}
