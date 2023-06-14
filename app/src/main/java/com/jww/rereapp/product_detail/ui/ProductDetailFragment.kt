package com.jww.rereapp.product_detail.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.jww.rereapp.R
import com.jww.rereapp.base.BaseFragment
import com.jww.rereapp.databinding.FragmentProductDetailBinding
import com.jww.rereapp.extension.getColorListChart
import com.jww.rereapp.extension.repeatOnStarted
import com.jww.rereapp.extension.throttleClick
import com.jww.rereapp.item_model.ProductAdapterItem
import com.jww.rereapp.product_detail.ProductDetailViewModel
import com.jww.rereapp.re_evaluate.ui.ReEvaluateFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ProductDetailFragment : BaseFragment() {
    private var _binding: FragmentProductDetailBinding? = null
    private val binding
        get() = _binding!!
    private val viewModel by viewModel<ProductDetailViewModel> {
        parametersOf(arguments?.getSerializable(ProductAdapterItem::class.simpleName))
    }

    fun putArgument(
        productAdapterItem: ProductAdapterItem?
    ) {
        arguments = bundleOf().apply {
            putSerializable(ProductAdapterItem::class.simpleName, productAdapterItem)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind()
        observe()
        initPieChartOption(binding.content.sexChart.pieChart)
        initPieChartOption(binding.content.ageChart.pieChart)
        viewModel.requestSexData()
        viewModel.requestAgeData()
    }

    private fun bind() {
        binding.run {
            vm = viewModel
            lifecycleOwner = viewLifecycleOwner

            register.throttleClick {
                parentFragmentManager.beginTransaction()
                    .add(R.id.container, ReEvaluateFragment().apply {
                        putArgument(viewModel.productAdapterItem)
                    }).addToBackStack(null).commit()
            }
        }
    }

    private fun observe() {
        repeatOnStarted {
            viewModel.eventFlow().collect {
                handle(it)
            }
        }
    }

    private fun setPieChart(pieChartView: PieChart, pieDataSet: PieDataSet) {
        val pieData = pieDataSet.run {
            PieData(this).apply { setDrawValues(true) }
        }
        pieChartView.apply { this.data = pieData }.invalidate()
    }

    private fun initPieChartOption(pieChartView: PieChart) {
        pieChartView.apply {
            this.setUsePercentValues(true) //금액 대신 백분율을 값으로 상용
            this.description.isEnabled = false // 왼쪽 하단 모서리에 있는 설명 레이블을 제거 합니다. 설정되지 않은 경우 기본값은 true
            this.isRotationEnabled = false //사용자가 차트를 회전할수 있도록 함, 기본값은 true
            this.dragDecelerationFrictionCoef = 0.9f // 원형 차트 회전시 마찰 추가
            this.rotationAngle = 0.0f
            this.isHighlightPerTapEnabled = true // 항목을 탭하면 항목이 강조 표시 됩니다. 설정지 않은 경우 기본값은 true
            this.animateY(1400, Easing.EaseInOutQuad) // 항목이 0도에서 팝업되도록 애니메이션 추가
//            this.setHoleColor(ContextCompat.getColor(context, R.color.white)) // 가운데 구멍의 색상 설정, 흰색

//            속빈 원형 차트 대신 단색 원형 차트를 사용하려면 옵션추가
            this.holeRadius = 0.0f
            this.transparentCircleRadius = 0.0f
//
        }
    }

    private fun handle(event: ProductDetailViewModel.Event) {
        when (event) {
            is ProductDetailViewModel.Event.ResultSexListData -> {
                val label = "성별"
                val pieData = PieDataSet(event.resultList, label).apply {
                    valueTextSize = 12f
                    colors = context?.getColorListChart()
                }
                setPieChart(binding.content.sexChart.pieChart, pieData)
            }
            is ProductDetailViewModel.Event.ResultAgeListData -> {
                val label = "나이"
                val pieData = PieDataSet(event.resultList, label).apply {
                    valueTextSize = 12f
                    colors = context?.getColorListChart()
                }
                setPieChart(binding.content.ageChart.pieChart, pieData)
            }
        }
    }
}