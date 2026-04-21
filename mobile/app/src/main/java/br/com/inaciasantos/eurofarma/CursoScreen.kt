/*
 *
 *  * Copyright (c) 2026 Inacia Santos
 *  *
 *  * Este código é propriedade de Inacia Santos.
 *  * Não pode ser copiado, modificado ou distribuído sem autorização.
 *
 */

package br.com.inaciasantos.eurofarma

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.School
import androidx.compose.material.icons.outlined.Timer
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

// ── Paleta ──────────────────────────────────────────────────────
private val BgDeep      = Color(0xFF060B18)
private val BgCard      = Color(0xFF0E1729)
private val BgCardLight = Color(0xFF14213D)
private val AzulVibrant = Color(0xFF1A6EFF)
private val AzulGlow    = Color(0xFF0057B8)
private val Ouro        = Color(0xFFFFBB33)
private val Branco      = Color.White
private val Cinza       = Color(0xFF8A9BB5)
private val Sucesso     = Color(0xFF00E5A0)
private val CardGray    = Color(0xFF1C1C1E)

//Modelos
data class Licao(
    val titulo: String,
    val subtitulo: String = "",
    val concluida: Boolean = false
)

data class Modulo(
    val titulo: String,
    val concluidas: Int,
    val total: Int,
    val licoes: List<Licao>
)

data class Comentario(
    val nome: String,
    val texto: String,
    val avatarUrl: String
)

