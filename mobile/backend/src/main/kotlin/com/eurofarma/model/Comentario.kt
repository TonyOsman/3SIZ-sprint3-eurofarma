package com.eurofarma.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "comentarios")
data class Comentario(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    var usuario: Usuario,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id", nullable = false)
    var curso: Curso,

    @Column(nullable = false, length = 500)
    var texto: String,

    @Column(nullable = false)
    val criadoEm: LocalDateTime = LocalDateTime.now()
)
