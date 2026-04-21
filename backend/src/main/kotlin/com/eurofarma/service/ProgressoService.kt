package com.eurofarma.service

import com.eurofarma.model.*
import com.eurofarma.repository.*
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

data class ProgressoResult(
    val cursoId: Long,
    val licaoId: Long,
    val progressoTotal: Float,
    val xpGanho: Int,
    val novasConquistas: List<Conquista>
)

@Service
class ProgressoService(
    private val progressoRepository: ProgressoRepository,
    private val licaoRepository: LicaoRepository,
    private val usuarioRepository: UsuarioRepository,
    private val cursoService: CursoService,
    private val gamificacaoService: GamificacaoService
) {

    @Transactional
    fun registrarProgresso(usuarioId: Long, licaoId: Long): ProgressoResult {
        val usuario = usuarioRepository.findById(usuarioId)
            .orElseThrow { NoSuchElementException("Usuario nao encontrado") }

        val licao = licaoRepository.findById(licaoId)
            .orElseThrow { NoSuchElementException("Licao nao encontrada") }

        val cursoId = licao.modulo.curso.id

        var progresso = progressoRepository.findByUsuarioIdAndLicaoId(usuarioId, licaoId)
        var xpGanho = 0

        if (progresso == null) {
            progresso = Progresso(
                usuario = usuario,
                licao = licao,
                concluida = true,
                concluidaEm = LocalDateTime.now()
            )
            progressoRepository.save(progresso)

            xpGanho = 50
            usuario.xpTotal += xpGanho
            usuario.videosVistos++
            usuarioRepository.save(usuario)
        } else if (!progresso.concluida) {
            progresso.concluida = true
            progresso.concluidaEm = LocalDateTime.now()
            progressoRepository.save(progresso)

            xpGanho = 50
            usuario.xpTotal += xpGanho
            usuario.videosVistos++
            usuarioRepository.save(usuario)
        }

        val progressoTotal = cursoService.calcularProgresso(cursoId, usuarioId)
        val novasConquistas = gamificacaoService.verificarConquistas(usuario)

        return ProgressoResult(
            cursoId = cursoId,
            licaoId = licaoId,
            progressoTotal = progressoTotal,
            xpGanho = xpGanho,
            novasConquistas = novasConquistas
        )
    }
}
