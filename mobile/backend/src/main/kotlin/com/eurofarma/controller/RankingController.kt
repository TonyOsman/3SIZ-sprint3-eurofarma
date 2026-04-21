package com.eurofarma.controller

import com.eurofarma.model.*
import com.eurofarma.service.GamificacaoService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/ranking")
@Tag(name = "Ranking", description = "Sistema de ranking e gamificacao")
class RankingController(private val gamificacaoService: GamificacaoService) {

    @GetMapping
    @Operation(summary = "Obter ranking geral")
    fun getRanking(pageable: Pageable): Page<Usuario> = gamificacaoService.getRanking(pageable)

    @GetMapping("/departamento/{nome}")
    @Operation(summary = "Obter ranking por departamento")
    fun getRankingDepartamento(
        @PathVariable nome: String,
        pageable: Pageable
    ): Page<Usuario> = gamificacaoService.getRankingPorDepartamento(nome, pageable)
}

@RestController
@RequestMapping("/api/conquistas")
@Tag(name = "Conquistas", description = "Sistema de conquistas")
class ConquistaController(private val gamificacaoService: GamificacaoService) {

    @GetMapping
    @Operation(summary = "Listar conquistas")
    fun listar(): List<Conquista> = gamificacaoService.listarConquistas()

    @GetMapping("/usuario/{usuarioId}")
    @Operation(summary = "Conquistas do usuario")
    fun listarPorUsuario(@PathVariable usuarioId: Long): List<ConquistaUsuario> =
        gamificacaoService.listarConquistasUsuario(usuarioId)

    @PostMapping("/{id}/desbloquear")
    @Operation(summary = "Desbloquear conquista")
    fun desbloquear(
        @PathVariable id: Long,
        @RequestHeader("X-Usuario-Id") usuarioId: Long
    ): ResponseEntity<ConquistaUsuario> {
        return try {
            ResponseEntity.ok(gamificacaoService.desbloquearConquista(usuarioId, id))
        } catch (e: IllegalStateException) {
            ResponseEntity.status(409).build()
        }
    }
}
