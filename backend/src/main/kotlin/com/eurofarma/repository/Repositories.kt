package com.eurofarma.repository

import com.eurofarma.model.*
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface UsuarioRepository : JpaRepository<Usuario, Long> {
    fun findByEmail(email: String): Usuario?

    @Query("SELECT u FROM Usuario u ORDER BY u.xpTotal DESC")
    fun findAllByOrderByXpTotalDesc(pageable: Pageable): Page<Usuario>

    @Query("SELECT u FROM Usuario u WHERE u.departamento = :departamento ORDER BY u.xpTotal DESC")
    fun findByDepartamentoOrderByXpTotalDesc(departamento: String, pageable: Pageable): Page<Usuario>
}

@Repository
interface CursoRepository : JpaRepository<Curso, Long> {
    fun findByTipo(tipo: TipoCurso, pageable: Pageable): Page<Curso>

    @Query("SELECT c FROM Curso c LEFT JOIN FETCH c.modulos WHERE c.id = :id")
    fun findByIdWithModulos(id: Long): Curso?
}

@Repository
interface ModuloRepository : JpaRepository<Modulo, Long> {
    fun findByCursoIdOrderByOrdemAsc(cursoId: Long): List<Modulo>
}

@Repository
interface LicaoRepository : JpaRepository<Licao, Long> {
    fun findByModuloIdOrderByOrdemAsc(moduloId: Long): List<Licao>

    @Query("SELECT l FROM Licao l WHERE l.modulo.curso.id = :cursoId")
    fun findByCursoId(cursoId: Long): List<Licao>
}

@Repository
interface ProgressoRepository : JpaRepository<Progresso, Long> {
    fun findByUsuarioIdAndLicaoId(usuarioId: Long, licaoId: Long): Progresso?

    fun findByUsuarioId(usuarioId: Long): List<Progresso>

    @Query("SELECT p FROM Progresso p WHERE p.usuario.id = :usuarioId AND p.licao.modulo.curso.id = :cursoId")
    fun findByUsuarioIdAndCursoId(usuarioId: Long, cursoId: Long): List<Progresso>

    @Query("SELECT COUNT(p) FROM Progresso p WHERE p.usuario.id = :usuarioId AND p.licao.modulo.curso.id = :cursoId AND p.concluida = true")
    fun countConcluidasByUsuarioIdAndCursoId(usuarioId: Long, cursoId: Long): Long
}

@Repository
interface ConquistaRepository : JpaRepository<Conquista, Long>

@Repository
interface ConquistaUsuarioRepository : JpaRepository<ConquistaUsuario, Long> {
    fun findByUsuarioId(usuarioId: Long): List<ConquistaUsuario>
    fun findByUsuarioIdAndConquistaId(usuarioId: Long, conquistaId: Long): ConquistaUsuario?
}

@Repository
interface ComentarioRepository : JpaRepository<Comentario, Long> {
    fun findByCursoIdOrderByCreatedAtDesc(cursoId: Long): List<Comentario>

    @Query("SELECT c FROM Comentario c WHERE c.curso.id = :cursoId ORDER BY c.criadoEm DESC")
    fun findByCursoIdOrdered(cursoId: Long): List<Comentario>
}
