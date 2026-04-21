package com.eurofarma.controller

import com.eurofarma.model.*
import com.eurofarma.service.*
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/cursos")
@Tag(name = "Cursos", description = "Gerenciamento de cursos e treinamentos")
class CursoController(
    private val cursoService: CursoService,
    private val progressoService: ProgressoService
) {

    @GetMapping
    @Operation(summary = "Listar todos os cursos")
    fun listar(
        @RequestParam tipo: TipoCurso?,
        pageable: Pageable
    ): Page<Curso> = cursoService.listarCursos(tipo, pageable)

    @GetMapping("/{id}")
    @Operation(summary = "Obter detalhes do curso")
    fun obter(@PathVariable id: Long): ResponseEntity<Curso> {
        val curso = cursoService.obterCurso(id) ?: return ResponseEntity.notFound().build()
        cursoService.incrementarVisualizacoes(id)
        return ResponseEntity.ok(curso)
    }

    @PostMapping("/{id}/progresso")
    @Operation(summary = "Registrar progresso em uma licao")
    fun registrarProgresso(
        @PathVariable id: Long,
        @RequestBody request: ProgressoRequest,
        @RequestHeader("X-Usuario-Id") usuarioId: Long
    ): ResponseEntity<ProgressoResult> {
        val result = progressoService.registrarProgresso(usuarioId, request.licaoId)
        return ResponseEntity.ok(result)
    }
}

data class ProgressoRequest(val licaoId: Long)
