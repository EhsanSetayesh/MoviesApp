package com.ehsansetayesh.core.common.extension

//fun <T> Fragment.flowLife(flow: Flow<T>, collector: FlowCollector<T>) {
//    viewLifecycleOwner.lifecycleScope.launch {
//        repeatOnLifecycle(Lifecycle.State.STARTED) {
//            flow.collect(collector)
//        }
//    }
//}
//fun <T> AppCompatActivity.flowLife(flow: Flow<T>, collector: FlowCollector<T>) {
//    lifecycleScope.launch {
//        repeatOnLifecycle(Lifecycle.State.STARTED) {
//            flow.collect(collector)
//        }
//    }
//}