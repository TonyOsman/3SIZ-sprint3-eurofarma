/*
 *
 *  * Copyright (c) 2026 Inacia Santos
 *  *
 *  * Este código é propriedade de Inacia Santos.
 *  * Não pode ser copiado, modificado ou distribuído sem autorização.
 *
 */

package br.com.inaciasantos.eurofarma

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

val BodoniFont = FontFamily(
    Font(R.font.bodoni, FontWeight.Normal)
)

@Composable
fun MainScreen(navController: NavController) {

    val azulEurofarma = Color(0xFF1565C0)
    var selectedIndex by remember { mutableStateOf(0) }

    val cards = listOf(
        Triple("Treinamento Obrigatório", "Lorem ipsum dolor sit amet", 0.4f),
        Triple("Cursos Específicos", "Lorem ipsum amet dolor", 0.7f),
        Triple("Cursos Aleatórios", "Lorem ipsum for cursos", 0.3f),
        Triple("Cursos Aleatórios", "Lorem ipsum amet cursos", 0.6f),
    )

    val blocos = listOf(
        Pair("Seja criativo, seja original. Qual é o Parque de Como?",
            "Estamos pesquisando o que é Empresa está original e listou quem ajuda a encontrar essas coisas."),
        Pair("Edição: a importância de revisar",
            "Com a edição, garantimos que cada caractere, frase e parágrafo transmita a mensagem correta."),
        Pair("Criando um futuro como o roteiro do investimento.",
            "A essência do nome é o que ela representa está na frase que melhor representa a ideia."),
        Pair("Estamos juntos para criar um futuro melhor",
            "Há novos aspectos a considerar ao criar um sucesso. Você encontrará um guia para ajudá-lo."),
    )

    Box(modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF5F5F5))
                .verticalScroll(rememberScrollState())
                .padding(bottom = 70.dp)
        ) {

            //HEADER COM IMAGEM
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(260.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.fundoazul),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                //lOGO no topo
                Image(
                    painter = painterResource(id = R.drawable.logo_eurofarma),
                    contentDescription = "Logo Eurofarma",
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(top = 36.dp)
                        .height(36.dp)
                )

                Box(
                    modifier = Modifier
                        //.fillMaxWidth(0.8f)
                        .height(210.dp)
                        .align(Alignment.BottomCenter)
                        .clip(RoundedCornerShape(16.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Treinamentos",
                        color = Color.White,
                        fontSize = 37.sp,
                        fontWeight = FontWeight.ExtraBold,
                        //fontWeight = FontWeight.Light,
                        //fontStyle = FontStyle.Italic

                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            //CARDS
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                cards.forEach { (titulo, descricao, progresso) ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            //miniatura
                            Box(
                                modifier = Modifier
                                    .size(60.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(Color(0xFFE0E0E0))
                            )

                            //conteúdo
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = titulo,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF212121)
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = descricao,
                                    fontSize = 11.sp,
                                    color = Color.Gray,
                                    maxLines = 2
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                LinearProgressIndicator(
                                    progress = { progresso },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(4.dp)
                                        .clip(RoundedCornerShape(2.dp)),
                                    color = azulEurofarma,
                                    trackColor = Color(0xFFE0E0E0)
                                )
                                Spacer(modifier = Modifier.height(6.dp))
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                                ) {
                                    Text("Detalhes", fontSize = 11.sp, color = azulEurofarma, fontWeight = FontWeight.Bold)
                                    Text(
                                        text = "Iniciar",
                                        fontSize = 11.sp,
                                        color = azulEurofarma,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.clickable {
                                            navController.navigate("curso/${titulo}")  // ✅ passa o título como argumento
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))


            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                blocos.chunked(2).forEach { par ->
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        par.forEach { (titulo, descricao) ->
                            Card(
                                modifier = Modifier.weight(1f),
                                shape = RoundedCornerShape(12.dp),
                                colors = CardDefaults.cardColors(containerColor = Color.White),
                                elevation = CardDefaults.cardElevation(2.dp)
                            ) {
                                Column(modifier = Modifier.padding(12.dp)) {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(90.dp)
                                            .clip(RoundedCornerShape(8.dp))
                                            .background(Color(0xFFE0E0E0))
                                    )
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text(text = titulo, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color(0xFF212121))
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(text = descricao, fontSize = 11.sp, color = Color.Gray, lineHeight = 16.sp)
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Box(
                                        modifier = Modifier.fillMaxWidth(),
                                        contentAlignment = Alignment.CenterEnd
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .size(28.dp)
                                                .clip(RoundedCornerShape(50))
                                                .background(azulEurofarma),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Text("+", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                                        }
                                    }
                                }
                            }
                        }
                        if (par.size == 1) Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}