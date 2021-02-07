package io.github.bradpatras.bikeomaha.views

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import io.github.bradpatras.bikeomaha.R
import io.github.bradpatras.bikeomaha.adapters.TrailAdapter
import io.github.bradpatras.bikeomaha.databinding.BottomSheetViewBinding

class TrailListBottomSheetView : CoordinatorLayout {
    private lateinit var binding: BottomSheetViewBinding

    val listAdapter: TrailAdapter?
        get() { return binding.trailList.adapter as? TrailAdapter }

    val bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout> by lazy {
        BottomSheetBehavior.from(binding.sheetView)
    }

    constructor(context: Context) : super(context) {
        setup()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        setup()
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        setup()
    }

    private fun setup() {
        binding = BottomSheetViewBinding.bind(inflate(context, R.layout.bottom_sheet_view, this))
        binding.trailList.adapter = TrailAdapter()
        binding.trailList.layoutManager = LinearLayoutManager(context)
    }
}
