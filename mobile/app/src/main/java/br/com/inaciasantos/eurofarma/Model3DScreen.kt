/*
 *
 *  * Copyright (c) 2026 Inacia Santos
 *  *
 *  * Este código é propriedade de Inacia Santos.
 *  * Não pode ser copiado, modificado ou distribuído sem autorização.
 *
 */

package br.com.inaciasantos.eurofarma

import android.view.Choreographer
import android.view.SurfaceView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.filament.EntityManager
import com.google.android.filament.LightManager
import com.google.android.filament.utils.ModelViewer
import kotlinx.coroutines.*
import java.nio.ByteBuffer

@Composable
fun Model3DScreen(
    onNavigateToNext: () -> Unit
) {
    var selectedIndex by remember { mutableStateOf(0) }

    Box(modifier = Modifier.fillMaxSize().background(Color.Black)) {



        //modelo 3D
        AndroidView(
            modifier = Modifier.fillMaxSize()
                .offset(y = 110.dp),
            factory = { context ->
                val surfaceView = SurfaceView(context)
                val modelViewer = ModelViewer(surfaceView)

                surfaceView.setOnTouchListener { _, event ->
                    modelViewer.onTouchEvent(event)
                    true
                }

                CoroutineScope(Dispatchers.IO).launch {
                    val buffer = context.assets.open("modelo1.glb").use {
                        ByteBuffer.wrap(it.readBytes())
                    }
                    withContext(Dispatchers.Main) {
                        modelViewer.loadModelGlb(buffer)
                        modelViewer.transformToUnitCube()

                        val engine = modelViewer.engine
                        val entityManager = EntityManager.get()

                        //escala o modelo
                        val tm = engine.transformManager
                        val asset = modelViewer.asset
                        if (asset != null) {
                            val root = asset.root
                            val instance = tm.getInstance(root)
                            val matrix = FloatArray(16)
                            tm.getTransform(instance, matrix)
                            val scale = 1.4f
                            matrix[0] *= scale
                            matrix[5] *= scale
                            matrix[10] *= scale
                            tm.setTransform(instance, matrix)
                        }

                        val lightPositions = listOf(
                            //pontos de iluminação do globo
                            Triple( 3f,  0f,  0f),
                            Triple(-3f,  0f,  0f),
                            Triple( 0f,  3f,  0f),
                            Triple( 0f, -3f,  0f),
                            Triple( 0f,  0f,  3f),
                            Triple( 0f,  0f, -3f),
                            Triple( 2f,  2f,  2f),
                            Triple(-2f,  2f,  2f),
                            Triple( 2f, -2f,  2f),
                            Triple(-2f, -2f,  2f),
                            Triple( 2f,  2f, -2f),
                            Triple(-2f,  2f, -2f),
                            Triple( 2f, -2f, -2f),
                            Triple(-2f, -2f, -2f),
                            Triple( 3f,  3f,  0f),
                            Triple(-3f,  3f,  0f),
                            Triple( 3f, -3f,  0f),
                            Triple(-3f, -3f,  0f),
                            Triple( 3f,  0f,  3f),
                            Triple(-3f,  0f,  3f),
                            Triple( 3f,  0f, -3f),
                            Triple(-3f,  0f, -3f),
                            Triple( 0f,  3f,  3f),
                            Triple( 0f, -3f,  3f),
                            Triple( 0f,  3f, -3f),
                            Triple( 0f, -3f, -3f),
                            Triple( 2f,  1f,  2f),
                            Triple(-2f,  1f,  2f),
                            Triple( 2f, -1f,  2f),
                            Triple(-2f, -1f,  2f),
                            Triple( 2f,  1f, -2f),
                            Triple(-2f,  1f, -2f),
                            Triple( 2f, -1f, -2f),
                            Triple(-2f, -1f, -2f),
                            Triple( 1f,  2f,  2f),
                            Triple(-1f,  2f,  2f),
                            Triple( 1f, -2f,  2f),
                            Triple(-1f, -2f,  2f),
                            Triple( 1f,  2f, -2f),
                            Triple(-1f,  2f, -2f),
                            Triple( 1f, -2f, -2f),
                            Triple(-1f, -2f, -2f),
                            Triple( 2f,  2f,  1f),
                            Triple(-2f,  2f,  1f),
                            Triple( 2f, -2f,  1f),
                            Triple(-2f, -2f,  1f),
                            Triple( 2f,  2f, -1f),
                            Triple(-2f,  2f, -1f),
                            Triple( 2f, -2f, -1f),
                            Triple(-2f, -2f, -1f),
                            Triple(-4f,  0f,  0f),
                            Triple(-4f,  2f,  0f),
                            Triple(-4f, -2f,  0f),
                            Triple(-4f,  0f,  2f),
                            Triple(-4f,  0f, -2f),
                            Triple(-3f,  3f,  3f),
                            Triple(-3f, -3f,  3f),
                            Triple(-3f,  3f, -3f),
                            Triple(-3f, -3f, -3f),
                            Triple(-4f, -4f,  0f),
                            Triple(-4f, -4f,  2f),
                            Triple(-4f, -4f, -2f),
                            Triple(-3f, -3f,  0f),
                            Triple(-2f, -4f,  2f),
                            Triple(-2f, -4f, -2f),
                            Triple( 0f,  0f,  4f),
                            Triple( 0f,  0f, -4f),
                            Triple( 4f,  0f,  0f),
                            Triple( 0f,  4f,  0f),
                            Triple( 0f, -4f,  0f),
                            Triple( 4f,  4f,  4f),
                            Triple(-4f,  4f,  4f),
                            Triple( 4f, -4f,  4f),
                            Triple(-4f, -4f,  4f),
                            Triple( 4f,  4f, -4f),
                            Triple(-4f,  4f, -4f),
                            Triple( 4f, -4f, -4f),
                            Triple(-4f, -4f, -4f),
                            Triple( 3f,  0f,  4f),
                            Triple(-3f,  0f,  4f),
                            Triple( 0f,  3f,  4f),
                            Triple( 0f, -3f,  4f),
                            Triple( 3f,  0f, -4f),
                            Triple(-3f,  0f, -4f),
                            Triple( 0f,  3f, -4f),
                            Triple( 0f, -3f, -4f),
                            Triple( 4f,  3f,  0f),
                            Triple( 4f, -3f,  0f),
                            Triple( 4f,  0f,  3f),
                            Triple( 4f,  0f, -3f),
                            Triple(-4f,  3f,  0f),
                            Triple(-4f, -3f,  0f),
                            Triple(-4f,  0f,  3f),
                            Triple(-4f,  0f, -3f),
                            Triple(-5f, -5f,  0f),
                            Triple(-5f, -5f,  3f),
                            Triple(-5f, -5f, -3f),
                            Triple(-5f, -3f,  5f),
                            Triple(-5f,  0f,  5f),
                            Triple(-3f, -5f,  5f),
                            Triple( 0f, -5f,  5f),
                            Triple(-5f, -5f,  5f),
                            Triple(-4f, -3f,  4f),
                            Triple(-3f, -4f,  4f),
                            Triple(-4f, -4f,  3f),
                            Triple(-5f,  3f,  5f),
                            Triple(-5f,  5f,  3f),
                            Triple(-3f,  5f,  5f),
                            Triple(-6f,  0f,  0f),
                            Triple(-6f,  2f,  2f),
                            Triple(-6f, -2f,  2f),
                            Triple(-6f,  2f, -2f),
                            Triple(-6f, -2f, -2f),
                            Triple(-6f,  4f,  0f),
                            Triple(-6f, -4f,  0f),
                            Triple(-6f,  0f,  4f),
                            Triple(-6f,  0f, -4f),
                            Triple(-6f,  4f,  4f),
                            Triple(-6f, -4f,  4f),
                            Triple(-6f,  4f, -4f),
                            Triple(-6f, -4f, -4f),
                            Triple(-5f,  5f,  0f),
                            Triple(-5f, -5f,  0f),
                            Triple(-5f,  0f,  5f),
                            Triple(-5f,  0f, -5f),
                            Triple(-5f,  5f,  5f),
                            Triple(-5f, -5f,  5f),
                            Triple(-5f,  5f, -5f),
                            Triple(-5f, -5f, -5f),
                            Triple(-4f,  6f,  0f),
                            Triple(-4f, -6f,  0f),
                            Triple(-4f,  0f,  6f),
                            Triple(-4f,  0f, -6f),
                            Triple(-3f,  6f,  6f),
                            Triple(-3f, -6f,  6f),
                            Triple(-3f,  6f, -6f),
                            Triple(-3f, -6f, -6f),
                            Triple(-7f,  0f,  0f),
                            Triple(-7f,  3f,  3f),
                            Triple(-7f, -3f,  3f),
                            Triple(-7f,  3f, -3f),
                            Triple(-7f, -3f, -3f),
                            Triple( 6f,  0f,  0f),
                            Triple( 6f,  2f,  2f),
                            Triple( 6f, -2f,  2f),
                            Triple( 6f,  2f, -2f),
                            Triple( 6f, -2f, -2f),
                            Triple( 6f,  4f,  0f),
                            Triple( 6f, -4f,  0f),
                            Triple( 6f,  0f,  4f),
                            Triple( 6f,  0f, -4f),
                            Triple( 6f,  4f,  4f),
                            Triple( 6f, -4f,  4f),
                            Triple( 6f,  4f, -4f),
                            Triple( 6f, -4f, -4f),
                            Triple( 7f,  0f,  0f),
                            Triple( 7f,  3f,  3f),
                            Triple( 7f, -3f,  3f),
                            Triple( 7f,  3f, -3f),
                            Triple( 7f, -3f, -3f),
                            Triple( 5f,  5f,  0f),
                            Triple( 5f,  0f,  5f),
                            Triple( 5f,  5f,  5f),
                            Triple( 5f, -5f,  5f),
                            Triple( 5f,  5f, -5f),
                            Triple( 0f, -6f,  0f),
                            Triple( 0f, -6f,  3f),
                            Triple( 0f, -6f, -3f),
                            Triple( 3f, -6f,  3f),
                            Triple(-3f, -6f,  3f),
                            Triple( 3f, -6f, -3f),
                            Triple(-3f, -6f, -3f),
                            Triple( 6f, -6f,  0f),
                            Triple(-6f, -6f,  0f),
                            Triple( 6f, -6f,  6f),
                            Triple(-6f, -6f,  6f),
                            Triple( 6f, -6f, -6f),
                            Triple(-6f, -6f, -6f),
                            Triple( 0f, -7f,  0f),
                            Triple( 4f, -7f,  4f),
                            Triple(-4f, -7f,  4f),
                            Triple( 4f, -7f, -4f),
                            Triple(-4f, -7f, -4f),
                            Triple( 0f, -8f,  0f),
                            Triple( 3f, -8f,  3f),
                            Triple(-3f, -8f,  3f),
                            Triple( 3f, -8f, -3f),
                            Triple(-3f, -8f, -3f),
                            Triple( 8f,  0f,  0f),
                            Triple( 8f,  4f,  0f),
                            Triple( 8f, -4f,  0f),
                            Triple( 8f,  0f,  4f),
                            Triple( 8f,  0f, -4f),
                            Triple( 8f,  4f,  4f),
                            Triple( 8f, -4f,  4f),
                            Triple( 8f,  4f, -4f),
                            Triple( 8f, -4f, -4f),
                            Triple( 9f,  0f,  0f),
                            Triple( 9f,  3f,  3f),
                            Triple( 9f, -3f,  3f),
                            Triple( 9f,  3f, -3f),
                            Triple( 9f, -3f, -3f),
                            Triple( 7f,  7f,  0f),
                            Triple( 7f, -7f,  0f),
                            Triple( 7f,  0f,  7f),
                            Triple( 7f,  0f, -7f),
                            Triple( 7f,  7f,  7f),
                            Triple( 7f, -7f,  7f),
                            Triple( 7f,  7f, -7f),
                            Triple( 7f, -7f, -7f),
                            Triple(10f,  0f,  0f),
                            Triple(10f,  5f,  5f),
                            Triple(10f, -5f,  5f),
                            Triple(10f,  5f, -5f),
                            Triple(10f, -5f, -5f),
                            Triple( 8f, -8f,  0f),
                            Triple( 8f, -8f,  4f),
                            Triple( 8f, -8f, -4f),
                            Triple( 9f, -9f,  0f),
                            Triple( 9f, -9f,  4f),
                            Triple( 9f, -9f, -4f),
                            Triple(10f, -5f,  0f),
                            Triple(10f, -8f,  0f),
                            Triple(10f, -5f,  5f),
                            Triple(10f, -5f, -5f),
                            Triple(10f, -8f,  5f),
                            Triple(10f, -8f, -5f),
                            Triple( 7f, -9f,  0f),
                            Triple( 7f, -9f,  5f),
                            Triple( 7f, -9f, -5f),
                            Triple( 5f, -10f, 0f),
                            Triple( 5f, -10f, 5f),
                            Triple( 5f, -10f,-5f),
                            Triple( 0f, -10f, 0f),
                            Triple( 3f, -10f, 3f),
                            Triple(-3f, -10f, 3f),
                            Triple( 3f, -10f,-3f),
                            Triple(-3f, -10f,-3f),
                            Triple(-8f, -8f,  0f),
                            Triple(-8f, -8f,  4f),
                            Triple(-8f, -8f, -4f),
                            Triple(-9f, -9f,  0f),
                            Triple(-9f, -9f,  4f),
                            Triple(-9f, -9f, -4f),
                            Triple(-10f, -5f,  0f),
                            Triple(-10f, -8f,  0f),
                            Triple(-10f, -5f,  5f),
                            Triple(-10f, -5f, -5f),
                            Triple(-10f, -8f,  5f),
                            Triple(-10f, -8f, -5f),
                            Triple(-7f, -9f,  0f),
                            Triple(-7f, -9f,  5f),
                            Triple(-7f, -9f, -5f),
                            Triple(-5f, -10f,  0f),
                            Triple(-5f, -10f,  5f),
                            Triple(-5f, -10f, -5f),
                            Triple(-3f, -10f,  3f),
                            Triple(-3f, -10f, -3f),
                            Triple(-8f,  0f, -8f),
                            Triple(-8f,  0f,  8f),
                            Triple(-8f, -5f,  8f),
                            Triple(-8f, -5f, -8f),
                            Triple(-8f, -10f,  0f),
                            Triple(-8f, -10f,  5f),
                            Triple(-8f, -10f, -5f),
                            Triple(-6f, -12f,  0f),
                            Triple(-6f, -12f,  5f),
                            Triple(-6f, -12f, -5f),
                            Triple(-4f, -12f,  4f),
                            Triple(-4f, -12f, -4f),
                            Triple(-2f, -12f,  4f),
                            Triple(-2f, -12f, -4f),
                            Triple( 0f, -12f,  0f),
                            Triple(-10f, -10f,  0f),
                            Triple(-10f, -10f,  5f),
                            Triple(-10f, -10f, -5f),
                            Triple(-12f,  0f,  0f),
                            Triple(-12f, -5f,  0f),
                            Triple(-12f,  0f,  5f),
                            Triple(-12f,  0f, -5f),
                            Triple(-12f, -5f,  5f),
                            Triple(-12f, -5f, -5f),
                            Triple(-10f, -12f,  0f),
                            Triple(-10f, -12f,  5f),
                            Triple(-10f, -12f, -5f),
                            Triple(-8f,  -14f,  0f),
                            Triple(-8f,  -14f,  5f),
                            Triple(-8f,  -14f, -5f),
                            Triple(-5f,  -14f,  5f),
                            Triple(-5f,  -14f, -5f),
                            Triple( 0f,  -14f,  0f),
                            Triple(-12f, -12f,  0f),
                            Triple(-12f, -12f,  5f),
                            Triple(-12f, -12f, -5f),
                            Triple(-14f,  -8f,  0f),
                            Triple(-14f,  -8f,  5f),
                            Triple(-14f,  -8f, -5f),
                            Triple(-14f,  -5f,  5f),
                            Triple(-14f,  -5f, -5f),
                            Triple(-14f, -10f,  0f),
                            Triple(-14f, -10f,  5f),
                            Triple(-14f, -10f, -5f),
                            Triple(-14f, -14f,  0f),
                            Triple(-14f, -14f,  5f),
                            Triple(-14f, -14f, -5f),
                            Triple(-14f,  5f,  0f),
                            Triple(-14f,  8f,  0f),
                            Triple(-14f, 10f,  0f),
                            Triple(-14f,  5f,  5f),
                            Triple(-14f,  5f, -5f),
                            Triple(-14f,  8f,  5f),
                            Triple(-14f,  8f, -5f),
                            Triple(-14f, 10f,  5f),
                            Triple(-14f, 10f, -5f),
                            Triple(-12f, 12f,  0f),
                            Triple(-12f, 12f,  5f),
                            Triple(-12f, 12f, -5f),
                            Triple(-10f, 14f,  0f),
                            Triple(-10f, 14f,  5f),
                            Triple(-10f, 14f, -5f),
                            Triple(-8f,  14f,  0f),
                            Triple(-8f,  14f,  5f),
                            Triple(-8f,  14f, -5f),
                            Triple(-5f,  14f,  5f),
                            Triple(-5f,  14f, -5f),
                            Triple( 0f,  14f,  0f),
                            Triple(-14f,  0f,  8f),
                            Triple(-14f,  0f, -8f),
                            Triple(-14f, -14f,  8f),
                            Triple(-14f, -14f, -8f),
                            Triple(-12f, -14f,  8f),
                            Triple(-12f, -14f, -8f),
                            Triple(-10f, -14f,  8f),
                            Triple(-10f, -14f, -8f),
                            Triple(-8f,  -14f,  8f),
                            Triple(-8f,  -14f, -8f),
                            Triple(-16f, -10f,  0f),
                            Triple(-16f, -10f,  8f),
                            Triple(-16f, -10f, -8f),
                            Triple(-16f, -14f,  0f),
                            Triple(-16f, -14f,  8f),
                            Triple(-16f, -14f, -8f),
                            Triple(-16f,  -5f,  8f),
                            Triple(-16f,  -5f, -8f),
                            Triple(-18f,  -8f,  0f),
                            Triple(-18f,  -8f,  8f),
                            Triple(-18f,  -8f, -8f),
                            Triple(-18f, -12f,  0f),
                            Triple(-18f, -12f,  8f),
                            Triple(-18f, -12f, -8f),
                            Triple(-20f,  0f,   0f),
                            Triple(-20f, -10f,  0f),
                            Triple( 0f,  -20f,  0f),
                            Triple( 5f,  -20f,  5f),
                            Triple(-5f,  -20f,  5f),
                            Triple( 5f,  -20f, -5f),
                            Triple(-5f,  -20f, -5f),
                            Triple( 10f, -18f,  0f),
                            Triple( 10f, -18f,  8f),
                            Triple( 10f, -18f, -8f),
                            Triple(-10f, -18f,  0f),
                            Triple(-10f, -18f,  8f),
                            Triple(-10f, -18f, -8f),
                            Triple( 14f, -14f,  0f),
                            Triple( 14f, -14f,  8f),
                            Triple( 14f, -14f, -8f),
                            Triple( 16f, -10f,  0f),
                            Triple( 16f, -10f,  8f),
                            Triple( 16f, -10f, -8f),
                            Triple( 18f,  -8f,  0f),
                            Triple( 18f,  -8f,  8f),
                            Triple( 18f,  -8f, -8f),
                            Triple( 20f,  0f,   0f),
                            Triple( 20f, -10f,  0f),
                            Triple( 20f, -10f,  8f),
                            Triple( 20f, -10f, -8f),
                            Triple(-20f,  5f,  0f),
                            Triple(-20f,  5f,  8f),
                            Triple(-20f,  5f, -8f),
                            Triple(-20f, -5f,  0f),
                            Triple(-20f, -5f,  8f),
                            Triple(-20f, -5f, -8f),
                            Triple(-18f,  10f,  0f),
                            Triple(-18f,  10f,  8f),
                            Triple(-18f,  10f, -8f),
                            Triple(-16f,  12f,  0f),
                            Triple(-16f,  12f,  8f),
                            Triple(-16f,  12f, -8f),
                            Triple(-20f,  0f,  10f),
                            Triple(-20f,  0f, -10f),
                            Triple(-20f, -5f,  10f),
                            Triple(-20f, -5f, -10f),
                            Triple(-20f,  5f,  10f),
                            Triple(-20f,  5f, -10f),
                            Triple(-20f, -15f,  0f),
                            Triple(-20f, -15f,  8f),
                            Triple(-20f, -15f, -8f),
                            Triple(-18f, -16f,  0f),
                            Triple(-18f, -16f,  8f),
                            Triple(-18f, -16f, -8f),
                            Triple(-15f, -18f,  0f),
                            Triple(-15f, -18f,  8f),
                            Triple(-15f, -18f, -8f),
                            Triple(-12f, -20f,  0f),
                            Triple(-12f, -20f,  8f),
                            Triple(-12f, -20f, -8f),
                            Triple(-8f,  -22f,  0f),
                            Triple(-8f,  -22f,  8f),
                            Triple(-8f,  -22f, -8f),
                            Triple(-5f,  -22f,  5f),
                            Triple(-5f,  -22f, -5f),
                            Triple( 0f,  -22f,  0f),
                            Triple(-22f, -10f,  0f),
                            Triple(-22f, -10f,  8f),
                            Triple(-22f, -10f, -8f),
                            Triple(-22f,  0f,   0f),
                            Triple(-22f,  0f,   8f),
                            Triple(-22f,  0f,  -8f),
                            Triple(-20f,  15f,  0f),
                            Triple(-20f,  15f,  8f),
                            Triple(-20f,  15f, -8f),
                            Triple(-18f,  16f,  0f),
                            Triple(-18f,  16f,  8f),
                            Triple(-18f,  16f, -8f),
                            Triple(-15f,  18f,  0f),
                            Triple(-15f,  18f,  8f),
                            Triple(-15f,  18f, -8f),
                            Triple(-12f,  20f,  0f),
                            Triple(-12f,  20f,  8f),
                            Triple(-12f,  20f, -8f),
                            Triple(-8f,   22f,  0f),
                            Triple(-8f,   22f,  8f),
                            Triple(-8f,   22f, -8f),
                            Triple(-5f,   22f,  5f),
                            Triple(-5f,   22f, -5f),
                            Triple( 0f,   22f,  0f),
                            Triple(-22f,  10f,  0f),
                            Triple(-22f,  10f,  8f),
                            Triple(-22f,  10f, -8f),
                            Triple(-22f,  15f,  0f),
                            Triple(-22f,  15f,  8f),
                            Triple(-22f,  15f, -8f),
                        )

                        lightPositions.forEach { (x, y, z) ->
                            val light = entityManager.create()
                            LightManager.Builder(LightManager.Type.POINT)
                                .intensity(800_000f)
                                .position(x, y, z)
                                .falloff(100f)
                                .build(engine, light)
                            modelViewer.scene.addEntity(light)
                        }

                        modelViewer.camera.lookAt(
                            0.0, 0.0, 2.0,
                            0.0, 0.0, 0.0,
                            0.0, 1.0, 0.0
                        )
                    }
                }

                val choreographer = Choreographer.getInstance()
                val frameCallback = object : Choreographer.FrameCallback {
                    override fun doFrame(frameTimeNanos: Long) {
                        choreographer.postFrameCallback(this)
                        modelViewer.render(frameTimeNanos)
                    }
                }
                choreographer.postFrameCallback(frameCallback)
                surfaceView
            }
        )

        //logo
        Image(
            painter = painterResource(id = R.drawable.logo_eurofarma),
            contentDescription = "Logo",
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 48.dp)
                .height(40.dp)
        )

        //texto
        Column(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 28.dp, top = 110.dp)
                .clickable { onNavigateToNext() }
        ) {
            Text(text = "Seja bem-vindo ao", color = Color.White, fontSize = 18.sp)
            Text(text = "Onboarding", color = Color.White, fontSize = 32.sp, fontWeight = FontWeight.Bold)
            Text(text = "360°", color = Color.White, fontSize = 50.sp, fontWeight = FontWeight.ExtraLight, fontStyle = FontStyle.Italic)
        }
    }
}