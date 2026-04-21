package com.eurofarma.model

import jakarta.persistence.*

@Entity
@Table(name = "modulos")
data class Modulo(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    var titulo: String,

    @Column(nullable = false)
    var ordem: Int = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id", nullable = false)
    var curso: Curso,

    @OneToMany(mappedBy = "modulo", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val licoes: MutableList<Licao> = mutableListOf()
)
