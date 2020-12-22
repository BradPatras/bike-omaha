package io.github.bradpatras.bikeomaha.views

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import io.github.bradpatras.bikeomaha.R
import io.github.bradpatras.bikeomaha.databinding.BottomSheetViewBinding

class BottomSheetView : CoordinatorLayout {
    lateinit var binding: BottomSheetViewBinding

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
    }
}
