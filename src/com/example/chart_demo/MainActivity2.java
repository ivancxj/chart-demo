package com.example.chart_demo;

import java.util.ArrayList;
import java.util.List;

import org.achartengine.GraphicalView;
import org.achartengine.chart.AbstractChart;
import org.achartengine.chart.BarChart;
import org.achartengine.chart.CombinedXYChart;
import org.achartengine.chart.LineChart;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.os.Bundle;

//把AndroidManfest.xml文件的17行
//android:name="com.example.chart_demo.MainActivity2" 
//修改为
//android:name="com.example.chart_demo.MainActivity"
//可以看到第一种实现方式

// 第二种方式 创建View
public class MainActivity2 extends AbstractDemoChart {

	GraphicalView mView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mView = new GraphicalView(this, getChart());
		setContentView(mView);
	}
	
	protected AbstractChart getChart(){
		String[] titles = new String[] { "折线" };
		// 填充X数据(可以多组)
		List<double[]> x = new ArrayList<double[]>();
		for (int i = 0; i < titles.length; i++) {
			x.add(new double[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 });
		}

		// 填充Y数据(与X对应)
		List<double[]> values = new ArrayList<double[]>();
		values.add(new double[] { 12.3, 12.5, 13.8, 16.8, 20.4, 24.4, 26.4,
				26.1, 23.6, 20.3, 17.2, 13.9 });

		// 折线颜色(可以多组,但是要与 上面的x/y 数据组数对应)
		int[] colors = new int[] { Color.GREEN };
		// 画的point类型
		PointStyle[] styles = new PointStyle[] { PointStyle.CIRCLE };
		
		// 创建Renderer
		XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);
		// 设置Point 尺寸
		renderer.setPointSize(5.5f);
		
		// 目前只有填充了上面一组
		int length = renderer.getSeriesRendererCount();
		for (int i = 0; i < length; i++) {
			XYSeriesRenderer r = (XYSeriesRenderer) renderer
					.getSeriesRendererAt(i);
			r.setLineWidth(5);
			r.setFillPoints(true);
		}

		setChartSettings(renderer, "混合图形", "月", "温度", 0.5, 12.5, 0, 40,
				Color.LTGRAY, Color.LTGRAY);

		renderer.setXLabels(12);
		renderer.setYLabels(10);
		renderer.setShowGrid(true);
		renderer.setXLabelsAlign(Align.RIGHT);
		renderer.setYLabelsAlign(Align.RIGHT);
		// 显示缩放按钮
		renderer.setZoomButtonsVisible(true);
		renderer.setPanLimits(new double[] { -10, 20, -10, 40 });
		renderer.setZoomLimits(new double[] { -10, 20, -10, 40 });

		XYSeries waterSeries = new XYSeries("柱型");
		// 填充数据
		waterSeries.add(1, 16);
		waterSeries.add(2, 15);
		waterSeries.add(3, 16);
		waterSeries.add(4, 17);
		waterSeries.add(5, 20);
		waterSeries.add(6, 23);
		waterSeries.add(7, 25);
		waterSeries.add(8, 25.5);
		waterSeries.add(9, 26.5);
		waterSeries.add(10, 24);
		waterSeries.add(11, 22);
		waterSeries.add(12, 18);
		renderer.setBarSpacing(0.5);
		XYSeriesRenderer waterRenderer = new XYSeriesRenderer();
		waterRenderer.setColor(Color.argb(250, 0, 210, 250));

		XYMultipleSeriesDataset dataset = buildDataset(titles, x, values);
		dataset.addSeries(0, waterSeries);
		renderer.addSeriesRenderer(0, waterRenderer);
		waterRenderer.setDisplayChartValues(true);
		waterRenderer.setChartValuesTextSize(10);

		// 传入两种类型(柱状/线型)
		String[] types = new String[] { BarChart.TYPE, LineChart.TYPE };
		
		// ======================================
		
		return new CombinedXYChart(dataset, renderer, types);
	}

	// 通过这个方法的实现 在 MainActivity.class
	@Override
	protected Intent execute(Context context) {
		return null;
	}
	
}
