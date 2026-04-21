package com.eurofarma.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "conquistas")
data class Conquista(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    var nome: String,

    var descricao: String? = null,

    @Column(nullable = false)
    var xp: Int,

    var icone: String? = null
)

@Entity
@Table(name = "conquistas_usuario")
data class ConquistaUsuario(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    var usuario: Usuario,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conquista_id", nullable = false)
    var conquista: Conquista,

    @Column(nullable = false)
    val desbloqueadaEm: LocalDateTime = LocalDateTime.now()
)
