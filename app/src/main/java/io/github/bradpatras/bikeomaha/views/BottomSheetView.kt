package io.github.bradpatras.bikeomaha.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.text.TextPaint
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import io.github.bradpatras.bikeomaha.R
import io.github.bradpatras.bikeomaha.databinding.BottomSheetViewBinding
import kotlinx.android.synthetic.main.bottom_sheet_view.view.*

class BottomSheetView : CoordinatorLayout {
    lateinit var binding: BottomSheetViewBinding

    val bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout> by lazy {
        BottomSheetBehavior.from(binding.sheetView)
    }

    constructor(context: Context) : super(context) {
        init(null, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        init(attrs, defStyle)
    }

    private fun init(attrs: AttributeSet?, defStyle: Int) {
        //binding = BottomSheetViewBinding.inflate(LayoutInflater.from(context), this, true)
        binding = BottomSheetViewBinding.bind(inflate(context, R.layout.bottom_sheet_view, this))
    }
}
