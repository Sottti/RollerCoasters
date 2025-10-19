package com.sottti.roller.coasters.presentation.design.system.playground

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// --- 1. State Definition (A Simple Stable Class for the test) ---
// We use a property delegate for 'name' to make it Observable.
// We make the class explicitly @Stable to ensure the compiler gives us the best
// chance to skip when only 'id' changes (which is what we want to test).

@Stable
private class MyState(initialId: Int, initialName: String) {
    var id: Int by mutableStateOf(initialId)
    var name: String by mutableStateOf(initialName)
}

// Global counter for logging to distinguish runs in the console
private var recompositionCounter = 0

// --- 2. The Root Stateful Composable ---
@Composable
private fun RecompositionDemonstrator() {
    val state = remember { MyState(1, "Alice") }
    var trigger by remember { mutableStateOf(0) }

    // Logic to cause a state change that affects ID but NOT NAME
    Button(onClick = {
        state.id += 1 // Changes state.id, but not state.name
        trigger += 1  // Force A to recompose
    }) {
        Text("Click to change state.id (Current ID: ${state.id})")
    }

    // This section forces A to run, which in turn calls the two test D variants.
    Spacer(Modifier.height(16.dp))
    Column {
        // Run A, which calls the two D variants
        A(state)
    }
}

// --- 3. The Parent Composable A (Connects the State to D) ---
@Composable
private fun A(state: MyState) {
    recompositionCounter++
    println("A recomposed. Count: $recompositionCounter")

    // Calls B and C (omitted for brevity)

    // Test 1: Passing the entire object
    D_FullObject(state)

    // Test 2: Passing only the required property (State Hoisting)
    D_HoistedProperty(state.name)
}

// --------------------------------------------------------------------------------

// --- 4. Test Variant 1: Passing the Full Object ---
@Composable
private fun D_FullObject(state: MyState) {
    recompositionCounter++
    println(">>> D_FullObject recomposed. Count: $recompositionCounter. Name: ${state.name}, ID: ${state.id}")

    // Reads only state.name
    Text("D_FullObject Name: ${state.name}")
}

// --- 5. Test Variant 2: Passing Only the Hoisted Property ---
@Composable
private fun D_HoistedProperty(name: String) {
    recompositionCounter++
    println(">>> D_HoistedProperty recomposed. Count: $recompositionCounter. Name: $name")

    // Reads only the hoisted name string
    Text("D_HoistedProperty Name: $name")
}

// --------------------------------------------------------------------------------

@Preview
@Composable
private fun RecompositionDemoPreview() {
    MaterialTheme {
        Column {
            RecompositionDemonstrator()
        }
    }
}
