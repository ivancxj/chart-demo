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
//		for (int i = 0; i < titles.length; i++) {
			x.add(new double[] { 1, 2, 3});
//		}

		// 填充Y数据(与X对应)
		List<double[]> values = new ArrayList<double[]>();
		values.add(new double[] { 12.3, 12.5, 13.8});

		// 折线颜色(可以多组,但是要与 上面的x/y 数据组数对应)
		int[] colors = new int[] { Color.GREEN };
		// 画的point类型
		PointStyle[] styles = new PointStyle[] { PointStyle.CIRCLE };
		
		// 创建Renderer
		XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);
		// 设置Point 尺寸
		renderer.setChartTitle("");//填入空表示 不显示 title
		renderer.setXTitle("收入项目");//x轴
		renderer.setYTitle("金额(万元)");
		renderer.setXAxisMin(0);
		renderer.setXAxisMax(6.5);
		renderer.setYAxisMin(0);
		renderer.setYAxisMax(20);
		renderer.setInScroll(true);
		//设置外边框颜色
		renderer.setMarginsColor(Color.parseColor("#E7F3F7"));
		renderer.setAxesColor(Color.GRAY);//x y轴颜色
		renderer.setLabelsColor(Color.GREEN);//x y轴 laberls 颜色
		renderer.setLabelsTextSize(16);//################################################

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
		
		renderer.setXLabels(0);
		renderer.setYLabels(10);

//		setChartSettings(renderer, "混合图形", "月", "温度", 0.5, 12.5, 0, 40,
//				Color.LTGRAY, Color.LTGRAY);
		
        //设置X轴##########################################################
		renderer.addXTextLabel(1, "医疗");
		renderer.addXTextLabel(2, "药品");
		renderer.addXTextLabel(3, "非药品");

//	    renderer.setYAxisAlign(Align.RIGHT,0);
	    
		renderer.setXLabelsAlign(Align.LEFT);//从左到右
		renderer.setYLabelsAlign(Align.LEFT);
		renderer.setPanEnabled(true, false);//x轴可移动   y轴不能到(参数自己可以改)
		// renderer.setZoomEnabled(false);
		renderer.setZoomRate(1.1f);
		renderer.setBarSpacing(0.5f);
		
//		renderer.setPanLimits(new double[] { -10, 20, -10, 40 });
//		renderer.setZoomLimits(new double[] { -10, 20, -10, 40 });

		XYSeries waterSeries = new XYSeries("柱型");
		// 填充数据
		waterSeries.add(1, 16);
		waterSeries.add(2, 15);
		waterSeries.add(3, 16);
		renderer.setBarSpacing(0.5);
		XYSeriesRenderer waterRenderer = new XYSeriesRenderer();
		waterRenderer.setColor(Color.argb(250, 0, 210, 250));

		XYMultipleSeriesDataset dataset = buildDataset(titles, x, values);
		dataset.addSeries(0, waterSeries);
		System.err.println(renderer.getSeriesRendererCount());
		renderer.addSeriesRenderer(0, waterRenderer);
		
		System.err.println(renderer.getSeriesRendererCount());
		
		renderer.setYAxisAlign(Align.RIGHT, 0);
//		renderer.setYAxisAlign(Align.RIGHT, 1);
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
