package com.eurofarma.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "usuarios")
data class Usuario(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    var nome: String,

    @Column(nullable = false, unique = true)
    var email: String,

    @Column(nullable = false)
    var senha: String,

    @Column(nullable = false)
    var departamento: String,

    @Column(nullable = false)
    var nivel: String = "Iniciante",

    @Column(nullable = false)
    var xpTotal: Int = 0,

    @Column(nullable = false)
    var videosVistos: Int = 0,

    @Column(nullable = false)
    var cursosConcluidos: Int = 0,

    var avatarUrl: String? = null,

    @Column(nullable = false)
    val criadoEm: LocalDateTime = LocalDateTime.now(),

    @OneToMany(mappedBy = "usuario", cascade = [CascadeType.ALL])
    val progressos: MutableList<Progresso> = mutableListOf(),

    @OneToMany(mappedBy = "usuario", cascade = [CascadeType.ALL])
    val conquistas: MutableList<ConquistaUsuario> = mutableListOf()
)

enum class NivelUsuario(val xpMinimo: Int) {
    INICIANTE(0),
    BASICO(500),
    INTERMEDIARIO(1500),
    AVANCADO(3000),
    EXPERT(6000),
    MESTRE(10000)
}
