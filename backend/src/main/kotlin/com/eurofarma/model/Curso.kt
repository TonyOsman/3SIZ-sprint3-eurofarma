package com.eurofarma.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "cursos")
data class Curso(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    var titulo: String,

    @Column(columnDefinition = "TEXT")
    var descricao: String? = null,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var tipo: TipoCurso,

    var thumbnailUrl: String? = null,

    var duracao: String? = null,

    @Column(nullable = false)
    var aoVivo: Boolean = false,

    @Column(nullable = false)
    var visualizacoes: Int = 0,

    @Column(nullable = false)
    val criadoEm: LocalDateTime = LocalDateTime.now(),

    @OneToMany(mappedBy = "curso", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val modulos: MutableList<Modulo> = mutableListOf(),

    @OneToMany(mappedBy = "curso", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val comentarios: MutableList<Comentario> = mutableListOf()
)

enum class TipoCurso {
    OBRIGATORIO,
    ESPECIFICO,
    ALEATORIO
}
