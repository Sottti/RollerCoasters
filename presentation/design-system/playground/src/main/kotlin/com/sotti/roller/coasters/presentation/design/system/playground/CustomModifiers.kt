package com.sotti.roller.coasters.presentation.design.system.playground

//private data class PulsateElement(
//    val enabled: Boolean,
//) : ModifierNodeElement<PulsateNode>() {
//    override fun create(): PulsateNode = PulsateNode(enabled)
//
//    override fun update(node: PulsateNode) {
//        node.enabled = enabled
//    }
//
//    override fun InspectorInfo.inspectableProperties() {
//        name = "pulsate"
//        properties["enabled"] = enabled
//    }
//}
//
//private class PulsateNode(
//    var enabled: Boolean,
//) : Modifier.Node() {
//    private val scale = Animatable(1f)
//
//    override fun onAttach() {
//        if (enabled) {
//            coroutineScope.launch {
//                while (true) {
//                    scale.animateTo(1.2f, animationSpec = tween(500))
//                    scale.animateTo(1f, animationSpec = tween(500))
//                }
//            }
//        }
//    }
//
//    override fun onDetach() {
//        scale.stop()
//    }
//
//    fun getScale(): Float = scale.value
//}

// Extension function to apply the custom modifier
//private fun Modifier.pulsate(enabled: Boolean = true) = this.then(PulsateElement(enabled))
