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
import androidx.compose.material.icons.filled.Menu
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

//cores
private val BgDeep      = Color(0xFF060B18)
private val BgCard      = Color(0xFF0E1729)
private val BgCardLight = Color(0xFF14213D)
private val AzulVibrant = Color(0xFF1A6EFF)
private val AzulGlow    = Color(0xFF0057B8)
private val Ouro        = Color(0xFFFFBB33)
private val OuroLight   = Color(0xFFFFD97D)
private val Branco      = Color.White
private val Cinza       = Color(0xFFC3CAD9)
private val Sucesso     = Color(0xFF00E5A0)

@Composable
fun PerfilScreen(navController: NavController) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BgDeep)
    ) {
        //decorativos de fundo
        Box(
            modifier = Modifier
                .size(300.dp)
                .offset(x = (-60).dp, y = 80.dp)
                .blur(120.dp)
                .background(BgDeep)
                //.background(AzulVibrant.copy(alpha = 0.18f), CircleShape)
        )
        Box(
            modifier = Modifier
                .size(200.dp)
                .align(Alignment.TopEnd)
                .offset(x = 40.dp, y = 160.dp)
                .blur(100.dp)
                .background(Ouro.copy(alpha = 0.10f), CircleShape)
                .background(BgDeep)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {

            //topBar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 22.dp, vertical = 18.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 36.dp)
                    ) {
                        Image(
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
                                .padding(end = 20.dp)
                                .size(28.dp)
                        )
                    }
                }
            }

            //perfil
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 22.dp)
                    .clip(RoundedCornerShape(28.dp))
                    .background(
                        Brush.linearGradient(
                            colors = listOf(BgCardLight, Color(0xFF0A1628)),
                            start = Offset(0f, 0f),
                            end = Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY)
                        )
                    )
                    .border(
                        width = 1.dp,
                        brush = Brush.linearGradient(
                            listOf(AzulVibrant.copy(0.5f), Color.Transparent)
                        ),
                        shape = RoundedCornerShape(28.dp)
                    )
                    .padding(20.dp)
            ) {
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        // Foto com anel gradiente
                        Box(contentAlignment = Alignment.Center) {
                            Box(
                                modifier = Modifier
                                    .size(96.dp)
                                    .clip(CircleShape)
                                    .background(
                                        Brush.sweepGradient(
                                            listOf(AzulVibrant, Ouro, Sucesso, AzulVibrant)
                                        )
                                    )
                            )
                            Image(
                                painter = painterResource(id = R.drawable.perfil),

                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(89.dp)
                                    .clip(CircleShape)
                            )
                            Box(
                                modifier = Modifier
                                    .align(Alignment.BottomEnd)
                                    .size(26.dp)
                                    .clip(CircleShape)
                                    .background(Ouro),
                                contentAlignment = Alignment.Center
                            ) {
                                Text("★", color = Color(0xFF2A1500), fontSize = 13.sp)
                            }
                        }

                        Spacer(modifier = Modifier.width(18.dp))

                        Column {
                            Text(
                                text = "Inacia Santos",
                                color = Branco,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.ExtraBold
                            )
                            Text(
                                text = "Nível Avançado",
                                color = Ouro,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(20.dp))
                                    .background(AzulVibrant.copy(alpha = 0.2f))
                                    .border(
                                        1.dp,
                                        AzulVibrant.copy(0.4f),
                                        RoundedCornerShape(20.dp)
                                    )
                                    .padding(horizontal = 10.dp, vertical = 4.dp)
                            ) {
                                Text(
                                    text = "🏆  Top 5% da empresa",
                                    color = AzulVibrant,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        listOf(
                            Triple("100", "Vídeos\nvistos", AzulVibrant),
                            Triple("7",   "Cursos\nconcluídos", Sucesso),
                            Triple("423", "Views\ntotais", Ouro),
                        ).forEach { (value, label, accent) ->
                            StatPill(value = value, label = label, accent = accent)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            //barradeXP
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 22.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(
                        Brush.horizontalGradient(listOf(Color(0xFFFF9900), Ouro, OuroLight))
                    )
                    .padding(18.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "PONTUAÇÃO TOTAL",
                            color = Color(0xFF5A3000),
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.2.sp
                        )
                        Text(
                            text = "1.000 XP",
                            color = Color(0xFF1A0800),
                            fontSize = 28.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }
                    Column(horizontalAlignment = Alignment.End) {
                        Text(
                            text = "Próximo nível",
                            color = Color(0xFF5A3000),
                            fontSize = 10.sp
                        )
                        Text(
                            text = "2.000 XP",
                            color = Color(0xFF2A1500),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Box(
                            modifier = Modifier
                                .width(80.dp)
                                .height(6.dp)
                                .clip(RoundedCornerShape(3.dp))
                                .background(Color(0x44000000))
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .fillMaxWidth(0.5f)
                                    .clip(RoundedCornerShape(3.dp))
                                    .background(Color(0xFF3D1F00))
                            )
                        }
                        Text(
                            text = "50% concluído",
                            color = Color(0xFF5A3000),
                            fontSize = 9.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(22.dp))

            //conquistas
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 22.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Conquistas",
                    color = Branco,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Ver todas →",
                    color = AzulVibrant,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            val badges = listOf(
                BadgeItem("Introdução",        "50 XP",  "📘", AzulVibrant),
                BadgeItem("LGPD",              "500 XP", "🔒", Sucesso),
                BadgeItem("Ética no trabalho", "200 XP", "⚖️", Ouro),
                BadgeItem("Curso específico",  "50 XP",  "🎯", Color(0xFFFF6B6B)),
                BadgeItem("Curso obrigatório", "100 XP", "📋", Color(0xFFAD7BFF)),
                BadgeItem("Metas",             "100 XP", "🚀", Sucesso),
            )

            Column(
                modifier = Modifier.padding(horizontal = 22.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                badges.chunked(3).forEach { row ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        row.forEach { badge ->
                            BadgeCardNew(badge = badge, modifier = Modifier.weight(1f))
                        }
                        repeat(3 - row.size) { Spacer(modifier = Modifier.weight(1f)) }
                    }
                }
            }

            Spacer(modifier = Modifier.height(22.dp))

            //Painel Chat/Menu
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 22.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .background(BgCard)
                    .border(
                        1.dp,
                        Brush.linearGradient(listOf(AzulVibrant.copy(0.3f), Color.Transparent)),
                        RoundedCornerShape(24.dp)
                    )
                    .padding(18.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(
                                    Brush.radialGradient(listOf(AzulVibrant, AzulGlow))
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.perfil),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(38.dp)
                                    .clip(CircleShape)
                            )
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                "",
                                color = Ouro,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Row(horizontalArrangement = Arrangement.spacedBy(3.dp)) {
                                listOf(Color.Red, Color(0xFF444444), Color(0xFF444444)).forEach { c ->
                                    Box(
                                        Modifier
                                            .size(width = 16.dp, height = 5.dp)
                                            .clip(RoundedCornerShape(3.dp))
                                            .background(c)
                                    )
                                }
                            }
                        }
                    }
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .background(AzulVibrant.copy(0.15f))
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                    ) {
                        Text(
                            "7 itens",
                            color = AzulVibrant,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                val menuItems = listOf(
                    "Desenvolvimento Pessoal" to true,
                    "Métrica pessoal"          to false,
                    "Métrica corporativa"      to false,
                    "Solicitação de senha"     to false,
                    "Solicitações RH"          to false,
                    "Métrica corporativa"      to false,
                    "Solicitação de senha"     to false,
                )

                menuItems.forEachIndexed { index, (label, isHeader) ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(10.dp))
                            .background(
                                if (isHeader) AzulVibrant.copy(0.12f) else Color.Transparent
                            )
                            .padding(
                                horizontal = if (isHeader) 10.dp else 4.dp,
                                vertical = 8.dp
                            )
                    ) {
                        Box(
                            modifier = Modifier
                                .size(30.dp)
                                .clip(CircleShape)
                                .background(if (isHeader) AzulVibrant else BgCardLight),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = if (isHeader) "★" else "C",
                                color = if (isHeader) Branco else Cinza,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = label,
                            color = if (isHeader) Branco else Cinza,
                            fontSize = if (isHeader) 14.sp else 13.sp,
                            fontWeight = if (isHeader) FontWeight.Bold else FontWeight.Normal,
                            modifier = Modifier.weight(1f)
                        )
                        if (!isHeader) {
                            Text("›", color = Cinza, fontSize = 18.sp)
                        }
                    }
                    if (index < menuItems.size - 1) {
                        HorizontalDivider(
                            color = Color(0xFF1A2A45),
                            modifier = Modifier.padding(vertical = 2.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                //Chat com IA
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(
                            Brush.horizontalGradient(
                                listOf(Color(0xFF0D2044), Color(0xFF0A1628))
                            )
                        )
                        .border(1.dp, AzulVibrant.copy(0.25f), RoundedCornerShape(16.dp))
                        .padding(14.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(42.dp)
                                .clip(CircleShape)
                                .background(
                                    Brush.radialGradient(listOf(AzulVibrant, Color(0xFF003090)))
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("🤖", fontSize = 20.sp)
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Chat com IA",
                                    color = Branco,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Box(
                                        Modifier
                                            .size(6.dp)
                                            .clip(CircleShape)
                                            .background(Sucesso)
                                    )
                                    Spacer(Modifier.width(4.dp))
                                    Text("Online", color = Sucesso, fontSize = 10.sp)
                                }
                            }
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = "Ficou com mais alguma dúvida?",
                                color = Cinza,
                                fontSize = 12.sp
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(28.dp))
        }
    }
}

//Componentes extras

@Composable
fun StatPill(value: String, label: String, accent: Color) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .border(1.dp, accent.copy(0.25f), RoundedCornerShape(16.dp))
            .padding(horizontal = 14.dp, vertical = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = value,
            color = accent,
            fontSize = 20.sp,
            fontWeight = FontWeight.ExtraBold
        )
        Text(
            text = label,
            color = Cinza,
            fontSize = 9.sp,
            textAlign = TextAlign.Center,
            lineHeight = 12.sp
        )
    }
}

data class BadgeItem(
    val label: String,
    val xp: String,
    val emoji: String,
    val accent: Color
)

@Composable
fun BadgeCardNew(badge: BadgeItem, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(18.dp))
            .background(BgCard)
            .border(1.dp, badge.accent.copy(0.3f), RoundedCornerShape(18.dp))
            .padding(vertical = 14.dp, horizontal = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(52.dp)
                .clip(CircleShape)
                .background(
                    Brush.radialGradient(
                        listOf(badge.accent.copy(0.35f), badge.accent.copy(0.05f))
                    )
                )
                .border(1.5.dp, badge.accent.copy(0.5f), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(badge.emoji, fontSize = 24.sp)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = badge.label,
            color = Branco,
            fontSize = 10.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
            lineHeight = 13.sp
        )
        Spacer(modifier = Modifier.height(4.dp))
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(badge.accent.copy(0.15f))
                .padding(horizontal = 6.dp, vertical = 2.dp)
        ) {
            Text(
                text = badge.xp,
                color = badge.accent,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}