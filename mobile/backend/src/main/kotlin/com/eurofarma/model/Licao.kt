package com.eurofarma.model

import jakarta.persistence.*

@Entity
@Table(name = "licoes")
data class Licao(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    var titulo: String,

    var subtitulo: String? = null,

    var videoUrl: String? = null,

    @Column(nullable = false)
    var ordem: Int = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "modulo_id", nullable = false)
    var modulo: Modulo
)