@Composable
fun CursoScreen(navController: NavController, titulo: String) {

    var mensagem by remember { mutableStateOf("") }
    var moduloExpandido by remember { mutableStateOf(true) }

    val nomeCurso = titulo.ifBlank { "Integração à Empresa" }

    val modulos = listOf(
        Modulo(
            titulo = "Primeiro módulo",
            concluidas = 2,
            total = 12,
            licoes = listOf(
                Licao("Introdução", concluida = true),
                Licao("Apresentação institucional", "(história, missão, visão e valores)", true),
                Licao("Cultura organizacional e propósito", concluida = true),
                Licao("Estrutura da empresa e principais áreas", concluida = true),
                Licao("Produtos, serviços e mercado de atuação", concluida = true),
                Licao("Políticas internas e código de conduta", concluida = true),
                Licao("Ética e compliance", concluida = true),
                Licao("Sustentabilidade e responsabilidade social", concluida = true),
            )
        )
    )

    val comentarios = listOf(
        Comentario("Inacia",   "Conteúdo incrível!",                        "https://i.pravatar.cc/150?img=47"),
        Comentario("Tony",    "Aproveitando o treinamento até agora.",      "https://i.pravatar.cc/150?img=11"),
        Comentario("Bruna",   "Estou aprendendo muito.",                    "https://i.pravatar.cc/150?img=5"),
        Comentario("Enzo",    "Não vejo a hora de ver o próximo vídeo.",   "https://i.pravatar.cc/150?img=53"),
        Comentario("Rickson", "Tirando todas as minhas dúvidas.",           "https://i.pravatar.cc/150?img=60"),
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BgDeep)
    ) {

        Box(
            modifier = Modifier
                .size(280.dp)
                .offset(x = (-50).dp, y = 300.dp)
                .blur(120.dp)
                //.background(AzulVibrant.copy(alpha = 0.12f), CircleShape)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {

            //TopBar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Box(
                        modifier = Modifier
                            .size(38.dp)
                            .clip(CircleShape)
                            .background(BgCard)
                            .border(1.dp, AzulVibrant.copy(0.3f), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "Voltar",
                            tint = Branco,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = nomeCurso,
                    color = Branco,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f),
                    maxLines = 1
                )
            }

            //Vídeo
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .background(Color.Black),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.svideo),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                //gradiente
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                listOf(Color.Transparent, Color.Black.copy(alpha = 0.5f))
                            )
                        )
                )

                //BotãoPlay
                Box(
                    modifier = Modifier
                        .size(62.dp)
                        .clip(CircleShape)
                        .background(AzulVibrant.copy(alpha = 0.9f))
                        .border(2.dp, Branco.copy(0.3f), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        Icons.Default.PlayArrow,
                        contentDescription = "Play",
                        tint = Branco,
                        modifier = Modifier.size(32.dp)
                    )
                }

                //Badge de views
                Row(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(12.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color.Black.copy(alpha = 0.65f))
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.Visibility,
                        contentDescription = null,
                        tint = Cinza,
                        modifier = Modifier.size(13.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("13.9k", color = Branco, fontSize = 11.sp, fontWeight = FontWeight.SemiBold)
                }

                //Ao viv
                Row(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(12.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color(0xFFCC0000).copy(alpha = 0.85f))
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(6.dp)
                            .clip(CircleShape)
                            .background(Branco)
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text("AO VIVO", color = Branco, fontSize = 10.sp, fontWeight = FontWeight.ExtraBold, letterSpacing = 0.5.sp)
                }
            }

            //Header do Curso
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.verticalGradient(listOf(Color(0xFF101C35), BgDeep))
                    )
                    .padding(horizontal = 20.dp, vertical = 20.dp)
            ) {
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .clip(RoundedCornerShape(14.dp))
                                .background(
                                    Brush.linearGradient(listOf(Color(0xFF2A4A1A), Color(0xFF1A3A0A)))
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("🦅", fontSize = 24.sp)
                        }
                        Spacer(modifier = Modifier.width(14.dp))
                        Column {
                            Text(
                                text = nomeCurso,
                                color = Branco,
                                fontSize = 17.sp,
                                fontWeight = FontWeight.ExtraBold
                            )
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    modifier = Modifier
                                        .size(7.dp)
                                        .clip(CircleShape)
                                        .background(Sucesso)
                                )
                                Spacer(modifier = Modifier.width(5.dp))
                                Text(
                                    text = "Finished",
                                    color = Sucesso,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    //Barradeprogressototal
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(5.dp)
                            .clip(RoundedCornerShape(3.dp))
                            .background(Color(0xFF1E2D48))
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(RoundedCornerShape(3.dp))
                                .background(
                                    Brush.horizontalGradient(listOf(Sucesso, Color(0xFF00FFC8)))
                                )
                        )
                    }

                    Spacer(modifier = Modifier.height(14.dp))


                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .clip(RoundedCornerShape(20.dp))
                                .background(Color(0xFF1A2A45))
                                .padding(horizontal = 10.dp, vertical = 5.dp)
                        ) {
                            Icon(Icons.Outlined.Timer, contentDescription = null, tint = Cinza, modifier = Modifier.size(13.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("7 Dias", color = Cinza, fontSize = 12.sp, fontWeight = FontWeight.Medium)
                        }

                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(20.dp))
                                .background(Color(0xFF3D2200))
                                .border(1.dp, Ouro.copy(0.4f), RoundedCornerShape(20.dp))
                                .padding(horizontal = 12.dp, vertical = 5.dp)
                        ) {
                            Text("Dúvidas", color = Ouro, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                        }

                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(20.dp))
                                .background(AzulVibrant.copy(0.15f))
                                .border(1.dp, AzulVibrant.copy(0.4f), RoundedCornerShape(20.dp))
                                .padding(horizontal = 12.dp, vertical = 5.dp)
                        ) {
                            Text("24 Lições", color = AzulVibrant, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                        }
                    }
                }
            }

            //HorizontalDivider(color = Color(0xFF1A2A45), thickness = 1.dp)
            Spacer(modifier = Modifier.height(20.dp))


            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(BgCard)
                    .border(
                        1.dp,
                        Brush.linearGradient(listOf(AzulVibrant.copy(0.3f), Color.Transparent)),
                        RoundedCornerShape(20.dp)
                    )
                    .padding(18.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Sobre o curso",
                        color = Branco,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(7.dp)
                                .clip(CircleShape)
                                .background(Sucesso)
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text("Transmitido • Agora", color = Cinza, fontSize = 11.sp)
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Durante as primeiras semanas, você participará de momentos de apresentação institucional, conhecerá nossos valores, áreas e líderes, e terá acesso a treinamentos e materiais que vão ajudar no seu desenvolvimento e entendimento sobre o nosso negócio.",
                    color = Cinza,
                    fontSize = 13.sp,
                    lineHeight = 20.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { },
                    colors = ButtonDefaults.buttonColors(containerColor = AzulVibrant),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Icon(Icons.Default.PlayArrow, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Próxima aula", fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
                }
            }

            Spacer(modifier = Modifier.height(22.dp))


            modulos.forEach { modulo ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(44.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(
                                    Brush.linearGradient(listOf(Color(0xFF2A4A1A), Color(0xFF1A3A0A)))
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("🦅", fontSize = 22.sp)
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(modulo.titulo, color = Branco, fontSize = 15.sp, fontWeight = FontWeight.Bold)
                            Text("Concluí ${modulo.concluidas} de ${modulo.total}", color = Cinza, fontSize = 12.sp)
                        }
                    }
                    IconButton(onClick = { moduloExpandido = !moduloExpandido }) {
                        Icon(
                            if (moduloExpandido) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                            contentDescription = null,
                            tint = Cinza
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .height(5.dp)
                        .clip(RoundedCornerShape(3.dp))
                        .background(Color(0xFF1E2D48))
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth(modulo.concluidas.toFloat() / modulo.total.toFloat())
                            .clip(RoundedCornerShape(3.dp))
                            .background(
                                Brush.horizontalGradient(listOf(Sucesso, AzulVibrant))
                            )
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                if (moduloExpandido) {
                    Column(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        modulo.licoes.forEachIndexed { index, licao ->
                            LicaoCard(licao = licao, isFirst = index == 0)
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

            Spacer(modifier = Modifier.height(22.dp))
            //HorizontalDivider(color = Color(0xFF1A2A45), thickness = 1.dp)
            Spacer(modifier = Modifier.height(22.dp))


            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                Text(
                    text = "Comentários",
                    color = Branco,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                comentarios.forEach { comentario ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.perfil),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(38.dp)
                                .clip(CircleShape)
                                .border(1.5.dp, AzulVibrant.copy(0.4f), CircleShape)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(12.dp))
                                .background(BgCard)
                                .padding(10.dp)
                        ) {
                            Text(
                                text = comentario.nome,
                                color = Branco,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = comentario.texto,
                                color = Cinza,
                                fontSize = 12.sp,
                                lineHeight = 17.sp
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                //mensagem
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    OutlinedTextField(
                        value = mensagem,
                        onValueChange = { mensagem = it },
                        placeholder = {
                            Text("Envie uma mensagem...", color = Cinza, fontSize = 13.sp)
                        },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(20.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = AzulVibrant,
                            unfocusedBorderColor = Color(0xFF1E2D48),
                            focusedTextColor = Branco,
                            unfocusedTextColor = Branco,
                            focusedContainerColor = BgCard,
                            unfocusedContainerColor = BgCard,
                            cursorColor = AzulVibrant
                        )
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .background(
                                Brush.radialGradient(listOf(AzulVibrant, AzulGlow))
                            )
                            .clickable { /* enviar */ },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            Icons.Default.Send,
                            contentDescription = "Enviar",
                            tint = Branco,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(28.dp))
        }
    }
}

//Componentes

@Composable
fun LicaoCard(licao: Licao, isFirst: Boolean) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(
                Brush.verticalGradient(listOf(Color(0xFF101C35), BgDeep))
            )
            .then(
                if (isFirst) Modifier.border(
                    1.dp,
                    Brush.linearGradient(listOf(Ouro.copy(0.6f), Color.Transparent)),
                    RoundedCornerShape(16.dp)
                ) else Modifier
            )
    ) {
        if (isFirst) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .size(14.dp)
                    .clip(RoundedCornerShape(bottomStart = 10.dp))
                    .background(Ouro)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(
                        Brush.verticalGradient(listOf(Color(0xFF101C35), BgDeep))
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.School,
                    contentDescription = null,
                    tint = if (licao.concluida) Cinza else AzulVibrant,
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column(modifier = Modifier.weight(1f)) {
                if (licao.concluida) {
                    Text(
                        text = "Concluído",
                        color = Sucesso,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.SemiBold,
                        letterSpacing = 0.3.sp
                    )
                }
                Text(
                    text = licao.titulo,
                    color = Branco,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    lineHeight = 18.sp
                )
                if (licao.subtitulo.isNotEmpty()) {
                    Text(text = licao.subtitulo, color = Cinza, fontSize = 11.sp)
                }
            }

            Icon(
                Icons.Default.ArrowForwardIos,
                contentDescription = null,
                tint = Cinza,
                modifier = Modifier.size(13.dp)
            )
        }
    }
}