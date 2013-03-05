/*package com.tianrunjs.trjsdss.context.trjsdss1;

import java.util.ArrayList;
import java.util.List;

import org.achartengine.chart.AbstractChart;
import org.achartengine.chart.BarChart;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;

import android.graphics.Color;
import android.graphics.Paint.Align;
import com.tianrunjs.trjsdss.bean.TableData.Data;
import com.tianrunjs.trjsdss.context.BaseChartActivity;


public class BarChartActivity1_1 extends BaseChartActivity
{
	@Override
	protected AbstractChart getChart()
	{
		String[] titles = new String[] { "本年数","上年数" };
		List<double[]> values = new ArrayList<double[]>();
		
		//设置Y轴########################################################
		int len = result.size();
		len=3;
		double[] v_week = new double[len];
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
		values.add(v_Month);
		
		
		int maxvalue=(int)maxValue(v_week,v_Month);
		//总体设置###########################################################
		int[] colors = new int[] { Color.parseColor("#E5007F"), Color.parseColor("#01B1EC") };//颜色自己修改
		XYMultipleSeriesRenderer renderer = buildBarRenderer(colors);
		
		renderer.setChartTitle("");//填入空表示 不显示 title
		renderer.setXTitle("收入项目");//x轴
		renderer.setYTitle("金额(万元)");
		renderer.setXAxisMin(1.5);
		renderer.setXAxisMax(6.5);
		renderer.setYAxisMin(0);
		renderer.setYAxisMax(maxvalue);
		renderer.setInScroll(true);
		//设置外边框颜色
		renderer.setMarginsColor(Color.parseColor("#E7F3F7"));
		renderer.setAxesColor(Color.GRAY);//x y轴颜色
		renderer.setLabelsColor(Color.GREEN);//x y轴 laberls 颜色
		renderer.setLabelsTextSize(16);//################################################

		
	    int length = renderer.getSeriesRendererCount();
	    for (int i = 0; i < length; i++) {
	      SimpleSeriesRenderer seriesRenderer = renderer.getSeriesRendererAt(i);
	      seriesRenderer.setDisplayChartValues(true);
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
		
		XYMultipleSeriesDataset dataset = buildBarDataset(titles, values);
		return new BarChart(dataset, renderer, Type.DEFAULT);//Type.STACKED
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

}
*/