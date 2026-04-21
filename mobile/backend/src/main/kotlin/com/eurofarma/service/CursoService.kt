package com.eurofarma.service

import com.eurofarma.model.*
import com.eurofarma.repository.*
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CursoService(
    private val cursoRepository: CursoRepository,
    private val licaoRepository: LicaoRepository,
    private val progressoRepository: ProgressoRepository
) {

    fun listarCursos(tipo: TipoCurso?, pageable: Pageable): Page<Curso> {
        return if (tipo != null) {
            cursoRepository.findByTipo(tipo, pageable)
        } else {
            cursoRepository.findAll(pageable)
        }
    }

    fun obterCurso(id: Long): Curso? {
        return cursoRepository.findByIdWithModulos(id)
    }

    fun calcularProgresso(cursoId: Long, usuarioId: Long): Float {
        val licoes = licaoRepository.findByCursoId(cursoId)
        if (licoes.isEmpty()) return 0f

        val concluidas = progressoRepository.countConcluidasByUsuarioIdAndCursoId(usuarioId, cursoId)
        return concluidas.toFloat() / licoes.size
    }

    @Transactional
    fun incrementarVisualizacoes(cursoId: Long) {
        cursoRepository.findById(cursoId).ifPresent { curso ->
            curso.visualizacoes++
            cursoRepository.save(curso)
        }
    }
}
