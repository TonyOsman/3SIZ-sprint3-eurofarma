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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

// ── Paleta ──────────────────────────────────────────────────────
private val BgDeep      = Color(0xFF060B18)
private val BgCard      = Color(0xFF0E1729)
private val AzulVibrant = Color(0xFF1A6EFF)
private val AzulGlow    = Color(0xFF0057B8)
private val Ouro        = Color(0xFFFFBB33)
private val Prata       = Color(0xFFB0C4DE)
private val Bronze      = Color(0xFFCD7F32)
private val Branco      = Color.White
private val Cinza       = Color(0xFF8A9BB5)
private val Sucesso     = Color(0xFF00E5A0)

data class RankingPlayer(
    val nome: String,
    val xp: Int,
    val avatarRes: Int,
    val posicao: Int,
    val departamento: String = "Corporativo"
)

@Composable
fun RankingScreen(navController: NavController) {

    val players = remember {
        listOf(
            RankingPlayer("Inacia Santos",  9800, R.drawable.perfil, 1,  "RH"),
            RankingPlayer("Tony Osman",     8750, R.drawable.perfil, 2,  "TI"),
            RankingPlayer("Bruna Dames",    7600, R.drawable.perfil, 3,  "Vendas"),
            RankingPlayer("Rickson Hirata", 6400, R.drawable.perfil, 4,  "Jurídico"),
            RankingPlayer("Enzo Crispim",  5900, R.drawable.perfil, 5,  "Marketing"),
            RankingPlayer("Bruna Dames",    5200, R.drawable.perfil, 6,  "Vendas"),
            RankingPlayer("Rickson Hirata", 4900, R.drawable.perfil, 7,  "TI"),
            RankingPlayer("Enzo Crispim",  4300, R.drawable.perfil, 8,  "Marketing"),
            RankingPlayer("Inacia Santos",  3800, R.drawable.perfil, 9,  "RH"),
            RankingPlayer("Bruna Dames",    3200, R.drawable.perfil, 10, "Vendas"),
            RankingPlayer("Tony Osman",     2800, R.drawable.perfil, 11, "TI"),
            RankingPlayer("Rickson Hirata", 2400, R.drawable.perfil, 12, "Jurídico"),
            RankingPlayer("Enzo Crispim",  2100, R.drawable.perfil, 13, "Marketing"),
            RankingPlayer("Inacia Santos",  1800, R.drawable.perfil, 14, "RH"),
            RankingPlayer("Tony Osman",     1500, R.drawable.perfil, 15, "TI"),
            RankingPlayer("Bruna Dames",    1200, R.drawable.perfil, 16, "Vendas"),
            RankingPlayer("Rickson Hirata",  900, R.drawable.perfil, 17, "Jurídico"),
            RankingPlayer("Tony Osman",      700, R.drawable.perfil, 18, "TI"),
        )
    }

    val top3 = players.take(3)
    val resto = players.drop(3)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BgDeep)
    ) {

        // ── Orbs decorativos de fundo ────────────────────────────
        Box(
            modifier = Modifier
                .size(350.dp)
                .offset(x = (-80).dp, y = (-40).dp)
                .blur(130.dp)
                //.background(Ouro.copy(alpha = 0.15f), CircleShape)
        )
        Box(
            modifier = Modifier
                .size(250.dp)
                .align(Alignment.TopEnd)
                .offset(x = 60.dp, y = 100.dp)
                .blur(110.dp)
                .background(AzulVibrant.copy(alpha = 0.12f), CircleShape)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {

            // ── TopBar ───────────────────────────────────────────
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 36.dp, start = 20.dp, end = 20.dp, bottom = 8.dp)
            ) {
                androidx.compose.foundation.Image(
                    painter = painterResource(id = R.drawable.logo_eurofarma),
                    contentDescription = "Logo Eurofarma",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .height(36.dp)
                )
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menu",
                    tint = Branco,
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .size(28.dp)
                )
            }

            // ── Hero Banner dourado ──────────────────────────────
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .background(
                        Brush.verticalGradient(
                            listOf(Color(0xFFFFCC44), Color(0xFFFF9900))
                        )
                    )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.radialGradient(
                                colors = listOf(Color.White.copy(0.08f), Color.Transparent),
                                center = Offset(0f, 0f),
                                radius = 600f
                            )
                        )
                )
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 28.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(
                            text = "RANKING",
                            color = Color(0xFF1A0800),
                            fontSize = 42.sp,
                            fontWeight = FontWeight.ExtraBold,
                            letterSpacing = (-1).sp
                        )
                        Text(
                            text = "Top colaboradores do mês",
                            color = Color(0xFF5A3000),
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                    Text("🏆", fontSize = 64.sp)
                }
            }

            // ── Pódio Top 3 ──────────────────────────────────────
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF0A0F1E))
                    .padding(top = 28.dp, bottom = 12.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.Bottom
                ) {
                    PodioItem(player = top3[1], medalColor = Prata,  height = 100.dp)
                    PodioItem(player = top3[0], medalColor = Ouro,   height = 130.dp, isFirst = true)
                    PodioItem(player = top3[2], medalColor = Bronze, height = 80.dp)
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // ── Cabeçalho da grid ────────────────────────────────
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 22.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Todos os participantes",
                    color = Branco,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(AzulVibrant.copy(0.15f))
                        .border(1.dp, AzulVibrant.copy(0.4f), RoundedCornerShape(20.dp))
                        .padding(horizontal = 12.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "${players.size} participantes",
                        color = AzulVibrant,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // ── Grid 3 colunas ───────────────────────────────────
            Column(
                modifier = Modifier.padding(horizontal = 14.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                resto.chunked(3).forEach { rowItems ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        rowItems.forEach { player ->
                            PlayerCard(player = player, modifier = Modifier.weight(1f))
                        }
                        repeat(3 - rowItems.size) {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(30.dp))

        } // fim Column
    } // fim Box raiz
}

// ── Pódio ────────────────────────────────────────────────────────

@Composable
fun PodioItem(
    player: RankingPlayer,
    medalColor: Color,
    height: Dp,
    isFirst: Boolean = false
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier.width(100.dp)
    ) {
        Text(
            text = player.nome.split(" ").first(),
            color = Branco,
            fontSize = 11.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "${player.xp} XP",
            color = medalColor,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(6.dp))

        Box(contentAlignment = Alignment.Center) {
            Box(
                modifier = Modifier
                    .size(if (isFirst) 72.dp else 60.dp)
                    .clip(CircleShape)
                    .background(
                        Brush.sweepGradient(listOf(medalColor, medalColor.copy(0.3f), medalColor))
                    )
            )
            androidx.compose.foundation.Image(
                painter = painterResource(id = player.avatarRes),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(if (isFirst) 66.dp else 54.dp)
                    .clip(CircleShape)
            )
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .size(22.dp)
                    .clip(CircleShape)
                    .background(medalColor),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = when (player.posicao) { 1 -> "🥇"; 2 -> "🥈"; else -> "🥉" },
                    fontSize = 12.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height)
                .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
                .background(
                    Brush.verticalGradient(
                        listOf(
                            medalColor.copy(if (isFirst) 0.9f else 0.6f),
                            medalColor.copy(0.2f)
                        )
                    )
                )
                .border(
                    1.dp,
                    medalColor.copy(0.5f),
                    RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "#${player.posicao}",
                color = if (isFirst) Color(0xFF1A0800) else Branco,
                fontSize = if (isFirst) 22.sp else 16.sp,
                fontWeight = FontWeight.ExtraBold
            )
        }
    }
}

