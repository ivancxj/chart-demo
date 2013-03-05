/*package com.tianrunjs.trjsdss.context.trjsdss1;

import java.util.ArrayList;
import java.util.List;

import org.achartengine.chart.AbstractChart;
import org.achartengine.chart.BarChart;
import org.achartengine.chart.CombinedXYChart;
import org.achartengine.chart.LineChart;
import org.achartengine.chart.PointStyle;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.graphics.Color;
import android.graphics.Paint.Align;
import com.tianrunjs.trjsdss.bean.TableData.Data;
import com.tianrunjs.trjsdss.context.BaseChartActivity;


public class BarChartActivity1_2 extends BaseChartActivity
{
	@Override
	protected AbstractChart getChart()
	{
		String[] titles = new String[] { "本年数"};
		List<double[]> values = new ArrayList<double[]>();
		
		//设置Y轴########################################################
		int len = result.size();
		len=3;
		// v_week 折线 X数据
		List<double[]> x = new ArrayList<double[]>();
		x.add(new double[] { 1, 2, 3});
//		for (int i = 0; i < len; i++) {
//			x.add(new double[] { 1, 2, 3});
//		}
		// v_week 折线 Y数据
		double[] v_week = new double[len];
		// v_Month 株型 Y数据
		double[] v_Month = new double[len];
		List<Data> rowData=result.get(2).getData();
		for (Data data : rowData) {
			//System.out.println("Y==="+data.getY()+";X=="+data.getX()+";data==="+data.getT().toString());
			if(data.getY() == 1 && data.getX() == 1){
				v_week[0]=Double.parseDouble(data.getT().toString());
			}
			else if(data.getY() == 2 && data.getX() == 1){
				v_week[1]=Double.parseDouble(data.getT().toString());
			}
			else if(data.getY() == 4 && data.getX() == 1){
				v_week[2]=Double.parseDouble(data.getT().toString());
			}
			
			else if(data.getY() == 1 && data.getX() == 2){
				v_Month[0]=Double.parseDouble(data.getT().toString());
			}
			else if(data.getY() == 2 && data.getX() == 2){
				v_Month[1]=Double.parseDouble(data.getT().toString());
			}
			else if(data.getY() == 4 && data.getX() == 2){
				v_Month[2]=Double.parseDouble(data.getT().toString());
			}
		}
		
		for(int i=0;i<len;i++){
			v_week[i]=(int)v_week[i]/10000;
			v_Month[i]=(int)v_Month[i]/10000;
		}

		//添加数据
		values.add(v_week);
//		values.add(v_Month);
		
		int maxvalue=(int)maxValue(v_week,v_Month);
		//总体设置###########################################################
		int[] colors = new int[] { Color.parseColor("#E5007F") };//颜色自己修改
		// 画的point类型
		PointStyle[] styles = new PointStyle[] { PointStyle.CIRCLE };
		
		// 创建Renderer
		XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);
		
		renderer.setChartTitle("");//填入空表示 不显示 title
		renderer.setXTitle("收入项目");//x轴
		renderer.setYTitle("金额(万元)");
//		renderer.setXAxisMin(1.5);
		renderer.setXAxisMin(0);
		renderer.setXAxisMax(6.5);
		renderer.setYAxisMin(0);
		renderer.setYAxisMax(maxvalue);
		renderer.setInScroll(true);
		//设置外边框颜色
		renderer.setMarginsColor(Color.parseColor("#E7F3F7"));
		renderer.setAxesColor(Color.GRAY);//x y轴颜色
		renderer.setLabelsColor(Color.GREEN);//x y轴 laberls 颜色
		renderer.setLabelsTextSize(16);//################################################

		// 设置Point 尺寸
		renderer.setPointSize(5.5f);
		
		int length = renderer.getSeriesRendererCount();
		for (int i = 0; i < length; i++) {
			XYSeriesRenderer r = (XYSeriesRenderer) renderer
					.getSeriesRendererAt(i);
			r.setLineWidth(5);
			r.setFillPoints(true);
		}
		renderer.setXLabels(0);
		renderer.setYLabels(10);
		
        //设置X轴##########################################################
		renderer.addXTextLabel(1, "医疗");
		renderer.addXTextLabel(2, "药品");
		renderer.addXTextLabel(3, "非药品");

		renderer.setXLabelsAlign(Align.LEFT);//从左到右
		renderer.setYLabelsAlign(Align.LEFT);
		renderer.setPanEnabled(true, false);//x轴可移动   y轴不能到(参数自己可以改)
		// renderer.setZoomEnabled(false);
		renderer.setZoomRate(1.1f);
		renderer.setBarSpacing(0.5f);
		
		XYSeries waterSeries = new XYSeries("上年数");
		// 填充数据
		for(int i = 0;i<len;i++){
			waterSeries.add(i, v_Month[i]);
		}
		
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
	
	private XYMultipleSeriesRenderer buildBarRenderer(int[] colors)
	{
		XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
		renderer.setAxisTitleTextSize(16);
		renderer.setChartTitleTextSize(20);
		renderer.setLabelsTextSize(15);
		renderer.setLegendTextSize(15);
		int length = colors.length;
		for (int i = 0; i < length; i++)
		{
			SimpleSeriesRenderer r = new SimpleSeriesRenderer();
			r.setColor(colors[i]);
			r.setChartValuesTextSize(14);//###############################################
			renderer.addSeriesRenderer(r);
		}
		return renderer;
	}
	
	private XYMultipleSeriesDataset buildBarDataset(String[] titles,
			List<double[]> values)
	{
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		int length = titles.length;
		for (int i = 0; i < length; i++)
		{
			CategorySeries series = new CategorySeries(titles[i]);
			double[] v = values.get(i);
			int seriesLength = v.length;
			for (int k = 0; k < seriesLength; k++)
			{
				series.add(v[k]);
			}
			dataset.addSeries(series.toXYSeries());
		}
		return dataset;
	}
	
	protected XYMultipleSeriesRenderer buildRenderer(int[] colors,
			PointStyle[] styles) {
		XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
		setRenderer(renderer, colors, styles);
		return renderer;
	}
	
	protected void setRenderer(XYMultipleSeriesRenderer renderer, int[] colors,
			PointStyle[] styles) {
		renderer.setAxisTitleTextSize(16);
		renderer.setChartTitleTextSize(20);
		renderer.setLabelsTextSize(15);
		renderer.setLegendTextSize(15);
		renderer.setPointSize(5f);
		renderer.setMargins(new int[] { 20, 30, 15, 20 });
		int length = colors.length;
		for (int i = 0; i < length; i++) {
			XYSeriesRenderer r = new XYSeriesRenderer();
			r.setColor(colors[i]);
			r.setPointStyle(styles[i]);
			renderer.addSeriesRenderer(r);
		}
	}
	
	protected XYMultipleSeriesDataset buildDataset(String[] titles,
			List<double[]> xValues, List<double[]> yValues) {
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		addXYSeries(dataset, titles, xValues, yValues, 0);
		return dataset;
	}

	public void addXYSeries(XYMultipleSeriesDataset dataset, String[] titles,
			List<double[]> xValues, List<double[]> yValues, int scale) {
		int length = titles.length;
		for (int i = 0; i < length; i++) {
			XYSeries series = new XYSeries(titles[i], scale);
			double[] xV = xValues.get(i);
			double[] yV = yValues.get(i);
			int seriesLength = xV.length;
			for (int k = 0; k < seriesLength; k++) {
				series.add(xV[k], yV[k]);
			}
			dataset.addSeries(series);
		}
	}

}
*/