package com.eurofarma.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "progressos")
data class Progresso(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    var usuario: Usuario,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "licao_id", nullable = false)
    var licao: Licao,

    @Column(nullable = false)
    var concluida: Boolean = false,

    var concluidaEm: LocalDateTime? = null
)