// ── Card da grid ─────────────────────────────────────────────────

@Composable
fun PlayerCard(player: RankingPlayer, modifier: Modifier = Modifier) {
    val accentColor = when {
        player.posicao <= 3  -> Ouro
        player.posicao <= 6  -> AzulVibrant
        player.posicao <= 10 -> Sucesso
        else                 -> Cinza.copy(0.5f)
    }

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(18.dp))
            .background(BgCard)
            .border(1.dp, accentColor.copy(0.25f), RoundedCornerShape(18.dp))
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.Start)
                .clip(RoundedCornerShape(6.dp))
                .background(accentColor.copy(0.15f))
                .padding(horizontal = 6.dp, vertical = 2.dp)
        ) {
            Text(
                text = "#${player.posicao}",
                color = accentColor,
                fontSize = 9.sp,
                fontWeight = FontWeight.ExtraBold
            )
        }

        Spacer(modifier = Modifier.height(6.dp))

        Box(contentAlignment = Alignment.Center) {
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape)
                    .background(
                        Brush.sweepGradient(listOf(accentColor, accentColor.copy(0.2f), accentColor))
                    )
            )
            androidx.compose.foundation.Image(
                painter = painterResource(id = player.avatarRes),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(51.dp)
                    .clip(CircleShape)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = player.nome.split(" ").first(),
            color = Branco,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = player.departamento,
            color = Cinza,
            fontSize = 9.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(6.dp))

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(accentColor.copy(0.12f))
                .padding(horizontal = 8.dp, vertical = 3.dp)
        ) {
            Text(
                text = "${player.xp} XP",
                color = accentColor,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}